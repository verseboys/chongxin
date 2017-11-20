<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/member/index/main">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
	    <div class="welinfo">
		    <span><img src="/assets/userui/images/sun.png" alt="天气" /></span>
		    <b>${(account.profile.nickName)?if_exists}，欢迎使用信息管理系统</b>
		    <a href="/member/setting/update">基本信息修改</a>
	    </div>
	    <div class="welinfo">
		    <span><img src="/assets/userui/images/time.png" alt="时间" /></span>
		    <i>您上次登录的时间：${(account.lastLoginStr)?if_exists}</i>
	    </div>
    
	    <div class="xline"></div>
	    <div class="welinfo">
		    <span><img src="/assets/userui/images/dp.png" alt="提醒" /></span>
		    <b>宠物</b>
	    </div>
	    <ul class="iconlist">
		    <li><img src="/assets/userui/images/mypet.jpg" /><p><a href="/member/dog">我的宠物</a></p></li>
		    <li><img src="/assets/userui/images/selledpet.jpg" /><p><a href="/member/dog/selled">已售宠物</a></p></li>
	    	<li><img src="/assets/userui/images/warranty.jpg" /><p><a href="/member/warranty">保单</a></p></li>
	    </ul>
	    <#if (account.profile.roleId)?exists && ((account.profile.roleId)== PetFactoryUser || (account.profile.roleId)==PetShopUser)>
	    	<div class="xline"></div>
		    <div class="welinfo">
			    <span><img src="/assets/userui/images/dp.png" alt="提醒" /></span>
			    <b>企业信息</b>
		    </div>
		    <ul class="iconlist">
			    <li><img src="/assets/userui/images/details.jpg" /><p><a href="/member/doghouse/update">信息</a></p></li>
			    <li><img src="/assets/userui/images/server.jpg" /><p><a href="/member/servers">服务</a></p></li>
			    <li><img src="/assets/userui/images/location.jpg" /><p><a href="/member/doghouse/baidumap">位置</a></p></li>
			    <li><img src="/assets/userui/images/_gonggao.jpg" /><p><a href="/member/announcement">公告</a></p></li>
		    </ul>
	    </#if>
	    <div class="xline"></div>
	    <div class="welinfo">
		    <span><img src="/assets/userui/images/dp.png" alt="提醒" /></span>
		    <b>客户关系</b>
	    </div>
	    <ul class="iconlist">
		    <li><img src="/assets/userui/images/fans.jpg" /><p><a href="/member/fans">粉丝</a></p></li>
		    <li><img src="/assets/userui/images/attention.jpg" /><p><a href="/member/attention">关注</a></p></li>
		    <li><img src="/assets/userui/images/message.jpg" /><p><a href="/member/message?uid=${(account.id)?if_exists}">消息</a></p></li>
	    </ul>
	    <div class="xline"></div>
	    <div class="welinfo">
		    <span><img src="/assets/userui/images/dp.png" alt="提醒" /></span>
		    <b>系统设置</b>
	    </div>
	    <ul class="iconlist">
		    <li><img src="/assets/userui/images/password.jpg" /><p><a href="/member/setting/changepassword">修改密码</a></p></li>
		    <li><img src="/assets/userui/images/avatar.jpg" /><p><a href="/member/setting/changeavatar">修改头像</a></p></li>
		    <li><img src="/assets/userui/images/telephon.jpg" /><p><a href="/member/setting/checkmobile">手机号验证</a></p></li>
	    </ul>
    </div>
</body>

</html>
