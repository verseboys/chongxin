<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<link href="/assets/css/recommend.css" rel="stylesheet">
<script src="/ui/js/jquery-1.7.2.js" type="text/javascript"></script>
<title>推荐注册</title>
<script type="text/javascript">
	var sessionId = "0";
	function getCode(){
		var telephone = $.trim($(".telephone").val());
		var rule1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;// 手机号
		var telephoneReg = rule1.test(telephone);
		if (telephoneReg == false) {
			alert("请填写正确的手机号");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/getcode",
			dataType : "json",
			contentType : "application/json",
			data : "{\"type\": \"register\",\"mobile\": \""+telephone+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					sessionId = rt.data.token;
					alert("验证码已发送到你的手机");
				}else{
					var messge = rt.errormsg;
					alert(messge);
				}
			}
		});
	}
	
	function register(){
		var code = $.trim($(".code").val());
		var password = $.trim($(".password").val());
		if(code==""){
			alert("请输入验证码");
			return false;
		}
		if(password==""){
			alert("请设置密码");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/register?sid="+sessionId,
			dataType : "json",
			contentType : "application/json",
			data : "{\"password\": \""+password+"\",\"code\": \""+code+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					alert("注册成功，请下载app登录");
					window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chongxin.app&g_f=991653#opened";
				}else{
					var messge = rt.errormsg;
					alert(messge);
				}
			}
		});
	}
</script>
</head>

<body>
	<div class="all">
    	<img src="/assets/img/banner1.jpg" width="100%"/>
        <div class="content">
            <input type="text" placeholder="请输入你的手机号" class="input telephone" style="width:100%;"/>
            <div class="yanzhengma">
            	<input type="text" placeholder="验证码" class="input input_div code"/>
                <div class="btn" onclick="javascript:getCode();">
                  	获取验证码
                </div> 
            </div>
            <input type="text" placeholder="请设置登录密码" class="input password" style="width:100%;margin-top:15px;"/> 
            <div class="btn2" onclick="javascript:register();">
                   	立即注册宠信APP,300元奖励等着你
            </div>           
        </div>
        <div class="friend">
        	你的好友：${(bean.nickName)?if_exists}邀请您一起加入宠信。
        </div>
        <img src="/assets/img/what.jpg" width="100%"/>
        <img src="/assets/img/why.jpg" style="margin-top:30px;width:100%;"/>
        <img src="/assets/img/online.jpg" style="margin-top:40px;width:100%;"/>
        <img src="/assets/img/dangan.jpg" style="margin-top:40px;margin-bottom:20px;width:100%;"/>
    </div>
</body>
</html>
