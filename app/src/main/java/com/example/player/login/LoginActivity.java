package com.example.player.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.player.App;
import com.example.player.R;
import com.example.player.main.navigation.home.favorites.FavoritesActivity;
import com.example.player.util.SP;
import com.example.player.util.SpKey;
import com.example.player.view.ToastUtil;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout ll_back, ll_isCheck;
    private ImageView iv_isCheck,iv_ishow;
    private EditText et_password,et_userName;
    private boolean isRemember;
    private boolean isShow;
    private Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        initData();
        onClick();
    }

    private void initData() {
        Boolean isRemember = (Boolean) SP.getInstance().GetData(App.getContext(), SpKey.isRemember, false);
        if(isRemember){
            String jsonStr= (String) SP.getInstance().GetData(App.getContext(), SpKey.useBean, "");
                UserBean userBean = new Gson().fromJson(jsonStr, UserBean.class);
                et_password.setText(userBean.passWord);
                et_userName.setText(userBean.account);
        }
    }

    private void onClick() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_isCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRemember) {
                    isRemember = false;
                    iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_n);
                } else {
                    isRemember = true;
                    iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_y);
                }
            }
        });
        iv_ishow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShow){
                    isShow = false;
                    iv_ishow.setBackgroundResource(R.drawable.ic_ishow_n);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    isShow = true;
                    iv_ishow.setBackgroundResource(R.drawable.ic_ishow_y);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_userName.getText().toString().trim().length()==0){
                    ToastUtil.errorShortToast("请输入账号");
                    return;
                }
                if(et_password.getText().toString().trim().length()==0){
                    ToastUtil.errorShortToast("请输入密码");
                    return;
                }
                UserBean userBean = new UserBean("ikun","9475863748","qq123456","abc123");
                SP.getInstance().PutData(App.getContext(), SpKey.isLogin,true);
                SP.getInstance().PutData(App.getContext(), SpKey.isRemember,isRemember);
                SP.getInstance().PutData(App.getContext(), SpKey.useBean,new Gson().toJson(userBean));
                finish();
            }
        });
    }

    private void findViewById() {
        ll_back = findViewById(R.id.ll_back);
        ll_isCheck = findViewById(R.id.ll_isCheck);
        iv_isCheck = findViewById(R.id.iv_isCheck);
        iv_ishow = findViewById(R.id.iv_ishow);
        et_password=findViewById(R.id.et_password);
        et_userName=findViewById(R.id.et_userName);
        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btn_login=findViewById(R.id.btn_login);
        et_userName=findViewById(R.id.et_userName);
    }
}
