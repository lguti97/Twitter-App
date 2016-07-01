package com.codepath.apps.mysimpletweets.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

@org.parceler.Parcel
public class User {
    String name;
    String screenName;
    String profileImageUrl;
    Long uid;

    public User(){}

    public String getTagline() {
        return tagline;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    String tagline;
    int followersCount;
    int followingCount;

    public String getScreenName() {
        return screenName;
    }
    public String getName() {
        return name;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public long getUid() {
        return uid;
    }


    //Method to deserialize JsonObject and make it into User Object
    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.uid = jsonObject.getLong("id");
            user.tagline = jsonObject.getString("description");
            user.followersCount = jsonObject.getInt("followers_count");
            user.followingCount = jsonObject.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;

    }


}
