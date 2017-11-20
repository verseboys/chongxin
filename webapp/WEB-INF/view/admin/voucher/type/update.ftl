<div class="pageContent">
	<form method="post" action="/admin/vouchertype/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>代金券名称：</label>
				<input type="text" name="name" size="30" class="required" value="${(bean.name)?if_exists}"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>有效时长：</label>
				<input type="text" name="effectivetime" size="30" class="digits required" value="${(bean.effectivetime)?if_exists}"/>
				<span class="info">天</span>
			</p>
			<div class="divider"></div>
			<div>
				<label>说明：</label>
				<textarea name="intro" cols="50" rows="3">${(bean.intro)?if_exists}</textarea>
			</div>
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
