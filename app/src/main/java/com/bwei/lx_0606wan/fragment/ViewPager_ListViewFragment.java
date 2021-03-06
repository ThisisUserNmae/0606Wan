package com.bwei.lx_0606wan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwei.lx_0606wan.HttpUtils;
import com.bwei.lx_0606wan.R;
import com.bwei.lx_0606wan.adapters.MyAdPagerAdapter;
import com.bwei.lx_0606wan.adapters.MyProductBaseAdapter;
import com.bwei.lx_0606wan.beans.ProductBean;
import com.bwei.lx_0606wan.http.HttpConfig;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewPager_ListViewFragment extends Fragment{

    private static final String TAG = "ViewPager_ListViewFragm";

    private int[] img = {R.drawable.ph2,R.drawable.ph3,R.drawable.ph4,R.drawable.ph6,R.drawable.ph7};

    List<Integer> imgList= new ArrayList<>();

    private View view;
    private ViewPager viewPager;

    private MyHandler h = new MyHandler();
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.one_fragment,null);

        //网络请求 ViewPager + list 实现无限轮播图 +ListView 数据展示

        viewPager = view.findViewById(R.id.viewpager);
        lv = view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < img.length; i++) {
            //添加到集合
            imgList.add(img[i]);
        }


        MyAdPagerAdapter adPagerAdapter = new MyAdPagerAdapter(imgList,getContext());

        viewPager.setAdapter(adPagerAdapter);


        HttpUtils httpUtils = HttpUtils.getHttpUtils();

        httpUtils.get(HttpConfig.PRODUCT_URL);

        httpUtils.setHttpListener(new HttpUtils.HttpListener() {
            @Override
            public void getSuccess(String json) {

                Gson g = new Gson();

                ProductBean productBean = g.fromJson(json, ProductBean.class);

                List<ProductBean.DataBean> data = productBean.getData();

                MyProductBaseAdapter adapter = new MyProductBaseAdapter(data,getContext());

                lv.setAdapter(adapter);

            }
        });

        h.sendEmptyMessageDelayed(0,2000);

    }


    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int num =  viewPager.getCurrentItem()+1;

            viewPager.setCurrentItem(num);

            h.sendEmptyMessageDelayed(0,2000);
        }
    }
}
