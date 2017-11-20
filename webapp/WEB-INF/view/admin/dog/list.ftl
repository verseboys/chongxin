<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/dog/${accountId?if_exists}/${havablood?if_exists}" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						宠物名字：<input type="text" name="title" value="${title?if_exists}"/>
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
			<!--
			<li><a class="edit" href="/admin/dog/shencha?id={sid_user}" target="navTab" warn="请选择记录！"><span>审查宠物信息</span></a></li>
			<li><a class="edit" href="/admin/dog/transinfor?id={sid_user}" target="navTab" warn="请选择记录！"><span>宠物转手信息</span></a></li>
			-->
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">宠物名字</th>
					<th width="100" align="center">宠物主人</th>
					<th width="100" align="center">宠物芯片</th>
					<th width="100" align="center">犬种</th>
					<th width="100" align="center">创建时间</th>
					<th width="20" align="center">转手信息</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td><a target="navTab" title="宠物详情" href="/admin/dog/shencha?id=${(ls.id)?if_exists}&accountId=${accountId?if_exists}&havablood=${havablood?if_exists}">${(ls.name)?if_exists}</a></td>
							<td><a target="navTab" title="宠物主详情" href="/admin/account/update?accountId=${(ls.owner.id)?if_exists}&roleid=${(ls.owner.roleId)?if_exists}">${(ls.owner.nickName)?if_exists}</a></td>
							<td>${(ls.blood)?if_exists}</td>
							<td>${(ls.category.name)?if_exists}</td>
							<td>${(ls.created)?if_exists}</td>
							<td>
								<a target="navTab" href="/admin/dog/transinfor?id=${(ls.id)?if_exists}">进入</a>
							</td>
							<td>
								<a title="删除" target="ajaxTodo" href="/admin/dog/delete?id=${(ls.id)?if_exists}&accountId=${accountId?if_exists}&havablood=${havablood?if_exists}" class="btnDel">删除</a>
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
