package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class GooglePlusChooser extends AbstractSpecifiedAppChooser {
    static final String GOOGLE_PLUS_PACKAGE = "com.google.android.apps.plus";
    static final String GOOGLE_PLUS_NAME = "com.google.android.libraries.social.gateway.GatewayActivity";

    String mText;

    public GooglePlusChooser(String text){
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return GOOGLE_PLUS_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return GOOGLE_PLUS_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
