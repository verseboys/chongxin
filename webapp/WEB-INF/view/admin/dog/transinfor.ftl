<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/dog/transinfor" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<!--宠物名字：<input type="text" name="title" value="${title?if_exists}"/>-->
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<!--<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>-->
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<!--<li><a class="edit" href="/admin/dog/shencha?id={sid_user}" target="navTab" warn="请选择记录！"><span>审查宠物信息</span></a></li>-->
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">宠物名字</th>
					<th width="100" align="center">原宠物主人</th>
					<th width="100" align="center">现宠物主人</th>
					<th width="100" align="center">创建时间</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}" comment="list">
							<td>${(ls.dog.name)?if_exists}</td>
							<td>${(ls.old.nickName)?if_exists}</td>
							<td>${(ls.now.nickName)?if_exists}</td>
							<td>${(ls.transtimeStr)?if_exists}</td>
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
