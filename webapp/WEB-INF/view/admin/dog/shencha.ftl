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
				url : "/admin/dog/ajaxUploadPic",
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
	<form method="post" action="/admin/dog/updateconfirm" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<input type="hidden" name="accountId"  value="${accountId?if_exists}">
		<input type="hidden" name="havablood"  value="${havablood?if_exists}">
		<input type="hidden" name="oldisok"  value="${(bean.isok)?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<div>
				<div id="avatar">
					<label>宠物头像：</label>
					<#if (dto.avatar)?exists && (dto.avatar)!="">
						<img src="${(dto.avatar)?if_exists}" style="max-height:200px;max-width:200px;"/>
					<#else>
						<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
					</#if>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="avatar_image" value=""/> 
		            <input name="avatar" value="${(bean.profile.avatar)?if_exists}" class="avatar" type="hidden"/>	
				</div>
			</div>
			<div class="divider"></div>
			<p>
				<label>芯片号：</label>
				<input type="text" name="blood" value="${(bean.blood)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>犬种：</label>
				<select class="combox" name="categoryId">
					<#if beans?exists && beans?size gt 0>
						<#list beans as ls>
							<option value="${(ls.id)?if_exists}" <#if (bean.categoryId)?exists && (bean.categoryId)==ls.id>selected="selected"</#if>>${(ls.name)?if_exists}</option>
						</#list>
					</#if>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>宠物名字：</label>
				<input type="text" name="name" value="${(bean.name)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>性别：</label>
				<select class="combox" name="sex">
					<option <#if (bean.sex)?exists && (bean.sex)==1>selected="selected"</#if> value="1">男</option>
					<option <#if (bean.sex)?exists && (bean.sex)==2>selected="selected"</#if> value="2">女</option>
				</select>
			</p>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>父亲芯片：</dt>
				<dd>
					<input name="fatherBlood" value="${(bean.fatherBlood)?if_exists}" names="father.blood" type="text" lookupGroup="father"/>
					<a class="btnLook" href="/admin/dog/querydog?dogid=${(bean.id)?if_exists}" lookupGroup="father">查找用户</a>	
				</dd>
			</dl>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>母亲芯片：</dt>
				<dd>
					<input name="matherBlood" value="${(bean.matherBlood)?if_exists}" names="mather.blood" type="text" lookupGroup="mather"/>
					<a class="btnLook" href="/admin/dog/querydog?dogid=${(bean.id)?if_exists}" lookupGroup="mather">查找用户</a>	
				</dd>
			</dl>
			<div class="divider"></div>
			<p>
				<label>肩高(cm)：</label>
				<input type="text" name="height" value="${(bean.height)?default(0)}" size="30" class="number"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>体重(kg)：</label>
				<input type="text" name="weight" value="${(bean.weight)?default(0)}" size="30" class="number"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>出生日期：</label>
				<input type="text" value="${(bean.birthdayStr)?if_exists}" class="date required" dateFmt="yyyy-MM-dd" readonly="true" name="birthdayStr1"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<div>
				<label>简介：</label>
				<textarea cols="80" rows="5" name="intro">${(bean.intro)?if_exists}</textarea>
			</div>
			<div class="divider"></div>
			<p>
				<label>是否确认芯片号：</label>
				<select class="combox" name="isok">
					<option value="0" <#if (bean.isok)?exists && (bean.isok)==0>selected="selected"</#if>>不确认</option>
					<option value="1" <#if (bean.isok)?exists && (bean.isok)==1>selected="selected"</#if>>确认</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
