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
 * Created by tuanchauict on 8/31/16.
 */
public abstract class AbstractSpecifiedAppChooser implements ShareTextChooser {
    protected abstract String getAppPackage();
    protected abstract String getActivityName();
    protected abstract void putExtra(Intent intent);

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        String pkg = getAppPackage();
        String name = getActivityName();
        if(Utils.isPackageInstalled(pm, pkg)) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setComponent(new ComponentName(pkg, name));
            intent.setPackage(pkg);
            intent.setType("text/plain");
            putExtra(intent);
            return Arrays.asList(new Pair<String, Intent>(pkg, intent));
        }
        return null;
    }
}
