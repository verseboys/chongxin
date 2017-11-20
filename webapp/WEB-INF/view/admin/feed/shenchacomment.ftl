<div class="pageContent">
	<form method="post" action="/admin/feed/deletecomment" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
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
				<label>评论：</label>
				<textarea readonly="true" cols="80" rows="5">${(bean.comment)?if_exists}</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">违规删除</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">通过关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
