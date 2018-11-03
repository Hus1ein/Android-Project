package com.selma.constructions.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.selma.constructions.Fragments.JobsTypesFragment;
import com.selma.constructions.Fragments.ProjectInfoFragment;
import com.selma.constructions.Fragments.ProjectsListFragment;
import com.selma.constructions.GetDataAsArray;
import com.selma.constructions.R;
import com.selma.constructions.model.JobTypeRow;
import com.selma.constructions.model.Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends BaseActivityForAsyncTask {

    public static final String CURRENT_PROJECT = "current_project";
    public static final String ALL_TYPES = "all_types";
    private LinearLayout mainLayout;
    private ProgressBar progressBar;
    private Project currentProject;
    private ArrayList<JobTypeRow> allTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        currentProject = (Project) getIntent().getSerializableExtra(ProjectsListFragment.PROJECT);

        getSupportActionBar().setTitle(currentProject.getName().toUpperCase());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainLayout = findViewById(R.id.activity_project_main_linear_layout);
        progressBar = findViewById(R.id.activity_project_progress_bar);

        getAllJobsTypes(currentProject.getId());

    }

    private void getAllJobsTypes(long projectId){

        progressBar.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
        GetDataAsArray getDataAsArray = new GetDataAsArray(this);
        String url = "http://www.mocky.io/v2/5bdcc2e93300003828813696";  //TODO: create url.
        getDataAsArray.execute(url, currentProject.getId() + "");

    }

    @Override
    public void getDataAsArray(JSONArray result) {
        allTypes = new ArrayList<>();

        if (result != null) {
            for (int n = 0; n < result.size(); n++) {
                JSONObject object = (JSONObject) result.get(n);
                JobTypeRow typeRow = new JobTypeRow();
                typeRow.setId((Long)object.get("TipPoslaId"));
                typeRow.setName(object.get("TipPoslaNaziv").toString());
                typeRow.setCount(Integer.parseInt(object.get("count").toString()));
                allTypes.add(typeRow);
            }

            progressBar.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);

            ViewPager viewPager = findViewById(R.id.activity_project_viewpager);
            TabLayout tabLayout = findViewById(R.id.activity_project_tabs);

            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);

        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void afterSendData(Boolean result) {
        if (result) {
            Toast.makeText(this, "updated Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error while saving", Toast.LENGTH_LONG).show();
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();

        bundle1.putSerializable(CURRENT_PROJECT, currentProject);
        bundle2.putSerializable(ALL_TYPES, allTypes);

        ProjectInfoFragment projectInfoFragment = new ProjectInfoFragment();
        JobsTypesFragment jobsTypesFragment = new JobsTypesFragment();

        projectInfoFragment.setArguments(bundle1);
        jobsTypesFragment.setArguments(bundle2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(projectInfoFragment, "O Projektu");
        adapter.addFragment(jobsTypesFragment, "Tipova poslaova");

        viewPager.setAdapter(adapter);

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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
