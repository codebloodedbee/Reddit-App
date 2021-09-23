package com.hizkeel.tredditapp.model;

public class Model {

    public String subreddit;
    public String selftext;
    public String title;
    public String ups;
    public String score;
    public String created;
    public String author;
    public String num_comments;
    public String url;
    public String downs;



    public Model(String subreddit, String selftext, String title, String ups, String score,
                 String created, String author, String num_comments, String url, String downs) {
        this.subreddit = subreddit;
        this.selftext = selftext;
        this.title = title;
        this.ups = ups;
        this.score = score;
        this.created = created;
        this.author = author;
        this.num_comments = num_comments;
        this.url = url;
        this.downs = downs;
    }




    public Model(String name, String age, boolean b) {
        this.name = name;
        this.age = age;
    }

    String name;
    String age;


}
