package com.example.player.main.navigation.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.example.player.main.navigation.home.tab.HomeTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment {
    private List<String> mData = new ArrayList<>();
    private RecyclerView  rv_personal;
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
        return inflate;
    }

    private void initAdapter() {
        mData.clear();
        mData.add("心花怒放");
        mData.add("霍比特人");
        mData.add("");
        PersonalAdapter personalAdapter = new PersonalAdapter(mData);
        rv_personal.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_personal.setAdapter(personalAdapter);
    }

    private void findViewById(View inflate) {
        rv_personal=inflate.findViewById(R.id.rv_personal);
    }
}
