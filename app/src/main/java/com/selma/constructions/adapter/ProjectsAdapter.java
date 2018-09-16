package com.selma.constructions.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selma.constructions.R;
import com.selma.constructions.model.Project;

import java.util.List;
import java.util.Random;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyHolder>{

    private List<Project> projects;
    private onProjectClick onProjectClick;
    private final int backgrounds[] = {R.drawable.text_view_shape_blue, R.drawable.text_view_shape_fuchsia
            , R.drawable.text_view_shape_green,R.drawable.text_view_shape_red};

    public ProjectsAdapter(List<Project> projects)
    {
        this.projects = projects;
    }
    @Override
    public ProjectsAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        return new MyHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ProjectsAdapter.MyHolder holder, final int position) {

        holder.projectName.setText(projects.get(position).getName());
        holder.projectLocation.setText(projects.get(position).getLocation());
        holder.projectExpiryDate.setText(projects.get(position).getEndProjectDate());
        holder.projectPhoto.setText(projects.get(position).getName().toUpperCase().charAt(0) + "");
        Random random = new Random();
        holder.projectPhoto.setBackgroundResource(backgrounds[Math.abs(random.nextInt()%4)]);

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


    // MyHolder Class
    public class MyHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView projectPhoto;
        private TextView projectName;
        private TextView projectLocation;
        private TextView projectExpiryDate;

        public MyHolder(CardView c) {
            super(c);
            cardView = c;

            projectPhoto = cardView.findViewById(R.id.project_row_photo);
            projectName = cardView.findViewById(R.id.project_row_name);
            projectLocation = cardView.findViewById(R.id.project_row_location);
            projectExpiryDate = cardView.findViewById(R.id.project_row_expiry_date);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ProjectsAdapter.this.onProjectClick != null) {
                        // TODO Send project id as param
                        ProjectsAdapter.this.onProjectClick.onClick(projects.get(getAdapterPosition()).getId());
                    }
                }
            });
        }
    }

    public interface onProjectClick{
        void onClick(long projectId);
    }

    public void setOnProjectClick(onProjectClick onProjectClick)
    {
        this.onProjectClick = onProjectClick;
    }


}
