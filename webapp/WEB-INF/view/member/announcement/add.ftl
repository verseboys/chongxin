<thead>
	<tr>
        <th>公告</th>
        <th>公告时间</th>
    </tr>
</thead>
<tbody>
	<#if beans?exists && beans?size gt 0>
		<#list beans as ls>
			<tr>
				<td>${(ls.notice)?if_exists}</td>
				<td>${(ls.createdStr)?if_exists}</td>
			</tr>
		</#list>
	</#if>
</tbody>