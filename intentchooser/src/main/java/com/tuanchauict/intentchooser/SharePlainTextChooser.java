package com.tuanchauict.intentchooser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.util.Pair;

import com.tuanchauict.intentchooser.sharetext.Chooser;
import com.tuanchauict.intentchooser.sharetext.UniversalChooser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class SharePlainTextChooser {
    public static SharePlainTextChooser newChooser(Context context) {
        SharePlainTextChooser chooser = new SharePlainTextChooser(context);
        return chooser;
    }

    private List<Chooser> mList;
    private Context mContext;


    public SharePlainTextChooser(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public SharePlainTextChooser add(Chooser chooser) {
        mList.add(chooser);
        return this;
    }

    public Intent create(String title) {
        Chooser universalChooser = null;
        for (Chooser chooser : mList) {
            if (chooser instanceof UniversalChooser) {
                universalChooser = chooser;
            }
        }

        PackageManager pm = mContext.getPackageManager();

        Set<String> excluded = new HashSet<>();
        List<Intent> intents = new ArrayList<>();

        if (universalChooser == null) {
            for (Chooser chooser : mList) {
                List<Pair<String, Intent>> ps = chooser.getIntents(mContext, pm, excluded);
                if(ps == null){
                    continue;
                }
                for (Pair<String, Intent> p : ps) {
                    if (p.second != null)
                        intents.add(p.second);
                    excluded.add(p.first);
                }
            }
        } else {
            Map<Chooser, List<Intent>> map = new HashMap<>();
            for (Chooser chooser : mList) {
                if (chooser != universalChooser) {
                    List<Pair<String, Intent>> ps = chooser.getIntents(mContext, pm, excluded);
                    if(ps == null){
                        continue;
                    }
                    List<Intent> l = new ArrayList<>();
                    for (Pair<String, Intent> p : ps) {
                        if (p.second != null)
                            l.add(p.second);
                        excluded.add(p.first);
                    }
                    map.put(chooser, l);
                }
            }
            List<Pair<String, Intent>> ps = universalChooser.getIntents(mContext, pm, excluded);
            if(ps != null) {
                List<Intent> l = new ArrayList<>();
                for (Pair<String, Intent> p : ps) {
                    if (p.second != null)
                        l.add(p.second);
                    excluded.add(p.first);
                }
                map.put(universalChooser, l);
            }

            for (Chooser chooser : mList) {
                intents.addAll(map.get(chooser));
            }
        }

        if (intents.isEmpty()) {
            return null;
        }

        return Utils.createChooserIntent(intents, title);
    }


}
