package com.tuanchauict.intentchooser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.util.Pair;

import com.tuanchauict.intentchooser.sharetext.UniversalChooser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tuanchauict on 8/31/16.
 */
public abstract class ChooserMaker<T extends ChooserMaker.Chooser> {
    public interface Chooser {
        /**
         * @param pm
         * @param excludedPackages
         * @return a list of pairs of packageName and intent
         */
        List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages);
    }

    private List<T> mList;
    private Context mContext;

    protected ChooserMaker(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public ChooserMaker<T> add(T chooser) {
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
                if (ps == null) {
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
                    if (ps == null) {
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
            if (ps != null) {
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

        return createChooserIntent(intents, title);
    }

    private Intent createChooserIntent(List<Intent> intents, String title) {
        if (intents == null || intents.isEmpty()) {
            return null;
        }
        Intent target;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            target = new Intent();
        } else {
            target = intents.get(intents.size() - 1);
            intents.remove(intents.size() - 1);
        }

        Intent intent = Intent.createChooser(target, title);
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[intents.size()]));
        return intent;
    }
}
