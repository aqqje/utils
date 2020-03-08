package com.pepay.api.alipay.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 *
 * @Date:2019/12/03 15:17
 * @Description: TODO 描述
 */
public class Base64Util {



    /**
     * BASE64加密
     *
     * @Date 2019/12/3  15:20
     * @Description TODO
     * @param
     * @return
     **/
    public static String encode(String str){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode( str.getBytes() );
    }

    /**
     * BASE64解密
     *
     * @Date 2019/12/3 15:20
     * @Description TODO
     * @param
     * @return
     **/
    public static String decoder(String encode){
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decode = decoder.decodeBuffer( encode );
            return new String( decode );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
