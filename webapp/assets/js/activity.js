var token = $.cookie("k9token");

function baoming(aid){
	var tel = $(".mobileinput").val(); //获取手机号
	var telReg = !!tel.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	//如果手机号码不能通过验证
	if(telReg == false){
 		alert("请输入正确的手机号！");
 		return false;
	}
	$.ajax({
		type : "POST",
		url : "/server/activity/save?sid="+token,
		dataType : "json",
		contentType : "application/json",
		data : '{"mobile":"'+tel+'","aid":'+aid+'}',
		success : function(dat) {
			if(dat.state==0){
				$("#noalreadyhtml").html($("#alreadyhtml").html());
			}
		}
	});
	
}