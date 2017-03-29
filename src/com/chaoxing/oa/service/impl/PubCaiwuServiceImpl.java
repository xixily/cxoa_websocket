package com.chaoxing.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.pub.caiwu.PKoukuan;
import com.chaoxing.oa.entity.po.caiwu.Baoxiao;
import com.chaoxing.oa.entity.po.caiwu.BaoxiaoStatus;
import com.chaoxing.oa.entity.po.caiwu.KoukuanItem;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.pub.caiwu.BaoxiaoView;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;
import com.chaoxing.oa.util.system.DateUtil;
import com.chaoxing.oa.util.system.SqlHelper;

@Service("publicCaiwuService")
public class PubCaiwuServiceImpl implements PubCaiwuService {
	@Autowired
	private BaseDaoI<Baoxiao> baoxiaoDao;
	@Autowired
	private BaseDaoI<BaoxiaoView> baoxiaoViewDao;
	@Autowired
	private BaseDaoI<Object> objectDao;
	@Autowired
	private BaseDaoI<RenshiUserName> userNameDao;
	@Autowired
	private BaseDaoI<BaoxiaoStatus> bxStatusDao;
	@Autowired
	private BaseDaoI<KoukuanItem> koukuanDao;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public PBaoxiao getBaoxiao(Long id) {
		BaoxiaoView bxv = baoxiaoViewDao.get(BaoxiaoView.class, id);
		PBaoxiao pbx = new PBaoxiao();
		copyE2P(bxv, pbx);
		return pbx;
	}
	
	@Override
	public Map<String, Object> findBaoxiaoByUid(PBaoxiao pbaoxiao, Page page, Integer id) {
		pbaoxiao.setUid(id);
		return findBaoxiao(pbaoxiao, page);
	}

	@Override
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page) {
		List<BaoxiaoView> baoxiaos = new ArrayList<BaoxiaoView>();
		List<PBaoxiao> pbs = new ArrayList<PBaoxiao>();
		Map<String, Object> results = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BaoxiaoView t where ");
		try {
			hql.append(SqlHelper.prepareAndSql(pbaoxiao, params, true));
		} catch (Exception e) {
			System.out.println(e);
		}
		String sort = "id";
		String order = SysConfig.DESC;
		if(page.getSort() != null){
			sort = page.getSort();
			if(page.getOrder() != null){
				order = page.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = null != page.getPage() ? page.getPage():0;
		int pageSize = null!=page.getRows() ? page.getRows():10;
		baoxiaos = baoxiaoViewDao.find(hql.toString(),params,intPage,pageSize);
		Iterator<BaoxiaoView> it = baoxiaos.iterator();
		BaoxiaoView bx = null;
		PBaoxiao pbx = null;
		if(null!=baoxiaos && baoxiaos.size()>0){
			while(it.hasNext()){
				bx = it.next();
				pbx = new PBaoxiao();
				copyE2P(bx, pbx);
				pbs.add(pbx);
			}
		}
		results.put("rows", pbs);
		results.put("total", getCount(hql.toString(),params));
		return results;
	}

	private void copyE2P(BaoxiaoView bx, PBaoxiao pbx) {
		pbx.setId(bx.getId());
		pbx.setUid(bx.getUid());
		pbx.setUsername(bx.getUsername());
		pbx.setEmail(bx.getEmail());
		pbx.setMoney(bx.getMoney());
		pbx.setHuankuan(bx.getHuankuan());
		pbx.setNumber(bx.getNumber());
		pbx.setExplain(bx.getExplain());
		pbx.setBank(bx.getBank());
		pbx.setAccount(bx.getAccount());
		pbx.setApproid(bx.getApproid());
		pbx.setApprover(bx.getApprover());
		pbx.setAproEmail(bx.getAproEmail());
		pbx.setAproTime(DateUtil.format(bx.getAproTime(), "yyyy-MM-dd"));
		pbx.setApproRemark(bx.getApproRemark());
		pbx.setJtime(DateUtil.format(bx.getJtime(), "yyyy-MM-dd"));
		pbx.setKdno(bx.getKdno());
		pbx.setReciverId(bx.getReciverId());
		pbx.setReciveTime(DateUtil.format(bx.getReciveTime(), "yyyy-MM-dd"));
		pbx.setRcRemarks(bx.getRcRemarks());
		pbx.setCheckerId(bx.getCheckerId());
		pbx.setTuipiao(bx.getTuipiao());
		pbx.setCaiwuRemarks(bx.getCaiwuRemarks());
		pbx.setKoujk(bx.getKoujk());
		pbx.setBaoxMoney(bx.getBaoxMoney());
		pbx.setBaoxTime(DateUtil.format(bx.getBaoxTime(), "yyyy-MM-dd"));
		pbx.setStatus(bx.getStatus());
		pbx.setKunhao(bx.getKunhao());
		pbx.setCreateTime(DateUtil.format(bx.getCreateTime(),"yyyy-MM-dd"));
		pbx.setFirstLevel(bx.getFirstLevel());
		pbx.setSecondLevel(bx.getSecondLevel());
		pbx.setThirdLevel(bx.getThirdLevel());
		pbx.setFourthLevel(bx.getFourthLevel());
		pbx.setCellCoreEmail(bx.getCellCoreEmail());
		pbx.setGuidanceEmail(bx.getGuidanceEmail());
	}
	
	@Override
	public Map<String, Object> findBaoxiaoByLeader(PBaoxiao pbaoxiao, Page page, String email) {
		List<BaoxiaoView> baoxiaos = new ArrayList<BaoxiaoView>();
		List<PBaoxiao> pbs = new ArrayList<PBaoxiao>();
		Map<String, Object> results = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BaoxiaoView t WHERE (cellCoreEmail=:email OR guidanceEmail=:email) AND ");
		params.put("email", email);
		try {
			hql.append(SqlHelper.prepareAndSql(pbaoxiao, params, true));
		} catch (Exception e) {
			System.out.println(e);
		}
		String sort = "id";
		String order = SysConfig.DESC;
		if(page.getSort() != null){
			sort = page.getSort();
			if(page.getOrder() != null){
				order = page.getOrder();
			}
		}
		hql.append(" order by t." + sort + " " + order);
		int intPage = null != page.getPage() ? page.getPage():0;
		int pageSize = null!=page.getRows() ? page.getRows():10;
		baoxiaos = baoxiaoViewDao.find(hql.toString(),params,intPage,pageSize);
		Iterator<BaoxiaoView> it = baoxiaos.iterator();
		BaoxiaoView bx = null;
		PBaoxiao pbx = null;
		if(null!=baoxiaos && baoxiaos.size()>0){
			while(it.hasNext()){
				bx = it.next();
				pbx = new PBaoxiao();
				copyE2P(bx, pbx);
				pbs.add(pbx);
			}
		}
		results.put("rows", pbs);
		results.put("total", getCount(hql.toString(),params));
		return results;
	}

	private long getCount(String hql, Map<String, Object> params) {
		String hqll = "select count(*) from " + hql.split("from")[1];
		return objectDao.count(hqll,params);
	}

	@Override
	public Serializable addBaoxiao(PBaoxiao pbaoxiao) {
		Baoxiao baoxiao = new Baoxiao();
		copyP2E(pbaoxiao, baoxiao);
		try {
			return baoxiaoDao.save(baoxiao);
		} catch (Exception e) {
			logger.error("方法[PubCaiwuServiceImpl.addBaoxiao]失败" + e);
		}
		return null;
	}

	private void copyP2E(PBaoxiao pbaoxiao, Baoxiao baoxiao) {
		baoxiao.setId(pbaoxiao.getId());
		baoxiao.setUid(pbaoxiao.getUid());
		baoxiao.setMoney(pbaoxiao.getMoney());
		baoxiao.setHuankuan(pbaoxiao.getHuankuan());
		baoxiao.setNumber(pbaoxiao.getNumber());
		baoxiao.setExplain(pbaoxiao.getExplain());
		baoxiao.setBank(pbaoxiao.getBank());
		baoxiao.setAccount(pbaoxiao.getAccount().replace(" ", ""));
		baoxiao.setApproid(pbaoxiao.getApproid());
		baoxiao.setApprover(pbaoxiao.getApprover());
		baoxiao.setAproEmail(pbaoxiao.getAproEmail());;
		baoxiao.setAproTime((null!=pbaoxiao.getAproTime() ? DateUtil.format(pbaoxiao.getAproTime()) : null));
		baoxiao.setApproRemark(pbaoxiao.getApproRemark());
		baoxiao.setJtime((null!=pbaoxiao.getJtime() ? DateUtil.format(pbaoxiao.getJtime()) : null));
		baoxiao.setKdno(pbaoxiao.getKdno());
		baoxiao.setReciverId(pbaoxiao.getReciverId());
		baoxiao.setReciveTime((null!=pbaoxiao.getReciveTime() ? DateUtil.format(pbaoxiao.getReciveTime()):null));
		baoxiao.setRcRemarks(pbaoxiao.getRcRemarks());
		baoxiao.setCheckerId(pbaoxiao.getCheckerId());
		baoxiao.setCpid(pbaoxiao.getCpid());
		baoxiao.setTuipiao(pbaoxiao.getTuipiao());
		baoxiao.setCaiwuRemarks(pbaoxiao.getCaiwuRemarks());
//		baoxiao.setKoujk(pbaoxiao.getKoujk());
		baoxiao.setBaoxMoney(pbaoxiao.getBaoxMoney());
		baoxiao.setBaoxTime((null!=pbaoxiao.getBaoxTime() ? DateUtil.format(pbaoxiao.getBaoxTime()) : null));
		baoxiao.setStatus(pbaoxiao.getStatus());
		baoxiao.setKunhao(pbaoxiao.getKunhao());
		baoxiao.setCreateTime((null!=pbaoxiao.getCreateTime() ? DateUtil.format(pbaoxiao.getCreateTime()):null));
	}

	@Override
	public long updateBaoxiao(PBaoxiao pBaoxiao) {
		Baoxiao baoxiao = new Baoxiao();
		copyP2E(pBaoxiao, baoxiao);
		try {
			baoxiaoDao.update(baoxiao);
			return 1;
		} catch (Exception e) {
			logger.error("[PubCaiwuServiceImpl][updateBaoxiao]失败：" + e);
			return 0;
		}
	}
	
	@Override
	public int updateSeltBaoxiao(PBaoxiao pbaoxiao) {
		StringBuffer hql = new StringBuffer("update Baoxiao t set");
		Baoxiao baoxiao = new Baoxiao();
		copyP2E(pbaoxiao, baoxiao);
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(SqlHelper.prepareSetSql(baoxiao, params, "t", "uid")).append(" and t.uid=:uid");
		params.put("uid", pbaoxiao.getUid());
		System.out.println(hql);
		return baoxiaoDao.executeHql(hql.toString(), params);
	}

	@Override
	public int deleteBaoxiao(Long id, Integer uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getLastYear(int id) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date lastYear = cal.getTime();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from Baoxiao t where t.uid=:id and t.baoxTime>=:lastYear and t.baoxTime<:thisYear");
		params.put("thisYear", thisYear);
		params.put("lastYear", lastYear);
		params.put("id", id);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(null!=lis && lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}

	@Override
	public Double getLastYear(String email) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date lastYear = cal.getTime();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(baoxMoney) from BaoxiaoView t where (cellCoreEmail=:email OR guidanceEmail=:email) AND t.baoxTime>=:lastYear and t.baoxTime<:thisYear");
		params.put("thisYear", thisYear);
		params.put("lastYear", lastYear);
		params.put("email", email);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(null!=lis && lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}

	@Override
	public Double getThisYear(int id) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from Baoxiao t where t.uid=:id and t.baoxTime>=:thisYear and t.baoxTime<:afterYear");
		params.put("thisYear", thisYear);
		params.put("afterYear", afterYear);
		params.put("id", id);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}

	@Override
	public Double getThisYear(String email) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2016.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from BaoxiaoView t where (cellCoreEmail=:email OR guidanceEmail=:email) AND t.baoxTime>=:thisYear and t.baoxTime<:afterYear");
		params.put("thisYear", thisYear);
		params.put("afterYear", afterYear);
		params.put("email", email);
		List<Object> lis = objectDao.find(hql.toString(), params);
		Double value1 = 0.0d;
		if(lis.size()>0){
			value1 = (Double) ((null!=lis.get(0)) ? lis.get(0):0.0d);
		}
		return value1;
	}
	
	

	@Override
	public Long getBaoxiaoTotal(PBaoxiao pbaoxiao, Date min, Date max) {
		Map<String, Object> params = new HashMap<String, Object>();
//		String sql = "select sum(t.baoxMoney) from BaoxiaoView t where ";
		StringBuffer hql = new StringBuffer("select sum(t.baoxMoney) from BaoxiaoView t where ");
		try {
			hql.append(SqlHelper.prepareAndSql(pbaoxiao, params, true));
			List<Object> bxvs = objectDao.find(hql.toString(), params);
			if(null!=bxvs && bxvs.size()>0){
				Double value = (Double) bxvs.get(0);
				if(null != value){
					return value.longValue();
				}else{
					return 0l;
				}
				
			}
		} catch (Exception e) {
			logger.error("PubCaiwuServiceImpl.getBaoxiaoTotal:" + e);
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 合同
	 */
	@Override
	public void findCellsByEmail(String email) {
		// TODO Auto-generated method stub
//		StringBuffer hql = new StringBuffer("select id from RenshiUserName where guidanceEmail=:gemail");
//		String sql = "select id from RenshiUserName where guidanceEmail=:gemail";
//		Map<String,Object> params = new HashMap<String, Object>();
//		List<Object> olist = objectDao.find(hql.toString(),params, 0, 1000);
//		Iterator<Object> it = olist.iterator();
//		while(it.hasNext()){
//			Object id = it.next();
//		}
	}

	@Override
	public PRenshiEmployee getEmployee(Integer uid) {
		RenshiUserName username = userNameDao.get(RenshiUserName.class, uid);
		PRenshiEmployee puser = new PRenshiEmployee();
		BeanUtils.copyProperties(username, puser);
		return puser;
	}

	/**
	 * @param pbaoxiaos 报销信息
	 * @param b <strong>true</strong>为批准<strong>false<strong>为拒绝批准
	 */
	@Override
	public int updateApprove(PBaoxiao pbaoxiaos, boolean agree) {
		String sql = "UPDATE 报销表 t,人事username r  SET t.status = :nstatus,t.批准人id = :approId,t.批准人 = :approver, t.批准人邮箱 = :email, t.领导意见 = :approRemark,t.批准时间  = CURRENT_TIMESTAMP  WHERE t.id=:id AND t.报销人id=r.id AND (r.细胞核邮箱=:email or r.指导邮箱=:email) and status=:status";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nstatus", agree ? SysConfig.CW_BX_APPROVE_AGREE : SysConfig.CW_BX_APPROVE_DISAGREE );
		params.put("status", getPreStep(SysConfig.CW_BX_APPROVE_AGREE));
		params.put("approId", pbaoxiaos.getApproid());
		params.put("id", pbaoxiaos.getId());
		params.put("email", pbaoxiaos.getAproEmail());
		params.put("approver", pbaoxiaos.getApprover());
//		params.put("email", email);
		params.put("approRemark", pbaoxiaos.getApproRemark());
		return baoxiaoDao.prepareCall(sql, params);
	}
	

	@Override
	public int updateBaoxiaoReceive(Long id, boolean agree, String spRemarks, Integer uid) {
		String hql ="update Baoxiao set reciverId=:uid,reciveTime=CURRENT_TIMESTAMP,status=:status,rcRemarks=:rcRemarks where id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("id", id);
		params.put("rcRemarks", spRemarks);
		params.put("status", agree ? SysConfig.CW_BX_RECIVED_AGREE : SysConfig.CW_BX_RECIVED_DISAGREE);
		return baoxiaoDao.executeHql(hql, params);
	}

	@Override
	public int updateBaoxiaoCheck(Long id, boolean agree, Integer uid) {
		String hql ="update Baoxiao set checkerId=:uid,status=:status where id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("uid", uid);
		params.put("status", agree ? SysConfig.CW_BX_CHECK_AGREE : SysConfig.CW_BX_CHECK_DISAGREE);
		return baoxiaoDao.executeHql(hql,params);
	}
	

	@Override
	public int updateBaoxiaoChupiao(PBaoxiao pbaoxiaos) {
		String hql = "update Baoxiao set cpid=:cpid,tuipiao=:tuipiao,caiwuRemarks=:caiwuRemarks,huikuan=:huikuan,baoxMoney=:baoxMoney,status=:nstatus where id=:id and status=:status";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpid", pbaoxiaos.getCpid());
		params.put("tuipiao", pbaoxiaos.getTuipiao());
		params.put("caiwuRemarks", pbaoxiaos.getCaiwuRemarks());
//		params.put("koujk", pbaoxiaos.getKoujk());
		params.put("huikuan", pbaoxiaos.getHuikuan());
		params.put("baoxMoney", pbaoxiaos.getBaoxMoney());
		params.put("nstatus", SysConfig.CW_BX_CHUPIAO);
		params.put("status", getPreStep(SysConfig.CW_BX_CHUPIAO));
		params.put("id", pbaoxiaos.getId());
		return baoxiaoDao.executeHql(hql,params);
	}
	
	@Override
	public int updateBaoxiaoHuikuan() {
		String hql = "update Baoxiao set status=:nstatus where status=:status";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", SysConfig.CW_BX_CHUPIAO);
		params.put("nstatus", SysConfig.CW_BX_YIHUIKUAN);
		return baoxiaoDao.executeHql(hql,params);
	}

	@Override
	public Serializable addKouJk(PKoukuan pkk) {
		KoukuanItem kk = new KoukuanItem();
		BeanUtils.copyProperties(pkk, kk);
		try {
			return koukuanDao.save(kk);
		} catch (Exception e) {
			logger.error("PubCaiwuServiceImpl.addKouJk:" + e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteKouJk(Long id) {
		KoukuanItem kk = new KoukuanItem();
		kk.setId(id);
		try {
			koukuanDao.delete(kk);
			return 1;
		} catch (Exception e) {
			logger.error("publicCaiwuService.deleteKouJk:[" + e);
			return 0;
		}
	}

	@Override
	public int updateKouJk(PKoukuan pkk) {
		KoukuanItem kk = new KoukuanItem();
		BeanUtils.copyProperties(pkk, kk);
		try {
			koukuanDao.update(kk);
			return 1;
		} catch (Exception e) {
			logger.error("PubCaiwuServiceImpl.updateKouJk:" + e);
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addKouJKList(List<PKoukuan> lis) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int deleteKjkByBxid(Long bxid) {
		String hql ="delete from KoukuanItem where bxid=:bxid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bxid", bxid);
		return koukuanDao.executeHql(hql,params);
	}

	@Override
	public List<PKoukuan> findJiekoukuan(Long bxid) {
		String hql = "from KoukuanItem t where bxid=:bxid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bxid", bxid);
		List<KoukuanItem> kks = koukuanDao.find(hql, params);
		Iterator<KoukuanItem> it = kks.iterator();
		List<PKoukuan> pks = new ArrayList<PKoukuan>();
		PKoukuan pk = null;
		while(it.hasNext()){
			pk = new PKoukuan();
			BeanUtils.copyProperties(it.next(), pk);
			pks.add(pk);
		}
		return pks;
	}

	@Override
	public List<PBaoxiaoStatus> findAllBaoxiaoStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAllCells(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNextStep(Integer statu, int st) {
		List<BaoxiaoStatus> status = getBaoxiaoStatus();
		Iterator<BaoxiaoStatus> it = status.iterator();
		while(it.hasNext()){
			BaoxiaoStatus bxt = it.next();
			if(null != bxt.getPrestatus() && bxt.getPrestatus()==statu && null!=bxt.getGrade() && st == bxt.getGrade()){
				return bxt.getStatus();
			}
		}
		return 0;
	}

	@Override
	public int getPreStep(Integer statu) {
		List<BaoxiaoStatus> status = getBaoxiaoStatus();
		Iterator<BaoxiaoStatus> it = status.iterator();
		while(it.hasNext()){
			BaoxiaoStatus bxt = it.next();
			if(statu == bxt.getStatus()){
				if(null != bxt.getPrestatus()){
					return bxt.getPrestatus();
				}
			}
		}
		return 0;
	}

	@Override
	public int updateTonextStep(Long id, Integer statu, int st) {
		Map<String, Object> params = new HashMap<String, Object>();
		Integer nextStatu = getNextStep(statu, st);
		if(nextStatu != 0){
			params.put("status", nextStatu);
			params.put("id", id);
			return baoxiaoDao.executeHql("update Baoxiao set status=:status where id=:id", params);
		}
		return 0 ;
	}

	@Override
	public int updateToPreStep(Long id, Integer statu) {
		Integer preStatu = getPreStep(statu);
		if(preStatu != 0){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", getPreStep(statu));
			params.put("id", id);
			return baoxiaoDao.executeHql("update Baoxiao set status=:status where id=:id", params);
		}
		return 0;
	}
	
	public List<BaoxiaoStatus> getBaoxiaoStatus(){
		return getBaoxiaoStatus(false);
	}
	
	public List<BaoxiaoStatus> getBaoxiaoStatus(boolean flag){
		if(flag || null == CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS)){
			List<BaoxiaoStatus> status = bxStatusDao.find("from BaoxiaoStatus");
			CacheManager.getInstance().put(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS, status);
		}
		return (List<BaoxiaoStatus>) CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS);
		
	}
	

}
