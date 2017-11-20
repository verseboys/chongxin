<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	#allmap {height: 95%;}
	</style>
	<script src="/assets/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	<script src="/assets/js/baidumap.js" type="text/javascript"></script>
	<title>地图展示</title>
</head>
<body>
	<form id="baidumap-form" action="/member/doghouse/mapupdate" method="post">
		<input type="hidden" name="id"  value="${(bean.id)?if_exists}">
		<div style="width:100%;height:5%;">
			<input type="text" value="" size="20" id="searchtext" style="margin-right:10px;"/>
			<input value="查询" onclick="theLocation()" type="button" style="margin-right:10px;"/>
			<input value="经度：" size="2" style="border-style:none;margin-right:10px;" readOnly="true"/>
			<input value="${(bean.longtitudeStr)?if_exists}" size="8" name="longtitude" id="longtitude" readOnly="true" style="margin-right:10px;"/>
			<input value="纬度：" size="2" style="border-style:none;margin-right:10px;" readOnly="true"/>
			<input value="${(bean.latitudeStr)?if_exists}" size="8" name="latitude" id="latitude" readOnly="true" style="margin-right:10px;"/>
			<input type="button" class="btn" value="确认保存"/>
		</div>
	</form>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(${(bean.longtitudeStr)?if_exists}, ${(bean.latitudeStr)?if_exists});
	map.centerAndZoom(point,14);
	
	var removeMarker = function(e,ee,marker){
		document.getElementById("latitude").value='';
		document.getElementById("longtitude").value='';
		map.removeOverlay(marker);
	};
	
	//创建右键菜单
	var markerMenu1=new BMap.ContextMenu();
	markerMenu1.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker1)));
	
	var marker1 = new BMap.Marker(point);// 创建标注
	map.addOverlay(marker1);// 将标注添加到地图中
	marker1.addContextMenu(markerMenu1);
	
	window.map = map;//将map变量存储在全局
	map.enableScrollWheelZoom(true);//开启鼠标滚轮缩放
	var menu = new BMap.ContextMenu();
	var txtMenuItem = [
		{
			text:'加入宠信',
			callback:function(p){join(p)}
		}
	];
	for(var i=0; i < txtMenuItem.length; i++){
		menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
	}
	map.addContextMenu(menu);
	
	function join(p){
		//var myIcon = new BMap.Icon("http://image.k9sv.com/avatar-2015063020062895360.png", new BMap.Size(20,20));
		//var marker = new BMap.Marker(p,{icon:myIcon});  // 创建标注    
		deletePoint();
		var marker = new BMap.Marker(p);
		var px = map.pointToPixel(p);
		document.getElementById("latitude").value=p.lat;
		document.getElementById("longtitude").value=p.lng;
		//创建右键菜单
		var markerMenu=new BMap.ContextMenu();
		markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));
		marker.addContextMenu(markerMenu);
		map.addOverlay(marker);
	};
	//删除所有标志
	function deletePoint(){
		var allOverlay = map.getOverlays();
		//map.removeOverlay(allOverlay[0]);
		for (var i = 0; i < allOverlay.length; i++){
			map.removeOverlay(allOverlay[i]);
		}
	};
	
	function theLocation(){
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		var searchtext = document.getElementById("searchtext").value;
		if(searchtext==""){
			return false;
		}
		local.search(searchtext);
	};
</script>