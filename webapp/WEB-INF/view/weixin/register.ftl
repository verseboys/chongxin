<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="UTF-8">
<title>微信登陆</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet" href="/assets/jquerymobile/jquery.mobile-1.4.5.min.css">
<script src="/assets/jquerymobile/jquery-2.1.4.min.js"></script>
<script src="/assets/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
<script src="/assets/js/jquery-cookie.js"></script>
<script src="/assets/js/ajaxfileupload.js" ></script>
<style>

</style>
<script>
$(function(){
	$("#registerbutton").click(function(){
		register();
	});
	$("#loginbutton").click(function(){
		login();
	}); 
});

function register(){
	var mobile = $("#mobile").val();
	var nickname = $("#nickname").val();
	var avatar = $("#avatar").val();
	var unionid = $("#unionid").val();
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/user/wxregister",//要访问的后台地址
            data: '{"mobile":"'+mobile+'","nickname":"'+nickname+'","avatar":"'+avatar+'","unionid":"'+unionid+'"}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
                var state = msg.state;
                if(state==0){
                	var dat = msg.data;
					var token = msg.token;
					$.cookie("chongxintoken", token);
                }else{
                	$("#loginDialog").popup('open');
                	$("#loginmobile").attr("value",mobile);
                	$("#loginDialogTitle").html("手机号码已经注册，请登录 ");
                	
                }
				
			}
		});
}
function login(){
	var mobile = $("#loginmobile").val();
	var password = $("#password").val();
	var unionid = $("#unionid").val();
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/user/login",//要访问的后台地址
            data: '{"username":"'+mobile+'","password":"'+password+'","unionid":"'+unionid+'"}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
                var state = msg.state;
                if(state==0){
                	var dat = msg.data;
					var token = msg.token;
					bind(token,unionid);
					$.cookie("chongxintoken", token);
					$.mobile.changePage("m",{ transition: "slideup" });
                }else{
                	alert("用户名密码错误");
                	$("#password").attr("value","");
                }
				
			}
		});
}
function bind(token,unionid){
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/user/bind?sid="+token,//要访问的后台地址
            data: '{"type":"weixin","platform":"web","bindid":"'+unionid+'"}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
                var state = msg.state;
			}
		});
}
</script>
</head>
<body>
<#if account?exists>
	${(account.username)?if_exists}
<#else>
<div data-role="page"  class="wrap" id="pageone">
	<div style="text-align:center;padding-top:10px">
	 <img src="<#if weixin?exists>${(weixin.avatar)?if_exists}<#else>http://image.k9sv.com/avatar-2015101422433446319_1242-1242.jpg</#if>" style="border-radius: 50em;width:130px">
    </div >
    <div class="intro">
    	<input type="hidden" name="unionid" id="unionid" value="<#if weixin?exists>${(weixin.unionid)?if_exists}<#else>1</#if>">
    	<input type="hidden" name="avatar" id="avatar" value="<#if weixin?exists>${(weixin.avatar)?if_exists}<#else>http://image.k9sv.com/avatar-2015101422433446319_1242-1242.jpg</#if>">
        <div class="name">
        	 <label>昵称:</label>
            <input type="text" name="nickname" id="nickname" value="<#if weixin?exists>${(weixin.nickname)?if_exists}</#if>">
        </div>
        <div class="name">
        	 <label>性别:</label>
            <input type="text" name="sex" id="sex" value="<#if weixin?exists>${(weixin.sex)?if_exists}</#if>">
        </div>
        <div class="name">
        	 <label>手机:</label>
            <input type="text" name="mobile" id="mobile" value="">
        </div>
        <div class="mobile">
            <button id="registerbutton">确 定</button>
        </div>
    </div>
    <div data-role="popup" id="loginDialog" style="width:400px;height:300px">
	  <div data-role="header"><h1 id="loginDialogTitle">..</h1></div>
	  <div data-role="main" class="ui-content">
	  		<div>
            	 <label>用户名</label>
                <input type="text" name="loginmobile" id="loginmobile" value="">
            </div>
            <div class="job">
            	<label>密码</label>
                <input type="password" name="password" id="password" value="">
            </div>
            <div>
                <button id="loginbutton">登录</button> 
            </div>
	  </div>
	</div>
</div>


</#if>
<div style="display:none;">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
</body>
</html>