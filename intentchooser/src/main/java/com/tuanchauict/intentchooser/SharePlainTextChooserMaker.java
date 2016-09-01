package com.tuanchauict.intentchooser;

import android.content.Context;

import com.tuanchauict.intentchooser.sharetext.ShareTextChooser;

/**
 * Created by tuanchauict on 8/30/16.
 */
public class SharePlainTextChooserMaker extends ChooserMaker<ShareTextChooser> {
    public static SharePlainTextChooserMaker newChooser(Context context) {
        SharePlainTextChooserMaker chooser = new SharePlainTextChooserMaker(context);
        return chooser;
    }

    protected SharePlainTextChooserMaker(Context context) {
        super(context);
    }

}
