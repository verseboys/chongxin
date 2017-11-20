<div class="cityright">
    <div class="uew-select">
    	<div class="uew-select-value ue-state-default" style="width: 140px;">
    		<em class="uew-select-text">${(classifies[0].name)?if_exists}</em>
    		<em class="uew-icon uew-icon-triangle-1-s"></em>
    	</div>
    	<select name="classifyid" class="select2 classifyid" style="width: 167px;">
				<#if classifies?exists && classifies?size gt 0>
					<#list classifies as ls>
						<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
					</#list>
				</#if>
    	</select>
    </div>
</div>
<script type="text/javascript">
	$(".classifyid").change(function() {
		var fclassifyid = $(this).children('option:selected').html();
		$(".cityright .uew-select-text").html(fclassifyid);
	});
</script>
