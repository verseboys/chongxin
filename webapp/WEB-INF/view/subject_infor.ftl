<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${(subject.name)?if_exists} 宠信 您身边的宠物健康专家</title>
<link href="/assets/css/subject_infor.css" rel="stylesheet">
</head>
	
<body>
	<#include "common/top.ftl"/>
	<div class="all">
    	<div class="content">  
            <div class="select01">
                <div class="top">
                	<div class="f-left">
                    	<div class="weizhi">首页>主题>${(subject.name)?if_exists}</div>
                        <div class="text">
                            <div class="title">${(subject.name)?if_exists}</div>
                            <div class="text-content">
                                ${(subject.summary)?if_exists}
                            </div>
                        </div>
                    </div>
                    <div class="f-midd"><img src="${(subject.logo)?if_exists}" width="470px" height="380px"/></div>
                    <div class="f-right"></div>
                </div>
                <div class="top-bottom"></div>
                <div class="line"></div>
                <div class="bottom">
                	<div class="left-content">
                		<div class="text-content">
                			${(subject.content)?if_exists}
                		</div>
                	</div>
                    <div class="right-content">
                    	<!--
                    	<div class="other1">
                        	<div class="span"><span>在线咨询</span></div>
                            <div class="details">
                            	<div class="name"><span>${(subject.name)?if_exists}</span></div>
                                <div class="xuxian">
                                	<span>-------------------------------------------</span>
                                </div>
                                <div class="main">
                                	<p>犬舍名称：&nbsp;杭州金盾犬舍</p>
                                    <p>犬舍地址：&nbsp;杭州余杭区</p>
                                    <p>犬舍电话：&nbsp;0571-88058079</p>
                                    <p>种犬价格：&nbsp;0571-88056905</p>
                                </div>
                                <div class="xuxian">
                                	<span>-------------------------------------------</span>
                                </div>
                                <div class="button">
                                	<div class="buttonlogo1">预定</div>
                                    <div class="buttonlogo2">咨询</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="other1">
                        	<div class="span"><span>推荐阅读</span></div>
                          <div class="details">
                            	<div class="ul">
                                   <ul>
                                   		<li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                   </ul>
                                </div>
                            </div>
                        </div>
                        <div class="other1">
                        	<div class="span"><span>宠信咨询</span></div>
                            <div class="details">
                            	<div class="ul">
                                   <ul>
                                   		<li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                        <li>狗狗掉毛很厉害怎么办？</li>
                                   </ul>
                                </div>
                            </div>
                        </div>
                        -->
                        <div class="other2">
                        	<div class="span2">
                            	<span>扫一扫，关注宠信公共号查看更多内容</span>
                                <div class="qrcode">
                                	<img src="/assets/img/qrcode.jpg" width="200px" height="200px"/>
                                </div>
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
