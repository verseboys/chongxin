
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
    <title>宠信客服系统</title>
    <meta charset="utf-8">
    <meta name="apple-touch-fullscreen" content="yes">
    <link rel="stylesheet" href="/assets/jquerymobile/jquery.mobile-1.4.5.min.css">
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/assets/jquerymobile/jquery-2.1.4.min.js"></script>
<script src="/assets/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
<script src="/assets/js/jquery-cookie.js"></script>
<script src="/assets/js/ajaxfileupload.js" ></script>
<script src="/assets/js/weixin.js" ></script>
<script src="/assets/js/exif.js" ></script>
<script src="/assets/js/binaryajax.js" ></script>
<style>
.userleft{float:left;text-align:left;width:100%;line-height:35px}
.meright{text-align:right;width:100%;;line-height:35px}
</style>
<script>
function showIM(uid){
	$.mobile.changePage("#pageIM");
	$("#imcontent").val(uid);
}
</script>
</head>
</head>
<body>

<div data-role="page" id="pagemain">
	<div style="text-align:center;padding-top:10px;height:30px">
	 客服系统
    </div >
    <ul data-role="listview" data-inset="true">
    	<#list msgs as ls>
      	<li id="list_${ls.profile.id}"><a href="#" onclick="showIM(${ls.profile.id})"><h2>${ls.profile.nickName!}</h2><p>
      		<#if ls.type==0>
      		${ls.content}
      		<#elseif ls.type == 1>
      		[图片]
      		</#if>
      		</p></a>
      	</li>
		</#list>
    </ul>
</div>

<div data-role="page" id="pageIM">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>预约</h1>
    </div>
    <div data-role="main" class="ui-content">
    	<div class="userleft">
    	<#--img src="/assets/img/no_avatar.jpg" style="width:30px;height:30px" class="ui-corner-all"-->
    		吴玉启：阿斯顿发送到发送到发送到发送到发送到发送到发送到发送到发
   	 	</div>
   	 	<div class="meright">
    		阿斯顿发送到发送到发送到发送到发送到发送到发送到发送到发 ：我
   	 	</li>
    </div>
    <div data-role="footer" data-position="fixed"  style="text-align:center">
	    <input type="text" name="lname" id="imcontent">
        <a href="#" class="ui-btn" data-inline="true" style="width:50%">发 送</a>
  	</div>
</div>
</body>
</html>