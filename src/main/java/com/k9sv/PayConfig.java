package com.k9sv;

public class PayConfig {
	// 微信
	public static String WebAPPID = "wx5a8aa22b8771f735";
	public static String WebSECRET = "fa62aece6452d5bed9832256efc8e6e9";
	public static String APPID = "wxee35af4db784f721";
	public static String APPSECRET = "029ca09e4f1ea6ce32f3906132426bda";
	public static String APPID_BAO = "wx7b0a0e6b4dcc769c";//宠信宝id wx7b0a0e6b4dcc769c
	public static String APPSECRET_BAO = "4112e5e53440dccfde88a69cf88312fa";//宠信宝key 
	public static String weixinpartner = "1277132001";// 商户号(正)
	public static String weixinkey = "ichongxinabcwuyuqideiuesixinchsd";// 商户号对应的密钥(正)

	public static String notify_url = Config.Server
			+ "server/product/weixinpay";// 接收财付通通知的URL(外网)
	public static String tokenurl = "https://api.weixin.qq.com/cgi-bin/token";// 获取access_token对应的url
	public static String grant_type = "client_credential";// 常量固定值
	public static String access_token = "access_token";// access_token常量值
	public static String gateurl = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 获取预支付id的接口url
	public static String PckageValue = "Sign=WXPay";

	public static String WeixinDingyue = "wxee35af4db784f721";
	public static String WeixinDingyueSecret = "029ca09e4f1ea6ce32f3906132426bda";

	public static String WeixinFuwu = "wxd0394b0112944897";
	public static String WeixinFuwuSecret = "97b73cfce5dfa361f3c5ab1e58f5c90d";
	// 支付宝
	public static String partner = "";
	public static String key = "";
	public static String seller_email = "pay@ichongxin.com";
	public static String log_path = "D:\\";// 调试用，创建TXT日志文件夹路径
	public static String sign_type = "RSA";// 签名方式 不需修改
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
}
