
<div class="pageHeader">
	<form id="pagerForm" method="post" action="/admin/company/queryuser" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>用户昵称:</label>
					<input class="textInput" name="userName" value="${userName?if_exists}" type="text">
				</li>	  
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" width="100%">
		<thead>
			<tr>
				<th width="100">用户名</th>
				<th width="100">昵称</th>
				<th width="100">注册时间</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if beans?exists && beans?size gt 0>
				<#list beans as ls>
					<tr>
						<td>
							<#if (ls.username)?exists &&(ls.username)?length gt 32>
								${(ls.username)?if_exists[0..31]}
							<#else>
								${(ls.username)?if_exists}
							</#if>
						</td>
						<td>${(ls.profile.nickName)?if_exists}</td>
						<td>${(ls.createdStr)?if_exists}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({id:'${(ls.id)?if_exists}', username:'${(ls.profile.nickName)?if_exists}'})" title="查找带回">选择</a>
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select class="combox" name="numPerPage" onchange="dwzPageBreak({targetType:'dialog', numPerPage:this.value})">
				<option value="20" <#if (numPerPage?exists)&&(numPerPage==20)>selected="selected"</#if> >20</option>
				<option value="50" <#if (numPerPage?exists)&&(numPerPage==50)>selected="selected"</#if> >50</option>
				<option value="100" <#if (numPerPage?exists)&&(numPerPage==100)>selected="selected"</#if> >100</option>
				<option value="200" <#if (numPerPage?exists)&&(numPerPage==200)>selected="selected"</#if> >200</option>
			</select>
			<span>条，共${totalCount?if_exists}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${totalCount?default(0)}" numPerPage="${numPerPage?default(20)}" pageNumShown="${pageNumShown?default(6)}" currentPage="${pageNum?default(1)}"></div>
	</div>
</div>