<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<link href="/assets/userui/css/select.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>
<script type="text/javascript" src="/assets/userui/js/select-ui.min.js"></script>
<script src="/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/assets/js/record.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
});
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/member/index/main">首页</a></li>
    <li><a href="/member/dog">宠物</a></li>
    <li><a href="/member/dog/record?dogid=${(bean.dogid)?if_exists}">记录</a></li>
    <li><a href="javascript:;">修改</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>基本信息</span></div>
    <form method="post" action="/member/dog/record/submit" id="record-form">
    	<input type="hidden" name="createdStr1"  value="${(bean.createdStr)?if_exists}">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="feedid"  value="${(bean.feedid)?default(0)}">
		<input type="hidden" name="dogid"  value="${(bean.dogid)?if_exists}">
	    <ul class="forminfo">
		    <li>
		    	<label>记录类型<b>*</b></label>
		    	<div class="vocation">
				    <select class="select1 classifyid" name="classifyid">
					    <#if classifies?exists && classifies?size gt 0>
							<#list classifies as ls><!--list循环-->
								<option value="${(ls.id)?if_exists}" <#if (bean.classifyid)?exists && (bean.classifyid)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
							</#list>
						</#if>
				    </select>
				</div>
		    </li>
		    <li>
		    	<label>记录时间<b>*</b></label>
		    	<input id="d12" value="${(bean.recordTimeStr)?if_exists}" name="recordTime1" type="text" class="dfinput recordTime1" readonly="readonly"/>
		    	<img onclick="WdatePicker({el:$dp.$('d12')})" src="/assets/My97DatePicker/skin/datePicker.gif" width="30" height="40" align="absmiddle"/>
		    </li>
		    <li>
		    	<label>肩高(cm)</label>
		    	<input name="height" value="${(bean.height)?default(0)}" type="text" class="dfinput height" />
		    </li>
		    <li>
		    	<label>体重(kg)</label>
		    	<input name="weight" value="${(bean.weight)?default(0)}" type="text" class="dfinput weight" />
		    </li>
		    <li>
		    	<label>备注</label>
		    	<textarea name="remark" cols="" rows="" class="textinput">${(bean.remark)?if_exists}</textarea>
		    </li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input type="button" class="btn" value="确认保存"/>
		    </li>
	    </ul>
    </form>
    </div>
</body>

</html>
