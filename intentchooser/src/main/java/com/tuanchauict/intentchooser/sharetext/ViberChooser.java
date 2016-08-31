package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Pair;

import com.tuanchauict.intentchooser.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/31/16.
 */
public class ViberChooser implements ShareTextChooser {
    private static final String VIBER_PACKAGE = "com.viber.voip";
    private static final String VIBER_NAME = "com.viber.voip.WelcomeShareActivity";

    private String mText;

    public ViberChooser(String text) {
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        List<Pair<String, Intent>> result = new ArrayList<>();
        if (Utils.isPackageInstalled(pm, VIBER_PACKAGE)) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setComponent(new ComponentName(VIBER_PACKAGE, VIBER_NAME));
            intent.setPackage(VIBER_PACKAGE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, mText);
            result.add(new Pair<String, Intent>(VIBER_PACKAGE, intent));
        }

        return result;
    }
}
