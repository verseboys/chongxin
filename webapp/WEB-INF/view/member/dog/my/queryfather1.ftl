<table class="imgtable">
	<thead>
	    <tr>
		    <th width="100px">宠物头像</th>
			<th >宠物名字</th>
			<th >芯片号</th>
			<th >犬种</th>
			<th width="300px">确认</th>
	    </tr>
    </thead>
    <tbody>
	    <#if beans?exists && beans?size gt 0>
			<#list beans as ls>
			    <tr>
				    <td class="imgtd">
				    	<#if (ls.avatarStr)?exists&&(ls.avatarStr)!="">
							<img src="${(ls.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
						<#else>
							<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
						</#if>
				    </td>
				    <td>
				    	${(ls.name)?if_exists}
				    	<p>出生日期：${(ls.birthdayStr)?if_exists}</p>
				    </td>
				    <td>${(ls.blood)?if_exists}</td>
				    <td>${(ls.category.name)?if_exists}</td>
				    <td>
				    	<div class="operation"><a href="javascript:;" onclick="javascript:submitBlood(2,'${(ls.blood)?if_exists}');" ><img src="/assets/userui/images/conform.png"/></a></div>
				    </td>
			    </tr>
		    </#list>
		</#if>
    </tbody>
</table>
<div class="pagin">
	${page?if_exists}
</div>