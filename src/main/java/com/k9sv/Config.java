package com.k9sv;

import java.util.Arrays;
import java.util.List;

public class Config {

	public static String Server = "http://sev.ichongxin.com/";
	public static String Toutiao = "http://sev.ichongxin.com/toutiao/";

	public static String ShareUrl = "http://www.ichongxin.com";
	public static String RecommendUrl = "/web/register/";// 推荐地址

	public static String FeedShareUrl = "http://www.ichongxin.com/h5/feed/";

	public static Integer TaintedUser = -1; // 污点用户
	public static Integer CommonUser = 1; // 普通用户
	public static Integer PetFactoryUser = 2; // 犬舍
	public static Integer PetShopUser = 3; // 宠物店
	public static Integer BaoUser = 4; // 宠信宝用户
	public static Integer Admin = 100;// 管理员

	public static String pwdExtention = "gsdef";
	// 腾讯云
	public static Integer projectID = 10002859;
	public static String projectName = "k9img";
	public static String secretKey = "Dp3ClajvLMvgt1OIki3IspmhSe1tLPFR";
	public static String secretID = "AKIDHVrPdhBm9bkAfpiuKDKNHvAEBriNiEzQ";

	public static String PushFeedIM = "IM"; // 推送类型 聊天消息
	public static String PushFeedComment = "feedComment"; // 动态评论
	public static String PushFeedZan = "feedZan"; // 动态赞
	public static String PushFriend = "friend"; // 关注好友
	public static String PushOrderState = "order"; // 预约状态转变
	public static String PushTrans = "trans"; // 转让
	public static String BuyState = "BuyState"; // 订单状态

	public static Integer UserMan = 1; //

	public static Integer UserWoman = 2; //

	public static Integer PageSize = 15;

	public static int AvatarWidth1 = 92; // 头像缩略图宽度

	public static int AvatarHeight1 = 92; // 头像缩略图高

	public static int AvatarWidth2 = 134;

	public static int AvatarHeight2 = 134; // 头像缩略图高

	public static int FeedWidth = 210;

	public static int FeedHeight = 210;

	public static int MaxPicWidth = 1500;

	public static int MaxPicHeight = 1500;

	public static int MaxTopicPicWidth = 1080;// 用户主题图片
	public static int MaxTopicPicHeight = 1080;

	public static int MallProductPicWidth = 750;// 商城产品图片
	public static int MallProductPicHeight = 480;
	public static int ProductSmallPicHeight = 500;
	public static int ProductSmallPicWidth = 500;

	public static int OrderState = 0; // 订单未处理状态
	public static int OrderStateTaker = 1; // 已经接单
	public static int OrderStateService = 2; // 服务中，
	public static int OrderStateCancel = 3; // 订单客户取消
	public static int OrderStateFinished = 4; // 订单完成

	public static String LoginPlatformMoble = "mobile";
	public static String LoginPlatformWeb = "web";
	public static String LoginPlatformWeixin = "weixin";

	public static Integer ContentTypeText = 0;// 消息类型（文本）
	public static Integer ContentTypeImg = 1;// 消息类型（图片）
	public static Integer ContentTypeVoice = 2;// 消息类型（语音）
	public static Integer ContentTypeCard = 10;// 消息类型（卡片）
	public static Integer SubjectWidth1 = 470;
	public static Integer SubjectHeight1 = 380;
	public static Integer SubjectWidth2 = 670;
	public static Integer SubjectHeight2 = 670;

	public static String DefaultPassword = "chongxin";// 第三方登录默认密码

	public static final List<String> Municipality = Arrays.asList("北京", "天津",
			"重庆", "上海", "香港", "澳门");// 直辖市、特别行政区

	public static int DayGoldCount = 10;// 每天领取金币上限
	public static int ZanGole = 1;// 赞一次得的金币
	public static int CommentGold = 1;// 评论一次得的金币
	public static int SignInGold = 1;// 签到得的金币
	public static int Comment = 0;// 评论
	public static int Zan = 1;// 赞
	public static int SignIn = 2;// 签到

	public static float Recommend = 50;// 推荐获得金额
	public static float Rate = (float) 0.05; // 购物返还率
	public static float MinCharge = 1; // 获得推荐金的最低消费
	public static int GoldRate = 10;// 金币兑换率(10个金币是1元)

}
