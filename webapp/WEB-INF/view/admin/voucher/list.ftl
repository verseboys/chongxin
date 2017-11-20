<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/activity" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>	
						代金券类型：
					</td>
					<td>	
						<select class="combox" name="type">
							<#if voucherTypes?exists && voucherTypes?size gt 0>
								<#list voucherTypes as ls>
									<option value="${(ls.id)?if_exists}" <#if type?exists && type==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
								</#list>
							</#if>
						</select>	
					</td>
					<td>
						用户昵称：<input type="text" name="title" value="${title?if_exists}"/>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确定？" class="edit" href="/admin/voucher/update?id={sid_user}" target="ajaxTodo"><span>修改状态</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">用户昵称</th>
					<th width="100" align="center">代金券</th>
					<th width="100" align="center">面值</th>
					<th width="20" align="center">状态</th>
					<th width="20" align="center">到期时间</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>${(ls.profile.nickName)?if_exists}</td>
							<td>${(ls.type.name)?if_exists}</td>
							<td>${(ls.price)?if_exists}元</td>
							<td>
								<select>
								 	<#if (ls.beused)?exists && (ls.beused)==1>
										<option selected="selected" >已使用</option>
									<#else>
										<option selected="selected" >未使用</option>
									</#if>
     							</select>
							</td>
							<td>${(ls.endtimeStr)?if_exists}</td>
						</tr>
					</#list>
				</#if>
			</tbody>
		</table>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <#if (numPerPage?exists)&&(numPerPage==20)>selected="selected"</#if> >20</option>
				<option value="50" <#if (numPerPage?exists)&&(numPerPage==50)>selected="selected"</#if> >50</option>
				<option value="100" <#if (numPerPage?exists)&&(numPerPage==100)>selected="selected"</#if> >100</option>
				<option value="200" <#if (numPerPage?exists)&&(numPerPage==200)>selected="selected"</#if> >200</option>
			</select>
			<span>条，共${totalCount?if_exists}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${totalCount?default(0)}" numPerPage="${numPerPage?default(20)}" pageNumShown="${pageNumShown?default(6)}" currentPage="${pageNum?default(1)}"></div>

	</div>
</div>
