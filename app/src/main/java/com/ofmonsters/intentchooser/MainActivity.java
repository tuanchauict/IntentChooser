package com.ofmonsters.intentchooser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tuanchauict.intentchooser.SharePlainTextChooser;
import com.tuanchauict.intentchooser.sharetext.EmailChooser;
import com.tuanchauict.intentchooser.sharetext.FacebookChooser;
import com.tuanchauict.intentchooser.sharetext.FacebookMessengerChooser;
import com.tuanchauict.intentchooser.sharetext.GooglePlusChooser;
import com.tuanchauict.intentchooser.sharetext.SMSChooser;
import com.tuanchauict.intentchooser.sharetext.TwitterChooser;
import com.tuanchauict.intentchooser.sharetext.UniversalChooser;
import com.tuanchauict.intentchooser.sharetext.ViberChooser;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_share_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SharePlainTextChooser.newChooser(MainActivity.this)
                        .add(new SMSChooser("SMS"))
                        .add(new FacebookChooser("https://google.com", false))
                        .add(new TwitterChooser("This is text for twitter"))
                        .add(new GooglePlusChooser("This is a text: http://google.com"))
                        .add(new EmailChooser("Email subject", "Email body"))
                        .add(new FacebookMessengerChooser("Facebook messenger"))
                        .add(new ViberChooser("Viber message"))
                        .add(new UniversalChooser("Universal subject", "Universal Text"))
                        .create("Share To");
                startActivity(intent);
            }
        });

    }
}
