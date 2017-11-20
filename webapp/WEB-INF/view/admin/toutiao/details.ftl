<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
    <title>题目</title>
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
          title: '宠信头条',
          desc: '题目',
          link: '链接地址',
          imgUrl: 'http://h5.welian.com//public/img/toutiao.png',
          trigger: function (res) {},
          success: function (res) {},
          cancel: function (res) {},
          fail: function (res) {
            alert(JSON.stringify(res));
          }
        });

      //监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
        wx.onMenuShareTimeline({
          title: '创业头条 ',
          link: 'http://h5.welian.com/toutiao/i/491',
          imgUrl: 'http://h5.welian.com//public/img/toutiao.png',
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
<div class="wrap pageContent" layoutH="56">
<div class="container">
    <div class="poster">
        <img src="http://img.welian.com/1441613822.jpg">
    </div>
    <h1 class="title">题目</h1>
    <div class="info">
        <label>作者</label>
        <label>发布时间</label>
        <label>阅读 180</label>
    </div>
    <div class="article"><p><br/></p><section style="border: 0px; margin: 0.8em 0px 0px; box-sizing: border-box; padding: 0px;" class="tn-Powered-by-XIUMI"><section style="width: 100%; padding: 5px; font-size: 1em; font-family: inherit; text-decoration: inherit; color: rgb(160, 160, 160); border-color: rgb(249, 110, 87); box-sizing: border-box;" class="tn-Powered-by-XIUMI"><span style="float: left; width: 5%; font-size: 3em; line-height: 0.8em; display: inline-block; font-weight: bolder; vertical-align: top; box-sizing: border-box;" class="tn-Powered-by-XIUMI">“</span> <span style="width: 88%; line-height: 1.5em; margin-top: 0.5em; margin-left: 1.2em; display: inline-block; vertical-align: middle; font-size: 1em; font-family: inherit; box-sizing: border-box;" class="tn-Powered-by-XIUMI"><section class="tn-Powered-by-XIUMI" style="box-sizing: border-box;">你看见一个东西会问为什么，我会梦想那些还未出现的东西，并且是问为什么不的人，祝朋友们都是问为什么不的人。</section></span></section><section style="width: 0px; height: 0px; clear: both;"></section></section><p><br/></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">“我说个笑话，”奇虎360（以下简称 “360”）创始人周鸿祎面无表情，“小弟告诉大哥，我马上要发射核弹了。大哥一惊，问，什么时候？小弟说，5、4、3、2……”</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">360公司创始人周鸿祎正在试图用一个笑话，描述自己得知乐视入股酷派时的心情：2015年5月，周鸿祎公布了360和老牌手机厂商酷派成立合资公司奇酷科技，并推出新手机品牌“奇酷”，然而出乎所有人包括周鸿祎意料的是，仅一个月之后，酷派却引入了另一个投资者乐视，并宣布其将成为公司第二大股东。在外界看来，这让周鸿祎陷入了被动，因为这可能意味着他的一举一动可能都将被对手乐视掌握。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">周鸿祎则告诉《福布斯》，刚听到这个消息，自己也曾情绪激动，但他很快平静下来：“360、乐视、酷派必须抱团，共同抵御更强大的对手。未来的关系将是合作大于竞争。”这位从来不惧怕和竞争对手硬碰硬的企业家，这样定位将来三家公司的关系。此外有消息指在6月乐视投资酷派后，原持股比例仅45%的360在今年5月增持奇酷科技股份至49.5%后，6月已依据之前协议增持奇酷股权至75%。</span></p><p><br/></p><p style="line-height: 2em;"><strong><span style="font-size: 16px; color: rgb(63, 63, 63);">一直以来，霸气外露是周鸿祎和360的显著标签。</span></strong><span style="font-size: 16px; color: rgb(63, 63, 63);">自2005年创立360之后，他带领公司以免费的杀毒模式，在短短几年内就彻底颠覆了传统杀毒玩家们的盈利模式，在互联网用户中渗透率一度超过95%，创业历程中还伴随着多次著名的与巨头交锋的战役。这家如今为逾8亿中国互联网用户提供互联网和移动互联网安全产品及服务的公司，2011年登陆纽约证券交易所，总计获得40倍超额认购。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">这家公司也一度被认为是BAT之后的下一个商界巨擘，2013年，周鸿祎也曾津津乐道对外宣讲如何在BAT火力交叉点下生存，如何以最优策略挑战巨头。然而，两年过去，奇虎360的股价已经从最高时期的120美元滑落到了60美元左右，并在附近长期徘徊，外界一度怀疑，周鸿祎是否锋芒尚在。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">下降的背后是360赖以起家的主业正在触及增长天花板。另一方面，是周鸿祎在硬件领域上也曾经历起伏。2012年，周鸿祎挥师杀入了手机领域，并与华为、阿尔卡特、海尔、夏新等多家公司合作，推出了多款“360特供机”，并曾在新浪微博上和小米创始人雷军发生了多轮针对双方产品的交锋。然而被周鸿祎寄予厚望的“360特供机”并不成功，出师不利的360之后逐渐淡出了手机领域。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">“特供机的失败挺丢人的，”号称最早读懂“小米模式”的周鸿祎反思，“最关键的是贻误了战机。”当时公司和合作企业采取了一种极为松散的合作关系，360并未深度参与手机的设计、制造，更无产品定价权，仅在商品上预装了360手机应用。这些手机以“360特供机”为卖点，360在其中仅相当于销售代理，作用是帮助手机厂商营销推广。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">“这种结合很失败，我们软件优势无法发挥，对硬件也没有掌控能力，我们也无法做到一个真正软硬件服务一体的模式，”他分析当时的心理，认为自己对硬件有畏惧心理，所以想走捷径，“现在看来，我当时踏踏实实地找一帮人开始做手机，积累到今天，可能也已小成。但我想的却是走捷径，希望借力打力，最终让事态完全失控。”</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">这次失利并没有浇熄周鸿祎对智能硬件和IOT（InternetOfThings，万物互联）的热情，此后，360陆续推出了随身Wifi、儿童智能手表、大户型路由器、行车记录仪等产品，其中随身Wifi在上市五个月内销量即突破3,000万台。2014年年底，周鸿祎再次进入手机领域，宣布360和酷派公司将成立一家合资公司，运营新的手机品牌“奇酷”和酷派原有的“大神”品牌，并由周鸿祎担当CEO。从去年12月合作意向达成后，酷派和360中负责手机业务的员工分别辞职，全职加入奇酷公司，团队中，360负责产品定义、OS系统、品牌营销等，酷派则提供生产、售后以及供应链的管理。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">团队初步成形，然而市场里这场智能手机盛宴或许并没有为360预留席位。时机已经过晚了，周鸿祎明白这点。“按照商人的理论，我们错过了最好的时机，”他眼里最好的时机是2012年，“那时候只要有一台智能机，只要配置高、性价比高，不断的堆砌硬件的性能，就能够博得用户的欢迎。”而现在，智能手机厂商的黄金期已经过去，IDC公布的最新数据显示，2015年中国市场第一季度智能手机出货量较去年同比下滑4.3%，如周鸿祎所言，360闯入的不是红海，而是一片血海。实际上，周鸿祎最为看重的对手雷军已经带领小米一骑绝尘地领跑国内智能手机厂商，360追赶小米将是一件比三年前更困难的任务。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">他将自己再次进入手机行业的原因解释为与智能设备紧密结合的互联网又开始了新一轮革命，而他希望参与智能设备的中枢和核心——手机的创新游戏：“iPhone是2007年推出来的，很快满十年了，我很好奇，难道手机一直这样了吗？手机会不会被颠覆？如果你不去做手机，你永远无法知道这个答案，因为你永远是一个局外者。”<strong>周鸿祎直言手机厂商们“太懒惰”，走不出思维定势，而360希望扮演鲶鱼角色。</strong>“我的缺点你可以说是我不懂手机，我不懂手机有很多制造工艺，但优点是我没有各种思维定势，敢于挑战和突破。”</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">周鸿祎告诉《福布斯》：“我并不准备为所有人造手机。”他将新手机取名“奇酷”，据奇酷科技市场负责人于涛介绍，周鸿祎希望拉拢最有好奇心和创造力的群体：设计师、工程师、程序员、产品经理四种人和他一起为用户造手机。“奇酷的slogan是好奇心改变世界，这四种人我认为是可以改变世界的，在互联网时代，这四种人也承担特别重要的作用。”周鸿祎这样表示。对周鸿祎的采访也从谈论即将在8月份面世的360手机开始。在位于798的360办公大楼，他坐在办公室的沙发上，滔滔不绝地叙述奇酷手机的各种创新点，从摄像头到边框设计，从机身材质到尺寸选择……“做软硬件有一点是相通的，从用户出发，用同理心去思考用户真正需要什么。”周鸿祎认为自己即使进入硬件领域，一样可以指导做硬件的人去做好产品。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">但他也坦承360本没有做硬件的基因，这两年也摔了很多跟头。当年从手机业务撤退后，周鸿祎坚持自建团队，决定制造路由器，但进展并不顺利。“我发现我们把别人走过的弯路又走了一遍，因为即使小到做一个简单的路由器，这里面还是有很多的knowhow（技术诀窍），毕竟别的企业很专注干了很多年。”</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">碰壁后的360最终选择了和传统硬件公司共建合资公司的路径。2014年底，周鸿祎投资2亿元，和有十余年生产路由器经验的深圳路由器公司磊科合资成立了一家新公司——蜂联科技。这一次，磊科公司高级副总裁卢东发现，自己需要应对的是来自周鸿祎的苛刻要求，因为理念不同、背景不同，360和磊科员工一开始也争论不断。卢东团队曾在路由器内部构件中使用一款通用的绿色PPB板材，这一选择让周鸿祎勃然大怒，他认为，360路由器生产后，必然有一群用户详细拆解，尽管是内部用料颜色，也应该讲究。最后，路由器采用了黑色板材。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">类似的多次碰撞，让路由器的生产过程“代价巨大”，蜂联在设计新路由器P1大户型路由器时，周鸿祎为了要求极致，一共打了6个手模。卢东认为：“互联网公司强调快速和用户体验，而硬件制造公司讲究严谨和长期规划。硬件业和软件业是两个大相径庭的行业，硬件业权衡非常多，如果中途做出调整，很多时候意味着前面的工作推倒重来。”如今看来，这些代价也是值得的，360大户型路由器P1半年销售超过100万台。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">现在，经历了第一代产品的磨合，周鸿祎明白了在硬件领域，好的体验需要付出成本代价，而理性的商业公司必须考虑用户对价格的接受度，卢东则也学习到了什么是互联网企业所强调的“追求极致”。在360公司，追求极致的动力来自企业最高层，卢东眼里的“超级产品经理”周鸿祎对产品极有感觉，同时也不留情面，容易给团队极大压力。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">这种压力似乎也传导到了360的手机合作伙伴酷派一方。在团队中，周鸿祎多充当教练的角色，在他自己看来，对硬件业“大无畏”的颠覆举动，让合作伙伴“心惊肉跳”。奇酷科技集团中，酷派负责硬件层面、360负责软件层面，而周鸿祎在软件层面，声称要彻底改变商业预装软件的游戏规则。手机预装软件一直以来都是手机厂商收入的主要来源之一，而周鸿祎则要“自己砍自己挣钱的金饭碗”，不再接受商业预装，把原来手机里操作系统默认必带的，包括闹钟、天气、录音机等基本组件开放，让用户来选择。他并不担心因为这一举动不挣钱：“我觉得在挣钱和用户体验之间，我肯定先选择用户体验，有了好的用户体验，你才有用户群，你才能真正建立挣钱的商业模式。”</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">他没有放弃指导硬件制造厂商们创新：“他们的最大短板是基因里就不懂用户，需要我们教给他们做产品的哲学和理念。”但他也学会了以更开放的心态面对传统制造业，并告诫团队，做智能硬件不是软硬件行业简单的结合，而是要完成从产品理念、管理理念和企业文化的全面融合。</span></p><p><br/></p><p style="line-height: 2em;"><strong><span style="font-size: 16px; color: rgb(63, 63, 63);">他也希望重塑自己和公司的形象。</span></strong><span style="font-size: 16px; color: rgb(63, 63, 63);">2014年年初，360召开年会的时候，主题是reboot（重启），意思是整个公司都要重新拾起创业心态，而周鸿祎也表达了进行自我反思的意愿。但近距离接触周鸿祎，会发现他依然故我。他依旧穿着多次在公众前露面的红色T恤，在采访过程中表现得急躁，尽管约定的采访时间在十点，而他九点四十就开始不断催促采访尽快开始，“他是个闲不下来的人。”360公关部人员这样表示。大量的公司事务等着他亲自处理，接受采访的中途，他甚至不得不中止谈话而去参加和富士康召开的电话会议。</span></p><p><br/></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">没有发生改变的还有，他仍然对产品有着极大热情和敏锐感觉。周鸿祎的微信公众号平台上，他写下的第一句话就是：“用萧伯纳的一句话来欢迎关注我的朋友，‘你看见一个东西会问为什么，我会梦想那些还未出现的东西，并且是问为什么不的人’，祝朋友们都是问为什么不的人。”“为什么不”四个字可以概括他一直以来的产品思维，也可以解释他一路的创业动机。</span></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);">（本文选自虎嗅网）</span></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);"><br/></span></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);"><br/></span></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);"><br/></span></p><p style="line-height: 2em;"><span style="font-size: 16px; color: rgb(63, 63, 63);"><br/></span></p><p><span style="font-size: 14px;">上一篇：</span><a href="http://h5.welian.com/toutiao/preview/444" target="_self" style="font-size: 14px; color: rgb(0, 176, 240); text-decoration: none;"><em><span style="font-size: 14px; color: rgb(0, 176, 240);">且看“吴晓波们”如何打造公号中的“航空母舰”</span></em><em><span style="font-size: 14px; color: rgb(0, 176, 240);"></span></em></a></p><p style="line-height: 2em;"><span style="font-size: 14px; color: rgb(63, 63, 63);"><br/></span></p><p style="line-height: 2em;"><span style="font-size: 14px; color: rgb(63, 63, 63);">相关阅读：</span></p><p style="line-height: 2em;"><br/></p><p style="line-height: 2em;"><a href="http://finance.sina.com.cn/roll/20150906/103923165366.shtml" target="_self" style="text-decoration: none;"><span style="color:#3f3f3f"><span style="font-size: 14px; color: rgb(0, 176, 240);">奇酷手机：周鸿祎的背水一战</span></span></a></p><p><br/></p>
    </div>
    <div class="download">
        <img src="http://www.welian.com/index/scan/url/aHR0cDovL2g1LndlbGlhbi5jb20vL3RvdXRpYW8vaS80OTE=" class="qrcode">
        <p>微信扫一扫，分享该文章</p>
    </div>
</div>
<!-- 通用头部 -->
<div class="wl-header">
    <div class="wl-logo"><img src="http://fed.welian.com/3_welian/logo_100x100.png"></div>
    <div class="wl-info">更多创业项目、精彩活动、认证投资人，尽在微链</div>
    <a class="wl-download" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.csm.welian">下载微链</a>
</div>
</div>
</body>
</html>


