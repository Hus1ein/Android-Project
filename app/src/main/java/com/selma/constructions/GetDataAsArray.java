package com.selma.constructions;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.selma.constructions.activity.BaseActivityForArrays;
import com.selma.constructions.activity.BaseActivityForObjects;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetDataAsArray extends AsyncTask<String, Integer, JSONArray> {

    //private String value;
    private AppCompatActivity activity;

    public GetDataAsArray(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {

        JSONArray jsonArray = null;
        URL url = null;
        /*try {
            url = new URL(strings[0]);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                JSONParser jsonParser = new JSONParser();
                jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(responseBody, "UTF-8"));
            } else {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show();
            }
            myConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
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
        ((BaseActivityForArrays) activity).getDataFromAsyncTask(value);
    }
}

