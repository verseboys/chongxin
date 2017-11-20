$.Zero = {
    Desc: "",
    BackUrl: null,
    PoslistUrl: null,
    Params: null,
    DownUrl: null,
    NameOfCookie: "OpenByCookie12",

    Init: function (json) {
        this.DownUrl = json.DownUrl;
        this.BackUrl = json.BackUrl;
        this.PoslistUrl = json.PoslistUrl;
        this.GetUrl();
        $("#fhfont").show();
        this.InitUrl();
        this.BindEvent();
        this.CheckOpen();
        $.Zero.Login.Init(json);
        $("#upd").hide();
    },
    InitUrl: function () {
        if ($.Zero.GetParam("pos") == '') {
            $("#jtid").hide();
        }
        else {
            $("#jtid").show();
            //$("a[name=backpos]").each(function () {
            //    $(this).attr("href", $.Zero.BackUrl + "?pos=" + $.Zero.GetParam("pos") + $.Zero.GetUserIDParam() + $.Zero.GetTokenParam());
            //});
        }

        $("a[name=comm]").each(function () {
            $(this).attr("href", $.Zero.Position.Url + "?pos=" + $.Zero.GetParam("pos") + "&UserID=" + $("#hdnuser").val() + $.Zero.GetTokenParam());
        });

        $("a[name=poslist]").each(function () {
            if ($("#hdnuser").val() == '') {

                //$("#down").hide();
                $(this).attr("href", $.Zero.PoslistUrl + "?pos=" + $.Zero.GetParam("pos") + "&UserID=" + $.Zero.GetParam("UserID") + $.Zero.GetTokenParam());

            } else {

                $(this).attr("href", $.Zero.PoslistUrl + "?pos=" + $.Zero.GetParam("pos") + "&UserID=" + $("#hdnuser").val() + $.Zero.GetTokenParam());

            }
        });
        //if ($.Zero.IsWeixin()) {
        //    $("#down").hide();
        //}
        $("#down").hide();
    },
    BindEvent: function () {
        $("#exit").click(function () {
            $("#down").hide();
        });

        $("#downurl").click(function () {
            if ($.Zero.IsWeixin()) {
                $("#divShade").show();
                $("#divWarn").show();
            }

        });
        $("a[name=downurl]").click(function () {
            if ($.Zero.IsWeixin()) {
                $("#divShade").show();
                $("#divWarn").show();
            }
        });
        //点击提示框，隐藏
        $("#divShade").click(function () {
            $("#divShade").hide();
            $("#divWarn").hide();
            $("#divLogin").hide();
            $("#divbingmobile").hide();
            $("#divAlert").hide();
            $("#divAlert1").hide();
            $("#divService").hide();
            $("#RemarkNull").hide();
            $("#RemarkLength").hide();
            $("#divcomplete").hide();
            $("#divTCount").hide();
            $("#divDown").hide();
            $("#divShare").hide();
            $("#divcashShare").hide();
            $("#divShareMsg").hide();
            $("#divGeneral").hide();
            $("#showsharelist").hide();
        });

        $("#divWarn").click(function () {
            $("#divShade").hide();
            $("#divWarn").hide();
            $("#divLogin").hide();
            $("#divbingmobile").hide();
            $("#divAlert").hide();
            $("#divAlert1").hide();
            $("#divService").hide();
            $("#RemarkNull").hide();
            $("#RemarkLength").hide();
            $("#divcomplete").hide();
            $("#divTCount").hide();
            $("#divDown").hide();
            $("#divShare").hide();
            $("#divcashShare").hide();
            $("#divShareMsg").hide();
            $("#divGeneral").hide();
            $("#showsharelist").hide();
        });
        $("#divShare").click(function () {
            $("#divShare").hide();
            $("#divShade").hide()
        });
        $("#divcashShare").click(function () {
            $("#divcashShare").hide();
            $("#divShade").hide()
        });
        $("#divGeneral").click(function () {
            $("#divGeneral").hide();
            $("#divShade").hide()
        });

        $("#btnlogin").click(function () {
            //判断是否已登录
            if ($.Zero.CheckCookies("LoginCreateByCookie")) {
                $("#divLogin").hide();
                $("#divShade").hide();
                var t = $("#topics").val();
                if (t == 1) {
                    location.href = "/topics/zhaopin";
                } else {
                    location.href = "/Hunter/Detail?pos=" + $.Zero.GetParam("pos");
                }

            }
            else {
                $("#divLogin").show();
                $("#divShade").show();
                $("#first").show();
                $("#login").hide();
                $("#regist").hide();
            }
        });

        $("addPic").click(function () {
            wx.checkJsApi({
                jsApiList: [
                  'onMenuShareTimeline',
                  'onMenuShareAppMessage'
                ],
                success: function (res) {
                    alert(JSON.stringify(res));
                }
            });
        })
    },
    GetRequest: function () {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0].toLowerCase()] = decodeURIComponent(strs[i].split("=")[1]);
            }
        }
        this.Params = theRequest;
    },
    GetParam: function (name) {
        if (!this.Params) {
            this.GetRequest();
        }
        return this.Params == null ? "" : this.Params[name.toLowerCase()];
    },
    GetUserIDParam: function () {
        if ($.Zero.GetParam("userid") != undefined && $.Zero.GetParam("userid") != '') {
            return "&userid=" + $.Zero.GetParam("userid");
        } else {
            return '';
        }
    },
    GetTokenParam: function () {
        if ($.Zero.CheckCookies("LoginCreateByCookie")) {
            return "&token=" + $.Zero.GetCookieValue("LoginCreateByCookie");
        } else {
            return '';
        }
    },
    GetSharParam: function () {
        var t = '';
        if ($.Zero.GetParam("s") != undefined && $.Zero.GetParam("s") != '') {
            t += "&s=" + $.Zero.GetParam("s");
        }
        if ($.Zero.GetParam("l") != undefined && $.Zero.GetParam("l") != '') {
            t += "&l=" + $.Zero.GetParam("l");
        }
        if ($.Zero.GetParam("c") != undefined && $.Zero.GetParam("c") != '') {
            t += "&c=" + $.Zero.GetParam("c");
        }
        return t;
    },
    Alter: function (content) {
        $("#alterid").show();
        $("#tsspan").html(content);
        $.layer({
            type: 1,
            title: false, //不显示默认标题栏
            shade: [0], //不显示遮罩
            area: ['auto', 'auto'],
            border: [0],
            bgcolor: 'transparent',
            page: { dom: "#alterid" },
            closeBtn: false,
            time: 2
        });
    },
    GetUrl: function () {
        $.getJSON(this.DownUrl,
                {
                }, function (json) {
                    if (json.Code == 0) {
                        $("a[name=downurl]").each(function () {
                            $(this).attr("href", json.Result);
                        });
                    }
                });
    },
    IsWeixin: function () {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    },
    CheckOpen: function () {

        //判断如果是在微信内置浏览器中
        if ($.Zero.IsWeixin()) {

            $("a[name=shopname]").each(function () {
                $(this).attr("href", "tel:" + $.Zero.Position.ShopMPhone);
            });

            if ($.Zero.CheckCookies($.Zero.NameOfCookie)) {
                $("#divShade").hide();
                $("#divWarn").hide();

                $("img[name=tsimg]").each(function () {
                    $(this).attr("src", "/Images/ts.png");
                });
            }
            else {
                $("img[name=tsimg]").each(function () {
                    $(this).attr("src", "/Images/sc.png");
                });

                $.Zero.AddCookies($.Zero.NameOfCookie);
                //$("#divShade").show();
                //$("#divWarn").show();
            }
        }

    },
    GetComName: function () {

        $("#nowcom").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/Search/SearchGet",
                    dataType: "json",
                    data: {
                        name: request.term,
                        pagesize: "5"
                    },
                    success: function (data) {

                        var r = $.parseJSON(data)

                        response($.map(r.Result, function (item) {
                            return {
                                label: item.Name,
                                value: item.Name
                            }
                        }));
                    }
                });
            },
            minLength: 2
        });
    },
    GetPosName: function () {

        $("#nowpos").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/Search/SearchGetPos",
                    dataType: "json",
                    data: {
                        name: request.term,
                        pagesize: "5"
                    },
                    success: function (data) {

                        var r = $.parseJSON(data)

                        response($.map(r.Result, function (item) {
                            return {
                                label: item.Name,
                                value: item.Name
                            }
                        }));
                    }
                });
            },
            minLength: 2
        });
    },
    AddCookies: function (name, domain) {

        var str = name + "=" + escape("ok");

        str += ";path=" + "/";

        document.cookie = str;
    },
    GetCookieValue: function (name) {
        var cookie_value = $.Zero.GetCookies(name);
        return cookie_value;
    },
    CheckCookies: function (name) {

        var c = document.cookie.indexOf(name + "=");
        if (c >= 0) {
            return true;
        }
        else {
            return false;
        }
    },
    GetCookies: function (objName) {
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName) return unescape(temp[1]);
        }
    },
    AddWeChatCount: function (pos, type) {
        if ($.Zero.IsWeixin()) {
            $.getJSON("/Position/WeChatAnalysis",
                        {
                            posNo: pos,
                            type: type
                        }, function (json) {
                        });
        }
       
    },
    AddWeChatShare: function (url, act) {
        if ($.Zero.IsWeixin()) {
            $.ajax({
                type: "get",
                async: true,
                url: "/Analysis/WeChatShare",
                data: { acturl: encodeURIComponent(url), actfrom: act },
                dataType: "json",
                success: function (json) {
                },
            });
        }
    },
    GetComName: function () {

        $("#txtEmName").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/Position/SearchEmployer",
                    dataType: "json",
                    data: {
                        name: request.term,
                        pagesize: "5"
                    },
                    success: function (data) {

                        var r = $.parseJSON(data)
                        response($.map(r.Result, function (item) {

                            return {
                                label: item.Name,
                                value: item.Name,
                                id: item.ID
                            }

                        }));
                    }
                });
            },
            minLength: 2,

            select: function (event, ui) {
                $("#hdfEid").val(ui.item.id);
            }
        });
    },
}