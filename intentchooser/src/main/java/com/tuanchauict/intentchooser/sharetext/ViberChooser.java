package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/31/16.
 */
public class ViberChooser extends AbstractSpecifiedAppChooser {
    private static final String VIBER_PACKAGE = "com.viber.voip";
    private static final String VIBER_NAME = "com.viber.voip.WelcomeShareActivity";

    private String mText;

    public ViberChooser(String text) {
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return VIBER_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return VIBER_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
