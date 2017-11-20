<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/warranty/${checked?if_exists}" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						宠物芯片号：<input type="text" name="title" value="${title?if_exists}"/>
					</td>
					<td>
						保单号：<input type="text" name="title2" value="${title2?if_exists}"/>
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
			<li><a class="edit" href="/admin/warranty/check?id={sid_user}&checked=${checked?if_exists}" target="navTab"><span>保单操作</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">保单号</th>
					<th width="100" align="center">宠物芯片号</th>
					<th width="100" align="center">下单人</th>
					<th width="100" align="center">价格</th>
					<th width="100" align="center">状态</th>
					<th width="100" align="center">下单日期</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>${(ls.id)?if_exists}</td>
							<td>${(ls.blood)?if_exists}</td>
							<td>${(ls.profile.nickName)?if_exists}</td>
							<td>${(ls.price)?if_exists}</td>
							<td>
								<#if (ls.checked)==1>
									已确认
								<#else>
									未确认
								</#if>
							</td>
							<td>${(ls.createdStr)?if_exists}</td>
							<td>
								<a title="保单操作" target="navTab" href="/admin/warranty/check?id=${(ls.id)?if_exists}&checked=${checked?if_exists}" class="btnEdit">修改</a>
								<!--<a title="删除" target="ajaxTodo" href="/admin/doctor/delete?id=${(ls.id)?if_exists}" class="btnDel">删除</a>-->
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
