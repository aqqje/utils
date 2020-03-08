package com.pepay.app.commons.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

public class AutoIDQrcode {


    private final static String ALIYPAYROOT = "https://render.alipay.com/p/s/i?scheme=";
    private final static String ALIPAYSUBROOT= "alipays://platformapi/startapp?saId=10000007&qrcode=";
    private final static String ALIPAYSUFFIX = "?_s=web-other";

    /**
     * 自动识别支付宝收款码，并返回连接
     * @param url
     * @return
     */
    public static String autoIDAlipayQrcode(String url) {
        String alipayPath = "";
        // 获取文件后缀
        String tempFilePath = "";
        final File excelFile;
        try {
            // 通过 url 获取图片
            tempFilePath = HttpURLConnectionUtil.downloadPicture(url);
            excelFile = new File(tempFilePath);
            String qrcodeStr = QRCodeUtil.decode(excelFile).toUpperCase();
            System.out.println(qrcodeStr);
            // 短网址处理
            qrcodeStr = BaiduDwz.createShortUrl(handlerStr(qrcodeStr+ALIPAYSUFFIX));
            // step one URL encode
            alipayPath = AURLEncoder.encode(handlerStr(qrcodeStr));
            // step two URL encode
            alipayPath =  AURLEncoder.encode(handlerStr(ALIPAYSUBROOT + alipayPath));
            // step three
            alipayPath = ALIYPAYROOT + alipayPath;
            return alipayPath;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HttpURLConnectionUtil.deleteFile(tempFilePath);
        }
        return null;
    }

    private static String handlerStr(String str){
        return str.replace("\n", "").replace("\t", "").replace("\r", "");
    }
}
