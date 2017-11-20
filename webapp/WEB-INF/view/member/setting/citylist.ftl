<div class="cityright">
    <div class="uew-select">
    	<div class="uew-select-value ue-state-default" style="width: 140px;">
    		<em class="uew-select-text">${(cities[0].name)?if_exists}</em>
    		<em class="uew-icon uew-icon-triangle-1-s"></em>
    	</div>
    	<select name="cityid" class="select2 cityid" style="width: 167px;">
				<#if cities?exists && cities?size gt 0>
					<#list cities as ls>
						<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
					</#list>
				</#if>
    	</select>
    </div>
</div>
<script type="text/javascript">
	$(".cityid").change(function() {
		var fcityid = $(this).children('option:selected').html();
		$(".cityright .uew-select-text").html(fcityid);
	});
</script>
