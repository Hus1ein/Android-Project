package com.selma.constructions;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.selma.constructions.activity.BaseActivityForAsyncTask;
import com.selma.constructions.activity.BaseActivityForObjects;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetDataAsObject extends AsyncTask<String, Integer, JSONObject> {

    private AppCompatActivity activity;

    public GetDataAsObject(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    protected JSONObject doInBackground(String... strings) {

        JSONObject jsonObject = null;
        URL url = null;
        try {
            url = new URL(strings[0] + "?username=" + strings[1] + "&password=" + strings[2]);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                JSONParser jsonParser = new JSONParser();
                jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(responseBody, "UTF-8"));
            }
            myConnection.disconnect();
        } catch (IOException | ParseException | RuntimeException e) {
            e.printStackTrace();
        }
        return jsonObject;
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
    protected void onPostExecute(JSONObject value) {
        super.onPostExecute(value);
        ((BaseActivityForAsyncTask) activity).getDataAsObject(value);
    }
}
