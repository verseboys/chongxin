<script type="text/javascript">
$(function() {
	// 上传图片
	$("#avatar_image").live("change",function() {
		var isImg = false;
		var file = $("#avatar_image").val();
		var objtype = file.substring(file.lastIndexOf("."))
				.toLowerCase();
		var fileType = new Array(".png", ".gif", ".jpeg",
				".jpg");
		for ( var i = 0; i < fileType.length; i++) {
			if (objtype == fileType[i]) {
				isImg = true;
				break;
			}
		}
		if (isImg) {
			$.ajaxFileUpload({
				url : "/admin/account/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'avatar_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".avatar").val(data.name);
						$("#avatar img").attr("src", data.name);
					}

				},
				error : function(data) {
					alert('加载出错');
				}

			});
		}

	});
})
</script>
<div class="pageContent">
	<form method="post" action="/admin/account/updateconfirm" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(dto.uid)?if_exists}">
		<input type="hidden" name="roleid"  value="${roleid?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<div>
				<div id="avatar">
					<label>头像：</label>
					<#if (dto.avatar)?exists && (dto.avatar)!="">
						<img src="${(dto.avatar)?if_exists}" style="max-height:200px;max-width:200px;"/>
					<#else>
						<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
					</#if>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="avatar_image" value=""/> 
		            <input name="profile.avatar" value="${(bean.profile.avatar)?if_exists}" class="avatar" type="hidden"/>	
				</div>
			</div>
			<div class="divider"></div>
			<p>
				<label>用户名：</label>
				<input type="text" value="${(bean.username)?if_exists}" name="username" class="required" size="30"/>
			</p>
			<p>
				<label>昵称：</label>
				<input type="text" value="${(bean.profile.nickName)?if_exists}" name="profile.nickName" class="required" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>真实姓名：</label>
				<input type="text" value="${(bean.profile.trueName)?if_exists}" name="profile.trueName" size="30"/>
			</p>
			<p>
				<label>性别：</label>
				<select class="combox" name="profile.sex">
					<option value="1" <#if (dto.sex)?exists && (dto.sex)==1>selected="selected"</#if>>男</option>
					<option value="2" <#if (dto.sex)?exists && (dto.sex)==2>selected="selected"</#if>>女</option>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>手机：</label>
				<input type="text" value="${(dto.mobile)?if_exists}" name="profile.mobile" size="30"/>
			</p>
			<p>
				<label>家庭地址：</label>
				<input type="text" value="${(dto.address)?if_exists}" name="profile.address" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>所在地：</label>
				<select class="combox" name="fcityid" ref="w_combox_classify" refUrl="/admin/account/city_{value}">
					<#if fcities?exists && fcities?size gt 0>
						<#list fcities as ls>
							<option value="${(ls.id)?if_exists}" <#if (_city.pid)?exists && (_city.pid)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
				<select class="combox" name="cityid1" id="w_combox_classify">
					<#if cities?exists && cities?size gt 0>
						<#list cities as ls>
							<option value="${(ls.id)?if_exists}" <#if (_city.id)?exists && (_city.id)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>密码：</label>
				<input type="text" value="" name="password" size="30"/>
			</p>
			<div class="divider"></div>
			<p style="width:400px;">
				<label>ClientId：</label>
				<input type="text" value="${(bean.clientid)?if_exists}" readonly="readonly" size="35"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>是否认证：</label>
				<select class="combox" name="checked">
					<option value="0" <#if (bean.checked)?exists && (bean.checked)==0>selected="selected"</#if>>不认证</option>
					<option value="1" <#if (bean.checked)?exists && (bean.checked)==1>selected="selected"</#if>>认证</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
