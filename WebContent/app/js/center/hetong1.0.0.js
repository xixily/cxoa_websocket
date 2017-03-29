/**
 * Created by dengxf on 2016/12/13.
 */
var hetong = {
    htManager:{
        coldefine: {
            cellCore: '细胞核',
            charger:'负责人',
            email:'邮箱',
            dname:'单位名称',
            autoCode:'自动编号',
            cemail: '细胞核邮箱',
            gemail: '指导邮箱',
            lastYear: '2015',
            thisYear: '2016',
            yingshou: '应收'
        },
        zhidao: {
            detailFormatter: function(index, row, element){
                var html = [];
                $.each(row, function (key, value) {
                    if(typeof value == "number") value += " 元";
                    if(value && ''!==value){
                        html.push('<p><b>' + (hetong.htManager.coldefine[key]?hetong.htManager.coldefine[key]:key) + ':</b> ' + value + '</p>');
                    }
                });
                return html.join('');
            },
            footerFormatter_yingshou: function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.yingshou;
                })
                return '<a>'+round2(sum,2)+'</a>';
            },
            footerFormatter_2015: function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.lastYear;
                })
                return '<a>'+round2(sum,2)+'</a>';
            },
            footerFormatter_2016:function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.thisYear;
                })
                return '<a>'+ round2(sum,2)+'</a>';
            },
            onCoreClickRow: function(row, $element, field){
                var url = 'hetong/cells';
                session.table.coreClickRow = row;
                if(url && url!=''){
                    var cellViews = $('#cellsview');
                    var table = cellViews.find('table');
                    if(table && table.length>0){
                        table.bootstrapTable('destroy');
                    }
                    $('#guidance').hide();
                    $('#cellsview').show();
                    getBufferView(url,function(view){
//                        var row = $('#ht_guidance_table').bootstrapTable('getSelections');
                        var view = $(view);
                        var cvContainer = $('#cellsview');
                        cvContainer.html("");
                        var ul = view.find('form ul');
                        var data = $('#ht_guidance_table').bootstrapTable('getData');
                        view.find('#ht_find_cells').html('<input type="hidden" name="email" value="'+ (row.email ? row.email:row.cemail) + '"/>'+ (row.username ? row.username:row.cellCore));
                        $.each(data, function(i, obj){
                            var email = obj.email ? obj.email:obj.cemail;
                            var username = obj.username ? obj.username:obj.cellCore;
                            var li = $('<li><a><span class="sr-only">'+ email +'</span>' + username + '</a>');
                            li.appendTo(ul);
//                            if(0 === i){
//                                view.find('#ht_find_cells').html('<input type="hidden" name="email" value="'+ email + '"/>'+ username);
//                            }
                        })
                        view.appendTo(cvContainer);
                        $('#ht_cellsView_table').bootstrapTable({
                            url:'public/hetong/getCoreCells.action',
                            queryParams: function(params){
//                                if(!params.email){
                                    params.email = row.cemail;
//                                }
                                return params;
                            },
                            onClickRow: hetong.htManager.zhidao.onCellsClickRow
                        });
                    })
                }
            },
            onCellsClickRow: function(row, $element, field){
                var url = 'hetong/users';
                if(url && url!=''){
                    var cellViews = $('#user_list_view');
                    cellViews.html("");
                    $('#user_list_view').show();
                    $('#cellsview').hide();
                    getBufferView(url,function(view){
                        var view = $(view);
                        var cvContainer = $('#user_list_view');
                        var ul = view.find('form ul');
                        var data = $('#ht_cellsView_table').bootstrapTable('getData');
                        view.find('#ht_find_ulist').html('<input type="hidden" name="email" value="'+ row.email + '"/>'+ row.charger);
                        $.each(data, function(i, obj){
                            var email = obj.email;
                            var username = obj.charger;
                            var li = $('<li><a><span class="sr-only">'+ email +'</span>' + username + '</a>');
                            li.appendTo(ul);
                        })
                        view.appendTo(cvContainer);
                        $('#ht_ulist_table').bootstrapTable({
                            url:'public/hetong/getUserList.action',
                            queryParams: function(params){
//                                if(!params.email){
                                params.email = row.email;
//                                }
                                return params;
                            },
                            onClickRow: hetong.htManager.zhidao.onUlistClickRow
                        });
                    })
                }
            },
            onUlistClickRow: function(row, $element, field){
                var url = 'hetong/contracts';
                if(url && url!=''){
                    var cellViews = $('#ht_detail');
                    cellViews.html("");
                    $('#user_list_view').hide();
                    $('#ht_detail').show();
                    getBufferView(url,function(view){
                        var view = $(view);
                        var cvContainer = $('#ht_detail');
                        var ul = view.find('ul.dropdown-menu');
                        var data  = $('#ht_ulist_table').bootstrapTable('getData');
                        view.find('#ht_contract_find_text').html('<input type="hidden" name="autoCode" value="'+ row.autoCode + '"/>'+ (row.dname.length<8?row.dname:(row.dname.substring(0,6)+"…")));
                        $.each(data, function(i, obj){
                            var dname = obj.dname;
                            var li = $('<li><a>' + (dname.length<8?dname:(dname.substring(0,6)+'…')) + '</a>');
                            li.data('data',obj);
                            li.appendTo(ul);
                        })
                        view.appendTo(cvContainer);
                        $('#ht_sk_table').bootstrapTable({
                            cache:false,
                            url:'public/hetong/getShoukuan.action',
                            queryParams: function(params){
                                if(row.autoCode){
                                    params.autoCode = row.autoCode;
                                }
                                return params;
                            },
                        });
                        $('#ht_ys_table').bootstrapTable({
                            cache:false,
                            url:'public/hetong/getYingshou.action',
                            queryParams: function(params){
                                if(row.autoCode){
                                    params.id = row.autoCode;
                                }
                                return params;
                            }
                        });
                    })
                }
            },
            onDblClickRow2: function(row, $element, field){
                var cellViews = $('#cellsview');
                var table = cellViews.find('table');
                var url = 'app/data/ud_data.json';
                table.bootstrapTable('destroy');
                $('#cellsview').show();
                $('#guidance').hide();
                table.bootstrapTable({
                    height: 568,
                    striped: true,
                    showColumns: true,
                    showRefresh: true,
                    showToggle: true,
                    search: true,
                    idField: 'email',
                    searchAlign: 'right',
                    buttonsAlign: 'right',
                    toolbar: '#ht_cells_toolbar',
                    detailView: true,
                    url: url,
                    queryParams: row,
                    detailFormatter: hetong.htManager.zhidao.detailFormatter,
                    onLoadSuccess: function(data){
//                        $('#cellsview').show();
//                        $('#guidance').hide();
                        return data;
                    },
                    columns:[
                        {
                            field:'dId',
                            title:'自动编号',
                            sortable: true,
                            visible: false,
                            searchable: false
                        },{
                            field:'dname',
                            title:'单位名称',
                            sortable: true
                        },{
                            field:'email',
                            title:'负责人邮箱',
                            sortable: true,
                            visible: false,
                        },{
                            field:'cemail',
                            title:'细胞核邮箱',
                            sortable: true,
                            visible: false,
                        },{
                            field:'charger',
                            title:'负责人',
                            sortable: true,
                        },{
                            field:'thisYear',
                            title:'2015 收款（元）',
                            sortable: true,
                        },{
                            field:'lastYear',
                            title:'2016 收款（元）',
                            sortable: true,
                        },{
                            field:'yingshou',
                            title:'应收款（元）',
                            sortable: true,
                        }
                    ]
                });
            },
            toggleCellCore: function(email){
                $('#ht_cellsView_table').bootstrapTable('refresh',{
                    query:{email:email},
                    onLoadSuccess:function(){
                        $('#ht_cells_find').toggleClass('open');
                    }
                })
            },
            toggleUserList: function(email){
                $('#ht_ulist_table').bootstrapTable('refresh',{
                    query:{email:email},
                    onLoadSuccess:function(){
                        $('#ht_customer_find').toggleClass('open');
                    }
                })
            },
            toggleContracts: function(data){
                $('#ht_sk_table').bootstrapTable('refresh',{
                    query:{
                        autoCode:data.autoCode
                    }
                });
                $('#ht_ys_table').bootstrapTable('refresh',{
                    query:{
                        id:data.autoCode
                    }
                });
            }
        },
        testTable: function(){
            $('#ht_customer_list').DataTable({
                responsive: true,
                language: {
                    lengthMenu: '显示 _MENU_',
                    zeroRecords: '抱歉，没有找到任何记录',
                    info: '当前第 _PAGE_ 页，共 _PAGES_ 页',
                    infoEmpty: '没有记录',
                    infoFiltered: '(从 _MAX_ 中过滤记录)',
                    search: '查找',
                    paginate: {
                        sPrevious: '前一页',
                        sNext: '下一页'
                    }
                }
            });
        },
        findCharger: function(id,$table){
            //TODO 考虑一下在server端分页有没有必要

        },
        prepareParams: function(params){
            params = params ? params : {};
            $('#ht_customer_toolbar').find('input[name]').each(function () {
                params[$(this).attr('name')] = $(this).val();
            });
            return params;
        },
        responseHandler: function(){

        },
        queryCustomer: function(){

        },

    }
}