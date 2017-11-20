<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>专题 宠信 您身边的宠物健康专家</title>
<link href="/assets/css/subject.css" rel="stylesheet">
</head>
<body>
	<#include "common/top.ftl"/>
	<div class="all">
    	<div class="content">
            <div class="select01">
                <div class="banner"><img src="/assets/img/banner-2.png" width="1080px" height="600px" /></div>
                <div class="line"><span>精彩专题</span></div>
                <div>
                    <div style="float:left;"> <img src="/assets/img/hong.png" width="165px" height="6px"/></div>
                    <div style="float:left;"> <img src="/assets/img/jianbian.png" width="915px" height="6px;"/></div>
                </div>
                <div class="subject imglist">
                	<#if subjects?exists && subjects?size gt 0>
                		<#list subjects as ls>
                			<a href="/subject/${(ls.domain)?if_exists}"><img src="${(ls.logo)?if_exists}" class="subject_img" title="${(ls.name)?if_exists}"/></a>
                		</#list>
                	</#if>
                </div>
                <!--
                <div class="line"><span>合作犬舍</span></div>
                <div>
                    <div style="float:left;"> <img src="/assets/img/hong.png" width="165px" height="6px"/></div>
                    <div style="float:left;"> <img src="/assets/img/jianbian.png" width="915px" height="6px;"/></div>
                </div>
                <div class="company imglist">
                	<#if companies?exists && companies?size gt 0>
                		<#list companies as ls>
                			<a href="/doghouse/infor_${(ls.id)?if_exists}"><img src="${(ls.logo)?if_exists}" class="company_img"/></a>
                		</#list>
                	</#if>
                </div>
                
                <div class="line"><span>买家秀</span></div>
                <div>
                    <div style="float:left;"> <img src="/assets/img/hong.png" width="165px" height="6px"/></div>
                    <div style="float:left;"> <img src="/assets/img/jianbian.png" width="915px" height="6px;"/></div>
                </div>
                <div class="buyer imglist">
                    <img src="/assets/img/start.jpg" class="buyer_img"  width="1080px" height="550px"/>
                </div>
                -->
            </div>
        </div>
    </div>
    <div style="display:none;">
    	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
