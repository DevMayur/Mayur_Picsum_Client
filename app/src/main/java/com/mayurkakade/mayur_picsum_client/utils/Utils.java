package com.mayurkakade.mayur_picsum_client.utils;

import android.util.Log;
import android.widget.Adapter;

import com.mayurkakade.mayur_picsum_client.adapters.RecyclerAdapter;
import com.mayurkakade.mayur_picsum_client.apiRequests.NetworkTask;
import com.mayurkakade.mayur_picsum_client.apiRequests.TaskRunner;
import com.mayurkakade.mayur_picsum_client.apiRequests.iOnDataFetched;
import com.mayurkakade.mayur_picsum_client.models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static class API {
        public static String LIST_URL = "https://picsum.photos/list";
        public static String IMAGE_URL = "https://picsum.photos/300/300?image="; //concatenate image id
    }

    public static List<Model> getImagesList(String jsonData) {
        List<Model> resultList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            int lengthOfData = jsonArray.length();
            for (int i=0; i<lengthOfData; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String authorName = jsonObject.getString("author");
                String id = jsonObject.getString("id");
                String url = Utils.API.IMAGE_URL + id;

                Model model = new Model(id,authorName,url);
                resultList.add(model);
            }
        } catch (JSONException e) {
            Log.d("exception","exception : " + e.getMessage());
        }
        return resultList;
    }

}
