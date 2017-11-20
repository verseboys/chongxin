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
    <li><a href="/member/attention">关注</a></li>
    <li><a href="javascript:;">关注详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
	    <li>
	    	<label>头像</label>
	    	<#if (dto.avatar)?exists && (dto.avatar)!="">
				<img src="${(dto.avatar)?if_exists}" style="max-height:200px;max-width:200px;"/>
			<#else>
				<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
			</#if>
	    </li>
	    <li>
	    	<label>昵称</label>
	    	<input type="text" value="${(bean.profile.nickName)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
    </ul>
    
    </div>


</body>

</html>
