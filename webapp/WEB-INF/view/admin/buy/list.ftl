<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/buy/${statetype?default(-2)}" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						订单号：<input type="text" name="title" value="${title?if_exists}"/>
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
			<li><a class="edit" href="/admin/buy/update?id={sid_user}&statetype=${statetype?default(-2)}" target="navTab" warn="请选择记录！"><span>订单更新</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">订单号</th>
					<th width="100" align="center">购买人</th>
					<th width="100" align="center">联系电话</th>
					<th width="100" align="center">购买时间</th>
					<th width="100" align="center">订单状态</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td><a target="navTab" title="订单详情" href="/admin/buy/details?id=${(ls.id)?if_exists}">${(ls.id)?if_exists}</a></td>
							<td>${(ls.name)?if_exists}</td>
							<td>${(ls.telephone)?if_exists}</td>
							<td>${(ls.buytimeStr)?if_exists}</td>
							<td>
								<#if (ls.state)==0>
									待付款 
								<#elseif (ls.state)==1>
									付款成功 
								<#elseif (ls.state)==2>
									已发货 
								<#elseif (ls.state)==3>
									已收货
								<#elseif (ls.state)==-1>
									 关闭
								</#if>
							</td>
							<td>
								<a title="订单更新" target="navTab" href="/admin/buy/update?id=${(ls.id)?if_exists}&statetype=${statetype?default(-2)}" class="btnEdit">订单状态更新</a>
							</td>
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
