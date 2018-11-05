package com.selma.constructions.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selma.constructions.R;
import com.selma.constructions.model.JobTypeRow;

import java.util.List;

public class JobsTypesAdapter extends RecyclerView.Adapter<JobsTypesAdapter.MyHolder>{

    private List<JobTypeRow> types;
    private OnJobClick onJobClick;

    public JobsTypesAdapter(List<JobTypeRow> types)
    {
        this.types = types;
    }
    @Override
    public JobsTypesAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.job_row, parent, false);
        return new MyHolder(cardView);
    }

    @Override
    public void onBindViewHolder(JobsTypesAdapter.MyHolder holder, final int position) {

        holder.type.setText(types.get(position).getName());
        holder.employeesNumber.setText(types.get(position).getCount() + "");

    }

    @Override
    public int getItemCount() {
        return types.size();
    }


    // MyHolder Class
    public class MyHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView type;
        private TextView employeesNumber;
        public MyHolder(CardView c) {
            super(c);
            cardView = c;

            type = cardView.findViewById(R.id.job_row_job_type);
            employeesNumber = cardView.findViewById(R.id.job_row_employees_number);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(JobsTypesAdapter.this.onJobClick != null) {
                        JobsTypesAdapter.this.onJobClick.onClick(types.get(getAdapterPosition()).getId());
                    }
                }
            });
        }
    }

    public interface OnJobClick {
        void onClick(long jobId);
    }

    public void setOnJobClick(OnJobClick onJobClick)
    {
        this.onJobClick = onJobClick;
    }


}
