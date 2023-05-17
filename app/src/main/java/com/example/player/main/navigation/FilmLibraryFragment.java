package com.example.player.main.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.player.R;

public class FilmLibraryFragment extends Fragment {
    private static final String NAME = "name";
    private static final String ARG = "arg";

    public static FilmLibraryFragment newInstance(String name, String arg) {
        FilmLibraryFragment fragment = new FilmLibraryFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(ARG, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_library, container, false);
    }
}
