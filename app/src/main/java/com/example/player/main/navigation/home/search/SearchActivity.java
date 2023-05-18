package com.example.player.main.navigation.home.search;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.example.player.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class SearchActivity extends AppCompatActivity {
    private LinearLayout ll_back,ll_Expand,ll_del,ll_search;
    private RecyclerView rv_search_list;
    private TextView tv_history;
    private EditText et_search;
    private SearchHistoryAdapter searchHistoryAdapter;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViewById();
        onClick();
        initAdapter();
    }

    private void initAdapter() {
        mData.clear();
        mData.add("斗破苍穹");
        mData.add("苏里南");
        mData.add("黑话律师");
        mData.add("斗罗大陆");
        mData.add("罚罪");
        mData.add("梦华录");
        mData.add("黑袍纠察队第三季");
        mData.add("向往的生活第六季");
         searchHistoryAdapter = new SearchHistoryAdapter(mData);
        rv_search_list.setAdapter(searchHistoryAdapter);
    }

    private void onClick() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_Expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_Expand.setVisibility(GONE);
                mData.add("新增1");
                mData.add("新增2");
                mData.add("新增3");
                mData.add("新增4");
                mData.add("新增5");
                searchHistoryAdapter.notifyDataSetChanged();
            }
        });
        ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_history.setVisibility(GONE);
                ll_del.setVisibility(GONE);
                ll_Expand.setVisibility(GONE);
                mData.clear();
                searchHistoryAdapter.notifyDataSetChanged();
            }
        });

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_history.setVisibility(View.VISIBLE);
                ll_del.setVisibility(View.VISIBLE);
                ll_Expand.setVisibility(View.VISIBLE);
                if (et_search.getText().toString().trim().length() > 0) {
                mData.add(0,et_search.getText().toString());
                searchHistoryAdapter.notifyDataSetChanged();}
            }
        });
    }
    private void findViewById() {
        ll_back=findViewById(R.id.ll_back);
        rv_search_list=findViewById(R.id.rv_search_list);
        ll_Expand=findViewById(R.id.ll_Expand);
        ll_del=findViewById(R.id.ll_del);
        ll_search=findViewById(R.id.ll_search);
        tv_history=findViewById(R.id.tv_history);
        et_search=findViewById(R.id.et_search);
        rv_search_list.setLayoutManager(new GridLayoutManager(this,2));
    }


}
