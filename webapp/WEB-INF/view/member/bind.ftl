<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>绑定</title>
        <link href="/assets/css/bind.css" rel="stylesheet">
		<script src="/ui/js/jquery-1.7.2.js" type="text/javascript"></script>
		<script src="/assets/js/login.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="w">  
            <div class="head">  
                <a href="https://www.ichongxin.com"><img src="/assets/img/logo.png" alt="宠信网"></a>
            </div>
            
            <div class="infor">
                <span class="label text-lable">绑定帐号</span>
                <span class="label">你已成功验证了微信帐号！马上绑定宠信帐号，下次登录更便捷哦！</span>
            </div>
            
            <div class="content"> 
                <div class="mc">
                    <div class="register">
                        <div class="unite-info">
                            <span>你没有宠信帐号</span>
                        </div>
                        <div class="main-lable">
                        	<form id="register-form" action="/member/registeraction" method="post">
	                        	<div class="login-div first">
	                            	<div class="register-label lable-text"><b class="ftx04">*</b>用户名：</div> 
	                            	<div class="register-label"><input type="text" name="username" id="register-account" class="itxt"/></div>
	                            </div>
	                            <div class="login-div username">
	                            	<div class="register-label lable-text"><b class="ftx04">*</b>请设置密码：</div>
	                            	<div class="register-label"><input type="password" name="password" id="register-password" class="itxt"/></div>    
	                            </div>
	                            <div class="login-div username">
	                            	<div class="register-label lable-text"><b class="ftx04">*</b>请确认密码：</div> 
	                            	<div class="register-label"><input type="password" name="password1" id="register-password1" class="itxt"/></div> 
	                            </div>
	                            <div class="login-div username">
	                            	<div class="register-label lable-text"><b class="ftx04">*</b>手机号：</div>
	                            	<div class="register-label"><input type="text" name="profile.mobile" id="register-phone" placeholder="手机号" class="itxt"></div>    
	                            </div>
                            </form>
                            <div class="register-btn">立即注册</div>                           
                        </div>
                    </div>
                    <div class="line"></div>
                    <div class="bind">
                        <div class="unite-info">
                           <span>你已有宠信帐号</span>
                        </div>
                        <div class="main-lable">
                        	 <form id="bind-form" action="/member/bindaction" method="post">
	                        	 <div class="bind-lable first">
	                             	<div class="imgdiv"><img src="/assets/img/user0.png" alt="用户名" /></div>
	                                <input type="text" name="username" id="bind-account" placeholder="用户名/已验证手机号" class="itxt"/>
	                             </div> 
	                             <div class="bind-lable">
	                             	<div class="imgdiv"><img src="/assets/img/pw0.png" alt="密码"/></div>
	                                <input type="password" name="password" id="bind-password" placeholder="用户名/已验证手机号" class="itxt"/>
	                             </div> 
                             </form>
                             <div class="bind-btn">立即绑定</div>                     
                        </div>      
                    </div>
                </div>          
            </div>
        </div>
    </body>
</html>
