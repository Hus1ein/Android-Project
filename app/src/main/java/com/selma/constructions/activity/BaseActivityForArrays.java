package com.selma.constructions.activity;

import android.support.v7.app.AppCompatActivity;

import org.json.simple.JSONArray;

public abstract class BaseActivityForArrays extends AppCompatActivity {

    public abstract void getDataFromAsyncTask(JSONArray result);

}
