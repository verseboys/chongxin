<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/assets/userui/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/assets/userui/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
	
	function addCss (){
	
		var type = ${type?if_exists};
		var _class = ".list_"+type;
		$(".list").css("display","none");
		$(_class).css("display","block");
		
		$('dd').find('.menuson').slideDown();
	}
	addCss();
})	
</script>


</head>

<body style="background:#fff3e1;">
	<div class="lefttop"><span></span>主菜单</div>
    <dl class="leftmenu">
    	<dd class="list list_0">
		    <div class="title">
		    	<span><img src="/assets/userui/images/leftico01.png" /></span>工作台
		    </div>
	    	<ul class="menuson">
		        <li >
		            <div class="header">
		            <cite></cite>
		            <a href="/member/index/main" target="rightFrame">主页</a>
		            <i></i>
		            </div>
		        </li>
	        </ul>    
	    </dd>
	    <dd class="list list_1">
		    <div class="title">
		    	<span><img src="/assets/userui/images/leftico01.png" /></span>宠物
		    </div>
	    	<ul class="menuson">
		        <li >
		            <div class="header">
		            <cite></cite>
		            <a href="/member/dog" target="rightFrame">我的宠物</a>
		            <i></i>
		            </div>
		        </li>
		        <li>
		            <div class="header">
		            <cite></cite>
		            <a href="/member/dog/selled" target="rightFrame">已售宠物</a>
		            <i></i>
		            </div>                
		        </li>
		        <li>
		           	<div class="header">
		            <cite></cite>
		            <a href="/member/warranty" target="rightFrame">保单</a>
		            <i></i>
		            </div>                
		       </li>
	        </ul>    
	    </dd>
	    <dd class="list list_2">
		    <div class="title">
		    	<span><img src="/assets/userui/images/leftico02.png" /></span>企业信息
		    </div>
		    <ul class="menuson">
		        <li>
		            <div class="header">
		            <cite></cite>
		            <a href="/member/doghouse/update" target="rightFrame">信息</a>
		            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
		            <cite></cite>
		            <a href="/member/servers" target="rightFrame">服务</a>
		            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
		            <cite></cite>
		            <a href="/member/doghouse/baidumap" target="rightFrame">位置</a>
		            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
		            <cite></cite>
		            <a href="/member/announcement" target="rightFrame">公告</a>
		            <i></i>
		            </div>                
		        </li>
		    </ul>     
	    </dd> 
	    <dd class="list list_3">
		    <div class="title">
		    	<span><img src="/assets/userui/images/leftico02.png" /></span>客户关系
		    </div>
		    <ul class="menuson">
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/fans" target="rightFrame">粉丝</a>
			            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/attention" target="rightFrame">关注</a>
			            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/message?uid=${(account.id)?if_exists}" target="rightFrame">消息</a>
			            <i></i>
		            </div>                
		        </li>
		    </ul>     
	    </dd>
	    <dd class="list list_4">
		    <div class="title">
		    	<span><img src="/assets/userui/images/leftico02.png" /></span>系统设置
		    </div>
		    <ul class="menuson">
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/setting/changepassword" target="rightFrame">修改密码</a>
			            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/setting/changeavatar" target="rightFrame">修改头像</a>
			            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/setting/update" target="rightFrame">基本信息修改</a>
			            <i></i>
		            </div>                
		        </li>
		        <li>
		            <div class="header">
			            <cite></cite>
			            	<a href="/member/setting/checkmobile" target="rightFrame">手机号验证</a>
			            <i></i>
		            </div>                
		        </li>
		    </ul>     
	    </dd>
    </dl>
</body>
</html>
