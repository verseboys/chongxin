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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<!--<script src="/assets/js/weixin.js" ></script>-->

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
function setpartnerid(_partnerid){
	$("#partnerid").val(_partnerid);
};
$(document).on("pagebeforeshow", "#partnerinfor", function() {
	 partnerinfo();
});
function partnerinfo() {
	
	var partnerid = $("#partnerid").val();
	$.ajax({
		type : "post",
		url : "/weixin/loadpartner?partnerid=" + partnerid, // 要访问的后台地址
		success : function(msg) { // msg为返回的数据，在这里做数据绑定
			$("#partnerinfordiv").html(msg);
		}
	});
};

function change(_this){
	$("._tab").each(function(){
		$(this).removeClass("ui-btn-active"); 
	});
	$(_this).addClass("ui-btn-active");
	
	$(".div-block").each(function(){
		$(this).css("display","none"); 
	});
	var name = $(_this).parent().attr('class');
	name = "#div"+name;
	$(name).css("display","block");
};
</script>
</head>
 
<body>
	<div data-role="page" id="partners">
		<div data-role="header">
	    	<h1>合作伙伴</h1>
	  	</div>
	  	<div data-role="main" class="ui-content">
	  		<input type="hidden" value="" id="partnerid"/>
		   	<ul data-role="listview" id="partnersul">
		   		<#if companyDtos?exists&&companyDtos?size gt 0>
		   			<#list companyDtos as ls>
		   				<li class="ui-li-has-thumb">
				   			<a href="#partnerinfor" onclick="setpartnerid(${(ls.id)?default(0)})" style="height:80px;" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
				   				<img src="<#if (ls.logo)?exists&&(ls.logo)!= ''>${ls.logo}<#else>/assets/img/logo1.png</#if>" style="margin:20px 0px 10px 10px;"/>
				   				<div style="height:100%;padding-top:5%;">
				   					<div style="height:30%;">${(ls.name)?if_exists}</div>
				   					<div style="height:30%;font-size:12px;">地区：${(ls.district)?if_exists}</div>
				   					<div style="height:30%;font-size:12px;">电话：${(ls.telephone)?if_exists}</div>
				   				</div>
							</a>
						</li>
		   			</#list>
		   		</#if>
			</ul>
	    </div>
	</div>

	<div data-role="page" id="partnerinfor">
		<div data-role="main" class="ui-content" id="partnerinfordiv">
			
		</div>
	</div>

	<div style="display:none;">
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
	</div>
</body>
</html>