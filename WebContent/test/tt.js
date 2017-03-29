/**
 * Created by dengxf on 2016/11/4.
 */
function tt(data,parentId){
    if(data.rows){
        $.each(data.rows,function(n,obj){
            session.jiagou.push(obj.fourthLevel+'_'+obj.id + '_'+ obj.level);
            if(obj.level == 4){
                obj.iconCls = 'icon-man';
            }else{
                obj.iconCls = 'icon-organisation';
            }
        })
    }else if(data.level){
        if(data.level == 4){
            data.iconCls = 'icon-man';
        }else{
            data.iconCls = 'icon-organisation';
        }
    }
    return data;
}
function show_num(n){
    var it = $(".t_num i");
    var len = String(n).length;
    for(var i=0;i<len;i++){
        if(it.length<=i){
            $(".t_num").append("<i></i>");
        }
        var num=String(n).charAt(i);
        var y = -parseInt(num)*30; //y÷·Œª÷√
        var obj = $(".t_num i").eq(i);
        obj.animate({ //πˆ∂Ø∂Øª≠
                    backgroundPosition :'(0 '+String(y)+'px)'
                }, 'slow','swing',function(){}
        );
    }
}