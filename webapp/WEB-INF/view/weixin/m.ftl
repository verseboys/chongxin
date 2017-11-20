<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>宠信</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
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
.ui-li-icon-chongxin{width:25px;height:25px}
.list_input{width:100%;border-bottom:1px solid #ddd;height:59px}
.span_left{float:left;width:30%;padding:15px 0px 0px 20px}
.span_right{float:left;}
.avatar{margin:0px auto;width:130px;border:0px solid #ccc;margin-top:20px}
</style>
<script>
wx.config({    
debug: false,    
appId: 'wxee35af4db784f721',    
timestamp: 1421142450,    
nonceStr: 'ichongxink9svcom',    
signature: 'bf7a5555f9ad0e7e491535f232349a40510a6f8f',    
jsApiList: [    
'checkJsApi',    
'onMenuShareTimeline',    
'onMenuShareAppMessage',    
'onMenuShareQQ',    
'onMenuShareWeibo',    
'hideMenuItems',    
'showMenuItems',    
'hideAllNonBaseMenuItem',    
'showAllNonBaseMenuItem',    
'translateVoice',    
'startRecord',    
'stopRecord',    
'onRecordEnd',    
'playVoice',    
'pauseVoice',    
'stopVoice',    
'uploadVoice',    
'downloadVoice',    
'chooseImage',    
'previewImage',    
'uploadImage',    
'downloadImage',    
'getNetworkType',    
'openLocation',    
'getLocation',    
'hideOptionMenu',    
'showOptionMenu',    
'closeWindow',    
'scanQRCode',    
'chooseWXPay',    
'openProductSpecificView',    
'addCard',    
'chooseCard',    
'openCard'    
]    
});

function saveDog(){
	alert("saveDog");
	$.mobile.changePage("#mydogs");
}

$(document).on("change","#filesupload1",function(){ 
	var files = !!this.files ? this.files : []; 
	var file = files[0];
  	drawOnCanvas(file); 
});


function showavatr(file){
	var reader = new FileReader();
    reader.readAsDataURL(file);
	 reader.onloadend = function(){
    	var html = "<img src=\""+this.result+"\" width=130 height=130>";
		$("#avatar").html(html);
    }
}

</script>
</head>
 
<body>
<div data-role="page" id="pagemain">
	<div style="text-align:center;padding-top:20px;height:160px">
	 <img src="<#if user?exists>${user.avatar?default("/assets/img/no_avatar.jpg")}<#else>http://image.k9sv.com/avatar-2015101422433446319_1242-1242.jpg</#if>"
	  style="width:120px;height:120px" class="ui-corner-all">
    </div >
    <ul data-role="listview">
      <li><a href="#mydogs"><img src="/assets/img/weixin/wode_icon_dog.png" class="ui-li-icon">我的宠物</a></li>
      <li><a href="#myprofile"><img src="/assets/img/weixin/wode_icon_profile.png" class="ui-li-icon">我的资料</a></li>
      <li><a href="#myyuyue"><img src="/assets/img/weixin/wode_icon_friend.png" class="ui-li-icon">我的预约</a></li>
    </ul>
</div>
<#include "m-user.ftl"/>
<#include "m-dog.ftl"/>
<div data-role="page" id="myyuyue">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>预约</h1>
    </div>
   
</div>

<div data-role="page" id="myfriend">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>我的资料</h1>
    </div>
    	<div id="contentHolder">       
			 <video id="video" width="320" height="320" autoplay>
			</video>       
			 <button id="snap" style="display:none"> 拍照</button>        
			<canvas style="display:none" id="canvas" width="320" height="320">
			</canvas> 
		</div>
</div>

<div style="display:none;">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
</body>