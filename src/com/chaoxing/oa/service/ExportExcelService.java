package com.chaoxing.oa.service;

import java.util.List;

import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.po.view.ShebaoAR;
import com.chaoxing.oa.entity.po.view.ShebaoMX;
import com.chaoxing.oa.entity.po.view.Yidong;

public interface ExportExcelService {

	/** 人事查询结果excel报表导出 **/
//	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees);
	//人事信息
	public String getRenshiQueryExport(List<PRenshiEmployee> renshiEmployees, boolean flag);
	
	public String getYidong(List<Yidong> shebaoMXs, String type);

	//考勤信息
	public String getKaoqinExportExcel(List<PKaoQin> pKaoqins);

	//当月工资表信息
	public String getMonthWagesExcel(List<PMonthWages> pMonthWages);

	//社保信息
	public String getShebaoCompany(List<PshebaoDetail> pShebaoDetails);

	public String getShebaoMX(List<ShebaoMX> shebaoMXs);

	public String getshebaoAR(List<ShebaoAR> shebaoMXs, String type);

	public String getShebaoSummary(List<PSheBaoSummary> pShebaoSummarys);

	//架构信息
	public String getPOStructExcel(List<POStructV> pos);

	public String getPOStructExcel2(List<POStructV> pos);

	//发货管理
	public String getFahuo(List<PFahuo> pFahuos);



}
