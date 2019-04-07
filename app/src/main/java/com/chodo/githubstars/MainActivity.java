package com.chodo.githubstars;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // a List to store all the data in
    List<Class_Repos> List_repos = new ArrayList<>();

    // Url Composing
    String BaseUrl = "https://api.github.com/search/repositories?q=created:>";
    String WhatToSort ="&sort=stars&order=desc";
    int PageNbr = 1;

    // Recycler View position
    int pos = 0;

    // A Progress Dialog so the user don't get bored
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the first 100 repos
        getAndDisplayData(BaseUrl+getDate()+WhatToSort);
    }

    // To get data i used volley
    void getAndDisplayData(String url) {
        // showing dialog
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                // Calling my function parseJson and giving it the response string
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
        dialog.dismiss();
    }

    //  a function that take Json String and parse it to array object
    void parseJsonData(String jsonString) {
        ArrayList al = new ArrayList();
        try {
            JSONObject object = new JSONObject(jsonString);
            // getting the items
            JSONArray reposArray = object.getJSONArray("items");
            for (int i = 0; i < reposArray.length(); ++i) {
                String Name = reposArray.getJSONObject(i).get("name").toString();
                String description = reposArray.getJSONObject(i).get("description").toString();
                String stars = reposArray.getJSONObject(i).get("stargazers_count").toString();
                String Owner = reposArray.getJSONObject(i).getJSONObject("owner").get("login").toString();
                String Image = reposArray.getJSONObject(i).getJSONObject("owner").get("avatar_url").toString();
                // Adding every thing to the same list
                List_repos.add(new Class_Repos(Name, description, stars, Owner, Image));
            }
            // Showing the list
            ShowList(List_repos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // show list is responsable for building my recycler view
    void ShowList(List<Class_Repos> List_repos) {
        // For holding the position

        RecyclerView rv = (RecyclerView) findViewById(R.id.RV_Repos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        // calling my adapter
        Adabter_repos adapter = new Adabter_repos(List_repos);

        // Scroll Listener to know if its in the bottom so i load more topics
        adapter.setOnBottomReachedListener(new Adabter_repos.OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                // incrementing the page number
                PageNbr++;
                getAndDisplayData(BaseUrl+getDate()+WhatToSort+"&page="+PageNbr);
                pos = position;
            }
        });

        rv.setAdapter(adapter);
        rv.scrollToPosition(pos);
    }

    // just for getting the right date
    String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(cal.getTimeZone());
        return dateFormat.format(cal.getTime());
    }
}
