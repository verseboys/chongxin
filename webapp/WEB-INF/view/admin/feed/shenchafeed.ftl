
<div class="pageContent">
	<form method="post" action="/admin/feed/deletefeed" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="accountId"  value="${accountId?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input type="text" value="${(bean.profile.nickName)?if_exists}" readonly="readonly" size="30"/>
			</p>
			<p>
				<label>真实姓名：</label>
				<input type="text" value="${(bean.profile.trueName)?if_exists}" readonly="readonly" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>动态：</label>
				<textarea readonly="true" cols="80" rows="5">${(bean.content)?if_exists}</textarea>
			</div>
			<div class="divider"></div>
			<div>
				<label>动态图片：</label>
				<#if (dto.photos)?exists && (dto.photos)?size gt 0>
					<#list (dto.photos) as ls><!--list循环-->
						<#if (ls.photo)?exists && (ls.photo)!="">
							<img src="${ls.photo?if_exists}" style="max-height:200px;max-width:200px;"/>
						<#else>
							<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
						</#if>
					</#list>
				<#else>
					<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				</#if>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">违规删除</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">通过关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
