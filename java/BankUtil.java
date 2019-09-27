package com.pepay.app.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pepay.app.commons.HttpsUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BankUtil {
    public static JSONObject getBankCardMessage(Map<String, String> params) {
        //需要参数cardNo=6217002950107009238&cardBinCheck=true
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json";
        String result = null;
        try {
            result = HttpsUtil.get(url, null, params);
        }catch (IOException ie){
            System.out.print("=======获取银行卡类型信息失败======");
        }
        return JSON.parseObject(result);
    }


//public static void main(String[] args) {
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("cardNo","62170029107009238");
//        params.put("cardBinCheck","true");
//        JSONObject result = BankUtil.getBankCardMessage(params);
//        System.out.println(result);
//        boolean flag = (Boolean) result.get("validated");
//        if(flag == true){
//            System.out.println("success");
//            System.out.println(result.get("bank"));
//        }else{
//            System.out.println("error");
//        }
//    }

}
