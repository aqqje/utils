package com.pepay.api.alipay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.pepay.api.alipay.domain.AlipayAccounts;
import com.pepay.api.alipay.dto.AlipayBarCodeDto;
import com.pepay.api.alipay.dto.RoutingDto;
import com.pepay.app.commons.utils.StringUtils;
import com.pepay.app.controller.ApplicationContextRegister;
import com.pepay.app.entity.SysConfig;
import com.pepay.app.mapper.SysConfigMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 支付API
 *
 *
 * @Date:2019/12/03 14:22
 * @Description: TODO 支付类接口
 */
@Component
public class AlipayToolSon {
    private static Logger log = Logger.getLogger(AlipayToolSon.class.getName());
    public static void main(String[] args)throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", "266241315669278720");
        jsonObject.put("refund_amount", "0.01");
        JSONObject gateway = AlipayToolSon.refund(jsonObject);
        System.out.println(gateway);
    }


    public static JSONObject refund(JSONObject jsonObject) {
        AlipayTradeRefundResponse response = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.putOtherTextParam("app_auth_token", "202002BBf755a1dc25e6428c8665598ae5cf0X02");
            request.setBizContent(jsonObject.toJSONString());
            response = alipayClient.certificateExecute(request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }

    public static JSONObject alipay(AlipayBarCodeDto alipayBarCodeDto) {
        AlipayTradePayResponse alipayTradePayResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradePayRequest request = new AlipayTradePayRequest();
            log.info("发起支付参数：" + alipayBarCodeDto);
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());
            JSONObject object = new JSONObject();
            object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
            object.put("scene", AlipayConstants.SCRNC_BAR);
            object.put("auth_code", alipayBarCodeDto.getBarCode());
            object.put("subject", alipayBarCodeDto.getBName());
            object.put("buyer_id", alipayBarCodeDto.getBuyerId());
            object.put("total_amount", alipayBarCodeDto.getAmount());
            request.setBizContent(object.toJSONString());
            alipayTradePayResponse = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + alipayTradePayResponse.getBody());
            return JSONObject.parseObject(alipayTradePayResponse.getBody());
        }
    }

    public static String appPay(AlipayBarCodeDto alipayBarCodeDto) {
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());
            JSONObject object = new JSONObject();
            object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
            object.put("subject", "APP");
            object.put("product_code", "QUICK_MSECURITY_PAY");
            object.put("total_amount", alipayBarCodeDto.getAmount());
            request.setBizContent(object.toJSONString());
            System.out.println(request.getTextParams());
            alipayTradeAppPayResponse = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("APP支付response---" + alipayTradeAppPayResponse.getBody());
            return alipayTradeAppPayResponse.getBody();
        }
    }





    /**alipay.trade.precreate( 统一收单线下交易预创建)二维码有效时间：2小时（计算时间是生成二维码链接开始计算）*/
    public static JSONObject precreate(AlipayBarCodeDto alipayBarCodeDto) {
        AlipayTradePrecreateResponse response = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());
            JSONObject object = new JSONObject();
            object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
            object.put("subject", "零售商品-2");
            object.put("total_amount", alipayBarCodeDto.getAmount());
            System.out.println(object.toJSONString());
            request.setBizContent(object.toJSONString());
            response = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }

    public static JSONObject bind(List<RoutingDto> routingDtos, String appAuthToken, String outRequestNo) {
        AlipayTradeRoyaltyRelationBindResponse tradeRoyaltyRelationBindResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", appAuthToken);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(routingDtos));
            JSONObject object = new JSONObject();
            object.put("receiver_list", array);
            object.put("out_request_no", outRequestNo);
            request.setBizContent(object.toJSONString());
            tradeRoyaltyRelationBindResponse = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeRoyaltyRelationBindResponse.getBody());
            return JSONObject.parseObject(tradeRoyaltyRelationBindResponse.getBody());
        }
    }

    public static JSONObject bind(RoutingDto routingDto, String appAuthToken, String outRequestNo) {
        List<RoutingDto> routingDtos = new ArrayList<>();
        routingDtos.add(routingDto);
        return AlipayToolSon.bind(routingDtos, appAuthToken, outRequestNo);
    }

    public static JSONObject unBind(RoutingDto routingDto, String appAuthToken, String outRequestNo) {
        List<RoutingDto> routingDtos = new ArrayList<>();
        routingDtos.add(routingDto);
        return AlipayToolSon.unBind(routingDtos, appAuthToken, outRequestNo);
    }

    public static JSONObject unBind(List<RoutingDto> routingDtos, String appAuthToken, String outRequestNo) {
        AlipayTradeRoyaltyRelationUnbindResponse tradeRoyaltyRelationUnbindResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", appAuthToken);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(routingDtos));
            JSONObject object = new JSONObject();
            object.put("receiver_list", array);
            object.put("out_request_no", outRequestNo);
            request.setBizContent(object.toJSONString());
            tradeRoyaltyRelationUnbindResponse = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeRoyaltyRelationUnbindResponse.getBody());
            return JSONObject.parseObject(tradeRoyaltyRelationUnbindResponse.getBody());
        }
    }

    public static JSONObject settle(JSONObject jsonObject, String appAuthToken) {
        AlipayTradeOrderSettleResponse tradeOrderSettleResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", appAuthToken);
            request.setBizContent(jsonObject.toJSONString());
            tradeOrderSettleResponse = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeOrderSettleResponse.getBody());
            return JSONObject.parseObject(tradeOrderSettleResponse.getBody());
        }
    }

    public static JSONObject query(String outTradeNo, String appAuthToken) {
        AlipayTradeQueryResponse response = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", appAuthToken);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("out_trade_no", outTradeNo);
            request.setBizContent(jsonObject.toJSONString());
            response = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }

    public static JSONObject Transfer(AlipayBarCodeDto alipayBarCodeDto,AlipayAccounts alipayAccounts){
        AlipayFundTransUniTransferResponse  alipayFundTransUniTransferResponse = null;
        try {
            AlipayClient  alipayClient = new DefaultAlipayClient(getCertAlipayRequest());
            AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
            log.info("转账参数：" + alipayBarCodeDto);
            request.putOtherTextParam("app_id", AlipayConstants.APP_ID);
            request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());
            JSONObject object = new JSONObject();
            object.put("out_biz_no", alipayBarCodeDto.getOrderNumber());
            object.put("product_code", AlipayConstants.TRANS_ACCOUNT_NO_PWD);
            object.put("trans_amount", alipayBarCodeDto.getAmount());
            object.put("biz_scene", AlipayConstants.DIRECT_TRANSFER);

            JSONObject payeeInfo = new JSONObject();
            payeeInfo.put("identity",alipayAccounts.getAccounts());
            payeeInfo.put("identity_type", AlipayConstants.ALIPAY_LOGON_ID);
            payeeInfo.put("name", alipayAccounts.getName());

            object.put("payee_info", payeeInfo);
            request.setBizContent(object.toJSONString());
            alipayFundTransUniTransferResponse = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + alipayFundTransUniTransferResponse.getBody());
            return JSONObject.parseObject(alipayFundTransUniTransferResponse.getBody());
        }
    }





    /**
     * 证书请求
     * @return
     */
    public static CertAlipayRequest getCertAlipayRequest(){
        log.info("证书请求--->>>com.pepay.api.alipay.util.AlipayToolSon.getCertAlipayRequest");
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(AlipayConstants.URL);
        certAlipayRequest.setAppId(AlipayConstants.APP_ID);
        certAlipayRequest.setPrivateKey(AlipayConstants.APP_PRIVATE_KEY);
        certAlipayRequest.setFormat(AlipayConstants.FORMAT);
        certAlipayRequest.setCharset(AlipayConstants.CHARSET);
        certAlipayRequest.setSignType(AlipayConstants.SIGN_TYPE);
        try{
            System.out.println(PathUtil.getPath("ZS")+"/appCertPublicKey_2021001133650936.crt");
            System.out.println(PathUtil.getPath("ZS")+"/alipayCertPublicKey_RSA2.crt");
            System.out.println(PathUtil.getPath("ZS")+"/alipayRootCert.crt");
            certAlipayRequest.setCertPath(PathUtil.getPath("ZS")+"/appCertPublicKey_2021001133650936.crt");
            certAlipayRequest.setAlipayPublicCertPath(PathUtil.getPath("ZS")+"/alipayCertPublicKey_RSA2.crt");
            certAlipayRequest.setRootCertPath(PathUtil.getPath("ZS")+"/alipayRootCert.crt");
        }catch (Exception e){
            e.printStackTrace();
        }
        return certAlipayRequest;
    }

    public static CertAlipayRequest getCertAlipayRequestQJ(){
        log.info("证书请求--->>>com.pepay.api.alipay.util.AlipayToolSon.getCertAlipayRequest");
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(AlipayConstants.URL);
        certAlipayRequest.setAppId("2021001140654785");
        certAlipayRequest.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdytgBG2yppHrurlE4rldjwun88dIdwWtW6daXuM6yJ2kPyPGedc7jMwnJQ3NNutImwrHLZTq01ST4dP8LX+LFRPXP/RQ1HVWRsPefIPj1fqeZ0autceeqgH0ylSMQyzhB7usuOGlGJzKT3e1dgiQoSCTgJAH/S9o66JQWahfLUZYU7LEc/tFepwqgh5cKGJyv8kW0NhOnPYd3fL5t/Zi3Hh7ID2sGfFhqxvwvFrb4vDzUjIsIliXcmOfo9qgmqz8fSCfK7mF/2ZzRMfbkh2pbBOv7XLvy78lUbvriiWuReZtFwROaBwGI6LDchZ4M24SSMR0ISzqO+UOx3tk9OaghAgMBAAECggEAc8Ls1WMZRRu2VITucXb2BeppX3jJfar45LqJBKZAfdML8Niq6mXjcitvcohZfx0gL++GIIITn7b/eIfc1Ktz4uXEsOIqgQ0USd5azBOUkgZpTx/To1t9akj5kLJ5F0j/7N8rCq24Dg+Tu4eFLKTdge/rncTM8k7ju3QFsKwE6NO3FDA3UevaE7Jyizw+Pg5f0JDI/goDo/hjqqT1lv8KN5j+3OuSEWBvVjSR+WhzENLpE1qCSO6d68YR6WCT5wJwJATm/QuvWNS/OPWdkbdsI2mnJ82rWT4PKcX0yEoOi9Oua0yUFWL5lve+km0EtgFXRE3ZRhT+YX0mcwmDhuN/AQKBgQDtdpGQNDzfEpv2DmDvXbJQWAjz8rdoagxq4p+YRvbPEqZTwbVh4BcZ3ij9bVrGZRPUzxt1/v/2Q9qx506vJLzJPvg511G9T9lZQffVyey+GZTToXYPrdsHhiRUFK+Ee6QSybwrO2bJSbNDAeAqvZN4FxxYmvXebiS+UE5heYN8JQKBgQCqHCUOU3xIAH8/hQm5gj92WS+i8w9makd572BXq49B+4QyVJZFe1FkFVQXN6stA2WmF6dRrOTH8tqtXMwxiMFw/GYFSJ5dSMUZJXqVm00+z+6iGQt1dHuJyxy9utwUzT093LzsnmWHQGTvt3xZpqfRf5Grs161CJMNQqPbRA69TQKBgQCvyzhF3jY2Gmu1Q73FVsL8McAnAMeAmAmrUyw9KkPq1m6l0OC7nY7cipgjzUIHX6rHv68fsnDBWkhbrdaVWIgeH+zZvSp5ENTh/DFCZuNJdFy2M3DYeKeOsWLRkCLD3FiBxDD2fFpCp7n64DeYzJGQtOM6QTXFvhqbfY4ZIUEStQKBgBP/Kvv8JGfyZwL4iKQeA4fOBCOLnwvTAoP698BWkoiXIHsqJmAUlwTE3RW9nYm1FIXp0elwAZTfn4Uxm29XPCYkBXophzRAEatbFIXxs6rmlo7HkulDwCH6lm5XQPfRCJn1EOBf3l9/2l2znBKN3Lxr2pvsgf2V/HNamhh0b6chAoGAbrbUBQFVXS552h7Cu83j/vNN7qVRzvBIWRSDxNoFlmGC4L9r1kU7OXsLFxxdp/Wn0jSBxuUMOqTQOHEiLkqPbGUjY9KxfRM4nNE1WVbvy6p11mSTeGs/yzIvpUGN18vT2paDQ99a3wiCpO+Ue2070Muv+CHZJxKl4MRhCOV0YXo=");
        certAlipayRequest.setFormat(AlipayConstants.FORMAT);
        certAlipayRequest.setCharset(AlipayConstants.CHARSET);
        certAlipayRequest.setSignType(AlipayConstants.SIGN_TYPE);
        try{
            System.out.println(PathUtil.getPath("ZS/QJ")+"/appCertPublicKey_2021001140654785.crt");
            System.out.println(PathUtil.getPath("ZS/QJ")+"/alipayCertPublicKey_RSA2.crt");
            System.out.println(PathUtil.getPath("ZS/QJ")+"/alipayRootCert.crt");
            certAlipayRequest.setCertPath(PathUtil.getPath("ZS/QJ")+"/appCertPublicKey_2021001140654785.crt");
            certAlipayRequest.setAlipayPublicCertPath(PathUtil.getPath("ZS/QJ")+"/alipayCertPublicKey_RSA2.crt");
            certAlipayRequest.setRootCertPath(PathUtil.getPath("ZS/QJ")+"/alipayRootCert.crt");
        }catch (Exception e){
            e.printStackTrace();
        }
        return certAlipayRequest;
    }

    public static CertAlipayRequest getCertAlipayRequestYD(){
        log.info("证书请求--->>>com.pepay.api.alipay.util.AlipayToolSon.getCertAlipayRequest");
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(AlipayConstants.URL);
        certAlipayRequest.setAppId("2021001132628134");
        certAlipayRequest.setPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCO95rJq3/ZX9oUhwm6mXBkQGn6HVme5s4skO2zt1Hk5sRKcAkAos11tFgN2FqUTDJuwZ0qccXYELCq9M3DnvADOeU3nbyMXUbcZJXgOQEkgBTSxqWpJ4j3ngRvUVonKLM2AdfZVQCnBPOdWnSQVdC7tf3XJkoyJNxSfuY7RWZNi5PncS0gIm0EzwVvm5TG5ESWuUgka0Np8/niY8ydHoAOkjhCctJLOMe4/RyvON5NmeKy4de+4hLbC/O9L686gPscv7XK5kD7aZCCcE/RH2Zl8IQXc2zr7EF+Ix9smUANxu+f4K530LcfPp+Kd6KetusHiNUarF0RkieEYlvd+qD9AgMBAAECggEAT9VMBhlSRxwTNytA74YHELQ3eYcyKF1R++jiE1zmyh9itP5iyIwglv2hvfl2eyEheF99V9o4VDLdtFkHHZl/BQ4rRSc7xlW/4An69z5duPgcqKZsk0yUW1FNwKt44C0GBb55vc6NkrZdIx0DwiVpbahumtmyzNVwglCCV86lFd9lwRbwLiBSwR9BslWl3KWOefFXaDIrIDVMPEvkUVZaTPdL3c2QSVNpaxy6bCGZlZxqOL6vbEHmwWAp0ve9Nb9HZKA4X2pcyglXqT0v2ZCE2pFf8CuOOUNrRzGlchryPCeZ+Dmod+a4S+mIL/Dj6ySBLYAelKqfVRZ3pTWq5EAWAQKBgQDm5LcE1/4SEhR3CzYZxg1v4StQ56/EFJlsdVhQ83IkO0++rhTbqEI7CxsIeyAeSoKwMKcG++fAoC63JE1ZsDpWtotAOYbN2Fe+/Ojfmsg7966Wh80aZFe4o0moOW34CNwnJPXw77/2S2/t8kr3Ejv7oZ8VoiVJLR7Af0fZoKwOIQKBgQCeg1LtApru9Wae8idcYDQDsXFNt2QWwERVD4fiF+aIvWUqcOek8HH0lXDFtdE+/wweL63J/2FFXRfAQTxur1HTodXnXrUuxrBR7++FUZ6c4VQgTuedHyu9v8HYUwlzHdXk8DBTItXLoz9KFOg6pPX3W9StcgcQUBT18z4LTlOfXQKBgCbxwOTyQYpuvkDuBSwHudB7HEFDMuRRzSb8C+9rgH8X+fVhi2cZVEJ9NwwRKHOzgaOSMTn+VzKoUfjWCK3uCYkCtS4aBeDGctZl9ilxBAEJX8tIEJI5BorAD/Zt6VyCsg9wLoN7DNfQFYr+6SIPU0q4EgsmFD/pelxhm47biOnBAoGAPyxpdUoPg2LMNlad90PkFwC1/lozipmv4hKhfis2VpLc6dA7kdG+KNMSMcmnjirJopIv+gxMsP9MAvQ86IP44ZZjEKX/IZhQFye/1utTgoSPKZ0o08bjgOkWkWduobFnWKmdKNT+Tf1OsPCqKQ0rdhzkIwgJ5qdz4x3BQXJDA4UCgYAiX/ab2HrRO9pZXq4uIMgIwdQFy9Vm5tQkHk8pgZv9wBHBY85T3MAS7hCeI6Wwq7wEBKzJddOwbKyXjZEDsr1vHcExtMC88xAcrJTvvV7ncaLoMyb0a5Ez3srtd7hCETqEvrv/nqrKLIH+iDGykod3Nrv/CkEOMs8/o2I1X3kxMQ==");
        certAlipayRequest.setFormat(AlipayConstants.FORMAT);
        certAlipayRequest.setCharset(AlipayConstants.CHARSET);
        certAlipayRequest.setSignType(AlipayConstants.SIGN_TYPE);
        try{
            System.out.println(PathUtil.getPath("ZS/YD")+"/appCertPublicKey_2021001132628134.crt");
            System.out.println(PathUtil.getPath("ZS/YD")+"/alipayCertPublicKey_RSA2.crt");
            System.out.println(PathUtil.getPath("ZS/YD")+"/alipayRootCert.crt");
            certAlipayRequest.setCertPath(PathUtil.getPath("ZS/YD")+"/appCertPublicKey_2021001132628134.crt");
            certAlipayRequest.setAlipayPublicCertPath(PathUtil.getPath("ZS/YD")+"/alipayCertPublicKey_RSA2.crt");
            certAlipayRequest.setRootCertPath(PathUtil.getPath("ZS/YD")+"/alipayRootCert.crt");
        }catch (Exception e){
            e.printStackTrace();
        }
        return certAlipayRequest;
    }

    public static CertAlipayRequest getCertAlipayRequestXS(){
        log.info("证书请求--->>>com.pepay.api.alipay.util.AlipayToolSon.getCertAlipayRequest");
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(AlipayConstants.URL_DEV);
        certAlipayRequest.setAppId("2016102100729854");
        certAlipayRequest.setPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCdy8F1N51JuOmtVDvXOOEajKeAiNeVbxgpJjfbfb0WrOLfXZ7Xi509V99BsTMEysF075gIBqZ2RLXQdC/5WQqwWBXMrwL5CtkSlHkFF7uJm9KuiVboOtAp6Amjo/GkS2P134zxGbDwgQwwiIETfxD48UEu/6CQutcgVMhq4WJetHoWDiA1BY1gahEHtxOuKC08aEoGx1CPH1ogf9gURp0HBbWgEqYCqBoQcjRp7dqPNVWAv6rOzsjtCCmfFzNXGHb2WGce0zPJK57zhLP52U4geTdD8cplsM1Lm/L5RbstRaVOmuxrv4PLvnr25LUTnq/B5TXCUTkkhkAF1a0IniRjAgMBAAECggEAXmGD6feExZardjfevwFPatDcUz4GLGRRgDNSz57qnYh0/oMBwgzI1OsdJy60xFX91lKEjbBkmnLIiNEl7K98fDODfnTjg1AgvENAC3LBgjJIloqhB7A92ALo2Q7zNoC9XlAC/iaOtZuKTfEnuyCSZ0Pt6A2P3WFT0QvFAGCktN2tgbniyysDMETyzUQDJVR5C/NuH0oKVytzaDn47nnwMgF4AuBv7dO5rnDx/qMZIHsR7FVeJuMMvSreD3xKyPVyl7tCzYTAnOFUYZPSBX9KHNwhr49NJgK1Aogx4mlxRM+hTFPzHrednwEZGykp2sNe+w+GupuXO0NsFS6iZaUL6QKBgQDkv3Wuv2xXA2FcudAO294vF0vrxW9RzlqjkcQQa1WbZHVDBMcZYyG0/Lse0KdZxRYsO4itaSft4TJ4Sfqfm9DuKzrEAoiUkMc2UG8j0ha2EDox0s857ZpvoIo57u288Es34vRh+L9IsnlpCkLQKnpOV/O9NgBSX15e6e2/wZcNBQKBgQCwmFhEtB5wG1IGYA17gMfnWYsXLRPhq4nIObTynCRXJ+sdR50CTK2CyNDsfyoskkTrZtOqpNefmFqB9mFgh1NYTRykF3EowslNEJCctJTPK1CNhpoNqX5f/C/LWUdz5s4l5Uw5R8TUyj4giDGcQ0dIXZBpbbDsrnNVbL1AP8noRwKBgHsVin09A07OwtTiJtHX/PXZ00BKPFwIbT0Wciljl+zFYVNufrNqY/i/BPDP1Ty4j/W9Ew48Rv/47O9M306CySlemYBe3J8USB0xqpvbr9zHXojwphDFiRQHjlkd905y/gZUWKte7Y5wemPK4aihMq51JPHq3MF2D5GCzisgP+cBAoGAcUnaXoDagfrU3SgjnhZYp+8GzuBpP72nD18N2RIYMh12tHrrm70LFrqzZPSTXrKbKhT2NyO2/Dhl37fqcl1+N0iI1i//tTF4qEq3KwCh8mGMNK6kB52zZ+pF0wM8Vns7xo3aH2kOOaBgyMf2Y5O5mBAGA8yN0th7PtMaeawyYCMCgYAowtrLzFUlafL1aTPb/xMruMNe9cTpcinDTUi4QdVtkKk22DYwokv793fXsSPwSNJBbgCMaOk1mw3/e53H/rIU+RUglpi4pc/5Nv2hhyG7XNOgguG108myH3e0pMC/9/q/fwkaXoFpijX1QXKQC222afPTdvANyVSfBwlFDwp+dg==");
        certAlipayRequest.setFormat(AlipayConstants.FORMAT);
        certAlipayRequest.setCharset(AlipayConstants.CHARSET);
        certAlipayRequest.setSignType(AlipayConstants.SIGN_TYPE);
        try{
            System.out.println(PathUtil.getPath("ZS/SX")+"/appCertPublicKey_2016102100729854.crt");
            System.out.println(PathUtil.getPath("ZS/SX")+"/alipayCertPublicKey_RSA2.crt");
            System.out.println(PathUtil.getPath("ZS/SX")+"/alipayRootCert.crt");
            certAlipayRequest.setCertPath(PathUtil.getPath("ZS/SX")+"/appCertPublicKey_2016102100729854.crt");
            certAlipayRequest.setAlipayPublicCertPath(PathUtil.getPath("ZS/SX")+"/alipayCertPublicKey_RSA2.crt");
            certAlipayRequest.setRootCertPath(PathUtil.getPath("ZS/SX")+"/alipayRootCert.crt");
        }catch (Exception e){
            e.printStackTrace();
        }
        return certAlipayRequest;
    }
}
