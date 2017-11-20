<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
    <title>${(bean.sucai.title)?default("宠信")}</title>
    <meta charset="utf-8">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="format-detection" content="telephone=no" />
    <link href="/assets/css/common.css" rel="stylesheet">
    <link href="/assets/css/toutiao.css" rel="stylesheet">
</head>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
   wx.config({
    //debug: true,
    appId: '${(weixinShare.appId)?if_exists}',
    timestamp: '${(weixinShare.timestamp)?if_exists}',
    nonceStr: '${(weixinShare.nonceStr)?if_exists}',
    signature: '${(weixinShare.signature)?if_exists}',
    jsApiList: [
      'onMenuShareAppMessage',
      'onMenuShareTimeline'
    ]
   });
   wx.ready(function () {
        wx.checkJsApi({
            jsApiList: [
                'onMenuShareAppMessage',
      			'onMenuShareTimeline'
            ]
        });

    //监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareAppMessage({
      title: '宠信头条', // 分享标题
      desc: '题目', // 分享描述
      link: '链接地址', // 分享链接
      imgUrl: '/public/img/toutiao.png', // 分享图标
      trigger: function (res) {},
      success: function (res) {},
      cancel: function (res) {},
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });

    //监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareTimeline({
      title: '',
      link: '',
      imgUrl: '/public/img/toutiao.png',
      trigger: function (res) {},
      success: function (res) {},
      cancel: function (res) {},
      fail: function (res) {
        alert(JSON.stringify(res));
      }
    });
  });
</script>
<body>
    <!-- 通用头部 -->
        <div class="wrap">
        <div class="container">
            <h1 class="title title-tou">${(bean.sucai.title)?if_exists}</h1>
            <div class="info">
                <label>${(bean.sucai.auth)?if_exists}</label>
                <label>${(bean.sucai.createdStr)?if_exists}</label>
                <label>阅读 ${viewCount?default(0)}</label>
            </div>
            <div class="article">
            	${(bean.sucai.content)?if_exists}
            </div>
            </div>
            <div class="download">
                <img src="${(bean.qrCode)?if_exists}" class="qrcode">
                <p>微信扫一扫，分享该文章</p>
            </div>
        </div>
        <!-- 通用头部 -->
        <div class="wl-header">
            <div class="wl-logo"><img src="/assets/img/iocn.png"></div>
            <div class="wl-info">小病不愁，大病无忧！宠物健康尽在宠信！</div>
            <a class="wl-download" target="_blank" href="http://www.ichongxin.com/download">下载宠信</a>
        </div>
    </div>
    <div style="display:none;">
    	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>
