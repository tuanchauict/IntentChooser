package com.ofmonsters.intentchooser;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tuanchauict.intentchooser.ImageChooserMaker;
import com.tuanchauict.intentchooser.SharePlainTextChooserMaker;
import com.tuanchauict.intentchooser.selectphoto.CameraChooser;
import com.tuanchauict.intentchooser.selectphoto.ImageChooser;
import com.tuanchauict.intentchooser.sharetext.EmailChooser;
import com.tuanchauict.intentchooser.sharetext.FacebookChooser;
import com.tuanchauict.intentchooser.sharetext.FacebookMessengerChooser;
import com.tuanchauict.intentchooser.sharetext.GooglePlusChooser;
import com.tuanchauict.intentchooser.sharetext.SMSChooser;
import com.tuanchauict.intentchooser.sharetext.SkypeChooser;
import com.tuanchauict.intentchooser.sharetext.TwitterChooser;
import com.tuanchauict.intentchooser.sharetext.UniversalChooser;
import com.tuanchauict.intentchooser.sharetext.ViberChooser;
import com.tuanchauict.intentchooser.sharetext.WhatAppChooser;

import java.util.List;

public class MainActivity extends Activity {
    private static final int REQUEST_IMAGE_CHOOSER = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_share_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SharePlainTextChooserMaker.newChooser(MainActivity.this)
                        .add(new SMSChooser("SMS"))
                        .add(new FacebookChooser("https://google.com", false))
                        .add(new TwitterChooser("This is text for twitter"))
                        .add(new GooglePlusChooser("This is a text: http://google.com"))
                        .add(new WhatAppChooser("WhatApp text"))
                        .add(new SkypeChooser("Skype text"))
                        .add(new EmailChooser("Email subject", "Email body"))
                        .add(new FacebookMessengerChooser("Facebook messenger"))
                        .add(new ViberChooser("Viber message"))
                        .add(new UniversalChooser("Universal subject", "Universal Text"))
                        .create("Share To");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_select_image).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ImageChooserMaker.isExplicitCameraPermissionRequired(MainActivity.this)) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CHOOSER);
                } else {
                    startImageChooserActivity();
                }
            }
        });


    }

    private void startImageChooserActivity() {
        Intent intent = ImageChooserMaker.newChooser(MainActivity.this)
                .add(new CameraChooser())
                .add(new ImageChooser(true))
                .create("Select Image");
        startActivityForResult(intent, REQUEST_IMAGE_CHOOSER);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_IMAGE_CHOOSER) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startImageChooserActivity();
            } else {
                //TODO notify user when no camera permission
                Toast.makeText(this, "Permission failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CHOOSER && resultCode == RESULT_OK) {
            Uri imageUri = ImageChooserMaker.getPickImageResultUri(this, data);
            System.out.println(imageUri.getPath());
        }

    }
}
