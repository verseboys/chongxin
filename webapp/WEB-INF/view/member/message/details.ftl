<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/userui/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script type="text/javascript" src="/assets/js/chat.js"></script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
<script type="text/javascript">
$(function() {
	// 上传图片
	$("#content2_image").live("change",function() {
		var isImg = false;
		var file = $("#content2_image").val();
		var objtype = file.substring(file.lastIndexOf("."))
				.toLowerCase();
		var fileType = new Array(".png", ".gif", ".jpeg",
				".jpg");
		for ( var i = 0; i < fileType.length; i++) {
			if (objtype == fileType[i]) {
				isImg = true;
				break;
			}
		}
		if (isImg) {
			$.ajaxFileUpload({
				url : "/member/message/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'content2_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".content2").val(data.name);
						$("#content2 img").attr("src", data.name);
					}
				},
				error : function(data) {
					alert('加载出错');
				}
			});
		} else {
			alert("图片限于png,gif,jpeg,jpg格式");
		}
	});
});
</script>
</head>

<body>
	<div class="place">
    	<span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
			<li><a href="/member/message?uid=${uid?if_exists}">消息</a></li>
			<li><a href="/member/message/details?uid=${uid?if_exists}&touid=${touid?if_exists}">聊天</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
	    <div id="usual1" class="usual"> 
	    	<input value="${uid?if_exists}" class="uid" type="hidden"/>
	    	<input value="${touid?if_exists}" class="touid" type="hidden"/>
		    <div class="itab">
			  	<ul> 
				    <li><a href="#tab1" class="selected">发送文字</a></li> 
				    <li><a href="#tab2">发送图片</a></li> 
			  	</ul>
		    </div> 
		  	<div id="tab1" class="tabson">
			    <ul class="forminfo">
				    <li>
				    	<label>内容<b>*</b></label>
				    	<textarea cols="" rows="" class="textinput content1"></textarea>
				    </li>
			    	<li>
			    		<label>&nbsp;</label>
			    		<input type="button" class="btn btn1" value="发送"/>
			    	</li>
			    </ul>
	    	</div> 
	    
		  	<div id="tab2" class="tabson">
			    <ul class="forminfo">
			    	<li id="content2">
				    	<label>图片<b>*</b></label>
				    	<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				    	<input value="" class="content2" type="hidden"/>
				    	<input type="file" name="file" id="content2_image" value="" />
				    </li>
			    	<li>
			    		<label>&nbsp;</label>
			    		<input type="button" class="btn btn2" value="发送"/>
			    	</li>
			    </ul>
		    </div>  
		</div> 
    </div>
    
    <div class="rightinfo">
	    <table class="imgtable">
	    	<thead>
			    <tr>
				    <th width="100px">最新20条</th>
					<th></th>
					<th></th>
			    </tr>
		    </thead>
		    <tbody>
			    <#if beans?exists && beans?size gt 0>
					<#list beans as ls>
					    <tr>
						    <td class="imgtd" width="100px;">
								<#if (ls.profile.avatarStr)?exists && (ls.profile.avatarStr)!="">
									<img src="${(ls.profile.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
								<#else>
									<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
								</#if>
						    </td>
						    <td>
					    		<#if (ls.type)?exists && (ls.type)==1>
									<a href="/member/message/bigimg?uid=${uid?if_exists}&touid=${touid?if_exists}&img_url=${(ls.content)?if_exists}">
										<img src="${(ls.content)?if_exists}" style="height:60px;width:60px;"/>
									</a>
								<#else>
									<#if (ls.content)?exists &&(ls.content)?length gt 10>
										<a href="/member/message/chatcontent?uid=${uid?if_exists}&touid=${touid?if_exists}&id=${(ls.id)?if_exists}">
											${(ls.content)?if_exists[0..9]}...
										</a>
									<#else>
										${(ls.content)?if_exists}
									</#if>
								</#if>
						    </td>
						    <td>${(ls.date)?if_exists}</td>
					    </tr>
				    </#list>
				</#if>
		    </tbody>
	    </table>
    </div>
    <script type="text/javascript"> 
	  $("#usual1 ul").idTabs(); 
	</script>
	<script type="text/javascript">
		$('.imgtable tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
