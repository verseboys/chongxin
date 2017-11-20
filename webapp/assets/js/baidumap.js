$(function() {
	$(".btn").click(function() {
		commit();
	});
})
function commit() {
	var longtitude = $("#longtitude").val();
	var latitude = $("#latitude").val();
	if (longtitude == "" || latitude == "") {
		alert("请右键标记位置");
		return false;
	}
	$("#baidumap-form").submit();
}
