<meta charset="utf-8">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta name="format-detection" content="telephone=no" />
<link href="/assets/css/common.css" rel="stylesheet">
<link href="/assets/css/toutiao.css" rel="stylesheet">
<script src="/assets/js/toutiao.js" type="text/javascript"></script>
<div class="pageContent">
	<form method="post" action="/admin/sucai/submit" enctype="multipart/form-data" class="pageForm required-validate" onsubmit="return iframeCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${(bean.id)?if_exists}">
		<input type="hidden" name="createdStr1" value="${(bean.createdStr)?if_exists}">
		<input type="hidden" name="viewed" value="${(bean.viewed)?if_exists}">
		<input type="hidden" name="deleted" value="${(bean.deleted)?if_exists}">
		<input type="hidden" name="intro" value="${(bean.intro)?if_exists}">
		<input type="hidden" name="link" value="${(bean.link)?if_exists}">
		<div class="pageFormContent right" layoutH="56">
			<p>
				<label>标题：</label>
				<input name="title" class="titles" type="text" value="${(bean.title)?if_exists}" size="30" class="required"/>
			</p>
			<div class="divider"></div>
			<p>
				<label>作者：</label>
				<input name="auth" class="auth" type="text" value="${(bean.auth)?if_exists}" size="30"/>
			</p>
			<div class="divider"></div>
			<div>
				<div id="preimage">
					<label>logo：</label>
					<#if (bean.logo)?exists&&(bean.logo)!="">
						<img src="${(bean.logo)?if_exists}" style="max-height:200px;max-width:200px;"/>
					<#else>
						<img src="/assets/img/default.png" style="max-height:200px;max-width:200px;"/>
					</#if>
				</div>
				<div>
					<label>&nbsp;</label>
					<input type="file" name="file" id="j_image" value=""/> 
		            <input name="logo" value="${(bean.logo)?if_exists}" class="logo" type="hidden"/>	
				</div>
			</div>
			<div class="divider"></div>
			<p>
				<label>正文：</label>
				<textarea class="editor" name="content" rows="20" cols="100"
					upImgUrl="/upload/newsImage/upload" upImgExt="jpg,jpeg,gif,png">
					${(bean.content)?if_exists}
				</textarea>
			</p>
		</div>
		<div id="content_top" layoutH="56" style="display:none;">
		  <div class="content_top_wrapper">
		  </div>
		  <div style = "text-align:right;">
		  	<button type="button" class="closepreview">关闭预览</button>
		  </div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="preview">预览</button></div></div>
				</li>
				<!--
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="preview" onclick="previewToutiao()">预览</button></div></div>
				</li>
				-->
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
