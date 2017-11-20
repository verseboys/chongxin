package com.k9sv;

public enum  Errorcode {

//	public static Integer SUCESS = 0;  //正确
//	
//	public static Integer Error =1;  //失败
//	
//	public static Integer NotLogin = 2;  //未登陆
//	
//	public static Integer SystemError = -1;  //系统错误
//	
//	public static Integer UserNotRegister = 3;  //用户未登陆
//	
//	public static Integer UserExists = 3;  //用户已经注册，相同的微信uniond
	
	SUCESS("成功","0"),
	notLogin("还没登陆","1001"),
	notRegister("账号没有注册过","1002"),
	Registered("手机号码已经注册过了","1003"),
	passwordError("用户名或者密码错误","1004"),
	oldPasswordError("原密码不正确","1005"),
	CheckCodeError("验证码错误","1006"),
	JsonError("json字符串错误","1007"),
	reLogin("重新登录","1008"),
	//ChangeTopic("只能修改自己的主题","1009"),
	UserNotExists("用户不存在","1100"),
	maxFileError("上传文件大于5M","1200"),
	deleteUserFeed("只能删除自己的动态","1500"),
	deleteFeedComment("没有权限删除的评论","1501"),
	NOTFeedComment("还没有评论过","1502"),
	AddFeedComment("动态已删除","1503"),
	ReAddFeedComment("重复添加动态","1504"),
	AlreadyZan("已经赞过了","1600"),
	NOTZan("还没有赞过","1601"),
	deleteUserAsk("只能删除自己的问题","1700"),
	deleteUserAnswer("只能删除自己的回复","1701"),
	AlreadyAttention("已关注","1800"),
	NOTAttention("还未关注","1801"),
	deleteDog("只能删除自己的宠物信息","1900"), 
	updateDog("只能修改自己的宠物信息","1901"),
	haveDogName("宠物名已存在","1902"),
	transDog("只能转让自己的宠物","1903"),
	deleteOrder("只能删除自己的预约单","2000"),
	existOrder("有未完成订单","2001"),
	exitOrderNotDeleted("服务中的订单不能删除","2002"),
	deleteAddress("只能删除自己的地址","2100"),
	AlipayVerify("验证失败","2200"),
	AlipayTrade("支付不成功","2201"),
	AlipayGold("支付金币有误","2202"),
	Alipay("支付金额有误","2203"),
	recordUpdate("只能修改自己的记录","2300"),
	recordDelete("只能删除自己的记录","2301"),
	NoCity("城市尚无定位","2400"),
	Recommend("同一用户不可重复推荐","2500"),
	RecommendMobile("手机号还未绑定","2501"),
	Cash("可用金额不足","2600"),
	BuyState("订单状态有误","2700"),
	noCompany("还未开通店铺","2800"),
	haveCompany("已经拥有店铺","2801"),
	updateCompany("只能修改自己的店铺信息","2802"),
	;

	
	private String errormsg;
	
	private String code;
	
	
	
	Errorcode(String errormsg,String code){
		this.errormsg = errormsg;
		this.code = code;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
