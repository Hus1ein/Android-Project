package com.selma.constructions;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.selma.constructions.activity.BaseActivityForAsyncTask;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostData extends AsyncTask<String, Integer, Boolean> {

    //private String value;
    private AppCompatActivity activity;
    private JSONObject postData;

    public PostData(AppCompatActivity activity, JSONObject postData) {
        this.activity = activity;
        this.postData = postData;
    }


    @Override
    protected Boolean doInBackground(String... strings) {

        JSONObject jsonObject = null;
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            myConnection.setDoInput(true);
            myConnection.setDoOutput(true);
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestMethod("POST");

            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(myConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            if (myConnection.getResponseCode() == 200) {
                return true;
            }
            myConnection.disconnect();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        ((BaseActivityForAsyncTask) activity).afterSendData(result);
    }
}
