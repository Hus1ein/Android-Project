package com.selma.constructions.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.selma.constructions.Fragments.JobsTypesFragment;
import com.selma.constructions.GetDataAsArray;
import com.selma.constructions.PostData;
import com.selma.constructions.R;
import com.selma.constructions.adapter.EmployeesListAdapter;
import com.selma.constructions.adapter.ProjectsAdapter;
import com.selma.constructions.model.Employee;
import com.selma.constructions.model.JobTypeRow;
import com.selma.constructions.model.Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeesListActivity extends BaseActivityForAsyncTask implements EmployeesListAdapter.OnEmployeeClick, EmployeesListAdapter.OnWorkHoursTextViewClick{

    private long jobTypeId;
    private List<Employee> employees;
    public static final String EMPLOYEE = "employee";
    private RelativeLayout mainLayout;
    private ProgressBar progressBar;
    private EmployeesListAdapter employeesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        jobTypeId = getIntent().getLongExtra(JobsTypesFragment.JOB_ID, 0);

        getSupportActionBar().setTitle("Svi uposlenici");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainLayout = findViewById(R.id.activity_employees_list_main_layout);
        progressBar = findViewById(R.id.activity_employees_list_progress_bar);

        getAllEmployees();
    }

    private void getAllEmployees(){

        progressBar.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
        GetDataAsArray getDataAsArray = new GetDataAsArray(this);
        String url = "http://www.mocky.io/v2/5bdd58253200005a008c625f";  //TODO: change url.
        getDataAsArray.execute(url, jobTypeId + "");
    }

    @Override
    public void getDataAsArray(JSONArray result) {
        employees = new ArrayList<>();

        if (result != null) {
            for (int n = 0; n < result.size(); n++) {
                JSONObject object = (JSONObject) result.get(n);
                Employee employee = new Employee();
                employee.setId((Long)object.get("Id"));
                employee.setName(object.get("Ime").toString() + " " + object.get("Prezime").toString());
                employee.setRank(object.get("Zvanje").toString());
                employee.setSocialNumber(object.get("JMBG").toString());
                employee.setDate((object.get("DatumRodjenja").toString()));
                employee.setEmail(object.get("Email").toString());
                employee.setPhone(object.get("KontaktTelefon").toString());
                employee.setAddress(object.get("Adresa").toString());
                employee.setWorkHours(Integer.parseInt(object.get("RadniSati").toString()));
                employees.add(employee);
            }

            progressBar.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);

            createRecyclerView();

        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void createRecyclerView()
    {
        RecyclerView employeesRecyclerView = findViewById(R.id.activity_employees_list_recycler_view);
        employeesListAdapter = new EmployeesListAdapter(employees, false);
        final LinearLayoutManager employeesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        employeesRecyclerView.setLayoutManager(employeesLayoutManager);
        employeesRecyclerView.setAdapter(employeesListAdapter);
        employeesListAdapter.setOnEmployeeClick(this);
        employeesListAdapter.setOnWorkHoursTextViewClick(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(long employeeId, RelativeLayout relativeLayout) {

        for (Employee employee : employees){

            if (employee.getId() == employeeId) {

                Intent intent = new Intent(this, EmployeeActivity.class);
                intent.putExtra(EMPLOYEE, employee);
                startActivity(intent);

                break;
            }

        }

    }

    public void addEmployees(View view) {
        startActivity(new Intent(this, AddEmployeesActivity.class));
    }

    @Override
    public void onClickWorkHoursTextView(final long employeeId) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Evidentiranje utrošenih radnih sati radnika");
        final EditText input = new EditText(this);
        input.setSingleLine(false);
        input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        input.setPadding(50, 5, 50, 5);
        input.setBackground(null);
        input.setText("");
        input.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        builder.setView(input);
        // Set up the buttons:
        // 1) save:
        builder.setPositiveButton(R.string.main_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (Employee employee : employees){

                    if (employee.getId() == employeeId) {
                        int workHours;
                        try {
                            workHours = Integer.parseInt(input.getText().toString());
                        } catch (NumberFormatException e) {
                            return;
                        }
                        employee.setWorkHours(workHours);
                        JSONObject data = new JSONObject();
                        data.put("employeeId", employeeId);
                        data.put("radniSati", workHours);
                        PostData postData = new PostData(EmployeesListActivity.this, data);
                        String url = "http://www.mocky.io/v2/5bdd58253200005a008c625f";  //TODO: change url.
                        postData.execute(url);
                        employeesListAdapter.notifyDataSetChanged();
                        break;
                    }

                }
            }
        });
        // 2) cancel:
        builder.setNegativeButton(R.string.main_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void afterSendData(Boolean result){
        if (result) {
            Toast.makeText(this, "updated Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error while saving", Toast.LENGTH_LONG).show();
        }
    }
}
