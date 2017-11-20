[
	<#if cities?exists && cities?size gt 0>
		<#list cities as ls>
			<#if ls_index+1 == cities?size>
				["${(ls.id)?if_exists}", "${(ls.name)?if_exists}"]
			<#else>
				["${(ls.id)?if_exists}", "${(ls.name)?if_exists}"],
			</#if>
		</#list>
	</#if>
]