
<div class="pageContent">
	<form method="post" action="/admin/ask/deleteask" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>问题：</label>
				<input type="text" value="${(bean.content)?if_exists}" readonly="readonly" size="30"/>
			</p>
			<p>
				<label>用户名：</label>
				<input type="text" value="${(bean.profile.nickName)?if_exists}" readonly="readonly" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>问题图片：</label>
				<#if (bean.askPics)?exists && (bean.askPics)?size gt 0>
					<#list (bean.askPics) as ls><!--list循环-->
						<#if (ls.photo)?exists && (ls.photo)!="">
							<img src="${ImgServer?if_exists}${(ls.photo)?if_exists}" style="max-height:200px;max-width:200px;"/>
						<#else>
							<img src="/assets/img/default.jpg" style="max-height:200px;max-width:200px;"/>
						</#if>
					</#list>
				<#else>
					<img src="/assets/img/default.jpg" style="max-height:200px;max-width:200px;"/>
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
