package com.hizkeel.tredditapp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hizkeel.tredditapp.controller.AdapterClass;
import com.hizkeel.tredditapp.model.Model;
import com.hizkeel.tredditapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;
import static com.hizkeel.tredditapp.api.Api.BASE_URL;
import static com.hizkeel.tredditapp.ui.BestFragment.haveNetworkConnection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControversialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControversialFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView mRecyclerView;
    ArrayList<Model> data = new ArrayList<>();

    ProgressDialog progressDialog;

    public ControversialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControversialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControversialFragment newInstance(String param1, String param2) {
        ControversialFragment fragment = new ControversialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_controversial, container, false);

        mRecyclerView = root.findViewById(R.id.recyclerviewControversial);
        //check if there is a network connect(wifi or data)
        if (!haveNetworkConnection(getActivity())) {
            Toast.makeText(getContext(), "Check network connection", LENGTH_SHORT).show();
        } else {
            fetchData();
        }

        return root;
    }



    public void progProc(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Processing"); // Setting Title
//        progressDialog.setIcon(R.drawable.app_logo);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);



    }

    public void fetchData(){

        progProc();

        String URL = BASE_URL+"controversial/.json";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i("xyz", "abc"+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

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
                                GridLayoutManager llm =  new GridLayoutManager(getContext(), 1);
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

                        //retry
                        fetchData();

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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);




    }
}