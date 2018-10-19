package com.example.compus;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
    private List<View> mViews;//�������ֵļ���
    /*
    ͨ���������������
     */
    public MyPagerAdapter(List<View> mViews) {
        this.mViews = mViews;
    }

    @Override
    public int getCount() {
        return mViews.size();//��ʾ�Ĳ��ֵ���������������Ϊ������
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //����View
        container.addView(mViews.get(position));
        return mViews.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //ɾ��View
        container.removeView(mViews.get(position));
    }
}