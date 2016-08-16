package com.android.settings.oneofakind;

import com.android.internal.logging.MetricsLogger;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class About extends SettingsPreferenceFragment {

    public static final String TAG = "About";

    private static final String KEY_ONEOFAKIND_SHARE = "share";

    Preference mWebsiteUrl;
    Preference mCommunityUrl;
    Preference mSourceUrl;
    Preference mMyXdaUrl;
    Preference mMyGooglePlusUrl;
    Preference mDonateMeUrl;
    Preference mDoNothing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.oneofakind_about);

        mWebsiteUrl = findPreference("oneofakind_website");
        mCommunityUrl = findPreference("oneofakind_community");
        mSourceUrl = findPreference("oneofakind_source");
        mMyXdaUrl = findPreference("developer_xda");
        mMyGooglePlusUrl = findPreference("developer_google_plus");
        mDonateMeUrl = findPreference("developer_donation");
        mDoNothing = findPreference("credits_mention");
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mWebsiteUrl) {
            launchUrl("http://oneofakind.bitballoon.com/");
        } else if (preference == mCommunityUrl) {
            launchUrl("https://plus.google.com/communities/109430183766691539019");
        } else if (preference == mSourceUrl) {
            launchUrl("https://github.com/oneofakind-aosp");
        } else if (preference == mMyXdaUrl) {
            launchUrl("http://forum.xda-developers.com/member.php?u=4887164");
        } else if (preference == mDonateMeUrl) {
            launchUrl("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=M5YPM55K2PM8J");
        } else if (preference == mMyGooglePlusUrl) {
            launchUrl("https://plus.google.com/116487239818811681953");
        } else if (preference == mDoNothing) {
            // Do nothing
        } else if (preference.getKey().equals(KEY_ONEOFAKIND_SHARE)) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, String.format(
                getActivity().getString(R.string.share_message), Build.MODEL));
        startActivity(Intent.createChooser(intent, getActivity().getString(R.string.share_chooser_title)));
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
      try {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
      } catch (ActivityNotFoundException ex) {
          Toast.makeText(getActivity().getApplicationContext(), "Please install a browser app", Toast.LENGTH_SHORT).show();
      }
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.DISPLAY;
    }
}
