package com.selma.constructions;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import com.selma.constructions.activity.BaseActivityForAsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetDataAsArray extends AsyncTask<String, Integer, JSONArray> {

    private AppCompatActivity activity;

    public GetDataAsArray(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {

        JSONArray jsonArray = null;
        URL url = null;
        try {
            url = new URL(strings[0] + "?id=" + strings[1]);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                JSONParser jsonParser = new JSONParser();
                jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(responseBody, "UTF-8"));
            }
            myConnection.disconnect();
        } catch (IOException | ParseException | RuntimeException e) {
            e.printStackTrace();
        }
        return jsonArray;
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
    protected void onPostExecute(JSONArray value) {
        super.onPostExecute(value);
        ((BaseActivityForAsyncTask) activity).getDataAsArray(value);
    }
}

