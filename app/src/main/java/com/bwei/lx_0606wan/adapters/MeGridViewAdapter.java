package com.bwei.lx_0606wan.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bwei.lx_0606wan.MyApp;
import com.bwei.lx_0606wan.R;
import com.bwei.lx_0606wan.bean.GsonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MeGridViewAdapter extends BaseAdapter {
    private List<GsonBean.DataBean> list;
    private Context context;

    public MeGridViewAdapter(List<GsonBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder videoView;
        if (convertView==null){
            videoView=new ViewHolder();
            convertView=View.inflate(context, R.layout.gridview_itme,null);
            videoView.pic=convertView.findViewById(R.id.iv_pic);
            convertView.setTag(videoView);
        }else {
            videoView= (ViewHolder) convertView.getTag();
        }
        String url = list.get(position).getUrl();
        ImageLoader.getInstance().displayImage(url,videoView.pic, MyApp.getOptions());
        return convertView;
    }
    class ViewHolder{
        ImageView pic;
    }
}
