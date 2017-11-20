<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="UTF-8">
<title>登陆</title>
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link rel="stylesheet" href="/assets/jquerymobile/jquery.mobile-1.4.5.min.css">
<script src="/assets/jquerymobile/jquery-2.1.4.min.js"></script>
<script src="/assets/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
<script src="/assets/js/jquery-cookie.js"></script>
<script src="/assets/js/ajaxfileupload.js" ></script>
<script>
$(document).on("pageinit","#pageone",function(){
	$.mobile.changePage("#pageone");

});
$(document).on("pageshow", "#pagemain", function(){
	loadFeeds();
});
function loadFeeds(){
	var token = $.cookie("k9token");
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/feed/list?sid="+token,//要访问的后台地址
            data: '{"start":0,"size":30}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
                var state = msg.state;
				var feeds = msg.data;
				var html = "<div data-role='content'>";
				
				for(i = 0; i < feeds.length; i++){
					var feed = feeds[i];
					html +="<div id='feed_"+feed.fid+"'>";
					html +="<div style='float:left;width:80px;clear:both'>";
					html +="<img src='"+feed.user.avatar+"' width=60 height=60><br>";
					html +=feed.user.nickname;
					html +="</div>";
					html +="<div style='float:left'>";
					html +="<div>"+feed.content+"</div>";
					var photos = feed.photos;
					if (typeof(photos) != "undefined") { 
						for(t =0;t < photos.length; t++){
						var photo = photos[t].photo;
						html +="<div style=\"img{max-width:500px}\"><img src='"+photo+"'></div>";
						}
					}
					
					html +="</div>";
					html +="</div>";
					html +="<div style=\"float:left;width:100%;margin:10px 0px 10px 0px;border-bottom:1px solid #666\"></div>"
				}
				html +="</div>";
				
				$("#feedwrap").html(html);
			}
		});
}   
function getYPhoto(photo){
	var extention = photo.substr(photo.length-4,4);
	var strs = photo.substr(0,photo.length-4);
	var names = strs.split("_");
	if(names.length==2){
		return names[0]+extention;
	}
	return photo;
}     
$(function(){
	$("#login").click(function(){
		login();
	}); 
	$("#savefeedbutton").click(function(){
		saveFeed();
	}); 
});
function login(){
	var name = $("#username").val();
	var password = $("#password").val();
	if(name=="" || password==""){
		alert("请填写 用户名密码")
		return;
	}
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/user/login",//要访问的后台地址
            data: '{"username":'+name+',"password":'+password+'}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
			
                var state = msg.state;
				var dat = msg.data;
				var token = msg.token;
				$.cookie("k9token", token);
				$.mobile.changePage("#pagemain");
			}
		});
}

//上传方法  
function ajaxUpload(){
   $.ajaxFileUpload  
   (    
        {   
            url: '/server/upload/files/feed?sid='+$.cookie("k9token"), //你处理上传文件的服务端  
            secureuri:false,  
            fileElementId:'files',  
            dataType: 'json',  
            success: function (msg, status)  
            {
            	//alert(msg.state); 
				//var datas = new Function("return" + data)();
				var jsons = msg.data;
				//alert(JSON.stringify(jsons));
				var imgs = "";
				for(i = 0; i < jsons.length; i++){
					var json = jsons[i];
					imgs = imgs + json.photo;
				}
				$("#feedimages").val(JSON.stringify(jsons));
				$("#feedimage").html(imgs);
            },  
            error: function (data, status, e)  
            {    
               alert(data+" error:"+e);
            }  
        }  

    );  
    return false;$
}  
function saveFeed(){
	var content = $("#feedcontent").val();
	var feedimages = $("#feedimages").val();
	$.ajax({
            type: "post",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
			contentType: "application/json", 
            url: "/server/feed/save?sid="+$.cookie("k9token"),//要访问的后台地址
            data: '{"content":'+content+',"photos":'+feedimages+'}',//要发送的数据
            complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
			success: function(msg){//msg为返回的数据，在这里做数据绑定
                var state = msg.state;
				var dat = msg.data;
				var token = dat.token;
				$.mobile.changePage("#pagemain");
			}
		});
}
</script>
<style>
div{line-height:35px}
img{max-width:500px}
</style>
</head>
 
<body>
    <div data-role="page"  class="wrap" id="pageone">
        <div class="intro">
            <div class="name">
            	 <label>用户名</label>
                <input type="text" name="username" id="username" value="cnyuqi">
            </div>
            <div class="job">
            	<label>密码</label>
                <input type="password" name="password" id="password" value="d5e0fcfd7e5fe33fe5de32f19de6057c">
            </div>
            <div class="mobile">
                <button id="login">按钮</button>
                
            </div>
        </div>
    </div>
<!--首页-->
	<div data-role="page" id="pagemain">
	  <div data-role="header">
	  <a></a>
		<h1>首 页</h1>
		<a href="#savefeed" data-role="button" data-icon="plus">发布</a>
	  </div>
	  <div data-role="content" id="feedwrap">
		<p>现在我已经！</p>
	  </div>
	  <div data-role="footer"  data-position="fixed">
	   <div data-role="navbar">
		  <ul>
			<li><a href="#pagemain" data-icon="home"  class="ui-btn-active ui-state-persist">首页</a></li>
			<li><a href="#pagefound" data-icon="search"  data-transition="none">发现</a></li>
			<li><a href="#pagemsg" data-icon="info"  data-transition="none">消息</a></li>
			<li><a href="#pagemy" data-icon="gear"  data-transition="none">我</a></li>
		  </ul>
		</div>
	  </div>
	</div>
<!--发现-->
	<div data-role="page" id="pagefound">
	  <div data-role="header">
		<h1>发现</h1>
	  </div>

	<div data-role="content">
		<h1></h1>
		<ul data-role="listview" data-inset="false">
			<li><a href="#pagezhidao"  data-transition="slide">知道</a></li>
		</ul>
		<h1 style="height:8px"></h1>
		<ul data-role="listview" data-inset="false">
			<li><a href="#">摇一摇</a></li>
			<li><a href="#">附近的人</a></li>
			<li><a href="#">寄养</a></li>
		</ul>
	</div>

	<div data-role="footer"  data-position="fixed">
		<div data-role="navbar">
			<ul>
				<li><a href="#pagemain" data-icon="home"   data-transition="none">首页</a></li>
				<li><a href="#" data-icon="search"  class="ui-btn-active ui-state-persist">发现</a></li>
				<li><a href="#pagemsg" data-icon="info" data-transition="none">消息</a></li>
				<li><a href="#pagemy" data-icon="gear" data-transition="none">我</a></li>
			</ul>
		</div>
		</div>
	</div>
<!--我-->
	<div data-role="page" id="pagemsg">
	  <div data-role="header">
		<h1>消息</h1>
	  </div>

	  <div data-role="content">
		<p>现在我已经发现</p>
	  </div>

	  <div data-role="footer"  data-position="fixed">
	   <div data-role="navbar">
		  <ul>
			<li><a href="#pagemain" data-icon="home"  data-transition="none">首页</a></li>
			<li><a href="#pagefound" data-icon="search"  data-transition="none">发现</a></li>
			<li><a href="#" data-icon="info" data-transition="none" class="ui-btn-active ui-state-persist">消息</a></li>
			<li><a href="#pagemy" data-icon="gear" data-transition="none">我</a></li>
		  </ul>
		</div>
	  </div>
	</div>
<!--我-->
	<div data-role="page" id="pagemy">
	  <div data-role="header">
		<h1>我</h1>
	  </div>

	  <div data-role="content">
		<p>现在我已经发现</p>
	  </div>

	  <div data-role="footer"  data-position="fixed">
	   <div data-role="navbar">
		  <ul>
			<li><a href="#pagemain" data-icon="home"  data-transition="none">首页</a></li>
			<li><a href="#pagefound" data-icon="search"  data-transition="none">发现</a></li>
			<li><a href="#pagemsg" data-icon="info"  data-transition="none">消息</a></li>
			<li><a href="#" data-icon="gear" data-transition="none" class="ui-btn-active ui-state-persist">我</a></li>
		  </ul>
		</div>
	  </div>
	</div>
	<!--知道-->
	<div data-role="page" id="pagezhidao">
		<div data-role="header">
			<a href="#pagefound" data-role="button" data-icon="arrow-l">发现</a>
			<h1>知道</h1>
		</div>

		<div data-role="content">
			<h1></h1>
			<ul data-role="listview" data-inset="false">
				<li><a href="#zhidao">知道</a></li>
			</ul>
		</div>
	</div>
<!--我-->
	<div data-role="page" id="pagemy">
	  <div data-role="header">
		<h1>我</h1>
	  </div>

	  <div data-role="content">
		<p>现在我已经发现</p>
	  </div>

	  <div data-role="footer"  data-position="fixed">
	   <div data-role="navbar">
		  <ul>
			<li><a href="#pagemain" data-icon="home"  data-transition="none">首页</a></li>
			<li><a href="#pagefound" data-icon="search"  data-transition="none">发现</a></li>
			<li><a href="#pagemsg" data-icon="info"  data-transition="none">消息</a></li>
			<li><a href="#" data-icon="gear" data-transition="none" class="ui-btn-active ui-state-persist">我</a></li>
		  </ul>
		</div>
	  </div>
	</div>
	<!--知道-->
	<div data-role="page" id="pagezhidao">
		<div data-role="header">
			<a href="#pagefound" data-role="button" data-icon="arrow-l">发现</a>
			<h1>知道</h1>
		</div>

		<div data-role="content">
			<ul data-role="listview" data-inset="false">
				<li><a href="#zhidao">知道</a></li>
			</ul>
		</div>
	</div>
	<!--发布动态-->
	<div data-role="page" id="savefeed">
	  <div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
		<h1>&nbsp;</h1>
		<a href="#pagemain" data-role="button" data-icon="check" id="savefeedbutton">发布</a>
	  </div>

		<div data-role="content">
			<form method="post" action="">
			  <textarea cols="40" rows="18" name="textarea" id="feedcontent"></textarea>
			  <input type="file" name="files" id="files" enctype="multipart/form-data" multiple=”multiple” data-inline="true"  data-ajax="false" onchange="ajaxUpload()">
			  <input type="hidden" id="feedimages">
			  <span id="feedimage"></span>
			</form>
		</div>
	</div>
</body>

</html>
