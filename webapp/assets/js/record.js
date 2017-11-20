$(function() {
	$(".btn").click(function() {
		commit();
	});
});
function commit() {
	var classifyid = $(".classifyid").val();
	var recordTime1 = $(".recordTime1").val();
	var height = $(".height").val();
	var weight = $(".weight").val();
	var rule = /^[+]?[\d]+(([\.]{1}[\d]+)|([\d]*))$/;// 正数或0
	if (classifyid == "") {
		alert("请选择记录类型");
		return false;
	}
	if (recordTime1 == "") {
		alert("请填写记录时间");
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
	$("#record-form").submit();
}