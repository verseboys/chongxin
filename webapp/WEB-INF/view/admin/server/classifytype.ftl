[
	<#if classifies?exists && classifies?size gt 0>
		<#list classifies as ls>
			<#if ls_index+1 == classifies?size>
				["${(ls.id)?if_exists}", "${(ls.name)?if_exists}"]
			<#else>
				["${(ls.id)?if_exists}", "${(ls.name)?if_exists}"],
			</#if>
		</#list>
	</#if>
]