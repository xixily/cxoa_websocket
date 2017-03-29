/**
 * Created by dengxf on 2016/11/30.
 * required $ or jQuery first
 */
$(function () {
    menuInit(function () {
        $('#side-menu').metisMenu();
        sideBarClick();
        initClickHandler();
        enterKeyLisener();
        //默认打开合同管理
        try {
            var uls = $('#side-menu ul>li');
            var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
            $.each(uls, function (k, oo) {
                if (oo.innerText.isLike("合同管理")) {
                    var url = $(oo).find('a').attr('href').split('\.')[0];
                    ;
                    if (width < 768) {
                        $.open(url, function (success) {
                            if (success) {
                                try {
                                    $('#container [id*="_head"]').find("[app-action]").trigger('click');
                                    var action = $('#container [app-init]').attr('app-init');
                                    if (action && action != '') {
                                        var actionHandler = eval(action);//jQuery.gloabEval()全局方法
                                        if (actionHandler) {
                                            actionHandler();
                                        } else {
                                            console.log('action handler [' + action + '] is not support!');
                                        }
                                    }
                                } catch (e) {
                                    console.log("没有找到app-action");
                                }
                            }
                        });
                    } else {
                        $(oo).parent().parent().find('>a').trigger('click')
                        $(oo).find('a').trigger('click');
                    }
                }
            })
        } catch (e) {
        }
    });
});
$(function () {//监听屏幕变化，重组slider 导航条
    $(window).bind("load resize", function () {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 112; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
//        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });
    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function () {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
//    responsiveHandler.run();
});
//var handler = window;
(function ($) {
    function containerAppend(jq, callback) {
        var container = $('#container');
        try {
            container.html("");
            jq.appendTo(container);
            container.find("[id*='_body']").html("");//掏空body里面的内容
            callback(true);
        } catch (e) {
            callback(false);
            $.messager.alert("提示", "container 插入失败!~");
            console.log("container 插入失败 %o", e);
        }
    }

    function open(jq, callback) {
        var url = (typeof jq == "string") ? jq : jq.attr("href");
        if (url && url != '') {
            if (!session.sidebar.opened || session.sidebar.opened != url) {
                session.sidebar.opened = url;
                try {
                    $('#loading').removeClass('hide');
                    $('#container').html('');
                } catch (e) {
                }
                getBufferView(url, function (view) {
                    var view = $(view);
                    try {
                        $('#loading').addClass('hide');
                    } catch (e) {
                    }
                    containerAppend(view, callback);
                })
            }
        }
    }

    $.open = open;

    $.containerAppend = containerAppend;

    var appDialog = {
        getDefaults: function () {
            return $.extend(true, {}, appDialog.DEFAULTS);
        },
        DEFAULTS: {
            title: '提示：',
            content: '',
            backdrop: 'static',
            closeAble: true,
            buttons: [
                {
                    text: '确定',
                    class: 'btn-default',
                    bstarget: {
                        name: 'data-dismiss',
                        value: 'modal'
                    },
                    handler: function (event) {//dom 点击事件
                    }
                }
            ]
        }
    }

    function addButtonFn(buttons, dom) {
        if (buttons.length > 0) {
            var button;
            var btn;
            for (var i = 0; i < buttons.length; i++) {
                button = buttons[i];
                btn = $('<button class="btn" type="button"></button>');
                btn.html(button.text);//只可以放文本
                button.class ? btn.addClass(button.class) : btn.addClass(button.class).addClass("btn-default");
                button.bstarget ? (function () {
                    btn.attr(button.bstarget.name, button.bstarget.value)
                })() : undefined;
                button.target ? (function () {
                    btn.attr(button.target.name, button.target.value)
                })() : undefined;
                button.handler ? (function () {
                    btn.bind('click', button.handler)
                })() : undefined;
                dom.append(btn);
            }
        } else {
            return false;
        }
        var DEFAULTS = [
            {
                text: '取消',
                class: 'btn-default',
                bstarget: {
                    name: 'data-dismiss',
                    value: 'modal'
                },
                handler: function (event) {//dom 点击事件
                }
            }, {
                text: '确定',
                class: 'btn-default',
                bstarget: {
                    name: 'data-dismiss',
                    value: 'modal'
                },
                handler: function (event) {
                }
            }
        ]
    }

    appDialog.addButtonFn = addButtonFn;

    /**
     *{默认点击背景不会关闭}
     * @param options {title,content,backdrop,closeAble,buttons:[]}
     * @param okfn {默认确认按钮}
     * @param cancelfn {默认取消按钮}
     * @returns {*|jQuery}
     */
    function createDialog(options, okfn, cancelfn) {
        //先删除已有的创建的dialog,保证页面只有这么一个createDialog创建的dialog
        $('#app_dialog').length > 0 ? (function () {
            $('#app_dialog').modal('hide');
            $('#app_dialog').remove()
        })() : undefined;
//    $('.modal.fade[app-target="modal-dialog"]') ?(function(){$('.modal.fade[app-target="modal-dialog"]').modal('hide') ;$('.modal.fade[app-target="modal-dialog"]').remove()})():undefined;
        //创建dialog模板,并添加到body里面
        var dialog = $(
                "<div id='app_dialog' class='modal fade' app-target=\"modal-dialog\" aria-hidden=\"true\" data-backdrop=\"static\">" +
                "<div class='modal-dialog'>" +
                "<div class=\"modal-content\">" +
                "<div class=\"modal-header\">" +
                "<button class=\"close\" type=\"button\" data-dismiss=\"modal\"><span aria-hidden='modal'>&times;</span><span class=\"sr-only\">Close</span></button>" +
                "</div>" +
                "<div class=\"modal-body\"></div>" +
                "<div class=\"modal-footer\">" +
                "<button app-target='modal-cancel' class=\"btn btn-default\" type='button' data-dismiss='modal'>取消</button>" +
                "<button app-target='modal-save' type='button' class=\"btn btn-primary\" data-dismiss='modal'>确定</button>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>").appendTo('body');
        //判断是否允许点击背景关闭
        !options.backdrop ? dialog.attr('data-backdrop', true) : undefined;
        //添加用户数据
        $(dialog.find('.modal-header')[0]).append('<h4 class="modal-title">' + options.title + '</h4>');//添加头
        $(dialog.find('.modal-body')[0]).append(options.content);// 添加内容 (可以是标记语言）
        //是否显示关闭按钮
        !options.closeAble ? dialog.find('.modal-header button[data-dismiss="modal"]').remove() : undefined;

        var footer = $(dialog.find('.modal-footer')[0]);
        if (options.buttons.length > 0) {
            footer.html("");
            addButtonFn(options.buttons, footer);
        } else {
            okfn && typeof okfn == 'function' ? $(dialog.find('[app-target="modal-save"]')).bind('click', okfn) : undefined;//保存修改事件
            cancelfn && typeof cancelfn == 'function' ? $(dialog.find('[app-target="modal-cancel"]')).bind('click', cancelfn) : undefined;//关闭事件
        }
        return dialog;
    }

    appDialog.createDialog = createDialog;

    $.appDialog = appDialog;
    //创建消息提示dialog,方法调用规则（参照easyui）
    $.messager = {
        alert: function (title, content, okfn) {
            var options = appDialog.getDefaults();
            options.title = title ? title : options.title;
            options.content = content ? content : options.content;
            if (okfn && typeof okfn == 'function') options.buttons[0].handler = okfn;
//            if(buttons && buttons.length>0){
//                var btn = options.buttons[0];
//                $.each(buttons, function(index,value){
//                    var button = $.extend(true,{},btn);
//                    for(var i in value){
//                        button[i] = value[i];
//                    }
//                })
//            }
            createDialog(options).modal('show');
        },
        confirm: function (title, content, okfn, cancelfn) {
            var options = appDialog.getDefaults();
            options.buttons = [];
            options.title = title || options.title;
            options.content = content || options.content;
            createDialog(options, okfn, cancelfn).modal('show');
        },
        show: function (title, content, options) {

        }
    }

    function senAction() {

    }

    function appPost(url, params, callback, errorCallback, async, timeout) {
        timeout = timeout || 20000;
        async = async==undefined ? true : async;
        $.ajax({
            url: url,
            data: params,
            async: async,
            dataType: "json",
            timeout: timeout,
            type: "POST",
            success: function (data) {
                if (data && data.success) {
                    if (typeof callback == "function") {
                        data.obj ? callback(data.obj) : callback(data);
                    }
                } else {
                    var msg = data.msg ? data.msg : "请求被拒绝或者请求失败！";
                    $.messager.alert('请求失败：', msg)
                }
            },
            error: function (xhr, status, error) {
                if (errorCallback && (typeof errorCallback == "function")) {
                    errorCallback(xhr, status, error);
                } else {
                    if (xhr.statusText != 'success' && xhr.statusText != 'OK') {
                        $.messager.alert("请求反馈：", "请求超时或者网络异常" + status || error);
                    } else {
                        try {
                            $.messager.alert("请求反馈：", xhr.responseText);
                        } catch (e) {
                            console.log(e);
                        }
                    }
                }
            },
        })
    }

    $.appPost = appPost;

    function appGet(url, params, callback, errorCallback, async, timeout) {
        timeout = timeout ? timeout : 20000;
        async = async ? async : true;
        $.ajax({
            url: url,
            data: params,
            async: async,
            dataType: "json",
            timeout: timeout,
            type: "GET",
            success: function (data) {
                if (typeof callback == "function") {
                    data.obj ? callback(data.obj) : callback(data);
                }
                if (data && !data.Succeed && data.SessionTimeout == 1) {
                    $.messager.alert('Get请求：', '请求失败！~')
//                    alert("会话已经失效，请您重新登录");
//                    window.location.href = "./";
                    return;
                }
            },
            error: function (xhr, status, error) {
                if (errorCallback && (typeof errorCallback == "function")) {
                    errorCallback(xhr, status, error);
                } else {
                    if (xhr.statusText != 'success') {
                        $.messager.alert("请求反馈：", "请求超时或网络问题," + status || error);
                    }
                }
            }
        })
    }

    $.appGet = appGet;

    var post = $.post;
    $.post = undefined;
    function post_mask(url, data, callback, dataType) {
        post(url, data, function (data, textStatus, jqXHR) {
            if (typeof data == "string") {
                try {
                    data = eval("(" + data + ")");
                } catch (e) {
                    data = {msg: data};
                }
            }
            callback(data, textStatus, jqXHR);
        }, dataType)
    }

    $.post = post_mask;
})($);

/**
 * 初始 seesion 缓存 实例
 * @type {{user: {userName: string, userRole: number}, views: {}, logined: boolean, equipment: string}}
 */
session = {
    user: {
        userName: '',
        userRole: 99
    },
    views: {},
    sidebar: {},
    opened: '',
    logined: false,
    equipment: 'computer',
    table: {},
    app_back: ''
}
///为字符串添加模糊比较的方法
String.prototype.isLike = function (exp/*类似于SQL中的模糊查询字符串*/, i/*是否区分大小写*/, start/*以该字符串开始*/, end/*以该字符串结束*/) {
    var str = this;
    i = (i == null ? false : i);
    start = (start ? "^" : "");
    end = (end ? "$" : "");
    if (exp.constructor == String) {
        /*首先将表达式中的‘_’替换成‘.’，但是‘[_]’表示对‘_’的转义，所以做特殊处理*/
        var s = exp.replace(/_/g, function (m, i) {
            if (i == 0 || i == exp.length - 1) {
                return ".";
            }
            else {
                if (exp.charAt(i - 1) == "[" && exp.charAt(i + 1) == "]") {
                    return m;
                }
                return ".";
            }
        });
        /*将表达式中的‘%’替换成‘.’，但是‘[%]’表示对‘%’的转义，所以做特殊处理*/
        s = s.replace(/%/g, function (m, i) {
            if (i == 0 || i == s.length - 1) {
                return ".*";
            }
            else {
                if (s.charAt(i - 1) == "[" && s.charAt(i + 1) == "]") {
                    return m;
                }
                return ".*";
            }
        });

        /*将表达式中的‘[_]’、‘[%]’分别替换为‘_’、‘%’*/

        s = s.replace(/\[_\]/g, "_").replace(/\[%\]/g, "%");

        /*对表达式处理完后构造一个新的正则表达式，用以判断当前字符串是否和给定的表达式相似*/

        /*var regex = new RegExp("^" + s, i ? "" : "i");*/
        var regex = new RegExp(start + s + end, i ? "" : "i");
        return regex.test(this);
    }
    return false;
};

///为数组添加模糊查询方法
Array.prototype.selectLike = function (exp/*类似于SQL中的模糊查询字符串*/, fun, start, end) {
    var arr = [];
    start = (start ? true : false);
    end = (end ? true : false);
    if (fun && fun.constructor == Function) {
        for (var i = 0; i < this.length; i++) {
            if (fun(this[i], exp)) {
                arr.push(i);
            }
        }
    }
    else {
        for (var i = 0; i < this.length; i++) {
            if (this[i].isLike(exp, false, start, end)) {
                arr.push(i);
            }
        }
    }
    return arr;
};
/**
 * getCaiwu() {获取财务id}
 * getBaoxiao() {获取报销id}
 * getHetong() {获取合同id}
 * getHetongGuanli() {获取合同管理id}
 * @type {{DEFAULTS: {CAIWU: string, BAOXIAO: string, HETONG: string, HETONGGUANLI: string}, getCaiwu: Function, getBaoxiao: Function, getHetong: Function, getHetongGuanli: Function}}
 */
app = {
    ID_DEFAULTS: {
        CAIWU: 'cw_',
        BAOXIAO: 'cw_bx_',
        YIPIZHUN: 'cw_ypz_',
        DAISHOUPIAO: 'cw_dsp_',
        DAICHUPIAO: 'cw_dcp_',
        DAIHUIKUAN: 'cw_dhk_',
        YIHUIKUAN: 'cw_yhk_',
        YIBAOXIAO: 'cw_ybx_',
        HETONG: 'ht_',
        HETONGGUANLI: 'ht_gl_',
        EXCELDOWNLOAT: 'excel_download_form'
    },
    DATA_DEFAULTS: {
        DIV_DATA: 'data',
        PAGE: 'page',
        PAGE_SIZE: 10,
        QUERY_PARAMS: 'queryParams'
    },
    PAGE_DEFAULTS: {
        page: 1,
        rows: 10
    },
    getCaiwu: function () {
        return app.ID_DEFAULTS.CAIWU;
    },
    getBaoxiao: function () {
        return app.ID_DEFAULTS.BAOXIAO;
    },
    getYiPiZhun: function () {
        return app.ID_DEFAULTS.YIPIZHUN;
    },
    getDaiShouPiao: function () {
        return app.ID_DEFAULTS.DAISHOUPIAO;
    },
    getDaiChuPiao: function () {
        return app.ID_DEFAULTS.DAICHUPIAO;
    },
    getDaiHuiKuan: function () {
        return app.ID_DEFAULTS.DAIHUIKUAN;
    },
    getYiHuiKuan: function () {
        return app.ID_DEFAULTS.YIHUIKUAN;
    },
    getYiBaoxiao: function () {
        return app.ID_DEFAULTS.YIBAOXIAO;
    },
    getHetong: function () {
        return app.ID_DEFAULTS.HETONG;
    },
    getHetongGuanli: function () {
        return app.ID_DEFAULTS.HETONGGUANLI;
    },
    getDivData: function () {
        return app.DATA_DEFAULTS.DIV_DATA;
    },
    getPage: function () {
        return app.DATA_DEFAULTS.PAGE;
    },
    getQueryParams: function () {
        return app.DATA_DEFAULTS.QUERY_PARAMS;
    },
    getPageSize: function () {
        return app.DATA_DEFAULTS.PAGE_SIZE;
    },
    getPageDefault: function () {
        return $.extend(true, {}, app.PAGE_DEFAULTS)
    },
    getExcelDownloadExcel: function () {
        return app.ID_DEFAULTS.EXCELDOWNLOAT;
    }
}

basePath = "./"
imgPath = "./app/images/";

/**
 * 初始化菜单
 */
function menuInit(callback) {
    var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
    if (height < 1) height = 1;
    if (height > 112) {
        $("#page-wrapper").css("min-height", (height) + "px");
    }
    //获得菜单信息
    $.appPost('public/user/getMenus.action', undefined, function (data) {
        $.each(data, function (i, obj) {
            var uls = obj.uls;
            var li = $('<li>');
            var a = $('<a href="#">' +
                    '<i class="' + (obj.iconCls ? obj.iconCls : 'fa fa-edit fa-fw') + '"></i>' + obj.menuName +
                    (uls.length > 0 ? '<span class="fa arrow"></span>' : '') +
                    '</a>');
            a.appendTo(li);
            //二级菜单
            if (uls && uls.length > 0) {
                var ul = $('<ul class="nav nav-second-level"></ul>');
                var second_li;
                $.each(uls, function (j, o) {
                    second_li = $('<li>' +
                            '<a href="' + o.url + '">' + o.text + '</a>' +
                            '</li>');
                    second_li.appendTo(ul);
                })
                ul.appendTo(li);
            }
            li.appendTo($('#side-menu'));

        })
        if (callback && typeof callback == 'function') {
            callback();
        }
    });
}

function logout() {
    $.getJSON('user/logout.action', function (result) {
        if (result.success) {
            console.log('登出成功！');
            location.replace('/cxoa/app_login.jsp');
        } else {
            console.log('登出成功！');
            location.replace('/cxoa/app_login.jsp');
        }
    });
}

function getView(view, callback, errorCallback) {
    $.ajax({
        type: "GET",
//        url: "./views/" + view,
        url: "./app/views/" + view,
        timeout: 10000,
        cache: false,
        success: function (data) {
            if (typeof callback == "function") {
                callback(data);
            }
            if (data && !data.Succeed && data.SessionTimeout == 1) {
                alert("会话已经失效，请您重新登录");
                window.location.href = "./";
                return;
            }
        },
        error: function (xhr, status, error) {
            if (errorCallback && (typeof errorCallback == "function")) {
                errorCallback(xhr, status, error);
            } else {
                if (xhr.statusText != 'success') {
                    $.messager.alert('页面请求：', '请求超时或网络问题,' + status || error);
                }
            }
        }
    });
}

/**
 *
 * @param view 放在view文件夹下的view
 * @param callback
 */
function getBufferView(view, callback) {
    session.views = session.views || {};
    if (!session.views[view]) {
        getView(view + ".html", function (data) {
            session.views[view] = data;
            callback(data);
        }, function () {

        })
    } else {
        callback(session.views[view]);
//        return session[view];
    }
}
/**
 * sidebar 菜单单击事件
 */
function sideBarClick() {
    //document 委派事件
    $(document).on("click", '#app_sidebar ul a[href*=".html"]', function (event) {
        event.preventDefault();//关闭默认事件
        event.stopImmediatePropagation();//停止冒泡
        var src = $(event.currentTarget);
        src.closest('ul').find('li a').removeClass('active')
//        src.siblings('a').removeClass('active');
        src.addClass("active");
        var url = src.attr('href').split("\.")[0];
        $('#app_sidebar>div').toggleClass("in")
        $.open(url, function (success) {
            if (success) {
                try {
                    $('#container [id*="_head_init"]').find("[app-action]").trigger('click');
                    var action = $('#container [app-init]').attr('app-init');
                    if (action && action != '') {
                        var actionHandler = eval(action);//jQuery.gloabEval()全局方法
                        if (actionHandler) {
                            actionHandler();
                        } else {
                            console.log('action handler [' + action + '] is not support!');
                        }
                    }
                } catch (e) {
                    console.log("没有找到app-action");
                    return false;
                }
            }
        });
    })
}
/**
 * @author dengxuefeng
 * @requires jquery.js easyui.js
 * @callback [data.data]<strong>form表单的元素值</strong>、[src]<strong>当前元素dom</strong>
 * 初始化
 */
function initClickHandler() {
    $(document).on("click", "[app-action]", function (event) {
        event.preventDefault();//关闭默认事件
        event.stopImmediatePropagation();//停止冒泡
        var src = $(event.currentTarget);
        var action = src.attr("app-action");
        var frm = src.closest("form");
        if (frm && !frm.form('validate')) {
            console.log('form 表单校验失败！');
            return false;
        }
        var data = frm.getDataFromForm();
//        var data = frm.serializeJson();
//        console.log('o%', data);
        var actionHandler = eval(action);//jQuery.gloabEval()全局方法
        if (actionHandler) {
            actionHandler(data, src);
        } else {
            console.log('action handler [' + action + '] is not support!');
        }
    });
}
/**
 *避免按了enter键之后，页面重新加载
 *
 */
function enterKeyLisener() {
    $(document).on("keypress", "form", function (event) {
        var theEvent = event || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
//        var src = $(event.currentTarget);
        if (13 === code) {
            var form = $(event.currentTarget);
            var actionHandler = form.find("[app-action]")
            if (actionHandler && actionHandler.length > 0) {
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();//停止冒泡
                actionHandler.trigger('click');
            } else {
                console.log("form:[%o" + form[0] + "]\n没有 app-action 方法。")
                return false;
            }
        }
//        console.log(code+ ":" + (typeof code));
    });

    //避免textarea因为按Enter键换行，被form按键监听到。
    $(document).on("keypress", "form textarea", function (event) {
        var theEvent = event || window.event;
        theEvent.stopImmediatePropagation();
    });
}
/**
 *
 * @param formId
 * @param isDisabled
 */
function disabledButton(formId, isDisabled) {
    var attr = "disable";
    if (!isDisabled) {
        attr = "enable";
        isDisabled = false;
    } else {
        attr = "disable";
        isDisabled = true;
    }
    //禁用jquery easyui中的linkbutton组件
    $("#" + formId + " a[class~='easyui-linkbutton']").each(function () {
        $(this).linkbutton({disabled: isDisabled})
    });
    //禁用jquery easyui中的menubutton组件
    $("#" + formId + " a[class~='easyui-menubutton']").each(function () {
        $(this).menubutton(attr);
    });
}

function bodyAppend(jq, head) {
    if (!jq || !head) return false;
    var body = $('#' + head + 'body');
    try {
        body.html("");
        body.append(jq.html());
//        jq.appendTo(body);
        return true;
    } catch (e) {
        console.log("container 插入失败 %o", e);
    }
}

/**
 *
 * @param number 要计算的数
 * @param fractiondigits 精确位
 * @returns {Number}
 */
function round2(number, fractiondigits) {
    with (Math) {
        return round(number * pow(10, fractiondigits)) / pow(10, fractiondigits);
    }
}

function responseHandler(res) {
    if (!res.success) {
        $.messager.alert('请求失败：', res.msg);
    }
    return res.success ? res.obj ? res.obj : new Array() : new Array();
    ' '
}

function queryParams() {

}

function show() {
    alert('toggle');
}
function getAppData(src) {
    if (!src) return false;
    var data = {};
    var app_datas = $(src).find('[app-data]');
    $.each(app_datas, function (i, obj) {
        obj = $(obj);
        if (obj.html() != "undefined") {
            data[obj.attr('app-data')] = obj.html();
        } else {
            data[obj.attr('app-data')] = undefined;
        }
    })
    return data;
}
app.getAppData = getAppData;
var downloadForm = {
    /**
     * 创建一个临时form，用于文件下载请求
     * @param formId
     * @returns {String}
     */
    createForm: function (formId) {
        var rformId = formId || app.getExcelDownloadExcel() + (new Date().getTime());
        $('form#' + rformId).remove();
        var frm = $("<form>");
        frm.attr("style", "display:none");
        frm.attr("target", "");
        frm.attr("id", rformId);
        frm.attr("method", "post");
        $("body").append(frm);//将表单放置在web中
        return rformId;

    },
    /**
     * 销毁创建的临时form
     * @param formId
     */
    destoryForm: function (formId) {
        if (!formId || formId == "") {
            return false;
        }
        $("form#" + rformId).remove();
    },
    download: function (url, data, callback) {
        data = data || {};
        var form = $('form#' + downloadForm.createForm());
        form.attr('action', url);
        try {
            for (var n in data) {
                $('<input type="hidden" name="' + n + '">').val(data[n]).appendTo(form);
            }
            if(callback &&  typeof callback == "function"){
                callback.call(form, data);
            }
            form.submit();
        } catch (e) {
            return false;
        } finally {
            form.remove();
        }
    },
    submit: function (url, data, formId, callback) {
        if (!formId || formId == "") {
            return false;
        }
        var form = $('form#' + formId);
        form.attr('action', url);
        try {
            for (var n in data) {
                $('<input type="hidden" name="' + n + '">').val(data[n]).appendTo(form);
            }
            callback.call(form, data);
            form.submit();
        } catch (e) {
            console.log("导出失败~");
            return false;
        } finally {
            form.remove();
        }
    },
    exportYDExcel: function (type) {
        var url = "file/exportYiDong.action";
        var exportParam = {};
        exportParam.type = type || "211";
        downloadForm.createForm();
        $("#export_query").form('submit', {
            url: url,
            queryParams: exportParam,
            onSubmit: function () {
                console.log("正在导出,请稍后");
            },
            onLoadSuccess: function () {
                downloadForm.destoryForm();
            }
        });
    }
}
app.downloadForm = downloadForm;
var responsiveHandler = {
    run: function () {
        $.each(responsiveHandler.handler, function (i, obj) {
            if (typeof obj == 'function') {
                $(obj);
            }
        })
    },
    handler: {}
}