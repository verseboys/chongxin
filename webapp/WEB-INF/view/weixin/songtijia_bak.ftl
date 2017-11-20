
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>送1个亿体检-宠信-宠物医疗健康管理专家</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<script src="/assets/jquerymobile/jquery-2.1.4.min.js"></script>
<style>
html {
    font-size: 12px;
    color: #666;
}
body {
    width: 100%;
    font-size: 1em;
    font-family: Microsoft YaHei,STHeiti STXihei,Microsoft JhengHei,Helvetica,Tohoma,Arial;
    margin: 0px;
    padding:0px; 
    background:url(/assets/img/weixin/songbg.jpg);    
}
.page{
	width:100%;
	height:100%;
}
.dximg {
	height: auto; 
	width: 100%; 
}
.all{
	width:90%;
	margin:0px auto;
}
.button{
	height:35px;
	margin-top:20px;
	background:#ffd72f;
	border-radius:5px;
	text-align:center;
	color:#fc2a33;
	font-size:20px;
	padding-top:10px;
}
.mobileinput{
	width:100%;
	height:40px;
	border:0px;
	font-size:20px;
	text-align:center;
	border-radius: 5px;
}
.guize{
	font-size:15px;
	color:#ffffff;
}
</style>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
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

function download(){
	var tel = $(".mobileinput").val(); //获取手机号
	var telReg = !!tel.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	if(telReg == false){
 		alert("请输入正确的手机号！");
 		return false;
	}
	
	//手机号码通过验证
	$.ajax({
		type : "POST",
		url : "/weixin/lijilingqu",
		data : {
			'tel' : tel
		},
		success : function(rt) {
			if($("#success")){
				$("div").remove("#success");
			}
			if($("#already")){
				$("div").remove("#already");
			}
			$(".mobile").after(rt);
		}
	});
}
</script>
</head>
 
<body>
	<div id="pagemain" class="page">
		<img src="/assets/img/weixin/song.jpg" class="dximg">
		<div class="all">
			<div class="mobile">
				<input type="text" class="mobileinput" maxlength="11" placeholder = "请输入手机号" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</div>
			<div class="button" onclick="download()">
				立即领取
			</div>
			<div class="guize">
				<p style="font-size:20px;">活动规则</p>
				<p>1.宠物免费体检，目前只限杭州地区，微信直接搜索<宠信>直接关注</p>
				<p>2.所有关注宠信微信号的爱狗人士都可以免费咨询狗狗生病、养殖、喂养、训练等众多问题。</p>
				<p>3.本次活动解释权归宠信所有</p>
			</div>
		</div>
	</div>
	<div style="display:none;">
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
	</div>
</body>
</html>