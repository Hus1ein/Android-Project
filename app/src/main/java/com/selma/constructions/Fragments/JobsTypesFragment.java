package com.selma.constructions.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.selma.constructions.R;
import com.selma.constructions.activity.EmployeesListActivity;
import com.selma.constructions.activity.ProjectActivity;
import com.selma.constructions.adapter.JobsTypesAdapter;
import com.selma.constructions.model.JobTypeRow;
import com.selma.constructions.model.Project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JobsTypesFragment extends Fragment implements JobsTypesAdapter.OnJobClick{

    private ArrayList<JobTypeRow> jobTypeRows;
    public static final String JOB_ID = "job_id";

    public JobsTypesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jobs_types, container, false);
        jobTypeRows = (ArrayList<JobTypeRow>) getArguments().getSerializable(ProjectActivity.ALL_TYPES);

        createRecyclerView(view, inflater);
        return view;
    }

    private void createRecyclerView(View view, final LayoutInflater inflater)
    {
        RecyclerView jobsRecyclerView = view.findViewById(R.id.fragment_jobs_types_recycler_view);
        JobsTypesAdapter jobsTypesAdapter = new JobsTypesAdapter(jobTypeRows);
        final LinearLayoutManager jobsLayoutManager = new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        jobsRecyclerView.setLayoutManager(jobsLayoutManager);
        jobsRecyclerView.setAdapter(jobsTypesAdapter);
        jobsTypesAdapter.setOnJobClick(this);

    }


    @Override
    public void onClick(long jobId) {

        Intent intent = new Intent(getContext(), EmployeesListActivity.class);
        intent.putExtra(JOB_ID, jobId);
        startActivity(intent);
    }

}
