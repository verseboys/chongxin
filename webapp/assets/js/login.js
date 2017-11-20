$(function() {
	$(".login-btn").click(function() {
		login();
	});
	$(".register-btn").click(function() {
		register();
	});
	$(".bind-btn").click(function() {
		bind();
	});
})
function login() {
	var username = $("#account").val();
	var password = $("#password").val();
	/*
	 * var rule1 =
	 * /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;//
	 * 手机号 var telephoneReg = rule1.test(username); if (telephoneReg == false) {
	 * alert("请填写正确的手机号"); return false; }
	 */

	if (username == "") {
		alert("请输入用户名");
		return false;
	}

	if (password == "") {
		alert("请输入密码");
		return false;
	}
	$("#login-form").submit();
}
function register() {
	var username = $("#register-account").val();
	var password = $("#register-password").val();
	var password1 = $("#register-password1").val();
	var phone = $("#register-phone").val();
	if (username == "") {
		alert("请输入用户名");
		return false;
	}
	if (password == "") {
		alert("请输入密码");
		return false;
	}
	if (password1 == "") {
		alert("请确认密码");
		return false;
	}
	if (phone == "") {
		alert("请确认手机号");
		return false;
	}
	if (password != password1) {
		alert("密码确认不一致");
		return false;
	}
	$("#register-form").submit();
}
function bind() {
	var username = $("#bind-account").val();
	var password = $("#bind-password").val();
	if (username == "") {
		alert("请输入用户名");
		return false;
	}
	if (password == "") {
		alert("请输入密码");
		return false;
	}
	$("#bind-form").submit();
}