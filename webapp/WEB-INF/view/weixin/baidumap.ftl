<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {
			width: 100%;
			height: 100%;
			overflow: hidden;
			margin:0;
			font-family:"微软雅黑";
		}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	<title>${(company.name)?if_exists}</title>
</head>
<body>
	<div id="allmap"></div>
	<div style="display:none;">
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
	</div>
</body>
</html>
<script type="text/javascript">

	var map = new BMap.Map("allmap");    // 创建Map实例
	var point = new BMap.Point(${(company.longtitude)?if_exists}, ${(company.latitude)?if_exists});
	map.centerAndZoom(point, 16);  // 初始化地图,设置中心点坐标和地图级别
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
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
</script>
