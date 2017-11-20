<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/assets/js/warranty.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
});
</script>

</head>

<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
		    <li><a href="/member/warranty">保单</a></li>
		    <li><a href="javascript:;">添加</a></li>
	    </ul>
    </div>
    
    <div class="formbody formbody1">
	    <div class="formtitle"><span>基本信息</span></div>
	    <form method="post" action="/member/warranty/submit" id="warranty-form">
	    	<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		    <ul class="forminfo">
			    <li>
			    	<label>宠物芯片号<b>*</b></label>
			    	<input name="blood" type="text" class="dfinput blood" value="${(bean.blood)?if_exists}"/>
			    	<b class="error1" style="display:none;">芯片号不能为空！</b><b class="error2" style="display:none;">芯片号不正确，或宠物信息还未录入！</b>
			    </li>
			    <li>
			    	<label>价格<b>*</b></label>
			    	<input name="price" placeholder="正整数" value="${(bean.price)?default(0)}" type="text" class="dfinput price" />
			    </li>
			    <li>
			    	<label>&nbsp;</label>
			    	<input type="button" class="btn" value="确认保存"/>
			    </li>
		    </ul>
	    </form>
    </div>
</body>

</html>
