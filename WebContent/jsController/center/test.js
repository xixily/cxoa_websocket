/**
 * Created by dengxf on 2017/5/16.
 */
var show = function()  {
    var date = new Date();
    console.log(date.valueOf());
    setTimeout(function(){
        console.log('now date is:' + new Date().valueOf())
    },5000)
}

show();