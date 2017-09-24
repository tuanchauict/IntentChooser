package com.tuanchauict.intentchooser;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.tuanchauict.intentchooser.selectphoto.SelectImageChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tuanchauict on 9/1/16.
 */
public class ImageChooserMaker extends ChooserMaker<SelectImageChooser> {
    private static String sCapturedImageName = "capturedImageResult.jpeg";

    public static ImageChooserMaker newChooser(Context context) {
        ImageChooserMaker chooser = new ImageChooserMaker(context);
        return chooser;
    }

    public ImageChooserMaker(Context context) {
        super(context);
    }

    @TargetApi(18)
    public static List<Uri> getPickMultipleImageResultUris(Context context, Intent data) {
        if (data == null)
            return new ArrayList<>();

        if (data.getClipData() != null) {
            ClipData clipData = data.getClipData();
            int length = clipData.getItemCount();
            List<Uri> result = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                result.add(clipData.getItemAt(i).getUri());
            }
            return result;
        } else {
            return Arrays.asList(getPickImageResultUri(context, data));
        }
    }

    //    /**
//     * Should call this for getting image uri from result intent, especially on getting image from camera.
//     */
    public static Uri getPickImageResultUri(Context context, Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera || data.getData() == null ? getCaptureImageOutputUri(context) : data.getData();
    }

    //    /**
//     * Get URI to image received from capture  by camera.
//     */
    private static Uri getCaptureImageOutputUri(Context context) {
        Uri outputFileUri = null;
        File getImage = context.getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), sCapturedImageName));
        }
        return outputFileUri;
    }

    public static boolean isExplicitCameraPermissionRequired(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return hasPermissionInManifest(context, "android.permission.CAMERA") &&
                    context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    //    /**
//     * Check if the app requests a specific permission in the manifest.
//     * @return true - the permission in requested in manifest, false - not.
//     */
    public static boolean hasPermissionInManifest(Context context, String permissionName) {
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            final String[] declaredPermisisons = packageInfo.requestedPermissions;
            if (declaredPermisisons != null && declaredPermisisons.length > 0) {
                for (String p : declaredPermisisons) {
                    if (p.equalsIgnoreCase(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    public static String getCapturedImageName() {
        return sCapturedImageName;
    }

    public static void setCapturedImageName(String capturedImageName) {
        if (capturedImageName == null || capturedImageName.isEmpty())
            throw new IllegalStateException("capturedImageName must not be empty");
        sCapturedImageName = capturedImageName;
    }
}
