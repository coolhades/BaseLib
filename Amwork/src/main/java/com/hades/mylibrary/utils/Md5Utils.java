package com.hades.mylibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiuzheyange on 2016/8/11.
 */
public class Md5Utils {

    //默认方法
    public static String md5(String string) {
        return enCodeMd5(string).substring(6,22);
    }

    /**
    * 创建时间 16/10/14
    * auther Hades
    * 描述   range 取字符串位置
    **/
    public static String md5(String string, int start, int end) {
        return enCodeMd5(string).substring(start,end);
    }

    public static String enCodeMd5(String ss){
        byte[] hash;
        String str;
        try {
            hash = MessageDigest.getInstance("MD5").digest(ss.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        str=hex.toString();
        return str;
    }
    
}
