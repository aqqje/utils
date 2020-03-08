package com.pepay.api.alipay.util;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class HttpRequestUtil {

    public static String getSign(Map<String,Object> map, String key,Boolean b){
        StringBuilder sb = new StringBuilder();
        String result =null;
        try{
            ArrayList<String> list = new ArrayList<String>();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                if(entry.getValue()!=""){
                    list.add(entry.getKey() + "=" + entry.getValue() + "&");
                }
            }
            int size = list.size();
            String [] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            for(int i = 0; i < size; i ++) {
                sb.append(arrayToSort[i]);
            }
            String substring = sb.substring(0, sb.length() - 1);

            substring+=key;
            if(b){
                result=MD5(substring);
            }else{
                result=substring;
            }
        }catch (Exception e){
            return null;
        }
        return result;
    }


    /**
     * 生成 MD5
     * @Author LiuYong
     * @Date 2019/12/11 17:08
     * @Description TODO 生成 MD5
     * @param  data 待处理数据
     * @return 加密结果
     **/
    public static String MD5(String data) {
        StringBuilder sb = new StringBuilder();
        try{
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("GBK"));
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
        }catch (Exception e){
            return null;
        }
        return sb.toString().toUpperCase();
    }

    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new SecureRandom());
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSocketFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if (outputStr != null) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("GBK"));
                os.close();
            }
            //读取服务器端的返回内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader bufferedReader = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
                buffer.append(line);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
