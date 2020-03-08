package com.pepay.api.alipay.handler;

import com.pepay.api.alipay.domain.AlipayBusiness;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 轮询关系映射
 */
@Data
public class AlipayApiHandler {

    /** api 数组*/
    private String[] apiArr;
    /** 轮询指针*/
    private int pointer = 0;
    /** 支付宝商户ID*/
    private Long businessId;

    protected AlipayApiHandler(String[] apiArr,Long businessId ){
        this.apiArr = apiArr;
        this.businessId = businessId;
    }

    /**
     * 获取api，并更新指定
     * @return api
     */
    public String apiPoll(){
        String result = apiArr[pointer];
        pointer ++;
        if(pointer >= apiArr.length){pointer = 0;}
        return result;
    }

    /**
     * 如果api发生了更改，再进行api，指定重置
     * @param apiArr
     */
    public void reset(String[] apiArr){
        this.pointer = 0;
        this.apiArr = apiArr;
    }

    /**
     * 新增api映射关系
     * @param alipayBusiness
     */
    public static int saveAndPoll(List<AlipayApiHandler> alipayApiHandlerMapping, AlipayBusiness alipayBusiness){
        /** 判断此映射关系是存在*/
        String apiType = null;
        String[] newApiArr = alipayBusiness.getPayType().split(",");
        Long newbusinessId = alipayBusiness.getBusinessId();
        boolean isExist = false;
        for (AlipayApiHandler alipayApiItem: alipayApiHandlerMapping){
            if(alipayApiItem.getBusinessId().equals(newbusinessId)){
                isExist=true;
                /** 存在，判断此映射api是否有更新**/
                Boolean ygjm = alipayApiItem.getApiArr().toString().equals(newApiArr.toString());
                if(!Arrays.toString(alipayApiItem.getApiArr()).equals(Arrays.toString(newApiArr))){
                    alipayApiItem.reset(newApiArr);
                    System.out.println("*****"+ alipayBusiness.getBusinessName() +"api更新******>"+alipayApiItem);
                }
                apiType = alipayApiItem.apiPoll();
            }
        }
        /** 不存在*/
        if(!isExist){
            AlipayApiHandler apiHandler = new AlipayApiHandler(newApiArr,newbusinessId);
            apiType = apiHandler.apiPoll();
            alipayApiHandlerMapping.add(apiHandler);
            System.out.println("======"+alipayBusiness.getBusinessName()+"api新增======>"+apiHandler.toString());
        }
        return Integer.parseInt(apiType);
    }
}
