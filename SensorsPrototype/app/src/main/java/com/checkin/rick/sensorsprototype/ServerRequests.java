package com.checkin.rick.sensorsprototype;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rick on 21-3-2016.
 */
public class ServerRequests {
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://android.cigirci.com/FetchUserData.php";
    Context c;

    public ServerRequests(Context context) {
        c = context;
    }

    public void getTemperatureLol(GetTemperatureCallback tempCallBack){
        new getTemperature(tempCallBack).execute();
    }

    public void fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
        new fetchUserDataAsyncTask(user, userCallBack).execute();
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallBack;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "?username=" + user.username + "&password=" + user.password);

            User returnedUser = null;

            try {
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                JSONObject jObject = null;

                if(result.equals("[]")){
                    return null;
                }else{
                    jObject = new JSONObject(result);
                }

                if (jObject.length() != 0){
                    returnedUser = new User(user.username, user.password);
                }else{
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            userCallBack.done(returnedUser);
        }
    }

    public class getTemperature extends AsyncTask<Void, Void, String> {
        GetTemperatureCallback callback;
        String result = "";

        public getTemperature(GetTemperatureCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                // Create a URL for the desired page
                URL url = new URL("http://145.28.187.174/file.txt");

                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str;
                while ((str = in.readLine()) != null) {
                    result = str;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callback.done(result);
        }
    }
}
