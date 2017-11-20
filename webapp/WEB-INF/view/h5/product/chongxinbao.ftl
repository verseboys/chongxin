<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>宠信宝详情</title>
<link href="/assets/css/cxbdetail.css" rel="stylesheet">
</head>

<body>
	<div class="all">
    	<div class="banner"><img src="/assets/img/detail_banner.jpg" width="1080px" height="505px"/></div>
        <div class="price">
        	<div class="price_in">
            	<div class="left">
                	<div class="left_top"><span>${(product.product)?if_exists}</span></div>
                    <div class="left_bottom"><span>销量：${(product.count)?if_exists}</span></div>
                </div>
                <div class="right"><span>${(product.price)?if_exists}${(product.unit)?if_exists}</span></div>
            </div>
        </div>
        <div class="middle">
        	<div class="infor"><img src="/assets/img/detail_bg.png" width="1020px" height="159px"/></div>
            <div class="content">
            	<p>宠信宝是中国第一个家庭宠物健康医疗保障计划。</p>
            	<p>宠物医疗费用昂贵已经成为养宠人的一个负担，宠信宝，就是通过医疗保险的形式，降低养宠人的医疗负担，一次缴费，全年看病都免费。</p>
                <p>宠信宝的保障范围：除了外伤和慢性病不保，其他病症我们都会秉承救助宠物的原则进行救治包括感冒、发烧拉肚子等常见病，细小犬瘟等传染病，寄生虫、内科、神经科等等。</p>
            </div>
            <div class="youshi"><div class="youshi_img"><img src="/assets/img/deatai_ys.jpg" width="901px" height="991px"/></div></div>
            <div class="wenti"><div class="wenti_img"><img src="/assets/img/detail_wt.jpg" width="985px" height="887px"/></div></div>
            <div class="fuwu"><div class="fuwu_img"><img src="/assets/img/detail_fw.jpg" width="916px" height="490px"/></div></div>
        </div>
    </div>
</body>
</html>
