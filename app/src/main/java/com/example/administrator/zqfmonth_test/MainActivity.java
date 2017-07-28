package com.example.administrator.zqfmonth_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.administrator.zqfmonth_test.view.XListView;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String path = "http://qhb.2dyt.com/Bwei/news?page=1&type=5&postkey=ff1d1AK";
    /**
     * Hello World!
     */
    private XListView mLv;
    private MyAdapter myAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myAdapter.notifyDataSetChanged();
            close();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //关闭网络，接受网络切换广播，弹出提示框
        boolean netState = getNetState();
        if (netState == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("是否设置网络");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                }
            });
            builder.create().show();
        } else {
            Toast.makeText(MainActivity.this, "有网！！", Toast.LENGTH_SHORT).show();
        }

        initView();
        new MyClass().execute();

    }

    private void initView() {
        mLv = (XListView) findViewById(R.id.lv);

    }

    //1)使用HttpURLConnection发起请求获取数据（AsyncTask ＋HttpURLConnection ）post请求 (5分)
    class MyClass extends AsyncTask<Void, Void, Bean> implements XListView.IXListViewListener {

        private Bean bean;
        private int a = 0;
        private int b = 0;

        @Override
        protected Bean doInBackground(Void... voids) {
            try {
                //使用Post请求数据
                HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(6000);
                connection.setConnectTimeout(6000);
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    //调用工具类
                    String fromStream = Tools.getTextStringFromStream(inputStream);
                    Gson gson = new Gson();
                    bean = gson.fromJson(fromStream, Bean.class);
                }
                return bean;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bean bean) {
            super.onPostExecute(bean);
            //填充布局
            View view = View.inflate(MainActivity.this, R.layout.myheadview, null);
            //Banner轮播
            Banner banner = view.findViewById(R.id.banner);
            List<String> listViewPager = bean.listViewPager;
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(listViewPager);
            banner.setDelayTime(1000);
            banner.start();
            //创建适配器
            myAdapter = new MyAdapter(bean, MainActivity.this);
            mLv.setAdapter(myAdapter);
            mLv.addHeaderView(view);
            mLv.setPullLoadEnable(true);
            mLv.setXListViewListener(this);
            //6)listview 中，点击一张图片的item，阻塞主线程，产生anr，如图2（10分）
            mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //做耗时操作 产生ANR
//                    try {
//                        Thread.sleep(6000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //子线程中更改主UI产生ANR
                    new Thread() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"产生ANR",Toast.LENGTH_SHORT).show();
                        }
                    }.start();
                }
            });

        }

        @Override
        public void onRefresh() {
//7)listview 上拉刷新数据，加载到当前集合中显示（10分）
            b++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bean.list.add(0,bean.list.get(a));
                    handler.sendEmptyMessage(0);
                }
            }, 2000);
        }

        @Override
        public void onLoadMore() {
            //7)listview 上拉刷新数据，加载到当前集合中显示（10分）
            a++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bean.list.add(bean.list.get(a));
                    handler.sendEmptyMessage(0);
                }
            }, 2000);
        }

    }

    //判断网络状态的方法
    public boolean getNetState() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
        return false;
    }
    public void close(){
        mLv.stopLoadMore();
        mLv.stopRefresh();
    }
}
