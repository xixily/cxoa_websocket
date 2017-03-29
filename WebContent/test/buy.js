/**
 * Created by dengxf on 2017/3/12.
 */
var buy = function(){
    var a = $('div.popup.popup_purchase a.confirm');
    try{
        a.trigger('click');
    }catch(e){

    }finally{

    }
}
var timeout = false;
var timeWait = 2000;
var times = 0;
var endTime = "2017-03-26 18:14:01";
//var endTime = "2017-03-26 16:42:01";
//var endTime = "2017-03-26 18:13:01";
var timmer = function(start){
    timeWait = start || timeWait;
    if(ifEnd()){
        console.log('很抱歉,抢购结束！')
        return false;
    }
    buy();
    console.log("第"+ (++times) +"次抢购");
    setTimeout(timmer,timeWait);
}

function ifEnd(){
//    if(getNowFormatDate() == endTime || timeout){
    if(timeout){
        console.log('到点啦。');
        return true;
    }
    return false;
}

function getNowFormatDate() {
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

//    var hours = date.getHours().toString.length<2 ? ("0"+date.getHours()) : date.getHours();
//    var minutes = date.getMinutes().toString.length<2 ? ("0"+date.getMinutes()) : date.getMinutes();
//    var seconds = date.getSeconds().toString.length<2 ? ("0"+date.getSeconds()) : date.getSeconds();

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}

timmer(200)