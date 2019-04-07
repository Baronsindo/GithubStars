package com.chodo.githubstars;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Class_Repos> List_repos = new ArrayList<>();
    //DateTime dd;
    TextView tv;
    //String url = "https://www.thecrazyprogrammer.com/example_data/fruits_array.json";
    String url = "https://api.github.com/search/repositories?q=created:>2019-04-01&sort=stars&order=desc";
    // https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
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
    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray fruitsArray = object.getJSONArray("items");
            ArrayList al = new ArrayList();

            for (int i = 0; i < fruitsArray.length(); ++i) {
                String Name = fruitsArray.getJSONObject(i).get("name").toString();
                String description  = fruitsArray.getJSONObject(i).get("description").toString();
                String stars = fruitsArray.getJSONObject(i).get("stargazers_count").toString();
                String Owner =fruitsArray.getJSONObject(i).getJSONObject("owner").get("login").toString();
                String Image = fruitsArray.getJSONObject(i).getJSONObject("owner").get("avatar_url").toString();
                Log.w("Mine","*********************");
                List_repos.add(new Class_Repos(Name,description,stars,Owner,Image));
            }

            ShowList(List_repos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }

    void ShowList(List<Class_Repos> List_repos) {


        RecyclerView rv = (RecyclerView) findViewById(R.id.RV_Repos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        //CategoryList = mDBHelper.Select_Cat();

        Adabter_repos adapter = new Adabter_repos(List_repos);

        //Card_item_Clik_5 : n3ayat 3la item listener o o func li wasto
        rv.setAdapter(adapter);
    }
}
