package com.selma.constructions.activity;

import android.support.v7.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class BaseActivityForAsyncTask extends AppCompatActivity {

    public void afterSendData(Boolean result){}
    public void getDataAsArray(JSONArray result){}
    public void getDataAsObject(JSONObject result){}

}