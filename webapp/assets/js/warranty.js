$(function() {

	$(".blood")[0].focus();
	// 导航切换
	$(".imglist li").click(function() {
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	});
	$(".blood").blur(function() {
		var blood = $.trim($(".blood").val());
		if (blood == "") {
			$(".error1").css("display", "block");
		}
		$.ajax({
			type : "POST",
			url : "/member/warranty/querydog",
			data : {
				'blood' : blood
			},
			success : function(rt) {
				if (rt == 0) {
					$(".error2").css("display", "block");
				}
			}
		});
	});
	$(".blood").focus(function() {
		$(".error1").css("display", "none");
		$(".error2").css("display", "none");
	});

	$(".btn").click(function() {
		commit();
	});
});
function query() {
	$("#warrantyform").submit();
}
function commit() {
	var blood = $.trim($(".blood").val());
	var price = $.trim($(".price").val());
	var rule = /^[+]?[\d]+(([\.]{1}[\d]+)|([\d]*))$/;// 正数或0
	if (blood == "") {
		alert("芯片号不能为空！");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/member/warranty/querydog",
		data : {
			'blood' : blood
		},
		success : function(rt) {
			if (rt == 0) {
				alert("芯片号不正确，或宠物信息还未录入！");
				return false;
			}
		}
	});
	var priceReg = rule.test(price);
	if (priceReg == false) {
		alert("请输入正确的价格！");
		return false;
	}
	$("#warranty-form").submit();
}
