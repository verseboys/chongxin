$(function() {
	// 上传图片
	$("#logo_image").live("change", function() {
		var isImg = false;
		var file = $("#logo_image").val();
		var objtype = file.substring(file.lastIndexOf(".")).toLowerCase();
		var fileType = new Array(".png", ".gif", ".jpeg", ".jpg");
		for ( var i = 0; i < fileType.length; i++) {
			if (objtype == fileType[i]) {
				isImg = true;
				break;
			}
		}
		if (isImg) {
			$.ajaxFileUpload({
				url : "/member/doghouse/ajaxUploadPic",
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
						$("#logo img").attr("src", data.name);
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

	$("#banner_image").live("change", function() {
		var isImg = false;
		// var image = $("#bannerpre");
		var file = $("#banner_image").val();
		var objtype = file.substring(file.lastIndexOf(".")).toLowerCase();
		var fileType = new Array(".png", ".gif", ".jpeg", ".jpg");
		for ( var i = 0; i < fileType.length; i++) {
			if (objtype == fileType[i]) {
				isImg = true;
				break;
			}
		}
		if (isImg) {
			$.ajaxFileUpload({
				url : "/member/doghouse/ajaxUploadPic",
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
						$("#banner img").attr("src", data.name);
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

	$(".btn").click(function() {
		commit();
	});
})

function commit() {
	var name = $(".name").val();
	var telephone = $(".telephone").val();
	var address = $(".address").val();
	// var avg_price = $(".avg_price").val();
	var rule1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;// 手机号
	var rule2 = /^0\d{2,3}-?\d{7,8}$/;// 电话
	// var rule2 = /((^(?!0)\d+)|(^(?=0)\d$))/;//0或正整数
	if (name == "") {
		alert("请输入店名");
		return false;
	}
	var telephoneReg1 = rule1.test(telephone);
	var telephoneReg2 = rule2.test(telephone);
	if (telephoneReg1 == false && telephoneReg2 == false) {
		alert("请填写正确的电话或手机号");
		return false;
	}
	if (address == "") {
		alert("请填写地址");
		return false;
	}
	/*
	 * var avg_priceReg = rule2.test(avg_price); if (avg_priceReg == false) {
	 * alert("请填写正确的平均价格"); return false; }
	 */
	window.EditorObject.sync("content7");
	$("#doghouse-form").submit();
}