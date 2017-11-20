$(function() {
	$("#avatar_image").live("change", function() {
		var isImg = false;
		var file = $("#avatar_image").val();
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
				url : "/member/dog/ajaxUploadPic",
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
		} else {
			alert("图片限于png,gif,jpeg,jpg格式");
		}

	});
	$(".btn").click(function() {
		commit();
	});
});

function commit() {
	var categoryId = $(".categoryId").val();
	var name = $.trim($(".name").val());
	var sex = $(".sex").val();
	var height = $.trim($(".height").val());
	var weight = $.trim($(".weight").val());
	var birthday1 = $(".birthday1").val();
	var rule = /^[+]?[\d]+(([\.]{1}[\d]+)|([\d]*))$/;// 正数或0
	if (categoryId == "") {
		alert("请选择犬种");
		return false;
	}
	if (name == "") {
		alert("请填写犬名");
		return false;
	}
	if (sex == "") {
		alert("请选择性别");
		return false;
	}
	var heightReg = rule.test(height);
	if (heightReg == false) {
		alert("请输入正确的肩高！");
		return false;
	}
	var weightReg = rule.test(weight);
	if (weightReg == false) {
		alert("请输入正确的体重！");
		return false;
	}
	if (birthday1 == "") {
		alert("请填写生日");
		return false;
	}
	$("#dog-form").submit();
}

function querydog(_type, _pageindex) {
	var petid = $("#petid").val();
	if (typeof (_pageindex) == "undefined") {
		_pageindex = 1;
	}
	var openUrl = '/member/dog/querydog?dogid='+petid+'&type='+_type+'&pageNum='+_pageindex;//弹出窗口的url
	var iWidth=800; //弹出窗口的宽度;
	var iHeight=600; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	window.open(openUrl,"芯片号查询","height="+iHeight+",width="+iWidth+",top="+iTop+",left="+iLeft+",scrollbars=yes,location=no,menubar=no");
}

function submitBlood(_type, _blood) {
	if (_type == 2) {
		window.opener.document.getElementById('fatherBlood').value=_blood;
	} else {
		window.opener.document.getElementById('matherBlood').value=_blood;
	}
	window.close();
}

function query() {
	$("#dogform").submit();
}

function querydog1(_type, _pageindex) {
	var petid = $("#petid").val();
	if (typeof (_pageindex) == "undefined") {
		_pageindex = 1;
	}
	$.ajax({
		type : "POST",
		url : "/member/dog/querydog",
		data : {
			'dogid' : petid,
			'type' : _type,
			'pageNum' : _pageindex
		},
		success : function(rt) {
			var _class = ".formbody" + _type;
			$(_class).empty();
			$(_class).html(rt);
			$(".formbody1").css("display", "none");
			$(".formbody2").css("display", "none");
			$(".formbody3").css("display", "none");
			$(_class).css("display", "block");
		}
	});
}

function submitBlood1(_type, _blood) {
	if (_type == 2) {
		$(".fatherBlood").val(_blood);
	} else {
		$(".matherBlood").val(_blood);
	}
	$(".formbody2").css("display", "none");
	$(".formbody3").css("display", "none");
	$(".formbody1").css("display", "block");
}
