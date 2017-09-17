package com.tuanchauict.intentchooser.selectphoto;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Pair;

import com.tuanchauict.intentchooser.ImageChooserMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tuanchauict on 9/1/16.
 */
public class CameraChooser implements SelectImageChooser {

    @Override
    public List<Pair<String, Intent>> getIntents(Context context, PackageManager pm, Collection<String> excludedPackages) {
        Uri outputFileUri = getCaptureImageOutputUri(context);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = pm.queryIntentActivities(captureIntent, 0);
        List<Pair<String, Intent>> result = new ArrayList<>();
        for (ResolveInfo res : listCam) {
            ActivityInfo info = res.activityInfo;
            System.out.println(info.packageName + " " + info.name);
            if (excludedPackages.contains(info.packageName))
                continue;
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(info.packageName, info.name));
            intent.setPackage(info.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            result.add(new Pair<>(info.packageName, intent));
        }

        return result;
    }

    /**
     * Get URI to image received from capture  by camera.
     *
     * @param context used to access Android APIs, like content resolve, it is your activity/fragment/widget.
     */
    private Uri getCaptureImageOutputUri(Context context) {
        Uri outputFileUri = null;
        File getImage = context.getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), ImageChooserMaker.getCapturedImageName()));
        }
        return outputFileUri;
    }
}
