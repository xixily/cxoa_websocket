package com.chaoxing.oa.service;

import java.util.List;
import java.util.Map;

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

public interface PubHetongService {

	/**
	 * 统计数据 
	 * @param email
	 */
	public void findCellcoreTotal(String email);

//	public Map<String, Object> findGdView(String email);
	
	//获取细胞核统计信息
	public List<PGuidanceView> findCellCoresCount(String email);

	//查找细胞核下面的销售（胞质）
	public  List<Cells> findCoreCellsCount(String cemail);
	
	//查找用户单位列表
	public List<UserList> findUserListCount(String email, String charger);
	
	//查找用户单位合同列表
	public List<PFapiaoDetail> findUserContractsCount(Integer autoCode);
	
	//查找合同发票信息
	public void findContractFapiaoCount(Integer id);
	
	//查找应收统计
	public List<PYingshou> findYingshouCount(Integer id);
	
	
	
	/**
	 * 合同数据
	 */
	
	public Map<String, Object> findUcfView(Page page, PucfView pucf);
	
	public void findUserList();
	
	public Map<String,Object> findContracts(PContract pcontract);
	
//	public Map<String,Object> findUserFapiao(Integer id);
	
	public Map<String, Object> findContractFapiaos(Page page, PFapiao pfapiao);
	
	public Map<String, Object> findContractItemPrice(Page page, PItemPrice ptprice);
	
	public List<PFapiao>  findUserFapiao(UserList userList);
	
	public List<PItemPrice>  findUserItemprice(Integer id);
	
	public PCustomerDepart getUserList(Integer id);
	
	public void getContractDetail(Integer id);
	
	public void getFapiaoDetail(Integer id);
	
	public PRenshiEmployee getUserByEmai(String email);

}
