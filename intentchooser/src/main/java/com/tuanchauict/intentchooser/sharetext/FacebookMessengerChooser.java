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
public class FacebookMessengerChooser implements ShareTextChooser {
    static final String MESSENGER_PACKAGE = "com.facebook.orca";
    static final String MESSAGE_NAME = "com.facebook.messenger.intents.ShareIntentHandler";

    private String mText;

    public FacebookMessengerChooser(String text){
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        if(Utils.isPackageInstalled(pm, MESSENGER_PACKAGE)){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setComponent(new ComponentName(MESSENGER_PACKAGE, MESSAGE_NAME));
            intent.setPackage(MESSENGER_PACKAGE);
            intent.putExtra(Intent.EXTRA_TEXT, mText);
            return Arrays.asList(new Pair<String, Intent>(MESSENGER_PACKAGE, intent));
        }
        return null;
    }
}
