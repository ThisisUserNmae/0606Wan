package com.bwei.lx_0606wan.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdPagerAdapter extends PagerAdapter {

    private List<Integer> list;

    private Context context;

    public MyAdPagerAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView img = new ImageView(context);

        img.setImageResource(list.get(position%list.size()));

        container.addView(img);

        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);


    }
}
