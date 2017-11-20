<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script language="javascript">
$(function(){	
	$(".deleteInfo").live("click", function() {
		if(confirm("确定要删除吗？")){
			var obj = $(this);
			var id = $(this).attr("id");
			$.ajax({
				type : "POST",
				url : "/member/dog/record/delete",
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
    <li><a href="/member/dog">宠物</a></li>
    <li><a href="/member/dog/record?dogid=${dogid?if_exists}">记录</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
        	<a href="/member/dog/record/add?dogid=${dogid?if_exists}"><li class="click"><span><img src="/assets/userui/images/t01.png" /></span>添加</li></a>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th >记录类型</th>
				<th >记录时间</th>
				<th >肩高(cm)</th>
				<th >体重(kg)</th>
				<th >操作</th>
	        </tr>
        </thead>
        <tbody>
        	<#if beans?exists && beans?size gt 0>
				<#list beans as ls>
					<tr>
						<td>${(ls.classify.name)?if_exists}</td>
						<td>${(ls.recordTimeStr)?if_exists}</td>
						<td>${(ls.height)?if_exists}</td>
						<td>${(ls.weight)?if_exists}</td>
						<td>
							<div class="operation"><a href="/member/dog/record/update?id=${(ls.id)?if_exists}"><img src="/assets/userui/images/edit.png" alt="修改"/></a></div>
						    <div class="operation"><a href="javascript:;" class="deleteInfo" id ="${(ls.id)?if_exists}"><img src="/assets/userui/images/delete.png"/></a> </div>
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
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
