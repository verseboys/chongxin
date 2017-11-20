<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345			  
		});
		$(".select2").uedSelect({
			width : 167  
		});
	});
</script>
<script type="text/javascript">
$(function() {
	$(".combox1").change(function() {
		var fcityid = $(this).children('option:selected').val();
		var obj = $(".cityright");
		var pobj = $(".usercity");
		$.ajax({
			type : "POST",
			url : "/member/setting/update/city_"+fcityid,
			success : function(rt) {
				if (rt != "") {
					$(obj).remove();
					$(pobj).append(rt);
				} else {
					alert("糟糕，出错了");
				}
			},
			error : function() {
				alert("糟糕，出错了");
			}
		});
	});
})
function submit(){
	var nickName = $(".nickName").val();
	var sex = $(".sex").val();
	var mobile = $(".mobile").val();
	var combox1 = $(".combox1").val();
	var cityid = $(".cityid").val();
	var address = $(".address").val();
	var rule1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;// 手机号
	if (nickName == "") {
		alert("请填写昵称");
		return false;
	}
	if (sex == "") {
		alert("请选择性别");
		return false;
	}
	var telephoneReg = rule1.test(mobile);
	if (telephoneReg == false) {
		alert("请填写正确的手机号");
		return false;
	}
	if (!cityid) {
		if(!combox1){
			alert("请选择城市");
			return false;
		}
		cityid = combox1;
	}
	if (address == "") {
		alert("请填写家庭地址");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/server/user/update?sid=${sessionid?if_exists}",
		dataType : "json",
		contentType : "application/json",
		data : "{\"nickname\": \""+nickName+"\",\"mobile\": \""+mobile+"\",\"sex\":\""+sex+"\",\"address\":\""+address+"\",\"cityid\":\""+cityid+"\"}",
		success : function(rt) {
			if (rt.state == 0) {
				alert("修改成功");
				window.location.href = "/member/index/main";
			} else {
				var message = rt.errormsg;
				alert(message);
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
		    <li><a href="/member/setting/update">基本信息修改</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle">
    	<span>基本信息</span>
    </div>
    
    <ul class="forminfo">
	    <li>
	    	<label>昵称<b>*</b></label>
	    	<input type="text" class="dfinput nickName" value="${(account.profile.nickName)?if_exists}"/>
	    </li>
	    <li>
	    	<label>性别<b>*</b></label>
	    	<div class="vocation">
			    <select class="select1 sex">
				    <option value="1" <#if (account.profile.sex)?exists && (account.profile.sex)==1>selected="selected"</#if>>男</option>
					<option value="2" <#if (account.profile.sex)?exists && (account.profile.sex)==2>selected="selected"</#if>>女</option>
			    </select>
			</div>
	    </li>
	    <li>
	    	<label>手机号<b>*</b></label>
	    	<input type="text" value="${(account.profile.mobile)?if_exists}" class="dfinput mobile"/>
	    </li>
	    <li>
	    	<label>所在地<b>*</b></label>
	    	<div class="usercity">
			    <div class="cityleft">
				    <select class="select2 combox1">
					    <#if fcities?exists && fcities?size gt 0>
							<#list fcities as ls>
								<option value="${(ls.id)?if_exists}" <#if (_city.pid)?exists && (_city.pid)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
							</#list>
						</#if>
				    </select>
			    </div>
			    <div class="cityright">
				    <select class="select2 cityid" name="cityid">
					   <#if cities?exists && cities?size gt 0>
							<#list cities as ls>
								<option value="${(ls.id)?if_exists}" <#if (_city.id)?exists && (_city.id)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
							</#list>
						</#if>
				    </select>
			    </div>
			</div>
	    </li>
	    <li>
	    	<label>家庭地址<b>*</b></label>
	    	<input type="text" value="${(account.profile.address)?if_exists}" class="dfinput address"/>
	    </li>
	    <li><label>&nbsp;</label><input type="button" class="btn" value="确认修改" onclick="javascript:submit();"/></li>
    </ul>
    
    </div>


</body>

</html>
