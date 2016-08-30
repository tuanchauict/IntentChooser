package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
public class TwitterChooser implements Chooser {
    static final String TWITTER_PACKAGE = "com.twitter.android";
    static final String TWITTER_NAME = "com.twitter.android.composer.ComposerActivity";

    String mText;

    public TwitterChooser(String text){
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        List<Pair<String, Intent>> result = new ArrayList<>();
        if(Utils.isPackageInstalled(pm, TWITTER_PACKAGE)){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setComponent(new ComponentName(TWITTER_PACKAGE, TWITTER_NAME));
            intent.setPackage(TWITTER_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, mText);
            result.add(new Pair<String, Intent>(TWITTER_PACKAGE, intent));
        }

        return result;
    }
}
