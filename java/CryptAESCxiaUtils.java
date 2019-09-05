package com.pepay.app.commons;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class CryptAESCxiaUtils {

    /**
     * AES的加密函数
     * @param str 传入需要加密的字符
     * @param key 传入一个16位长度的密钥。否则报错
     * @return 执行成功返回加密结果，否则报错
     * @throws Exception 抛出一个加密异常
     */
    public static String aesEncrypt(String str, String key){
        if (str == null || key == null) return null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            String encode = new BASE64Encoder().encode(bytes);
            return encode.replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * AES的解密函数
     * @param str 传入需要解密的字符
     * @param key 传入一个16位长度的密钥。否则报错
     * @return 执行成功返回加密结果，否则报错
     * @throws Exception 抛出一个解密异常
     */
    public static String aesDecrypt(String str, String key){
        if (str == null || key == null) return null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    private static Key generateKey(String key)throws Exception{
        try{
            KeyGenerator generator = KeyGenerator.getInstance( "AES" );
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(key.getBytes());
            generator.init(128,secureRandom);
            return generator.generateKey();

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
