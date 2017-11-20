<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script src="/assets/js/dog.js" type="text/javascript"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
});
</script>

</head>

<body>
    
    <div class="formbody formbody1">
    
    <div class="formtitle"><span>宠物信息</span></div>
    <ul class="forminfo">
	    <li id="avatar">
	    	<label>头像</label>
	    	<#if (dto.avatar)?exists&&(dto.avatar)!="">
				<img src="${(dto.avatar)?if_exists}" style="max-height:200px;max-width:200px;"/>
			<#else>
				<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
			</#if>
	    </li>
	    <li>
	    	<label>芯片号</label>
	    	<input readonly="true" type="text" class="dfinput" value="${(bean.blood)?if_exists}"/>
	    </li>
	    <li>
	    	<label>犬种<b>*</b></label>
	    	<div class="vocation">
			    <select class="select1 categoryId" name="categoryId">
				    <#if beans?exists && beans?size gt 0>
						<#list beans as ls><!--list循环-->
							<#if (bean.categoryId)?exists && (bean.categoryId)==ls.id><option value="${(ls.id)?if_exists}" selected="selected">${(ls.name)?if_exists}</option></#if>
						</#list>
					</#if>
			    </select>
			</div>
	    </li>
	    <li>
	    	<label>名字<b>*</b></label>
	    	<input readonly="true" type="text" class="dfinput name" value="${(bean.name)?if_exists}"/>
	    </li>
	    <li>
	    	<label>性别<b>*</b></label>
	    	<div class="vocation">
			    <select class="select1 sex" name="sex">
				    <#if (bean.sex)?exists && (bean.sex)==1><option value="1" selected="selected">男</option></#if>
					<#if (bean.sex)?exists && (bean.sex)==2><option value="2" selected="selected">女</option></#if>
			    </select>
			</div>
	    </li>
	    <li>
	    	<label>父亲芯片号</label>
	    	<input readonly="true" type="text" class="dfinput fatherBlood" id="fatherBlood" value="${(bean.fatherBlood)?if_exists}"/>
	    </li>
	    <li>
	    	<label>母亲芯片号</label>
	    	<input readonly="true" type="text" class="dfinput matherBlood" id="matherBlood" value="${(bean.matherBlood)?if_exists}"/>
	    </li>
	    <li>
	    	<label>肩高(cm)</label>
	    	<input readonly="true" value="${(bean.height)?default(0)}" type="text" class="dfinput height"  />
	    </li>
	    <li>
	    	<label>体重(kg)</label>
	    	<input readonly="true" value="${(bean.weight)?default(0)}" type="text" class="dfinput weight" />
	    </li>
	    <li>
	    	<label>出生日期<b>*</b></label>
	    	<input readonly="true" value="${(bean.birthdayStr)?if_exists}" type="text" class="dfinput" />
	    </li>
	    <li>
	    	<label>简介</label>
	    	<textarea readonly="true" cols="" rows="" class="textinput">${(bean.intro)?if_exists}</textarea>
	    </li>
    </ul>
    </div>
</body>

</html>
