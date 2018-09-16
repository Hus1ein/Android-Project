package com.selma.constructions;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.selma.constructions.activity.AddEmployeesActivity;
import com.selma.constructions.activity.BaseActivityForObjects;

import org.json.simple.JSONObject;

public class PostData extends AsyncTask<String, Integer, JSONObject> {

    //private String value;
    private AppCompatActivity activity;

    public PostData(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    protected JSONObject doInBackground(String... strings) {

        JSONObject jsonObject = null;
        /*URL url = null;
        try {
            url = new URL(strings[0]);
            HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                JSONParser jsonParser = new JSONParser();
                jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(responseBody, "UTF-8"));
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
        ((AddEmployeesActivity) activity).closeActivity();
    }
}
