
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

<head>
<meta charset="UTF-8">
<title>宠信-宠物医疗健康管理专家</title>
<meta name="apple-touch-fullscreen" content="yes" />
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
}
body{margin:0px}
.dximg {display: block;height: auto; width: 100%; }
.downloadimg{position:absolute;z-index:100;top:1200px;left:160px;height:154;width:694px}
.page{width:100%;display:none;height:100%}
</style>
<script>

$(document).ready(function(){
 	var flag = isApple();
	if(flag =="ios"){
		//$("#pageios").show();
		location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chongxin.app&g_f=991653";
	}else if(flag  == "android"){
  		//$("#pageandroid").show();
  		location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chongxin.app";
	}else{
		$("#pagemain").show();
	}
});
function isApple() {
    var flag = "",
        osName = navigator.userAgent.split(";")[0].split('(')[1];
   // var browserName=navigator.userAgent.toLowerCase();

    if (osName == 'iPhone' || osName == 'iPad') {
        flag = "ios";
    }else if(osName == 'Linux'){
    	flag = "android";
    }
    return flag;
}
</script>
</head>
 
<body background="">
<div id="pagemain" class="page">
	<img src="/assets/img/weixin/wxiosdownload.jpg" class="dximg">
	<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chongxin.app"><img src="/assets/img/androiddownload.png" class="downloadimg"></a>
	</div>
</div>

<div  class="page" id="pageandroid">
	<img src="/assets/img/androidbg.jpg" class="dximg">
	<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.chongxin.app"><img src="/assets/img/androiddownload.png"  class="dximg"></a>
</div>

<div class="page" id="pageios">
	<img src="/assets/img/weixin/wxiosdownload.jpg" class="dximg">
</div>


<div style="display:none;">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
</body>