package com.example.player;

import android.app.Application;
import android.content.Context;

import com.example.player.view.ToastUtil;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ToastUtil.init(getApplicationContext());
    }
    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }
}
