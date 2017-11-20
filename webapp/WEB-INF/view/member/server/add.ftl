<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script src="/assets/js/servers.js" type="text/javascript"></script>

</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/member/index/main">首页</a></li>
    <li><a href="/member/servers">服务</a></li>
    <li><a href="javascript:;">添加</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form id="servers-form" action="/member/servers/submit" method="post">
	    <ul class="forminfo">
		    <li>
		    	<label>服务类型<b>*</b></label>
		    	<div class="usercity">
				    <div class="cityleft">
					    <select class="select2 combox1">
						    <#if classifies?exists && classifies?size gt 0>
								<#list classifies as ls>
									<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
								</#list>
							</#if>
					    </select>
				    </div>
				    <div class="cityright">
					    <select class="select2 classifyid" name="classifyid">
						   <#if classifies2?exists && classifies2?size gt 0>
								<#list classifies2 as ls>
									<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
								</#list>
							</#if>
					    </select>
				    </div>
				</div> 
		    </li>
		    <li>
		    	<label>价格<b>*</b></label>
				<input type="text" name="price" class="dfinput price" style="float:left;" placeholder="正整数"/>
				<div class="vocation" style="float:left;">
				    <select class="select3 units" name="units">
					    <option value="元/次" selected="selected">元/次</option>
						<option value="元/天">元/天</option>
				    </select>
				</div>
		    </li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    
    </div>
	<script type="text/javascript">
		$(document).ready(function(e) {
			$(".select2").uedSelect({
				width : 167  
			});
			$(".select3").uedSelect({
				width : 70  
			});
		});
	</script>
</body>

</html>
