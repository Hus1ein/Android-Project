package com.selma.constructions.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.selma.constructions.GetDataAsArray;
import com.selma.constructions.PostData;
import com.selma.constructions.R;
import com.selma.constructions.adapter.EmployeesListAdapter;
import com.selma.constructions.model.Employee;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCompanyEmployeesActivity extends BaseActivityForAsyncTask implements EmployeesListAdapter.OnEmployeeClick{

    private List<Long> selectedEmployees;
    private List<Employee> allEmployees;
    private FloatingActionButton addSelectedEmployeesButton;
    private RelativeLayout mainLayout;
    private ProgressBar progressBar;
    private long currentJobId;
    public static final String EMPLOYEE = "employee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employees);

        currentJobId = getIntent().getLongExtra("jobId", -1);
        if (currentJobId > -1) {
            getSupportActionBar().setTitle("Dodaj uposlenika");
            selectedEmployees = new ArrayList<>();
        } else {
            getSupportActionBar().setTitle("Sve zaposlane");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addSelectedEmployeesButton = findViewById(R.id.activity_add_employees_floating_button);
        mainLayout = findViewById(R.id.activity_add_employees_main_layout);
        progressBar = findViewById(R.id.activity_add_employees_progress_bar);

        getAllEmployees();

    }

    private void getAllEmployees(){

        progressBar.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
        GetDataAsArray getDataAsArray = new GetDataAsArray(this);
        String url = "http://www.mocky.io/v2/5bdcc934330000382881369e";  //TODO: change url.
        getDataAsArray.execute(url, "-1"); // id = -1 , Show all employees in company

    }

    @Override
    public void getDataAsArray(JSONArray result) {
        allEmployees = new ArrayList<>();

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
                allEmployees.add(employee);
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
        RecyclerView employeesRecyclerView = findViewById(R.id.activity_add_employees_recycler_view);
        EmployeesListAdapter employeesListAdapter = new EmployeesListAdapter(allEmployees, true);
        final LinearLayoutManager employeesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        employeesRecyclerView.setLayoutManager(employeesLayoutManager);
        employeesRecyclerView.setAdapter(employeesListAdapter);
        employeesListAdapter.setOnEmployeeClick(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addEmployees(View view) {

        JSONObject data = new JSONObject();
        data.put("jobTypeId", currentJobId);
        data.put("selectedEmployees", selectedEmployees);
        PostData postData = new PostData(this, data);
        String url = "http://www.mocky.io/v2/5bdd58253200005a008c625f";  //TODO: change url.
        postData.execute(url);

        finish();

    }

    @Override
    public void onClick(long selectedEmployeeId, RelativeLayout relativeLayout) {


        if (currentJobId > -1) {

            for(Long employeeId : selectedEmployees){
                if (employeeId == selectedEmployeeId){
                    selectedEmployees.remove(selectedEmployeeId);
                    relativeLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));

                    if (selectedEmployees.size() == 0)
                        addSelectedEmployeesButton.setVisibility(View.GONE);
                    else
                        addSelectedEmployeesButton.setVisibility(View.VISIBLE);
                    return;
                }

            }
            selectedEmployees.add(selectedEmployeeId);
            relativeLayout.setBackgroundColor(Color.parseColor("#cceeff"));
            addSelectedEmployeesButton.setVisibility(View.VISIBLE);

        } else {

            for (Employee employee : allEmployees){

                if (employee.getId() == selectedEmployeeId) {

                    Intent intent = new Intent(this, EmployeeActivity.class);
                    intent.putExtra(EMPLOYEE, employee);
                    startActivity(intent);

                    break;
                }

            }

        }


    }

    @Override
    public void afterSendData(Boolean result){
        if (result) {
            Toast.makeText(this, "updated Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error while saving", Toast.LENGTH_LONG).show();
        }
        finish();
    }

}
