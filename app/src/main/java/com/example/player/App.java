package com.example.player;

import android.app.Application;

import com.example.player.view.ToastUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(getApplicationContext());
    }

}
