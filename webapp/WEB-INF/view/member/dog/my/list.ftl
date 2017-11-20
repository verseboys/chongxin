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
	});
	
	$(".deleteInfo").live("click", function() {
		if(confirm("确定要删除吗？")){
			var obj = $(this);
			var id = $(this).attr("id");
			$.ajax({
				type : "POST",
				url : "/member/dog/delete",
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
function forward(_dogid){
	var openUrl = '/member/dog/queryfriend?dogid='+_dogid;//弹出窗口的url
	var iWidth=800; //弹出窗口的宽度;
	var iHeight=600; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	window.open(openUrl,"好友查询","height="+iHeight+",width="+iWidth+",top="+iTop+",left="+iLeft+",scrollbars=yes,location=no,menubar=no");
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
		    <li><a href="/member/dog">我的宠物</a></li>
	    </ul>
    </div>
    <div class="rightinfo">
	    <div class="tools">
	    	<ul class="toolbar">
		        <a href="/member/dog/add"><li><span><img src="/assets/userui/images/t01.png" /></span>添加</li></a>
	        </ul>
	    </div>
	    <table class="imgtable">
	    	<thead>
			    <tr>
				    <th width="100px">宠物头像</th>
					<th >宠物名字</th>
					<th >芯片号</th>
					<th >犬种</th>
					<th width="300px">操作(修改、删除、转让、记录)</th>
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
						    	${(ls.name)?if_exists}
						    	<p>出生日期：${(ls.birthdayStr)?if_exists}</p>
						    </td>
						    <td>${(ls.blood)?if_exists}</td>
						    <td>${(ls.category.name)?if_exists}</td>
						    <td>
						    	<div class="operation"><a href="/member/dog/update?id=${(ls.id)?if_exists}"><img src="/assets/userui/images/edit.png" alt="修改"/></a></div>
						    	<div class="operation"><a href="javascript:;" class="deleteInfo" id ="${ls.id}"><img src="/assets/userui/images/delete.png"/></a> </div>
						    	<div class="operation"><a href="javascript:forward('${(ls.id)?if_exists}');"><img src="/assets/userui/images/forward.png"/></a> </div>
						    	<!--<div class="operation"><a href="/member/dog/queryfriend?dogid=${(ls.id)?if_exists}"><img src="/assets/userui/images/forward.png"/></a> </div>-->
						    	<div class="operation"><a href="/member/dog/record?dogid=${(ls.id)?if_exists}"><img src="/assets/userui/images/t04.png"/></a> </div>
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
