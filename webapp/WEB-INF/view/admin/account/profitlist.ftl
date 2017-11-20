<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/account/profit" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<input type="hidden" name="uid" value="${uid?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="50" align="center">类型</th>
					<th width="50" align="center">金额</th>
					<th width="100" align="center">状态</th>
					<th width="100" align="center">时间</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>
								<#if (ls.type)==0>
									推荐注册
								<#elseif (ls.type)==1>
									购物返利
								<#elseif (ls.type)==2>
									提现
								</#if>
							</td>
							<td>
								${(ls.profit)?if_exists}
							</td>
							<td>
								<#if (ls.status)==0||(ls.status)==2>
									<font color="red">不可使用</font>
								<#elseif (ls.status)==1>
									<font color="green">可使用</font>
								</#if>
							</td>
							<td>
								${(ls.createdStr)?if_exists}
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
