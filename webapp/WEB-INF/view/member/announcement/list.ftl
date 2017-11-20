<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script language="javascript">
function checkCompany(){
	var company = ${check?if_exists};
	if(company==0){
		alert("还没有店铺，去开店");
		window.location.href = "/member/doghouse/update";
	}
}
checkCompany();
function commit1() {
	var content1 = $(".content1").val();
	var companyid = $(".companyid").val();
	if (content1 == "") {
		alert("公告不能为空！");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/member/announcement/add",
		data : {
			'content' : content1,
			'companyId' : companyid
		},
		success : function(rt) {
			$(".content1").val("");
			$(".tablelist").empty();
			$(".tablelist").html(rt);
		}
	});
}
$(function(){
	$(".btn1").click(function() {
		commit1();
	});	
	$(".deleteInfo").live("click", function() {
		if(confirm("确定要删除吗？")){
			var obj = $(this);
			var id = $(this).attr("id");
			$.ajax({
				type : "POST",
				url : "/member/announcement/delete",
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
    <li><a href="/member/announcement">公告</a></li>
    </ul>
    </div>
    <div id="tab1" class="tabson">
    	<input value="${companyid?if_exists}" class="companyid" type="hidden"/>
	    <ul class="forminfo">
		    <li>
		    	<label>公告<b>*</b></label>
		    	<textarea cols="" rows="" class="textinput content1"></textarea>
		    </li>
	    	<li>
	    		<label>&nbsp;</label>
	    		<input type="button" class="btn btn1" value="确认发布"/>
	    	</li>
	    </ul>
	</div> 
    <div class="rightinfo">
	    <table class="tablelist">
	    	<thead>
		    	<tr>
			        <th>公告</th>
			        <th>公告时间</th>
		        </tr>
	        </thead>
	        <tbody>
	        	<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr>
							<td>${(ls.notice)?if_exists}</td>
							<td>${(ls.createdStr)?if_exists}</td>
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
