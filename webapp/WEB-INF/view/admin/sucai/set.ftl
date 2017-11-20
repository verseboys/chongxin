
<div class="pageContent">
	
	<form method="post" action="/admin/sucai/submitSet" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="sid" value="${(bean.id)?if_exists}">
		<input type="hidden" name="viewCount" value="0">
		<div class="pageFormContent" layoutH="68">
			<p>
				<label>素材标题：</label>
				<input type="text" value="${(bean.title)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<div id="preimage">
					<img src="${(bean.logo)?if_exists}" height="200" width="200"/>
				</div>
			</div>
			<div class="divider"></div>
			<p>
				<label>发布时间：</label>
				<input type="text" class="date required" dateFmt="yyyy-MM-dd HH" readonly="true" name="createdStr1"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

