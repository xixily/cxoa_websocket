package com.chaoxing.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.BaseDaoI;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.hetong.PContract;
import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.entity.page.hetong.PItemPrice;
import com.chaoxing.oa.entity.page.pub.hetong.Cells;
import com.chaoxing.oa.entity.page.pub.hetong.PFapiaoDetail;
import com.chaoxing.oa.entity.page.pub.hetong.PGuidanceView;
import com.chaoxing.oa.entity.page.pub.hetong.PYingshou;
import com.chaoxing.oa.entity.page.pub.hetong.PucfView;
import com.chaoxing.oa.entity.page.pub.hetong.UserList;
import com.chaoxing.oa.entity.po.hetong.Contract;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.pub.UcfView;
import com.chaoxing.oa.service.PubHetongService;
import com.chaoxing.oa.util.system.DateUtil;
import com.chaoxing.oa.util.system.ResourceUtil;
import com.chaoxing.oa.util.system.SqlHelper;

@Service(value="pubHetongService")
public class PubHetongServiceImpl implements PubHetongService {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BaseDaoI<Object> objectDao;
	@Autowired
	private BaseDaoI<RenshiUserName> rusernameDao;
	@Autowired
	private BaseDaoI<CustomerDepart> cdepartDao;
	@Autowired
	private BaseDaoI<UcfView> ucfViewDao;
	@Autowired
	private BaseDaoI<ItemPrice> itemPriceDao;
	@Autowired
	private BaseDaoI<FaPiao> fapiaoDao;
	@Autowired
	private BaseDaoI<Contract> contractDao;
	@Autowired
	private BaseDaoI<ItemPrice> itemsDao;
	
	@Override
	public void findCellcoreTotal(String email) {
		
	}
	
	@Override
	public List<PGuidanceView> findCellCoresCount(String email) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.细胞核,t1.细胞核邮箱, t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 细胞核邮箱,细胞核,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 指导邮箱=:gemail "
				+ "GROUP BY 细胞核邮箱) t1 LEFT JOIN "
				+ "(SELECT 细胞核邮箱,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 指导邮箱=:gemail AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 细胞核邮箱) t2 ON t1.细胞核邮箱 = t2.细胞核邮箱  LEFT JOIN "
				+ "(SELECT 细胞核邮箱,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 指导邮箱=:gemail AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 细胞核邮箱) t3 ON t1.细胞核邮箱 = t3.细胞核邮箱 LEFT JOIN "
				+ "(SELECT 细胞核邮箱,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 指导邮箱=:gemail "
				+ "GROUP BY 细胞核邮箱) t4 ON t1.细胞核邮箱=t4.细胞核邮箱";
		params.put("gemail", email);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<PGuidanceView> pgvs = new ArrayList<PGuidanceView>();
		String cellcore;
		String cemail;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				PGuidanceView pgv = new PGuidanceView();
				cellcore = null!=obs[0] ? String.valueOf(obs[0]) : "";
				cemail = null!=obs[1] ? String.valueOf(obs[1]) : "";
				lastyear = null!=obs[2] ? (BigDecimal)obs[2] : new BigDecimal(0);
				thisyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				yingshou = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				pgv.setCellCore(cellcore);
				pgv.setCemail(cemail);
				pgv.setLastYear(lastyear);
				pgv.setThisYear(thisyear);
				pgv.setYingshou(yingshou);
				pgv.setGemail(email);
				pgv.setEmail(cemail);
				pgvs.add(pgv);
			}
		}
		return pgvs;
	}

	@Override
	public List<Cells> findCoreCellsCount(String cemail) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.负责人,t1.邮箱, t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 邮箱,负责人,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 细胞核邮箱=:cemail "
				+ "GROUP BY 邮箱) t1 LEFT JOIN "
				+ "(SELECT 邮箱,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 细胞核邮箱=:cemail AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 邮箱) t2 ON t1.邮箱 = t2.邮箱  LEFT JOIN "
				+ "(SELECT 邮箱,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 细胞核邮箱=:cemail AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 邮箱) t3 ON t1.邮箱 = t3.邮箱 LEFT JOIN "
				+ "(SELECT 邮箱,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 细胞核邮箱=:cemail "
				+ "GROUP BY 邮箱) t4 ON t1.邮箱=t4.邮箱";
		params.put("cemail", cemail);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<Cells> cells = new ArrayList<Cells>();
		String username;
		String email;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				Cells cell = new Cells();
				username = null!=obs[0] ? String.valueOf(obs[0]) : "";
				email = null!=obs[1] ? String.valueOf(obs[1]) : "";
				lastyear = null!=obs[2] ? (BigDecimal)obs[2] : new BigDecimal(0);
				thisyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				yingshou = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				cell.setCemail(cemail);
				cell.setCharger(username);
				cell.setEmail(email);
				cell.setLastYear(lastyear);
				cell.setThisYear(thisyear);
				cell.setYingshou(yingshou);
				cells.add(cell);
			}
		}
		return cells;
	}
	

	@Override
	public Map<String, Object> findUcfView(Page page, PucfView pucf) {
		StringBuffer hql = new StringBuffer("from UcfView t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PucfView> pucfs = new ArrayList<PucfView>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(pucf, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<UcfView> ucfs = ucfViewDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<UcfView> it = ucfs.iterator();
			while(it.hasNext()){
				UcfView ucf = it.next();
				PucfView puc = new PucfView();
				BeanUtils.copyProperties(ucf, puc);
				pucfs.add(puc);
			}
		}catch(Exception e){
			log.error("pubHetongService[findUcfView] has a error with message:[" + e + "]");
		}
		result.put("rows", pucfs);
		result.put("total", count);
		return result;
	}

	@Override
	public List<UserList> findUserListCount(String email, String charger) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT t1.用户名称,t1.自动编号,单位编号,t2.去年 ,t3.今年, (t4.合同款 - t1.回款) 应收 FROM"
				+ "(SELECT 自动编号,单位编号,用户名称,SUM(IFNULL(回款情况,0)) 回款 FROM 用户合同发票 "
				+ "WHERE 邮箱=:email "
				+ "GROUP BY 自动编号) t1 LEFT JOIN "
				+ "(SELECT 自动编号,SUM(IFNULL(回款情况,0)) 去年 FROM 用户合同发票 "
				+ "WHERE 邮箱=:email AND YEAR(汇款时间)='" + ResourceUtil.getKey("lastyear") + "' "
				+ "GROUP BY 自动编号) t2 ON t1.自动编号 = t2.自动编号  LEFT JOIN "
				+ "(SELECT 自动编号,SUM(IFNULL(回款情况,0)) 今年 FROM 用户合同发票 "
				+ " WHERE 邮箱=:email AND YEAR(汇款时间)='"+ ResourceUtil.getKey("thisyear") + "' "
				+ "GROUP BY 自动编号) t3 ON t1.自动编号 = t3.自动编号 LEFT JOIN "
				+ "(SELECT 自动编号,(SUM(IFNULL(合同金额,0)) - SUM(IFNULL(年代,0)) - SUM(IFNULL(开票总金额,0))) 合同款 FROM 用户合同 "
				+ "WHERE 邮箱=:email "
				+ "GROUP BY 自动编号) t4 ON t1.自动编号=t4.自动编号";
		params.put("email", email);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<UserList> uds = new ArrayList<UserList>();
		String dname;
		Integer dId;
		Integer dwId;
		BigDecimal lastyear;
		BigDecimal thisyear;
		BigDecimal yingshou;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=6){
				UserList ud = new UserList();
				dname = null!=obs[0] ? String.valueOf(obs[0]) : "";
				dId = null!=obs[1] ? (Integer)obs[1] : -1;
				dwId = null!=obs[2] ? (Integer)obs[2] : -1;
				lastyear = null!=obs[3] ? (BigDecimal)obs[3] : new BigDecimal(0);
				thisyear = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				yingshou = null!=obs[5] ? (BigDecimal)obs[5] : new BigDecimal(0);
				ud.setDname(dname);
				ud.setEmail(email);
				ud.setCharger(charger);
				ud.setAutoCode(dId);
				ud.setDwCode(dwId);
				ud.setLastYear(lastyear);
				ud.setThisYear(thisyear);
				ud.setYingshou(yingshou);
				uds.add(ud);
			}
		}
		return uds;
	}

	@Override
	public List<PFapiaoDetail> findUserContractsCount(Integer autoCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "SELECT 负责人,自动编号,汇款时间,发票品名,回款情况 FROM 用户合同发票  WHERE 自动编号= :autoCode";
		params.put("autoCode", autoCode);
		List<Object> oblist = objectDao.findSql(sql, params);
		Iterator<Object> it = oblist.iterator();
		List<PFapiaoDetail> pfs = new ArrayList<PFapiaoDetail>();
		String charger;
		Integer dId;
		Date huikuanDate;
		String pinming;
		BigDecimal huikuan;
		while(it.hasNext()){
			Object[] obs = (Object[]) it.next();
			if(obs.length>=5){
				PFapiaoDetail pf = new PFapiaoDetail();
				try{
					charger = null!=obs[0] ? String.valueOf(obs[0]) : "";
					dId = null!=obs[1] ? (Integer)obs[1] : -1;
					huikuanDate = null!=obs[2] ? (Date)obs[2] : null;
					pinming = null!=obs[3] ? (String)obs[3] : "";
					huikuan = null!=obs[4] ? (BigDecimal)obs[4] : new BigDecimal(0);
				}catch(Exception e){
					continue;
				}
				pf.setCharger(charger);
				pf.setAutoCode(dId);
				pf.setHuikuanTime(DateUtil.format(huikuanDate));
				pf.setPinming(pinming);
				pf.setHuikuan(huikuan);
				pfs.add(pf);
			}
		}
		return pfs;
	}

	@Override
	public void findContractFapiaoCount(Integer id) {
		
	}
	
	

	@Override
	public List<PYingshou> findYingshouCount(Integer id) {
		if(null==id || 0==id) return null;
		PContract pcontract = new PContract();
		pcontract.setCid(id);
		List<PContract> pcontracts = (List<PContract>) findContracts(pcontract).get("rows");
		List<PFapiao> pfapiaos = findUserFapiao(new UserList(id));
		List<PItemPrice> pitems = findUserItemprice(id);
		List<PYingshou> plis = new ArrayList<PYingshou>();
		Iterator<PContract> it = pcontracts.iterator();
		Iterator<PFapiao> it2;
		Iterator<PItemPrice> it3;
		PContract pct;
		PYingshou pys;
		Integer ctid;
		StringBuffer pinming;
		Float huikuan,htMoney;
		while(it.hasNext()){
			pys = new PYingshou();
			pct = it.next();
			ctid = pct.getId();
			htMoney = pct.getContractMoney();
			huikuan = 0f;
			pinming = new StringBuffer("");
			pys.setCid(pct.getId());
			pys.setHtmoney(pct.getContractMoney());
			pys.setKaipiao(pct.getKaipiaoMoney());
			pys.setSignedTime(DateUtil.format(pct.getDengjiTime(),"yyyy-MM-dd"));
			PFapiao pfa;
			it2 = pfapiaos.iterator();
			it3 = pitems.iterator();
			//合同发票回款
			while(it2.hasNext()){
				pfa = it2.next();
				if(ctid.equals(pfa.getHetongNumber())){
					huikuan += (null!=pfa.getHuiKuan()?pfa.getHuiKuan():0);
					it2.remove();
				}
			}
			PItemPrice pitem;
			while(it3.hasNext()){
				pitem = it3.next();
				if(ctid.equals(pitem.getCtid())){
					pinming.append(pitem.getName()+"，");
					it3.remove();
				}
			}
			if(pinming.toString().length()>0){
//				System.out.println("pinming进来了：" + pinming.toString());
				pinming.delete(pinming.length()-1, pinming.length());
//				System.out.println("pinming删除后:" + pinming.toString());
//				pinming.substring(0, pinming.length()-1);
			}
			pys.setHuikuan(huikuan);
			pys.setPinming(pinming.toString());
			pys.setYingshou((htMoney-huikuan));
			plis.add(pys);
		}
		return plis;
	}

	@Override
	public void findUserList() {
		
	}

	@Override
	public Map<String,Object> findContracts(PContract pcontract) {
		Map<String,Object> results = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from Contract t where ");
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			hql.append(SqlHelper.prepareAndSql(pcontract, params, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		params.put("id", pcontract.getId());
		List<Contract> contracts= contractDao.find(hql.toString(),params);
		List<PContract> pcotracts = new ArrayList<PContract>();
		Iterator<Contract> it = contracts.iterator();
		PContract pc = null;
		while(it.hasNext()){
			pc = new PContract();
			BeanUtils.copyProperties(it.next(), pc);
			pcotracts.add(pc);
		}
		results.put("total",getCount(hql.toString(),params));
		results.put("rows", pcotracts);
		return results;
	}
	
	@Override
	public Map<String, Object> findContractFapiaos(Page page, PFapiao pfapiao) {
		StringBuffer hql = new StringBuffer("from FaPiao t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PFapiao> pfapiaos = new ArrayList<PFapiao>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(pfapiao, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<FaPiao> fapiaos = fapiaoDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<FaPiao> it = fapiaos.iterator();
			while(it.hasNext()){
				FaPiao fapiao = it.next();
				PFapiao pf = new PFapiao();
				BeanUtils.copyProperties(fapiao, pf);
				pfapiaos.add(pf);
			}
		}catch(Exception e){
			log.error("pubHetongService[findContractFapiaos] has a error with message:[" + e + "]");
		}
		result.put("rows", pfapiaos);
		result.put("total", count);
		return result;
	}
	

	@Override
	public Map<String, Object> findContractItemPrice(Page page, PItemPrice ptprice) {
		StringBuffer hql = new StringBuffer("from ItemPrice t where ");
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		List<PItemPrice> pitps = new ArrayList<PItemPrice>();
		long count = 0;
		try{
			hql.append(SqlHelper.prepareAndSql(ptprice, params, true));
			int intPage = 0;
			int pageSize = 30000;//最多导出30000条数据
			intPage = (page == null || page.getPage() == 0) ? 1 : page.getPage();
			pageSize = (page == null || page.getRows() == 0 || page.getRows()>500) ? 500 : page.getRows();
			List<ItemPrice> itemPrices = itemPriceDao.find(hql.toString(),params, intPage, pageSize);
			count = getCount(hql.toString(),params);
			Iterator<ItemPrice> it = itemPrices.iterator();
			while(it.hasNext()){
				ItemPrice item = it.next();
				PItemPrice pitem = new PItemPrice();
				BeanUtils.copyProperties(item, pitem);
				pitps.add(pitem);
			}
		}catch(Exception e){
			log.error("pubHetongService[findContractItemPrice] has a error with message:[" + e + "]");
		}
		result.put("rows", pitps);
		result.put("total", count);
		return result;
	}
	

	@Override
	public List<PFapiao> findUserFapiao(UserList userList) {
		String hql = "from FaPiao t where t.hetongNumber in (select t2.id from Contract t2 where t2.cid=:id)";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", userList.getAutoCode());
		List<FaPiao> fapiaos = fapiaoDao.find(hql, params);
		List<PFapiao> pfapiaos = new ArrayList<PFapiao>();
		Iterator<FaPiao> it = fapiaos.iterator();
		while(it.hasNext()){
			FaPiao fp = it.next();
			PFapiao pf = new PFapiao();
			pf.setId(fp.getId());
			pf.setAccount(fp.getAccount());
			pf.setApplicant(fp.getApplicant());
			pf.setCapitalMoney(fp.getCapitalMoney());
			pf.setCompany(fp.getCompany());
			pf.setDate(DateUtil.format(fp.getDate()));
			pf.setDepartMement(fp.getDepartMement());
			pf.setFundType(fp.getFundType());
			pf.setHetongNumber(fp.getHetongNumber());
			pf.setHuiKuan(fp.getHuiKuan().floatValue());
			pf.setMoney(fp.getMoney().floatValue());
			pf.setName(fp.getName());
			pf.setQueryStatus(fp.getQueryStatus());
			pf.setRecorder(fp.getRecorder());
			pf.setRemark(fp.getRemark());
			pf.setRemittanceDate(DateUtil.format(fp.getRemittanceDate(),"yyyy-MM-dd"));
			pf.setType(fp.getType());
			pfapiaos.add(pf);
		}
		return pfapiaos;
	}
	
	@Override
	public List<PItemPrice> findUserItemprice(Integer id) {
		String hql = "from ItemPrice t where t.ctid in (select t2.id from Contract t2 where t2.cid=:id)";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<ItemPrice> itemms = itemsDao.find(hql, params);
		List<PItemPrice> pitems = new ArrayList<PItemPrice>();
		Iterator<ItemPrice> it = itemms.iterator();
		PItemPrice pitm = null;
		while(it.hasNext()){
			pitm = new PItemPrice();
			BeanUtils.copyProperties(it.next(), pitm);
			pitems.add(pitm);
		}
		return pitems;
	}

	@Override
	public PCustomerDepart getUserList(Integer id) {
		PCustomerDepart pcd = new PCustomerDepart();
		CustomerDepart cd = cdepartDao.get(CustomerDepart.class, id);
		BeanUtils.copyProperties(cd, pcd);
		return pcd;
	}

	@Override
	public void getContractDetail(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFapiaoDetail(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PRenshiEmployee getUserByEmai(String email) {
		Map<String,Object> params = new HashMap<String, Object>();
		String hql = "from RenshiUserName t where t.email = :email";
		params.put("email", email);
		List<RenshiUserName> employee = rusernameDao.find(hql, params);
		PRenshiEmployee prenshi = null;
		if(null!=employee && employee.size()>0){
			prenshi = new PRenshiEmployee();
			BeanUtils.copyProperties(employee.get(0), prenshi);
		}
		return prenshi;
	}

	protected long getCount(String hql, Map<String, Object> params) {
		String hqll = "select count(*) from " + hql.split("from")[1];
		return objectDao.count(hqll,params);
	}


}
