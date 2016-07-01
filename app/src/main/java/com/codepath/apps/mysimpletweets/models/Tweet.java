package com.codepath.apps.mysimpletweets.models;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.extra.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

//Parcelable Object so we can send it through intents.
@org.parceler.Parcel
public class Tweet {

    String body;
    String createdAt;
    User user;
    long uid;


    //At this step we want to have a method to make JSON object into a java object

    public String getBody() {
        return body;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public long getUid() {
        return uid;
    }
    public User getUser(){
        return user;
    }

    //Converts time posted into relative time
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }



    //Empty Constructor so we can instantiate Tweet Object without arguments.
    public Tweet(){}

    //INPUT: JSONOBJECT
    //OUTPUT: Tweet Object
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    /*
    Takes in a JsonArray and outputs an ArrayList of Tweet type objects.
     */
    public static ArrayList<Tweet> fromJSONArray(JSONArray array){
        ArrayList<Tweet> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            try {
                JSONObject tweetJson = array.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null){
                    results.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return results;
    }


}
