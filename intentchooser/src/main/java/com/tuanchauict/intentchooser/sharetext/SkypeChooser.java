package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/31/16.
 */
public class SkypeChooser extends AbstractSpecifiedAppChooser {
    static final String SKYPE_PACKAGE = "com.skype.raider";
    static final String SKYPE_NAME = "com.skype.android.app.main.SplashActivity";

    private String mText;

    public SkypeChooser(String text) {
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return SKYPE_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return SKYPE_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
