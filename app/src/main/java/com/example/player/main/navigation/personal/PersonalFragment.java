package com.example.player.main.navigation.personal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.App;
import com.example.player.R;
import com.example.player.login.LoginActivity;
import com.example.player.login.UserBean;
import com.example.player.main.navigation.personal.histrory_record.HistoryRecordActivity;
import com.example.player.main.navigation.personal.setting.SetCenterActivity;
import com.example.player.util.SP;
import com.example.player.util.SpKey;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment {
    private List<String> mData = new ArrayList<>();
    private RecyclerView  rv_personal;
    private LinearLayout ll_useId,ll_edit,ll_setting,ll_more;
    private RelativeLayout rl_isLogin;
    private TextView tv_name,tv_useId;
    private ImageView iv_portrait;
    private PersonalAdapter personalAdapter;
    private static final String NAME = "name";
    private static final String ARG = "arg";

    public static PersonalFragment newInstance(String name, String arg) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(ARG, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_personal, container, false);
        findViewById(inflate);
        initAdapter();
        onClick();
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        Boolean isLogin = (Boolean) SP.getInstance().GetData(App.getContext(), SpKey.isLogin, false);
        if(!isLogin){
            tv_name.setText("Log in Register");
            tv_useId.setText("Log in to view more content");
            tv_useId.setTextColor(Color.parseColor("#FF999999"));
            iv_portrait.setBackgroundResource(R.drawable.ic_portrait);
            ll_useId.setBackground(null);
            ll_edit.setVisibility(View.GONE);
        }else{
            String jsonStr= (String) SP.getInstance().GetData(App.getContext(), SpKey.useBean, "");
            UserBean userBean = new Gson().fromJson(jsonStr, UserBean.class);
            tv_name.setText(userBean.getName());
            tv_useId.setTextColor(Color.parseColor("#FF666666"));
            tv_useId.setText("ID："+userBean.getId());
            iv_portrait.setBackgroundResource(R.drawable.ic_02);
            ll_useId.setBackgroundResource(R.drawable.shape_personal_useid_bg);
            ll_edit.setVisibility(View.VISIBLE);
//            android:background="@drawable/shape_edit_bg"
        }
    }

    private void onClick() {
        rl_isLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isLogin = (Boolean) SP.getInstance().GetData(App.getContext(), SpKey.isLogin, false);
                if(!isLogin){
                    Intent intent =new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SetCenterActivity.class);
                startActivity(intent);
            }
        });
        ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HistoryRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        mData.clear();
        mData.add("心花怒放");
        mData.add("霍比特人");
        mData.add("");
        personalAdapter = new PersonalAdapter(mData);
        rv_personal.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_personal.setAdapter(personalAdapter);
    }

    private void findViewById(View inflate) {
        rv_personal=inflate.findViewById(R.id.rv_personal);
        rl_isLogin=inflate.findViewById(R.id.rl_isLogin);
        tv_name=inflate.findViewById(R.id. tv_name);
        tv_useId=inflate.findViewById(R.id.tv_useId);
        iv_portrait=inflate.findViewById(R.id.iv_portrait);
        ll_useId=inflate.findViewById(R.id.ll_useId);
        ll_edit=inflate.findViewById(R.id.ll_edit);
        ll_setting=inflate.findViewById(R.id.ll_setting);
        ll_more=inflate.findViewById(R.id.ll_more);
    }
}
