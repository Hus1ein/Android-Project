package com.selma.constructions.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.selma.constructions.Fragments.ProjectsListFragment;
import com.selma.constructions.GetDataAsArray;
import com.selma.constructions.R;
import com.selma.constructions.model.Project;
import com.selma.constructions.model.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivityForArrays {

    private ArrayList<Project> activeProjects;
    private ArrayList<Project> inactiveProjects;
    public static final String PROJECTS = "projects";
    private User currentUser;
    private LinearLayout mainLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Svi Projekti");

        currentUser = (User)getIntent().getSerializableExtra(LoginActivity.CURRENT_USER);
        mainLayout = findViewById(R.id.activity_main_linear_layout);
        progressBar = findViewById(R.id.activity_main_progress_bar);

        getAllProjects();

    }

    private void setupViewPager(ViewPager viewPager) {

        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();

        bundle1.putSerializable(PROJECTS, activeProjects);
        bundle2.putSerializable(PROJECTS, inactiveProjects);

        ProjectsListFragment activeProjectsFragment = new ProjectsListFragment();
        ProjectsListFragment inactiveProjectsFragment = new ProjectsListFragment();

        activeProjectsFragment.setArguments(bundle1);
        inactiveProjectsFragment.setArguments(bundle2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(activeProjectsFragment, "Aktivni projekti");
        adapter.addFragment(inactiveProjectsFragment, "Ne aktivni projekti");

        viewPager.setAdapter(adapter);
    }

    private void getAllProjects(){
        /*List<Project> projects = new ArrayList<>();
        projects.add(new Project(0, "First project", "Sarajevo, BiH", "20.09.2018", true, "asd"));
        projects.add(new Project(1, "Second project", "Tuzla, BiH", "20.09.2018", true, "asd"));
        projects.add(new Project(2, "Third project", "Zenica, BiH", "20.09.2018", false, "asd"));
        projects.add(new Project(3, "Fourth project", "Zagreb, Croatia", "20.09.2018", true, "asd"));
        projects.add(new Project(4, "Fifth project", "Split, Croatia", "20.09.2018", false, "asd"));
        projects.add(new Project(5, "Sixth project", "Sarajevo, BiH", "20.09.2018", true, "asd"));
        projects.add(new Project(6, "First project", "Sarajevo, BiH", "20.09.2018", true, "asd"));
        projects.add(new Project(7, "Second project", "Tuzla, BiH", "20.09.2018", true, "asd"));
        projects.add(new Project(8, "Third project", "Zenica, BiH", "20.09.2018", false, "asd"));
        projects.add(new Project(9, "Fourth project", "Zagreb, Croatia", "20.09.2018", true, "asd"));
        projects.add(new Project(10, "Fifth project", "Split, Croatia", "20.09.2018", false, "asd"));
        projects.add(new Project(11, "Sixth project", "Sarajevo, BiH", "20.09.2018", true, "asd"));

        activeProjects = new ArrayList<>();
        inactiveProjects = new ArrayList<>();

        for (Project project : projects){

            if (project.getStatus())
                activeProjects.add(project);
            else
                inactiveProjects.add(project);

        }

        return projects;*/
        progressBar.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);
        GetDataAsArray getDataAsArray = new GetDataAsArray(this);
        String url = "";  //TODO: create url.
        getDataAsArray.execute(url);
    }

    @Override
    public void getDataFromAsyncTask(JSONArray result) {
        List<Project> allProjects = new ArrayList<>();

        if (result != null) {
            for (int n = 0; n < result.size(); n++) {
                JSONObject object = (JSONObject) result.get(n);
                Project project = new Project();
                project.setId((Long)object.get("Id"));
                project.setStatus((Boolean) object.get("status"));
                project.setName(object.get("Naziv").toString());
                project.setLocation(object.get("Lokacija").toString());
                project.setContractDate((object.get("DatumUgovora").toString()));
                project.setEndProjectDate(object.get("KrajProjekta").toString());
                project.setStartProjectDate(object.get("PocetakProjekta").toString());
                allProjects.add(project);
            }

            activeProjects = new ArrayList<>();
            inactiveProjects = new ArrayList<>();

            for (Project project : allProjects){

                if (project.getStatus())
                    activeProjects.add(project);
                else
                    inactiveProjects.add(project);

            }

            progressBar.setVisibility(View.GONE);
            mainLayout.setVisibility(View.VISIBLE);

            ViewPager viewPager = findViewById(R.id.activity_main_viewpager);
            TabLayout tabLayout = findViewById(R.id.activity_main_tabs);

            tabLayout.setupWithViewPager(viewPager);
            setupViewPager(viewPager);

        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            finish();
        }

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
