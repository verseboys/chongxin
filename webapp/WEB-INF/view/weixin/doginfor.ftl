<div style="text-align:center;padding-top:20px;height:160px">
	<input type="hidden" id="input_dogavatar1" value="${(dog.avatar)?if_exists}">
	<a href="#dogavatarpage" class="ui-link">
		 <img class="ui-corner-all" style="width:120px;height:120px" src="${(dog.avatar)?if_exists}">
	</a>
</div>

<ul data-role="listview" class="ui-listview">
	<input type="hidden" id="input_dogname1" value="${(dog.name)?if_exists}">
	<input type="hidden" id="class1" value="1">   
  <li class="ui-li-has-count">
  	<a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		名称:
  		<span class="ui-li-count ui-body-inherit" id="dogname_span">
  			<input type="text" value="${(dog.name)?if_exists}" id="input_dogname" style="border:hidden;"/>
  		</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		犬种:<span class="ui-li-count ui-body-inherit">
  			<select id="dogclass_select" style="border:hidden;">
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls><!--list循环-->
						<option <#if dog?exists && ls.id==dog.classify>selected ="selected"</#if> value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
					</#list>
				</#if>
			</select>
			<input type="hidden" id="input_dogclass1" value="${(dog.classify)?if_exists}">
  		</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<input type="hidden" id="input_dogsex1" value="${(dog.sex)?if_exists}">
  	<a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		性别:<span class="ui-li-count ui-body-inherit">
  			<select id="dogsex_select">
  				<option <#if dog?exists && dog.sex==1>selected ="selected"</#if> value="1">男</option>
  				<option <#if dog?exists && dog.sex==2>selected ="selected"</#if> value="2">女</option>
			</select>
  		</span>
  	</a>
  </li>
  <li class="ui-li-has-count">
  	<input type="hidden" id="input_dogchip1" value="${(dog.chip)?if_exists}"> 
  	<a href="#" class="ui-btn ui-btn-icon-right ui-icon-carat-r">
  		芯片号:
  		<span class="ui-li-count ui-body-inherit" id="dogchip_span">
  			<input type="text" value="${(dog.chip)?if_exists}" id="input_dogchip" style="border:hidden;"/>
  		</span>
  	</a>
  </li>
</ul>