package com.hizkeel.tredditapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hizkeel.tredditapp.R;
import com.hizkeel.tredditapp.controller.AdapterClass;
import com.hizkeel.tredditapp.model.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<Model> data = new ArrayList<>();

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRecyclerView = findViewById(R.id.recyclerview);


        // Manual Data Entry

//        pt();

    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void progProc(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Processing"); // Setting Title
//        progressDialog.setIcon(R.drawable.app_logo);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);



    }

    public void pt(){

        progProc();


//        String URL = "https://www.reddit.com/r/trending/.json";
        String URL = "https://www.reddit.com/r/best/.json";


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i("xyz", "abc"+response);



                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("xyz", "abc"+ jsonObject.getJSONObject("data")
                                    .getJSONArray("children").getJSONObject(0)
                                    .getJSONObject("data").getString("subreddit"));


                            for(int i = 0; i <= 100; i++){

                                String subreddit = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("subreddit");

                                String selftext = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("selftext");

                                String title = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("title");

                                String ups = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("ups");

                                String score = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("score");

                                String created = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("created");

                                String author = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("author");

                                String num_comments = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("num_comments");

                                String url = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("url");

                                String downs = jsonObject.getJSONObject("data")
                                        .getJSONArray("children").getJSONObject(i)
                                        .getJSONObject("data").getString("downs");



                                data.add(new Model(subreddit,selftext, title, ups,score,created, author, num_comments, url, downs));


                                AdapterClass adapter = new AdapterClass(data);
                                mRecyclerView.setAdapter(adapter);
//                                LinearLayoutManager llm =  new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                                GridLayoutManager llm =  new GridLayoutManager(MainActivity.this, 1);



                                llm.setAutoMeasureEnabled(false);

                                mRecyclerView.setLayoutManager(llm);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //what to do if it encounter error
                        progressDialog.dismiss();

                        Log.i("xyz", "abc-error"+error.getMessage());
//                        if (!haveNetworkConnection()) {
//                            Toast.makeText(MainActivity.this, "Check network connection", LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Not successfull"+error.networkResponse, LENGTH_SHORT).show();
//                        }
                    }
                }


        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("command", "agent_login");



                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);




    }
}