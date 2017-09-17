package com.tuanchauict.intentchooser.sharetext;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

        String defApp = Settings.Secure.getString(context.getContentResolver(),
                "sms_default_application");
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        if (defApp != null && !defApp.isEmpty())
            intent.setPackage(defApp);
        intent.putExtra("sms_body", mText);

        List<Pair<String, Intent>> result = new ArrayList<>();
        result.add(new Pair<>(defApp, intent));

        return result;
    }
}
