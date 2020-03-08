package com.pepay.api.alipay.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;

import java.util.logging.Logger;


/**
 * 阿里回调授权工具类
 *
 * @Date:2019/12/03 10:38
 * @Description: TODO 阿里回调授权工具类
 */
public class AlipayAuthorizationTool {
    private static Logger log = Logger.getLogger(AlipayAuthorizationTool.class.getName());

    /**
     * 获取请求授权URL
     *
     *
     * @Date 2019/12/3 11:14
     * @param bid 商户
     * @return String
     **/
    public static String getAuthorizationUrl(String bid) {
        StringBuilder sb = new StringBuilder();
        sb.append(AlipayConstants.APP_TO_APP_AUTH);
        sb.append("?app_id=" + AlipayConstants.APP_ID);
        sb.append("&redirect_uri=" + AlipayConstants.REDIRECT_URL);
        sb.append("&application_type=" + AlipayConstants.APPLICATION_TYPE);
        sb.append("&state=" + Base64Util.encode(bid));
        return sb.toString();
    }

    public static void main(String[] args) {
        JSONObject appAuthToken = AlipayAuthorizationTool.getAppAuthToken("5ae3cd6c2e6b4c57bfd782ba81210X03");
        System.out.println(appAuthToken.toJSONString());
    }
    /**
     * 根据回调的app_auth_code 获取授权令牌及商户信息
     *
     *
     * @Date 2019/12/3  11:18
     * @param appAuthCode 回调返回的code
     * @return
     **/
    public static JSONObject getAppAuthToken(String appAuthCode) {

        AlipayOpenAuthTokenAppResponse response = null;
        try {
            log.info("APP_ID:"+AlipayConstants.APP_ID);
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayToolSon.getCertAlipayRequest());
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grant_type","authorization_code");
            jsonObject.put("code",appAuthCode);
            request.setBizContent(jsonObject.toJSONString());
            response = alipayClient.certificateExecute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(response.getBody());
    }





}
