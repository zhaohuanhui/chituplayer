package com.example.player.main.navigation.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.player.R;
import com.example.player.main.navigation.home.favorites.FavoritesActivity;
import com.example.player.main.navigation.home.search.SearchActivity;
import com.example.player.main.navigation.home.tab.HomeTabFragment;
import com.example.player.view.ScrollTextView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class HomeFragment extends Fragment {
    private static final String NAME = "name";
    private static final String ARG = "arg";
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private ScrollTextView  scrollTextView;
    private  RelativeLayout rl_search;
    private  LinearLayout ll_favorites;
    private String[] tabTitleList = new String[]{"Now Playing","Upcoming"};
    private ArrayList<Fragment> tabFragmetList = new ArrayList<Fragment>();
    public static HomeFragment newInstance(String name, String arg) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(ARG, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        findViewById(inflate);
        initFragment();
        initViewPage();
        onClick();
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        return inflate;
    }

    private void onClick() {
        rl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ll_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment() {
        tabFragmetList.clear();
        HomeTabFragment NowPlayingFragment = HomeTabFragment.newInstance("Now Playing", "");
        tabFragmetList.add(NowPlayingFragment);
        HomeTabFragment UpcomingFragment = HomeTabFragment.newInstance("Upcoming", "");
        tabFragmetList.add(UpcomingFragment);

        List<String> demographicsList = new ArrayList<>();
        demographicsList.add("Please enter the name of the movie one");
        demographicsList.add("Please enter the name of the movie Two");
        demographicsList.add("Please enter the name of the movie Three");
        scrollTextView.setList(demographicsList);
        scrollTextView.startScroll();
    }
    private void initViewPage() {
        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(requireActivity().getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabFragmetList );
        viewPager.setOffscreenPageLimit(tabFragmetList.size());
        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void findViewById(View inflate) {
        tabLayout = inflate.findViewById(R.id.home_tab);
        viewPager = inflate.findViewById(R.id.home_vp);
        scrollTextView=inflate.findViewById(R.id.tv_scroll);
        rl_search=inflate.findViewById(R.id.rl_search);
        ll_favorites=inflate.findViewById(R.id.ll_favorites);
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> tabFragmetList;
        public HomeFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> tabFragmetList) {
            super(fm, behavior);
            this.tabFragmetList = tabFragmetList;
        }
        @Override
        public int getCount() {
            return tabFragmetList.size();
        }
        @Override
        public Fragment getItem(int position) {
            return tabFragmetList.get(position);
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitleList[position];
        }
    }
}


