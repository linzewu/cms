package com.lzw.work.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 该类提供了MD5加密
 * @author linzewu
 *
 */
public class MD5Encrypt {
	
	public static String encodingMD5(String pass) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		//确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符
        String newpass=base64en.encode(md5.digest(pass.getBytes("utf-8")));
		return newpass;
	}

}
