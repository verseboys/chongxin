<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script language="javascript">
function checkCompany(){
	var company = ${check?if_exists};
	if(company==0){
		alert("还没有店铺，去开店");
		window.location.href = "/member/doghouse/update";
	}
}
checkCompany();
$(function(){	
	$(".deleteInfo").live("click", function() {
		if(confirm("确定要删除吗？")){
			var obj = $(this);
			var id = $(this).attr("id");
			$.ajax({
				type : "POST",
				url : "/member/servers/delete",
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
    <li><a href="/member/servers">服务</a></li>
    </ul>
    </div>
    <!--<div id="tab1" class="tabson">
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
		    <li><label>&nbsp;</label><input type="button" class="btn" value="添加服务"/></li>
	    </ul>
	</div> -->
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
        	<a href="/member/servers/add"><li class="click"><span><img src="/assets/userui/images/t01.png" /></span>添加</li></a>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th>服务</th>
		        <th>价格</th>
		        <th>操作</th>
	        </tr>
        </thead>
        <tbody>
        	<#if beans?exists && beans?size gt 0>
				<#list beans as ls>
					<tr>
						<td>${(ls.classify.classify.name)?if_exists}/${(ls.classify.name)?if_exists}</td>
						<td>${(ls.price)?if_exists}</td>
						<td>
							<div class="operation"><a href="/member/servers/update?id=${(ls.id)?if_exists}"><img src="/assets/userui/images/edit.png" alt="修改"/></a></div>
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
		$(document).ready(function(e) {
			$(".select2").uedSelect({
				width : 167  
			});
			$(".select3").uedSelect({
				width : 70  
			});
		});
	</script>
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>

</html>
