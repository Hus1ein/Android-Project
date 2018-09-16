package com.selma.constructions.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.selma.constructions.Fragments.JobsTypesFragment;
import com.selma.constructions.GetDataAsArray;
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

public class EmployeesListActivity extends BaseActivityForArrays implements EmployeesListAdapter.OnEmployeeClick{

    private long jobTypeId;
    private List<Employee> employees;
    public static final String EMPLOYEE = "employee";
    private RelativeLayout mainLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);

        jobTypeId = getIntent().getLongExtra(JobsTypesFragment.JOB_ID, 0);

        getSupportActionBar().setTitle("Svi uposlenici");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainLayout = findViewById(R.id.activity_add_employees_main_layout);
        progressBar = findViewById(R.id.activity_employees_list_progress_bar);

        getAllEmployees();
    }

    private void createRecyclerView()
    {
        RecyclerView employeesRecyclerView = findViewById(R.id.activity_employees_list_recycler_view);
        EmployeesListAdapter employeesListAdapter = new EmployeesListAdapter(employees);
        final LinearLayoutManager employeesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        employeesRecyclerView.setLayoutManager(employeesLayoutManager);
        employeesRecyclerView.setAdapter(employeesListAdapter);
        employeesListAdapter.setOnEmployeeClick(this);

    }

    private void getAllEmployees(){
        /*employees = new ArrayList<>();
        employees.add(new Employee(0, "Hussain Abd", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(1, "Hussain Abd1", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(2, "Hussain Abd2", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(3, "Hussain Abd3", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(4, "Hussain Abd4", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(5, "Hussain Abd5", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        employees.add(new Employee(6, "Hussain Abd6", "dsa", "1808876123432", "18.09.1980", "Ilidza, Sarajevo", "hussain.abd121@gmail.com", "062961404"));
        return employees;*/

        progressBar.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
        GetDataAsArray getDataAsArray = new GetDataAsArray(this);
        String url = "";  //TODO: create url.
        getDataAsArray.execute(url);
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
    public void getDataFromAsyncTask(JSONArray result) {
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
}
