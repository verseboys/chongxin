$(function() {
	$(".btn").click(function() {
		commit();
	});
	$(".combox1").change(function() {
		var fclassifyid = $(this).children('option:selected').val();
		var obj = $(".cityright");
		var pobj = $(".usercity");
		$.ajax({
			type : "POST",
			url : "/member/servers/classify_"+fclassifyid,
			success : function(rt) {
				if (rt != "") {
					$(obj).remove();
					$(pobj).append(rt);
				} else {
					alert("糟糕，出错了");
				}
			},
			error : function() {
				alert("糟糕，出错了");
			}
		});
	});
})
function commit() {
	var classifyid = $(".classifyid").val();
	var price = $(".price").val();
	var rule1 = /((^(?!0)\d+)|(^(?=0)\d$))/;//0或正整数
	if (classifyid == "") {
		alert("请选择服务类型");
		return false;
	}
	var priceReg = rule1.test(price);
	if (priceReg == false) {
		alert("请按要求填写价格");
		return false;
	}
	$("#servers-form").submit();
}
