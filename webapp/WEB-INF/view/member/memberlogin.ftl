<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/userui/js/cloud.js" type="text/javascript"></script>
<script src="/assets/js/login.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(/assets/userui/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录宠信信息管理系统</span>    
    <ul>
    <li><a href="/">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="/about">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox loginbox1">
    <form id="login-form" action="/member/loginaction" method="post">
	    <ul>
	    <li><input id="account" name="username" type="text" class="loginuser" placeholder="用户名/已验证手机号" onclick="JavaScript:this.value=''"/></li>
	    <li><input id="password" name="password" type="password" class="loginpwd" placeholder="密码" onclick="JavaScript:this.value=''"/></li>
	    <!--<li class="yzm">
	    <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite> 
	    </li>-->
	    <li>
	    	<input name="" type="button" class="loginbtn login-btn" value="登录"  />
	    	<label style="color: red">${error?if_exists}</label>
	    	<!--<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
	    	<label><a href="#">忘记密码？</a></label>-->
	    </li>
	    </ul>
    </form>
    <div style="width:410px;float:right;margin-top:10px;">
    	<div style="float:left;width:128px;height:20px;text-align:left;font-size:16px;margin-top:5px;">使用社交帐号登录</div>
        <div style="float:left;width:30px;height:29px;margin-left:10px;"><a href="${url?if_exists}"><img src="/assets/img/weixin.png" alt="微信"/></a></div>
        <!--<div class="other-img"><a href="${url?if_exists}"><img src="/assets/img/qq.png" alt="QQ"/></a></div>-->
    </div>
    
    </div>
    
    </div>
    
    
    <div class="loginbm">版权所有 杭州宠信科技有限公司  <a href="http://www.ichongxin.com/" target="_blank">www.ichongxin.com</a></div>
	
    
</body>

</html>
