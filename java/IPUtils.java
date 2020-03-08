package com.pepay.app.commons;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	public static String trimSpaces(String IP){//去掉IP字符串前后所有的空格
		while(IP.startsWith(" ")){
			IP= IP.substring(1,IP.length()).trim();
		}
		while(IP.endsWith(" ")){
			IP= IP.substring(0,IP.length()-1).trim();
		}
		return IP;
	}

	/**
	 * 判断是否为 IP
	 * @return
	 */
	public static boolean isIp(String IP){
		if(IP.equals("localhost")){
			return true;
		}
		boolean b = false;
		IP = trimSpaces(IP);
		if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){
			String s[] = IP.split("\\.");
			if(Integer.parseInt(s[0])<255)
				if(Integer.parseInt(s[1])<255)
					if(Integer.parseInt(s[2])<255)
						if(Integer.parseInt(s[3])<255)
							b = true;
		}
		return b;
	}

}
