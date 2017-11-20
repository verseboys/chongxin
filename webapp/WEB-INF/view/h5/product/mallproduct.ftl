<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="apple-touch-fullscreen" content="yes" />
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<title>${(product.product)?if_exists}</title>
<style>
	body {
		width: 100%;
		margin: 0px;
		padding:0px;
		font-size:62.5%;     
	}
	img{ 
		max-width:100%;height:auto; 
	} 
	.all{
		width:100%;
	}
	.content{
		margin:0 auto;
		margin-left:5%;
		margin-right:5%;
	}
	.title{
		margin-top:10px;
		font-size:1.6em;
		color:#333333;
	}
	.price{
		margin-top:10px;
		height:20px;
	}
	.price_left{
		float:left;
		font-size:2em;
		color:#eb5c58;
		
	}
	.price_right{
		float:right;
		font-size:1.3em;
		color:#999999;
	}
	.market_price{
		margin-top:10px;
		font-size:1.3em;
		color:#666666;
		margin-bottom:10px;
	}
	.line{
		width:100%;
		height:10px;
		background:#F2F2F2;
	}
	.detail{
		width:100%;
		border-bottom:1px solid #E5E5E5;
	}
	.detail span{
		margin-left:3%;
		font-size:1.6em;
		height:40px;
		float:left;
		margin-top:9px;
	}
</style>
</head>

<body>
	<div class="all">
		<!--<img src="/assets/img/detail_bg.png" width="100%"/>-->
    	<!--<img src="${(product.imgurl)?if_exists}" width="100%"/>-->
    	<img src="${(product.imgurl)?default('/assets/img/detail_bg.png')}" width="100%"/>
        <div class="content">
            <div class="title">
                <span>${(product.product)?if_exists}</span>
            </div>
            <div class="price">
                <div class="price_left">
                   <span>¥${(product.price)?if_exists}</span>
                </div>
                <div class="price_right">
                   <span>销量：${(product.count)?if_exists}</span>
                </div>
            </div>
            <div class="market_price">
            	<del>市场原价：${(product.marketprice)?if_exists}</del>
            </div>
        </div>
        <div class="line"></div>
        <div class="detail">
        	<div class="content">
                <div style="height:37px;">
                    <img src="/assets/img/jx12.jpg" style="margin-top:10px;float:left;height:17px;"/>
                    <span>
                        详情介绍
                    </span>
                </div>
            </div>
        </div>
        <div style="width:100%;">
        	<div class="content" style="font-size:1.4em;padding-top:20px;padding-bottom:10px;">
        		${(product.detail)?if_exists}
        	</div>
        </div>
    </div>
</body>
</html>
