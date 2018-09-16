package com.selma.constructions.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.selma.constructions.R;
import com.selma.constructions.activity.MainActivity;
import com.selma.constructions.activity.ProjectActivity;
import com.selma.constructions.adapter.JobsTypesAdapter;
import com.selma.constructions.adapter.ProjectsAdapter;
import com.selma.constructions.model.Project;

import java.util.ArrayList;
import java.util.List;


public class ProjectsListFragment extends Fragment  implements ProjectsAdapter.onProjectClick {

    private ArrayList<Project> projects;
    public static final String PROJECT = "project";

    public ProjectsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_projects_list, container, false);
        projects = (ArrayList<Project>) getArguments().getSerializable(MainActivity.PROJECTS);
        createRecyclerView(view, inflater);
        return view;

    }

    private void createRecyclerView(View view, final LayoutInflater inflater)
    {
        RecyclerView projectsRecyclerView = view.findViewById(R.id.fragment_projects_list_recycler_view);
        ProjectsAdapter projectsAdapter = new ProjectsAdapter(projects);
        final LinearLayoutManager projectsLayoutManager = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        projectsRecyclerView.setLayoutManager(projectsLayoutManager);
        projectsRecyclerView.setAdapter(projectsAdapter);
        projectsAdapter.setOnProjectClick(this);

    }



    @Override
    public void onClick(long projectId) {

        for (Project project : projects){

            if (project.getId() == projectId) {

                if (project.getStatus()) {

                    Intent intent = new Intent(getContext(), ProjectActivity.class);
                    intent.putExtra(PROJECT, project);
                    startActivity(intent);

                }else
                    Toast.makeText(getActivity(), "Ovaj projekat nije vise aktivan", Toast.LENGTH_SHORT).show();

                break;
            }

        }

    }
}
