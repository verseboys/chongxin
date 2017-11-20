<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(/assets/userui/images/topbg.gif) repeat-x;">

    <div class="topleft">
    	<a href="/" target="_parent">
    		<img src="/assets/userui/images/logo.png" title="系统首页" />
    	</a>
    </div>
        
    <ul class="nav">
    <!--
    <li><a href="javascript:;" target="rightFrame" class="selected"><img src="/assets/userui/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="javascript:;" target="rightFrame"><img src="/assets/userui/images/icon02.png" title="宠物" /><h2>宠物</h2></a></li>
    <li><a href="javascript:;"  target="rightFrame"><img src="/assets/userui/images/icon03.png" title="企业信息" /><h2>企业信息</h2></a></li>
    <li><a href="javascript:;"  target="rightFrame"><img src="/assets/userui/images/icon04.png" title="客户关系" /><h2>客户关系</h2></a></li>
    <li><a href="javascript:;" target="rightFrame"><img src="/assets/userui/images/icon05.png" title="系统设置" /><h2>系统设置</h2></a></li>
    <li><a href="javascript:;"  target="rightFrame"><img src="/assets/userui/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    -->
    <li><a href="/member/index/main" target="rightFrame" class="selected"><img src="/assets/userui/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="/member/index/left?type=1" target="leftFrame"><img src="/assets/userui/images/pet.png" title="宠物" /><h2>宠物</h2></a></li>
    <#if (account.profile.roleId)?exists && ((account.profile.roleId)== PetFactoryUser || (account.profile.roleId)==PetShopUser)>
    	<li><a href="/member/index/left?type=2"  target="leftFrame"><img src="/assets/userui/images/company.png" title="企业信息" /><h2>企业信息</h2></a></li>
    </#if>
    <li><a href="/member/index/left?type=3"  target="leftFrame"><img src="/assets/userui/images/client.png" title="客户关系" /><h2>客户关系</h2></a></li>
    <li><a href="/member/index/left?type=4" target="leftFrame"><img src="/assets/userui/images/icon05.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li>
    	<a href="javascript:;">
    		<#if (account.profile.roleId)?exists>
    			<#if (account.profile.roleId)==PetFactoryUser>
    				认证犬舍
    			<#elseif (account.profile.roleId)==PetShopUser>
    				认证宠物店
    			<#else>
    				普通会员
    			</#if>
    		</#if>
    	</a>
    </li>
    <li><a href="/about" target="_parent">关于</a></li>
    <li><a href="/member/logout" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${(account.profile.nickName)?if_exists}</span>
    <a href="/member/message?uid=${(account.id)?if_exists}" target="rightFrame">
	    <i style="margin-right:13px;">消息</i>
    </a>
    </div>    
    
    </div>

</body>
</html>
