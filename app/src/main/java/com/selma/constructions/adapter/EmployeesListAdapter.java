package com.selma.constructions.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.selma.constructions.R;
import com.selma.constructions.model.Employee;

import java.util.List;

public class EmployeesListAdapter extends RecyclerView.Adapter<EmployeesListAdapter.MyHolder> {

    private List<Employee> employees;
    private EmployeesListAdapter.OnEmployeeClick onEmployeeClick;

    public EmployeesListAdapter(List<Employee> employees)
    {
        this.employees = employees;
    }
    @Override
    public EmployeesListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false);
        return new EmployeesListAdapter.MyHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EmployeesListAdapter.MyHolder holder, final int position) {

        holder.employeeName.setText(employees.get(position).getName());
        holder.employeePhoneNum.setText(employees.get(position).getPhone());
        holder.employeeWorkHours.setText("8");

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }


    // MyHolder Class
    public class MyHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView employeeName;
        private TextView employeePhoneNum;
        private TextView employeeWorkHours;
        private RelativeLayout relativeLayout;

        public MyHolder(final CardView c) {
            super(c);
            cardView = c;
            employeeName = cardView.findViewById(R.id.employee_row_name);
            employeePhoneNum = cardView.findViewById(R.id.employee_row_phone_number);
            employeeWorkHours = cardView.findViewById(R.id.employee_row_work_hours);
            relativeLayout = cardView.findViewById(R.id.employee_row_relative_layout);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(EmployeesListAdapter.this.onEmployeeClick != null) {

                        EmployeesListAdapter.this.onEmployeeClick.onClick(employees.get(getAdapterPosition()).getId(), relativeLayout);
                    }
                }
            });
        }
    }

    public interface OnEmployeeClick {
        void onClick(long employeeId, RelativeLayout relativeLayout);
    }

    public void setOnEmployeeClick(EmployeesListAdapter.OnEmployeeClick onEmployeeClick)
    {
        this.onEmployeeClick = onEmployeeClick;
    }


}
