<div class="pageContent">
	<form method="post" action="/admin/classify/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="isfirst"  value="${isfirst?if_exists}">
		<input type="hidden" name="type"  value="${(classify.type)?if_exists}">
		<input type="hidden" name="id"  value="${(classify.id)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>服务类型：</label>
				<input type="text" name="name" value="${(classify.name)?if_exists}" size="30" class="required"/>
			</p>
			<#if isfirst?exists&&isfirst==0>
				<div class="divider"></div>
				<p>
					<label>上级服务：</label>
					<select class="combox" name="pid">
						<#if classifies?exists && classifies?size gt 0>
							<#list classifies as ls>
								<option value="${(ls.id)?if_exists}" <#if (classify.pid)?exists && (classify.pid)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
							</#list>
						</#if>
					</select>
				</p>
			</#if>
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
