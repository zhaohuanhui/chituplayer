package com.example.player.main.navigation.home.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;

import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<String> mData = new ArrayList<>();
    private static final String NAME = "name";
    private static final String ARG = "arg";
    private  String name;
    private  String arg;
    public static HomeTabFragment newInstance(String name, String arg) {
        HomeTabFragment fragment = new HomeTabFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(ARG, arg);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_tab, container, false);
        findViewById(inflate);
        mData.clear();
        mData.add("哈利波特");
        mData.add("独行地球");
        mData.add("人生大事");
        mData.add("功夫熊猫3");
        HomeTabAdapter  homeTabAdapter = new HomeTabAdapter(mData);
        recyclerView.setAdapter(homeTabAdapter);
        return inflate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            arg = getArguments().getString(ARG);
        }
    }
    private void findViewById(View inflate) {
        recyclerView= (RecyclerView) inflate.findViewById(R.id.home_tab_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
    }
}
