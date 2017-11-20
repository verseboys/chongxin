<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script src="/assets/js/warranty.js" type="text/javascript"></script>
<script language="javascript">
$(function(){
	$(".deleteInfo").live("click",function(){
		if(confirm("确定要删除吗？")){
			var obj = $(this);
			var id = $(this).attr("id");
			$.ajax({
				type : "POST",
				url : "/member/warranty/delete",
				data : {
					'id' : id
				},
				success : function(rt) {
					if (rt == 1) {
						//alert("删除成功");
						$(obj).parents("tr").remove();
					} else {
						alert("糟糕，出错了");
					}
				},
				error : function() {
					alert("糟糕，出错了");
				}
			});
		}
	});
})
function dogdetails(_blood) {
	var openUrl = '/member/warranty/dogdetails?blood=' + _blood;// 弹出窗口的url
	var iWidth = 800; // 弹出窗口的宽度;
	var iHeight = 600; // 弹出窗口的高度;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	window.open(openUrl, "宠物详情", "height=" + iHeight + ",width=" + iWidth
			+ ",top=" + iTop + ",left=" + iLeft
			+ ",scrollbars=yes,location=no,menubar=no");
}
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
		    <li><a href="/member/warranty">保单</a></li>
	    </ul>
    </div>
    <form method="post" id="warrantyform" action="/member/warranty" enctype="multipart/form-data">
		<input type="hidden" name="pageNum"  value="1">
		<input type="hidden" name="numPerPage"  value="${numPerPage?default(20)}">
		<div id="tab1" class="rightinfo">
			<ul class="seachform" style="float:left;">
			    <li><label>宠物芯片号：</label><input name="title" type="text" class="scinput" value="${title?if_exists}"/></li>
			    <li><label>&nbsp;</label><input type="button" onclick="javascript:query();" class="scbtn" value="查询"/></li>
		    </ul>
		    <ul class="toolbar" style="float:right;">
		        <a href="/member/warranty/add"><li><span><img src="/assets/userui/images/t01.png" /></span>添加</li></a>
	        </ul>
		</div>
	</form>
    <div class="rightinfo">
	    <table class="tablelist">
	    	<thead>
			    <tr>
				    <th>保单号</th>
					<th>宠物芯片号</th>
					<th>下单人</th>
					<th>价格</th>
					<th>状态</th>
					<th>下单日期</th>
					<th>操作</th>
			    </tr>
		    </thead>
		    <tbody>
			    <#if beans?exists && beans?size gt 0>
					<#list beans as ls>
					    <tr>
					    	<td>${(ls.id)?if_exists}</td>
						    <td>
						    	<a href="javascript:;" onclick="javascript:dogdetails('${(ls.blood)?if_exists}');">${(ls.blood)?if_exists}</a>
						    </td>
						    <td>${(ls.profile.nickName)?if_exists}</td>
						    <td>${(ls.price)?if_exists}</td>
						    <td>
						    	<#if (ls.checked)==1>
									已确认
								<#else>
									未确认
								</#if>
						    </td>
						    <td>${(ls.createdStr)?if_exists}</td>
						    <td>
						    	<div class="operation"><a href="/member/warranty/update?id=${(ls.id)?if_exists}"><img src="/assets/userui/images/edit.png" alt="修改"/></a></div>
					    		<div class="operation"><a href="javascript:;" class="deleteInfo" id="${(ls.id)?if_exists}" ><img src="/assets/userui/images/delete.png"/></a></div>
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
