package com.tuanchauict.intentchooser.sharetext;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Pair;

import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 8/30/16.
 */
public interface Chooser {
    /**
     *
     * @param pm
     * @param excludedPackages
     * @return a list of pairs of packageName and intent
     */
    List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages);
}
