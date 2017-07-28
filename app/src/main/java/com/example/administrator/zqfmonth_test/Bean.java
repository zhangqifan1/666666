package com.example.administrator.zqfmonth_test;

import java.util.List;

/**
 * Created by 张祺钒
 * on2017/7/28.
 */

public class Bean {

    /**
     * ret_code : 200
     * ret_msg : ok
     * listViewPager : ["https://img10.360buyimg.com/da/jfs/t4747/277/1368712300/170619/35098d7f/58f038e0N9b3a0ca5.jpg","https://img14.360buyimg.com/da/jfs/t4915/21/1427207714/81116/b005bb06/58f08963Ndb295b3c.jpg","https://img13.360buyimg.com/da/jfs/t4651/104/2867456043/68336/99da4c16/58f41eaeN5b614a63.jpg"]
     * list : [{"date":"2222","id":2,"pic":"http://p3.pstatp.com/list/190x124/1c19000062675e53b27e|http://p3.pstatp.com/list/190x124/1aa4000a2b8a788b321f|http://p3.pstatp.com/list/190x124/2a3f0000d9a4a6ac884d|http://p1.pstatp.com/list/190x124/2a3c000520bf9b36fdf0","title":"北京的你再忙也要加一下这个投资微信！不然后悔！","type":4},{"date":"5555","id":5,"pic":"http://p3.pstatp.com/list/190x124/26ed0005636b714a9f61|http://p1.pstatp.com/list/190x124/26ee000375da57f8b8b1|http://p3.pstatp.com/list/190x124/26ef0000545531df0dfa|http://p3.pstatp.com/list/190x124/26ef00005463b7a8f958","title":" \n袁世凯临终前，在传位名单上写下三个人的名字，众人看了敬佩不已","type":4},{"date":"7777","id":7,"pic":"http://p3.pstatp.com/list/190x124/213300016c777190f9ed|http://p3.pstatp.com/list/190x124/22ca00011911b0a8061c","title":" \n曾经的歌星，落魄到参加选秀，结果唱一半却被韩红喊停","type":2},{"date":"333","id":12,"pic":"http://p3.pstatp.com/list/190x124/26ed0005636b714a9f61|http://p1.pstatp.com/list/190x124/26ee000375da57f8b8b1|http://p3.pstatp.com/list/190x124/26ef0000545531df0dfa|http://p3.pstatp.com/list/190x124/26ef00005463b7a8f958","title":"童星长大以后对比照，有人大红大紫，有人却流落街头","type":4},{"date":"34323","id":16,"pic":"http://p3.pstatp.com/list/190x124/28820012a2ed870afe9c|http://p9.pstatp.com/list/190x124/28820012a37f01432419","title":"紫谷伊甸园\u201c童心童行\u201d活动开幕啦","type":2},{"date":"","id":17,"pic":"http://s3m.mediav.com/galileo/88d8e16558fa0fd6402fe8daa32f5d44.jpg|http://p3.pstatp.com/list/190x124/289c001c528de064679d","title":"永创佳乐 专业蔬菜大棚/花卉大棚","type":2}]
     */

    public int ret_code;
    public String ret_msg;
    public List<String> listViewPager;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * date : 2222
         * id : 2
         * pic : http://p3.pstatp.com/list/190x124/1c19000062675e53b27e|http://p3.pstatp.com/list/190x124/1aa4000a2b8a788b321f|http://p3.pstatp.com/list/190x124/2a3f0000d9a4a6ac884d|http://p1.pstatp.com/list/190x124/2a3c000520bf9b36fdf0
         * title : 北京的你再忙也要加一下这个投资微信！不然后悔！
         * type : 4
         */

        public String date;
        public int id;
        public String pic;
        public String title;
        public int type;
    }
}
