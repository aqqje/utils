package com.pepay.api.alipay.util;

import java.math.BigDecimal;

/**
 * 公用常量
 */
public class Constant {

    public static final String SYSYTEN_ERROR = "系统异常";
    public static final String ORDER_GENERATION_FAILED = "订单生成失败";
    public static final String ALIPAY_ORDER_GENERATION_FAILED = "支付宝订单生成失败";

    public static final String ORDER_REMARK_ONE = "支付宝当面付二维码支付";
    public static final String ORDER_REMARK_TWO = "支付宝手机网站支付";
    public static final String ORDER_REMARK_THREE = "支付宝电脑网站支付";
    public static final String ORDER_REMARK_FOUR = "支付宝APP支付";
    public static final String ORDER_REMARK_FOUR_2 = "支付宝APP支付WEB";
    public static final String ORDER_REMARK_OTHER = "未知支付方式";

    public static final String CURRENT_SYSTEM_BUSY = "当前系统繁忙";
    public static final String MERCHANT_ORDER_NUM_EMPTY = "商家订单号不能为空";
    public static final String MERCHANT_FROZEN = "商家被冻结";
    public static final String MERCHANT_NOT_EXIST = "商家不存在";
    public static final String AMOUNT_NOT_LESS_ZERO = "金额不能小于0";
    public static final String CHANNEL_NOT_OPEN = "通道未开放";
    public static final String SIGNATURE_ERROR = "签名错误";
    public static final String MISSING_REQUIRED_PARAMETERS = "缺少必要参数";
    public static final String PAYMENT_REQUEST_FAILED = "支付请求失败";
    public static final String ORDER_TIME_OUT = "订单超时";
    public static final String TATE_NOT_INITIAL = "费率未初始";
    public static final String DUPLICATE_ORDER = "订单号重复";
    public static final String USER_NOT_EXIST = "用户不存在";
    public static final String CURRENT_MERCHANT_NOT_ORDER_REMARK_ONE = "当前商户不支付当面付";

    public static final String MESSAGE = "message";
    public static final String SUCCESS = "success";
    public static final String ERROR_PAGE = "error";
    public static final String ALIPAY_AUTO_PAGE = "alipay_auto";
    public static final String ALIPAY_TEST_ORDER_PAGE = "alipay_test_order";
    public static final String ALIPAY_TEST_ORDER_PAGE_TWO = "alipay_test_order_2";
    public static final String ALIPAY_PAGE = "alipay";

    /** app 支付测试商家信息*/
    public static final String PUBLIC_TEST_KEY = "16DI+zadgAvKaxND";
    public static final String PRIVATE_TEST_KEY = "5ATbc4ErLj7XoMj7";
    public static final String PLATFORM_TEST_ID = "156886766630367734";
    public static final String ZFFS_TEST_TYPE = "2";
    public static final BigDecimal PAY_TEST_MONEY = new BigDecimal("0.01");

    public static final BigDecimal HUNDRED = new BigDecimal("100");





}
