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
	<li><a href="/member/message?uid=${uid?if_exists}">消息</a></li>
	<li><a href="/member/message/details?uid=${uid?if_exists}&touid=${touid?if_exists}">聊天</a></li>
	<li><a href="javascript:;">查看聊天详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
	    <li>
	    	<label>聊天详情</label>
	    	<textarea cols="" rows="" class="textinput content1">${(message.content)?if_exists}</textarea>
	    </li>
    </ul>
    </div>
</body>

</html>
