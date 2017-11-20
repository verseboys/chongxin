<div class="wrap">
    <div class="container">
    	<h1 class="title title-tou">${(bean.title)?if_exists}</h1>
        <div class="info">
            <label>${(bean.auth)?if_exists}</label>
            <label>${(bean.createdStr)?if_exists}</label>
            <label>阅读 ${viewCount?default(0)}</label>
        </div>
        <div class="article">
        	${(bean.content)?if_exists}
        </div>
    </div>
</div>
<!-- 通用头部 -->
<div class="wl-header">
    <div class="wl-logo"><img src="/assets/img/iocn.png"></div>
    <div class="wl-info">大病不愁，小病无忧！宠物健康尽在宠信！</div>
    <a class="wl-download" href="#">下载宠信</a>
</div>