package com.selma.constructions.activity;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.selma.constructions.R;
import com.selma.constructions.model.Employee;

public class EmployeeActivity extends AppCompatActivity {

    private TextView employeeName;
    private TextView employeeAddress;
    private TextView employeePhoneNumber;
    private TextView employeeRank;
    private TextView employeeSocialNumber;
    private TextView employeeEmail;
    private TextView employeeBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Employee employee = (Employee) getIntent().getSerializableExtra(EmployeesListActivity.EMPLOYEE);

        getSupportActionBar().setTitle(employee.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        employeeName = findViewById(R.id.activity_employee_name);
        employeeAddress = findViewById(R.id.activity_employee_location);
        employeePhoneNumber = findViewById(R.id.activity_employee_phone_number);
        employeeRank = findViewById(R.id.activity_employee_rank);
        employeeSocialNumber = findViewById(R.id.activity_employee_social_number);
        employeeEmail = findViewById(R.id.activity_employee_email);
        employeeBirthday = findViewById(R.id.activity_employee_birthday);

        setData(employee);

    }

    private void setData(Employee employee) {

        employeeName.setText(employee.getName());
        employeeAddress.setText(employee.getAddress());
        employeePhoneNumber.setText(employee.getPhone());
        employeeRank.setText(employee.getRank());
        employeeSocialNumber.setText(employee.getSocialNumber());
        employeeEmail.setText(employee.getEmail());
        employeeBirthday.setText(employee.getDate());
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
}
