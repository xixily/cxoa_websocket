package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;

public interface PubFileOperatorService {
	
	public String getDaihuiKuanExcel(List<PBaoxiao> pbs);

	/**
	 * 生成已汇款的excel表格
	 * @param pbs
	 * @return
	 */
	public String getyihuikuanExcel(List<PBaoxiao> pbs);
}
