package com.pepay.api.alipay.util;

/**
 * 支付宝参数配置
 *
 *
 * @Date:2019/12/03 14:19
 * @Description: TODO 用于接口调用参数
 */
public class AlipayConstants {

    /**--------------------服务商--------------------*/
    /**
     * APP私钥
     */
    public static final String APP_PRIVATE_KEY ="MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCyNcRLW2tIlgGsoE6Q1sChv8wJVTrc0T4qoYxRyw0b82rZBS5v+ptDVq/LlTzYY0Yf+2QTA2k0dUJwkQByns7Q4iBvDU9fOLqMyn/FH0eLExAfjogJlZedRFmlIicXKUAH4Y+ZNKlUSv7BM7X5f64B/5IT2ZoDp8ZFOT8Cl+iCrCPK7EKRcz2BLZykoASmTJbKXvbT2ZFBrk48LAiMAXZQFGN82zLO4Z2lqd+tQIrSPAEam++W81oszcEyB2kJ20rJXWLrfGnnw4dOrLICaJnop818zraZ7MwllNbrxjthZ1Nwu1OeP2n146NvndG54mIGLF+/pNkKSLEuSUMnPCXJAgMBAAECgf82TCuCylflxc68yhsmBkiq/8UrEszceuR2GMKoKTXVTlU/ylRbyf+EhOULIm1ngNfc5ruXD3L+iLu1kHiSc9CP1FYNgACvApnUS96iMOWbejNvaf4I7YtEX47wwXcnl2nq/M7D30e4ZyE3vIm1ezGJalUXtzU15+iBLvAD2kT9ivGwPgvIkpFB9PsTZaIvUQhcnkSPX4aW9P4mvuaDyTduUX2ltTAkdyxKDgLfeyJE/T+CAMJiu8Fu6a4c+VEibjYvBBWtUgfZ/83Hxsr5dl2tDFgv3SsI0hTYXVd+KAOG27pXuB9kwZuduZD5ws4FNw7ZxSvvlXqYlbhLp25A2uECgYEA+MfHMk6fzu7B1Lkcz1iYUm8UJtuchBwOczhjpoHgm+OkAPsbsJ0tMBTSNFVSnc9Z8SVgiXuozlRpyUWPOsRhX0wn5crnnaAkmqD/umF4mPToJgf2qihTKbU2crVwXpUYubEl/hGbbNrHVW2884MxN9gssY+VB523gJP+V+o0+P0CgYEAt2G2WGGdxHZxkASHccC9ZjueFE7ADUTcTYfsUMY7/dBtXA45fB8mQ7QylJs8rDBtfeqP2vwisq7P522a3yV8ENukMqXQ1Y+6T90Aa1FUoVGRo2cIHS/+/27nn8HUMyvoBWWAHWJfD5N0UkW6NEPBUVk6rycEnFIZ/8O7aEKnj70CgYEAhPS396HfdIdTXe2kgQfXOE8XFQFPb6dS7a8YSreko4uQbdnF3ajSqpymDsP+yEo1xlP0eOV2vPaOJrwfJQgpBfY8aGn9SNd6WI1svl4zRoO0wgaFhEDS2Tp4o+tl55aFRVBwfo1IZG+0Emr4jafymObWU/rpac2XcYcmajPIJb0CgYBNU5ujaw4u0M8ULoS6l86FtG/1GjvrnHTVEJK6gMy9O01R+wGMcEofVHXbqhYMeaNB7E3axmUPOZ2bdeFPZSwKo8N6Q+dqll+223cYycPI/m9OGt0WCa4aIbbz67TCGyqMU/+Q5HE3TTNImfRpo4R5/Ak94FMWY5xo+C0nSqwDdQKBgFWgcIi1PkvjFibVCAklPx25ujCquCFgarxuf+Ori0SSRB+8M4QhY+zAqwyyrjwlTNhwf7YnJqadNdfMgyVgvdLCrQ+9bHRjJHbgA4VXtyCx4vBHTHS0/Fb3uCBj/pLeu/VpqNeK3fRoPMfezMadgk/Sf9WhPA2iHi948EDsmd7Z";
    /**
     * APP公钥
     */
    public static final String APP_PUBLIC_KEY ="";
    /**
     * APP_ID
     */
    public static final String APP_ID = "2021001136666232";
    /**
     * 支付宝公钥
     */
    public static final String ALIPAY_PUBLIC_KEY ="";
    /**
     * 支付宝私钥
     */
    public static final String ALIPAY_PRIVATE_KEY ="";

    /**手机网站支付通知地址*/
    public static final String NOTIFYURL ="";

    /****/
    public static final String APP_TO_APP_AUTH="https://openauth.alipay.com/oauth2/appToAppAuth.htm";
    /**
     * 授权回调页面
     */
    public static final String REDIRECT_URL = "https://icepay.jialapay.com/api/alipay/callback/callback";
    /**
     * 应用类型,目前支持类型 MOBILEAPP (移动应用)，WEBAPP（网页应用），PUBLICAPP（生活号），TINYAPP（小程序），ARAPP（AR应用）
     */
    public static final String APPLICATION_TYPE = "TINYAPP,WEBAPP,MOBILEAPP,PUBLICAPP,ARAPP";
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF-8
     */
    public static final String CHARSET = "gbk";
    /**
     * 参数返回格式，只支持json
     */
    public static final String FORMAT = "json";
    /**
     * 支付宝网关（固定）
     */
    public static final String URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 支付宝网关（固定）【沙箱】
     */
    public static final String URL_DEV = "https://openapi.alipaydev.com/gateway.do";
    /**
     * APPID【沙箱】
     */
    public static final String APP_ID_DEV = "2016102100729854";
    /**
     * 应用么钥【沙箱】
     */
    public static final String APP_PRIVATE_KEY_DEV = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQ1hwdVwdlkeCf1tgfg49R6nT9TfCpIKyjP0hXXqduDt/jC5QIU1oYg4Ga+XxX/JshDuM5v0z5QGz7Iv3g/45azNtHBv/QU07MNnUoNu26+T5y4NLsXHFSHV0sNW+Q2IDj/QFZxdDyVMgqzksOZlvROSzIQE9xy3r0O1Ecc5qs2CQ9/5SGSvCzAIYnVxFl+zvTU/G/9grdiZctySEwij6IpwYTjbY54EatTQ1x89OXgzmUfwQKgcAwlKDaUlq9JSlCoolIEs8nPpReHWhOzK3SnUM3hoUYLBrNwqk3kUCVt+CKI99mCABlSLQetjXCRBwXO8rQK6sfWIbUhNwMGLHZAgMBAAECggEASzlJBNwMlW48kgfN3CBCMbNp652tXsn/oBHgCrC4mOrU0WFUEzbnVlAG4mytbi1ejL7kuTtoSRRzBcRYw6E3WGWJhcxW1OZPyLSICMjuk+ubyaKHKgehO5rQp4NhXIN1/fOL3gplyVbLV6ApT2NOUM43OhJcsKeURoEAUZyhOx+HGoN3MMbxGPDFMecs0+UIcRhF1PFAXribvwBo8oErkCr5nw1vV/IspdrFxPFoE/N3Q2KPpV5Vis0Hp7K1IUBdyypiv6isTCDUcyHNtmxKpThq1W+kSaXSc+vEHMLWDpwCz1Xf3Z4rIIsd4n2KXLmtnaGxpWABzVS3a4SgH9Mp4QKBgQDH4dd5ItizaJXOVd3gpwzDkxbm9BpoHugMQl2tIll3o6IF/c+aFNc6Xds5HiO95kvRCNJD0Gk9cx9MusknARcMrlo2+ADVtw5uUl1N30FXAXvOeLRaXQCs0rxLGsUXQiaGB0y/pJGqKrWOPDFWF/K+ORRKIEoKOUHO/8B91HpLTQKBgQC5f/PO4gnwZZVvs6hIDYrIpkcwRZmPs6DBx9/RmEZ7Z1rBpTST7conKPyQkLWHJNkKa9y+AFHMgzn0UKzrrwR6FXoceiHMhEpwDYna/J+QBn3jh+n+2+KcSCTEbJ5KzJCyMiZbOHlx5pfpv/Tx/+BiMKZfWIzQE1TKc4zZxNqCvQKBgQCSFnsguxo8zhSncNSwrAdFLc0QUZq3PNeGlj7XGoLfUUZLML05tT+1FgHy1hdzZM1G4zaif8FbOzfGIcKA8mgBSbD/+fTKPneapDIzhxe/WoM9XbUbUlv69C7QialBHYUAcndingokoACAC3hXnzs4UomEv+K9ZCm3++6ZSOF1BQKBgQCXUyk6sS04fjY9DIuQU/POoc7bMzwFfZz72CjfG+ELlJH1/OZ6cvaBoa7/4/0iRU4FcV5/ZVVViAvEIfgewATf7xtRldRyuczbwj+edI1aeeFCt0KNVSV1JmlQD+fFz0GPDNg7fEOeRmXRG3Yf4QRUNanTiDI/IDYAfej+UdeL5QKBgHTzwmzdhyi2W07AHP6v049GzdY/xQIuJbr1XiuiTUDJZebwN7EefLMHVmzEnNTOO5y3Ls99LZWUYP8qyq2OsAmGwgUYl+QcTLiM2H5mRhL4VSySaMU57cMSJsxOWGYsST9Xdzhs+kge1o7zPbDS60hXTMg/sncYGOuhIk2gqsbl";
    /**
     * 支付宝公钥【沙箱】
     */
    public static final String ALIPAY_PUBLIC_KEY_DEV = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqwetFvAywQO+H2swKHojfdSbgQX/jNuUW18jUX0Gijga50qPlzrgr7aJ6TVSR9ctFpuB49WANvcRqrZB2mGI3kwBZpCC/b0Fno5NsAkr4+vdBPrKvqSyir5xw6mA69WPS8SP9wD9JUgMvMOQeRYnXu8KOE8IqBVjqu87qNUQoJR9f6J61IZs3Vj+cmTdlg/vKPIy2w37hiNFPlUqFZnnkOnXJrAwPseujcAeYCYXVHIF++EC6f1L2gVL/lIQCGDCOQDImfNxG6oeSDEnKmmT48WdA0/LznBUCSJW+nlKjDb/WBbYH+wdJ1Cej1YsBmLHwyNHHhRcbG3oJVY6xprYwwIDAQAB";
    /**
     * 签名算法类型
     */
    public static final String SIGN_TYPE = "RSA2";
    /**
     * 条码支付
     */
    public static final String SCRNC_BAR = "bar_code";
    /**
     * 声波支付
     */
    public static final String SCRNC_WAVE = "wave_code";



    /**交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、
     * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
     * TRADE_SUCCESS（交易支付成功）、
     * TRADE_FINISHED（交易结束，不可退款）
   */
    public static final String PAY_WAIT_BUYER_PAY="WAIT_BUYER_PAY";
    public static final String PAY_TRADE_CLOSED="TRADE_CLOSED";
    public static final String PAY_TRADE_SUCCESS="TRADE_SUCCESS";
    public static final String PAY_TRADE_FINISHED="TRADE_FINISHED";

    /**
     * 转账
     * 业务产品码，
     * 收发现金红包固定为：STD_RED_PACKET；
     * 单笔无密转账到支付宝账户固定为：TRANS_ACCOUNT_NO_PWD；
     * 单笔无密转账到银行卡固定为：TRANS_BANKCARD_NO_PWD
     */
    public static final String STD_RED_PACKET="STD_RED_PACKET";
    public static final String TRANS_ACCOUNT_NO_PWD="TRANS_ACCOUNT_NO_PWD";
    public static final String TRANS_BANKCARD_NO_PWD="TRANS_BANKCARD_NO_PWD";
    /**
     * 转账
     * 描述特定的业务场景，可传的参数如下：
     * PERSONAL_COLLECTION：C2C现金红包-领红包；
     * DIRECT_TRANSFER：B2C现金红包、单笔无密转账到支付宝/银行卡
     */
    public static final String PERSONAL_COLLECTION="PERSONAL_COLLECTION";
    public static final String DIRECT_TRANSFER="DIRECT_TRANSFER";
    /**
     * 转账
     * 参与方的标识类型，目前支持如下类型：
     * 1、ALIPAY_USER_ID 支付宝的会员ID
     * 2、ALIPAY_LOGON_ID：支付宝登录号，支持邮箱和手机号格式
     */
    public static final String ALIPAY_USER_ID="ALIPAY_USER_ID";
    public static final String ALIPAY_LOGON_ID="ALIPAY_LOGON_ID";
    /**
     * 转账单据状态。
     * SUCCESS：成功（对转账到银行卡的单据, 该状态可能变为退票[REFUND]状态）；
     * FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）；
     * DEALING：处理中；
     * REFUND：退票；
     */
    public static final String SUCCESS="SUCCESS";
    public static final String FAIL="FAIL";
    public static final String DEALING="DEALING";
    public static final String REFUND="REFUND";


    /** 1,2,3,4 api 支持*/
    public final static  String[] API_ARR_ONE = {"1", "2", "3", "4"};
    public final static String INTERFACETYPE_ONE="INTERFACETYPE_ONE";
    /** 4 api 支持*/
    public final static  String[] API_ARR_TWO = {"4"};
    public final static String INTERFACETYPE_TWO="INTERFACETYPE_TWO";

    /** APP 订单字符串*/
    public final static String ORDER_STR = "orderStr";
}
