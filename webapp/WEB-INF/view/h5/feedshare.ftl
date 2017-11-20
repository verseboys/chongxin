<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${(feed.user.nickname)?default('游客')} 动态分享</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<link href="/assets/css/feedshare.css" rel="stylesheet">
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
	}
</script>
</head>

<body>
	<div class="all">
    	<div class="title">
        	<div class="left"><img src="${(feed.user.avatar)?default('/assets/img/dogimg.png')}" /></div>
            <div class="left2">
            	<div class="span1"><span>${(feed.user.nickname)?default('游客')}</span></div>
                <div class="span2"><span>${timeString?if_exists}</span></div>
            </div>
            <!--<div class="left left3"><img src="/assets/img/dogimg.png" height="50px" width="50px" style="border-radius:30px;"/></div>-->
        </div>
        <div class="img">
        	<#if picDtos?exists && picDtos?size gt 0>
        		<#list picDtos as ls>
					<img src="${(ls.photo)?if_exists}"/>
        		</#list>
        	</#if>
        	<#-- if picDto?exists>
        		<img src="${(picDto.photo)?if_exists}"/>
        	</#if -->
        </div>
        <#-- div class="imglist">
        	<#if picDtos?exists && picDtos?size gt 0>
        		<#list picDtos as ls>
        			<#if ls_index lt 4 >
						<img src="${(ls.photo)?if_exists}"/>
					</#if>
        		</#list>
        	</#if>
        </div -->
        <div class="content">
        	<span>${(feed.content)?if_exists}</span>
        </div>
        <#if zanUsers?exists && zanUsers?size gt 0>
	        <div class="attention">
	        	<div class="attentionimg">
	            	<img src="/assets/img/dianzan.png"/>
	            </div>
        		<#list zanUsers as ls>
        			<#if ls_index lt 7 >
						<img src="${(ls.avatar)?if_exists}"/>
					</#if>
        		</#list>
	            <div class="behind">
	            	<div class="behind1">${(feed.zancount)?if_exists}</div>
	            </div>
	        </div>
        </#if>
        <div class="line"></div>
        <div class="pinglun">
        	<div class="pinglun1">
            	<div class="img1"><img src="/assets/img/pinglun.png"/></div>
                <div class="content1"><span><#if (feed.commentcount)?exists && (feed.commentcount) gt 0>${(feed.commentcount)}<#else>评论</#if></span></div>
            </div>
            <div class="pinglun2">
            	<div class="img2"><img <#if (feed.iszan)?exists && (feed.iszan)==0>src="/assets/img/budianzan.png"<#else>src="/assets/img/dianzan.png"</#if>/></div>
                <div class="content1"><span>赞Ta</span></div>
            </div>
            <div class="pinglun3">
            	<div class="img3"><img src="/assets/img/fenxiang.png"/></div>
                <div class="content1"><span>分享</span></div>
            </div> 
        </div>
        <div class="line1"></div>
        <div id="bottom">
        	<div class="bot-left"><img src="/assets/img/logo1.png"/></div>
            <div class="bot-left2">
            	<div class="chongxin"><img src="/assets/img/chongxin.png"/></div>
                <div class="xiaobing"><span>下载宠信 和她聊聊</span></div>
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
