package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/31/16.
 */
public class WhatAppChooser extends AbstractSpecifiedAppChooser {
    static final String WHATAPP_PACKAGE = "com.whatsapp";
    static final String WHATAPP_NAME = "com.whatsapp.ContactPicker";

    private String mText;

    public WhatAppChooser(String text) {
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return WHATAPP_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return WHATAPP_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
