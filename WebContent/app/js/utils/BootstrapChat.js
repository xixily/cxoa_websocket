/**
 * Created by dengxf on 2017/3/27.
 */
(function($){

    var websocket = null;
    var url = null;

    /**
     * 日期格式化
     * @param fmt "yyyy-MM-dd HH:mm:ss"
     * @returns string
     * @private
     */
    Date.prototype.format__=function(fmt) {
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }

    /**
     * 定义BootstrapChat
     * @param dom
     * @param options
     * @constructor
     */
    BootstrapChat = function(dom, options){
        this.options = options || {};
        this.$jq = $(dom);
        this.$jq_ = this.$jq.clone();
        this.$jq.addClass("display","none");
        this.timeoutId_ = 0;
        this.timeoutFooter_ = 0;
        this.dateValue = new Date().valueOf();
        this.datePattern = "yyyy-MM-dd HH:mm:ss";
    };

    var Dom = function(){

    }


    /**
     * BootstrapChat 默认属性
     * @type {{}}
     */
    var DEFAULTS = {
        dom:{
            class:'chat-panel panel panel-default',
            head:{
                item:'<div>',
                class:'panel-heading',
                content:[{
                    item:'<i>',
                    class:'fa fa-comments fa-fw',
                },{
                    item:'<span>',
                    text:'聊天'
                },{
                    item:'<div>',
                    class:'btn-group pull-right',
                    content:[{
                        item:'<button>',
                        class:'btn btn-default btn-xs dropdown-toggle',
                        property:{
                            type:'button'
                        },
                        exProperty:{
                            data_toggle:'dropdown',
                            aria_expanded:'true'
                        },
                        content:[{
                            item:'<i>',
                            class:'fa fa-chevron-down'
                        }]
                    },{
                        item:'<ul>',
                        class:'dropdown-menu slidedown',
                        content:[{
                            item:'<li>',
                            content:[{
                                item:'<a>',
                                content:[{
                                    item:'<i>',
                                    class:'fa fa-refresh fa-fw'
                                },{
                                    item:'<span>',
                                    text:'刷新'
                                }]
                            },{
                                item:'<a>',
                                content:[{
                                    item:'<i>',
                                    class:'fa fa-check-circle fa-fw'
                                },{
                                    item:'<span>',
                                    text:'在线'
                                }]
                            },{
                                item:'<a>',
                                content:[{
                                    item:'<i>',
                                    class:'fa fa-times fa-fw'
                                },{
                                    item:'<span>',
                                    text:'繁忙'
                                }]
                            },{
                                item:'<a>',
                                content:[{
                                    item:'<i>',
                                    class:'fa fa-clock-o fa-fw'
                                },{
                                    item:'<span>',
                                    text:'离开'
                                }]
                            },{
                                item:'<i>',
                                class:'divider'
                            },{
                                item:'<a>',
                                content:[{
                                    item:'<i>',
                                    class:'fa fa-refresh fa-fw'
                                },{
                                    item:'<span>',
                                    text:'退出'
                                }]
                            }]
                        }]
                    }]
                }]
            },
            body:{
                item:'<div>',
//                id:'chat_body_'+ this.dateValue,
                class:'panel-body',
                style:'height:150px;',
                content:[{
                    item:'<ul>',
//                    id:'chat_ul_'+ this.dateValue,
                    class:'chat',
                    content:[{
                        item:'<li>',
                        class:'left clearfix',
                        content:[{
                            item:'<span>',
                            class:'chat-img pull-left',
                            content:[{
                                item:'<img>',
                                class:'img-circle',
                                property:{
                                    src:"app/images/blue_60.png",
                                    alt:'系统消息',
                                },
                                exProperty:{
                                    app_data:"img"
                                }
                            }]
                        },{
                            item:'<div>',
                            class:'chat-body clearfix',
                            content:[{
                                item:'<div>',
                                class:'header',
                                content:[{
                                    item:'<strong>',
                                    class:'primary-font',
                                    content:[{
                                        item:'<span>',
                                        style:'color: #33a731;',
                                        exProperty:{
                                            app_data:'sender'
                                        },
                                        text:'[系统消息]'
                                    }]
                                },{
                                    item:'<small>',
                                    class:'pull-right text-muted',
                                    content:[{
                                        item:'<i>',
                                        class:'fa fa-clock-o fa-fw'
                                    },{
                                        item:'<span>',
                                        exProperty:{
                                            app_data:'time'
                                        },
                                        text: new Date().format__(this.date_pattern ? this.date_pattern : "yyyy-MM-dd HH:mm:ss")
                                    }]
                                }]
                            },{
                                item:'<p>',
                                style:"color:#66b665;",
                                exProperty:{
                                    app_data:'msg'
                                },
                                text:'欢迎使用 BootstrapChat！'
                            }]
                        }]
                    }]
                }]
            },
            footer:{
                item:'<div>',
//                id:'chat_footer_' + this.dateValue,
                class:'panel-footer',
                content:[{
                    item:'<form>',
                    content:[{
                        item:'<div>',
                        class:'input-group',
                        content:[{
                            item:'<input>',
//                            id:'chat_msg_inpu_' + this.dateValue,
                            class:'form-control input-sm',
                            property:{
                                type:'text',
                                name:'msg',
                                placeholder:'输入回复内容...'
                            }
                        },{
                            item:'<span>',
                            class:'input-group-btn',
                            content:[{
                                item:'<button>',
//                                id:'chat_send_btn_' + this.dateValue,
                                class:'btn btn-warning btn-sm',
                                property:{
                                    role:'button'
                                },
                                exProperty:{
                                    app_action:'BootstrapChat.__sendMessages'
                                },
                                text: '发送'
                            }]
                        }]
                    }]
                }]
            },
        },
        language:{
            en:{
                head_title: 'Chat',
                sys_img_alt: 'System messages.',
                sys_Name: '[System messages]',
                welcome:'Welcome to BootstrapChat!',
                sender_placeholder: 'Enter the reply content ...',
                sender_text:'Send'
            },
            cn:{
                head_title: '聊天',
                sys_img_alt: '系统消息.',
                sys_Name: '[系统消息]',
                welcome:'欢迎使用 BootstrapChat！',
                sender_placeholder: '输入回复内容...',
                sender_text:'发送'
            }
        },
        handler:{
            onClickSend:function(data){

            },
            onClickEmoji:function(dom){

            },
            onSelectEmoji:function(dom){

            }
        },
    };

    var getDefaults = function(that, language){
        var defaults = $.extend(true, {}, DEFAULTS);
        defaults.dom.body.id = 'chat_body_' + that.dateValue;
        defaults.dom.body.content[0].id = 'chat_ul_'+ that.dateValue;
        defaults.dom.body.content[0].content[0].content[1].content[0].content[1].content[1].text = new Date().format__(that.date_pattern ? that.date_pattern : "yyyy-MM-dd HH:mm:ss");
        if(language && language === 'en'){
            defaults.dom.head.content[1].text = defaults.language.en.head_title;
            defaults.dom.body.content[0].content[0].content[1].content[0].content[0].content[0].text = defaults.language.en.sys_Name;
            defaults.dom.body.content[0].content[0].content[1].content[1].text = defaults.language.en.welcome;
            defaults.dom.body.content[0].content[0].content[0].content[0].property.alt = defaults.language.en.sys_img_alt;
            defaults.dom.footer.content[0].content[0].content[0].property.placeholder = defaults.language.en.sender_placeholder;
            defaults.dom.footer.content[0].content[0].content[1].content[0].text = defaults.language.en.sender_text;
        }
        defaults.dom.footer.id = 'chat_footer_' + that.dateValue;
        defaults.dom.footer.content[0].content[0].content[0].id = 'chat_msg_input_' + that.dateValue;
        defaults.dom.footer.content[0].content[0].content[1].content[0].id = 'chat_send_btn_' + that.dateValue;
        return defaults;
    }



    Dom.prototype.DEFAULTS = {
        class:'',
        property:[],
        style:'',
        content:[],
        text:'dfdf',
        events:{
            onclick:function(){

            },
            ondblclick:function(){

            },
            onchange:function(){

            },
            onselect:function(){

            }
        }
    }

    var __isEmpty = function(e){
        var obj;
        for(obj in e){
            return false;
        }
        return true;
    }

    var getDom = function(data){
        var dom = $(data.item) ;
        data.id ? dom.attr("id", data.id) : undefined;
        data.class ? dom.addClass(data.class) : undefined;
        data.style ? dom.attr("style",data.style) : undefined;
        data.text ? dom.text(data.text) : undefined;
        var property = data.property;
        var exProperty = data.exProperty;
        if(property){
            var i;
            for(i in property){
                dom.attr(i,property[i]);
            }
        }
        if(exProperty){
            var j;
            for(j in exProperty){
                dom.attr(j.replace(/_/g,'-'),exProperty[j]);
            }
        }
        if(!__isEmpty(data.content)){
            $.each(data.content, function(i, obj){
                dom.append(getDom(obj));
            })
        }
        return dom;
    }

//    BootstrapChat.prototype.getDefault = function(){
//        return getDefaults(this);
//    }

//    var btchat = new BootstrapChat();
//
//    console.log(btchat.getDefault());

    BootstrapChat.prototype.init = function(options, language){
        this.$jq_ = $('<div>');
        options = $.extend(true,{},getDefaults(this, language), options);
        var head = options.dom.head;
        var body = options.dom.body;
        var footer = options.dom.footer;
        this.$jq_.attr("class",options.dom.class);
        var $head =getDom(head);
        var $body = getDom(body);
        var $footer = getDom(footer);
        return this.$jq_.append($head).append($body).append($footer);
    }

    /**
     * 初始化一个聊天窗口
     * @param options
     */
    BootstrapChat.prototype._init = function(options){
        options = $.extend(true,{}, this.DEFAULTS, options);
        this.$jq_.attr('class','');
        this.$jq_.addClass(options.bsClass).addClass(options.class);
        //head
        var head = $('<div>').addClass(options.head.class).append($('<i>').addClass(options.head.iconCls)).append($('<span>').text(options.head.text));
        if(options.head && options.head.style){
            head.attr('style',options.head.style);
        }
        this.$jq_.append(head);
        //body
        var body = $('<div>').addClass(options.body.class);
        if(options.body && options.body.style){
            body.attr("style", options.body.style);
        }
        var ul = $('<ul>').addClass(options.ul.class)
        ul.appendTo(body);
        this.$jq_.append(body);
        //footer
        var footer = $('<div>').addClass('panel-footer');
        var form = $('<form>');
        var input_group = $('<div>').addClass('input-group')
                .append($('<input>').addClass('form-control input-sm').attr('type','text').attr('name',this.DEFAULTS.app_data._comments).attr('placeholder',this.DEFAULTS.sender.placeholder))
                .append($('<span class="input-group-btn">'))
                .append($('<button role="button" class="btn btn-warning btn-sm" app-action="caiwu.baoxiaoCheck.sendMessager">发送</button>'));
        this.$jq_.append(input_group);
        this.$jq.after(this.$jq_);
        this.$jq.remove();
        this.$jq_.data("bootstrap.chat",this.$jq_);
    };

    BootstrapChat.prototype._addMessages = function(msg, ifself){
        if(isEmpty(msg)){
            return false;
        }
        var li = $('<li>').addClass(ifself ? 'left clearfix':'right clearfix');
        li.append($('<span>').append($('<img>').attr('src',msg.img.src).attr('alt',msg.img.alt).addClass(msg.img.class)).addClass(ifself ? 'chat-img pull-left':'chat-img pull-right'));
        li.append($('<div>').addClass('chat-body clearfix').append(
                $('<div>').addClass('header').append($('<strong>').addClass(ifself ? 'primary-font':'pull-right primary-font').text(msg.sender)).
                        append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append($('<span>').text(msg.date)))
        ).append($('<p>').text(msg.msg)))
        try{
            li.appendTo(this.$jq_);
            this.$jq_.find(".panel-body").scrollTop(this.$jq_.find(".panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    }

    BootstrapChat.prototype._addSysMessages = function(msg){
        if(msg.isEmpty()){
            return false;
        }
        var li = $('<li>').addClass( 'left clearfix');
        li.append($('<div>').addClass('chat-body clearfix').append(
                $('<div>').addClass('header').append($('<strong>').addClass( 'primary-font').html('<span style="color:red">[系统消息]:</span>')).
                        append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append($('<span>').text(msg.date)))
        ).append($('<p>').text(msg.msg)))
        try{
            li.appendTo(this.$jq_);
            this.$jq_.find(".panel-body").scrollTop(this.$jq_.find(".panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    }

    BootstrapChat.prototype._sendMessages = function(msg){
        if(!msg.id && !msg.msg_type) return false;
        this._addMessages(msg,true);
        delete msg.img;
        this.websocket.send(JSON.stringify(msg));
    }

    /**
     * 发送消息
     * @private
     */
    BootstrapChat.prototype._sendMessages = function(){

    }

    $.fn.bootstrapChat = function(options){
        var value;
        this.each(function(){
            var $this = $(this),
                    chat = $this.data('bootstrap.chat') ? $this.data('bootstrap.chat') : new BootstrapTable(this, options),
                    options = $.extend({}, BootstrapChat.DEFAULTS, $this.add(),
                            typeof options === 'object' && options);
            if(typeof options === 'string'){
                if(chat[options] && typeof chat[options] === 'function'){
                    value = chat[options].apply(chat);
                }else{
                    throw new Error("Unknow method: " + options);
                }
            }
        })
    }

}($))