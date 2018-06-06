package com.bwei.lx_0606wan.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.lx_0606wan.MyApp;
import com.bwei.lx_0606wan.R;
import com.bwei.lx_0606wan.beans.ProductBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyProductBaseAdapter extends BaseAdapter {

    private List<ProductBean.DataBean> list;

    private Context context;

    public MyProductBaseAdapter(List<ProductBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){

            viewHolder = new ViewHolder();

            convertView =View.inflate(context, R.layout.one_fragment_item_layout,null);
            viewHolder.img_item = convertView.findViewById(R.id.img_item);
            viewHolder.subhead_item = convertView.findViewById(R.id.subhead_item);
            viewHolder.price_item = convertView.findViewById(R.id.price_item);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        String images = list.get(position).getImages();

        ImageLoader.getInstance().displayImage(images,viewHolder.img_item, MyApp.getOptions());

        viewHolder.subhead_item.setText(list.get(position).getSubhead());
        viewHolder.price_item.setText(list.get(position).getPrice()+"");
        return convertView;
    }

    class ViewHolder{

        ImageView img_item;
        TextView subhead_item,price_item;

    }
}
