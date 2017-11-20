$(function() {
	// 上传图片
	$("#j_image").live("change",function() {
		var isImg = false;
		var image = $("#preimage");
		var file = $("#j_image").val();
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
				url : "/admin/sucai/ajaxUploadPic",
				type : 'POST',
				dataType : 'json',
				fileElementId : 'j_image',
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
	// 预览
	$(".preview").live("click", function() {
		var title = $(".titles").val();
		var auth = $(".auth").val();
		var content = $(".editor").val();
		$.ajax({
			type : "POST",
			url : "/admin/sucai/ajaxpreview",
			data : {
				'title' : title,
				'auth' : auth,
				'content' : content
			},
			success : function(rt) {
				$(".content_top_wrapper").html(rt);
				$("#content_top").show();
				$(".right").hide();
			}
		});

	});
	$(".closepreview").live("click", function() {
		$("#content_top").hide();
		$(".right").show();
	});
})

/*function previewToutiao(){
	var title = $(".title").val();
	var auth = $(".auth").val();
	var content = $(".editor").val();
	window.open("/admin/sucai/preview?title="+title+"&auth="+auth+"&content="+content); //在另外新建窗口中打开窗口
};*/