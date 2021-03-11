package com.servi.cloud.consumer.encode;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Encoder;

import java.io.*;

public class encode {

    public static void main(String[] args) {
//        System.out.println(ImageToBase64("C:\\Users\\Administrator\\Desktop\\20b9ea9a8122e82e451bd3eaee2605d.jpg"));
//        String a = ImageToBase64("C:\\Users\\Administrator\\Desktop\\20b9ea9a8122e82e451bd3eaee2605d.jpg");
//        Base64ToImage(a, "C:\\Users\\Administrator\\Desktop\\a.jpg");
        try {
            System.out.println(new BASE64Encoder().encode("NCCsscgxyx".getBytes("utf-8")));
            System.out.println(new BASE64Encoder().encode("NCCssctcyx".getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String StringToBase64(String imgPath) {

        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    private static String ImageToBase64(String imgPath) {

        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static boolean Base64ToImage(String imgStr, String imgFilePath) {

        try {
            // Base64解码
            byte[] b = Base64Utils.decodeFromString(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
