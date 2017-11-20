<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	$("#avatar_image").live("change", function() {
		var isImg = false;
		var file = $("#avatar_image").val();
		var objtype = file.substring(file.lastIndexOf(".")).toLowerCase();
		var fileType = new Array(".png", ".gif", ".jpeg", ".jpg");
		for ( var i = 0; i < fileType.length; i++) {
			if (objtype == fileType[i]) {
				isImg = true;
				break;
			}
		}
		if (isImg) {
			$.ajaxFileUpload({
				url : "/server/upload/files/avatar?sid=${sessionid?if_exists}",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'avatar_image',
				success : function(rt) {
					if (rt.state == '0') {
						var url = rt.data[0].photo;
						$(".avatar").val(url);
						url = "${localimg?if_exists}"+url;
						$("#avatar img").attr("src", url);
					} else {
						var messge = rt.message;
						alert(messge);
					}
				}
			});
		}
	});
	
	function confirm(){
		var url = $(".avatar").val();
		if(url==""){
			alert("请上传头像");
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/server/user/update?sid=${sessionid?if_exists}",
			dataType : "json",
			contentType : "application/json",
			data : "{\"avatar\": \""+url+"\"}",
			success : function(rt) {
				if (rt.state == 0) {
					alert("头像修改成功！");
					window.location.href = "/member/index/main";
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
		    <li><a href="/member/setting/changeavatar">修改头像</a></li>
	    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle">
    	<span>基本信息</span>
    </div>
    
    <ul class="forminfo">
	    <li id="avatar">
	    	<label>头像<b>*</b></label>
	    	<#if (account.profile.avatarStr)?exists&&(account.profile.avatarStr)!="">
				<img src="${(account.profile.avatarStr)?if_exists}" style="max-height:200px;max-width:200px;"/>
			<#else>
				<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
			</#if>
	    	<input value="${(account.profile.avatarStr)?if_exists}" class="avatar" type="hidden"/>
	    	<input type="file" name="files" id="avatar_image" value="" />
	    </li>
	    <li><label>&nbsp;</label><input type="button" class="btn" value="确认修改" onclick="javascript:confirm();"/></li>
    </ul>
    
    </div>


</body>

</html>
