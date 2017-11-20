<div class="pageContent">
	<form method="post" action="/admin/activity/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="isfinished"  value="0">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>活动名称：</label>
				<input type="text" value="" name="name" size="30" class="required"/>
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
