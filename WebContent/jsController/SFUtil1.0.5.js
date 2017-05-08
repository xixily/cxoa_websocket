/**
 * Created by Administrator on 2016/10/13.
 */
/**
 * 顺丰工具
 * @type {{sendOrder: Function, queryOrder: Function, queryRouteByid: Function, queryRouteByMailno: Function, queryRoute: Function}}
 */
var SFUtil = {
    /**
     *发送快递，获取运单号
     * @params order 订单属性
     * //Order 属性
     orderid;//(必填)
     mailno;//顺丰运单号
     is_gen_bill_no;//要求返回运单号
     j_company;//寄件方单位
     j_contact;//寄件方联系人
     j_tel;//寄件方联系电话
     //	j_shippercode;//寄件方国家/城市代码(条件，跨境必填)
     //	j_country;//寄方国家
     j_province;//寄件方所在省份
     j_city;//寄件方所在城市名称
     j_county;//寄件人所在县/区
     j_address;//寄件方详细地址(条件，如果需要 生成电子运单)
     //	j_post_code ;//寄方邮编(条件，跨境必填)
     //Order 到方
     d_company;//到方公司名称（必填）
     d_contact;//到件方联系人（必填）
     d_tel;//到件方联系电话（必填）
     d_mobile;//到件方手机
     //	d_deliverycode;//到件方代码，（条件，跨境必填）
     //	d_country;//到方国家
     d_province;//到件方所在省份
     d_city;//到件方所在城市名
     d_county;//到件方所在县/区
     d_address;//到件方详细地址（必填），如果不传输 d_province/d_city 字段，此详细地址 需包含省市信息，以提高地址识别的
     d_post_code;//到方邮编
     //Order 其他信息
     custid;//顺丰月结卡号（条件）
     pay_method;//付款方式：1、寄方付；2、收方付；3、第三方付
     express_type;//快件产品类别，见《快件产品类别表》，1、标准快递；2、顺丰特惠；3、电商特惠；5、顺丰次晨；6、即日件；7、电商速配;28、电商专配
     parcel_quantity;//包裹数,字母件
    cargo_length;//客户订单货物总长number(10,3)
    cargo_width;//客户订单货物总宽number(10,3)
    cargo_height;//客户订单货物总number(10,3)
    volume;//订单货物总体积number(17,3)
    cargo_total_weight ;//订单货物总重量number(10,3)
     //	private Float declared_value;//客户订单货物总声明价值(跨境件必填）
     //	declared_value_currency;//货物声明价值币别，CNY: 人民币 ；USD: 美元 ；HKD: 港币 ；NTD: 新台币 ；
     //	customs_batchs;//报关批次
     sendstarttime;//要求上门取件开始时间
     is_docall;//是否要求通过是否手持终端通知顺丰
     //	return_tracking;//顺丰签回单服务运单号
     //	d_tax_no;//收件人税号
     tax_pay_type;//税金付款方式： 1、寄方；2、到付
     //	tax_set_accounts;//税金结算账号
     //	original_number;//电商原始订单号
     payment_tool;//支付工具
     payment_number;//支付号码
     //	goods_code;//商品编号
     //	in_process_waybill_no;//头程运单号
     //	brand;//货物品牌
     //	specifications;//货物规格型号
     //	temp_range;//温度范围类型（条件，，当 express_type 为 12，1、冷藏；3、冻藏 ）
     order_name;//客户订单下单人姓名
     //	order_cert_type;//客户订单下单人证件类型
     //	order_source;//客户订单来源
     //	template;//业务模板编码
     remark;//备注
     oneself_pickup_flg;//快件自取；1 表示客户同意快件自取； 非 1 表示客户不同意快件自取
     //	dispatch_sys;//订单数据分发的系统编码
     *@param cargos [{}] 货物信息
     * name;//货物名称(必填，生成电子运单必填)
    * count;//货物数量（条件，跨境必填）
    * unit;//货物单位（条件，跨境必填）
    * private Float weight;//订单货物单位重量（条件，跨境必填）
    * amount;//货物单价（条件，跨境必填）
    * currency;//货物单价的币别：
    * source_area;//原产地国别（条件，跨境必填）
    * product_record_no;//货物产品国检备案编号
    * good_prepard_no;//商品海关备案号
     * @param addService
     * name;//增值服务名，如 COD 等。
     *value;//增值服务扩展属性，参考增值服务传 值说明。
     *value1;//增值服务扩展属性
     */
    sendOrder : function(order, cargos, addService){

    },
    printKuaidiList : function(){

    },
    queryOrder : function(){

    },
    queryRouteByid : function(){

    },
    queryRouteByMailno : function(){

    },
    /**
     * 路由查询
     * @param rtRequest {type:,orderid:,method_type:}
     * @type {type:'1、按顺丰运单号查询；2、客户订单号查询',orderid:'单号',method_type:'路由查询类别：1：标准路由查询 ;2：定制路由查询'}
     * @param  type 1、按顺丰运单号查询；2、客户订单号查询
     * @param orderid 单号
     * @param method_type 路由查询类别：1：标准路由查询 ;2：定制路由查询
     */
    queryRoute : function(rtRequest){

    }

}