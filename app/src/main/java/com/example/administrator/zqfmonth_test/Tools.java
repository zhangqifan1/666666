package com.example.administrator.zqfmonth_test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 张祺钒
 * on2017/7/28.
 */

public class Tools {
    //做一个把流转换为字符串的方法
    public static String getTextStringFromStream(InputStream inputStream){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int len=0;
        byte[] b=new byte[1024];
        try {
            while((len=inputStream.read(b))!=-1){
                baos.write(b,0,len);
            }
            baos.close();
            inputStream.close();
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
