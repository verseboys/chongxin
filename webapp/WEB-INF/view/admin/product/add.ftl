<script type="text/javascript">
$(function() {
	// 上传图片
	$("#imgurl_image").live("change",function() {
		var isImg = false;
		var file = $("#imgurl_image").val();
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
				url : "/admin/product/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'imgurl_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".imgurl").val(data.name);
						$("#imgurl img").attr("src", data.name);
					}

				},
				error : function(data) {
					alert('加载出错');
				}

			});
		}

	});
	
	$("#imgsmall_image").live("change",function() {
		var isImg = false;
		var file = $("#imgsmall_image").val();
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
				url : "/admin/product/ajaxUploadSmallPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'imgsmall_image',
				success : function(data) {
					if (data.status == '0') {
						alert("图片不能大于5M！");
					} else if (data.status == '1') {
						alert("服务端异常！");
					} else {
						$(".imgsmall").val(data.name);
						$("#imgsmall img").attr("src", data.name);
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
	<form method="post" action="/admin/product/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="deleted"  value="0">
		<input type="hidden" name="count"  value="0">
		<input type="hidden" name="type"  value="0">
		<div class="pageFormContent" layoutH="56">
			<div>
				<div id="imgurl">
					<label>产品图片：</label>
					<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="imgurl_image" value=""/> 
		            <input name="imgurl" value="" class="imgurl" type="hidden"/>	
				</div>
			</div>
			<div class="divider"></div>
			<div>
				<div id="imgsmall">
					<label>产品小图：</label>
					<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file2" id="imgsmall_image" value=""/> 
		            <input name="imgsmall" value="" class="imgsmall" type="hidden"/>	
				</div>
			</div>
			<div class="divider"></div>
			<p>
				<label>产品名：</label>
				<input name="product" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>产品价格：</label>
				<input name="price" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>市场价格：</label>
				<input name="marketprice" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>单位：</label>
				<input name="unit" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>上市时间：</label>
				<input type="text" name="publish" class="date" readonly="true" value="${publish?if_exists}"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<div class="divider"></div>
			<p>
				<label>商城产品：</label>
				<select class="combox" name="ismall">
					<option value="0" >宠信宝</option>
					<option value="1" selected="selected">商城产品</option>
				</select>
			</p>
			<div class="divider"></div>
			<p>
				<label>商品详情：</label>
				<textarea class="editor" name="detail" rows="20" cols="100"
					upImgUrl="/upload/newsImage/upload" upImgExt="jpg,jpeg,gif,png">
					
				</textarea>
			</p>
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
