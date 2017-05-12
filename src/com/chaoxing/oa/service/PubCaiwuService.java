package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.caiwu.PUserBank;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.pub.caiwu.PKoukuan;

public interface PubCaiwuService {

	/**
	 * 获得报销信息
	 * @param bxid
	 * @return
	 */
	public PBaoxiao getBaoxiao(Long bxid);
	
	public Map<String, Object> findBaoxiaoByUid(PBaoxiao pbaoxiao, Page page, Integer id);

	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page);
	
	public Map<String, Object> findBaoxiaoByLeader(PBaoxiao pbaoxiao, Page page, String email);

	public Serializable addBaoxiao(PBaoxiao pbaoxiao);

	/**
	 * 
	 * @param id required
	 * @param uid required
	 * @return
	 */
	public int deleteBaoxiao(Long id, Integer uid);
	
	public long updateBaoxiao(PBaoxiao pBaoxiao);
	//跟人报销更新
	public int updateSeltBaoxiao(PBaoxiao psbaoxiao);
	//去年总报销额
	public Double getLastYear(int id);
	public Double getLastYear(String email);
	//今年已经报销
	public Double getThisYear(int id);
	public Double getThisYear(String email);
	//根据条件汇总
	/**
	 * 查询时间t在区间 min<=t<max的记录总额
	 * @param pbaoxiao
	 * @param min 区间下限
	 * @param max 区间上限
	 */
	public Long getBaoxiaoTotal(PBaoxiao pbaoxiao, Date min, Date max);
	/**
	 * @param pbaoxiaos 报销信息
	 * @param agree <strong>true</strong>为批准<strong>false<strong>为拒绝批准
	 */
	public int updateApprove(PBaoxiao pbaoxiaos, boolean agree);

	public int updateBaoxiaoReceive(Long id, boolean agree, String spRemarks, Integer uid);

	public int updateBaoxiaoCheck(Long id, boolean agree, Integer uid);

	public int updateBaoxiaoChupiao(PBaoxiao pbaoxiaos);

	public int updateBaoxiaoHuikuan(PBaoxiao pbaoxiao);
	
	public Serializable addKouJk(PKoukuan pkk);
	
	public int deleteKouJk(Long id);
	
	public int updateKouJk(PKoukuan pkk);
	
	public int addKouJKList(List<PKoukuan> lis);
	
	public int deleteKjkByBxid(Long bxid);
//	
//	public int updateKouJKList(List<PKoukuan> addLis);
//
//	public int deleteKouJKList(List<PKoukuan> addLis);
	
	/**
	 * 查询口借款
	 * @param bxid
	 * @return
	 */
	public List<PKoukuan> findJiekoukuan(Long bxid);
	
	public int getNextStep(Integer statu, int st);
	
	public int getPreStep(Integer statu);
	
	public int updateTonextStep(Long id, Integer statu, int st);
	
	public int updateToPreStep(Long id, Integer statu);

	/**
	 * 合同
	 */
	public void findCellsByEmail(String email);

	public PRenshiEmployee getEmployee(Integer uid);

	public List<PBaoxiaoStatus> findAllBaoxiaoStatus();

	public void findAllCells(String email);

	public Long getMaxCpNumber();

	public Serializable addUserBank(Integer id, String bank, String account);

	public List<PUserBank> findBxBanks(Integer uid);

	/**
	 * 通过批准人id查询报销信息
	 * @param pbaoxiao
	 * @param page
	 * @param email
	 * @return
	 */
	public Map<String, Object> findBxByApprover(PBaoxiao pbaoxiao, Page page, Integer aid);

	/**
	 * 细胞核获取去年已批准
	 * @param id
	 * @return
	 */
	public Double getLastYearYpz(int id);

	/**
	 * 细胞核获取今年已批准
	 * @param id
	 * @return
	 */
	public Double getThisYearYpz(int id);

	/**
	 * 获得当前条件下的待汇款总额
	 * @param pbaoxiao
	 */
	public Double gethuikuanTotal(PBaoxiao pbaoxiao);


}
