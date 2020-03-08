package com.pepay.api.alipay.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransAppPayRequest;
import com.alipay.api.response.AlipayFundTransAppPayResponse;

/**
 * @Author: zz
 * @Date:2020/02/20 10:14
 * @Description: TODO 描述
 */
public class Test {

    public static void main(String[] args)throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayToolSon.getCertAlipayRequest());
        AlipayFundTransAppPayRequest request = new AlipayFundTransAppPayRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_biz_no","123231321");
        jsonObject.put("trans_amount","0.01");
        jsonObject.put("product_code","STD_RED_PACKET");
        jsonObject.put("biz_scene","PERSONAL_PAY");
        JSONObject business_params = new JSONObject();
        business_params.put("sub_biz_scene","REDPACKET");
        business_params.put("payer_binded_alipay_id ","2088432746844846");
        jsonObject.put("business_params",business_params.toJSONString());
        request.setBizContent(jsonObject.toJSONString());
        System.out.println(request.getTextParams());
        AlipayFundTransAppPayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());

        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}

