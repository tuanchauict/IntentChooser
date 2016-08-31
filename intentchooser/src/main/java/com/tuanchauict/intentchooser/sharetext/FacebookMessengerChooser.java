package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class FacebookMessengerChooser extends AbstractSpecifiedAppChooser {
    static final String MESSENGER_PACKAGE = "com.facebook.orca";
    static final String MESSAGE_NAME = "com.facebook.messenger.intents.ShareIntentHandler";

    private String mText;

    public FacebookMessengerChooser(String text){
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return MESSENGER_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return MESSAGE_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
