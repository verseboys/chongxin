<table class="imgtable">
	<thead>
	    <tr>
		    <th width="100px">最新20条</th>
			<th></th>
			<th></th>
	    </tr>
    </thead>
    <tbody>
	    <#if beans?exists && beans?size gt 0>
			<#list beans as ls>
			    <tr>
				    <td class="imgtd" width="100px;">
						<#if (ls.profile.avatarStr)?exists && (ls.profile.avatarStr)!="">
							<img src="${(ls.profile.avatarStr)?if_exists}" style="height:60px;width:60px;"/>
						<#else>
							<img src="/assets/img/default.png" style="height:60px;width:60px;"/>
						</#if>
				    </td>
				    <td>
			    		<#if (ls.type)?exists && (ls.type)==1>
							<a href="/member/message/bigimg?uid=${uid?if_exists}&touid=${touid?if_exists}&img_url=${(ls.content)?if_exists}">
								<img src="${(ls.content)?if_exists}" style="height:60px;width:60px;"/>
							</a>
						<#else>
							<#if (ls.content)?exists &&(ls.content)?length gt 10>
								<a href="/member/message/chatcontent?uid=${uid?if_exists}&touid=${touid?if_exists}&id=${(ls.id)?if_exists}">
									${(ls.content)?if_exists[0..9]}...
								</a>
							<#else>
								${(ls.content)?if_exists}
							</#if>
						</#if>
				    </td>
				    <td>${(ls.date)?if_exists}</td>
			    </tr>
		    </#list>
		</#if>
    </tbody>
</table>