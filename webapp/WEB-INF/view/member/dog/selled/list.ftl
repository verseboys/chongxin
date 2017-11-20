<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>

<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="/member/index/main">首页</a></li>
		    <li><a href="/member/dog/selled">已售宠物</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <table class="imgtable">
	    	<thead>
			    <tr>
				    <th width="100px">宠物头像</th>
					<th >宠物名字</th>
					<th >宠物主人</th>
					<th >创建时间</th>
			    </tr>
		    </thead>
		    <tbody>
			    <#if beans?exists && beans?size gt 0>
					<#list beans as ls>
					    <tr>
						    <td class="imgtd">
						    	<#if (ls.dog.avatarStr)?exists&&(ls.dog.avatarStr)!="">
									<img src="${(ls.dog.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
								<#else>
									<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
								</#if>
						    </td>
						    <td>
						    	<a href="/member/dog/view?id=${(ls.dogid)?if_exists}">${(ls.dog.name)?if_exists}</a>
						    </td>
						    <td>
						    	<a href="/member/dog/owner?id=${(ls.toUid)?if_exists}">${(ls.now.nickName)?if_exists}</a>
						    </td>
						    <td>${(ls.transtimeStr)?if_exists}</td>
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
