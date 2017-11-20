<div class="pageContent">
	<form method="post" action="/admin/servers/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="accountId"  value="${accountId?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<#if accountId==0>
				<dl class="nowrap">
					<dt>合作伙伴：</dt>
					<dd>
						<input names="company.id" class="required" name="companyid" value="${(company.id)?if_exists}" type="hidden"/>
						<input class="required" readonly="readonly" value="${(company.name)?if_exists}" names="company.name" type="text" lookupGroup="company"/>
						<a class="btnLook" href="/admin/servers/querycompany" lookupGroup="company">查找合作伙伴</a>	
					</dd>
				</dl>
				<div class="divider"></div>
			<#else>
				<input class="required" name="companyid" value="${accountId?if_exists}" type="hidden"/>
			</#if>
			<p>
				<label>服务类型：</label>
				<select class="combox"  ref="w_combox_classify" refUrl="/admin/servers/classify_{value}">
					<#if classifies?exists && classifies?size gt 0>
						<#list classifies as ls>
							<option value="${(ls.id)?if_exists}" <#if (classify.pid)?exists && (classify.pid)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
				<select class="combox required" name="classifyid" id="w_combox_classify">
					<#if classifies2?exists && classifies2?size gt 0>
						<#list classifies2 as ls>
							<option value="${(ls.id)?if_exists}" <#if (classify.id)?exists && (classify.id)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>价格：</label>
				<input type="text" name="price" size="20" value="${(bean.price)?if_exists}" class="required"/>
				<select name="units">
					<option value="元/次" <#if (bean.units)?exists && (bean.units)=='元/次'>selected="selected"</#if>>元/次</option>
					<option value="元/天" <#if (bean.units)?exists && (bean.units)=='元/天'>selected="selected"</#if>>元/天</option>
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
