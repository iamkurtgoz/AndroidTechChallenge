package com.iamkurtgoz.androidtechchallenge.ui;

import android.app.Application;

import com.iamkurtgoz.easystore.EasyStore;
import com.iamkurtgoz.easystore.EasyStoreBuilder;
import com.iamkurtgoz.easystore.PreferenceMode;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //SharedPreferences kütüphanesini daha kolay veri kaydetme-okuma için kullanıyorum.
        EasyStoreBuilder.from(getApplicationContext(), "preferencename", PreferenceMode.MODE_PRIVATE).create();
    }
}
