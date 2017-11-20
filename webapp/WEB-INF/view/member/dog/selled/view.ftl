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
});
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/member/index/main">首页</a></li>
    <li><a href="/member/dog/selled">已售宠物</a></li>
    <li><a href="javascript:;">宠物详情</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    
    <ul class="forminfo">
	    <li id="avatar">
	    	<label>宠物头像</label>
	    	<#if (dto.avatar)?exists && (dto.avatar)!="">
				<img src="${(dto.avatar)?if_exists}" style="max-height:200px;max-width:200px;"/>
			<#else>
				<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
			</#if>
	    </li>
	    <li>
	    	<label>芯片号</label>
	    	<input type="text" value="${(bean.blood)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>犬种</label>
	    	<input type="text" value="${(bean.category.name)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>宠物名字</label>
	    	<input type="text" value="${(bean.name)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>性别</label>
	    	<div class="vocation">
			    <select class="select1 sex" name="sex">
				    <#if (bean.sex)?exists && (bean.sex)==1><option selected="selected" value="1">男</option></#if>
				<#if (bean.sex)?exists && (bean.sex)==2><option selected="selected" value="2">女</option></#if>
			    </select>
			</div>
	    </li>
	    <li>
	    	<label>父亲芯片号</label>
	    	<input type="text" value="${(bean.fatherBlood)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>母亲芯片号</label>
	    	<input type="text" value="${(bean.matherBlood)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>肩高(cm)</label>
	    	<input type="text" value="${(bean.height)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>体重(kg)</label>
	    	<input type="text" value="${(bean.weight)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>出生日期</label>
	    	<input type="text" value="${(bean.birthdayStr)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>拥有人</label>
	    	<input type="text" value="${(bean.owner.nickName)?if_exists}" readonly="readonly" class="dfinput"/>
	    </li>
	    <li>
	    	<label>简介</label>
	    	<textarea name="" cols="" rows="" class="textinput" readonly="readonly">${(bean.intro)?if_exists}</textarea>
	    </li>
    </ul>
    </div>


</body>

</html>
