package com.example.player.main.navigation.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //重写此方法使触摸viewpager时什么都不做，让其不能滑动，实现viewpager不能滑动
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    //事件拦截,false不拦截子控件的事件，让内层的viewpager可以滑动，切不带动外层的viewpager
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
