<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript">
	var token;
	function check(){
		var roleid = ${(account.checked)?if_exists};
		if(roleid!=1){
			alert("请先绑定手机号！");
			window.location.href = "/member/setting/checkmobile";
		}
	}
	check();
	function getcode(){
		var telephone = ${(account.username)?if_exists};
		var rule1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;// 手机号
		var telephoneReg = rule1.test(telephone);
		if (telephoneReg == false) {
			alert("请填写正确的手机号");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/getcode?sid=${sessionid?if_exists}",
			dataType : "json",
			contentType : "application/json",
			data : "{\"type\": \"forgetpassword\",\"mobile\": \""+telephone+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					token = rt.data.token;
					alert("验证码已发送到你的手机");
				}else{
					var messge = rt.errormsg;
					alert(messge);
				}
			}
		});
	};
	var isok = 0;//验证码验证状态
	function checkcode(){
		var _code = $(".code").val();
		if (_code == "") {
			alert("请输入验证码");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/confirmcode?sid="+token,
			dataType : "json",
			contentType : "application/json",
			data : "{\"code\": \""+_code+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					isok = 1;
					alert("验证成功");
				}else{
					var messge = rt.errormsg;
					alert(messge);
				}
			}
		});
	};
	function confirm(){
		if(isok==1){
			var _password = $(".password").val();
			if (_password == "") {
				alert("密码不能为空！");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "/member/setting/changepassword/confirm",
				data : {
					'token' : token,
					'password' : _password
				},
				success : function(rt) {
					if (rt == 1) {
						alert("密码修改成功,请退出重新登录");
					}
				}
			});
		}else{
			alert("验证码不正确");
		}
	};
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
		    <li><a href="/member/setting/changepassword">修改密码</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle">
    	<span>基本信息</span>
    	<ul class="toolbar1">
        	<input type="button" class="scbtn" value="获取验证码" onclick="javascript:getcode();"/>
        </ul>
    </div>
    
    <ul class="forminfo">
	    <li>
	    	<label>验证码<b>*</b></label>
	    	<input type="text" class="dfinput code"/>
	    	<input type="button" class="scbtn" value="验证验证码" onclick="javascript:checkcode();">
	    </li>
	    <li>
	    	<label>新密码<b>*</b></label>
	    	<input type="password" value="" class="dfinput password"/>
	    </li>
	    <li><label>&nbsp;</label><input type="button" class="btn" value="确认修改" onclick="javascript:confirm();"/></li>
    </ul>
    
    </div>


</body>

</html>
