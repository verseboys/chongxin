<div class="pageHeader">
	<form id="pagerForm" method="post" action="/admin/dog/querydog" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<input type="hidden" name="dogid" value="${dogid?if_exists}" />
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>宠物名字:</label>
					<input class="textInput" name="title" value="${title?if_exists}" type="text">
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
				<th width="100" align="center">宠物名字</th>
				<th width="100" align="center">宠物主人</th>
				<th width="100" align="center">宠物芯片</th>
				<th width="100" align="center">犬种</th>
				<th width="100" align="center">创建时间</th>
				<th width="20">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<#if beans?exists && beans?size gt 0>
				<#list beans as ls>
					<tr target="sid_user" rel="${(ls.id)?if_exists}">
						<td>${(ls.name)?if_exists}</td>
						<td>${(ls.owner.nickName)?if_exists}</td>
						<td>${(ls.blood)?if_exists}</td>
						<td>${(ls.category.name)?if_exists}</td>
						<td>${(ls.created)?if_exists}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({blood:'${(ls.blood)?if_exists}'})" title="查找带回">选择</a>
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