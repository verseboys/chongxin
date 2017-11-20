<div data-role="page" id="myprofile">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>我的资料</h1>
  	</div>
  	<div data-role="main" class="ui-content">
   		<ul data-role="listview">
		  <li><a href="#myprofile-nickname">昵称<span class="ui-li-count" id="profile_nickname">昵称</span></a></li>
		  <li><a href="#myprofile-mobile">手机号<span class="ui-li-count" id="profile_mobile">手机号码</span></a></li>
		  <li><a href="#myprofile-sex">性别<span class="ui-li-count" id="profile_sex"></span></a></li>
		  <li><a href="#myprofile-birthday">生日<span class="ui-li-count" id="profile_birthday"></span></a></li>
		  <li><a href="#myprofile-address">所在地区<span class="ui-li-count" id="profile_address">。</span></a></li>
		</ul>
    </div>
</div>
<div data-role="page" id="myprofile-nickname">
	<div data-role="header">
		<a href="#myprofile" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>昵称</h1>
    	<a href="#" data-role="button" data-icon="check" id="savenicknamebutton">保存</a>
  	</div>
  	<div class="ui-field-contain">
        <input type="text" name="fullname" id="input_profile_nickname">       
    </div>
</div>
<div data-role="page" id="myprofile-mobile">
	<div data-role="header">
		<a href="#myprofile" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>手机号码</h1>
    	<a href="#" data-role="button" data-icon="check" id="savemobilebutton">保存</a>
  	</div>
  	<div class="ui-field-contain">
        <input type="text" name="fullname" id="input_profile_mobile">       
    </div>
</div>
<div data-role="page" id="myprofile-sex">
	<div data-role="header">
		<a href="#myprofile" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>性别</h1>
    	<a href="#" data-role="button" data-icon="check"  id="savesexbutton">保存</a>
  	</div>
  	<fieldset data-role="controlgroup">
        <label for="male">男性</label>
        <input type="radio" name="input_profile_sex" id="male" value="1">
        <label for="female">女性</label>
        <input type="radio" name="input_profile_sex" id="female" value="2"> 
     </fieldset>
</div>

<div data-role="page" id="myprofile-address">
	<div data-role="header">
		<a href="#myprofile" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>地址</h1>
    	<a href="#myprofile" data-role="button" data-icon="check"  id="saveaddressbutton">保存</a>
  	</div>
  	<div class="ui-field-contain">
        <input type="text" name="fullname" id="input_profile_address">       
    </div>
</div>
<div data-role="page" id="myprofile-birthday">
	<div data-role="header">
		<a href="#myprofile" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>生日</h1>
    	<a href="#myprofile" data-role="button" data-icon="check"  id="savebirthdaybutton">保存</a>
  	</div>
  	<div class="ui-field-contain">
        <input type="date" name="fullname" id="input_profile_birthday">       
    </div>
</div>