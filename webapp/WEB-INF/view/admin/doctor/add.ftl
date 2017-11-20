<div class="pageContent">
	<form method="post" action="/admin/doctor/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="deleted"  value="0">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>医师姓名：</label>
				<input name="name" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>职务：</label>
				<input name="job" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系电话：</label>
				<input name="telephone" type="text" value="" size="30"/>
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
