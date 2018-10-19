package com.example.compus;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
    private List<View> mViews;//三个布局的集合
    /*
    通过构造器获得数据
     */
    public MyPagerAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        return mViews.size();//显示的布局的数量，我们这里为三个。
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //增加View
        container.addView(mViews.get(position));
        return mViews.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //删除View
        container.removeView(mViews.get(position));
    }
}