<div class="pageContent">
	<form method="post" action="/admin/account/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="accountId"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="roleid"  value="${roleid?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户身份：</label>
				<select class="combox" name="roleId">
					<option selected="selected" value="0">请选择</option>
					<#if roles?exists && roles?size gt 0>
						<#list roles as ls>
							<option <#if (bean.roleId)?exists && bean.roleId==ls.id>selected="selected"</#if> value="${(ls.id)?if_exists}">${(ls.title)?if_exists}</option>
						</#list>
					</#if>
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
