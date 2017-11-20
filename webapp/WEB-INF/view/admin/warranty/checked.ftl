<div class="pageContent">
	<form method="post" action="/admin/warranty/check/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="checked1"  value="${checked?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>保单号：</label>
				<input name="wid" type="text" value="${(bean.id)?if_exists}" size="30" readonly="readonly"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>宠物芯片号：</label>
				<input name="blood" type="text" value="${(bean.blood)?if_exists}" size="30" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>收款金额：</label>
				<input name="money" type="text" value="${(bean.price)?default(0)}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>生效时间：</label>
				<input type="text" name="starttimeStr" class="date" readonly="true" value="${latestDate?if_exists}"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>失效时间：</label>
				<input type="text" name="endtimeStr" class="date" readonly="true" value="" class="required"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<div>
				<label>操作说明：</label>
				<textarea name="remark" cols="80" rows="5"></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
