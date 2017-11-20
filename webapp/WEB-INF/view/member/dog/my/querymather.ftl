<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/assets/js/dog.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
});
</script>

</head>

<body>
	<form method="post" id="dogform" action="/member/dog/querydog" enctype="multipart/form-data">
		<input type="hidden" name="dogid"  value="${dogid?default(0)}" id="petid">
		<input type="hidden" name="type"  value="${type?if_exists}">
		<input type="hidden" name="pageNum"  value="1">
		<input type="hidden" name="numPerPage"  value="${numPerPage?default(20)}">
		<div id="tab1" class="rightinfo">
			<ul class="seachform">
			    <li><label>宠物名称</label><input name="title" type="text" class="scinput" value="${title?if_exists}"/></li>
			    <li><label>&nbsp;</label><input type="button" onclick="javascript:query();" class="scbtn" value="查询"/></li>
		    </ul>
		</div>
	</form>
    <div class="rightinfo formbody2" style="width:765px;">
    	<input type="hidden" name="petid"  value="${dogid?default(0)}" id="petid">
	    <table class="imgtable">
			<thead>
			    <tr>
				    <th width="100px">宠物头像</th>
					<th >宠物名字</th>
					<th >芯片号</th>
					<th >犬种</th>
					<th width="100px">确认</th>
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
						    	<div class="operation"><a href="javascript:;" onclick="javascript:submitBlood(3,'${(ls.blood)?if_exists}');"><img src="/assets/userui/images/conform.png" alt="修改"/></a></div>
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
</body>

</html>
