package com.chaoxing.oa.service;

import java.util.Map;

import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.hetong.PCompanyInfo;
import com.chaoxing.oa.entity.page.hetong.PContract;
import com.chaoxing.oa.entity.page.hetong.PCustomer;
import com.chaoxing.oa.entity.page.hetong.PCustomerDepart;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.hetong.PFapiao;
import com.chaoxing.oa.entity.page.hetong.PItemPrice;

public interface HetongService {
	
	//用户列表
	public Map<String, Object> findCustomers(PCustomer pCustomer, int isExport);
		
	public PCustomer getCustomer(int id);
	
	public int addCustomer(PCustomer pCustomer);
	
	public int updateCustomer(PCustomer pCustomer);
	
	public int deleteCustomer(PCustomer pCustomer);
		
	//用户单位
	public Map<String, Object> findCustomerDepart(PCustomerDepart pCustomerDepart, int isExport);
	
	public PCustomerDepart getCustomerDepart(int id);
		
	public int addCustomerDepart(PCustomerDepart pCustomerDepart);
		
	public int updateCustomerDepart(PCustomerDepart pCustomerDepart);
		
	public int deleteCustomerDepart(PCustomerDepart pCustomerDepart);
		
	//合同情况
	public Map<String, Object> findContracts(PContract pConstract, int isExport);
		
	public PContract getContract(Integer hetongCode);
		
	public int addContract(PContract pConstract);
		
	public int updateContract(PContract pConstract);
		
	public int deleteContract(PContract pConstract);
		
	//发票情况
	public Map<String, Object> findFapiao(PFapiao pfapiao, int isExport);
		
	public PFapiao getFapiao(int id);
		
	public int updateFapiao(PFapiao pfapiao);
		
	public int addFapiao(PFapiao pfapiao);
		
	public int deleteFapiao(PFapiao pfapiao);
		
	//分项报价
	public Map<String, Object> findItemPrice(PItemPrice pItemPrice, int isExport);
		
	public PItemPrice getItemPrice(int id);
		
	public int updateItemPrice(PItemPrice pItemPrice);
		
	public int addItemPrice(PItemPrice pItemPrice);
		
	public int deleteItemPrice(PItemPrice pItemPrice);
		
	//发货情况
	public Map<String, Object> findFahuo(PFahuo pfahuo, Page page, int isExport);
		
	public PFahuo getFahuo(Integer orderId);
		
	public int addFahuo(PFahuo pfahuo);
		
	public int deleteFahuo(PFahuo pfahuo);
		
	public int updateFahuo(PFahuo pfahuo);
	
	//公司代码
	public PCompanyInfo getCompanyInfo(int i);

	public PCompanyInfo getCompanyInfoByName(String company);

}
