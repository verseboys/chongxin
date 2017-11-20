<!DOCTYPE html>
<!-- saved from url=(0052)http://wap.liudu.com/Activity/Friends?autoID=1306562 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>我的朋友圈</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <link href="http://res.liudu.com/Content/Friends.css" rel="stylesheet">
    <script async="" src="/assets/js/analytics.js"></script>
    <script src="/assets/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    <!--
        !window.jQuery && document.write('<script src=http://res.liudu.com/Scripts/jquery-2.1.4.js><\/script>');//
    //-->
    </script>
    
    <script src="/assets/js/Zero.js"></script>
    <script src="/assets/js/jweixin-1.0.0.js"></script>
</head>
<body style="">
    <div style="margin:0 auto;display:none;">
        <img class="data-avt" src="/assets/img/amuse/0" >
    </div>
    <header>
        <img id="bg" src="/assets/img/amuse/bg.jpg">
        <p id="user-name" class="data-name">${(user.nickname)?default('宠信')}</p>
        <img id="avt" class="data-avt" src="/assets/img/amuse/0" >
    </header>
    <div id="main">
        <div id="list">
        
    	</div>
        <div style="display:none;">
            <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cspan id='cnzz_stat_icon_1256021271'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/stat.php%3Fid%3D1256021271%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script><span id="cnzz_stat_icon_1256021271"><a href="http://www.cnzz.com/stat/website.php?web_id=1256021271" target="_blank" title="站长统计"><img border="0" hspace="0" vspace="0" src="./我的朋友圈_files/pic.gif"></a></span><script src="./我的朋友圈_files/stat.php" type="text/javascript"></script><script src="./我的朋友圈_files/core.php" charset="utf-8" type="text/javascript"></script>
            <script>
                (function (i, s, o, g, r, a, m) {
                    i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date(); a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
                })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
                ga('create', 'UA-57334816-2', 'auto');
                ga('send', 'pageview');
            </script>
        </div>

        <style type="text/css">
            .shopping {
                width: 100%;
                z-index: 5;
                position: fixed;
                bottom: 0;
                left: 0;
                height: 68px;
                padding: 10px 20px;
                box-sizing: border-box;
                box-shadow: 0 -5px 10px rgba(0, 0, 0, 0.2);
                background: rgba(255, 255, 255, 0.94);
                font-size: 14px;
                margin: 0;
                color: #7d7d7d;
                line-height: 14px;
            }

            .shopping .container {
                max-width: 980px;
                margin: 0 auto;
            }

            .shopping .container button {
                margin-bottom: 10px;
            }
        </style>
        <div class="shopping" id="shopping-box" style="display: none;">
            <div class="container">
                <button onclick="gotoplay();" class="btn btn-success btn-lg btn-block">我也要玩</button>
            </div>
        </div>
        <div id="share">
            <p style="padding:10px;">分享朋友圈，让你朋友一起来装逼！</p>
            <p style="padding:10px;">（此朋友圈纯属虚构）</p>
            <div style="padding:15px;padding-bottom:20px">
                <button id="gotoplay" onclick="gotoplay();" class="btn btn-success btn-lg btn-block">我也要玩</button>
                <br>
            </div>
        </div>
        <div id="noguide" class="hide"></div>
    </div>
    <script id="man" type="text/template">
        <ul>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/n5.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">王思聪</p>
                        <div class="post">
                            <p>
					                                王可可，你还真的是个碧池啊<br>
					                                天天不吃饭<br>
					                                我到底该拿你怎么办
					            <img src="/assets/img/amuse/w1.jpg">
                            </p>
                        </div>
                        <p class="time">刚刚</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">吴亦凡，杨幂，林更新，angelababy...</div>
                        <div class="cmt-list">
                            <p><span>王健林：</span>儿子，你要给她吃金盾狗粮！</p>
                            <p><span>王思聪</span>回复<span class="data-name">王健林</span><span>：</span>听爸爸的。</p>
                            <p><span>${(user.nickname)?default('宠信')}：</span>对狗好的人，运气都不会太差。</p>
                            <p><span>王思聪</span>回复<span class="data-name">${(user.nickname)?default('宠信')}</span><span>：</span>哥，啥时候有空来看看可可呗~</p>
                            <p><span class="data-name">${(user.nickname)?default('宠信')}</span>回复<span>王思聪</span><span>：</span>好。洗干净等我<img src="/assets/img/amuse/yx.png"></p>
                        </div>
                    </div>
                </div>
            </li>

            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/lzl.jpg">
                </div>
                <div class="po-cmt">
                	<div class="po-hd">
                        <p class="po-name">林志玲</p>
                        <p>
                        	打你那么多通电话不接，考虑过我的感受吗
                        	<img src="/assets/img/amuse/pz.png">
                        </p>
                    </div>
                    <p class="post">
                        <img src="${(user.avatar)?if_exists}">
                    </p>
                    <div class="po-hd">
                        <p class="time">2分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">成龙，何炅，黄渤，蔡康永...</div>
                        <div class="cmt-list">
                            <p><span>蔡康永：</span>谁欺负你了宝贝？</p>
                            <p><span>黄渤：</span>我有空我有空我有空！</p>
                            <p><span class="data-name">${(user.nickname)?default('宠信')}</span><span>：</span>最近忙，就酱。</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/my.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">马云</p>
                        <div class="post">
                            <p>干得漂亮！<br>
							      双十一完美落幕！！<br>
							      双十二继续努力！！！
							  <img src="/assets/img/amuse/mypt.jpg">
							</p>
                        </div>
                        <p class="time">2分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">柳传志，冯小刚，李连杰，赵薇...</div>
                        <div class="cmt-list">
                        	<p><span>刘强东：</span>最近忙着带奶茶做孕检，等我忙好再发洪荒之力！</p>
                        	<p><span>${(user.nickname)?default('宠信')}：</span>你呀，就是太拼了！</p>
                            <p><span>马云</span>回复<span class="data-name">${(user.nickname)?default('宠信')}</span><span>：</span>哥说的是，哥啥时候有空一起出来吃个便饭呗。</p>
                            <p><span class="data-name">${(user.nickname)?default('宠信')}</span>回复<span>马云</span><span>：</span>问我助理哦~</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/logo1.png">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="ads">推广<img src="/assets/img/amuse/ads.png"></p>
                        <p class="po-name">宠信宝</p>
                        <div class="post">
                           	 长按识别下面二维码有惊喜
                            <p><a class="ad-link" href="">查看详情 <img src="/assets/img/amuse/link.png"></a></p>
                            <img src="/assets/img/qrcode.jpg">
                        </div>
                        <p class="time">45分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">周杰伦，蔡依林，刘德华，TFboys...</div>
                        <div class="cmt-list">
                            <p><span>成龙：</span>duang！真的很不错！</p>
                            <p><span>孙俪：</span>上次通过这个预约120上门服务，狗狗的小毛病很快就搞定了！<img src="/assets/img/amuse/qiang.png"></p>
                            <p><span>邓超</span>回复<span>孙俪 ：</span>老婆说好就是好。We are 伐木累，欧耶！<img src="/assets/img/amuse/mmd.png"></p>
                        </div>
                    </div>
            </div></li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/fbb.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">范冰冰</p>
                        <div class="post">
                            <p>拍完夜戏回来发现狗狗生病了，怎么办呀，亲爱的@李晨
                            	<img src="/assets/img/amuse/fbbpt.jpg">
                            </p>
                        </div>
                        <p class="time">50分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">${(user.nickname)?default('宠信')}，李冰冰，陈赫...</div>
                        <div class="cmt-list">
                        	<p><span>李冰冰：</span>手滑~</p>
                        	<p><span>陈赫：</span>你是猪吗？找宠信啊！</p>
                            <p><span>李晨</span>回复<span>陈赫</span><span>：</span>你能不能跟我家冰冰好好说话？</p>
                            <p><span>李晨：</span>不过宝贝，这次真被他说对了，艾特我没用，要艾特宠信啊！</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/wzl.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">王祖蓝</p>
                        <p class="post">完美！<img src="/assets/img/qrcode.jpg"></p>
                        <p class="time">55分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                    	<div class="like"><img src="/assets/img/amuse/l.png">金星，邓超，包贝尔，王宝强...</div>
                        <div class="cmt-list">
                        	<p><span>王宝强：</span>城会玩！</p>
                        	<p><span>金星：</span>祖蓝你个小调皮</p>
                        	<p><span class="data-name"></span>回复<span>金星</span><span>：</span>星姐，求虐</p>
                            <p><span>金星</span>回复<span class="data-name">${(user.nickname)?default('宠信')}</span><span>：</span>明明可以靠脸，你却偏要靠实力，就不多说了——完美！</p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </script>
    <script id="woman" type="text/template">
        <ul>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/n5.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">王思聪</p>
                        <div class="post">
                            <p>
                            @<b class="data-name"></b>下了班上公司天台，直升机接你，我在马尔代夫等你吃烛光晚餐哦！
                            </p>
                            <img class="list-img" src="/assets/img/amuse/md1.jpg" />
                            <img class="list-img" src="/assets/img/amuse/md2.jpg" />
                        </div>
                        <p class="time">刚刚</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">王健林，林更新，房祖名...</div>
                        <div class="cmt-list">
                            <p><span>林更新：</span>呵呵,任性</p>
                            <p><span>王健林：</span>好好玩，钱不够call爸比</p>
                            <p><span class="data-name"></span><span>：</span>谢谢亲爱哒！可可也去吗？</p>
                            <p><span>王思聪</span>回复<span class="data-name"></span><span>：</span>对的。你记得给她带她最爱的金盾狗粮</p>
                            <p><span class="data-name"></span>回复<span>王思聪</span><span>：</span>好的么么哒<img src="/assets/img/amuse/mmd.png"></p>
                        </div>
                    </div>
                </div>
            </li>

            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/dmj.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">都敏俊</p>
                        <div class="post">
                            <p>大家好！请群里的大伙欢迎第二季《来自星星的你》的新女主角，<span class="data-name"></span></p>
                            <p>
                                <img class="list-img" src="/assets/img/amuse/dmjpz.jpg">
                                <img class="list-img data-avt" src="">
                            </p>
                        </div>
                        <p class="time">1分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">李敏镐 ，玄彬,<span class="data-name"></span>...</div>
                        <div class="cmt-list">
                            <p><span>李敏镐：</span>好羡慕！期待下一部片子也可以和<b class="data-name"></b>合作~</p>
                            <p><span>权志龙：</span>下一场巡回演唱会，我也想邀请<b class="data-name"></b>当女嘉宾。</p>
                            <p><span>千颂伊：</span>欧巴，卡鸡嘛~</p>
                            <p><span class="data-name"></span><span>：</span>上面那位神烦，都不想想女主现在是谁。</p>
                            <p><span>都敏俊</span>回复<span class="data-name"></span><span>：</span>宝贝憋缩话，吻我~</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/wyf.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">吴亦凡</p>
                        <p class="post">
                        	选择在今天公开恋情......我爱你，我们在一起♥ @<b class="data-name"></b>
                        	<img src="/assets/img/amuse/wyfpt.jpg">
                        </p>
                        <p class="time">2分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">张艺谋，范冰冰 ，徐静蕾......</div>
                        <div class="cmt-list">
                        	<p><span>鹿晗：</span>我的梦碎了</p>
                        	<p><span>李易峰：</span>我的梦碎了x2</p>
                        	<p><span>杨洋：</span>我的梦碎了x3</p>
                            <p><span class="data-name"></span><span>：</span>谢谢大家对我们的祝福，我们会一直幸福下去</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/logo1.png">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="ads">推广<img src="/assets/img/amuse/ads.png"></p>
                        <p class="po-name">宠信宝</p>
                        <div class="post">
                           	 长按识别下面二维码有惊喜
                            <p><a class="ad-link" href="">查看详情 <img src="/assets/img/amuse/link.png"></a></p>
                            <img src="/assets/img/qrcode.jpg">
                        </div>
                        <p class="time">45分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">周杰伦，蔡依林，刘德华，TFboys...</div>
                        <div class="cmt-list">
                            <p><span>成龙：</span>duang！真的很不错！</p>
                            <p><span>孙俪：</span>上次通过这个预约120上门服务，狗狗的小毛病很快就搞定了！<img src="/assets/img/amuse/qiang.png"></p>
                            <p><span>邓超</span>回复<span>孙俪 ：</span>老婆说好就是好。We are 伐木累，欧耶！<img src="/assets/img/amuse/mmd.png"></p>
                        </div>
                    </div>
            </div></li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/fbb.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">范冰冰</p>
                        <div class="post">
                            <p>拍完夜戏回来发现狗狗生病了，怎么办呀，亲爱的@李晨
                            	<img src="/assets/img/amuse/fbbpt.jpg">
                            </p>
                        </div>
                        <p class="time">50分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                        <div class="like"><img src="/assets/img/amuse/l.png">${(user.nickname)?default('宠信')}，李冰冰，陈赫...</div>
                        <div class="cmt-list">
                        	<p><span>李冰冰：</span>手滑~</p>
                        	<p><span>陈赫：</span>你是猪吗？找宠信啊！</p>
                            <p><span>李晨</span>回复<span>陈赫</span><span>：</span>你能不能跟我家冰冰好好说话？</p>
                            <p><span>李晨：</span>不过宝贝，这次真被他说对了，艾特我没用，要艾特宠信啊！</p>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="po-avt-wrap">
                    <img class="po-avt" src="/assets/img/amuse/wzl.jpg">
                </div>
                <div class="po-cmt">
                    <div class="po-hd">
                        <p class="po-name">王祖蓝</p>
                        <p class="post">完美！<img src="/assets/img/qrcode.jpg"></p>
                        <p class="time">55分钟前</p><img class="c-icon" src="/assets/img/amuse/c.png">
                    </div>
                    <div class="r"></div>
                    <div class="cmt-wrap">
                    	<div class="like"><img src="/assets/img/amuse/l.png">金星，邓超，包贝尔，王宝强...</div>
                        <div class="cmt-list">
                        	<p><span>王宝强：</span>城会玩！</p>
                        	<p><span>金星：</span>祖蓝你个小调皮</p>
                        	<p><span class="data-name"></span>回复<span>金星</span><span>：</span>星姐，求虐</p>
                            <p><span>金星</span>回复<span class="data-name">${(user.nickname)?default('宠信')}</span><span>：</span>明明可以靠脸，你却偏要靠实力，就不多说了——完美！</p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>

    </script>

    <script>
        function gotoplay() {
            var gourl = '/amuse/share/friendcircle?gotoplay=1&fromuid=${(user.uid)?default(0)}';
            location.href = gourl;
        };

        function safetostring(str) {
            return String(str).replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"');
        }
        var nickname = '';
        var randomnumber = Math.floor(Math.random() * 100000);

        var nickname = '${(user.nickname)?default('宠信')}';
        var headimgurl = '${(user.avatar)?if_exists}';
        var user_sex = '${(user.sex)?if_exists}';

        if (user_sex == 2) {
            $("#list").html($("#woman").html());
        } else {
            $("#list").html($("#man").html());
        }
        setTimeout(function () {
            $(".data-name").text(nickname);
            $(".data-avt").attr("src", headimgurl);
            var cw = $('.list-img').width();
            $('.list-img').css({ 'height': cw + 'px' });
        }, 0);


        $(window).resize(function () {
            var cw = $('.list-img').width();
            $('.list-img').css({ 'height': cw + 'px' });
        });

        $.fn.isOnScreen = function () {

            var win = $(window);

            var viewport = {
                top: win.scrollTop(),
                left: win.scrollLeft()
            };
            viewport.right = viewport.left + win.width();
            viewport.bottom = viewport.top + win.height();

            var bounds = this.offset();
            bounds.right = bounds.left + this.outerWidth();
            bounds.bottom = bounds.top + this.outerHeight();

            return (!(viewport.right < bounds.left || viewport.left > bounds.right || viewport.bottom < bounds.top || viewport.top > bounds.bottom));

        };

        $(document.body).show();

        $("#gotoshare").click(function () {
            $("#guide").show();
        });

        $("#guide").click(function () {
            $(this).hide();
        });

        $('body').on('click', function (e) {

            $element = $('#gotoplay');
            if ($element.isOnScreen()) {
                $("#shopping-box").hide();
            } else {
                if ($("#shopping-box").is(":visible")) {
                    $("#shopping-box").slideUp();
                } else {
                    $("#shopping-box").hide();
                    $("#shopping-box").slideDown();
                }

            }
        });
        $(window).scroll(function () {
            //

            $element = $('#gotoplay');
            if ($element.isOnScreen()) {
                $("#shopping-box").hide();
            }
        })
    </script>
    <style>
        body > span {
            display: none;
        }
    </style>




    <script type="text/javascript">
        $(document).ready(function (e) {
            var url = location.href;
            var dataForWeixin = {
                MsgImg: '${(user.avatar)?if_exists}',
                TLImg: '${(user.avatar)?if_exists}',
                url: url+'?fromuid=${(user.uid)?default(0)}',
                title: "我的朋友圈",
                desc: "其实我的朋友圈，经常是这样的",
                callback: function () { }
            };
            wx.config({
                debug: false,
                appId: '${(weixinShare.appId)?if_exists}',
                timestamp: '${(weixinShare.timestamp)?if_exists}',
                nonceStr: '${(weixinShare.nonceStr)?if_exists}',
                signature: '${(weixinShare.signature)?if_exists}',
                jsApiList: [
                  'onMenuShareTimeline',
                  'onMenuShareAppMessage',
                  'onMenuShareQQ',
                  'onMenuShareWeibo',
                ]
            });
            wx.ready(function () {
                //在此输入各种API
                //分享到朋友圈
                wx.onMenuShareTimeline({
                    title: dataForWeixin.title, // 分享标题
                    link: dataForWeixin.url, // 分享链接
                    imgUrl: dataForWeixin.MsgImg, // 分享图标
                    success: function () {
                        //alert("onMenuShareTimeline_Success");
                        //$.Zero.AddWeChatCount(pos,2);
                        //IsAttentionUser();
                        //location.href = "/amuse/share/friendcircle?isme=0&fromuid=${(user.uid)?default(0)}";
                    },
                    cancel: function () {
                        //alert("onMenuShareTimeline_Fail");
                        // 用户取消分享后执行的回调函数
                    }
                });
                //分享给朋友
                //alert("onMenuShareAppMessage");
                wx.onMenuShareAppMessage({
                    title: dataForWeixin.title, // 分享标题
                    desc: dataForWeixin.desc, // 分享描述
                    link: dataForWeixin.url, // 分享链接
                    imgUrl: dataForWeixin.TLImg, // 分享图标
                    type: '', // 分享类型,music、video或link，不填默认为link
                    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {
                        //alert("onMenuShareAppMessage_Success");
                        //$.Zero.AddWeChatCount(pos, 2);
                        //IsAttentionUser();
                        //location.href = "/amuse/share/friendcircle?isme=0&fromuid=${(user.uid)?default(0)}";
                    },
                    cancel: function () {
                        //alert("onMenuShareAppMessage_Cancel");
                        // 用户取消分享后执行的回调函数
                    }
                });
                //QQ
                //alert("onMenuShareQQ");
                wx.onMenuShareQQ({
                    title: dataForWeixin.title, // 分享标题
                    desc: dataForWeixin.desc, // 分享描述
                    link: dataForWeixin.url, // 分享链接
                    imgUrl: dataForWeixin.MsgImg,// 分享图标
                    success: function () {
                        //alert("onMenuShareQQ_Success");
                        //$.Zero.AddWeChatCount(pos, 2);
                    },
                    cancel: function () {
                        //alert("onMenuShareQQ_Cancel");
                        // 用户取消分享后执行的回调函数
                    }
                });

                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，
                //所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            });
            wx.error(function (res) {

                //alert("error:" + res);
                //alert(res);
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            });
        });

        //function IsAttentionUser() {
            //var form = $.Zero.GetParam("from");
            //var isappinstalled = $.Zero.GetParam("isappinstalled");
            //var hasAttentionUrl = '/Activity/UnAttentionUserQrCode?sex=1&autoID=1306562';
            //location.href = hasAttentionUrl;// + "&form=" + form + "&isappinstalled=" + isappinstalled;
            //}
        //}
    </script>
	<div style="display:none;">
    	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256842157'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256842157%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
</body>
</html>