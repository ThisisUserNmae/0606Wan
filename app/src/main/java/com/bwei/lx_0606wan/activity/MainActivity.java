package com.bwei.lx_0606wan.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bwei.lx_0606wan.R;
import com.bwei.lx_0606wan.adapters.MyFragmentAdapter;
import com.bwei.lx_0606wan.fragment.GridViewFragment;
import com.bwei.lx_0606wan.fragment.ViewPager_ListViewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> list;

    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();

    }

    private void initDatas() {

        list = new ArrayList<>();

        list.add(new ViewPager_ListViewFragment());
        list.add(new GridViewFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.btn1:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.btn2:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });

    }

    private void initViews() {

        viewPager = findViewById(R.id.viewpager);

        group = findViewById(R.id.group);

    }
}
