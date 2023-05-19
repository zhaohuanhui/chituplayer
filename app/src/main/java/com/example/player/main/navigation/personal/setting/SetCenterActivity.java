package com.example.player.main.navigation.personal.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player.App;
import com.example.player.R;
import com.example.player.login.UserBean;
import com.example.player.util.SP;
import com.example.player.util.SpKey;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

public class SetCenterActivity  extends AppCompatActivity {
    MaterialButton btn_login_out,btn_switch_account;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_set_center);
            findViewById();
            onClick();
    }

    private void onClick() {
        btn_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SP.getInstance().PutData(App.getContext(), SpKey.isLogin,false);
                finish();
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
    }
}
