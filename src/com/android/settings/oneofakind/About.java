package com.android.settings.oneofakind;

import com.android.internal.logging.MetricsLogger;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;

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
    Preference mMyGithubUrl;
    Preference mMyGooglePlusUrl;
    Preference mMickybartUrl;
    Preference mJonathanUrl;
    Preference mOmniUrl;
    Preference mBitsykoUrl;
    Preference mChainfireUrl;
    Preference mXdaUrl;
    Preference mGoogleUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.oneofakind_about);

        mWebsiteUrl = findPreference("oneofakind_website");
        mCommunityUrl = findPreference("oneofakind_community");
        mSourceUrl = findPreference("oneofakind_source");
        mMyXdaUrl = findPreference("developer_xda");
        mMyGithubUrl = findPreference("developer_github");
        mMyGooglePlusUrl = findPreference("developer_google_plus");
        mMickybartUrl = findPreference("credits_mickybart");
        mJonathanUrl = findPreference("credits_jonathan");
        mOmniUrl = findPreference("credits_omnirom");
        mBitsykoUrl = findPreference("credits_bitsyko");
        mChainfireUrl = findPreference("credits_chainfire");
        mXdaUrl = findPreference("credits_xda");
        mGoogleUrl = findPreference("credits_google");

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mWebsiteUrl) {
            launchUrl("http://oneofakind.bitballoon.com/");
        } else if (preference == mCommunityUrl) {
            launchUrl("https://plus.google.com/communities/109430183766691539019");
        } else if (preference == mSourceUrl) {
            launchUrl("https://github.com/Oneofakind-AOSP");
        } else if (preference == mMyXdaUrl) {
            launchUrl("http://forum.xda-developers.com/member.php?u=4887164");
        } else if (preference == mMyGithubUrl) {
            launchUrl("https://github.com/ipromeh");
        } else if (preference == mMyGooglePlusUrl) {
            launchUrl("https://plus.google.com/116487239818811681953");
        } else if (preference == mMickybartUrl) {
            launchUrl("http://forum.xda-developers.com/member.php?u=6043081");
        } else if (preference == mJonathanUrl) {
            launchUrl("http://forum.xda-developers.com/member.php?u=5176116");
        } else if (preference == mOmniUrl) {
            launchUrl("http://forum.xda-developers.com/omni");
        } else if (preference == mBitsykoUrl) {
            launchUrl("https://plus.google.com/communities/102261717366580091389");
        } else if (preference == mChainfireUrl) {
            launchUrl("http://forum.xda-developers.com/member.php?u=631273");
        } else if (preference == mXdaUrl) {
            launchUrl("http://www.xda-developers.com/");
        } else if (preference == mGoogleUrl) {
            launchUrl("https://android.googlesource.com/");
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
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsLogger.DISPLAY;
    }
}
