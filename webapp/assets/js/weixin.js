var user = new Object();
var token = $.cookie("k9token");
// var petid = $("#petid").val();

$(document).on("pageshow", "#myprofile", function() {
	userinfo();
});

$(document).on("pageshow", "#mydogs", function() {
	loaddogs();
});

$(document).on("pageshow", "#doginfor", function() {
	doginfo();
});

$(document).on("pagebeforeshow", "#myprofile1", function() {
	checkuserinfo();
});

$(document).on("pagebeforeshow", "#partnerinfor", function() {
	partnerinfo();
});

$(function() {
	$("#savenicknamebutton").click(function() {
		checkuserinfo();
	});
	$("#savemobilebutton").click(function() {
		checkuserinfo();
	});
	$("#savesexbutton").click(function() {
		checkuserinfo();
	});
	$("#savebirthdaybutton").click(function() {
		checkuserinfo();
	});
	$("#saveaddressbutton").click(function() {
		checkuserinfo();
	});
	// 宠物
	$("#adddogbutton").click(function() {
		$.mobile.changePage("#adddog");
	});
	$("#savedogbutton").click(function() {
		checkdoginfo();
	});
	$("#savedoginfor2").click(function() {
		checkdog();
	});
	$("#savedogavatar").click(function() {
		checkdogavatar();
	});
	$("#saveorderbutton").click(function() {
		saveOrder();
	});
	$("#saveorderaddress").click(function() {
		saveorderaddress();
	});
});
function saveorderaddress() {
	var address = $("#input_order_address").val();
	$("#yuyue_address").attr("value", address);
	$("#server_address").html(address);
}
function checkdoginfo() {
	var avatar = $('#j_thumb').val();
	var fname = $('#fname').val();
	var fclass = $('#fclass').val();
	var sex = $('input[name="gender"]:checked').val();
	var chip = $('#fchip').val();
	if (avatar == "") {
		// alert("上传图片");
		$('#filesupload').css('border', '1px solid red');
		return false;
	}
	$('#filesupload').css('border', '1px');
	if (fname == "") {
		$('#fname').css('border', '1px solid red');
		return false;
	}
	$('#fname').css('border', '1px');
	if (fclass == "") {
		$('#fclass').css('border', '1px solid red');
		return false;
	}
	$('#fclass').css('border', '1px');
	if (chip == "") {
		$('#chip').css('border', '1px solid red');
		return false;
	}
	$('#chip').css('border', '1px');
	uploadPic(avatar, fname, fclass, sex, chip);
}

function saveOrder() {
	var name = $("#yuyue_name").val();
	var mobile = $("#yuyue_mobile").val();
	var address = $("#yuyue_address").val();
	if (name == "") {
		$('#yuyue_name').css('border', '1px solid red');
		return;
	}
	if (mobile == "" || mobile.length != 11) {
		$('#yuyue_mobile').css('border', '1px solid red');
		return;
	}
	if (address == "" || address.length < 5) {
		$.mobile.changePage("#pageaddress");
		$('#input_order_address').css('border', '1px solid red');
		return;
	}
	$.ajax({
		type : "POST",
		url : "/server/order/save?sid=" + token,
		dataType : "json",
		contentType : "application/json",
		data : "{\"name\":" + name + ",\"mobile\":\"" + mobile
				+ "\",\"address\":\"" + address + "\"}",
		success : function(dat) {
			if (dat.state == 0) {
				location.reload();
			}
		}
	});
}

function uploadPic(avatar, fname, fclass, sex, chip) {
	$.ajax({
		type : "POST",
		url : "/server/upload/filesapp/dog?sid=" + token,
		dataType : "json",
		data : {
			'pic' : avatar
		},
		success : function(rt) {
			var photo = rt.data.photo;
			savedog(fname, fclass, sex, chip, photo);
		}
	});
}

function savedog(fname, fclass, sex, chip, photo) {
	var jsonStr = "{\n" + "\"avatar\": \"" + photo + "\",\n" + "\"sex\": "
			+ sex + ",\n" + "\"name\": \"" + fname + "\",\n" + "\"classify\": "
			+ fclass + ",\n" + "\"chip\": \"" + chip + "\"\n" + "}";
	$.ajax({
		type : "POST",
		url : "/server/dog/save?sid=" + token,
		dataType : "json",
		data : {
			'json' : jsonStr
		},
		success : function(rt) {
			$.mobile.changePage("#mydogs");
		}
	});
}

function userinfo() {
	$.ajax({
		type : "post",
		dataType : "json",// 返回json格式的数据
		contentType : "application/json",
		url : "/server/user/load?sid=" + token, // 要访问的后台地址
		data : '{"uid":0}',// 要发送的数据
		complete : function() {
			$("#load").hide();
		},// AJAX请求完成时隐藏loading提示
		success : function(msg) {// msg为返回的数据，在这里做数据绑定
			var state = msg.state;
			if (state == 0) {
				user = msg.data;
				userinfoshow(user);
			} else {
				alert(msg.errormsg);
			}
		}
	});
}

function loaddogs() {
	$.ajax({
		type : "post",
		dataType : "json",// 返回json格式的数据
		contentType : "application/json",
		url : "/server/dog/list?sid=" + token, // 要访问的后台地址
		data : '{"uid":0,"page":1,size:30}',// 要发送的数据
		complete : function() {
			$("#load").hide();
		},// AJAX请求完成时隐藏loading提示
		success : function(msg) {// msg为返回的数据，在这里做数据绑定
			var state = msg.state;
			if (state == 0) {
				dogs = msg.data;
				dogsshow(dogs);
			} else {
				alert(msg.errormsg);
			}
		}
	});
}

// 宠物列表显示
function dogsshow(dogs) {
	$("#dogsul").empty();
	for ( var d in dogs) {
		// alert(" avatar:" +dogs[d].avatar + " name:" + dogs[d].name);
		var avatar = dogs[d].avatar;
		// avatar = avatar.split("_")[0] + "_92-92.jpg";
		avatar = avatar + "!avatar92";
		$("#dogsul")
				.append(
						"<li class=\"ui-li-has-thumb\"><a href=\"#doginfor\" onclick=\"setpetid('"
								+ dogs[d].petid
								+ "')\" style=\"height:80px;line-height:80px;\" class=\"ui-btn ui-btn-icon-right ui-icon-carat-r\"><img src=\""
								+ avatar
								+ "\"  style=\"margin:10px 0px 10px 10px;border-radius:40px;\">"
								+ "\n" + dogs[d].name + "\n" + "</a></li>");
	}
}

function setpetid(petid) {
	$("#petid").val(petid);
}

function doginfo() {
	var petid = $("#petid").val();
	$.ajax({
		type : "post",
		url : "/weixin/loaddog?sid=" + token + "&petid=" + petid, // 要访问的后台地址
		success : function(msg) {// msg为返回的数据，在这里做数据绑定

			$("#doginfordiv").html(msg);

		}
	});
}

// 显示用户信息
function userinfoshow(user) {
	$("#profile_nickname").html(user.nickname);
	$("#input_profile_nickname").val(user.nickname);
	$("#profile_mobile").html(user.mobile);
	$("#input_profile_mobile").attr("value", user.mobile);
	$("#profile_birthday").html(user.birthday);
	$("#input_profile_birthday").val(user.birthday);
	$("#profile_sex").html(user.sexname);
	$("input[name='input_profile_sex'][value=" + user.sex + "]").attr(
			"checked", true);
	$("#profile_address").html(user.address);
	$("#input_profile_address").attr("value", user.address);

}
function saveuser(djson) {
	$.ajax({
		type : "post",// 使用get方法访问后台
		dataType : "json",// 返回json格式的数据
		contentType : "application/json",
		url : "/server/user/update?sid=" + token, // 要访问的后台地址
		data : djson,// 要发送的数据
		complete : function() {
			$("#load").hide();
		},// AJAX请求完成时隐藏loading提示
		success : function(msg) {// msg为返回的数据，在这里做数据绑定
			var state = msg.state;
			if (state == 0) {
				user = msg.data;
				$.mobile.changePage("#myprofile");
			} else {
				alert(msg.errormsg);
			}

		}
	});
}

function checkuserinfo() {
	if (typeof (user.nickname) == "undefined") {
		return;
	}
	var nickname = $("#input_profile_nickname").val();

	if (nickname != user.nickname) {
		// user.nickname = nickname;
		var json = "{\"nickname\":\"" + nickname + "\"}";
		saveuser(json);
	}
	var mobile = $("#input_profile_mobile").val();
	if (mobile != user.mobile) {
		var json = "{\"mobile\":\"" + mobile + "\"}";
		saveuser(json);
	}
	var sex = $('input:radio[name=input_profile_sex]:checked').val();
	if (sex != user.sex) {
		var json = "{\"sex\":" + sex + "}";
		saveuser(json);
	}
	var address = $("#input_profile_address").val();
	if (address != "" && address != user.address) {
		var json = "{\"address\":" + address + "}";
		saveuser(json);
	}
	var birthday = $("#input_profile_birthday").val();
	if (birthday != user.birthday) {
		var json = "{\"birthday\":" + birthday + "}";
		saveuser(json);
	}
}

function checkdogavatar() {
	var avatar = $('#dogj_thumb').val();
	petid = $("#petid").val();
	if (avatar == "") {
		// alert("上传图片");
		$('#dog_filesupload').css('border', '1px solid red');
		return false;
	}
	$('#filesupload').css('border', '1px');
	uploadPic2(avatar);
}

function checkdog() {

	var favatar = $('#input_dogavatar1').val();
	favatar = 'dog-' + favatar.split('dog-')[1];

	var fname = $('#input_dogname').val();
	var fname1 = $('#input_dogname1').val();

	var fclass = $('#dogclass_select').val();
	var fclass1 = $('#input_dogclass1').val();

	var sex = $('#dogsex_select').val();
	var sex1 = $('#input_dogsex1').val();

	var chip = $('#input_dogchip').val();
	var chip1 = $('#input_dogchip1').val();
	petid = $("#petid").val();

	if (fname == "") {
		$('#input_dogname').css('border', '1px solid red');
		return false;
	}
	$('#input_dogname1').val(fname);
	$('#input_dogname').css('border', '1px');
	$('#input_dogclass1').val(fclass);
	$('#input_dogsex1').val(sex);
	if (chip == "") {
		$('#input_dogchip').css('border', '1px solid red');
		return false;
	}
	$('#input_dogchip1').val(chip);
	$('#input_dogchip').css('border', '1px');
	var json = "{" + "\"petid\": \"" + petid + "\"," + "\"avatar\": \""
			+ favatar + "\"," + "\"name\": \"" + fname + "\","
			+ "\"classify\": " + fclass + "," + "\"sex\": " + sex + ","
			+ "\"chip\": \"" + chip + "\"" + "}";
	updatedog(json, "");
}

function uploadPic2(avatar) {
	$
			.ajax({
				type : "POST",
				url : "/server/upload/filesapp/dog?sid=" + token,
				dataType : "json",
				data : {
					'pic' : avatar
				},
				success : function(rt) {
					var photo = rt.data.photo;
					var json = "{\"petid\":" + petid + ",\"avatar\":\"" + photo
							+ "\"}";
					updatedog(json, "avatar");
				}
			});
}

function updatedog(djson, avatar) {
	$.ajax({
		type : "post",
		dataType : "json",// 返回json格式的数据
		contentType : "application/json",
		url : "/server/dog/save?sid=" + token, // 要访问的后台地址
		data : djson,// 要发送的数据
		complete : function() {
			$("#load").hide();
		},// AJAX请求完成时隐藏loading提示
		success : function(msg) {// msg为返回的数据，在这里做数据绑定
			var state = msg.state;
			if (state == 0) {
				if (avatar == "avatar") {
					$.mobile.changePage("#doginfor");
				}
			} else {
				alert(msg.errormsg);
			}

		}
	});
}