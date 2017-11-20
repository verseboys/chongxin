<div class="pageContent">
	<form method="post" action="/admin/company/announcement/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id"  value="${id?if_exists}">
		<input type="hidden" name="type"  value="${type?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>公告：</label>
				<textarea cols="100" rows="20" name="notice"></textarea>
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
