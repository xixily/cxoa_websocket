/**
 * Created by dengxf on 2016/12/5.
 */
var caiwu = {
    baoxiao: {
        pageData:{
            url: 'caiwu/baoxiaoManager',
            action: 'public/caiwu/queryBaoxiao.action',
            source: app.getBaoxiao(),
            status: [2, 6, 8],
            infoSelector: '#bx_info',
            listSelector: '#bx_list',
            btnSelector: '#bx_msg_btn',
            media_li_selector: '#bx_media_li',
            app_back: 'cw_baoxiaoManager',
            appendSelector:'#cw_bx_append'
        },
        init: function () {
            $(document).on("click",'#bx_media_li a.media-body',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.baoxiao.showInfo($(this));
            })
        },
        find: function(data){
            caiwu.find(data, $.extend({}, caiwu.baoxiao.pageData));
        },
        findYbx: function(data){
            src = src ? (typeof src ==='string' ? src:'caiwu/baoxiaoManager'):'caiwu/baoxiaoManager';
            data = data ? data : {};
            if (data.value && data.type) {
                data[data.type] = data.value;
            }
            var url = 'public/caiwu/queryYbx.action';
            url = 'public/caiwu/queryBaoxiao.action';//TODO 冗余
            getBufferView(src, function (view) {
                var body = $($(view).find("#" + app.getYiPiZhun() + "body")[0]);
                var li = $(body.find('ul.media-list li')[0]);
                li.remove();
                if (!bodyAppend(body, app.getBaoxiao())) {
                    $.messager.alert("提示：", "添加body错误...");
                }
                ;
                body = $("#" + app.getYiPiZhun() + "body");
                $.extend(data, app.getPageDefault());
                body.data(app.getQueryParams(), data);//设置查询参数。
                $.post(url, data, function (result) {
//                    try{
//                        result = eval("(" + result + ")");
//                    }catch(e){
//                        try{
//                            result = $(result);
//                            var msg = result.find('span').html();
//                            var dialogInfo = appDialog.getDefaults();
//                            dialogInfo.content = msg;
//                            dialogInfo.buttons[0].text = '确认';
//                            dialogInfo.buttons[0].handler = function(event){
//                                location.replace('/cxoa/app_index.html');
//                            }
//                            $.appDialog.createDialog(dialogInfo).modal('show');
//                            return false;
//                        }catch(e){
//                            result = result;
////                            location.replace('/cxoa/app_index.html');
////                            return false;
//                        }
//                    }
                    if (result.success) {
                        body.data(app.getDivData(), result);//把数据和div关联
                        var rows = result.rows;
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="total"]').html(result.total);
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                        if (rows && rows.length > 0) {
                            for (var i = 0; i < rows.length; i++) {
                                var row = rows[i];
                                var list = li.clone(true);
                                if (row.status && row.status != "") {
                                    list.find('[app-img]').attr('src', imgPath + caiwu.status[row.status]);
                                }
                                for (var a in row) {
                                    list.find('[app-data="' + a + '"]').html(row[a]);
                                }
                                list.appendTo($("#ypz_media_li"));
                            }
                        }
                    } else {
                        var data = appDialog.getDefaults();
                        data.content = '获取页面信息失败~！';
                        var dialog = $.appDialog.createDialog(appDialog.getDefaults(), function (event) {
//                            console.log('保存按钮：' + event);
                        });
                        dialog.modal('show');
                    }
                })
            })
        },
        append: function(src){
            caiwu.append($.extend({},caiwu.baoxiao.pageData));
        },
        showInfo: function(dom){
//            var pageData = $.extend({},caiwu.baoxiao.pageData);
            caiwu.showInfo(dom, $.extend({},caiwu.baoxiao.pageData));
        },
        disable: function(){
            var user = session.user;
            if(false){
                $('#cw_bx_user').attr('disabled','');
            }
            if(true){
                $('#cw_bx_approver').attr('disabled','');
            }
            if(true){
                $('#cw_bx_cn').attr('disabled','');
            }
        },
        validator2: function(dom){
            if(dom){
                dom.bootstrapValidator({
                    container: 'tooltip',
                    message: '您输入的值无效。',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        number:{
                            validators: {
                                notEmpty: {
                                    message: '报销张数不能为空。'
                                }
                            }
                        },
                        email: {
                            validators: {
                                notEmpty: {
                                    message: '邮箱不能为空。'
                                },
                                emailAddress: {
                                    message: '无效的Email地址'
                                }
                            }
                        },
                        username: {
                            validators: {
                                notEmpty: {
                                    message: '报销人不能为空。'
                                }
                            }
                        },
                        money: {
                            validators: {
                                notEmpty: {
                                    message: '报销金额不能为空。'
                                }
                            }
                        },
                        bank: {
                            validators: {
                                notEmpty: {
                                    message: '银行名称不能为空。'
                                }
                            }
                        },
                        account: {
                            validators: {
                                notEmpty: {
                                    message: '银行账号不能为空。'
                                },
                                digits: {
                                    message: '银行卡只能为纯数字。'
                                },
                                regexp: {
                                    regexp: /^(\d{16}|\d{19})$/,
                                    message: '银行卡的长度为16或者19位数字组成，请检查。'
                                },
                            }
                        },
                    }
                })
            }
        },
        submitApprove: function(){
            var bootstrapValidator = $("#add_form").data('bootstrapValidator');
            if(!bootstrapValidator){
                caiwu.validator($("#add_form"));
                bootstrapValidator = $("#add_form").data('bootstrapValidator');
            }
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()){
               var data =  $('#add_form').serializeJson();
                data.account.replace(/\ +/g,"");
                $.post("public/caiwu/addBaoxiao.action",data,function(result){
//                    result = eval("("+ result +")");
                    if(result.success){
                        $('#bx_apply_dialog').modal('toggle')
                        caiwu.listRefresh();
                    }
                    $.messager.alert("添加提示：",result.msg);
                })
            }
        },
        updateInfo: function(data){
            $('#bx_msg_info').attr('disabled','true');
            data = data ? data : {};
//            $.messager.alert("提示：","提交申请成功~！");
            if(!data.id || data.id == ''){
                $.messager.alert("提示：", "批次号不存在，请刷新页面后重试。")
            }
            $.post("public/caiwu/updateSelfBaoxiao.action",data,function(result){
//                if(result.success){
//
//                }
                $.messager.alert("提示：", result.msg);
            })
        }
    },
    baoxiaoAppro:{
        pageData:{
            url: 'caiwu/baoxiaoApprove',
            action: 'public/caiwu/queryDaishenpi.action',
            source: app.getBaoxiao(),
            status: [1],
            infoSelector: '#bx_info',
            listSelector: '#bx_list',
            btnSelector: '#bx_msg_btn',
            media_li_selector: '#bx_media_li',
            app_back: 'cw_baoxiaoManager',
            appendSelector:'#cw_bx_append'
        },
        pageData_ypz:{
            url: 'caiwu/baoxiaoApprove',
            action: 'public/caiwu/queryYipizhun.action',
            source: app.getYiPiZhun(),
            infoSelector: '#ypz_info',
            listSelector: '#ypz_list',
            media_li_selector: '#ypz_media_li',
            app_back: 'ypz_baoxiaoManager',
            appendSelector:'#cw_ypz_append'
        },
        init: function () {
            $(document).on("click",'#bx_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.baoxiaoAppro.showInfo($(this));
            })
//            $.post('public/caiwu/getAllCells.action',{}, function(result){
//                result = eval("(" + result + ")");
//                if(result.success){
//
//                }
//            });
        },
        ypzInit: function(){
            $('#ypz_find_btn_').trigger('click');
            $(document).on("click",'#ypz_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.baoxiaoAppro.showYpzInfo($(this));
            })
        },
        showInfo: function(dom){
            var pageData = $.extend({},caiwu.baoxiaoAppro.pageData);
            caiwu.showInfo(dom, pageData);
        },
        showYpzInfo: function(dom){
            var pageData = $.extend({},caiwu.baoxiaoAppro.pageData_ypz);
            caiwu.showInfo(dom, pageData);
//            if(dom){
////                console.log(dom);
//                var id = dom.find('[app-data="id"]').html();
//                var data = $("#" + app.getYiPiZhun() + "body").data(app.getDivData());
//                var formData = caiwu.getDataById(id,data);
//                if(formData){
//                    $('#ypz_info form').form('clear');
//                    $('#ypz_info form').form('setForm',formData);
//                }
//            }
//            /**显示下一层申请单*/
//            if($('#ypz_list')[0].className == ""){
//                $('#ypz_list').addClass('col-lg-12');
//                $('#ypz_list').css('height','auto');
//                $('#ypz_info').css('height','0')
//            }else{
//                $('#ypz_list').removeClass('col-lg-12');
//                $('#ypz_list').css('height','0')
//                $('#ypz_info').css('height','auto')
//            }
//            session.app_back = 'ypz_baoxiaoManager';
        },
        find:function(data){
            caiwu.find(data, $.extend({}, caiwu.baoxiaoAppro.pageData));
//            caiwu.baoxiao.find(data,'caiwu/baoxiaoApprove')
        },
        findYbx: function(data){
            caiwu.find(data, $.extend({}, caiwu.baoxiaoAppro.pageData_ypz))
        },
        findYbx2: function(data){
            var src = 'caiwu/baoxiaoApprove';
            data = data ? data : {};
            if (data.value && data.type) {
                data[data.type] = data.value;
            }
            var url = 'public/caiwu/queryYbx.action';
            url = 'public/caiwu/queryBaoxiao.action';//TODO 冗余
            getBufferView(src, function (view) {
                var body = $($(view).find("#" + app.getYiPiZhun() + "body")[0]);
                var li = $(body.find('ul.media-list li')[0]);
                li.remove();
                if (!bodyAppend(body, app.getYiPiZhun())) {
                    $.messager.alert("提示：", "添加body错误...");
                }
                ;
                body = $("#" + app.getYiPiZhun() + "body");
                $.extend(data, app.getPageDefault());
                body.data(app.getQueryParams(), data);//设置查询参数。
                $.post(url, data, function (result) {
//                    try{
//                        result = eval("(" + result + ")");
//                    }catch(e){
//                        try{
//                            result = $(result);
//                            var msg = result.find('span').html();
//                            var dialogInfo = appDialog.getDefaults();
//                            dialogInfo.content = msg;
//                            dialogInfo.buttons[0].text = '确认';
//                            dialogInfo.buttons[0].handler = function(event){
//                                location.replace('/cxoa/app_index.html');
//                            }
//                            $.appDialog.createDialog(dialogInfo).modal('show');
//                            return false;
//                        }catch(e){
//                            location.replace('/cxoa/app_index.html');
//                            return false;
//                        }
//                    }
                    if (result.success) {
                        body.data(app.getDivData(), result);//把数据和div关联
                        var rows = result.rows;
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="total"]').html(result.total);
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                        $('#' + app.getYiPiZhun() + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                        if (rows && rows.length > 0) {
                            for (var i = 0; i < rows.length; i++) {
                                var row = rows[i];
                                var list = li.clone(true);
                                if (row.status && row.status != "") {
                                    list.find('[app-img]').attr('src', imgPath + caiwu.status[row.status]);
                                }
                                for (var a in row) {
                                    list.find('[app-data="' + a + '"]').html(row[a]);
                                }
                                list.appendTo($("#ypz_media_li"));
                            }
                        }
                    } else {
                        var data = appDialog.getDefaults();
                        data.content = '获取页面信息失败~！';
                        var dialog = $.appDialog.createDialog(appDialog.getDefaults(), function (event) {
//                            console.log('保存按钮：' + event);
                        });
                        dialog.modal('show');
                    }
                })

            })
        },
        append:function(){
            caiwu.append(caiwu.baoxiaoAppro.pageData);
//            caiwu.baoxiao.append('caiwu/baoxiaoApprove');
        },
        append_ypz:function(){
            caiwu.append(caiwu.baoxiaoAppro.pageData_ypz);
//            caiwu.baoxiao.append('caiwu/baoxiaoApprove');
        },
//        approve: function(event, agree){
//            event.preventDefault();//关闭默认事件
//            event.stopImmediatePropagation();
//
//            var src = $(event.currentTarget);
//            var id = src.parent().parent().find('[app-data="id"]').html();
//            var data = {};
//            if(id && id!=0){
//                data.id = id;
//                data.agree = agree ? true : false;
//               caiwu.baoxiaoAppro.updateInfo(data);
//            }
//        },
        agree: function(event){
            var data = {};
            data.agree = true;
            if(!event){
                $('#bx_msg_info').attr('disabled','true');
                data.id = $('#bx_info').find('#_id').val();
            }else{
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                var src = $(event.currentTarget);
                data.id = src.parent().parent().find('[app-data="id"]').html();
            }
            $('#agree_form').form('clear');
            $('#agree_form').form('setForm',data);
            $('#bx_appro_true').removeClass('hide');
            $('#bx_appro_false').addClass('hide');
            $('#bx_appro_dialog').modal('toggle');
        },
        disAgree: function(event){
            var data = {};
            data.agree = false;
            if(!event){
                $('#bx_msg_info').attr('disabled','true');
                data.id = $('#bx_info').find('#_id').val();
            }else{
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                var src = $(event.currentTarget);
                data.id = src.parent().parent().find('[app-data="id"]').html();
            }
            $('#agree_form').form('clear');
            $('#agree_form').form('setForm',data);
            $('#bx_appro_false').removeClass('hide');
            $('#bx_appro_true').addClass('hide');
            $('#bx_appro_dialog').modal('toggle');
        },
        submitAgree : function(){
            var data =  $('#agree_form').serializeJson();
            if(!data.id || !data.checked) $.messager.alert('提示','请刷新页面后再重试。');
            $('#bx_appro_dialog').modal('toggle');
            caiwu.baoxiaoAppro.updateInfo(data);
        },
        updateInfo: function(data){
            data = data ? data : {};
            if(!data.id || !data.checked) $.messager.alert('提示','请刷新页面后再重试。');
            $.post('public/caiwu/approveBaoxiao.action', data, function(result){
                if(result.success){
                    caiwu.listRefresh('caiwu.baoxiaoAppro.find');
                }
                $.messager.alert("批准提示:", result.msg);
            })
        }
    },
    baoxiaoCheck: {
        pageData:{
            url: 'caiwu/baoxiaoShenhe',
            action: 'public/caiwu/queryDaiShenhe.action',
            source: app.getBaoxiao(),
            status: [1],
            infoSelector: '#bx_info',
            listSelector: '#bx_list',
            btnSelector: '#bx_msg_btn',
            media_li_selector: '#bx_media_li',
            app_back: 'cw_baoxiaoManager',
            appendSelector:'#cw_bx_append'
        },
        pageData_dsp:{
            url: 'caiwu/baoxiaoShenhe',
            action: 'public/caiwu/queryDaishoupiao.action',
            source: app.getDaiShouPiao(),
//            status: [1],
            infoSelector: '#dsp_info',
            listSelector: '#dsp_list',
//            btnSelector: '#bx_msg_btn',
            media_li_selector: '#dsp_media_li',
            app_back: 'dsp_baoxiaoManager',
            appendSelector:'#cw_dsp_append'
        },
        pageData_dcp:{
            url: 'caiwu/baoxiaoShenhe',
            action: 'public/caiwu/queryDaiChupiao.action',
            source: app.getDaiChuPiao(),
//            status: [1],
            infoSelector: '#dcp_info',
            listSelector: '#dcp_list',
//            btnSelector: '#bx_msg_btn',
            media_li_selector: '#dcp_media_li',
            app_back: 'dcp_baoxiaoManager',
            appendSelector:'#cw_dcp_append'
        },
        pageData_dhk:{
            url: 'caiwu/baoxiaoShenhe',
            action: 'public/caiwu/queryDaihuikuan.action',
            source: app.getDaiHuiKuan(),
//            status: [1],
            infoSelector: '#dhk_info',
            listSelector: '#dhk_list',
//            btnSelector: '#bx_msg_btn',
            media_li_selector: '#dhk_media_li',
            app_back: 'dhk_baoxiaoManager',
            appendSelector:'#cw_dhk_append'
        },
        pageData_yhk:{
            url: 'caiwu/baoxiaoShenhe',
            action: 'public/caiwu/queryYihuikuan.action',
            source: app.getYiHuiKuan(),
            infoSelector: '#yhk_info',
            listSelector: '#yhk_list',
            media_li_selector: '#yhk_media_li',
            app_back: 'yhk_baoxiaoManager',
            appendSelector:'#cw_yhk_append'
        },
        init: function () {
            $(document).on("click",'#bx_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.showInfo($(this), $.extend({},caiwu.baoxiaoCheck.pageData));
            })
        },
        init_dsp: function(){
            $('#dsp_find_btn_').trigger('click');
            $(document).on("click",'#dsp_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.showInfo($(this), $.extend({},caiwu.baoxiaoCheck.pageData_dsp));
            })
        },
        init_dcp: function(){
            $('#dcp_find_btn_').trigger('click');
            $(document).on("click",'#dcp_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.showInfo($(this), $.extend({},caiwu.baoxiaoCheck.pageData_dcp));
            })
        },
        init_dhk: function(){
            $('#dhk_find_btn_').trigger('click');
            $(document).on("click",'#dhk_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.showInfo($(this), $.extend({},caiwu.baoxiaoCheck.pageData_dhk));
            })
        },
        init_yhk: function(){
            $('#yhk_find_btn_').trigger('click');
            $(document).on("click",'#yhk_media_li .media-body>a',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.showInfo($(this), $.extend({},caiwu.baoxiaoCheck.pageData_yhk));
            })
        },
        find:function(data){
            caiwu.find(data, $.extend({},caiwu.baoxiaoCheck.pageData));
        },
        find_dsp:function(data){
            caiwu.find(data, $.extend({},caiwu.baoxiaoCheck.pageData_dsp));
        },
        find_dcp:function(data){
            caiwu.find(data, $.extend({},caiwu.baoxiaoCheck.pageData_dcp));
        },
        find_dhk:function(data){
            caiwu.find(data, $.extend({},caiwu.baoxiaoCheck.pageData_dhk));
        },
        find_yhk:function(data){
            caiwu.find(data, $.extend({},caiwu.baoxiaoCheck.pageData_yhk));
        },
        append:function(){
            caiwu.append(caiwu.baoxiaoCheck.pageData);
        },
        append_dsp:function(){
            caiwu.append(caiwu.baoxiaoCheck.pageData_dsp);
        },
        append_dcp:function(){
            caiwu.append(caiwu.baoxiaoCheck.pageData_dcp);
        },
        append_dhk:function(){
            caiwu.append(caiwu.baoxiaoCheck.pageData_dhk);
        },
        append_yhk:function(){
            caiwu.append(caiwu.baoxiaoCheck.pageData_yhk);
        },
        sh_disagree: function(data, src){
          caiwu.baoxiaoCheck.sh_agree(data, src, false)
        },
        sh_agree: function(data, src, ag){
            var agree = true;
            if(ag !== undefined){
                agree = ag;
            }
            $.messager.confirm('提示：','您确定要通过该条审核记录吗？',function(){
                $.post("public/caiwu/checkBaoxiao.action",{id:Number(data.id),agree:agree},function(result){
                    if(result.success){
                        $('#cw_check_btn').toggleClass("hide");
//                        $('[app-action="caiwu.baoxiaoCheck.find"]').trigger('click');
                    }
                    $.messager.alert("提示：",result.msg);
                })
            })
        },
        sendMessager: function(data){
//            event.preventDefault();
//            event.stopImmediatePropagation();
            var msg = {
                img:{
                    alt:'邓雪锋'
                },
                username:'邓雪锋',
                time: caiwu.getNowFormatDate()
            };
            msg.comments = data.comments;
            if(msg.comments && msg.comments!=''){
                $('#btn_msg_input').val('');
//                session.chat_message = msg.comments;
//            $.post('',{}, function(result){
//                result = eval("(" + ")");
//                if(result.success){
                caiwu.baoxiaoCheck.addMessager(msg, true);
                $('#sh_chat_panel').scrollTop( $('#sh_chat_panel')[0].scrollHeight );
//                }
//            })
            }
        },
        addMessager: function(msg, ifself){
            msg = msg ? msg : {};
//            ifself = ifself ? 'left clearfix':'right clearfix';
            var defaults = {
                img:{
                    src: 'app/images/blue_60.png',
                    alt: '申请人',
                    class: 'img-circle'
                },
                username: '申请人',
                time: '',
                comments: ''
            }
            msg = $.extend(true,{},defaults,msg);
            var li = $('<li>').addClass(ifself ? 'left clearfix':'right clearfix');
            li.append($('<span>').append($('<img>').attr('src',msg.img.src).attr('alt',msg.img.alt).addClass(msg.img.class)).addClass(ifself ? 'chat-img pull-left':'chat-img pull-right'));
            li.append($('<div>').addClass('chat-body clearfix').append(
                    $('<div>').addClass('header').append($('<strong>').addClass(ifself ? 'primary-font':'pull-right primary-font').html(msg.username)).
                            append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append(msg.time))
            ).append($('<p>').html(msg.comments)))
            li.appendTo($('#sh_chat_lis'));
            return li ;
        },
        openCheck: function(event, checked, flag){
            event.preventDefault();//关闭默认事件
            event.stopImmediatePropagation();
            var src = $(event.currentTarget);
            var id = src.parent().parent().find('[app-data="id"]').html();
            var checked = checked ? true : false;
            var surl = '';
            if(!flag || flage === 0){
                surl= 'sh'
            }else if(flag === 1){
                sulr = 'dsp'
            }else if(flag===2){
                surl = 'dcp'
            }else if(flag===3){
                surl = 'dhk'
            }
            $('#sh_shenhe_form').form('clear');

        },
        openCheck: function(event, checked){
            event.preventDefault();//关闭默认事件
            event.stopImmediatePropagation();
            var src = $(event.currentTarget);
            var id = src.parent().parent().find('[app-data="id"]').html();
            checked = checked ? true : false;
//            var formData = caiwu.getDataById(id);
//            $('#bx_shenhe_form').form('setForm',formData);
            $('#sh_shenhe_form').form('clear');
            $('#sh_checked').val(checked);
            if(checked){
                $('#sh_checked_true').removeClass('hide');
                $('#sh_checked_false').addClass('hide');
            }else{
                $('#sh_checked_true').addClass('hide');
                $('#sh_checked_false').removeClass('hide');
            }
            $('#bx_sh_dialog').modal('toggle');

        },
        openShouPiao: function(event, checked){
//            event.preventDefault();//关闭默认事件
            event.stopImmediatePropagation();
            var src = $(event.currentTarget);
            var id = src.parent().parent().find('[app-data="id"]').html();
            checked = checked ? true : false;
            var data = {};
            data.id = id;
            data.agree = checked;
            $('#bx_dsp_form').form('clear');
            $('#bx_dsp_form').form('setForm',data);
            if(checked){
                $('#bx_dsp_true').removeClass('hide');
                $('#bx_dsp_false').addClass('hide');
            }else{
                $('#bx_dsp_true').addClass('hide');
                $('#bx_dsp_false').removeClass('hide');
            }
            $('#bx_dsp_dialog').modal('toggle');

        },
        openKoukuan: function(event, checked){
            event.preventDefault();//关闭默认事件
            event.stopImmediatePropagation();
            caiwu.baoxiaoCheck.cp_page_remove();
            var src = $(event.currentTarget);
            var id = src.closest('form').find('input[name="id"]').val();
            if(!id || id==0){
                $.messager.alert("提示：", "批次号不存在，请刷新页面后重试。");
                return false;
            }
            $('#cp_table').find('input[name="id"]').val(id);
            $.post('public/caiwu/queryKoujiekuan.action',{bxid:id},function(result){
                if(result.success){
                    var objs = result.obj;
                    var total = 0 ;
                    $.each(objs, function(i,obj){
                        caiwu.baoxiaoCheck.chupiao_add($('#cp_add_tr'),obj);
                        total += obj.money;
                    })
                    caiwu.baoxiaoCheck.cp_setkjk(total);
                    $('#bx_dcp_dialog').modal('toggle');
                }else{
                    $.messager.alert('失败提示：',result.msg);
                }
            })
        },
        cpConfirm: function(data, src){
            $.messager.confirm('提示：','您确定要通过该条审核记录吗？',function(){
                $.post("public/caiwu/baoxiaoChupiao.action", data, function(result){
                    if(result.success){
                        $('#cw_cp_btn').toggleClass("hide");
                        $('.do_action[app-action="caiwu.baoxiaoCheck.find_dcp"]').trigger('click');
                    }
                    $.messager.alert("提示：",result.msg);
                })
            })
        },
        submitCheck: function(){
            var bootstrapValidator = $("#bx_shenhe_form").data('bootstrapValidator');
            if(!bootstrapValidator){
                caiwu.validator($("#bx_shenhe_form"));
                bootstrapValidator = $("#bx_shenhe_form").data('bootstrapValidator');
            }
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()){
                var data =  $('#bx_shenhe_form').serializeJson();
                $.post("public/caiwu/recivedBaoxiao.action",data,function(result){
//                    result = eval("("+ result +")");
                    if(result.success){
                        caiwu.listRefresh('caiwu.baoxiaoAppro.find');//TODO listRefresh(target) target 需要修改
                    }
                    $.messager.alert("添加提示：",result.msg);
                })
            }
        },
        submitDaiShoupiao: function(){
            var bootstrapValidator = $("#bx_dsp_form").data('bootstrapValidator');
            if(!bootstrapValidator){
                caiwu.validator($("#bx_dsp_form"));
                bootstrapValidator = $("#bx_dsp_form").data('bootstrapValidator');
            }
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()){
                var data =  $('#bx_dsp_form').serializeJson();
                $('#bx_dsp_dialog').modal('toggle')
                $.post("public/caiwu/recivedBaoxiao.action",data,function(result){
                    if(result.success){
                        caiwu.listRefresh('caiwu.baoxiaoCheck.find_dsp');
                    }
                    $.messager.alert("添加提示：",result.msg);
                })
            }
        },
        submitDaiChoupiao: function(){
            $.messager.alert('提示：', '提交成功。')
//            var bootstrapValidator = $("#bx_dsp_form").data('bootstrapValidator');
//            if(!bootstrapValidator){
//                caiwu.validator($("#bx_dsp_form"));
//                bootstrapValidator = $("#bx_dsp_form").data('bootstrapValidator');
//            }
//            bootstrapValidator.validate();
//            if(bootstrapValidator.isValid()){
//                var data =  $('#bx_dsp_form').serializeJson();
//                $.post("public/caiwu/recivedBaoxiao.action",data,function(result){
////                    result = eval("("+ result +")");
//                    if(result.success){
//                        caiwu.listRefresh('caiwu.baoxiaoCheck.find_dsp');//TODO listRefresh(target) target 需要修改
//                    }
//                    $.messager.alert("添加提示：",result.msg);
//                })
//            }
        },
        submitDaihuikuan: function(){
            $.messager.confirm("下载提示","执行下载操作同时会直接汇款操作，之后您可以在已汇款查询这些信息，你确定要执行吗？",function(result){
                $.post('public/caiwu/baoxiaoHuikuan.action',{},function(result){
                    $.messager.alert('操作提示：',result.msg);
                    if(result.success){
                        app.downloadForm.download('public/file/daihuikuanExport.action',{});
                    }

                })
            })
        },
        chupiao_add: function(e,obj){
            var dom = e ? e.closest('tr'):$('#cp_add_tr');
            obj = obj ? obj : {item:'', money:0, description:''};
            var lastRow = Number($(dom.prev().find('span')[0]).text());
            obj.order = obj.order ? obj.order : (lastRow+1)
            var row = $('<tr class="item">' +
                    '<th scope="row"><sapn class="hide" app-data="id">' + obj.id +'</sapn><span app-data="order">' + obj.order + '</span>' +
                    '<a href="javascript:void(0)" onclick="caiwu.baoxiaoCheck.chupiao_remove($(this))"  style="margin-left: 12px;"><span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span></a>' +
                    '<a class="save" href="javascript:void(0)" onclick="caiwu.baoxiaoCheck.chupiao_save($(this))" style="margin-left: 12px;"><span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span></a></th>' +
                    '<td><a href="javascript:void (0)" data-type="text" app-data="item" data-pk="1" data-title="报销项目" class="e-text">'+ obj.item +'</a></td>' +
                    '<td><a href="javascript:void (0)" data-type="text" app-data="money" data-pk="1" data-title="金额" data-name="fp_money"  class="e-money">'+ obj.money +'</a></td>' +
                    '<td><a href="javascript:void (0)" data-type="textarea" app-data="description" data-name="sh_remarks" data-title="审核说明"  class="e-text">'+ obj.description +'</a></td>' +
                    '</tr>');
            dom.before(row);/** after before append prepend  appendTo */
            caiwu.baoxiaoCheck.chupiao_initEdit(dom.prev());
        },
        chupiao_remove: function(e){
            var row = e.closest('tr');
            var data = app.getAppData(row);
            if(data && data.id){
                $.post('public/caiwu/deleteKoujiekuan.action', data, function(result){
                    if(result.success){
                        row.remove();
                        var moneylist = $('[app-data="sh_money"]');
                        var total = 0;
                        var temp = 0;
                        $.each(moneylist,function(i,obj){
                            try{
                                temp = Number($(obj).html()) != NaN ? Number($(obj).html()): 0;
                            }catch(e){
                                temp = 0;
                            }
                            total += temp
                        })
                        $('[app-data="sh_total"]').html(total);
                    }
                    $.messager.alert(result.msg);
                })
            }else{
                row.remove();
                var moneylist = $('[app-data="sh_money"]');
                var total = 0;
                var temp = 0;
                $.each(moneylist,function(i,obj){
                    try{
                        temp = Number($(obj).html()) != NaN ? Number($(obj).html()): 0;
                    }catch(e){
                        temp = 0;
                    }
                    total += temp
                })
                $('[app-data="sh_total"]').html(total);
            }
        },
        chupiao_save: function(e){
            var row = e.closest('tr');
            var data = app.getAppData(row);
            var id = $("#cp_table").find('input[name="id"]').val();
            if(!id || id===0){
                $.messager.alert("提示：","报销批次号不存在，请刷新页面后重试。")
                return false;
            }
            data.bxid = id;
            if(data.money && data.money!=0){
                var url = (data.id && data.id != '') ?  'public/caiwu/updateKoujiekuan.action' :'public/caiwu/addKoujiekuan.action';
                $.post(url, data, function(result){
                    if(result.success){
                        row.removeClass('edit');
                        row.find('[app-data="id"]').html = result.obj;
                        caiwu.baoxiaoCheck.cp_setkjk();
                    }
                    $.messager.alert("更新提示：", result.msg);
                })
            }else{
                $.messager.alert("数据提示：","请您先输入金额");
            }
        },
        cp_remove_all: function(e){
            var data = {};
            data.bxid = $('#cp_table').find('input[name="id"]').val();
            if(data.bxid){
                $.post('public/caiwu/removeAllKjk.action',data,function(result){
                    if(result.success){
                        caiwu.baoxiaoCheck.cp_page_remove();
                    }
                    $.messager.alert("删除提示：",result.msg);
                })
            }
        },
        cp_page_remove: function(){
            var table = $('#cp_dk_table');
            try{
                table.find('tr.item').remove();
//                            $('[app-data="sh_total"]').html(0);
                caiwu.baoxiaoCheck.cp_setkjk(0);
            }catch(e){
                console.log('nothing to do .');
            }
        },
        cp_setkjk: function(){
            var moneylist =  $('#cp_dk_table [app-data="money"]');
            var total = 0;
            var temp = 0;
            $.each(moneylist,function(i,obj){
                try{
                    temp = Number($(obj).html()) != NaN ? Number($(obj).html()): 0;
                }catch(e){
                    temp = 0;
                }
                total += temp
            })
            value = total ? Number(total) : 0;
            $('[app-data="sh_total"]').html(value);
            $('#cp_kjk').val(value);
        },
        chupiao_initEdit: function(dom){
            dom ? dom : $(this);
            dom.find('a.e-text').editable({
            mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                }
            })
            dom.find('a.e-money').editable({
            mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                validate: function (value) { //字段验证
                    if(!/^-?([1-9]\d*\.\d{0,2}|[1-9]\d*|0\.[1-9]\d|0?\.0[1-9]|0)$/.test(value)){
                        return '请输入正确的金额（两位小数点内）。';
                    }
                }
            })
            dom.find('a.e-text').on('save', function(event, params){
                $(event.currentTarget).closest('tr').addClass('edit')
            });
            dom.find('a.e-money').on('save',function(event,params){
                $(event.currentTarget).closest('tr').addClass('edit')
                var moneylist = $('#cp_table').find('[app-data="money"]');
                var total = Number(params.newValue)!=NaN ? Number(params.newValue):0;
                var click_a = this;
                var temp = 0;
                $.each(moneylist,function(i,obj){
                    if(obj !== click_a){
                        try{
                            temp = Number($(obj).html()) != NaN ? Number($(obj).html()): 0;
                        }catch(e){
                            temp = 0;
                        }
                        total += temp;
                    }
                })
                $('[app-data="sh_total"]').html(total);
            })
        },
        chupiao_getList: function(){
            var table = $('table.table-bordered')[0];
            var trs = $(table).find('tr.item');
            var trList = [];
            var trInfo = {};
            var temp = undefined;
            var name = '';
            var value = '';
            $.each(trs,function(i,obj){
                temp = $(obj).find('[app-data]');
                $.each(temp,function(j,oo){
                    name = $(oo).attr('app-data');
                    value = $(oo).html();
                    if(name){
                        trInfo[name] = value;
                    }
                })
                trList.push($.extend({},trInfo));
            })
            alert(trList);
        }
    },
    status: {
        1: "1_tj_dpz.png",
        2: "2_pz_wtg.png",
        3: "2_ypz_dyj.png",
        4: "3_yyj_dsp.png",
        5: "4_ysp_dsh.png",
        6: "5_ysh_wtg.png",
        7: "5_ysh_dcp.png",
        8: "5_ysh_wtg.png",
        9: "6_ycp_dhk.png",
//        10: "7_ycp_yhk.png",
        10: "8_end.png",
        11: "8_end.png"
    },
    showInfo: function(dom, pageData){
        if(dom){
            var id = dom.find('[app-data="id"]').html();
            var data = $("#" + pageData.source + "body").data(app.getDivData());
            var formData = caiwu.getDataById(id, data);
            $(pageData.infoSelector + ' form').form('clear');
            if(formData){
                if(data && data.rows) {
                    if (data.index) {
                        var index = data.index[id];
                        formData = data.rows[index];
                    } else {
                        data.index = {};
                        $.each(data.rows, function (i, obj) {
                            data.index[obj.id] = i;
                        })
                    }
                }
                if(pageData.btnSelector){
                    var flag = false;
                    if(pageData.status){
                        $.each(pageData.status,function(i,obj){
                            if(formData.status == obj){
                                flag = true;
                                $(pageData.btnSelector).removeClass('hide');
                            }
                        })
                    }
                    if(!flag){
                        $(pageData.btnSelector).addClass('hide');
                    }
                }
                $(pageData.infoSelector + ' form').form('setForm',formData,dom);
            }
        }

        /**显示下一层申清单*/
        if($(pageData.listSelector)[0].className == ""){
            $(pageData.listSelector).addClass('col-lg-12');
            $(pageData.listSelector).css('height','auto');
            $(pageData.infoSelector).css('height','0')
        }else{
            $(pageData.listSelector).removeClass('col-lg-12');
            $(pageData.listSelector).css('height','0')
            $(pageData.infoSelector).css('height','auto')
        }

        session.app_back = pageData.app_back;
    },
    init: function (selector,ftn_bind, ftn) {
        ftn();
        $(document).on("click", selector, function(event){
            event.preventDefault();//关闭默认事件
            event.stopImmediatePropagation();
            ftn_bind($(this));
        })
    },
    find: function (data,pageData) {
        data = data ? data : {};
        if (data.value && data.type) {
            data[data.type] = data.value;
        }
        var toggle_data = $('#cw_toggle_form').serializeJson();
        if(toggle_data){
            data.uid = toggle_data.uid;
        }
        getBufferView(pageData.url, function (view) {
            var body = $($(view).find("#" + pageData.source + "body")[0]);
            var li = $(body.find('ul.media-list li')[0]);
            li.remove();
            if (!bodyAppend(body, pageData.source)) {
                $.messager.alert("提示：", "添加body错误...");
            };
            body = $("#" + pageData.source + "body");
            $.extend(data, app.getPageDefault());
            body.data(app.getQueryParams(), data);
            $.post(pageData.action, data, function (result) {
//                try{
//                    result = eval("(" + result + ")");
//                }catch(e){
//                    try{
//                        result = $(result);
//                        var msg = result.find('span').html();
//                        var dialogInfo = $.appDialog.getDefaults();
//                        dialogInfo.content = msg;
//                        dialogInfo.buttons[0].text = '确认';
//                        dialogInfo.buttons[0].handler = function(event){
//                            location.replace('/cxoa/app_index.html');
//                        }
//                        $.appDialog.createDialog(dialogInfo).modal('show');
//                        return false;
//                    }catch(e){
//                        location.replace('/cxoa/app_index.html');
//                        return false;
//                    }
//                }
                if (result.success) {
                    body.data(app.getDivData(), result);//把数据和div关联
                    var rows = result.rows;
                    $('#' + pageData.source + 'sum span[app-data="total"]').html(result.total);
                    $('#' + pageData.source + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                    $('#' + pageData.source + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                    if (rows && rows.length > 0) {
                        for (var i = 0; i < rows.length; i++) {
                            var row = rows[i];
                            var list = li.clone(true);
                            if (row.status && row.status != "") {
                                list.find('[app-img]').attr('src', imgPath + caiwu.status[row.status]);
                            }
                            for (var a in row) {
                                list.find('[app-data="' + a + '"]').html(row[a]);
                            }
                            list.appendTo($(pageData.media_li_selector));
                        }
                    }
                } else {
                    var data = $.appDialog.getDefaults();
                    data.content = '获取页面信息失败~！';
                    var dialog = $.appDialog.createDialog($.extend({},$.appDialog.getDefaults(),{content: result.msg ? result.msg : '' }), function (event) {
//                            console.log('保存按钮：' + event);
                    });
                    dialog.modal('show');
                }
            })

        })
    },
    append: function (pageData) {
        var body = $("#" + pageData.source + "body");
        var data = $("#" + pageData.source + "body").data(app.getQueryParams());
        data.page +=1;
        getBufferView(pageData.url, function (view) {
            var li = $($(view).find('ul.media-list li')[0]);
            $.post(pageData.action, data, function (result) {
                if (result.success) {
                    if(result.rows && result.rows.length<=0){
                        $("#" +  pageData.source + "body").data(app.getQueryParams()).page -= 1;
                        $(pageData.appendSelector).html("<i class=\"fa fa-drupal\"> 已经到底了~~ </i>");
                        $(pageData.appendSelector).toggleClass('disabled');
                        return false;
                    }
                    var oldRows = body.data(app.getDivData()).rows;
                    $.extend(false, body.data(app.getDivData()), result);
                    body.data(app.getDivData()).rows = $.merge(oldRows, result.rows);
                    var rows = result.rows;
                    $('#' + pageData.source + 'sum span[app-data="total"]').html(result.total);
                    $('#' + pageData.source + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                    $('#' + pageData.source + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                    if (rows && rows.length > 0) {
                        for (var i = 0; i < rows.length; i++) {
                            var row = rows[i];
                            var list = li.clone(true);
                            if (row.status && row.status != "") {
                                list.find('[app-img]').attr('src', imgPath + caiwu.status[row.status]);
                            }
                            for (var a in row) {
                                list.find('[app-data="' + a + '"]').html(row[a]);
                            }
                            list.appendTo($(pageData.media_li_selector));
                        }
                    }
                } else {
                    var data = $.appDialog.getDefaults();
                    data.content = '获取页面信息失败~！';
                    var dialog = $.appDialog.createDialog($.extend({},$.appDialog.getDefaults(),{content: result.msg ? result.msg : '' }), function (event) {
//                            console.log('保存按钮：' + event);
                    });
                    dialog.modal('show');
                }
            })
        })
    },
    validator: function(dom){
        if(dom){
            dom.bootstrapValidator({
                container: 'tooltip',
                message: '您输入的值无效。',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    number:{
                        validators: {
                            notEmpty: {
                                message: '报销张数不能为空。'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空。'
                            },
                            emailAddress: {
                                message: '无效的Email地址'
                            }
                        }
                    },
                    username: {
                        validators: {
                            notEmpty: {
                                message: '报销人不能为空。'
                            }
                        }
                    },
                    money: {
                        validators: {
                            notEmpty: {
                                message: '报销金额不能为空。'
                            }
                        }
                    },
                    bank: {
                        validators: {
                            notEmpty: {
                                message: '银行名称不能为空。'
                            }
                        }
                    },
                    account: {
                        validators: {
                            notEmpty: {
                                message: '银行账号不能为空。'
                            },
                            digits: {
                                message: '银行卡只能为纯数字。'
                            },
                            regexp: {
                                regexp: /^(\d{16}|\d{19})$/,
                                message: '银行卡的长度为16或者19位数字组成，请检查。'
                            },
                        }
                    },
                }
            })
        }
    },
    getDataById: function(id,data){
        data = data ? data : $("#" + app.getBaoxiao() + "body").data(app.getDivData());
        var formData = {};
        if(data && data.rows){
            if(data.index){
                var index = data.index[id];
                formData = data.rows[index];
            }else{
                data.index = {};
                $.each(data.rows, function(i,obj){
                    data.index[obj.id] = i;
                    if(obj.id == id){
                        formData = obj;
                    }
                })
            }
            return formData;
        }
    },
    listRefresh: function(target){
        target = target ? target : 'caiwu.baoxiao.find';
        $('[app-action="' + target + '"]').trigger('click');
    },
    getNowFormatDate: function () {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }
}