<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>联系方式</title>
<link href="/assets/css/contactway.css" rel="stylesheet">
<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>

</head>
	
<body>
	<#include "common/top.ftl"/>
	<div class="all">
    	<div class="content">  
            <div class="select01">
            	<div class="banner"><img src="${(company.banner)?if_exists}" width="100%" height="150px"/></div>
                <div class="nav">
                	<ul class="nav-menu">
                        <li class="menu-list-nav"><a class="nav-prod" href="/doghouse/infor_${(company.id)?if_exists}">首页</a></li>
                        <li class="menu-list-nav"><a class="nav-prod" href="#">犬种</a></li>
                        <li class="menu-list-nav"><a class="nav-prod" href="/doghouse/contactway_${(company.id)?if_exists}">联系方式</a></li>
                    </ul>
                </div>
                <div class="details">
                	<div class="zxzx">
                    	<div>
                            <div class="fzzx"><span>${(company.name)?if_exists}</span></div>
                            <div class="zxxq">
                            	<p>联系电话：&nbsp;${(company.telephone)?if_exists}</p>
                                <p>营业时间：&nbsp;${(company.open_time)?if_exists}</p>
                                <p>所在城市：&nbsp;${(company.district)?if_exists}</p>
                            </div>
                            <div class="zhixun">在线咨询</div>
                        </div>
                    </div>
                    <div class="qsjs">
                        <div class="top">联系方式</div>
                        <div class="qs-content">
                        	<p>联系电话：&nbsp;${(company.telephone)?if_exists}</p>
                            <p>营业时间：&nbsp;${(company.open_time)?if_exists}</p>
                            <p>所在城市：&nbsp;${(company.district)?if_exists}</p>
                            <p>地址：&nbsp;${(company.address)?if_exists}</p>
                        </div>
                        <div class="backg"></div>
                        <div class="top">地图</div>
                        <div class="dog-img">
                        	<div id="allmap" style="width:727px;height:483px;">
                        		
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="display:none;">
    	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	var point = new BMap.Point(${(company.longtitudeStr)?if_exists}, ${(company.latitudeStr)?if_exists});
	map.centerAndZoom(point, 16);  // 初始化地图,设置中心点坐标和地图级别
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
</script>
