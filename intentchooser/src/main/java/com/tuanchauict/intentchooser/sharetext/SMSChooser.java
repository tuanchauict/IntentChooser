package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class SMSChooser implements ShareTextChooser {
    private String mText;

    public SMSChooser(String text) {
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        String defApp = Settings.Secure.getString(context.getContentResolver(), "sms_default_application");
        Intent defIntent = pm.getLaunchIntentForPackage(defApp);
        ResolveInfo info = pm.resolveActivity(defIntent, 0);
        String pkg = info.activityInfo.packageName;

        List<Pair<String, Intent>> result = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("sms:"));
        intent.setComponent(new ComponentName(pkg, info.activityInfo.name));
        intent.setPackage(pkg);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mText);
        result.add(new Pair<String, Intent>(pkg, intent));

        //get all sms package for excluding purpose
//        Intent excludingIntent = new Intent(Intent.ACTION_SENDTO);
//        excludingIntent.setData(Uri.parse("sms:"));
//        List<ResolveInfo> l = pm.queryIntentActivities(excludingIntent, 0);
//        System.out.println("sms size = " + l.size());
//        for (ResolveInfo r : l) {
//            result.add(new Pair<String, Intent>(r.activityInfo.packageName, null));
//        }

        return result;
    }
}
