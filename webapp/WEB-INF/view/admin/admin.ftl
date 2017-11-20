<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="/ui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/ui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/ui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/ui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/assets/css/table.css" rel="stylesheet" type="text/css"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="/ui/js/jquery-1.7.2.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
<script src="/ui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/ui/js/jquery.validate.js" type="text/javascript"></script>
<script src="/ui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/ui/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="/ui/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/ui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="/ui/chart/raphael.js"></script>
<script type="text/javascript" src="/ui/chart/g.raphael.js"></script>
<script type="text/javascript" src="/ui/chart/g.bar.js"></script>
<script type="text/javascript" src="/ui/chart/g.line.js"></script>
<script type="text/javascript" src="/ui/chart/g.pie.js"></script>
<script type="text/javascript" src="/ui/chart/g.dot.js"></script>

<script src="/ui/js/dwz.core.js" type="text/javascript"></script>
<script src="/ui/js/dwz.util.date.js" type="text/javascript"></script>
<script src="/ui/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="/ui/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="/ui/js/dwz.drag.js" type="text/javascript"></script>
<script src="/ui/js/dwz.tree.js" type="text/javascript"></script>
<script src="/ui/js/dwz.accordion.js" type="text/javascript"></script>
<!--<script src="/ui/js/dwz.ui.js" type="text/javascript"></script>-->
<script src="/assets/js/dwz.ui.js" type="text/javascript"></script>
<script src="/ui/js/dwz.theme.js" type="text/javascript"></script>
<script src="/ui/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/ui/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/ui/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/ui/js/dwz.navTab.js" type="text/javascript"></script>
<script src="/ui/js/dwz.tab.js" type="text/javascript"></script>
<script src="/ui/js/dwz.resize.js" type="text/javascript"></script>
<script src="/ui/js/dwz.dialog.js" type="text/javascript"></script>
<script src="/ui/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/ui/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/ui/js/dwz.cssTable.js" type="text/javascript"></script>
<!--<script src="/ui/js/dwz.stable.js" type="text/javascript"></script>-->
<script src="/assets/js/dwz.stable.js" type="text/javascript"></script>
<script src="/ui/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="/ui/js/dwz.ajax.js" type="text/javascript"></script>
<script src="/ui/js/dwz.pagination.js" type="text/javascript"></script>
<script src="/assets/js/dwz.database.js" type="text/javascript"></script>
<script src="/ui/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="/ui/js/dwz.effects.js" type="text/javascript"></script>
<script src="/ui/js/dwz.panel.js" type="text/javascript"></script>
<script src="/ui/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="/ui/js/dwz.history.js" type="text/javascript"></script>
<script src="/ui/js/dwz.combox.js" type="text/javascript"></script>
<script src="/ui/js/dwz.print.js" type="text/javascript"></script>

<script src="/assets/js/ajaxfileupload.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
	DWZ.init("/ui/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/ui/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!--<a class="logo" href="http://j-ui.com">标志</a> -->
				<div style="font-size: 25px;font-weight: bold;color: #fff;padding-left:10px;height: 40px;;padding-top: 10px;"> </div>
				<ul class="nav">	
				    <li><a href="javascript:void(0);">您好${(account.username)?if_exists}</a></li>			 
					<li><a href="/admin/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					${menuStr?if_exists}
				</div>
			</div>
		</div>
		
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
                            <p><h1 style="font-size: 22px;text-align: center;">您好${(account.username)?if_exists}，欢迎使用！</h1></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						</div>

					</div>					
				</div>
			</div>
		</div>

	</div>
	<div id="footer">杭州宠信科技有限公司 2015</div>
</body>
</html>