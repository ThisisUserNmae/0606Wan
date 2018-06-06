package com.bwei.lx_0606wan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bwei.lx_0606wan.HttpUtils;
import com.bwei.lx_0606wan.R;
import com.bwei.lx_0606wan.adapters.MeGridViewAdapter;
import com.bwei.lx_0606wan.bean.GsonBean;
import com.bwei.lx_0606wan.http.HttpConfig;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewFragment extends Fragment {
    private static final String TAG = "GridViewFragment------";
    private View view;
    private int page = 1;
    private PullToRefreshGridView gridView;
    private List<GsonBean.DataBean> list = new ArrayList<>();
    private MeGridViewAdapter viewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.two_fragment, null);
        initView();
        return view;
    }

    private void initView() {
        gridView = view.findViewById(R.id.pull_to_refresh_gridview);
        gridView.setMode(PullToRefreshGridView.Mode.BOTH);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                page = 1;
                getDataPicFromNet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                page++;
                getDataPicFromNet();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewAdapter = new MeGridViewAdapter(list, getActivity());
        gridView.setAdapter(viewAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getDataPicFromNet();
        }
    }

    private void getDataPicFromNet() {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.get(HttpConfig.FS02_TOP + page);
        httpUtils.setHttpListener(new HttpUtils.HttpListener() {
            @Override
            public void getSuccess(String json) {
                Log.d(TAG, "getSuccess: " + json);
                Gson gson = new Gson();
                GsonBean gsonBean = gson.fromJson(json, GsonBean.class);
                List<GsonBean.DataBean> data = gsonBean.getData();
                if (page == 1) {
                    list.clear();
                }
                list.addAll(data);
                viewAdapter.notifyDataSetChanged();
                gridView.onRefreshComplete();

            }
        });
    }
}
