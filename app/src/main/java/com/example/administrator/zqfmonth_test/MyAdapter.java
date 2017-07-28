package com.example.administrator.zqfmonth_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 适配器
 * Created by 张祺钒
 * on2017/7/28.
 */

public class MyAdapter extends BaseAdapter {
    private Bean bean;
    private Context context;
    final int Type0=0;
    final int Type1=1;
    public MyAdapter(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int type = bean.list.get(position).type;
        if(type==4){
            return Type0;
        }else{
            return Type1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Bean.ListBean listBean = bean.list.get(i);
        int type = getItemViewType(i);
        ViewHolder holder=null;
        if(view==null){
            if(type ==Type0){
                holder=new ViewHolder();
                view=View.inflate(context,R.layout.item1,null);
                holder.imageView1= view.findViewById(R.id.image1);
                holder.imageView2= view.findViewById(R.id.image2);
                holder.imageView3= view.findViewById(R.id.image3);
                holder.imageView4= view.findViewById(R.id.image4);
                holder.textview=view.findViewById(R.id.tv1);
                view.setTag(holder);
            }
            if(type ==Type1){
                holder=new ViewHolder();
                view=View.inflate(context,R.layout.item2,null);
                holder.imageView1= view.findViewById(R.id.image11);
                holder.imageView2= view.findViewById(R.id.image22);
                holder.textview=view.findViewById(R.id.tv2);
                view.setTag(holder);
            }
        }else{
            //判断
            if(type ==Type0){
                holder= (ViewHolder) view.getTag();
                holder.textview.setText(listBean.title);
                String[] split = listBean.pic.split("\\|");
                ImageLoaderUtils.setImageView(split[0],context,holder.imageView1);
                ImageLoaderUtils.setImageView(split[1],context,holder.imageView2);
                ImageLoaderUtils.setImageView(split[2],context,holder.imageView3);
                ImageLoaderUtils.setImageView(split[3],context,holder.imageView4);
            }
            if(type ==Type1){
                holder= (ViewHolder) view.getTag();
                holder.textview.setText(listBean.title);
                String[] split = listBean.pic.split("\\|");
                ImageLoaderUtils.setImageView(split[0],context,holder.imageView1);
                ImageLoaderUtils.setImageView(split[1],context,holder.imageView2);
            }
        }
        return view;
    }
    static class ViewHolder{
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        TextView textview;
    }
//    static class ViewHolder2{
//        ImageView imageView11;
//        ImageView imageView22;
//        TextView textview;
//    }

}
