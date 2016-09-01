package com.tuanchauict.intentchooser.selectphoto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 9/1/16.
 */
public class ImageChooser implements SelectImageChooser {
    boolean mPickMultipleImage;

    /**
     * pick multiple image only available for API >= 18
     *
     * @param pickMultipleImage
     */
    public ImageChooser(boolean pickMultipleImage) {
        mPickMultipleImage = Build.VERSION.SDK_INT >= 18 ? pickMultipleImage : false;
    }

    public ImageChooser() {
        mPickMultipleImage = false;
    }

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = pm.queryIntentActivities(galleryIntent, 0);
        List<Pair<String, Intent>> result = new ArrayList<>();
        for (ResolveInfo res : listGallery) {
            ActivityInfo info = res.activityInfo;
            System.out.println(info.packageName + " " + info.name);
            if (excludedPackages.contains(info.packageName))
                continue;
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (mPickMultipleImage)
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, mPickMultipleImage);

            result.add(new Pair<String, Intent>(info.packageName, intent));
        }

        return result;
    }
}
