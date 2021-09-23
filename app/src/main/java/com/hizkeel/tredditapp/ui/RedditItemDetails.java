package com.hizkeel.tredditapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hizkeel.tredditapp.R;
import com.hizkeel.tredditapp.ui.MoreDetailsActivity;

public class RedditItemDetails extends AppCompatActivity {

    TextView subreddit, titlee, selftext, ups, downs,num_comments,score,created,author,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_item_details);


        subreddit = findViewById(R.id.subreddit);
        selftext = findViewById(R.id.selftext);
        ups = findViewById(R.id.ups);
        downs = findViewById(R.id.downs);
        num_comments = findViewById(R.id.num_comments);
        score = findViewById(R.id.score);
        created = findViewById(R.id.created);
        author = findViewById(R.id.author);
        titlee = findViewById(R.id.titlee);
        url = findViewById(R.id.url);






        subreddit.setText(getIntent().getExtras().getString("subreddit"));
        titlee.setText(getIntent().getExtras().getString("title"));
        ups.setText(getIntent().getExtras().getString("ups"));
        downs.setText(getIntent().getExtras().getString("downs"));
        score.setText(getIntent().getExtras().getString("score"));
        url.setText(getIntent().getExtras().getString("url"));
        num_comments.setText(getIntent().getExtras().getString("num_comments"));
        author.setText(getIntent().getExtras().getString("author"));
        selftext.setText(getIntent().getExtras().getString("selftext"));
        created.setText(getIntent().getExtras().getString("created"));





    }

    public void moreDetails(View v){
        final Intent it1 = new Intent(this, MoreDetailsActivity.class);
        it1.putExtra("url_more", getIntent().getExtras().getString("url"));
        startActivity(it1);
    }
}