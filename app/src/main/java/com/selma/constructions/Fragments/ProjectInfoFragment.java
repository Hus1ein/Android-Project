package com.selma.constructions.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.selma.constructions.PostData;
import com.selma.constructions.R;
import com.selma.constructions.activity.ProjectActivity;
import com.selma.constructions.model.Project;

import org.json.simple.JSONObject;

public class ProjectInfoFragment extends Fragment {

    private TextView projectName;
    private TextView projectLocation;
    private TextView projectContractDate;
    private TextView projectStartDate;
    private TextView projectEndDate;
    private TextView projectDescription;
    private Project currentProject;

    public ProjectInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_info, container, false);

        currentProject = (Project) getArguments().getSerializable(ProjectActivity.CURRENT_PROJECT);

        projectName = view.findViewById(R.id.fragment_project_info_name);
        projectLocation = view.findViewById(R.id.fragment_project_info_location);
        projectContractDate = view.findViewById(R.id.fragment_project_info_contract_date);
        projectStartDate = view.findViewById(R.id.fragment_project_info_start_project_date);
        projectEndDate = view.findViewById(R.id.fragment_project_info_end_project_date);
        projectDescription = view.findViewById(R.id.fragment_project_info_description);
        projectDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProjectDescription();
            }
        });

        setData(currentProject);

        return view;
    }

    private void editProjectDescription()
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Opis Projekta");
        final EditText input = new EditText(getContext());
        input.setSingleLine(false);
        input.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        input.setPadding(50, 5, 50, 5);
        input.setBackground(null);
        input.setText(projectDescription.getText());
        input.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        builder.setView(input);
        // Set up the buttons:
        // 1) save:
        builder.setPositiveButton(R.string.main_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                projectDescription.setText(input.getText().toString());
                JSONObject data = new JSONObject();
                data.put("projectId", currentProject.getId());
                data.put("opisProjekta", input.getText().toString());
                PostData postData = new PostData((AppCompatActivity) getActivity(), data);
                String url = "http://www.mocky.io/v2/5bdd58253200005a008c625f";  //TODO: change url.
                postData.execute(url);
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

    private void setData(Project currentProject){

        projectName.setText(currentProject.getName());
        projectLocation.setText(currentProject.getLocation());
        projectContractDate.setText(currentProject.getContractDate());
        projectStartDate.setText(currentProject.getStartProjectDate());
        projectEndDate.setText(currentProject.getEndProjectDate());
        projectDescription.setText(currentProject.getDescription());

    }
}
