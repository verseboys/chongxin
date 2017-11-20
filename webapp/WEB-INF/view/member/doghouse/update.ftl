<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/userui/kindeditor-4.1.7/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="/assets/userui/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script src="/assets/js/doghouse.js" type="text/javascript"></script>
<script type="text/javascript">
    KindEditor.ready(function(K) {
            window.EditorObject = K.create('#content7', {
                    resizeType : 2,
                    uploadJson : '/upload/newsImage/member_upload'
            });
    });
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/member/index/main">首页</a></li>
    <li><a href="/member/doghouse/update">信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form method="post" action="/member/doghouse/submit" id="doghouse-form">
    	<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
    	<input type="hidden" name="createdStr"  value="${(bean.createdStr)?if_exists}">
	    <input type="hidden" name="deleted"  value="${(bean.deleted)?if_exists}">
		<input type="hidden" name="type" value="${(bean.type)?if_exists}">
		<input type="hidden" name="notice" value="${(bean.notice)?if_exists}">
		<#if (bean.longtitudeStr)!=""&&(bean.latitudeStr)!="">
			<input type="hidden" value="${(bean.longtitudeStr)?if_exists}" name="longtitude" id="longtitude_update"/>
			<input type="hidden" value="${(bean.latitudeStr)?if_exists}" name="latitude" id="latitude_update"/>
		<#else>
			<input type="hidden" value="0" name="longtitude" id="longtitude_update"/>
			<input type="hidden" value="0" name="latitude" id="latitude_update"/>
		</#if>
	    <ul class="forminfo">
	    	<li>
		    	<label>店铺名称<b>*</b></label>
		    	<input name="name" type="text" value="${(bean.name)?default('${(account.profile.nickName)?if_exists}的宠信店')}" class="dfinput name"/>
		    </li>
		    <li>
		    	<label>手机或电话<b>*</b></label>
		    	<input name="telephone" type="text" value="${(bean.telephone)?default('${(account.profile.mobile)?if_exists}')}" class="dfinput telephone" placeholder="010-88888888"/>
		    </li>
		    <li>
		    	<label>地址<b>*</b></label>
		    	<input name="address" type="text" value="${(bean.address)?default('${(account.profile.address)?if_exists}')}" class="dfinput address"/>
		    </li>
		    <li>
		    	<label>所在地区</label>
		    	<input name="district" type="text" value="${(bean.district)?if_exists}" class="dfinput"/>
		    </li>
		    <li>
		    	<label>营业时间</label>
		    	<input name="open_time" type="text" value="${(bean.open_time)?if_exists}" class="dfinput"/>
		    </li>
		    <!--
		    <li id="banner">
		    	<label>banner</label>
		    	
		    	<#if (bean.banner)?exists&&(bean.banner)!="">
					<img src="${(bean.banner)?if_exists}" style="max-height:200px;max-width:200px;"/>
				<#else>
					<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				</#if>
		    	<input name="banner" value="${(bean.banner)?if_exists}" class="banner" type="hidden"/>
		    	<input type="file" name="file" id="banner_image" value="" />
		    </li>
		    -->
		    <li>
		    	<label>犬舍介绍</label>
		    	<textarea id="content7" name="introduction" style="width:700px;height:250px;">
		    		${(bean.introduction)?if_exists}
		    	</textarea>
		    </li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    </div>
</body>

</html>
