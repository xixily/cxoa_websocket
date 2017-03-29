package com.chaoxing.oa.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiaoStatus;
import com.chaoxing.oa.entity.page.pub.caiwu.PKoukuan;

public interface PubCaiwuService {

	/**
	 * 报销
	 */
	public PBaoxiao getBaoxiao(Long long1);
	
	public Map<String, Object> findBaoxiaoByUid(PBaoxiao pbaoxiao, Page page, Integer id);

	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page);
	
	public Map<String, Object> findBaoxiaoByLeader(PBaoxiao pbaoxiao, Page page, String email);

	public Serializable addBaoxiao(PBaoxiao pbaoxiao);

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

	public int updateApprove(PBaoxiao pbaoxiaos, boolean agree);

	public int updateBaoxiaoReceive(Long id, boolean agree, String spRemarks, Integer uid);

	public int updateBaoxiaoCheck(Long id, boolean agree, Integer uid);

	public int updateBaoxiaoChupiao(PBaoxiao pbaoxiaos);

	public int updateBaoxiaoHuikuan();
	
	public Serializable addKouJk(PKoukuan pkk);
	
	public int deleteKouJk(Long id);
	
	public int updateKouJk(PKoukuan pkk);
	
	public int addKouJKList(List<PKoukuan> lis);
	
	public int deleteKjkByBxid(Long bxid);
//	
//	public int updateKouJKList(List<PKoukuan> addLis);
//
//	public int deleteKouJKList(List<PKoukuan> addLis);
	
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


}
