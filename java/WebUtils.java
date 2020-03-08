package com.pepay.app.commons.utils;

/**
 * web 请求工具类
 * */
public class WebUtils {

    /**
     * 判断请求设备类型
     * @param requestHeader
     * @return
     */
    public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
    }
}
