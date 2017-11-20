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
<script src="/assets/js/dog.js" type="text/javascript"></script>

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
		    <li><a href="/member/dog">我的宠物</a></li>
		    <li><a href="javascript:;">添加</a></li>
	    </ul>
    </div>
    
    <div class="formbody formbody1">
	    <div class="formtitle"><span>基本信息</span></div>
	    <form method="post" action="/member/dog/submit" id="dog-form">
	    	<input type="hidden" name="createdStr1"  value="${created?if_exists}">
	    	<input type="hidden" name="update1"  value="${created?if_exists}">
			<input type="hidden" name="petid"  value="0" id="petid">
		    <ul class="forminfo">
			    <li id="avatar">
			    	<label>头像</label>
			    	<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
			    	<input name="avatar" value="" class="avatar" type="hidden"/>
			    	<input type="file" name="file" id="avatar_image" value="" />
			    </li>
			    <li>
			    	<label>芯片号</label>
			    	<input name="blood" type="text" class="dfinput"/>
			    </li>
			    <li>
			    	<label>犬种<b>*</b></label>
			    	<div class="vocation">
					    <select class="select1 categoryId" name="categoryId">
						    <#if beans?exists && beans?size gt 0>
								<#list beans as ls>
									<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
								</#list>
							</#if>
					    </select>
					</div>
			    </li>
			    <li>
			    	<label>名字<b>*</b></label>
			    	<input name="name" type="text" class="dfinput name" />
			    </li>
			    <li>
			    	<label>性别<b>*</b></label>
			    	<div class="vocation">
					    <select class="select1 sex" name="sex">
						    <option value="1" selected="selected">男</option>
							<option value="2">女</option>
					    </select>
					</div>
			    </li>
			    <li>
			    	<label>父亲芯片号</label>
			    	<input name="fatherBlood" type="text" class="dfinput fatherBlood" id="fatherBlood"/>
			    	<input type="button" class="scbtn" value="查询" onclick="javascript:querydog(2);">
			    </li>
			    <li>
			    	<label>母亲芯片号</label>
			    	<input name="matherBlood" type="text" class="dfinput matherBlood" id="matherBlood"/>
			    	<input type="button" class="scbtn" value="查询" onclick="javascript:querydog(3);">
			    </li>
			    <li>
			    	<label>肩高(cm)</label>
			    	<input name="height" value="0" type="text" class="dfinput height"/>
			    </li>
			    <li>
			    	<label>体重(kg)</label>
			    	<input name="weight" value="0" type="text" class="dfinput weight" />
			    </li>
			    <li>
			    	<label>出生日期<b>*</b></label>
			    	<input id="d12" name="birthday1" type="text" class="dfinput birthday1" readonly="readonly"/>
			    	<img onclick="WdatePicker({el:$dp.$('d12')})" src="/assets/My97DatePicker/skin/datePicker.gif" width="30" height="40" align="absmiddle"/>
			    </li>
			    <li>
			    	<label>简介</label>
			    	<textarea name="intro" cols="" rows="" class="textinput"></textarea>
			    </li>
			    <li>
			    	<label>&nbsp;</label>
			    	<input type="button" class="btn" value="确认保存"/>
			    </li>
		    </ul>
	    </form>
    </div>
    <div class="rightinfo formbody2" style="display:none;">
	    
    </div>
    <div class="rightinfo formbody3" style="display:none;">
	    
    </div>
</body>

</html>
