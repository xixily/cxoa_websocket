/**
 * Created by dengxf on 2017/3/23.
 */
window.onload = function() {
    $.get('basepath.jsp',{},function(data){
        if(typeof data === "string"){
            try{
                data = eval("("+data+")");
                var chat = new Chat({selector:'.chat-panel',url:data.basePath});
                chat.init();
            }catch(e){
                $.messager.alert("获取基础地址失败：o%", e);
            }
        }
    })
};
var Chat = function(options) {
    this.websocket = null;
    this.selector = options.selector || '.chat-panel';
    this.url = options.url || "";
};
var Messages = {
    sender:'system',
    sid: 101,
    to: 100,
    msg: 'hello',
    Date: '2017-03-23 16:09:23',
    msg_type: 100,
}
Chat.prototype = {
    init: function() {
        var that = this;
        //ws://localhost:8080/websocket//ws
        if ('WebSocket' in window) {
            this.websocket = new WebSocket("ws://" + this.url + "/ws?uid="+ uid);
        } else if ('MozWebSocket' in window) {
            this.websocket = new MozWebSocket("ws://" + this.url + "/ws"+ uid);
        } else {
            this.websocket = new SockJS("http://" + this.url + "/ws/sockjs"+ uid);
        }
        //打开一个链接
        this.websocket.onopen = function(event) {
            console.log("WebSocket:已连接");
            console.log(event);
        };
        //消息接收
        this.websocket.onmessage = function(event) {
            var data=JSON.parse(event.data);
//            data.id = data.from;
//            data.msg = data.text;
//            data.sender = data.fromName;
            data.img = that._defaults.sender;
            if(data.msg_type == 110){
                that.addSysMessages(data);
            }else{
                that.addMessages(data, false);
            }
        };
        //发生错误
        this.websocket.onerror = function(event) {
            var data = {};
            data.img = this._defaults.system;
            data.msg = "WebSocket:发生错误 ";
            that.addSysMessages(data);
        };
        //关闭事件
        this.websocket.onclose = function(event) {
            var data = {};
            data.img = this._defaults.system;
            data.msg = "WebSocket:已关闭";
            that.addSysMessages(data);
        }
    },
    _initialEmoji: function() {
        var emojiContainer = document.getElementById('emojiWrapper'),
                docFragment = document.createDocumentFragment();
        for (var i = 69; i > 0; i--) {
            var emojiItem = document.createElement('img');
            emojiItem.src = '../content/emoji/' + i + '.gif';
            emojiItem.title = i;
            docFragment.appendChild(emojiItem);
        };
        emojiContainer.appendChild(docFragment);
    },
    _displayNewMsg: function(user, msg, color) {
        var container = document.getElementById('historyMsg'),
                msgToDisplay = document.createElement('p'),
                date = new Date().toTimeString().substr(0, 8),
        //determine whether the msg contains emoji
                msg = this._showEmoji(msg);
        msgToDisplay.style.color = color || '#000';
        msgToDisplay.innerHTML = user + '<span class="timespan">(' + date + '): </span>' + msg;
        container.appendChild(msgToDisplay);
        container.scrollTop = container.scrollHeight;
    },
    _displayImage: function(user, imgData, color) {
        var container = document.getElementById('historyMsg'),
                msgToDisplay = document.createElement('p'),
                date = new Date().toTimeString().substr(0, 8);
        msgToDisplay.style.color = color || '#000';
        msgToDisplay.innerHTML = user + '<span class="timespan">(' + date + '): </span> <br/>' + '<a href="' + imgData + '" target="_blank"><img src="' + imgData + '"/></a>';
        container.appendChild(msgToDisplay);
        container.scrollTop = container.scrollHeight;
    },
    _showEmoji: function(msg) {
        var match, result = msg,
                reg = /\[emoji:\d+\]/g,
                emojiIndex,
                totalEmojiNum = document.getElementById('emojiWrapper').children.length;
        while (match = reg.exec(msg)) {
            emojiIndex = match[0].slice(7, -1);
            if (emojiIndex > totalEmojiNum) {
                result = result.replace(match[0], '[X]');
            } else {
                result = result.replace(match[0], '<img class="emoji" src="../content/emoji/' + emojiIndex + '.gif" />');//todo:fix this in chrome it will cause a new request for the image
            };
        };
        return result;
    },
    addMessages: function(msg, ifself){
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
            li.appendTo($(this.selector+" .panel-body>ul.chat"));
            $(this.selector+" .panel-body").scrollTop($(this.selector+" .panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    },
    addSysMessages: function(msg){
        if(msg.isEmpty()){
            return false;
        }
        var li = $('<li>').addClass( 'left clearfix');
        li.append($('<div>').addClass('chat-body clearfix').append(
                $('<div>').addClass('header').append($('<strong>').addClass( 'primary-font').html('<span style="color:red">[系统消息]:</span>')).
                        append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append($('<span>').text(msg.date)))
        ).append($('<p>').text(msg.msg)))
        try{
            li.appendTo($(this.selector+" .panel-body>ul.chat"));
            $(this.selector+".panel-body").scrollTop($(this.selector+".panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    },
    sendMessages: function(msg){
        if(!msg.id && !msg.msg_type) return false;
        this.addMessages(msg,true);
        delete msg.img;
        this.websocket.send(JSON.stringify(msg));
    },
    _defaults:{
        me: {
            src: 'app/images/blue_60.png',
            alt: '申请人',
            class: 'img-circle'
        },
        sender: {
            src: 'app/images/red_60.png',
            alt: '发信人',
            class: 'img-circle'
        },
        checker: {
            src: 'app/images/red_60.png',
            alt: '发信人',
            class: 'img-circle'
        },
        system: {
            src: 'app/images/red_60.png',
            alt: '系统消息',
            class: 'img-circle'
        }
    }
};
