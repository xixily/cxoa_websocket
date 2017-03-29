var url1 = 'http://bspoisp.sit.sf-express.com:11080/bsp-oisp/sfexpressService';
var url2 = 'https://bspoisp.sit.sf-express.com:11443/bsp-oisp/sfexpressService';
var xmlHead = "<?xml version='1.0' encoding='UTF-8'?><Request service='服务名' lang='zh-CN'><Head>utf8</Head><Body>请求数据 XML</Body></Request>";
var xmlEnd = "</Body></Request>";
var xmlBody="<orderid>123456</orderid>"+"<mailno>755123456789,001123456789,002123456789</mailno>" + "<is_gen_bill_no>1</is_gen_bill_no>";
var xml = xmlHead + xmlBody + xmlEnd;
var checkword = 'j8DzkIFgmlomPt0aLuwU';
var verifyCode = '';
var params = {};
params.xml = xml;
var md5Ckey = $.md5(xml + checkword);
var verifyCode = $.base64encode(md5Ckey);
params.verifyCode = verifyCode;
$.post(url1,params,function(result){
    console.log(result);
})