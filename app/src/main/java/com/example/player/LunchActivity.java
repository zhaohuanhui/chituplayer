package com.example.player;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player.main.MainActivity;

import static java.lang.Thread.sleep;

public class LunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Chituplayer);
        super.onCreate(savedInstanceState);
        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent =new Intent(LunchActivity.this, MainActivity.class);
                        startActivity(intent);
                        LunchActivity.this.finish();
                    }
                });
            }
        } ).start();
    }
}
