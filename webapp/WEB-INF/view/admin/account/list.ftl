<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/account/${roleid?if_exists}" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						手机号：<input type="text" name="userName" value="${userName?if_exists}"/>
					</td>
					<td>
						用户昵称：<input type="text" name="nickname" value="${nickname?if_exists}"/>
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
			<li><a class="edit" href="/admin/account/roleset?accountId={sid_user}&roleid=${roleid?if_exists}" target="dialog" warn=""><span>身份设置</span></a></li>
			<!--<li><a class="edit" href="/admin/account/details?accountId={sid_user}" target="navTab" warn="请选择记录！"><span>查看详情</span></a></li>
			<li><a class="delete" href="/admin/account/delete?accountId={sid_user}" target="ajaxTodo" warn="请选择记录！" title="确定删除？"><span>删除</span></a></li>-->
			<!--<li><a class="edit" href="/admin/account/checkmobile?accountId={sid_user}" target="ajaxTodo" warn="请选择记录！" title="确定验证？"><span>手机号验证</span></a></li>-->
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="50" align="center">ID</th>
					<th width="50" align="center">用户名</th>
					<th width="100" align="center">昵称</th>
					<th width="100" align="center">角色</th>
					<th width="100" align="center">动态</th>
					<th width="100" align="center">宠物</th>
					<th width="100" align="center">收益</th>
					<th width="100" align="center">注册平台</th>
					<th width="100" align="center">注册时间</th>
					<th width="10" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>${(ls.id)?if_exists}</td>
							<td>
								<a target="navTab" title="详情" href="/admin/account/update?accountId=${(ls.id)?if_exists}&roleid=${roleid?if_exists}">
									<#if (ls.username)?exists &&(ls.username)?length gt 32>
										${(ls.username)?if_exists[0..31]}
									<#else>
										${(ls.username)?if_exists}
									</#if>
								</a>
							</td>
							<td>
								<a target="navTab" title="详情" href="/admin/account/update?accountId=${(ls.id)?if_exists}&roleid=${roleid?if_exists}">${(ls.profile.nickName)?if_exists}</a>
							</td>
							<td>
								<#if (ls.profile.roleId)?exists && ((ls.profile.roleId)==2||(ls.profile.roleId)==3)>
									<a target="navTab" href="/admin/account/company?id=${(ls.id)}">${(ls.profile.role.title)?if_exists}</a>
								<#else>
									${(ls.profile.role.title)?if_exists}
								</#if>
							</td>
							<td><a target="navTab" href="/admin/feed/${(ls.id)?if_exists}">进入</a></td>
							<td><a target="navTab" href="/admin/dog/${(ls.id)?if_exists}/0">进入</a></td>
							<td><a target="navTab" href="/admin/account/profit?uid=${(ls.id)?if_exists}">进入</a></td>
							<td>${(ls.platform)?if_exists}</td>
							<td>${(ls.createdStr)?if_exists}</td>
							<td>
								<a title="删除" target="ajaxTodo" href="/admin/account/delete?accountId=${(ls.id)?if_exists}&roleid=${roleid?if_exists}" class="btnDel">删除</a>
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
