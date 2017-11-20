<script type="text/javascript">
$(function() {
	// 上传图片
	$("#logo_image").live("change",function() {
		var isImg = false;
		var image = $("#logopre");
		var file = $("#logo_image").val();
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
			// alert(objtype);
			$.ajaxFileUpload({
				url : "/admin/subject/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'logo_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".logo").val(data.name);
						$(image).html("<label>logo：</label> <image src='" + data.name + "' style='max-height:200px;max-width:200px;'/>");
					}

				},
				error : function(data) {
					alert('加载出错');
				}

			});
		} else {
			alert("图片限于png,gif,jpeg,jpg格式");
		}

	});
	
	$("#banner_image").live("change",function() {
		var isImg = false;
		var image = $("#bannerpre");
		var file = $("#banner_image").val();
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
			// alert(objtype);
			$.ajaxFileUpload({
				url : "/admin/subject/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'banner_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".banner").val(data.name);
						$(image).html("<label>banner：</label> <image src='" + data.name + "' style='max-height:200px;max-width:200px;'/>");
					}

				},
				error : function(data) {
					alert('加载出错');
				}

			});
		} else {
			alert("图片限于png,gif,jpeg,jpg格式");
		}

	});
})
</script>
<div class="pageContent">
	<form method="post" action="/admin/subject/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="deleted"  value="0">
		<input type="hidden" name="domain1"  value="">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>专题名：</label>
				<input name="name" type="text" value="" size="30"/>
			</p>
			<p>
				<label>域名：</label>
				<input name="domain" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<div id="logopre">
					<label>logo：</label>
					<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="logo_image" value=""/> 
		            <input name="logo" value="" class="logo" type="hidden"/>	
				</div>
			</div>
			<!--
			<div class="divider"></div>
			<div>
				<div id="bannerpre">
					<label>banner：</label>
					<img src="/assets/img/default.jpg" style="max-height:200px;max-width:200px;"/>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="banner_image" value=""/> 
		            <input name="banner" value="" class="banner" type="hidden"/>	
				</div>
			</div>
			-->
			<div class="divider"></div>
			<div>
				<label>简介：</label>
				<textarea class="editor" name="summary" rows="20" cols="100"
					upImgUrl="/upload/newsImage/upload/subject2" upImgExt="jpg,jpeg,gif,png">
					
				</textarea>
			</div>
			<div class="divider"></div>
			<div>
				<label>内容：</label>
				<textarea class="editor" name="content" rows="20" cols="100"
					upImgUrl="/upload/newsImage/upload/subject2" upImgExt="jpg,jpeg,gif,png">
					
				</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
