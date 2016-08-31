Simple way to make an intent chooser.

[](screenshots/share_text.png)

# Installation
Add this to your module's `build.gralde`

    repositories {
        maven {
            url  "http://dl.bintray.com/tuanchauict/maven"
        }
    }

    dependencies{
        compile 'com.tuanchauict.intentchooser:intentchooser:0.1.2'
    }

# Usage

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