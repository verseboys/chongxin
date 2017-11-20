<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/appointment" method="post">
		<input type="hidden" name="pageNum" value="${pageNum?if_exists}" />
		<input type="hidden" name="numPerPage" value="${numPerPage?if_exists}" />
		<div class="searchBar">
		
			<table class="searchContent">
				<tr>
					<td>
						预约号：<input type="text" name="title" value="${title?if_exists}"/>
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
			<li><a class="edit" href="/admin/appointment/update?id={sid_user}" target="dialog" warn=""><span>预约状态更新</span></a></li>
		</ul>
	</div>
	<div id="w_list_print">
		<table class="table" width="100%" layoutH="138">
			<thead>
				<tr>
					<th width="100" align="center">预约号</th>
					<th width="100" align="center">预约人</th>
					<th width="100" align="center">联系电话</th>
					<th width="100" align="center">服务地址</th>
					<th width="100" align="center">预约时间</th>
					<th width="100" align="center">服务医师</th>
					<th width="100" align="center">预约状态</th>
					<th width="100" align="center">备注</th>
					<th width="20" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls>
						<tr target="sid_user" rel="${(ls.id)?if_exists}">
							<td>${(ls.id)?if_exists}</td>
							<td>${(ls.name)?if_exists}</td>
							<td>${(ls.telephone)?if_exists}</td>
							<td>${(ls.address)?if_exists}</td>
							<td>${(ls.createdStr)?if_exists}</td>
							<td>${(ls.doctor.name)?if_exists}</td>
							<td>
								<select>
								 	<#if (ls.state)?exists && (ls.state)==0>
										<option selected="selected" >未处理</option>
									<#elseif (ls.state)?exists && (ls.state)==1>
										<option selected="selected" >已经接单</option>
									<#elseif (ls.state)?exists && (ls.state)==2>
										<option selected="selected" >服务中</option>
									<#elseif (ls.state)?exists && (ls.state)==3>
										<option selected="selected" >订单取消</option>
									<#elseif (ls.state)?exists && (ls.state)==4>
										<option selected="selected" >订单完成</option>
									<#else>
										<option selected="selected" ></option>
									</#if>
     							</select>
							</td>
							<td>${(ls.adminremark)?if_exists}</td>
							<td>
								<a title="预约状态更新" target="dialog" height="400" warn="" href="/admin/appointment/update?id=${(ls.id)?if_exists}" class="btnEdit">订单状态更新</a>
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
