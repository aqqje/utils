package com.pepay.app.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class CryptAESUtils {

    private static final String AESTYPE ="AES/ECB/PKCS5Padding";

    public static String AES_Encrypt(String keyStr, String plainText) {
        byte[] encrypt = null;
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    public static String AES_Decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null;
        System.out.println("========AES_Decrypt=========encryptData"+encryptData);
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(decrypt).trim();
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

    public static void main(String[] args) {

       /* String keyStr = "UITN25LMUQC436IM";

        String plainText = "this is a string will be AES_Encrypt  this is a string will be AES_Encrypt   this is a string will be AES_Encrypt  this is a string will be AES_Encrypt";

        String encText = AES_Encrypt(keyStr, plainText);*/

        Map<String, Object> tempMap = new HashMap<>();

        tempMap.put("ptId", "155920812658213968");
        tempMap.put("sjdd", "4564565344223423874");
        tempMap.put("zffs", 2);
        tempMap.put("money", 200);

        String key = "yO/WufWa1tMY+ydF";
        String decString = AES_Encrypt(key, JSON.toJSONString(tempMap));

        //System.out.println(encText);
        System.out.println(decString.replace("+", "%2B"));
    }

}