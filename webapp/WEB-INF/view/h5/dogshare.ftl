<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${(dogDto.name)?default('宠信')} - ${(dog.owner.nickName)?default('宠信')}</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<link href="/assets/css/dogshare.css" rel="stylesheet">
<script src="/assets/js/jweixin-1.0.0.js"></script>
<script src="/assets/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
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
		window.open("/download");
	};
	function feeddetail(uid,feedid){
		window.open("/feed/share?uid="+uid+"&feedid="+feedid);
	}
</script>
</head>
<body>
	<div style="with:100%;height:100%;">
		<div class="bg">
			<img class="first" src="/assets/img/dogback.jpg"/>
			<img class="second" src="/assets/img/dogbigbg.png"/>
			<img class="third" src="${(dogDto.avatar)?default('/assets/img/dogdefaultbg.png')}"/>
			<div>
				<div>${(dogDto.name)?default('宠信')}</div>
				<div>
					<img src="<#if (dogDto.sex)?exists&&(dogDto.sex)==2>/assets/img/sex_2.png<#else>/assets/img/sex_1.png</#if>" style="width:10%;"/>
				 	<span>${birth?default('刚刚')} </span>
					<span style="margin-top:20%;">| 被赞 ${(dogDto.zan)?default(0)}</span>
				</div>
			</div>
		</div>
		<div class="all">
			<#if recordDtos?exists&&recordDtos?size gt 0>
				<div class="records">
					<#list recordDtos as ls>
						<div class="record">
							<div class="time">
								<img src="/assets/img/dogtime.png"/>
								<div><span>${(ls.record_time)?default(0)}</span></div>
							</div>
							<#if (ls.isFeed)?exists&&(ls.isFeed)==1>
								<div class="kuang" style="background:url(/assets/img/dogfeed.png);background-size:100% 100%;" onclick="feeddetail(${uid?default(0)},${(ls.feed.fid)?default(0)})">
									<#if (ls.feed.photos)?exists&&(ls.feed.photos)?size gt 0>
										<#list (ls.feed.photos) as lss>
											<#if lss_index == 0>
												<img class="feedimg" src="${(lss.photo)?default('/assets/img/logo1.png')}" />
											<#elseif lss_index gt 0 && lss_index lt 3>
												<img class="feedimg" src="${(lss.photo)?default('/assets/img/logo1.png')}" />
											</#if>
										</#list>
									</#if>
								</div>
							<#else>
								<div class="kuang" style="padding-left:10%;">
									<#if (ls.typeid)?exists&&(ls.typeid) lt 17>
										<img class="classify" src="/assets/img/dogclassify_${ls.typeid}.png"/>
									<#else>
										<img class="classify" src="/assets/img/dogclassify_other.png"/>
									</#if>
									<div>
										<span class="classifyname">${(ls.recordtype)?if_exists}</span>
										<span>实施时间: ${(ls.record_time)?if_exists}</span>
									</div>
								</div>
							</#if>
						</div>
					</#list>
				</div>
			</#if>
	    </div>
	    <div id="bottom">
	    	<div class="bot-left"><img src="/assets/img/logo1.png"/></div>
	        <div class="bot-left2">
	        	<div class="chongxin"><img src="/assets/img/chongxin.png"/></div>
	            <div class="xiaobing"><span>下载宠信，聊聊它</span></div>
	        </div>
	        <div class="bot-left3" onclick="download()">
	            <div class="download"><img src="/assets/img/lijixizai.png"/></div>
	        </div>
	    </div>
	</div>
    <div style="display:none;">
    	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
