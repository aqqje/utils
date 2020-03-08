package com.pepay.api.alipay.handler;

import com.pepay.api.alipay.dao.AlipayBusinessDao;
import com.pepay.api.alipay.domain.AlipayBusiness;
import com.pepay.api.alipay.util.AlipayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class AlipayBusinessHandler {
    /** 一个LIST容器存储分帐帐号信息*/
    private static List<AlipayBusiness> businessList = new ArrayList<>();
    private static List<AlipayBusiness> businessList2 = new ArrayList<>();
    /** 容器轮询指针*/
    private static int pointer = 0;

     @Autowired
     private AlipayBusinessDao alipayBusinessDao;
     private static AlipayBusinessHandler alipayBusinessHandler;

    @PostConstruct
     public void init(){
        alipayBusinessHandler = this;
        System.out.println(alipayBusinessDao);
        List<AlipayBusiness> alipayBusinesses = alipayBusinessDao.selectAlipayBusinessByPayTepyStr(AlipayConstants.API_ARR_ONE);
        businessList.addAll(alipayBusinesses);
        List<AlipayBusiness> alipayBusinesses2 = alipayBusinessDao.selectAlipayBusinessByPayTepyStr(AlipayConstants.API_ARR_TWO);
        businessList2.addAll(alipayBusinesses2);
     }
    /**
     * 获取api，并更新指定
     * @return api
     */
    public AlipayBusiness businessPoll(String interfaceType){
        List<AlipayBusiness> list = businessList;
        if(interfaceType.equals(AlipayConstants.INTERFACETYPE_TWO)){
            list = businessList2;
        }
        pointer ++;
        if(pointer > list.size() - 1){pointer = 0;}
        if(list.isEmpty()){return null;}
        AlipayBusiness result = list.get(pointer);
        /**状态检查*/
        AlipayBusiness b2 = alipayBusinessDao.getById(String.valueOf(result.getBusinessId()));
        if(b2.getRemoveFlag() == 1 || b2.getStatus() == 1){
            boolean remove = list.remove(result);
            if(remove){
                return businessPoll(interfaceType);
            }
        }
        System.out.println("商家轮询列表");
        for (AlipayBusiness alipayBusiness : list) {
            System.out.println(alipayBusiness.getBusinessName());
        }
        return result;
    }

    /**
     * 新增分帐帐号映射关系
     * @param alipayBusiness
     */
    public AlipayBusiness saveAndPoll(AlipayBusiness alipayBusiness, String interfaceType){
        List<AlipayBusiness> list = businessList;
        if(interfaceType.equals(AlipayConstants.INTERFACETYPE_TWO)){
            list = businessList2;
        }
        /** 判断此映射关系是存在*/
        boolean isExist = false;
        for (int i = 0; i < list.size(); i++) {
            /** 应用主键存在*/
            if(list.get(i).getBusinessId().equals(alipayBusiness.getBusinessId())){
                isExist = true;
                // /** 应用信息不一致，则进行移除更新*/
                if(!(list.get(i).getAppId().equals(alipayBusiness.getAppId())
                        && list.get(i).getAppPrivateKey().equals(alipayBusiness.getAppPrivateKey())
                        && list.get(i).getAlipayPublicKey().equals(alipayBusiness.getAlipayPublicKey()))){
                /** 移除sccount*/
                list.remove(i);
                /** 更新accounts*/
                list.add(i, alipayBusiness);
                System.out.println("*****"+ alipayBusiness.getBusinessName() +"alipayBusiness更新******>"+alipayBusiness.toString());
                }
            }
        }
        if(!isExist){
            list.add(alipayBusiness);
            System.out.println("^^^^^^^"+ alipayBusiness.getBusinessName() +"alipayBusiness新增^^^^^^^^^>"+alipayBusiness.toString());
        }
        /** 更新指针并获取数据*/
        return businessPoll(interfaceType);
    }
}
