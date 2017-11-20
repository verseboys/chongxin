<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/js/delete.js" type="text/javascript"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<style>
.operation{float:left;width:35px;text-align:left}
</style>
</head>

<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
		    <li><a href="/member/message?uid=${uid?if_exists}">消息</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <table class="imgtable">
	    	<thead>
			    <tr>
				    <th width="100px">消息人</th>
					<th >消息内容</th>
					<th >消息时间</th>
			    </tr>
		    </thead>
		    <tbody>
			    <#if beans?exists && beans?size gt 0>
					<#list beans as ls>
					    <tr>
						    <td class="imgtd">
						    	<a href="/member/message/details?uid=${uid?if_exists}&touid=${(ls.profile.id)?if_exists}" title="与 ${(ls.profile.nickName)?if_exists} 的对话">
									<#if (ls.profile.avatarStr)?exists && (ls.profile.avatarStr)!="">
										<img src="${(ls.profile.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
									<#else>
										<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
									</#if>
								</a>
								
						    </td>
						    <td>
						    	${(ls.profile.nickName)?if_exists}
						    	<p>
						    		最新消息：
						    		<#if (ls.type)?exists && (ls.type)==1>
										[图片]
									<#else>
										<#if (ls.content)?exists &&(ls.content)?length gt 10>
											${(ls.content)?if_exists[0..9]}...
										<#else>
											${(ls.content)?if_exists}
										</#if>
									</#if>
						    	</p>
						    </td>
						    <td>${(ls.date)?if_exists}</td>
					    </tr>
				    </#list>
				</#if>
		    </tbody>
	    </table>
	    <div class="pagin">
	    	${page?if_exists}
	    </div>
    </div>
    
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
</script>
</body>

</html>
