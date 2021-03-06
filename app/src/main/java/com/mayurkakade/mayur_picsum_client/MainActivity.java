package com.mayurkakade.mayur_picsum_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mayurkakade.mayur_picsum_client.adapters.RecyclerAdapter;
import com.mayurkakade.mayur_picsum_client.apiRequests.NetworkTask;
import com.mayurkakade.mayur_picsum_client.apiRequests.TaskRunner;
import com.mayurkakade.mayur_picsum_client.apiRequests.iOnDataFetched;
import com.mayurkakade.mayur_picsum_client.models.Model;
import com.mayurkakade.mayur_picsum_client.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.mayurkakade.mayur_picsum_client.utils.Utils.getImagesList;

public class MainActivity extends AppCompatActivity {

    List<Model> list;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void getData() {
        TaskRunner runner = new TaskRunner();

        iOnDataFetched onDataFetched = new iOnDataFetched() {
            @Override
            public void showProgressBar() {
            }

            @Override
            public void hideProgressBar() {
            }

            @Override
            public void setDataInPageWithResult(Object result) {
                if (result != null) {
                    String jsonData = result.toString();
                    MainActivity.this.list.addAll(getImagesList(jsonData));
                    adapter.notifyDataSetChanged();
                }
            }
        };
        runner.executeAsync(new NetworkTask(onDataFetched, Utils.API.LIST_URL));
    }

    private void init() {
        list = new ArrayList<>();
        getData();
        recyclerView = findViewById(R.id.list_recycler_view);
        adapter = new RecyclerAdapter(MainActivity.this,list);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        }
        recyclerView.setAdapter(adapter);
    }


}