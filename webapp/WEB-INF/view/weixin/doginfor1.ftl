<div style="text-align:center;padding-top:20px;height:160px">
	<a href="#dogavatarpage" class="ui-link">
		 <img class="ui-corner-all" style="width:120px;height:120px" src="${(dog.avatar)?if_exists}">
	</a>
</div>

<ul data-role="listview" class="ui-listview">
	<input type="hidden" id="input_dogname1" value="${(dog.name)?if_exists}">
  <li class="ui-li-has-count">
  	<a href="#dognamepage" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		名称
  		<span class="ui-li-count ui-body-inherit" id="dogname_span">${(dog.name)?if_exists}</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<a href="#dogclasspage" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		犬种<span class="ui-li-count ui-body-inherit">
  			<select disabled="disabled">
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls><!--list循环-->
						<#if dog?exists && ls.id==dog.classify>
							<option>${(ls.name)?if_exists}</option>
						</#if>
					</#list>
				</#if>
			</select>
			<input type="hidden" id="input_dogclass1" value="${(dog.classify)?if_exists}">
  		</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<input type="hidden" id="input_dogsex1" value="${(dog.sex)?if_exists}">
  	<a href="#dogsexpage" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		性别<span class="ui-li-count ui-body-inherit">
  			<select disabled="disabled">
  				<#if dog?exists && dog.sex==1>
					<option>男</option>
				<#elseif dog?exists && dog.sex==2>
					<option>女</option>
				<#else>
					<option></option>
				</#if>
			</select>
  		</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<input type="hidden" id="input_dogchip1" value="${(dog.chip)?if_exists}"> 
  	<a href="#dogchippage" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		芯片号<span class="ui-li-count ui-body-inherit" id="dogchip_span">${(dog.chip)?if_exists}</span>
  	</a>
  </li>
</ul>

<div id="dogclassdiv" style="display:none">
	<select class="dogclass_select">
		<#if beans?exists && beans?size gt 0>
			<#list beans as ls><!--list循环-->
				<option value="${(ls.id)?if_exists}" <#if dog?exists && dog.classify==ls.id>selected ="selected"</#if>>${(ls.name)?if_exists}</option>
			</#list>
		</#if>
	</select>
</div>