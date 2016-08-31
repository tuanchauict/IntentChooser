package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Pair;

import com.tuanchauict.intentchooser.Utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class GooglePlusChooser implements ShareTextChooser {
    static final String GOOGLE_PLUS_PACKAGE = "com.google.android.apps.plus";
    static final String GOOGLE_PLUS_NAME = "com.google.android.libraries.social.gateway.GatewayActivity";

    String mText;

    public GooglePlusChooser(String text){
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {

        if (Utils.isPackageInstalled(pm, GOOGLE_PLUS_PACKAGE)) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setComponent(new ComponentName(GOOGLE_PLUS_PACKAGE, GOOGLE_PLUS_NAME));
            intent.setPackage(GOOGLE_PLUS_PACKAGE);
            intent.putExtra(Intent.EXTRA_TEXT, mText);

            return Arrays.asList(new Pair<String, Intent>(GOOGLE_PLUS_PACKAGE, intent));
        }
        return null;
    }
}
