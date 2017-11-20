<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/sucai" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						标题：<input type="text" name="title" value="${title?if_exists}"/>
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
			<li><a class="add" href="/admin/sucai/add" target="navTab"><span>添加素材</span></a></li>
			<li><a class="delete" id="delete" href="/admin/sucai/delete?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除素材</span></a></li>
			<li><a class="edit" id="edit" href="/admin/sucai/update?id={sid_user}" target="navTab" warn="请选择素材！"><span>修改素材</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" id="icon" href="/admin/sucai/set?id={sid_user}" target="dialog" warn="请选择素材！" height="400"><span>设置头条</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">标题</th>
					<th width="100" align="center">logo</th>
					<th width="100" align="center">作者</th>
					<th width="100" align="center">添加时间</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}" seted="set">
							<td>${(ls.title)?if_exists}</td>
							<td>${(ls.logo)?if_exists}</td>
							<td>${(ls.auth)?if_exists}</td>
							<td>${(ls.createdStr)?if_exists}</td>
							<td>
								<a title="修改素材" target="navTab" href="/admin/sucai/update?id=${(ls.id)?if_exists}" class="btnEdit">修改素材</a>
								<a title="删除素材" target="ajaxTodo" href="/admin/sucai/delete?id=${(ls.id)?if_exists}" class="btnDel">删除素材</a>
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
