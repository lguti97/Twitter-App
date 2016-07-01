package com.codepath.apps.mysimpletweets.activities;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.extra.SmartFragmentStatePagerAdapter;
import com.codepath.apps.mysimpletweets.fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.TweetsListFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity {


    //Find View Objects
    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.tabs) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.toolbar) Toolbar toolbar;
    //How do we input the image here?
    @BindView(R.id.ivProfile) ImageView ivProfile;
    SmartFragmentStatePagerAdapter adapterViewPager;


    private final int REQUEST_CODE = 20;
    Tweet tweet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        adapterViewPager = new TweetsPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        tabStrip.setViewPager(vpPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);


    }

    /*
    METHOD FOR VIEWING PROFILE
     */

    //Method Executed when User wants to see own Profile.
    public void onProfileView(MenuItem mi) {
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("screen_name", "LuisAGutZap");
        startActivity(i);
    }


    /*
    METHOD FOR RETURNING ACTIVITY FROM COMPOSING TWEET
     */

    //Method Executed when User wants to Tweet.
    public void onComposeView(MenuItem item) {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract object
            tweet =  Parcels.unwrap(data.getParcelableExtra("tweet"));
            HomeTimelineFragment fragmentHomeTweets = (HomeTimelineFragment) adapterViewPager.getRegisteredFragment(0);
            fragmentHomeTweets.appendTweet(tweet);
            adapterViewPager.notifyDataSetChanged();
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        }
    }


    /*
    IMPLEMENTING VIEW PAGER AND FRAGMENTS
     */

    //return the order of the fragments in the view pager
    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter{

        private String tabTitles[] = {"Home", "Mentions"};
        // Adapter gets the manager to insert or remove fragment from activity!
        public TweetsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        // The order and creation of fragments within the paper
        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new HomeTimelineFragment();
                //getSupportActionBar().setTitle("Home");
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        //  return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        // How many fragments there are to swipe
        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }


}
