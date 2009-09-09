/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquared;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Joe LaPenna (joe@joelapenna.com)
 */
public class StatsActivity extends Activity {
    public static final String TAG = "StatsActivity";
    public static final boolean DEBUG = FoursquaredSettings.DEBUG;

    private BroadcastReceiver mLoggedInReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DEBUG) Log.d(TAG, "onReceive: " + intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.stats_activity);
        registerReceiver(mLoggedInReceiver, new IntentFilter(Foursquared.INTENT_ACTION_LOGGED_OUT));

        setTitle("Foursquare Scoreboard");

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());

        loadScoreboardUrl(webView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mLoggedInReceiver);
    }

    private void loadScoreboardUrl(WebView webView) {
        String userId = PreferenceManager.getDefaultSharedPreferences(this).getString(
                Preferences.PREFERENCE_ID, "");
        String cityId = PreferenceManager.getDefaultSharedPreferences(this).getString(
                Preferences.PREFERENCE_CITY_ID, "");

        String url = "http://foursquare.com./iphone/me?view=all&scope=friends&uid=" + userId
                + "&cityid=" + cityId;
        Log.d(TAG, url);
        webView.loadUrl(url);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            setProgress(newProgress * 100);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            setProgressBarVisibility(false);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            setProgressBarVisibility(true);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
