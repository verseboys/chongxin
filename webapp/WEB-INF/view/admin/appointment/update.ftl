<div class="pageContent">
	<form method="post" action="/admin/appointment/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="uid"  value="${(bean.uid)?if_exists}">
		<input type="hidden" name="deleted"  value="${(bean.deleted)?if_exists}">
		<input type="hidden" name="remark"  value="${(bean.remark)?if_exists}">
		<input type="hidden" name="createdStr1"  value="${(bean.createdStr)?if_exists}">
		<input type="hidden" name="addressId"  value="${(bean.addressId)?if_exists}">
		<input type="hidden" name="oldstate"  value="${(bean.state)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>预约状态：</label>
				<select class="combox" name="state">
					<option <#if (bean.state)?exists && (bean.state)==0>selected="selected"</#if> value="0">未处理</option>
					<option <#if (bean.state)?exists && (bean.state)==1>selected="selected"</#if> value="1">已经接单</option>
					<option <#if (bean.state)?exists && (bean.state)==2>selected="selected"</#if> value="2">服务中</option>
					<option <#if (bean.state)?exists && (bean.state)==3>selected="selected"</#if> value="3">订单取消</option>
					<option <#if (bean.state)?exists && (bean.state)==4>selected="selected"</#if> value="4">订单完成</option>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>服务医师：</label>
				<select class="combox" name="doctorId">
					<option value="0">请选择</option>
					<#if beans?exists && beans?size gt 0>
						<#list beans as ls>
							<option <#if (bean.doctorId)?exists && (bean.doctorId)==(ls.id)>selected="selected"</#if> value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>预约人：</label>
				<input type="text" value="${(bean.name)?if_exists}" name="name" size="30" class="required"/>
			</p>
			<p>
				<label>联系电话：</label>
				<input type="text" value="${(bean.telephone)?if_exists}" name="telephone" size="30" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>收货地址：</label>
				<input type="text" value="${(bean.address)?if_exists}" name="address" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>备注：</label>
				<textarea name="adminremark" cols="80" rows="5">${(bean.adminremark)?if_exists}</textarea>
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
