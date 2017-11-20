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
<script src="/assets/js/weixin.js" ></script>
<style>
.ui-li-icon-chongxin{width:25px;height:25px}

.list_input{width:100%;border-bottom:1px solid #ddd;height:59px}
.span_left{float:left;width:30%;padding:15px 0px 0px 20px}
.span_right{float:left;}
.avatar{margin:0px auto;width:130px;border:0px solid #ccc;margin-top:20px}
.service_img{width:100%;text-align:center;padding:20px 0px 20px 0px;background:#fff}
.service_img img {     display: block;     height: auto;     width: 100%; }
.service_title{width:100%;background:#f2f2f2;border-top:0px solid #ccc;border-bottom:0px solid #ccc;line-height:50px;padding-left:50px}
.service_info{width:100%;line-height:50px;border-bottom:1px solid #ccc;clear:both;background:#fff}
.service_info .left{float:left;width:90px;text-align:right;color:#888;padding-right:10px}
.service_info .right{float:left}
.service_intro{width:100%;padding:10px 0px 10px 10px;line-height:25px;background:#fff;border-bottom:1px solid #ccc;}
</style>
<script>

function saveDog(){
	alert("saveDog");
	$.mobile.changePage("#mydogs");
}

$(document).on("change","#filesupload1",function(){ 
	var files = !!this.files ? this.files : []; 
	var file = files[0];
  	drawOnCanvas(file); 
});

</script>
</head>
 
<body>
<#if order?exists>
<div data-role="page" id="pagemain">
    <div class="service_img"><img src="/assets/img/weixin/${(order.state)?if_exists}.png"></div>
    <div class="service_title">服务信息</div>
    <div class="service_info">
    	<span class="left">订单号: </span>
    	<span class="right"> ${(order.orderid)?if_exists}</span>
    </div>
    <div class="service_info">
    	<span class="left">订单时间: </span>
    	<span class="right"> ${(order.created)?if_exists}</span>
    </div>
    <div class="service_info">
    	<span class="left">服务地点: </span>
    	<span class="right"><#if order.address.address?exists>${order.address.address}</#if></span>
    </div>
    <div class="service_info">
    	<span class="left">120医生: </span>
    	<span class="right"> <#if order.doctor?exists>${(order.doctor.job)?if_exists} &nbsp;&nbsp;${(order.doctor.name)?if_exists}<#else>正在为您选择合适医生......</#if></span>
    </div>
</div>
<#else>
<div data-role="page" id="pagemain">
	
	<div class="service_title">联系方式</div>
    <div class="service_info">
    	<span class="left">姓名: </span>
    	<span class="right"> <input type="text" value="<#if user?exists>${(user.nickname)?if_exists}</#if>" id="yuyue_name"></span>
    </div>
    <div class="service_info">
    	<span class="left">手机: </span>
    	<span class="right"> <input type="text" value="<#if user?exists>${(user.mobile)?default("")}</#if>" id="yuyue_mobile"></span>
    </div>
    <div class="service_info">
    	<span class="left">服务地点: </span>
    	<input type="hidden" id="yuyue_address" value="<#if user?exists>${(user.address)?default("")}</#if>">
    	<span class="right"> <a href="#pageaddress"><div id="server_address">
    	<#if user?exists>${(user.address)?default("未填写")}<#else>地址未填写</#if></div></a></span>
    </div>
    <div class="service_info"></div>
	<div class="service_title">宠信服务</div>
	 <div class="service_info"></div>
	<div class="service_intro">
		1、预约时间 8：00-22：00，120服务暂时只对杭州地区<br>
		2、紧急情况请拨打电话 <a href="tel:18058748997">18058748997</a><br>
		3、宠物80%的问题可以通过咨询得到解决，10%的症状需要120服务处理，10%的症状需要入院治疗<br>
	</div>
	<div data-role="main" class="ui-content">
   		 <button class="ui-btn" id="saveorderbutton">确定预约</button>
  	</div>
	
</div>
</#if>
<div data-role="page" id="pageaddress">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>地址</h1>
    	<a href="#pagemain" data-role="button" data-icon="check"  id="saveorderaddress">保存</a>
  	</div>
  	<div class="ui-field-contain">
        <input type="text" name="fullname" id="input_order_address" value="<#if user?exists>${(user.address)?if_exists}</#if>">       
    </div>
</div>
<div style="display:none;">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
</body>