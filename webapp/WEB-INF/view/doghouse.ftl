<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>犬舍</title>
<link href="/assets/css/doghouse.css" rel="stylesheet">
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
                        <div class="top">犬舍介绍</div>
                        <div class="qs-content">
                        	${(company.introduction)?if_exists}
                        </div>
                        <div class="qs-img"><img src="${(company.logo)?if_exists}" width="720px;" height="400px"/></div>
                        <div class="backg"></div>
                        <div class="top">狗狗推荐</div>
                        <div class="dog-img">
                        	<img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
                            <img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
                            <img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
                            <img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
                            <img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
                            <img src="/assets/img/dogimg.png" width="200px;" height="300px"/>
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
