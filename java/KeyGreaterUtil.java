package com.pepay.app.commons.utils;

import com.pepay.app.commons.smsUtils.DateUtil;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 22位序列生成：自定义标识位(1位)+时间位(13位)+UUID序列(8位)
 */
public class KeyGreaterUtil {
    public static String greater(char flag){
        Long time=System.currentTimeMillis();
        String result=flag+String.valueOf(time)+getUuid();
        return result;
    }
    public static String[] chars = new String[] { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    public static String getUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 36]);
        }
        return shortBuffer.toString();
    }

    // 19位收款账户id生成: 时间位(13位)+Random随机数(6位)
    public static Long greaterPaId(){
        return Long.parseLong(System.currentTimeMillis() + "" + (new Random().nextInt(899990) + 100000));
    }

    // 8位用户id生成
    public static String generMemberId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }
}
