package com.csy.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* @title: base64加密解密工具类
* @Description: 实际使用工具类的时候需 手动 判断返回的string是否为空，为空即停止，返回加密失败。
* @author: jiangman
* @date: 2018/4/13 19:22
*/
public class Base64Util {

    //统一编码UTF-8
    private static String textCode = "UTF-8";

    /**
    * @Description: base64编码
    * @author: jiangman
    * @date: 2018/5/3 15:46
    */
	public static String encode(String msg){
        Base64.Encoder encoder = Base64.getEncoder();
		byte[] textByte = new byte[0];
		try {
			textByte = msg.getBytes(textCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = encoder.encodeToString(textByte);
		return result;
	}
	
	/**
	* @Description: base64解码
	* @author: jiangman
	* @date: 2018/5/3 15:48
	*/
	public static String decode(String msg){
	    Base64.Decoder decoder = Base64.getDecoder();
		String result = null;
		try {
			result = new String(decoder.decode(msg),textCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encode("Aa123456"));
	}
}
