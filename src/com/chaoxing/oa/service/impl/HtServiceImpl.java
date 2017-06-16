package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.dao.BaseHetongDaoI;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.Fahuo;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.Usercontracts;
import com.chaoxing.oa.entity.po.view.Usercontracts2;
import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;
import com.chaoxing.oa.entity.po.employee.UserName;
import com.chaoxing.oa.entity.po.hetong.Area;
import com.chaoxing.oa.entity.po.hetong.CompanyInfo;
import com.chaoxing.oa.entity.po.hetong.Contract;
import com.chaoxing.oa.entity.po.hetong.ContractVO;
import com.chaoxing.oa.entity.po.hetong.Customer;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.service.HtService;

@Service("hetongService")
public class HtServiceImpl implements HtService {

	@Autowired
	private BaseDaoI<Contract> contractDao;
	@Autowired
	private BaseDaoI<CustomerDepart> customerDepartDao;
	@Autowired
	private BaseDaoI<Fahuo> fahuoDao;
	@Autowired
	private BaseDaoI<FaPiao> fapiaoDao;
	@Autowired
	private BaseDaoI<ItemPrice> itemPriceDao;
	@Autowired
	private BaseDaoI<Object> objDao;
	@Autowired
	private BaseDaoI<CompanyInfo> companyInfoDao;
//	@Autowired
//	private BaseDaoI<RenshiUserName> renshiUserNameDao;
	@Autowired
	private BaseDaoI<OrganizationStructure> organizationStructureDao;
	@Autowired
	private BaseDaoI<UserName> userNameDao;
	@Autowired
	private BaseDaoI<ContractVO> contractVODao;
	@Autowired
	private BaseDaoI<Customer> customerDao;
	@Autowired
	private BaseDaoI<Usercontracts> usercontractsDao;
	@Autowired
	private BaseDaoI<Usercontracts2> usercontracts2Dao;
	@Autowired
	private BaseDaoI<Area> areaDao;

	// 获取合同列表
	@Override
	public List<Contract> getContractList() {
		String sql = "";
		return null;
	}

	// 根据搜索条件获取合同列表
	@Override
	public List<Contract> getContractListCondition(String year, String purchaseCom, String gongsi, String danwei,
			String product, String province, String group, String responsibility, String zhuangtai, String userId,
			int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", year);
		params.put("purchaseCom", purchaseCom);
		params.put("gongsi", gongsi);
		params.put("danwei", danwei);
		params.put("product", product);
		params.put("province", province);
		params.put("group", group);
		params.put("responsibility", responsibility);
		params.put("zhuangtai", zhuangtai);
		params.put("userId", userId);
		params.put("page", page);
		params.put("size", size);

		String hql = "from Contract  where 签订时间  like :year and 单位 like :purchaseCom and 所属公司 like :gongsi and 单位性质 like :danwei"
				+ "and 品名 like :product and 省份 like :province and 小组 like :group and 经办人 like :responsibility and 处理状态 like :zhuangtai and"
				+ "用户编号=:userId ";
		List<Contract> contractList = contractDao.find(hql, params, page, size);
		return contractList;
	}

	// 获取满足搜索条件的合同数量
	@Override
	public int getConditionCountContract(String depart, String company, String xingzhi, String fourthLevel,
			String charger, Integer cid, Integer dealConditon) {
		Map<String, Object> params = new HashMap<String, Object>();
		/* String dengjiTime1 = "%"+dengjiTime+"%"; */
		/* params.put("dengjiTime", dengjiTime1); */

		if (depart != null && !"".equals(depart)) {  
			String depart1 = "%" + depart + "%";
			params.put("depart", depart1);
		}

		if (fourthLevel != null && !"".equals(fourthLevel)) {
			String fourthLevel1 = "%" + fourthLevel + "%";
			params.put("fourthLevel", fourthLevel1);
		}

		if (charger != null && !"".equals(charger)) {
			String charger1 = "%" + charger + "%";
			params.put("charger", charger1);
		}

		if (dealConditon != -10) {
			params.put("dealConditon", dealConditon);
		}
		if (company.equals("全部") == false) {
			params.put("company", company);
		}
		if (xingzhi.equals("全部") == false) {
			params.put("xingzhi", xingzhi);
		}
		/*
		 * if(pingming.equals("全部")==false){ params.put("pingming", pingming); }
		 */
		/*
		 * if(province.equals("全国")==false){ params.put("province", province); }
		 */
		if (cid != null) {
			params.put("cid", cid);
		}

		int count = usercontracts2Dao.queryResultList(Usercontracts2.class, params);
	//	int count = contractList.size();
		return count;
		/*
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * params.put("purchaseCom", purchaseCom); String hql =
		 * "select count(*) from Contract where 单位 like :purchaseCom"; Long
		 * count = contractDao.count(hql,params); int count1 =
		 * Integer.parseInt(count.toString()); return count1;
		 */
	}

	@Override
	public List<Usercontracts2> getContractListConditionTest(String depart, String company, String xingzhi,
			String fourthLevel, String charger, Integer cid, Integer dealConditon, int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		/* String dengjiTime1 = "%"+dengjiTime+"%"; */
		/* params.put("dengjiTime", dengjiTime1); */
		
		if (depart != null && !"".equals(depart)) {
			String depart1 = "%" + depart + "%";
			params.put("depart", depart1);
		}
		
		if (fourthLevel != null && !"".equals(fourthLevel)) {
			String fourthLevel1 = "%" + fourthLevel + "%";
			params.put("fourthLevel", fourthLevel1);
		}
		
		if (charger != null && !"".equals(charger)) {
			String charger1 = "%" + charger + "%";
			params.put("charger", charger1);
		}

		if (dealConditon != -10) {  //-10表示全部
			params.put("dealConditon", dealConditon);
		}
		if (company.equals("全部") == false) {
			params.put("company", company);
		}
		if (xingzhi.equals("全部") == false) {
			params.put("xingzhi", xingzhi);
		}
		/*
		 * if(pingming.equals("全部")==false){ params.put("pingming", pingming); }
		 */
		/*
		 * if(province.equals("全国")==false){ params.put("province", province); }
		 */
		if (cid != null) {
			params.put("cid", cid);
		}
		List<Usercontracts2> contractList = usercontracts2Dao.queryResultList(Usercontracts2.class, params, page, size);
		return contractList;
	}

	// 获取未处理合同列表
	@Override
	public List<Usercontracts2> getUnHandledContract(int page, int size) {
		String hql = "from Usercontracts2  where dealConditon=0 order by id desc";
		List<Usercontracts2> unHandledContractList = usercontracts2Dao.find(hql, page, size);
		return unHandledContractList;
	}

	// 更新合同信息
	// update 合同情况 set
	// 所属公司=:company,单位=:depart,用户编号=:cid,单位编号=:didNum,合同金额=:contractMoney,合同份数=:agreementNumber,项目预计结算时间=:endTime,合同内容:agreementText,备注=:remarksText,付款方式=:payMethod
	// where 合同编号=:id
	@Override
	public void updateContract(Integer id, String company, String depart, Integer cid, Integer didNum,
			Float contractMoney, String agreementNumber, Date endTime, String agreementText, String remarksText,
			String payMethod, Integer dealConditon) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("company", company);
		params.put("depart", depart);
		params.put("cid", cid);
		params.put("didNum", didNum);
		params.put("contractMoney", contractMoney);
		params.put("agreementNumber", agreementNumber);
		params.put("endTime", endTime);
		params.put("agreementText", agreementText);
		params.put("remarksText", remarksText);
		params.put("payMethod", payMethod);
		params.put("changeStatus", dealConditon);
		String hql = "update Contract set company=:company,depart=:depart,cid=:cid,didNum=:didNum,contractMoney=:contractMoney,agreementNumber=:agreementNumber,endTime=:endTime,agreementText=:agreementText,remarksText=:remarksText,payMethod=:payMethod,dealConditon=:changeStatus where id=:id";
		objDao.executeHql(hql, params);
	}

	// 由暂存改为未处理
	/*@Override
	public void updateZanCunContract(Integer id, String company, String depart, Integer cid, Integer didNum,
			Float contractMoney, String agreementNumber, Date endTime, String agreementText, String remarksText,
			String payMethod, Integer changeStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("company", company);
		params.put("depart", depart);
		params.put("cid", cid);
		params.put("didNum", didNum);
		params.put("contractMoney", contractMoney);
		params.put("agreementNumber", agreementNumber);
		params.put("endTime", endTime);
		params.put("agreementText", agreementText);
		params.put("remarksText", remarksText);
		params.put("payMethod", payMethod);
		params.put("changeStatus", changeStatus);
		
		String hql = "update Contract set company=:company,depart=:depart,cid=:cid,didNum=:didNum,contractMoney=:contractMoney,agreementNumber=:agreementNumber,endTime=:endTime,agreementText=:agreementText,remarksText=:remarksText,payMethod=:payMethod,dealConditon=:changeStatus where id=:id";
		objDao.executeHql(hql, params);
	}*/

	@Override
	public int getUnhandledContract() {
		String hql = "select count(*) from Usercontracts2 c where dealConditon=0";
		Long count = contractDao.count(hql);
		int count1 = Integer.parseInt(count.toString());
		return count1;
	}

	@Override
	public Contract getContracDetailById(int id) {
		Contract contract = contractDao.get(Contract.class, id);
		return contract;
	}

	@Override
	public int getTotalCountContract() {
		String hql = "select count(*) from Usercontracts2 where dealConditon!=4 and dealConditon!=-1";
		Long totalCountContract = usercontracts2Dao.count(hql);
		int totalCountContract1 = Integer.parseInt(totalCountContract.toString());
		return totalCountContract1;
	}

	// 获取合同列表(分页) or dealConditon is null
	@Override
	public List<Usercontracts2> getContractList(int page, int size) {
		String hql = "from Usercontracts2 where dealConditon!=4 and dealConditon!=-1 order by id desc";
		List<Usercontracts2> contractList = usercontracts2Dao.find(hql, page, size);
		return contractList;
	}

	// createContract
	@Override
	public void addContractNomal(Contract contract) {
		try {
			contractDao.save(contract);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除一个合同
	@Override
	public void deleteContract(int contractId) {
		// 删除该合同下的产品,快递,发票
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		String sql1 = "delete from 合同分项报价  WHERE 合同编号= :contractId";
		itemPriceDao.executeSql(sql1, params);

		String sql2 = "delete from 发货情况  WHERE 合同编号= :contractId";
		itemPriceDao.executeSql(sql2, params);

		String sql3 = "delete from 发票情况  WHERE 合同编号= :contractId";
		itemPriceDao.executeSql(sql3, params);

		Contract c = new Contract();
		c.setId(contractId);
		contractDao.delete(c);
	}

	@Override
	public void updateContractCondition(int contractId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		String sql = "UPDATE 合同情况 SET 处理状态=2 WHERE 合同编号= :contractId";
		contractDao.executeSql(sql, params);
	}
	
	@Override
	public void updateDealConditon(int contractId, int dealConditon) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		params.put("dealConditon", dealConditon);
		String sql = "UPDATE 合同情况 SET 处理状态= :dealConditon WHERE 合同编号= :contractId";
		contractDao.executeSql(sql, params);
	}

	@Override
	public void updatecontractNoPass(int contractId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		String hql = "update Contract set dealConditon=1 where id=:contractId";
		objDao.executeHql(hql, params);
	}

	// 添加产品
	@Override
	public void addItemPrice(ItemPrice itemprice) {
		try {
			itemPriceDao.save(itemprice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 更新产品
	@Override
	public void updateItemPrice(ItemPrice itemprice) {
		try {
			itemPriceDao.update(itemprice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除产品
	@Override
	public void deleteItemPrice(int itemPriceId) {
		ItemPrice i = new ItemPrice();
		i.setId(itemPriceId);
		itemPriceDao.delete(i);
	}

	// 回显产品信息
	@Override
	public ItemPrice selectItemPrice(Integer itemPriceId) {
		ItemPrice itemPrice = itemPriceDao.get(ItemPrice.class, itemPriceId);
		return itemPrice;
	}

	@Override
	public void updateItemPrice(String productName, Float amount, String begain, String end, Float money, Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", productName);
		params.put("amount", amount);
		params.put("begain", begain);
		params.put("end", end);
		params.put("money", money);
		params.put("id", id);
		String sql = "update 合同分项报价 set 分项名称=:name,数量=:amount,开始时间=:begain,截止时间=:end,分项金额=:money where id=:id";
		itemPriceDao.executeSql(sql, params);
	}

	// 获取产品列表信息
	@Override
	public List<ItemPrice> getitemPriceList(String htNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htNum", htNum);
		String sql = "select * from 合同分项报价  where 合同编号 =:htNum";
		List<ItemPrice> itemPriceList = itemPriceDao.findSql(sql, params);
		return itemPriceList;
	}

	// -----------------------------------发票

	@Override
	public List<com.chaoxing.oa.entity.po.hetong.FaPiao> getfaPiaoList(String htNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htNum", htNum);
		String sql = "select * from 发票情况  where 合同编号 =:htNum";
		List<FaPiao> faPiaoList = fapiaoDao.findSql(sql, params);
		return faPiaoList;

	}

	// 添加发票
	@Override
	public void addFaPiao(FaPiao faPiao) {
		try {
			fapiaoDao.save(faPiao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 更新发票
	@Override
	public void updateFaPiao(FaPiao faPiao) {
		try {
			fapiaoDao.update(faPiao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除该合同下的对应发票
	@Override
	public void deleteFaPiao(int faPiaoId) {
		FaPiao f = new FaPiao();
		f.setId(faPiaoId);
		fapiaoDao.delete(f);
	}

	// 回显发票
	@Override
	public FaPiao selectFaPiao(Integer faPiaoId) {
		FaPiao faPiao = fapiaoDao.get(FaPiao.class, faPiaoId);
		return faPiao;
	}

	// 更新发票
	@Override
	public void updateFapiao(BigDecimal money, String capitalMoney, String company, String departMement, String type,
			String name, Date date1, String remark, Integer fapiaoID, Date receivedpaymentsdate, BigDecimal huiKuan,
			String fundType, String account,Date yujihuikuanDate,String caiwuMonth,Integer hetongId_) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("money", money);
		params.put("capitalMoney", capitalMoney);
		params.put("company", company);
		params.put("departMement", departMement);
		params.put("type", type);
		params.put("name", name);
		params.put("date1", date1);
		params.put("remark", remark);
		params.put("fapiaoID", fapiaoID);
		params.put("receivedpaymentsdate", receivedpaymentsdate);
		params.put("huiKuan", huiKuan);
		params.put("fundType", fundType);
		params.put("account", account);
		params.put("yujihuikuanDate", yujihuikuanDate);
		params.put("caiwuMonth", caiwuMonth);
		params.put("hetongId_", hetongId_);
		String sql = "update 发票情况 set 金额=:money,大写金额=:capitalMoney,开票公司=:company,开票单位=:departMement,发票类型=:type,发票品名 =:name, 开票时间=:date1,备注=:remark,回款日期=:receivedpaymentsdate,回款情况=:huiKuan,资金类型=:fundType,账户=:account,开票预计回款时间=:yujihuikuanDate,财务月份=:caiwuMonth,合同编号=:hetongId_ where 序号=:fapiaoID";
		fapiaoDao.executeSql(sql, params);
	};
	
	//销售更新发票信息
	@Override
	public void updateFapiaoSale(BigDecimal money, String capitalMoney, String company, String departMement,
			String type, String name, Date date1, String remark, Integer fapiaoID, Date yujihuikuanDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("money", money);
		params.put("capitalMoney", capitalMoney);
		params.put("company", company);
		params.put("departMement", departMement);
		params.put("type", type);
		params.put("name", name);
		params.put("date1", date1);
		params.put("remark", remark);
		params.put("fapiaoID", fapiaoID);
		params.put("yujihuikuanDate", yujihuikuanDate);
		
		String sql = "update 发票情况  set 金额=:money,大写金额=:capitalMoney,开票公司=:company,开票单位=:departMement,发票类型=:type,发票品名 =:name, 开票时间=:date1,备注=:remark,开票预计回款时间=:yujihuikuanDate where 序号=:fapiaoID";
		fapiaoDao.executeSql(sql, params);
	}
	

	// --------------------------快递
	@Override
	public List<Fahuo> getFaHuoList(String htNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htNum", htNum);
		String sql = "select * from 发货情况  where 合同编号 =:htNum";
		List<Fahuo> FahuoList = fahuoDao.findSql(sql, params);
		return FahuoList;
	}

	// 添加快递
	@Override
	public void addFahuo(Fahuo fahuo) {
		try {
			fahuoDao.save(fahuo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 更新快递
	@Override
	public void updateFahuo(Fahuo fahuo) {
		try {
			fahuoDao.update(fahuo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除快递
	@Override
	public void deleteFahuo(Integer fahuoId) {
		Fahuo f1 = new Fahuo();
		f1.setOrderid(fahuoId);
		fahuoDao.delete(f1);
	}

	// 回显 发货信息
	@Override
	public Fahuo selectFahuo(Integer fahuoId) {
		Fahuo fahuo = fahuoDao.get(Fahuo.class, fahuoId);
		return fahuo;
	}

	//更新快递信息(销售)
	@Override
	public void updateFahuo(String mailno, String d_contact, String d_tel, String d_address, String jDate,
			String postMethod, String content, Integer orderid, String postAddress) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("d_contact", d_contact);
		params.put("d_tel", d_tel);
		params.put("d_address", d_address);
		params.put("postMethod", postMethod);
		params.put("content", content);
		params.put("postAddress", postAddress);
		params.put("orderid", orderid);
		/*if(mailno!=null){
			hql.append("mailno=:mailno").append(",");
			params.put("mailno", mailno);
		//	hql = "update Fahuo set mailno=:mailno,d_contact=:d_contact, d_tel=:d_tel,d_address=:d_address,jDate=:jDate,postMethod=:postMethod,content=:content where orderid=:orderid";
		}
		if(jDate!=null){
			hql.append("jDate=:jDate").append(",");
			params.put("jDate", jDate);
		}*/
		StringBuffer hql = new StringBuffer("update Fahuo set d_contact=:d_contact, d_tel=:d_tel,d_address=:d_address,postMethod=:postMethod,content=:content,postAddress=:postAddress where orderid=:orderid");
		itemPriceDao.executeHql(hql.toString(), params);
	}
     //更新快递信息(行政,合同编号可编辑)
	@Override
	public void updateFahuoXingzheng(String mailno, String d_contact, String d_tel, String d_address, String jDate,
			String postMethod, String content,Integer orderid,Integer hetongCodeInt, String postAddress) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mailno", mailno);
		params.put("d_contact", d_contact);
		params.put("d_tel", d_tel);
		params.put("d_address", d_address);
		params.put("jDate", jDate);
		params.put("postMethod", postMethod);
		params.put("content", content);
		params.put("orderid", orderid);
		params.put("hetongCodeInt", hetongCodeInt);
		params.put("postAddress", postAddress);
		String hql = "update Fahuo set mailno=:mailno,d_contact=:d_contact, d_tel=:d_tel,d_address=:d_address,jDate=:jDate,postMethod=:postMethod,content=:content,hetongCode=:hetongCodeInt,postAddress=:postAddress where orderid=:orderid";
		itemPriceDao.executeHql(hql, params);
	}
	
	
	// 根据合同ID查出该合同下的产品项
	@Override
	public List<ItemPrice> getItemPriceByContractId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htid", id);
		String hql = "from ItemPrice  where ctid=:htid";
		// String hql ="FROM ItemPrice where ctid=:htid";
		List<ItemPrice> itemPriceList = itemPriceDao.find(hql, params);
		return itemPriceList;
	}

	// 根据合同ID查出该合同下的发票项
	@Override
	public List<FaPiao> getFaPiaoByContractId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htid", id);
		String hql = "from FaPiao where hetongNumber=:htid";
		// String hql ="FROM FaPiao where hetongNumber=:htid";
		List<FaPiao> faPiaoList = fapiaoDao.find(hql, params);
		return faPiaoList;
	}

	// 根据合同ID查出该合同下的发票项
	@Override
	public List<FaPiao> getFaPiaoByContractId2(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String hql = "from FaPiao where hetongNumber=:id";
		// String hql ="FROM FaPiao where hetongNumber=:htid";
		List<FaPiao> faPiaoList = fapiaoDao.find(hql, params);
		return faPiaoList;
	}

	// 根据合同ID查出该合同下的快递项
	@Override
	public List<Fahuo> getFaHuoByContractId(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("htid", id);
		// String hql = "FROM Fahuo where hetongCode=:htid";
		String hql = "from Fahuo where hetongCode=:htid";
		List<Fahuo> fahuoList = fahuoDao.find(hql, params);
		return fahuoList;
	}

	// 获取下拉公司列表
	@Override
	public List<CompanyInfo> getCompanyList() {
		String hql = "from CompanyInfo ";
		List<CompanyInfo> companyInfoList = companyInfoDao.find(hql);
		return companyInfoList;
	}

	@Override
	public List<Contract> getPropertyList() {
		String sql = "select 性质  from 合同情况  group by 性质";
		List<Contract> propertyList = contractDao.findSql(sql);
		return propertyList;
	}

	@Override
	public List<ItemPrice> getProductList() {
		String sql = "select 分项名称  from 合同分项报价  group by 分项名称";
		List<ItemPrice> productList = itemPriceDao.findSql(sql);
		return productList;
	}

	// ---------------------销售

	@Override
	public List<Usercontracts2> getContractListSale(String email, int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		/*
		 * String hql =
		 * "from Contract where operator =:responsibility order by id desc";
		 */
		// String sql = "SELECT * FROM ((`用户单位` `c` LEFT JOIN (`username` `u`
		// LEFT JOIN `组织结构图` `d` ON ((`u`.`部门ID` = `d`.`id`))) ON (((`u`.`邮箱` =
		// `c`.`销售邮箱`) AND (`d`.`四级` = `c`.`小组`)))) LEFT JOIN `合同情况` `h` ON
		// ((`h`.`用户编号` = `c`.`自动编号`))) WHERE 销售邮箱=:email limit :page,:size";
		/*
		 * String sql =
		 * "SELECT * FROM usercontracts WHERE 邮箱=:email  limit :begin,:size ";
		 * List<Object> contractList = objDao.findSql(sql, params);
		 */ 
		//不查垃圾合同和副本合同 and dealConditon!=-1 and type=1
		String hql = "FROM Usercontracts2 where email=:email order by id desc";
		List<Usercontracts2> usercontracts2List = usercontracts2Dao.find(hql, params, page, size);
		/*String hql = "FROM Contract where email=:email and dealConditon!=-1 and type=1 order by id desc";
		List<Contract> contractList = contractDao.find(hql, params, page, size);*/
		return usercontracts2List;
	}

	@Override
	public int getTotalCountContractSale(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		//and 处理状态!=-1 and 合同状态!=2
		String sql = "SELECT count(*) FROM usercontracts2 WHERE 邮箱=:email ";
		List<Object> contractList = objDao.findSql(sql, params);
		BigInteger objs = (BigInteger) contractList.get(0);
		int totalCountContractSale = objs.intValue();
		return totalCountContractSale;

	}

	// 获取合同最大编号 SELECT MAX(合同编号) FROM 合同情况
	@Override
	public int gethtMaxNum() {
		String hql = "SELECT  MAX(id) FROM  Contract";
		List<Object> num = objDao.find(hql);
		Integer max_id = (Integer) num.get(0);
		return max_id;
	}

	// 获取单位列表
	@Override
	public List<Contract> getDepartList() {
		String sql = "select 单位  from 合同情况  group by 单位";
		List<Contract> departList = contractDao.findSql(sql);
		return departList;
	}

	// 获取单位列表
	@Override
	public List<CustomerDepart> getDepartListAboutSale(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String hql = "from CustomerDepart where email=:email ";
		List<CustomerDepart> departList = customerDepartDao.find(hql, params);
		return departList;
	}

	@Override
	public void findContracts() {
		String hal = "select 所属公司  from 合同情况  group by 所属公司";
		// String hql = "from Contract group by company";
		List<Object> lis = objDao.findSql(hal);
	}

	// id 自动编号 主键
	@Override
	public CustomerDepart getUserAndDepartId(Integer danweiId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("danweiId", danweiId);
		String hql = "from CustomerDepart where id=:danweiId";
		CustomerDepart c = customerDepartDao.get(hql, params);
		return c;

	}

	@Override
	public String getOperator(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String hql = "from CustomerDepart where email =:email ";
		List<CustomerDepart> customerDepartList = customerDepartDao.find(hql, params);
		String charger = null;
		for (CustomerDepart customerDepart : customerDepartList) {
			charger = customerDepart.getCharger();
		}
		return charger;
	}

	// 销售保存合同
	@Override
	public void updateContractSave(Integer id, String company, String depart, Integer cid, Integer didNum,
			Float contractMoney, String agreementNumber, Date endTime, String agreementText, String remarksText,
			String payMethod, Integer dealConditon, String gangweiXingzhi, String bumenmingcheng, String shengfen,
			String xibaohe, Date submitTime, String danweixingzhi, String yonghuxingzhi) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("company", company);
		params.put("depart", depart);
		params.put("cid", cid);
		params.put("didNum", didNum);
		params.put("contractMoney", contractMoney);
		params.put("agreementNumber", agreementNumber);
		params.put("endTime", endTime);
		params.put("agreementText", agreementText);
		params.put("remarksText", remarksText);
		params.put("payMethod", payMethod);
		params.put("dealConditon", dealConditon);
		params.put("gangweiXingzhi", gangweiXingzhi);
		params.put("bumenmingcheng", bumenmingcheng);
		params.put("shengfen", shengfen);
		params.put("xibaohe", xibaohe);
		params.put("submitTime", submitTime);
		params.put("danweixingzhi", danweixingzhi);
		params.put("yonghuxingzhi", yonghuxingzhi);

		String hql = "update Contract set company=:company,depart=:depart,cid=:cid,didNum=:didNum,contractMoney=:contractMoney,agreementNumber=:agreementNumber,endTime=:endTime,"
				+ "agreementText=:agreementText,remarksText=:remarksText,payMethod=:payMethod,dealConditon=:dealConditon, "
				+ "firstLevel=:gangweiXingzhi,secondLevel=:bumenmingcheng,thirdLevel=:shengfen,fourthLevel=:xibaohe,submitTime=:submitTime,xingzhi=:danweixingzhi,user_property=:yonghuxingzhi where id=:id";
		objDao.executeHql(hql, params);

	}

	@Override
	public void deleteItemPriceByHtid(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String hql = "DELETE FROM ItemPrice WHERE ctid =:id";
		itemPriceDao.executeHql(hql, params);
	}

	@Override
	public void deleteFapiaoByHtid(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String hql = "DELETE FROM FaPiao WHERE hetongNumber =:id";
		fapiaoDao.executeHql(hql, params);
	}

	@Override
	public void deleteFahuoByHtid(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String hql = "DELETE FROM Fahuo WHERE hetongCode =:id";
		fahuoDao.executeHql(hql, params);
	}

	// 获取发件地
	@Override
	public CompanyInfo getFajiandi(String company) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", company);
		String hql = "from CompanyInfo where name=:company";
		CompanyInfo companyInfo = companyInfoDao.get(hql, params);
		return companyInfo;
	}

	//// 根据负责人查出用户单位对象
	@Override
	public CustomerDepart getCustomerDepartByoperator(Integer cid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		String hql = "from CustomerDepart where id=:cid";
		CustomerDepart customerDepart = customerDepartDao.get(hql, params);
		return customerDepart;
	}

	@Override
	public OrganizationStructure getOrgStructure(int saleId) {
		//	String hql = "from RenshiUserName where id=:saleId";
		//	String hql2 = "(`username` JOIN `组织结构图` ON ((`username`.`部门ID` = `组织结构图`.`id`)) as userOrag";
		//	RenshiUserName renshiUserName = renshiUserNameDao.get(hql, params);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("saleId", saleId);
		String hql = "from UserName where ID=:saleId";
		UserName userName = userNameDao.get(hql, params);
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("orgId", userName.getDepartmentId());
		String hql2 = "from OrganizationStructure where id=:orgId";
		OrganizationStructure organizationStructure = organizationStructureDao.get(hql2, params2);
		return organizationStructure;
	}
	
	@Override
	public OrganizationStructure getOrgStructureById(int orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		String hql2 = "from OrganizationStructure where id=:orgId";
		OrganizationStructure organizationStructure = organizationStructureDao.get(hql2, params);
		return organizationStructure;
	}

	@Override
	public List<Object> getAllProduct() {
		String sql = "select 字段1 from 产品名称  ";
		List<Object> productList = objDao.findSql(sql);
		return productList;
	}

	@Override
	public ContractVO getContractVObyhtid(Integer contractId) {
		ContractVO contractVO = contractVODao.get(ContractVO.class, contractId);
		return contractVO;
	}

	/*
	 * @Override public void addErrorInfo(String shuxingming, String errorInfo,
	 * Integer contractId) { Map<String,Object> params = new
	 * HashMap<String,Object>(); if(shuxingming.equals("company")){
	 * params.put("errorInfo", errorInfo); params.put("contractId", contractId);
	 * String hql =
	 * "insert into ContractVO ()values id=:contractId,company=:errorInfo";
	 * contractVODao.executeHql(hql, params); } }
	 */
	@Override
	public void addErrorInfo(ContractVO contractVO) {
		try {
			contractVODao.save(contractVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ContractVO getContractVOByid(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String hql = "from ContractVO where id=:id";
		ContractVO contractVO = contractVODao.get(hql, params);
		return contractVO;
	}

	@Override
	public Customer getyonghuById(Integer id2) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id2", id2);
		String hql = "from Customer where id=:id2";
		Customer customer = customerDao.get(hql, params);
		return customer;
	}

	@Override
	public void updateContractXingzheng(String yinhuashui,Float huaizhangAmount,
			Integer contractId, String guidangNum,String kuaijifenlei,Date dengjiTime2) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("yinhuashui", yinhuashui);
		params.put("huaizhangAmount", huaizhangAmount);
		params.put("contractId", contractId);
		params.put("guidangNum", guidangNum);
		params.put("kuaijifenlei", kuaijifenlei);
		params.put("dengjiTime2", dengjiTime2);
		String hql = "update Contract set tiehuaStatus=:yinhuashui,year=:huaizhangAmount,guidangNum=:guidangNum,kuaijifenlei=:kuaijifenlei,dengjiTime=:dengjiTime2 where id=:contractId";
		objDao.executeHql(hql, params);

	}

	// 获取单位对应代码
	@Override
	public String getdanweicode(String company) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", company);
		String sql = "select code from 公司代码  where 公司代码 =:company";
		List<Object> findSql = objDao.findSql(sql, params);
		String code = (String) findSql.get(0);
		return code;
	}

	@Override
	public void deleteErrorInfo(int contractId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		String hql = "DELETE FROM ContractVO WHERE id =:contractId";
		contractVODao.executeHql(hql, params);
	}

	// 获取归档最大编号
	@Override
	public String getguidangMaxNum(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code + "%");
		// String sql = "select code from 公司代码 where 公司代码 =:company";
		// SELECT 归档编号 FROM 合同情况 WHERE 归档编号 LIKE 'dx%' ORDER BY 归档编号 DESC
		String sql = "select 归档编号  from 合同情况  where 归档编号  like :code order by 归档编号  desc";
		List<Object> findSql = objDao.findSql(sql, params);
		String guidangMaxNum = (String) findSql.get(0);
		return guidangMaxNum;

	}

	@Override
	public UserName getTelephoneByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String hql = "from UserName where email=:email";
		// String hql ="FROM ItemPrice where ctid=:htid";
		UserName userName = userNameDao.get(hql, params);
		return userName;

	}

	// 更新回款金额和日期
	@Override
	public void updateHuikuanAndDate(Integer contractId, String total, Date latestDate,Float totalKaiPiao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		params.put("total", total);
		params.put("latestDate", latestDate);
		params.put("totalKaiPiao", totalKaiPiao);
		String hql = "update Contract set receivedAmount=:total,receiveTime=:latestDate,kaipiaoMoney=:totalKaiPiao where id=:contractId";
		objDao.executeHql(hql, params);
	}

	@Override
	public void updateDealCondition(Integer contractId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("contractId", contractId);
		String sql = "UPDATE 合同情况 SET 处理状态=3 WHERE 合同编号=:contractId";
		contractDao.executeSql(sql, params);
	}

	@Override
	public List<Area> getZhanghu(String substringCompany) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("substringCompany", substringCompany + "%");
		String hql = "from Area where area_name like :substringCompany";
		List<Area> areaList = areaDao.find(hql, params);
		return areaList;
	}

	@Override
	public List<Object> getKaipiaoOption(String company) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", "%"+company+")");
		params.put("company2", "%"+company+"）");
		String sql = "select 字段1 from 发票名称  where 字段1 like :company or 字段1 like :company2";
		List<Object> kaipiaoOptionList = objDao.findSql(sql, params);
		return kaipiaoOptionList;
	}
	
	@Override
	public List<Object> getKaipiaoOptionSearch(String company,String pinmingSearch) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company", "%"+company+")");
		params.put("company2", "%"+company+"）");
		params.put("pinmingSearch", "%"+pinmingSearch+"%");
		String sql = "select 字段1 from 发票名称  where (字段1 like :company or 字段1 like :company2) and 字段1 like :pinmingSearch";
		List<Object> kaipiaoOptionList = objDao.findSql(sql, params);
		return kaipiaoOptionList;
	}

	@Override
	public void updateContractGuidangnum(String guidangDate, String guidangCode2, Integer contractId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guidangDate", guidangDate);
		params.put("contractId", contractId);
		params.put("guidangCode2", guidangCode2);  
		String hql = "update Contract set guidangDate=:guidangDate,guidangCode=:guidangCode2 where id=:contractId";
		objDao.executeHql(hql, params);
	}
 

	@Override
	public void updateContractGaiyao(Integer ctid, String htgaiyaofinal) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ctid", ctid);
		params.put("htgaiyaofinal", htgaiyaofinal);
		String hql = "update Contract set agreementText=:htgaiyaofinal where id=:ctid";
		objDao.executeHql(hql, params);
	}

	@Override
	public List<Object> getCompanyInfo() {
		String sql = "select name from company";
		List<Object> companyList = objDao.findSql(sql);
		return companyList;
		
	}

	@Override
	public Contract getCopyContract(int copyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("copyId", copyId);
		String hql = "from Contract where copyId=:copyId and dealConditon!=-1 and type=2";
		Contract contract = contractDao.get(hql, params);
		return contract;
	}
	
	@Override
	public List<Customer> getCustomerList() {
		String hql = "from Customer";
		List<Customer> customerList =  customerDao.find(hql);
		return customerList;
	}
	
	//分页查询母单位	
	@Override
	public List<Customer> getCustomerList(Integer id, String remarks, String city, String province, String xingzhi, String customerName, String charger,int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select distinct b.自动编号, b.用户名称, b.曾用名称, b.代理公司, b.性质, b.省份, b.城市, b.备注  FROM 用户列表 b left join 用户单位 a on a.单位编号=b.自动编号  where 1=1 ");
		if(id!=null){
			sql.append("and b.自动编号=:id ");
			params.put("id", id);
		}
		if(remarks!=null&&!"".equals(remarks)){
			sql.append("and b.备注 like :remarks ");
			params.put("remarks", "%"+remarks+"%");
		}
		if(city!=null&&!"".equals(city)){
			sql.append("and b.城市 like :city ");
			params.put("city", "%"+city+"%");
		}
		if(province!=null&&!"".equals(province)){
			sql.append("and b.省份 like :province ");
			params.put("province", "%"+province+"%");
		}
		if(xingzhi!=null&&!"".equals(xingzhi)){
			sql.append("and b.性质 like :xingzhi ");
			params.put("xingzhi", "%"+xingzhi+"%");
		}
		if(customerName!=null&&!"".equals(customerName)){
			sql.append("and b.用户名称 like :customerName ");
			params.put("customerName", "%"+customerName+"%");
		}
		if(charger!=null&&!"".equals(charger)){
			sql.append("and a.负责人 like :charger ");
			params.put("charger", "%"+charger+"%");
		}
		sql.append(" order by b.自动编号 desc limit :start, :size ");
		params.put("start", (page-1)*size);
		params.put("size", size);
	//	String hql = "select distinct b.* FROM 用户单位 a left join 用户列表 b on a.单位编号=b.自动编号  where b order by b.id desc limit :start, :size";
		List<Customer> customerList = customerDao.findSql(sql.toString(), params);
		return customerList;
	}
	
	@Override
	public int getTotalCountCustomer(Integer id, String remarks, String city, String province, String xingzhi, String customerName, String charger) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder("select COUNT(distinct b.自动编号) FROM 用户列表 b left join 用户单位 a on a.单位编号=b.自动编号  where 1=1 ");
		if(id!=null){
			sql.append("and b.自动编号=:id ");
			params.put("id", id);
		}
		if(remarks!=null&&!"".equals(remarks)){
			sql.append("and b.备注 like :remarks ");
			params.put("remarks", "%"+remarks+"%");
		}
		if(city!=null&&!"".equals(city)){
			sql.append("and b.城市 like :city ");
			params.put("city", "%"+city+"%");
		}
		if(province!=null&&!"".equals(province)){
			sql.append("and b.省份 like :province ");
			params.put("province", "%"+province+"%");
		}
		if(xingzhi!=null&&!"".equals(xingzhi)){
			sql.append("and b.性质 like :xingzhi ");
			params.put("xingzhi", "%"+xingzhi+"%");
		}
		if(customerName!=null&&!"".equals(customerName)){
			sql.append("and b.用户名称 like :customerName ");
			params.put("customerName", "%"+customerName+"%");
		}
		if(charger!=null&&!"".equals(charger)){
			sql.append("and a.负责人 like :charger ");
			params.put("charger", "%"+charger+"%");
		}
		List<Object> customerList = objDao.findSql(sql.toString(), params);
		BigInteger objs = (BigInteger) customerList.get(0);
		return objs.intValue();

	}

	// 添加母单位
	@Override
	public void addCustomer(Customer customer) {
		try {
			customerDao.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 删除母单位
	@Override
	public void deleteCustomer(Integer customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		String sql1 = "delete from 用户单位  WHERE 单位编号= :customerId";
		itemPriceDao.executeSql(sql1, params);
		Customer customer = new Customer();
		customer.setId(customerId);
		customerDao.delete(customer);
	}
	
	// 更新母单位
	@Override
	public void updateCustomer(Customer customer) {
		try {
			customerDao.update(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询母单位
	@Override
	public Customer getCustomer(Integer id) {
		Customer customer = customerDao.get(Customer.class, id);
		return customer;
	}
	
	// 添加子单位
	@Override
	public void addCustomerDepart(CustomerDepart customerDepart) {
		try {
			customerDepartDao.save(customerDepart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 删除子单位
	@Override
	public void deleteCustomerDepart(Integer id) {
		CustomerDepart customerDepart = new CustomerDepart();
		customerDepart.setId(id);
		customerDepartDao.delete(customerDepart);
	}
	
	// 更新子单位
	@Override
	public void updateCustomerDepart(CustomerDepart customerDepart) {
		try {
			customerDepartDao.update(customerDepart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询子单位
	@Override
	public CustomerDepart getCustomerDepart(Integer id) {
		CustomerDepart customerDepart = customerDepartDao.get(CustomerDepart.class, id);
		return customerDepart;
	}


	@Override
	public List<CustomerDepart> getCustomerDepartListByDid(Integer dId, int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dId", dId);
		String hql = "from CustomerDepart where dId=:dId order by id desc";
		List<CustomerDepart> CustomerDepartList = customerDepartDao.find(hql, params, page, size);
		return CustomerDepartList;
	}
	
	@Override
	public int getTotalCountCustomerDepart(Integer dId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dId", dId);
		String hql = "select count(*) from CustomerDepart where dId=:dId ";
		long count = customerDepartDao.count(hql, params);
		int count1 = Integer.parseInt(String.valueOf(count)); 
		return count1;
	}

	@Override
	public List<Object> getxingzhiList() {
		String sql = "select 名称 from 单位性质";
		List<Object> customerList = objDao.findSql(sql);
		return customerList;
	}
	
	@Override
	public UserName getUseInfo(String email) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String hql = "from UserName where email=:email ";
		UserName userName = userNameDao.get(hql, params);
		return userName;
	}

	
}