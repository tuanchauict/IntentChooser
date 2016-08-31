package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class EmailChooser implements ShareTextChooser {
    String mSubject;
    String mBody;

    public EmailChooser(String subject, String body) {
        mSubject = subject;
        mBody = body;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        List<ResolveInfo> l = pm.queryIntentActivities(intent, 0);
        List<Pair<String, Intent>> result = new ArrayList<>(l.size());
        for (ResolveInfo r : l) {
            ActivityInfo info = r.activityInfo;
            if (excludedPackages == null || !excludedPackages.contains(info.packageName)) {
                Intent in = new Intent(Intent.ACTION_SENDTO);
                in.setComponent(new ComponentName(r.activityInfo.packageName, r.activityInfo.name));
                intent.setPackage(r.activityInfo.packageName);
                intent.setType("text/plain");
                in.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                in.putExtra(Intent.EXTRA_TEXT, mBody);
                result.add(new Pair<String, Intent>(info.packageName, in));

            }
        }

        return result;
    }
}
