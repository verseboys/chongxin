<ul data-role="listview" class="ui-listview">
	<li class="ui-li-has-thumb">
		<div style="height:80px;border:0 solid red;" class="ui-btn ui-icon-carat-r">
			<img src="<#if (companyDto.logo)?exists&&(companyDto.logo)!= ''>${companyDto.logo}<#else>/assets/img/logo1.png</#if>" style="margin:15px 0px 10px 10px;"/>
			<div style="height:100%;padding-top:3%;text-align:left;">
				<div style="height:30%;">${(companyDto.name)?if_exists}</div>
				<div style="height:30%;font-size:12px;">地区：<a href="/weixin/baidumap?partnerid=${(companyDto.id)?if_exists}" target="_blank">${(companyDto.district)?if_exists}</a></div>
				<div style="height:30%;font-size:12px;">电话：${(companyDto.telephone)?if_exists}</div>
			</div>
		</div>
	</li>
</ul>

<div data-role="header" role="banner" class="ui-header ui-bar-inherit">
	<div data-role="navbar" class="ui-navbar" role="navigation">
	  <ul class="ui-grid-b">
	  	<#if classymap?exists && classymap?size gt 0>
		  	<#list classymap?keys as mykey> 
		  		<#if mykey_index==0>
		  			<li class="${mykey}"><div class="ui-btn _tab ui-btn-active" onclick="change(this);">${(classymap[mykey].name)?if_exists}</div></li>
		  		<#else>
		  			<li class="${mykey}"><div class="ui-btn _tab" onclick="change(this);">${(classymap[mykey].name)?if_exists}</div></li>
		  		</#if>
			</#list> 
		</#if>
	  </ul>
	</div>
</div>
<#if servermap?exists && servermap?size gt 0>
  	<#list servermap?keys as mykey> 
  		<#if mykey_index==0>
  			<div class="div-block" id="${mykey}" style="display:block;text-align:center;padding-top:10%;">
  				<#list servermap[mykey] as sl>
  					<p>${(sl.classify.name)?if_exists}：<span>${(sl.price)?if_exists}</span></p>
				</#list>
			</div>
  		<#else>
  			<div class="div-block" id="${mykey}" style="display:none;text-align:center;padding-top:10%;">
			    <#list servermap[mykey] as sl>
  					<p>${(sl.classify.name)?if_exists}：<span>${(sl.price)?if_exists}</span></p>
				</#list>
			</div>
  		</#if>
	</#list> 
</#if>

