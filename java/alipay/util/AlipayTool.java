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
import com.pepay.api.alipay.domain.AlipayBusiness;
import com.pepay.api.alipay.dto.AlipayBarCodeDto;
import com.pepay.api.alipay.dto.RoutingDto;
import com.pepay.app.controller.ApplicationContextRegister;
import com.pepay.app.entity.SysConfig;
import com.pepay.app.mapper.SysConfigMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 支付API
 *
 * @Date:2019/12/03 14:22
 * @Description: TODO 支付类接口
 */
@Component
public class AlipayTool {
    private static Logger log = Logger.getLogger(AlipayTool.class.getName());


    public static JSONObject refund(JSONObject jsonObject, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.putOtherTextParam("app_auth_token", "202002BBffa64f74c9954e40b0c7265ca3159X57");
        request.setBizContent(jsonObject.toJSONString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }

    public static JSONObject alipay(AlipayBarCodeDto alipayBarCodeDto, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        log.info("发起支付参数：" + alipayBarCodeDto);
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());*/
        JSONObject object = new JSONObject();
        object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
        object.put("scene", AlipayConstants.SCRNC_BAR);
        object.put("auth_code", alipayBarCodeDto.getBarCode());
        object.put("subject", alipayBarCodeDto.getBName());
        object.put("buyer_id", alipayBarCodeDto.getBuyerId());
        object.put("total_amount", alipayBarCodeDto.getAmount());
        request.setBizContent(object.toJSONString());
        AlipayTradePayResponse alipayTradePayResponse = null;
        try {
            alipayTradePayResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + alipayTradePayResponse.getBody());
            return JSONObject.parseObject(alipayTradePayResponse.getBody());
        }
    }

    /**
     * alipay.trade.precreate( 统一收单线下交易预创建)二维码有效时间：2小时（计算时间是生成二维码链接开始计算）
     */
    public static JSONObject precreate(AlipayBarCodeDto alipayBarCodeDto, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());*/
        JSONObject object = new JSONObject();
        object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
        object.put("subject", "零售商品-2");
        object.put("total_amount", alipayBarCodeDto.getAmount());
        System.out.println(object.toJSONString());
        request.setBizContent(object.toJSONString());
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }


    public static String appPay(AlipayBarCodeDto alipayBarCodeDto, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", alipayBarCodeDto.getAppAuthToken());*/
        JSONObject object = new JSONObject();
        object.put("out_trade_no", alipayBarCodeDto.getOrderNumber());
        object.put("subject", "APP");
        object.put("product_code", "QUICK_MSECURITY_PAY");
        object.put("total_amount", alipayBarCodeDto.getAmount());
        request.setBizContent(object.toJSONString());
        AlipayTradeAppPayResponse alipayTradeAppPayResponse = null;
        try {
            alipayTradeAppPayResponse = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + alipayTradeAppPayResponse.getBody());
            return alipayTradeAppPayResponse.getBody();
        }
    }


    public static JSONObject bind(List<RoutingDto> routingDtos, String appAuthToken, String outRequestNo, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", appAuthToken);*/
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(routingDtos));
        JSONObject object = new JSONObject();
        object.put("receiver_list", array);
        object.put("out_request_no", outRequestNo);
        request.setBizContent(object.toJSONString());
        AlipayTradeRoyaltyRelationBindResponse tradeRoyaltyRelationBindResponse = null;
        try {
            tradeRoyaltyRelationBindResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeRoyaltyRelationBindResponse.getBody());
            return JSONObject.parseObject(tradeRoyaltyRelationBindResponse.getBody());
        }
    }

    public static JSONObject bind(RoutingDto routingDto, String appAuthToken, String outRequestNo, AlipayBusiness alipayBusiness) {
        List<RoutingDto> routingDtos = new ArrayList<>();
        routingDtos.add(routingDto);
        return AlipayTool.bind(routingDtos, appAuthToken, outRequestNo,alipayBusiness);
    }

    public static JSONObject unBind(RoutingDto routingDto, String appAuthToken, String outRequestNo, AlipayBusiness alipayBusiness) {
        List<RoutingDto> routingDtos = new ArrayList<>();
        routingDtos.add(routingDto);
        return AlipayTool.unBind(routingDtos, appAuthToken, outRequestNo,alipayBusiness);
    }

    public static JSONObject unBind(List<RoutingDto> routingDtos, String appAuthToken, String outRequestNo, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", appAuthToken);*/
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(routingDtos));
        JSONObject object = new JSONObject();
        object.put("receiver_list", array);
        object.put("out_request_no", outRequestNo);
        request.setBizContent(object.toJSONString());
        AlipayTradeRoyaltyRelationUnbindResponse tradeRoyaltyRelationUnbindResponse = null;
        try {
            tradeRoyaltyRelationUnbindResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeRoyaltyRelationUnbindResponse.getBody());
            return JSONObject.parseObject(tradeRoyaltyRelationUnbindResponse.getBody());
        }
    }

    public static JSONObject settle(JSONObject jsonObject, String appAuthToken, AlipayBusiness alipayBusiness) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
        /**request.putOtherTextParam("app_auth_token", appAuthToken);*/
        request.setBizContent(jsonObject.toJSONString());
        AlipayTradeOrderSettleResponse tradeOrderSettleResponse = null;
        try {
            tradeOrderSettleResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + tradeOrderSettleResponse.getBody());
            return JSONObject.parseObject(tradeOrderSettleResponse.getBody());
        }
    }

    public static JSONObject query(String outTradeNo, String appAuthToken, AlipayBusiness alipayBusiness) {
        AlipayTradeQueryResponse response = null;
        try {
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConstants.URL, alipayBusiness.getAppId(),
                        alipayBusiness.getAppPrivateKey(), AlipayConstants.FORMAT,
                        AlipayConstants.CHARSET, alipayBusiness.getAlipayPublicKey(), AlipayConstants.SIGN_TYPE);
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            /**request.putOtherTextParam("app_id", AlipayConstants.APP_ID);*/
            /**request.putOtherTextParam("app_auth_token", appAuthToken);*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("out_trade_no", outTradeNo);
            request.setBizContent(jsonObject.toJSONString());
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            log.info("response---" + response.getBody());
            return JSONObject.parseObject(response.getBody());
        }
    }


    /**
     * 分割list
     *
     * @param sList
     * @param num   长度
     * @return
     */
    public static List<List<AlipayAccounts>> split(List<AlipayAccounts> sList, int num) {
        List<List<AlipayAccounts>> eList = new ArrayList<List<AlipayAccounts>>();
        int size = sList.size();
        if (sList.size() > num) {
            int i = size / num;
            if ((i * num) < size) {
                i = i + 1;
            }
            for (int s = 0; s < i; s++) {
                List<AlipayAccounts> a = new ArrayList<>();
                int i1 = s * num;
                int i2 = num + (s * num);
                if (i2 > size) {
                    i2 = size;
                }
                for (int n = i1; n < i2; n++) {
                    a.add(sList.get(n));
                }
                eList.add(a);
            }
            return eList;
        } else {
            eList.add(sList);
            return eList;
        }

    }

    /**
     * 分割list2
     *
     * @param list
     * @param num   长度
     * @return
     */
    public static List<AlipayAccounts> split2(List<AlipayAccounts> list, int num) {
        List<AlipayAccounts> result = new ArrayList<>();
        if(num >= list.size()){result.addAll(list); return result;}
        for (int i = 0; i < num; i++) {
            result.add(list.get(i));
        }
        return result;
    }


    /**
     * 默认获取随机 0.1-0.5数
     *
     * @return
     */
    public static BigDecimal randomDefault() {
        BigDecimal b = new BigDecimal(String.format("%.1f", (Math.random() * 0.5)));
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            b = new BigDecimal("0.1");
        }
        return b;
    }

    /**
     * 从配置表中获取指定区间生成随机数
     *
     * @return
     */
    public static BigDecimal getRandom() {
        SysConfigMapper configService = ApplicationContextRegister.getApplicationContext().getBean(SysConfigMapper.class);
        SysConfig randomMoneyInterval = configService.get("random_money_interval");
        String value = randomMoneyInterval.getConfigValue();
        BigDecimal result = randomDefault();
        if (value == null || "".equals(value)) {
            return result;
        }
        String[] split = value.split(",");
        if (split.length == 2) {
            String max = split[1].trim();
            String min = split[0].trim();
            /** max, min 都为数字类型，并且 max > min*/
            if (isNumber(max) && isNumber(min)) {
                if (new BigDecimal(max).compareTo(new BigDecimal(min)) == 1) {
                    // 判断 max 是几位小数
                    String[] maxSplit = max.split("\\.");
                    String[] minSplit = min.split("\\.");
                    int maxPlaces = 0;
                    int minPlaces = 0;
                    if (maxSplit.length > 1) {
                        maxPlaces = maxSplit[1].length();
                    }
                    if (minSplit.length > 1) {
                        minPlaces = minSplit[1].length();
                    }
                    result = random(Double.valueOf(max), Double.valueOf(min), maxPlaces, minPlaces);
                }
            }
        }
        return result;
    }

    /**
     * 获取指定区间随机数
     *
     * @param max       最大值
     * @param min       最小值
     * @param maxPlaces max小数位数
     * @param minPlaces min小数位数
     * @return
     */
    public static BigDecimal random(Double max, Double min, int maxPlaces, int minPlaces) {
        BigDecimal b = new BigDecimal(new BigDecimal(Math.random() * max).intValue());
        BigDecimal mn = new BigDecimal(min.intValue());
        if (maxPlaces != 0) {
            b = new BigDecimal(String.format("%." + maxPlaces + "f", (Math.random() * max)));
        }
        if (minPlaces != 0) {
            mn = new BigDecimal(String.format("%." + maxPlaces + "f", min));
        }
        if (b.compareTo(mn) <= 0) {
            return mn;
        }
        return b;
    }

    /**
     * 判断是否是数字类型
     */
    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);

    }

}
