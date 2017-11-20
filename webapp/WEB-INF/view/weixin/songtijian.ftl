<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>养宠也有补贴了，宠信1个亿体检免费送给养宠用户</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<meta name="keywords" content="免费体检 免费宠物体检 宠物体检 宠信 宠信免费 宠信免费体检" />
<meta name="description" content="宠物行业的第一次大规模补贴，规模有点吓人"/> 

<script src="/assets/jquerymobile/jquery-2.1.4.min.js"></script>
<script src="/assets/js/jquery-cookie.js"></script>
<script src="/assets/js/activity.js"></script>

<style>
html {
    font-size: 16px;
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
.dximg1 {
	height: auto; 
	width: 50%; 
}
.all{
	width:90%;
	margin:0px auto;
	text-align:center;
}
.all p{
	color:#ffd830;
	line-height:30px
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
	width:90%;
	height:40px;
	border:0px;
	font-size:20px;
	text-align:center;
	border-radius: 5px;
}
.guize{
	font-size:15px;
	color:#ffffff;
	text-align:left;
}
</style>
<script>

</script>
</head>
 
<body>
	<div id="pagemain" class="page">
		<img src="/assets/img/weixin/song.jpg" class="dximg">
		<div class="all">
			<#if activityRecord?exists>
			<div class="already">
				<img src="/assets/img/weixin/1000.png" class="dximg1">
				<p>
					您已经领取1000元体检大礼包<br>
					关注公众号，体检时间地点通过公众号通知
				</p>
				<img src="/assets/img/weixin/2wei.png" class="dximg1">
			</div>
			<#else>
			<div class="noalready" id="noalreadyhtml">
				<div class="mobile">
					<input type="text" class="mobileinput" maxlength="11" placeholder = "请输入手机号" onkeyup="this.value=this.value.replace(/\D/g,'')">
				</div>
				<div class="button" onclick="baoming(1)">
					立即领取
				</div>
				<div class="guize">
					<p style="font-size:20px;">活动规则</p>
					<p>1.宠物免费体检，微信直接搜索《宠信》直接关注</p>
					<p>2.打车补贴、吃饭补贴，养狗终于也有人补贴了</p>
					<p>3.本次活动解释权归宠信所有</p>
				</div>
			</div>
			</#if>
		</div>
	</div>
	<div style="display:none;">
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
		<div class="already" id="alreadyhtml">
			<img src="/assets/img/weixin/1000.png" class="dximg1">
			<p>
				您已经领取1000元体检大礼包<br>
				请关注公众号，时间、地点通过公众号通知
			</p>
			<img src="/assets/img/weixin/2wei.png" class="dximg1">
		</div>
	</div>
</body>
</html>