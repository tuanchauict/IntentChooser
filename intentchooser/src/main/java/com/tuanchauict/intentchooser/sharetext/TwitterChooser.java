package com.tuanchauict.intentchooser.sharetext;

import android.content.Intent;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class TwitterChooser extends AbstractSpecifiedAppChooser {
    static final String TWITTER_PACKAGE = "com.twitter.android";
    static final String TWITTER_NAME = "com.twitter.android.composer.ComposerActivity";

    String mText;

    public TwitterChooser(String text){
        mText = text;
    }

    @Override
    protected String getAppPackage() {
        return TWITTER_PACKAGE;
    }

    @Override
    protected String getActivityName() {
        return TWITTER_NAME;
    }

    @Override
    protected void putExtra(Intent intent) {
        intent.putExtra(Intent.EXTRA_TEXT, mText);
    }
}
