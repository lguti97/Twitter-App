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
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.application.TwitterApplication;
import com.codepath.apps.mysimpletweets.extra.SmartFragmentStatePagerAdapter;
import com.codepath.apps.mysimpletweets.fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.MessagesTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.NotificationsTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.TweetsListFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {


    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.tabs) PagerSlidingTabStrip tabStrip;
    @BindView(R.id.toolbar) Toolbar toolbar;
    //How do we input the image here?
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    SmartFragmentStatePagerAdapter adapterViewPager;



    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        tvTitle.setText("Home");
        TwitterApplication.getRestClient().getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                User user = User.fromJSON(response);
                Glide.with(getApplicationContext()).load(user.getProfileImageUrl()).into(ivProfile);
            }
        });


        adapterViewPager = new TweetsPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        //Listener that basically allows me to decide when I'm in a different fragment and shit.
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //With this method I can choose what to do with each fragment.
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvTitle.setText("Home");

                    //getSupportActionBar().setTitle("Home");
                } else if (position == 1) {
                    tvTitle.setText("Mentions");
                } else if (position == 2) {
                    tvTitle.setText("Notifications");
                } else if (position == 3) {
                    tvTitle.setText("Messages");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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


    public void ComposeTweet(View view) {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract object
            Tweet tweet =  Parcels.unwrap(data.getParcelableExtra("tweet"));
            HomeTimelineFragment fragmentHomeTweets = (HomeTimelineFragment) adapterViewPager.getRegisteredFragment(0);
            fragmentHomeTweets.appendTweet(tweet);
            adapterViewPager.notifyDataSetChanged();
            // TODO. Make it so when tweet is composed, the user is taken to the home pager.
            new HomeTimelineFragment();

        }
    }



    /*
    IMPLEMENTING VIEW PAGER AND FRAGMENTS
     */

    //return the order of the fragments in the view pager
    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter{

        private String tabTitles[] = {"Home", "Mentions", "Notifications", "Messages"};
        // Adapter gets the manager to insert or remove fragment from activity!
        public TweetsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        // The order and creation of fragments within the paper
        // This shit get's executed only once (Fuck me, I spent so much thing on this)
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                //For some reason it's only showing the mentions set title but not firing the position = 0;
                return new HomeTimelineFragment();

            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else if (position == 2) {
                return new NotificationsTimelineFragment();
            } else if (position == 3){
                return new MessagesTimelineFragment();
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
