package com.pepay.app.commons.smsUtils;

import com.pepay.app.controller.ApplicationContextRegister;
import com.pepay.app.service.RedisService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SMSUtil {

    public static String sendCode(String mobile) throws UnsupportedEncodingException {
        String code = RandomStringUtils.randomNumeric(6);
        String content = "【xxxx】您的验证码是" + code + "，有效时间10分钟，请勿告诉他人！";
        String url = "http://www.ztsms.cn/sendNSms.do";
        String username = "jucai";//内容
        String password = "ZGeca6Mb";//密码
        String productid = "676767";//产品id
        String xh = "";//没有
        String tkey = DateUtil.YMdhms_noSpli.format(DateUtil.getDate());
        content = URLEncoder.encode(content, "utf-8");
        String param = "url=" + url + "&username=" + username + "&password=" + MD5Util.getMD5(MD5Util.getMD5(password) + tkey) + "&tkey=" + tkey + "&mobile=" + mobile + "&content=" + content + "&productid=" + productid + "&xh" + xh;
        String ret = HttpRequest.sendPost(url, param);
        RedisService redisService = ApplicationContextRegister.getApplicationContext().getBean(RedisService.class);
        redisService.setStr(mobile, code, 10*60L);      //设置验证码有效期为10分钟
        System.out.println("=======code=====>" + code);
        System.out.println("ret:" + ret);
        System.out.println(param);
        if (ret.indexOf(',') != -1) {
            return ret.substring(0, 1);
        }
        return ret;
    }
    /**
     * 判断 phone 是不是真正的手机号
     * @param param
     * @return
     */
    public static boolean isMobile(String param) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";// 验证手机号
        if(StringUtils.isNotBlank(param)){
            p = Pattern.compile(s2);
            m = p.matcher(param);
            b = m.matches();
        }
        return b;
    }

    public static void main(String[] args) {
        try {
            String result = sendCode("18175129692");
            System.out.println("===============>" + result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
