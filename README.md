# Project 3 - *Twitter-App*

**Twitter-App** is an android app that allows a user to view home and mentions timelines, view user profiles with user timelines, as well as compose and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **30** hours spent in total

## User Stories

The following **required** functionality is completed:

* [YES] User can **sign in to Twitter** using OAuth login process
* [YES] User can **switch between Timeline and Mention views using tabs**
  * [YES] User is displayed the username, name, and body for each tweet
  * [YES] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [YES] User can **compose and post a new tweet**
  * [YES] User can click a "Compose" icon in the Action Bar on the top right
  * [YES] User can then enter a new tweet from a second activity and then post this to twitter
* [YES] User can navigate to **view their own profile**
  * [YES] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* [YES] User can **click on the profile image** in any tweet to see **another user's** profile.
 * [YES] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
 * [YES] Profile view includes that user's timeline of recent tweets

The following **optional** features are implemented:

* [ ] While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
* [YES] User can **pull down to refresh tweets** in either timeline.
* [ ] User can **search for tweets matching a particular query** and see results.
* [YES] Improve the user interface and theme the app to feel twitter branded with colors and styles
* [ ] When a network request is sent, user sees an [indeterminate progress indicator](http://guides.codepath.com/android/Handling-ProgressBars#progress-within-actionbar)
* [ ] User can **"reply" to any tweet on their home timeline**
  * [ ] The user that wrote the original tweet is automatically "@" replied in compose
* [ ] User can click on a tweet to be **taken to a "detail view"** of that tweet
 * [ ] User can take favorite (and unfavorite) or retweet actions on a tweet
* [YES] User can see embedded image media within the tweet item in list or detail view.
* [ ] Compose activity is replaced with a modal compose overlay.
* [ ] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [YES] Used Parcelable instead of Serializable leveraging the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler) when passing data between activities.
* [ ] Replaced all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [ ] User can view following / followers list through the profile of a user
* [YES] Apply the popular Butterknife annotation library to reduce view boilerplate.
* [ ] Implement collapse scrolling effects on the Twitter profile view using `CoordinatorLayout`.
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in an offline mode.

The following **additional** features are implemented:

* [ ] User can view more tweets as they scroll with [infinite pagination](http://guides.codepath.com/android/Endless-Scrolling-with-AdapterViews-and-RecyclerView). Number of tweets is unlimited.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

With this project, I focused more on the UI because I wanted hone my front end / UI skills better. I could have gotten more done if I understood fragment better though (I took a long time trying to accomplish things with fragments) 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Glide]
- [ButterKnife]
- [Parcel?]

## License

    Copyright [2016] [Luis A Gutierrez]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
