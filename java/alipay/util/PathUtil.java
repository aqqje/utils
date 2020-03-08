package com.pepay.api.alipay.util;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Author: zz
 * @Date:2019/12/16 11:29
 * @Description: TODO 描述
 */
public class PathUtil {

    public static void main(String[] args) throws Exception{
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(PathUtil.class.getClassLoader().getResource(".").getPath());
//        String readfile = readfile("D:\\Software\\IdeaProjects\\slf-app-api\\slf-pay\\slf-pay-service\\src\\main\\resources\\static\\sq\\success.html");
//        System.out.println(readfile);
    }

    public static String getPath(String url) throws Exception {
        //路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/"+url+"/");

        if (!upload.exists()) {
            upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        return upload.getAbsolutePath();
    }

    public static String readfile(String filePath){
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for(int n ; (n = input.read(bytes))!=-1 ; ){
                buffer.append(new String(bytes,0,n,"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
