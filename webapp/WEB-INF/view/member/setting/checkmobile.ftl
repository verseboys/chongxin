<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript">
	function getcode(){
		var telephone = $(".telephone").val();
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
			data : "{\"type\": \"bind\",\"mobile\": \""+telephone+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					alert("验证码已发送到你的手机");
				}else{
					var messge = rt.errormsg;
					alert(messge);
				}
			}
		});
	};
	function bind(){
		var _code = $(".code").val();
		if (_code == "") {
			alert("请输入验证码");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/confirmcode?sid=${sessionid?if_exists}",
			dataType : "json",
			contentType : "application/json",
			data : "{\"code\": \""+_code+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					alert("绑定成功");
					window.location.href = "/member/index/main";
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

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
		    <li><a href="/member/setting/checkmobile">手机号验证</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle">
    	<span>基本信息</span>
    </div>
    
    <ul class="forminfo">
	    <li>
	    	<label>手机号<b>*</b></label>
	    	<input type="text" class="dfinput telephone"/>
	    	<input type="button" class="scbtn" value="获取验证码" onclick="javascript:getcode();">
	    </li>
	    <li>
	    	<label>验证码<b>*</b></label>
	    	<input type="text" value="" class="dfinput code"/>
	    </li>
	    <li><label>&nbsp;</label><input type="button" class="btn" value="确认绑定" onclick="javascript:bind();"/></li>
    </ul>
    
    </div>


</body>

</html>
