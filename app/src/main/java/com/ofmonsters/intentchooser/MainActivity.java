package com.ofmonsters.intentchooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.clickme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SharePlainTextChooser.newChooser(MainActivity.this)
                        .add(new SMSChooser("SMS"))
                        .add(new FacebookChooser("https://google.com", false))
                        .add(new TwitterChooser("This is text for twitter"))
                        .add(new GooglePlusChooser("This is a text: http://google.com"))
                        .add(new EmailChooser("Email subject", "Email body"))
                        .add(new FacebookMessengerChooser("Facebook messenger"))
                        .add(new UniversalChooser("Universal subject", "Universal Text"))
                        .create("Share To");
                startActivity(intent);
            }
        });

    }
}
