package com.example.player.main.navigation.personal.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player.App;
import com.example.player.R;
import com.example.player.login.UserBean;
import com.example.player.main.navigation.home.favorites.FavoritesActivity;
import com.example.player.util.CacheDataManager;
import com.example.player.util.SP;
import com.example.player.util.SpKey;
import com.example.player.view.DelDialog;
import com.example.player.view.ToastUtil;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class SetCenterActivity  extends AppCompatActivity {
    MaterialButton btn_login_out,btn_switch_account;
    TextView tv_cache;
    RelativeLayout rl_clearCache;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_set_center);
            findViewById();
            onClick();
            setData();
    }

    private void setData() {
        try {
            tv_cache.setText(CacheDataManager.getTotalCacheSize(SetCenterActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void onClick() {
        btn_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SP.getInstance().PutData(App.getContext(), SpKey.isLogin,false);
                finish();
            }
        });
        rl_clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelDialog.Builder builder= new DelDialog.Builder(SetCenterActivity.this);
                builder.setInfo("Whether to clear the cache ?");
                builder.setButtonConfirm("To clean up", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         new Thread(new clearCache()).start();
                    }
                });
                builder.setButtonCancel("no", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        Boolean isLogin = (Boolean) SP.getInstance().GetData(App.getContext(), SpKey.isLogin, false);
        if(!isLogin){
            btn_login_out.setVisibility(View.GONE);
            btn_switch_account.setVisibility(View.GONE);
        }else{
            btn_login_out.setVisibility(View.VISIBLE);
            btn_switch_account.setVisibility(View.VISIBLE);
        }
    }

    private void findViewById() {
        btn_login_out= findViewById(R.id.btn_login_out);
        btn_switch_account= findViewById(R.id.btn_switch_account);
        tv_cache=findViewById(R.id.tv_cache);
        rl_clearCache=findViewById(R.id.rl_clearCache);
    }


    /**
     * 创建Handler
     * 接收消息
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtil.successShortToast("清理完成");
                    try {
                        tv_cache.setText(CacheDataManager.getTotalCacheSize(SetCenterActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    /**
     * 创建内部类，清除缓存
     */
    class clearCache implements Runnable {
        @Override
        public void run() {
            try {
                CacheDataManager.clearAllCache(SetCenterActivity.this);

                Thread.sleep(1000);

                if (CacheDataManager.getTotalCacheSize(SetCenterActivity.this).startsWith("0")) {

                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }
}
