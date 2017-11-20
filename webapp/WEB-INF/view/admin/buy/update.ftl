<div class="pageContent">
	<form method="post" action="/admin/buy/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="oddstate"  value="${(bean.state)?if_exists}">
		<input type="hidden" name="statetype"  value="${statetype?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<div id="w_list_print">
				<table class="table" width="100%">
					<thead>
						<tr>
							<th width="100" align="center">订单号</th>
							<th width="100" align="center">产品名</th>
							<th width="100" align="center">产品价格</th>
							<th width="100" align="center">购买数量</th>
						</tr>
					</thead>
					<tbody>
						<#if (bean.buyinfors)?exists && (bean.buyinfors)?size gt 0>
							<#list (bean.buyinfors) as ls>
								<tr target="sid_user">
									<td>${(bean.id)?if_exists}</td>
									<td>${(ls.product.product)?if_exists}</td>
									<td>${(ls.product.price)?if_exists}</td>
									<td>${(ls.number)?if_exists}</td>
								</tr>
							</#list>
						</#if>
					</tbody>
				</table>
			</div>
			<div class="divider"></div>
			<p>
				<label>购买状态：</label>
				<select class="combox" name="state">
					<option <#if (bean.state)?exists && (bean.state)==0>selected="selected"</#if> value="0">未付款</option>
					<option <#if (bean.state)?exists && (bean.state)==1>selected="selected"</#if> value="1">付款成功</option>
					<option <#if (bean.state)?exists && (bean.state)==2>selected="selected"</#if> value="2">已发货 </option>
					<option <#if (bean.state)?exists && (bean.state)==3>selected="selected"</#if> value="3">已收货</option>
					<option <#if (bean.state)?exists && (bean.state)==-1>selected="selected"</#if> value="-1">关闭</option>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>快递：</label>
				<input name="express" type="text" value="${(bean.express)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>快递单号：</label>
				<input name="oddNumbers" type="text" value="${(bean.oddNumbers)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系人：</label>
				<input name="name" type="text" value="${(bean.name)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系电话：</label>
				<input name="telephone" type="text" value="${(bean.telephone)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>联系地址：</label>
				<input name="address" type="text" value="${(bean.address)?if_exists}" size="30"/>
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
