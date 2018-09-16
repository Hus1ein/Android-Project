package com.selma.constructions.activity;

import android.support.v7.app.AppCompatActivity;

import org.json.simple.JSONObject;

public abstract class BaseActivityForObjects extends AppCompatActivity {

    public abstract void getDataFromAsyncTask(JSONObject result);

}
