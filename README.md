Simple way to make an intent chooser.

![](https://github.com/tuanchauict/IntentChooser/blob/master/screenshots/share_text.png?raw=true)

# Installation

    compile 'com.tuanchauict.intentchooser:intentchooser:0.1.+'


# Usage

    Intent intent = SharePlainTextChooserMaker.newChooser(MainActivity.this)
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

For Image selection, please take a look at example app's MainActivity