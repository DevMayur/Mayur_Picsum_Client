package com.mayurkakade.mayur_picsum_client.apiRequests;

import android.net.Uri;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkTask extends BaseAsyncTask {

    private final iOnDataFetched listener;
    String BASE_URL;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String recipeJson = null;


    public NetworkTask(iOnDataFetched onDataFetchedListener, String BASE_URL) {
        this.listener = onDataFetchedListener;
        this.BASE_URL = BASE_URL;
    }

    @Override
    public Object call() {
        Object result = null;
        Log.d("exception_networking","in call");
        result = getData();
        return result;
    }

    private Object getData() {
//        Log.d("exception_networking","in getData");
        Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
        try {
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            // Read the response string into a StringBuilder.
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }

            if (builder.length() == 0) {
                return null;
            }
            recipeJson = builder.toString();
//            Log.d("exception_networking","data inside : " + recipeJson);
        }catch(Exception e) {
            Log.d("exception_networking", " exception : " + e.getMessage());
        }
//        Log.d("exception_networking","data in class : " + recipeJson);

        return recipeJson;
    }

    @Override
    public void setUiForLoading() {
        listener.showProgressBar();
    }

    @Override
    public void setDataAfterLoading(Object result) {
        listener.setDataInPageWithResult(result);
        listener.hideProgressBar();
    }
}

