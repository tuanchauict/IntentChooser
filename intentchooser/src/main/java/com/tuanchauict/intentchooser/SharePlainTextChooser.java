package com.tuanchauict.intentchooser;

import android.content.Context;

import com.tuanchauict.intentchooser.sharetext.ShareTextChooser;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class SharePlainTextChooser extends ChooserMaker<ShareTextChooser> {
    public static SharePlainTextChooser newChooser(Context context) {
        SharePlainTextChooser chooser = new SharePlainTextChooser(context);
        return chooser;
    }

    protected SharePlainTextChooser(Context context) {
        super(context);
    }

}
