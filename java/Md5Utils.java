package com.pepay.app.commons.utils;

import java.security.MessageDigest;

public class Md5Utils {

	/**
	 * 生成有效签名
	 * 
	 * @param params
	 * @param secret
	 * @return
	 */
	public static String signature(String orgin) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 * 二行制转字符
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toLowerCase();
	}
}
