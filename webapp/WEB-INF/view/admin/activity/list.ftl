<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/activity" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>	
						活动状态：
					</td>
					<td>	
						<select class="combox" name="isfinished">
							<option value="0" <#if isfinished?exists && isfinished==0>selected="selected"</#if>>活动中</option>
							<option value="1" <#if isfinished?exists && isfinished==1>selected="selected"</#if>>活动结束</option>
						</select>
					</td>
					<td>
						活动名称：<input type="text" name="title" value="${title?if_exists}"/>
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
			<li><a class="add" href="/admin/activity/add" target="dialog" warn=""><span>添加</span></a></li>
			<!--<li><a class="delete" href="/admin/activity/delete?id={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>-->
			<li><a class="edit" href="/admin/activity/update?id={sid_user}" target="dialog" warn=""><span>修改</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">ID</th>
					<th width="100" align="center">活动名称</th>
					<th width="100" align="center">活动状态</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>${(ls.id)?if_exists}</td>
							<td>${(ls.name)?if_exists}</td>
							<td>
								<select>
								 	<#if (ls.isfinished)?exists && (ls.isfinished)==0>
										<option selected="selected" >活动中</option>
									<#else>
										<option selected="selected" >活动结束</option>
									</#if>
     							</select>
							</td>
							<td>
								<a title="修改" target="dialog" warn="" href="/admin/activity/update?id=${(ls.id)?if_exists}" class="btnEdit">修改</a>
								<!--<a title="删除" target="ajaxTodo" href="/admin/activity/delete?id=${(ls.id)?if_exists}" class="btnDel">删除</a>-->
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
