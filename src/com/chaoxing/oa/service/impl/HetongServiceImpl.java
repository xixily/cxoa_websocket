//package com.chaoxing.oa.service.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.chaoxing.oa.dao.BaseHetongDaoI;
//import com.chaoxing.oa.entity.page.common.Page;
//import com.chaoxing.oa.entity.page.hetong.PCompanyInfo;
//import com.chaoxing.oa.entity.page.hetong.PContract;
//import com.chaoxing.oa.entity.page.hetong.PCustomer;
//import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
//import com.chaoxing.oa.entity.page.hetong.PFahuo;
//import com.chaoxing.oa.entity.page.hetong.PFapiao;
//import com.chaoxing.oa.entity.page.hetong.PItemPrice;
//import com.chaoxing.oa.entity.sqlpo.CompanyInfo;
//import com.chaoxing.oa.entity.sqlpo.Contract;
//import com.chaoxing.oa.entity.sqlpo.FaPiao;
//import com.chaoxing.oa.entity.sqlpo.Fahuo;
//import com.chaoxing.oa.service.HetongService;
//import com.chaoxing.oa.system.SysConfig;
//import com.chaoxing.oa.util.SqlHelper;
//@Service("hetongService")
//public class HetongServiceImpl implements HetongService {
//	@Autowired
//	private BaseHetongDaoI<Fahuo> fahuoDao;
//	@Autowired
//	private BaseHetongDaoI<FaPiao> fapiaoDao;
//	@Autowired
//	private BaseHetongDaoI<Contract> contractDao;
//	@Autowired
//	private BaseHetongDaoI<Object> objDao;
//	@Autowired
//	private BaseHetongDaoI<CompanyInfo> companyInfoDao;
//	
//	@Override
//	public Map<String, Object> findFahuo(PFahuo queryForm, Page page, int isExport) {
//		StringBuffer hql = new StringBuffer("from Fahuo t where");
//		Map<String,Object> params = new HashMap<String, Object>();
//		try {
//			hql.append(SqlHelper.prepareAndSql(queryForm, params, true));
//		}catch(Exception e){
//			hql.append("1=1 "); 
//		}
//		hql.append(" and postMethod=:postMethod");
//		params.put("postMethod", "顺丰");
//		List<Fahuo> fahuos = null;
//		List<PFahuo> pfahuos = new ArrayList<PFahuo>();
//		Map<String, Object> result = new HashMap<String, Object>();
////		addConditions(queryForm, hql, params);
//		String sort = "id";
//		String order = SysConfig.DESC;
//		if(page.getSort() != null){
//			sort = page.getSort();
//			if(page.getOrder() != null){
//				order = page.getOrder();
//			}
//		}
//		long count = getHetongCount(hql.toString(),params);
//		int intPage = 0;
//		int pageSize = 30000;//最多导出30000条数据
//		if(isExport == 0){
//			intPage = (page.getPage() == 0) ? 1 : page.getPage();
//			pageSize = (page.getRows() == 0) ? 100 : page.getRows();
//		}
//		hql.append(" order by t." + sort + " " + order);
//		fahuos = fahuoDao.find(hql.toString(), params, intPage, pageSize);
//		for (Fahuo fahuo : fahuos) {
//			PFahuo pfahuo = new PFahuo();
//			BeanUtils.copyProperties(fahuo, pfahuo);
//			pfahuos.add(pfahuo);
//		}
//		result.put("rows", pfahuos);
//		result.put("total", count);
//		return result;
////		return pfahuos;
//	}
//
//	private long getHetongCount(String hql, Map<String, Object> params) {
//		StringBuffer hqll = new StringBuffer("select count(*) from Fahuo t where ");
//		hqll.append(hql.split("where")[1]);
//		return fahuoDao.count(hqll.toString(), params);
//	}
//	@Override
//	public Map<String, Object> findFapiao(PFapiao pfapiao, int isExport) {
//		StringBuffer hql = new StringBuffer("from FaPiao t where t.money > 0 ");
//		Map<String,Object> params = new HashMap<String, Object>();
//		List<FaPiao> fapiaos = null;
//		List<PFapiao> pfapiaos = new ArrayList<PFapiao>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		if(pfapiao.getCompany()!=null&&!pfapiao.getCompany().equals("")){
//			hql.append(" and t.company like :company");
//			params.put("company", "%" + pfapiao.getCompany() + "%");
//		}
//		if(pfapiao.getDepartMement()!=null&&!pfapiao.getDepartMement().equals("")){
//			hql.append(" and t.departMement like :departMement");
//			params.put("departMement", "%" + pfapiao.getDepartMement() + "%");
//		}
//		if(pfapiao.getType()!=null&&!pfapiao.getType().equals("")){
//			hql.append(" and t.type=:type");
//			params.put("type", pfapiao.getType());
//		}
//		if(pfapiao.getQueryStatus()!=null&&!pfapiao.getQueryStatus().equals("")){
//			hql.append(" and t.queryStatus=:queryStatus");
//			params.put("queryStatus", pfapiao.getQueryStatus());
//		}
////		addConditions(queryForm, hql, params);
//		String sort = "id";
//		String order = SysConfig.DESC;
//		if(pfapiao.getSort() != null){
//			sort = pfapiao.getSort();
//			if(pfapiao.getOrder() != null){
//				order = pfapiao.getOrder();
//			}
//		}
//		long count = getFapiaoCount(hql.toString(),params);
//		int intPage = 0;
//		int pageSize = 30000;//最多导出30000条数据
//		if(isExport == 0){
//			intPage = (pfapiao == null || pfapiao.getPage() == 0) ? 1 : pfapiao.getPage();
//			pageSize = (pfapiao == null || pfapiao.getRows() == 0) ? 100 : pfapiao.getRows();
//		}
//		hql.append(" order by t." + sort + " " + order);
//		fapiaos = fapiaoDao.find(hql.toString(), params, intPage, pageSize);
//		for (FaPiao fapiao : fapiaos) {
//			PFapiao pfapiao1 = new PFapiao();
//			BeanUtils.copyProperties(fapiao, pfapiao1);
//			pfapiaos.add(pfapiao1);
//		}
//		result.put("rows", pfapiaos);
//		result.put("total", count);
//		return result;
//	}
//	public long getFapiaoCount(String hql, Map<String, Object> params){
//		StringBuffer hqll = new StringBuffer("select count(*) from FaPiao t where ");
//		hqll.append(hql.split("where")[1]);
//		return objDao.count(hqll.toString(), params);
//	}
//	@Override
//	public int updateFapiao(PFapiao pfapiao) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("queryStatus", pfapiao.getQueryStatus());
//		params.put("id", pfapiao.getId());
//		StringBuffer hql = new StringBuffer("update FaPiao t set t.queryStatus = :queryStatus where id = :id");
//		int i = fapiaoDao.executeHql(hql.toString(), params);
//		return i;
//	}
//	@Override
//	public PFahuo getFahuo(Integer orderId) {
//		Fahuo fahuo = fahuoDao.get(Fahuo.class, orderId);
//		PFahuo pfahuo = new PFahuo();
//		BeanUtils.copyProperties(fahuo, pfahuo);
//		return pfahuo;
//	}
//	@Override
//	public Map<String, Object> findCustomers(PCustomer pCustomer, int isExport) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public PCustomer getCustomer(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public int addCustomer(PCustomer pCustomer) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int updateCustomer(PCustomer pCustomer) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteCustomer(PCustomer pCustomer) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public Map<String, Object> findCustomerDepart(PCustomerDepart pCustomerDepart, int isExport) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public PCustomerDepart getCustomerDepart(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public int addCustomerDepart(PCustomerDepart pCustomerDepart) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int updateCustomerDepart(PCustomerDepart pCustomerDepart) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteCustomerDepart(PCustomerDepart pCustomerDepart) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public Map<String, Object> findContracts(PContract pConstract, int isExport) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public PContract getContract(Integer hetongCode) {
//		Contract contract = contractDao.get(Contract.class, hetongCode);
//		PContract pcontract = null;
//		if(null != contract){
//			pcontract = new PContract();
//			BeanUtils.copyProperties(contract, pcontract);
//		}
//		return pcontract;
//	}
//	@Override
//	public int addContract(PContract pConstract) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int updateContract(PContract pConstract) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteContract(PContract pConstract) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public PFapiao getFapiao(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public int addFapiao(PFapiao pfapiao) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteFapiao(PFapiao pfapiao) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public Map<String, Object> findItemPrice(PItemPrice pItemPrice, int isExport) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public PItemPrice getItemPrice(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public int updateItemPrice(PItemPrice pItemPrice) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int addItemPrice(PItemPrice pItemPrice) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteItemPrice(PItemPrice pItemPrice) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int addFahuo(PFahuo pfahuo) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int deleteFahuo(PFahuo pfahuo) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int updateFahuo(PFahuo pfahuo) {
//		Fahuo fahuo = new Fahuo();
//		BeanUtils.copyProperties(pfahuo, fahuo);
//		try {
//			fahuoDao.update(fahuo);
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//	@Override
//	public PCompanyInfo getCompanyInfo(int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PCompanyInfo getCompanyInfoByName(String company) {
//		CompanyInfo companyInfo = null;
//		PCompanyInfo pcompany = null;
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("company", company);
//		List<CompanyInfo> comlists = companyInfoDao.find("from CompanyInfo t where t.name=:company",params);
//		if(null!=comlists && comlists.size()>0){
//			companyInfo = comlists.get(0);
//			pcompany = new PCompanyInfo();
//			BeanUtils.copyProperties(companyInfo, pcompany);
//		}
//		return pcompany;
//	}
//}