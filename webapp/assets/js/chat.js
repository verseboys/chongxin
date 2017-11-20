$(function() {
	$(".btn1").click(function() {
		commit1();
	});
	$(".btn2").click(function() {
		commit2();
	});
})
function commit1() {
	var content1 = $(".content1").val();
	var uid = $(".uid").val();
	var touid = $(".touid").val();
	if (content1 == "") {
		alert("不能发送空消息");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/member/message/chat",
		data : {
			'content' : content1,
			'uid' : uid,
			'touid' : touid,
			'type' : 0
		},
		success : function(rt) {
			$(".content1").val("");
			$(".rightinfo").empty();
			$(".rightinfo").html(rt);
		}
	});
}

function commit2() {
	var content2 = $(".content2").val();
	var uid = $(".uid").val();
	var touid = $(".touid").val();
	if (content2 == "") {
		alert("不能发送空消息");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "/member/message/chat",
		data : {
			'content' : content2,
			'uid' : uid,
			'touid' : touid,
			'type' : 1
		},
		success : function(rt) {
			$(".content2").val("");
			$("#content2 img").attr("src", "/assets/img/default.png");
			$(".rightinfo").empty();
			$(".rightinfo").html(rt);
		}
	});
}
