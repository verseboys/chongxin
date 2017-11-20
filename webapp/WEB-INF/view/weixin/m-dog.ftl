<div data-role="page" id="mydogs">
	<div data-role="header">
		<a href="#pagemain" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>我的宠物</h1>
    	<a href="#adddog" data-role="button" data-icon="plus">添加</a>
  	</div>
  	<div data-role="main" class="ui-content">
  		<input type="hidden" value="" id="petid"/>
	   	<ul data-role="listview" id="dogsul">
	   		
		</ul>
    </div>
</div>

<div data-role="page" id="doginfor">
	<div data-role="header">
		<a href="#mydogs" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>宠物资料</h1>
    	<a href="#mydogs" data-role="button" data-icon="plus" id="savedoginfor2">保存</a>
  	</div>
  	<div data-role="main" class="ui-content" id="doginfordiv">
  		
    </div>
</div>

<div data-role="page" id="dogavatarpage">
	<div data-role="header">
		<a href="#doginfor" data-role="button" data-icon="arrow-l">取消</a>
    	<h1>头像</h1>
    	<a href="#" data-role="button" data-icon="plus" id="savedogavatar">保存</a>
  	</div>
  	<div>
  		<div class="avatar" id="dog_avatar"><canvas id="dog_canvas" width="150" height="150"></canvas></div>
  		<div id="dog_console"></div>
  	</div>
  	<div class="list_input">
		<span class="span_left">选择头像：</span>
		<span class="span_right">
			<input type="hidden" value="" id="dogj_thumb"/>
			<input type="file" capture="camera" accept="image/*" id="dog_filesupload" name="files" 
			style="height:30px;width:80px;border:1px solid #ddd">
			
		</span>
    </div>
</div>
	
<div data-role="page" id="adddog">
	<div data-role="header">
		<a href="#mydogs" data-role="button" data-icon="arrow-l">返回</a>
    	<h1>添加宠物</h1>
    	<a href="#" data-role="button" data-icon="plus" id="savedogbutton">保存</a>
  	</div>
  	<div>
  		<div class="avatar" id="avatar"><canvas id="canvas" width="150" height="150"></canvas></div>
  		<div id="console"></div>
  	</div>
  	<div class="list_input">
		<span class="span_left">选择头像：</span>
		<span class="span_right">
			<input type="hidden" value="" id="j_thumb"/>
			<input type="file" capture="camera" accept="image/*" id="filesupload" name="files" 
			style="height:30px;width:80px;border:1px solid #ddd">
			
		</span>
    </div>
    <div class="list_input">
		<span class="span_left">名称：</span>
		<span class="span_right">
			<input type="text" name="fname" id="fname">
		</span>
    </div>
    <div class="list_input">
		<span class="span_left">犬种：</span>
		<span class="span_right">
			<!--<input type="text" name="fclass" id="fclass">-->
			<select class="combox" name="fclass" id="fclass">
				<option value="0" selected="selected">请选择</option>
				<#if beans?exists && beans?size gt 0>
					<#list beans as ls><!--list循环-->
						<option value="${(ls.id)?if_exists}">${(ls.name)?if_exists}</option>
					</#list>
				</#if>
			</select>
		</span>
    </div>
    <div class="list_input">
		<span class="span_left">性别：</span>
		<span class="span_right">
			<fieldset data-role="controlgroup" data-type="horizontal">
	        <label for="male">男性</label>
	        <input type="radio" name="gender" id="male" value="1" checked="checked">
	        <label for="female">女性</label>
	        <input type="radio" name="gender" id="female" value="2"> 
	        </fieldset>
		</span>
    </div>
    <div class="list_input">
		<span class="span_left">芯片号：</span>
		<span class="span_right">
			<input type="text" name="fchip" id="fchip">
		</span>
    </div>
</div>
<script type="text/javascript">
var filed = document.getElementById("filesupload");
var dog_filed = document.getElementById("dog_filesupload");
if(typeof FileReader === 'undefined') {
    filed.disabled = true;
    dog_filed.disabled = true;
    alert('你的浏览器不支持FileReader，请更换firefox/chrome访问本页！');
}
/**

    var exif = EXIF.readFromBinaryFile(new BinaryFile(this.result));
		var orientation = exif["Orientation"];	
		showorientation(orientation);
*/

filed.onchange = function() {
    var file = this.files[0];
    var reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onloadend = function(e) {
        var exif = EXIF.readFromBinaryFile(new BinaryFile(this.result));
		var orientation = exif["Orientation"];	
		drawOnCanvas(file,orientation) ;
    }; 
};

dog_filed.onchange = function() {
    var file = this.files[0];
    var reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onloadend = function(e) {
        var exif = EXIF.readFromBinaryFile(new BinaryFile(this.result));
		var orientation = exif["Orientation"];	
		dog_drawOnCanvas(file,orientation) ;
    };   
};

function drawOnCanvas(file,orientation) { 
	var imgRotation;
	switch(orientation) {
		case 3: 
			imgRotation = 180; 
			break;
		case 6: 
			imgRotation = 90; 
			break;
		case 8: 
			imgRotation = 270; 
			break;
	}
	showorientation(orientation,imgRotation);
	var reader = new FileReader(); 
	reader.readAsDataURL(file); 
	
	reader.onloadend = function (e) { 
		
		var dataURL = e.target.result, 
		canvas = document.getElementById('canvas'),
		// canvas = document.querySelector('canvas'), 
		ctx = canvas.getContext('2d'), 
		img = new Image(); 
		img.onload = function() { 
			
			var square =150; 
			canvas.width = square; 
			canvas.height = square; 
			var context = canvas.getContext('2d'); 
			context.clearRect(0, 0, square, square); 
			var imageWidth; 
			var imageHeight; 
			var offsetX = 0; 
			var offsetY = 0; 
			if (this.width > this.height) { 
				imageWidth = Math.round(square * this.width / this.height); 
				imageHeight = square; 
				offsetX = - Math.round((imageWidth - square) / 2); 
			} else { 
				imageHeight = Math.round(square * this.height / this.width); 
				imageWidth = square;  
				offsetY = - Math.round((imageHeight - square) / 2); 
			}
			
			if(imgRotation >0){
				context.translate(150,0);
				context.rotate(imgRotation * Math.PI / 180); 
			}
			 
			context.drawImage(this, 0, 0, imageWidth, imageHeight);
			if(imgRotation >0){
				context.translate(150, 0);
				context.scale(-1, 1); 
			}
			
			var base64 = canvas.toDataURL('image/jpeg',0.5); 
			$('#j_thumb').val(base64.substr(23)); 
		}; 
		img.src = dataURL; 
	};  
	
}

function dog_drawOnCanvas(file,orientation) { 
	var imgRotation;
	switch(orientation) {
		case 3: 
			imgRotation = 180; 
			break;
		case 6: 
			imgRotation = 90; 
			break;
		case 8: 
			imgRotation = 270; 
			break;
	}
	dog_showorientation(orientation,imgRotation);
	var reader = new FileReader(); 
	reader.readAsDataURL(file); 
	
	reader.onloadend = function (e) { 
		
		var dataURL = e.target.result,
		canvas = document.getElementById('dog_canvas'), 
		// canvas = document.querySelector('canvas'), 
		ctx = canvas.getContext('2d'), 
		img = new Image(); 
		img.onload = function() { 
			
			var square =150; 
			canvas.width = square; 
			canvas.height = square; 
			var context = canvas.getContext('2d'); 
			context.clearRect(0, 0, square, square); 
			var imageWidth; 
			var imageHeight; 
			var offsetX = 0; 
			var offsetY = 0; 
			if (this.width > this.height) { 
				imageWidth = Math.round(square * this.width / this.height); 
				imageHeight = square; 
				offsetX = - Math.round((imageWidth - square) / 2); 
			} else { 
				imageHeight = Math.round(square * this.height / this.width); 
				imageWidth = square;  
				offsetY = - Math.round((imageHeight - square) / 2); 
			}
			if(imgRotation >0){
				context.translate(150,0);
				context.rotate(imgRotation * Math.PI / 180); 
			}
			 
			context.drawImage(this, 0, 0, imageWidth, imageHeight);
			if(imgRotation >0){
				context.translate(150, 0);
				context.scale(-1, 1); 
			}
			
			var base64 = canvas.toDataURL('image/jpeg',0.5); 
			$('#dogj_thumb').val(base64.substr(23)); 
		}; 
		img.src = dataURL; 
	};  
	
}

function showorientation(orientation,imgRotation){
	var  html = [];
	html.push('<ol>');
   	html.push(orientation);
   	html.push('|');
   	html.push(imgRotation);
   	html.push('</ol>');
   	document.getElementById('console').innerHTML = html.join('');
}

function dog_showorientation(orientation,imgRotation){
	var  html = [];
	html.push('<ol>');
   	html.push(orientation);
   	html.push('|');
   	html.push(imgRotation);
   	html.push('</ol>');
   	document.getElementById('dog_console').innerHTML = html.join('');
}
</script>