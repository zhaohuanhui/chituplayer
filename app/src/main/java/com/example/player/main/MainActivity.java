package com.example.player.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.player.R;
import com.example.player.main.navigation.film_library.FilmLibraryFragment;
import com.example.player.main.navigation.home.HomeFragment;
import com.example.player.main.navigation.PersonalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


public class MainActivity extends AppCompatActivity {
    List<Fragment> fragmentList = new ArrayList<>();
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         findViewById();
        initFragment();
        initViewPager();
        initBottomNavigationView();
    }

    private void findViewById() {
        viewPager = findViewById(R.id.main_vp);
        bottomNavigationView = findViewById(R.id.main_bnv);
    }
    private void initViewPager() {
        MainActivityPagerAdapter mainActivityPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT , fragmentList);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setAdapter(mainActivityPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.film_library);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.personal);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initBottomNavigationView() {
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.film_library:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.personal:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return  true;
            }
        });
    }
    private void initFragment() {
        HomeFragment homeFragment = HomeFragment.newInstance("home", "");
        fragmentList.add(homeFragment);
        FilmLibraryFragment filmLibraryFragment = FilmLibraryFragment.newInstance("film library", "");
        fragmentList.add(filmLibraryFragment);
        PersonalFragment personalFragment = PersonalFragment.newInstance("personal", "");
        fragmentList.add(personalFragment);
    }
}