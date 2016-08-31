package com.tuanchauict.intentchooser.sharetext;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class UniversalChooser implements Chooser {

    private String mSubject;
    private String mText;

    public UniversalChooser(String subject, String text){
        mText = text;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludePackages) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        List<ResolveInfo> l = pm.queryIntentActivities(intent, 0);
        List<Pair<String, Intent>> result = new ArrayList<>();

        for(ResolveInfo r : l){
            ActivityInfo info = r.activityInfo;
            if(excludePackages == null || !excludePackages.contains(info.packageName)){
                System.out.println(info.packageName + "  " + info.name);
                Intent in = new Intent(Intent.ACTION_SEND);
                in.setComponent(new ComponentName(info.packageName, info.name));
                in.setPackage(info.packageName);
                in.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                in.putExtra(Intent.EXTRA_TITLE, mSubject);
                in.putExtra(Intent.EXTRA_TEXT, mText);
                Pair<String, Intent> p = new Pair<>(info.packageName, in);
                result.add(p);
            }
        }

        return result;
    }


}
