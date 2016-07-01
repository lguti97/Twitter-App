package com.codepath.apps.mysimpletweets.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.activities.ProfileActivity;
import com.codepath.apps.mysimpletweets.activities.TimelineActivity;
import com.codepath.apps.mysimpletweets.extra.TimeFormatter;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lguti on 6/27/16.
 */
//Taking tweet object and turning them into View Objects
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    //ViewHolder allows quicker lookup up of View Objects.
    public static class ViewHolder {
        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvBody;
        TextView tvPosted;
    }

    public TweetsArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, 0, tweets);
    }

    //Method initiates dirty sock method of mapping data to view objects.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
            viewHolder.ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
            viewHolder.tvPosted = (TextView) convertView.findViewById(R.id.tvPosted);
            Typeface font0 = Typeface.createFromAsset(getContext().getAssets(), "Gotham-Bold(1).ttf");
            Typeface font1 = Typeface.createFromAsset(getContext().getAssets(), "Gotham-Light(1).ttf");
            viewHolder.tvBody.setTypeface(font1);
            viewHolder.tvUserName.setTypeface(font0);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String formattedTime = TimeFormatter.getTimeDifference(tweet.getCreatedAt());

        viewHolder.tvUserName.setText(tweet.getUser().getScreenName());
        viewHolder.tvBody.setText(tweet.getBody());
        viewHolder.tvPosted.setText(formattedTime);
        viewHolder.ivProfileImage.setImageResource(0);
        Glide.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.ivProfileImage);

        // Allows user to click on profile image to observe user!
        viewHolder.ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String screenName = tweet.getUser().getScreenName();
                Intent i = new Intent(getContext(), ProfileActivity.class);
                i.putExtra("screen_name", screenName);
                i.putExtra("position", position);
                //Next have to get the user object and then make sure that's used in the ProfileActivity (or updated)
                getContext().startActivity(i);
            }
        });


        return convertView;


    }


}
