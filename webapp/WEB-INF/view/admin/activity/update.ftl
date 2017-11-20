<div class="pageContent">
	<form method="post" action="/admin/activity/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>活动名称：</label>
				<input type="text" value="${(bean.name)?if_exists}" name="name" size="30" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>活动状态：</label>
				<select class="combox" name="isfinished">
					<option <#if (bean.stisfinishedate)?exists && (bean.isfinished)==0>selected="selected"</#if> value="0">活动中</option>
					<option <#if (bean.isfinished)?exists && (bean.isfinished)==1>selected="selected"</#if> value="1">活动结束</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
