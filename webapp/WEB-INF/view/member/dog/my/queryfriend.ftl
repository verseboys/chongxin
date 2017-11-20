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
	
function query(){
	$("#friendform").submit();
}

function transsubmit(_dogid,_touid){
	$.ajax({
		type : "POST",
		url : "/member/dog/trans/submit",
		data : {
			'dogid' : _dogid,
			'touid' : _touid
		},
		success : function(rt) {
			window.opener.location.href=window.opener.location.href;
			window.close();
		}
	});
}
</script>
<style>
.operation{float:left;width:35px;text-align:left}
</style>
</head>

<body>
	<form method="post" id="friendform" action="/member/dog/queryfriend" enctype="multipart/form-data">
		<input type="hidden" name="dogid"  value="${dogid?default(0)}" id="petid">
		<input type="hidden" name="pageNum"  value="1">
		<input type="hidden" name="numPerPage"  value="${numPerPage?default(20)}">
		<div id="tab1" class="rightinfo">
			<ul class="seachform">
			    <li><label>好友昵称</label><input name="title" type="text" class="scinput" value="${title?if_exists}"/></li>
			    <li><label>&nbsp;</label><input type="button" onclick="javascript:query();" class="scbtn" value="查询"/></li>
		    </ul>
		</div>
	</form>
    <div class="rightinfo" style="width:780px;">
	    <table class="imgtable">
	    	<thead>
			    <tr>
				    <th width="100px">头像</th>
					<th>昵称</th>
					<th>注册时间</th>
					<th>确认转送</th>
			    </tr>
		    </thead>
		    <tbody>
			    <#if beans?exists && beans?size gt 0>
					<#list beans as ls>
					    <tr>
						    <td class="imgtd">
						    	<#if (ls.avatarStr)?exists&&(ls.avatarStr)!="">
									<img src="${(ls.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
								<#else>
									<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
								</#if>
						    </td>
						    <td>
						    	${(ls.nickName)?if_exists}
						    </td>
						    <td>${(ls.account.createdStr)?if_exists}</td>
						    <td>
						    	<div class="operation"><a href="javascript:transsubmit('${dogid?if_exists}','${(ls.id)?if_exists}');"><img src="/assets/userui/images/conform.png"/></a> </div>
						    	<!--<div class="operation"><a href="/member/dog/trans/submit?dogid=${dogid?if_exists}&touid=${(ls.id)?if_exists}"><img src="/assets/userui/images/conform.png"/></a> </div>-->
							</td>
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
