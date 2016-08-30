package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Pair;

import com.tuanchauict.intentchooser.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class FacebookChooser implements Chooser {
    final static String FACEBOOK_PACKAGE = "com.facebook.katana";
    final static String FACEBOOK_SHARE_NAME = "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias";
    final static String FACEBOOK_SAVE_NAME = "com.facebook.saved.intentfilter.SaveIconSaveToFacebookAlias";
    String mLink;
    boolean mIncludeSaveToFacebook;

    public FacebookChooser(String link, boolean includeSaveToFacebook) {
        mLink = link;
        mIncludeSaveToFacebook = includeSaveToFacebook;
    }


    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        List<Pair<String, Intent>> result = new ArrayList<>();

        if (Utils.isPackageInstalled(pm, FACEBOOK_PACKAGE)) {
            result.add(new Pair<>(FACEBOOK_PACKAGE, createIntent(FACEBOOK_SHARE_NAME)));
            if (mIncludeSaveToFacebook) {
                result.add(new Pair<>(FACEBOOK_PACKAGE, createIntent(FACEBOOK_SAVE_NAME)));
            }
        }
        return result;
    }

    private Intent createIntent(String name) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setComponent(new ComponentName(FACEBOOK_PACKAGE, name));
        intent.setPackage(FACEBOOK_PACKAGE);
        intent.putExtra(Intent.EXTRA_TEXT, mLink);
        return intent;
    }
}
