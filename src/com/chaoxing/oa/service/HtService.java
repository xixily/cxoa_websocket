package com.chaoxing.oa.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.po.hetong.Contract;
import com.chaoxing.oa.entity.po.hetong.ContractVO;
import com.chaoxing.oa.entity.po.hetong.Customer;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.Fahuo;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.Usercontracts;
import com.chaoxing.oa.entity.po.hetong.CompanyInfo;

public interface HtService {

//----------------------合同	
	// 获取合同列表
	public List<Contract> getContractList();
	
	//根据搜索条件获取合同列表
	public List<Contract> getContractListCondition(String year, String purchaseCom, String gongsi, String danwei,
			String product, String province, String group, String responsibility, String zhuangtai, String userId,
			int page, int size);
	
	//根据搜索条件获取合同列表(测试)
	public List<Contract> getContractListConditionTest(String purchaseCom, String gongsi, String danwei, String product, String province,String group,String responsibility,Integer userId,Integer state, int page, int size );
	
	//获取满足搜索条件的合同数量
	public int getConditionCountContract(String purchaseCom, String gongsi, String danwei, String product, String province, String group, String responsibility, Integer userId, Integer state);
	//分页获取合同列表(无搜索条件)
	public List<Contract> getContractList(int page, int size);
	
    //创建一个新合同
	public void addContractNomal(Contract contract);
	// 根据合同ID删除合同
	public void deleteContract(int contractId);
	//获取未处理合同的数量
	public int getUnhandledContract();
	//获取整个合同数量
	public int getTotalCountContract();
	//根据合同ID查询出该合同
	public Contract getContracDetailById(int id);
	//获取未处理合同列表
	public List<Contract> getUnHandledContract(int page,int size);
	
	public List<Contract> getPropertyList();
	
	public List<ItemPrice> getProductList();
	
	//改变合同状态
	public void updateContractCondition(int contractId);
	
	//合同未通过
	public void updatecontractNoPass(int contractId);
	
	//销售保存合同  
	public void updateContractSave(Integer id, String gongsi, String depart, Integer cid, Integer didNum,
			Float contractMoney, String agreementNumber, Date endTime, String agreementText, String remarksText,
			String payMethod, Integer dealConditon,String gangweiXingzhi, String bumenmingcheng,
			String shengfen, String xibaohe,Date submitTime,String danweixingzhi,String yonghuxingzhi);
	
//-----------------------产品
	
	//根据合同编号，添加新产品
	public void addItemPrice(ItemPrice itemprice);
    
	//根据合同编号和产品ID删除某一合同下的产品
	public void deleteItemPrice(int itemPriceId);
	
    //根据合同编号和产品ID查出某一合同下的产品    
	public ItemPrice selectItemPrice(Integer itemPriceId);

	//更新产品
	public void updateItemPrice(String name, Float amount, String begain, String end, Float money,Integer productId);

	//查询出该合同下面的产品列表信息
	public List<ItemPrice> getitemPriceList(String htNum);

	//更新合同信息
	public void updateContract(Integer id, String company, String depart, Integer cid, Integer didNum,
			Float contractMoney, String agreementNumber, Date endTime, String agreementText, String remarksText,
			String payMethod);
//--------------------------------发票	
	//获取该合同下的所有发票
	public List<FaPiao> getfaPiaoList(String htNum);

	//添加发票
	public void addFaPiao(FaPiao faPiao);

	//删除该合同下的对应发票
	public void deleteFaPiao(int faPiaoId);

	//回显发票
	public FaPiao selectFaPiao(Integer faPiaoId);
	
	//更新发票
	public void updateFapiao(BigDecimal money, String capitalMoney, String company, String departMement, String type,
			String name, Date date1, String remark, Integer fapiaoID);

//------------------------------快递
	public List<Fahuo> getFaHuoList(String htNum);

	//添加快递
	public void addFahuo(Fahuo fahuo);

	//删除快递
	public void deleteFahuo(Integer fahuoId);

	//回显
	public Fahuo selectFahuo(Integer fahuoId);

	//更新快递信息
	public void updateFahuo(String mailno, String d_contact, String d_tel, String d_company, 
			String d_address,  String jDate, String postMethod, String content,
			Integer fahuoId);

	
	
	
	//根据合同ID查出该合同下的产品项
	public List<ItemPrice> getItemPriceByContractId(int id);

	//根据合同ID查出该合同下的发票项
	public List<FaPiao> getFaPiaoByContractId(int id);

	//根据合同ID查出该合同下的快递项
	public List<Fahuo> getFaHuoByContractId(int id);

	
	//获取下拉公司列表
	public List<Contract> getCompanyList();
//	public List<Object> getCompanyList();
	
	
	
//-----------------------------------销售	
	
	
	//销售负责的合同列表
	public List<Usercontracts> getContractListSale(String emial, int page, int size);
	
   //销售负责的总合同数量
	public int getTotalCountContractSale(String eamil);

	//获取合同最大编号
	public int gethtMaxNum();

	public List<Contract> getDepartList();
   //获取该销售人员负责的项目单位
	public List<CustomerDepart> getDepartListAboutSale(String charger);

	
	

	/*
	 * //用户列表 public Map<String, Object> findCustomers(PCustomer pCustomer, int
	 * isExport);
	 * 
	 * public PCustomer getCustomer(int id);
	 * 
	 * public int addCustomer(PCustomer pCustomer);
	 * 
	 * public int updateCustomer(PCustomer pCustomer);
	 * 
	 * public int deleteCustomer(PCustomer pCustomer);
	 * 
	 * //用户单位 public Map<String, Object> findCustomerDepart(PCustomerDepart
	 * pCustomerDepart, int isExport);
	 * 
	 * public PCustomerDepart getCustomerDepart(int id);
	 * 
	 * public int addCustomerDepart(PCustomerDepart pCustomerDepart);
	 * 
	 * public int updateCustomerDepart(PCustomerDepart pCustomerDepart);
	 * 
	 * public int deleteCustomerDepart(PCustomerDepart pCustomerDepart);
	 * 
	 * //合同情况 public Map<String, Object> findContracts(PContract pConstract, int
	 * isExport);
	 * 
	 * public PContract getContract(Integer hetongCode);
	 * 
	 * public int addContract(PContract pConstract);
	 * 
	 * public int updateContract(PContract pConstract);
	 * 
	 * public int deleteContract(PContract pConstract);
	 * 
	 * //发票情况 public Map<String, Object> findFapiao(PFapiao pfapiao, int
	 * isExport);
	 * 
	 * public PFapiao getFapiao(int id);
	 * 
	 * public int updateFapiao(PFapiao pfapiao);
	 * 
	 * public int addFapiao(PFapiao pfapiao);
	 * 
	 * public int deleteFapiao(PFapiao pfapiao);
	 * 
	 * //分项报价 public Map<String, Object> findItemPrice(PItemPrice pItemPrice,
	 * int isExport);
	 * 
	 * public PItemPrice getItemPrice(int id);
	 * 
	 * public int updateItemPrice(PItemPrice pItemPrice);
	 * 
	 * public int addItemPrice(PItemPrice pItemPrice);
	 * 
	 * public int deleteItemPrice(PItemPrice pItemPrice);
	 * 
	 * //发货情况 public Map<String, Object> findFahuo(PFahuo pfahuo, Page page, int
	 * isExport);
	 * 
	 * public PFahuo getFahuo(Integer orderId);
	 * 
	 * public int addFahuo(PFahuo pfahuo);
	 * 
	 * public int deleteFahuo(PFahuo pfahuo);
	 * 
	 * public int updateFahuo(PFahuo pfahuo);
	 * 
	 * //公司代码 public PCompanyInfo getCompanyInfo(int i);
	 * 
	 * public PCompanyInfo getCompanyInfoByName(String company);
	 */
	public void findContracts();

	//获取该负责人下项目单位对应的用户ID和单位ID
	public CustomerDepart getUserAndDepartId(Integer yonghuId);

	//根据邮箱获取负责人
	public String getOperator(String email);

	//取消按钮操作
	public void deleteItemPriceByHtid(Integer id);

	public void deleteFapiaoByHtid(Integer id);

	public void deleteFahuoByHtid(Integer id);

	//获取发件地
	public CompanyInfo getFajiandi(String company);

	//根据负责人查出用户单位对象
	public CustomerDepart getCustomerDepartByoperator(Integer cid);

	//根据销售本人ID查询出RenshiUserName对象
	public RenshiUserName getRenshiUserName(int saleId);

	//获取所有产品列表
	public List<Object> getAllProduct();

	
	public ContractVO getContractVObyhtid(Integer contractId);

	/*public void addErrorInfo(String shuxingming, String errorInfo, Integer contractId);*/

	//创建错误信息对象
	public void addErrorInfo(ContractVO contractVO);

	//根据ID查询出错误消息对象
	public ContractVO getContractVOByid(Integer id);

	//根据用户单位id查用户列表对象
	public Customer getyonghuById(Integer id2);

	//行政人员更新合同信息
	public void updateContractXingzheng(String yinhuashui, String guidangDate, Float huaizhangAmount,String guidangCode,Integer contractId);

	//获取单位对应代码
	public String getdanweicode(String company);

	//点通过按钮，删除对应的错误信息
	public void deleteErrorInfo(int contractId);

	public List<FaPiao> getFaPiaoByContractId2(Integer id);

	//获取归档最大编号
	public String getguidangMaxNum(String code);

	

	


}
