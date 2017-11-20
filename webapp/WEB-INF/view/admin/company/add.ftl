
<div class="pageContent">
	<form method="post" action="/admin/company/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id"  value="0">
		<input type="hidden" name="deleted"  value="0">
		<input type="hidden" name="type"  value="${type?if_exists}">
		<input type="hidden" name="createdStr"  value="${createdtimeStr?if_exists}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>店铺名称：</label>
				<input name="name" type="text" value="${(bean.name)?default('${(profile.nickName)?default("我")}的宠信店')}" size="30" class="required"/>
			</p>
			<p>
				<label>电话：</label>
				<input name="telephone" type="text" value="${(bean.telephone)?default('${(profile.mobile)?if_exists}')}" size="30" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>地址：</label>
				<input name="address" type="text" value="${(bean.address)?default('${(profile.address)?if_exists}')}" size="30" class="required"/>
			</p>
			<p>
				<label>所在地区：</label>
				<input name="district" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>营业时间：</label>
				<input name="open_time" type="text" value="" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<label>地图定位：</label>
				<div style="width:626px;height:10px;">
					<input type="text" value="" size="20" id="searchtext"/>
					<input value="查询" onclick="theLocation()" type="button"/>
					<input value="经度：" size="2" style="border-style:none" readOnly="true"/>
					<input value="" size="8" name="longtitude" id="longtitude" readOnly="true" class="required"/>
					<input value="纬度：" size="2" style="border-style:none" readOnly="true"/>
					<input value="" size="8" name="latitude" id="latitude" readOnly="true" class="required"/>
				</div>
				<div id="allmap" style="width:726px;height:400px;margin-top:20px;margin-left:130px;"></div>
			</div>
			<div class="divider"></div>
			<div>
				<label>犬舍介绍：</label>
				<textarea class="editor" name="introduction" rows="20" cols="100">
					
				</textarea>
			</div>
			<div class="divider"></div>
			<dl class="nowrap">
				<dt>绑定用户：</dt>
				<dd>
					<input names="user.id" class="required" name="uid" value="${(profile.id)?default(0)}" type="hidden"/>
					<input class="required" readonly="readonly" value="${(profile.nickName)?if_exists}" names="user.username" type="text" lookupGroup="user"/>
					<a class="btnLook" href="/admin/company/queryuser" lookupGroup="user">查找用户</a>	
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">

// 百度地图API功能
var map = new BMap.Map("allmap");
//var point = new BMap.Point(116.404, 39.915);
map.centerAndZoom("杭州",12);
window.map = map;//将map变量存储在全局
//map.setCenter("杭州");
map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放
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
var removeMarker = function(e,ee,marker){
	document.getElementById("latitude").value='';
	document.getElementById("longtitude").value='';
	map.removeOverlay(marker);
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
