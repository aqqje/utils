package com.pepay.app.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


@Slf4j
public class HttpURLConnectionUtil {


//    @Value("${web.images-path}")
  private static String basePath = "/data/www/static";

    //链接url下载临时图片
    public static String downloadPicture(String urlList) {
        String tempPath = basePath + "/autoidqrcode/temp/";
        File fileDir = new File(tempPath);
        // 不存在则创建临时目录
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        tempPath += UUID.randomUUID() + ".PNG";
        URL url = null;
        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            url = new URL(urlList);
            dataInputStream = new DataInputStream(url.openStream());
            File file = new File(tempPath);
            fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            return tempPath;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 用于删除临时文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
        // 图片不存在则直接返回
        if(null == filePath || "".equals(filePath)){
            return;
        }
        File file = new File(filePath);
        if(file.delete()){
            log.info("删除自动识别临时图片成功:"+ filePath);
            System.out.println("删除自动识别临时图片成功:"+ filePath);
        }else{
            log.info("删除自动识别临时图片失败:"+ filePath);
            System.out.println("删除自动识别临时图片失败:"+ filePath);
        }
    }
}
