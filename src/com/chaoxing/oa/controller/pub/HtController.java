package com.chaoxing.oa.controller.pub;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.hetong.Contract;
import com.chaoxing.oa.entity.po.hetong.ContractVO;
import com.chaoxing.oa.entity.po.hetong.Customer;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.Fahuo;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.hetong.PageBean;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.hetong.CompanyInfo;
import com.chaoxing.oa.service.HtService;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/ht")
public class HtController {

	private HtService htService;

	public HtService getHtService() {
		return htService;
	}

	@Autowired
	public void setHtService(HtService htService) {
		this.htService = htService;
	}

	// --------------合同-----------------------------------------
	// 查出所有合同(根据session信息)
	@RequestMapping(value = "/contractList")
	public String agreementList(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, HttpSession session) {

		// 查询出未处理合同的数量
		int unhandledCount = htService.getUnhandledContract();
		List<Contract> contractList = htService.getContractList(page, size);
		// 总合同数量
		int totalCountContract = htService.getTotalCountContract();
		// 所属公司列表信息
		List<Contract> companyList = htService.getCompanyList(); 
		//单位性质列表信息
		List<Contract> propertyList = htService.getPropertyList();
		//产品名称列表信息
		List<ItemPrice> productList = htService.getProductList();
		PageBean<Contract> pageBean = new PageBean<Contract>();
		/* PageBean<Contract> pageBean = new PageBean<Contract>(); */
		pageBean.init(size, totalCountContract, page);
		pageBean.setList(contractList);

		model.addAttribute("contractList", contractList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("unhandledCount", unhandledCount);
		model.addAttribute("companyList", companyList);
		model.addAttribute("propertyList", propertyList);
		model.addAttribute("productList", productList);
		return "app/views/hetong/contractList";

	}

	// 合同详情       产品 发票 快递 获取负责人相关信息  
	@RequestMapping(value = "/contractDetail")
	public String contractDetail(Model model, @RequestParam("id") Integer id) throws ParseException {
		Contract contract = htService.getContracDetailById(id);
		/*//负责人
		String operator = contract.getOperator();*/
		Integer cid = contract.getCid();
		CustomerDepart customerDepart = htService.getCustomerDepartByoperator(cid);
	/*	Date dengjiTime = new Date();
		contract.setDengjiTime(dengjiTime);*/
		//产品列表
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		//发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		//快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);

		//根据合同编号查询出错误消息
		ContractVO contractVO = htService.getContractVOByid(id);
		
		model.addAttribute("contract", contract);
		model.addAttribute("itemPriceList", itemPriceList);
		model.addAttribute("faPiaoList", faPiaoList);
		model.addAttribute("fahuoList", fahuoList);
		model.addAttribute("customerDepart", customerDepart);
		model.addAttribute("contractVO", contractVO);
		return "app/views/hetong/contractNormal";
	}
	
	
	@RequestMapping(value = "/contractDetailForSale")
	public String contractDetailForSale(Model model, @RequestParam("id") Integer id,HttpSession session) throws ParseException {
		Contract contract = htService.getContracDetailById(id);
		
		//产品列表
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		//发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		//快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);

		//根据合同编号查询出错误消息
		ContractVO contractVO = htService.getContractVOByid(id);
		
		//查询出销售本人负责的项目单位
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		
		//销售本人负责的单位列表
		List<CustomerDepart> companyList = htService.getDepartListAboutSale(email);
		
		model.addAttribute("contract", contract);
		model.addAttribute("itemPriceList", itemPriceList);
		model.addAttribute("faPiaoList", faPiaoList);
		model.addAttribute("fahuoList", fahuoList);
		model.addAttribute("companyList", companyList);
		model.addAttribute("ContractVO", contractVO);
		return "app/views/hetong/contractNormalDetailSale";
	}
	

	// 年份 购买单位 所属公司 单位性质 产品名称 省份 小组/细胞核 项目负责人 合同金额100-500 合同状态
	@RequestMapping(value = "/contractListCondition")
	public String contractListCondition(Model model, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			
			@RequestParam(value = "size", defaultValue = "10") int size,
			
		/*	@RequestParam(value = "year", defaultValue = "") String yearStr,//年份
*/			  
			@RequestParam(value = "purchaseCom", defaultValue = "") String purchaseCom,// 购买单位
	
	        @RequestParam(value = "gongsi", defaultValue = "") String gongsi, // 所属公司
	  
	        @RequestParam(value = "danwei", defaultValue = "") String danwei,//单位性质 
	  
	        @RequestParam(value = "chanpin", defaultValue = "") String product,//产品名称
	  
	        @RequestParam(value = "shengfen", defaultValue = "") String province,//省份
	  
	        @RequestParam(value = "group", defaultValue = "") String group,//小组
	  
	        @RequestParam(value = "responsibility", defaultValue = "") String  responsibility,//项目负责人
	  
	        @RequestParam(value = "userId", defaultValue = "") Integer userId,
	        
        	@RequestParam(value = "zhuangtai", defaultValue = "")String stateStr//合同状态

	) throws ParseException {

		// List<Contract> contractList =  htService.getContractListCondition(year,purchaseCom,gongsi,danwei,product,province,group,responsibility,zhuangtai,userId,page,size);
		
		// 所属公司列表信息
	    List<Contract> companyList = htService.getCompanyList(); 
		//单位性质列表信息
		List<Contract> propertyList = htService.getPropertyList();
		//产品名称列表信息
		List<ItemPrice> productList = htService.getProductList();
		// 查询出未处理合同的数量
		int unhandledCount = htService.getUnhandledContract();
		/*Date year = null;
		if(yearStr!=null && (yearStr.equals("")==false)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.parse(yearStr);
		}*/
		
		
		Integer state=-1;
		try {
			if(stateStr!=null && (stateStr.equals("")==false)){
				state = Integer.parseInt(stateStr);
			}
			
			List<Contract> contractList = htService.getContractListConditionTest(purchaseCom,gongsi,danwei,product,province,group,responsibility,userId,state, page, size);
			int conditionCountContract = htService.getConditionCountContract(purchaseCom,gongsi,danwei,product,province,group,responsibility,userId,state);
			PageBean<Contract> pageBean = new PageBean<Contract>();

			pageBean.init(size, conditionCountContract, page);

			pageBean.setList(contractList);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("companyList", companyList);
			model.addAttribute("propertyList", propertyList);
			model.addAttribute("productList", productList);
			model.addAttribute("unhandledCount", unhandledCount);
		} catch (NumberFormatException e) {
                e.printStackTrace();
		}
		
	
		return "app/views/hetong/contractList";

	}

	// 获取未处理合同列表
	@RequestMapping(value = "/getUndealcontractList")
	public String getUndealcontractList(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		// 查询出未处理合同的数量
		int unhandledCount = htService.getUnhandledContract();
		List<Contract> unHandledContractList = htService.getUnHandledContract(page, size);
		PageBean<Contract> pageBean = new PageBean<Contract>();
		/* PageBean<Contract> pageBean = new PageBean<Contract>(); */
		pageBean.init(size, unhandledCount, page);
		pageBean.setList(unHandledContractList);

		model.addAttribute("pageBean", pageBean);
		model.addAttribute("unhandledCount", unhandledCount);
		return "app/views/hetong/contractList";

	}

	//获取用户和单位ID
	@ResponseBody
	@RequestMapping(value ="/getUserAndDepartId")
	public Json getUserAndDepartId(Integer yonghuId,HttpSession session) {
		Json json = new Json();
		try {
			//查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			
			CustomerDepart c = htService.getUserAndDepartId(yonghuId);
			json.setSuccess(true);
			json.setObj(c);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	
	//-销售 创建一个新合同(保存合同)
	@ResponseBody
	@RequestMapping(value ="/addContractNormal")
	public Json addContractNomal(
			@RequestParam("id") Integer id,
			@RequestParam("company") String company,
			@RequestParam("depart") String depart,
			@RequestParam("cid") Integer cid,
			@RequestParam("didNum") Integer didNum,
			@RequestParam("contractMoney") Float contractMoney,
			@RequestParam("agreementNumber") String agreementNumber,
			@RequestParam("endTime") String endTime,
			@RequestParam("agreementText") String agreementText,
			@RequestParam("remarksText") String remarksText,
			@RequestParam("payMethod") String payMethod,
			@RequestParam("yonghuId") Integer yonghuId,
			HttpSession session
			) {
		Json json = new Json();
		try {
			Contract contract = htService.getContracDetailById(id);
			Integer dealConditon = 4;
			//查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			/*//根据邮箱获取负责人
			String charger = htService.getOperator(email);*/
			//要从session中获取用户信息  set 负责人(本人负责的合同)
			
			int saleId = sessionInfo.getId();
			//根据销售本人ID查询出RenshiUserName对象
			RenshiUserName renshiUserName = htService.getRenshiUserName(saleId);
			
			//岗位性质  部门名称 省份  细胞核
			String gangweiXingzhi = renshiUserName.getFirstLevel();
			String bumenmingcheng = renshiUserName.getSecondLevel();
			String shengfen = renshiUserName.getThirdLevel();
			String xibaohe = renshiUserName.getFourthLevel();
			//提交时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date endTime2 = sdf.parse(endTime);
			/*//根据邮箱获取负责人
			String resPerson = htService.getOperator(email);*/
			CustomerDepart c = htService.getUserAndDepartId(yonghuId);
			//单位性质
			String danweixingzhi = c.getXingzhi();
			//自动编号
			Integer id2 = c.getId();
			Customer customer = htService.getyonghuById(id2);
			//用户性质
			String yonghuxingzhi = customer.getXingzhi();
			
			StringBuilder productName2 = new StringBuilder();
		/*	//产品列表
			List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
			for (ItemPrice itemPrice : itemPriceList) {
				//Object b = itemPrice;
				productName2.append(itemPrice.getName()+",");
			}
			String productName = productName2.toString();
			System.out.println(productName);*/
			
			
			
			htService.updateContractSave(id, company, depart, cid, didNum, contractMoney, 
					agreementNumber, endTime2, 
					agreementText, remarksText, payMethod,
					dealConditon,gangweiXingzhi,bumenmingcheng,shengfen,xibaohe,date,danweixingzhi,yonghuxingzhi);
			json.setSuccess(true);
			json.setObj(contract);
			json.setMsg("创建合同成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("创建合同失败");
		}
		return json;
	}
	
	
	//-销售 创建一个新合同(提交)
	@ResponseBody
	@RequestMapping(value ="/addContractNomalSubmit")
	public Json addContractNomalSubmit(
			@RequestParam("id") Integer id,
			@RequestParam("company") String company,//所属公司
			@RequestParam("depart") String depart,
			@RequestParam("cid") Integer cid,
			@RequestParam("didNum") Integer didNum,
			@RequestParam("contractMoney") Float contractMoney,
			@RequestParam("agreementNumber") String agreementNumber,
			@RequestParam("endTime") String endTime,
			@RequestParam("agreementText") String agreementText,
			@RequestParam("remarksText") String remarksText,
			@RequestParam("payMethod") String payMethod,
			@RequestParam("yonghuId") Integer yonghuId,
			HttpSession session) {
		Json json = new Json();
		try {
			Contract contract = htService.getContracDetailById(id);
			
			/*StringBuilder productName2 = new StringBuilder();
			//产品列表
			List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
			for (ItemPrice itemPrice : itemPriceList) {
				//Object b = itemPrice;
				productName2.append(itemPrice.getName()+",");
			}
			String productName = productName2.toString();*/
			
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			String formatDate = sdf.format(date);*/
			Integer dealConditon = 0;
			//查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			int saleId = sessionInfo.getId();
			//根据销售本人ID查询出RenshiUserName对象
			RenshiUserName renshiUserName = htService.getRenshiUserName(saleId);
			
			//岗位性质  部门名称 省份  细胞核
			String gangweiXingzhi = renshiUserName.getFirstLevel();
			String bumenmingcheng = renshiUserName.getSecondLevel();
			String shengfen = renshiUserName.getThirdLevel();
			String xibaohe = renshiUserName.getFourthLevel();
			//根据邮箱获取负责人
			String charger = htService.getOperator(email);
			//要从session中获取用户信息  set 负责人(本人负责的合同)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date endTime2 = sdf.parse(endTime);
			//提交时间
			Date date = new Date();

			
			
			CustomerDepart c = htService.getUserAndDepartId(yonghuId);
			//单位性质
			String danweixingzhi = c.getXingzhi();
			//自动编号
			Integer id2 = c.getId();
			Customer customer = htService.getyonghuById(id2);
			//用户性质
			if(customer!=null){
				String yonghuxingzhi = customer.getXingzhi();
				htService.updateContractSave(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2, agreementText, remarksText, payMethod,dealConditon,gangweiXingzhi,bumenmingcheng,shengfen,xibaohe,date,danweixingzhi,yonghuxingzhi);
				json.setSuccess(true);
				json.setObj(contract);
				json.setMsg("创建合同成功");
			}else{
				json.setSuccess(false);
				json.setMsg("创建合同失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("创建合同失败");
		}
		return json;
	}
	
	//取消
	@ResponseBody
	@RequestMapping(value = "/contractNormalCancel")
	public Json contractNormalCancel(Integer id) {
		Json json = new Json();
		try {
			htService.deleteItemPriceByHtid(id);
			htService.deleteFapiaoByHtid(id);
			htService.deleteFahuoByHtid(id);
			htService.deleteContract(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		
		return json;
	}
	
	
	// 到创建合同页面   
	@RequestMapping(value = "/createContract")
	public String createContract(Model model,HttpSession session) {
		//查询出销售本人负责的项目单位
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		//销售本人负责的单位列表
		List<CustomerDepart> companyList = htService.getDepartListAboutSale(email);
		
		//产品列表
		List<Object> productList = htService.getAllProduct();
		model.addAttribute("companyList", companyList);
		model.addAttribute("productList", productList);
		return "app/views/hetong/contractNormalSale";
	}

	//创建一个空合同对象
	@ResponseBody
	@RequestMapping(value="/addRealContract")
	public Json addRealContract() {
		Json json = new Json();
		try {
			Contract contract = new Contract();
			htService.addContractNomal(contract);
			Integer id = contract.getId();
			json.setSuccess(true);
			json.setObj(id);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		
		return json;
	}
	
	// 销售人员查看合同列表(只查看本人负责的合同)
	@RequestMapping(value = "/contractListSale")
	public String contractListSale(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size, HttpSession session) {
		
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		/*//根据邮箱获取负责人
		String responsibility = htService.getOperator(email);*/
		
		/*List<Contract> contractList = htService.getContractListSale(responsibility, page, size);*/
		List<Object> contractList = htService.getContractListSale(email, page, size);
		// 总合同数量
		int totalCountContract = htService.getTotalCountContractSale(email);
		
		PageBean<Object> pageBean = new PageBean<Object>();

		pageBean.init(size, totalCountContract, page);
		pageBean.setList(contractList);

		model.addAttribute("contractList", contractList);
		model.addAttribute("pageBean", pageBean);

		return "app/views/hetong/contractListSale";

	}

	// 根据合同ID把对应合同删除
	@ResponseBody
	@RequestMapping(value = "/deleteContract")
	public Json deleteAgreement(String id) {

		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			if (contractId > 0) {
				htService.deleteContract(contractId);
				json.setSuccess(true);
				json.setMsg("删除合同成功");
			} else {
				json.setMsg("删除合同失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("删除合同失败");
			
		}
        return json;
	}

	
	//改变合同状态   删除合同对应的错误消息
	@ResponseBody
	@RequestMapping(value = "/updateContractCondition")
	public Json changeContractCondition(@RequestParam(value ="id") String id) {

		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			if (contractId > 0) {
				htService.updateContractCondition(contractId);
				htService.deleteErrorInfo(contractId);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} else {
				json.setMsg("操作失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("操作失败");
			
		}
        return json;
				
	} 
	
	//更新合同信息
	@ResponseBody
	@RequestMapping(value = "/updateContractXingzheng")
	public Json updateContractXingzheng(@RequestParam(value ="id") String id,
			@RequestParam(value ="yinhuashui") String yinhuashui,
			@RequestParam(value ="guidangDate") String guidangDate,
			@RequestParam(value ="huaizhangAmount") Float huaizhangAmount,
			@RequestParam(value ="company") String company
			) {

		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			if (contractId > 0) {
				//生成归档编号
				String code = htService.getdanweicode(company);//cx
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				String format = sdf.format(date);
				String substringformat = format.substring(2);//17
				
				String guidangMaxNum = htService.getguidangMaxNum(code);
				String substring = guidangMaxNum.substring(4);//0009
				int a = Integer.parseInt(substring);
				int b = a+1;
				String bs = String.valueOf(b);
				if(bs.length()==1){
					bs = "000"+bs;
				}
				if(bs.length()==2){
					bs = "00"+bs;
				}
				if(bs.length()==3){
					bs = "0"+bs;
				}
				if(bs.length()==4){
					bs = ""+bs;
				}
				String guidangCode = code+substringformat+bs;
				htService.updateContractXingzheng(yinhuashui,guidangDate,huaizhangAmount,guidangCode,contractId);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} else {
				json.setMsg("操作失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("操作失败");
			
		}
        return json;
	}
	
	
	//删除对应的错误信息
	@ResponseBody
	@RequestMapping(value = "/deleteErrorInfo")
	public Json deleteErrorInfo(@RequestParam(value ="id") String id) {

		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			if (contractId > 0) {
				htService.deleteErrorInfo(contractId);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} else {
				json.setMsg("操作失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("操作失败");
			
		}
        return json;
	}
	
	  //合同未通过
		@ResponseBody
		@RequestMapping(value = "/updatecontractNoPass")
		public Json updatecontractNoPass(@RequestParam(value ="id") String id) {

			Json json = new Json();
			int contractId;
			try {
				contractId = Integer.parseInt(id);
				if (contractId > 0) {
					htService.updatecontractNoPass(contractId);
					json.setSuccess(true);
					json.setMsg("操作成功");
				} else {
					json.setMsg("操作失败");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				json.setMsg("操作失败");
				
			}
	        return json;
		}
	
	  //更新合同信息(回显页面)
			@ResponseBody
			@RequestMapping(value = "/updateContractNormal")
			public Json updateContractNormal(@RequestParam(value ="id") Integer id,
					@RequestParam(value ="company") String company,
					@RequestParam(value ="depart") String depart,
					@RequestParam(value ="cid") Integer cid,
					@RequestParam(value ="didNum") Integer didNum,
					@RequestParam(value ="contractMoney") Float contractMoney,
					@RequestParam(value ="agreementNumber") String agreementNumber,
					@RequestParam(value ="endTime") String endTime,
					@RequestParam(value ="agreementText") String agreementText,
					@RequestParam(value ="remarksText") String remarksText,
					@RequestParam(value ="payMethod") String payMethod
					) {
				Json json = new Json();
				try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
						Date endTime2 = sdf.parse(endTime);
					    htService.updateContract(id,company,depart,cid,didNum,contractMoney,agreementNumber,endTime2,agreementText,remarksText,payMethod);
						json.setSuccess(true);
						json.setMsg("更新合同信息成功");
				} catch (Exception e) {
					e.printStackTrace();
					json.setMsg("更新合同信息失败");
				}
		        return json;
			}

	
	// --------------产品-----------------------------------------

	@RequestMapping(value = "/itemPriceList")
	public String itemPriceList(String htNum, Model model) {

		List<ItemPrice> itemPriceList = htService.getitemPriceList(htNum);
		model.addAttribute("itemPriceList", itemPriceList);
		return "app/views/hetong/list.jsp";

	}
	
	@ResponseBody
	@RequestMapping(value = "/addItemPrice")
	public Json addItemPrice(ItemPrice itemprice) {
		Json json = new Json();
		try {
			htService.addItemPrice(itemprice);
			json.setObj(itemprice);
			json.setSuccess(true);
			json.setMsg("添加产品成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("添加产品失败");
		}
        return json;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteItemPrice")
	public Json deleteItemPrice(int itemPriceId) {
		Json json = new Json();
		try {
			htService.deleteItemPrice(itemPriceId);
			json.setSuccess(true);
			json.setMsg("删除产品成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除产品失败");
			e.printStackTrace();
		}
		return json;
	}

	// 表单回显
	@ResponseBody
	@RequestMapping(value = "/toUpdateItemPrice")
	public Json selectItemPrice(Integer itemPriceId) {
        Json json = new Json();
		try {
			ItemPrice itemPrice = htService.selectItemPrice(itemPriceId);
			json.setSuccess(true);
			json.setObj(itemPrice);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		
		return json;
	}
	
    @ResponseBody
	@RequestMapping(value = "/doUpdateItemPrice")
	public Json updateItemPrice(
			@RequestParam(value ="productID") Integer productID, 
			@RequestParam(value ="name") String productName, 
			@RequestParam(value ="amount") Float amount,
			@RequestParam(value ="begain") String begain, 
			@RequestParam(value ="end") String end,
			@RequestParam(value ="money") Float money) {
		Json json = new Json();
		try {
			htService.updateItemPrice(productName,amount,begain,end,money,productID);
			json.setSuccess(true);
			json.setMsg("修改产品成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改产品失败");
		}
		return json;

	}

	// ----------------发票

	@RequestMapping(value = "/faPiaoList")
	public String faPiaoList(String htNum, Model model) {

		List<FaPiao> faPiaoList = htService.getfaPiaoList(htNum);
		model.addAttribute("faPiaoList", faPiaoList);
		return "app/views/hetong/list.jsp";

	}
    @ResponseBody
	@RequestMapping(value="/addFaPiao")
	public Json addFaPiao(FaPiao faPiao,HttpSession session) {
		Json json = new Json();
		try {
			htService.addFaPiao(faPiao);
			json.setSuccess(true);
			json.setObj(faPiao);
			json.setMsg("增开发票成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("增开发票失败");
		}
        return json;

	}

	@ResponseBody
	@RequestMapping(value = "/deleteFaPiao")
	public Json deleteFaPiao(Integer fapiaoID) {
		Json json = new Json();
		try {
			htService.deleteFaPiao(fapiaoID);
			json.setSuccess(true);
			json.setMsg("删除发票成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除发票失败");
			e.printStackTrace();
		}
		return json;
	}

	// 表单回显
	@ResponseBody
	@RequestMapping(value = "/toUpdateFaPiao")
	public Json selectFaPiao(Integer fapiaoId) {
        Json json = new Json();   
		try {
			FaPiao faPiao = htService.selectFaPiao(fapiaoId);
			json.setSuccess(true);
			json.setObj(faPiao);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	
    @ResponseBody
	@RequestMapping(value = "/doUpdateFapiao")
    public Json doUpdateFapiao(
		@RequestParam(value ="money") BigDecimal money, 
		@RequestParam(value ="capitalMoney") String capitalMoney, 
		@RequestParam(value ="company") String company,
		@RequestParam(value ="departMement") String departMement, 
		@RequestParam(value ="type") String type,
		@RequestParam(value ="name") String name, 
	    @RequestParam(value ="date") String date,
		@RequestParam(value ="remark") String remark,
		@RequestParam(value ="fapiaoID") Integer fapiaoID
		) {
	Json json = new Json();
	try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(date);
		htService.updateFapiao(money,capitalMoney,company,departMement,type,name,date1,remark,fapiaoID);
		json.setSuccess(true);
		json.setMsg("修改发票成功");
	} catch (Exception e) {
		e.printStackTrace();
		json.setSuccess(false);
		json.setMsg("修改发票失败");
	}
	return json;

}
	
	
	// ------------------------ 快递

	@RequestMapping(value = "/faHuoList")
	public String faHuoList(String htNum, Model model) {

		List<Fahuo> faHuoList = htService.getFaHuoList(htNum);
		model.addAttribute("faHuoList", faHuoList);
		return "app/views/hetong/list.jsp";

	}
	
	@ResponseBody
	@RequestMapping(value = "/addFaHuo")
	public Json addFaHuo(Fahuo fahuo,HttpSession session) {
		Json json = new Json();
		try {
			//查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			//根据邮箱获取负责人
			String charger = htService.getOperator(email);
			//发件人为登陆用户
			fahuo.setSender(charger);
			htService.addFahuo(fahuo);
			json.setSuccess(true);
			json.setObj(fahuo);
			json.setMsg("添加快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("添加快递失败");
		}
        return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteFahuo")
	public Json deleteFahuo(Integer fahuoId) {
		Json json = new Json();
		try {
			htService.deleteFahuo(fahuoId);
			json.setSuccess(true);
			json.setMsg("删除快递成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除快递失败");
			e.printStackTrace();
		}
		return json;
	}
	

	// 表单回显
	@ResponseBody
	@RequestMapping(value = "/toUpdateFahuo")
	public Json selectFahuo(@RequestParam(value ="fahuoId") Integer fahuoId) {
		  Json json = new Json();
			try {
				Fahuo fahuo = htService.selectFahuo(fahuoId);
				json.setSuccess(true);
				json.setObj(fahuo);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
			}
			return json;
	}

    @ResponseBody
	@RequestMapping(value = "/doUpdateFahuo")
	public Json doUpdateFahuo(
			@RequestParam(value ="fahuoId") Integer fahuoId, 
			@RequestParam(value ="mailno") String mailno,
			@RequestParam(value ="d_contact") String d_contact,
		    @RequestParam(value ="d_tel") String d_tel,
			@RequestParam(value ="d_company") String d_company,
    	    @RequestParam(value ="d_address") String d_address,
   		    @RequestParam(value ="jDate") String jDate,
    		@RequestParam(value ="postMethod") String postMethod,
//   		@RequestParam(value ="remark") String remark,
   		    @RequestParam(value ="content") String content) 
			{
		Json json = new Json();
		try {
			
			htService.updateFahuo(mailno,d_contact,d_tel,d_company,d_address,jDate,postMethod,content,fahuoId);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;

	}
    
    //获取发件地    
	@ResponseBody
	@RequestMapping(value = "/getFajiandi")
	public Json getFajiandi(String company) {
        Json json = new Json();   
		try {
			CompanyInfo companyInfo = htService.getFajiandi(company);
			json.setSuccess(true);
			json.setObj(companyInfo);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
 //添加错误消息   ContractVO
	@ResponseBody
	@RequestMapping(value = "/addErrorInfo")
	public Json addErrorInfo(ContractVO contractVO) {
        Json json = new Json();   
		try {
			//创建
			htService.addErrorInfo(contractVO);
			json.setSuccess(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	//getTotalFapiaoAmount
	@ResponseBody
	@RequestMapping(value = "/getTotalFapiaoAmount")
	public Json getTotalFapiaoAmount(@RequestParam(value ="id") Integer id) {
        Json json = new Json();   
		try {
			
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId2(id);
			int a = 0; 
			for (FaPiao faPiao : faPiaoList) {
				BigDecimal money = faPiao.getMoney();
				int b=money.intValue();
				a = a+b;
			}
			json.setSuccess(true);
			json.setObj(a);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	
	
	
/*   //添加错误消息   
	@ResponseBody
	@RequestMapping(value = "/addErrorInfo")
	public Json addErrorInfo(String shuxingming,String errorInfo,Integer contractId) {
        Json json = new Json();   
		try {
			//没有合同错误信息对象 创建      有 更新
			ContractVO contractVO = htService.getContractVObyhtid(contractId);
			if(contractVO==null){
				//创建
				htService.addErrorInfo(shuxingming,errorInfo,contractId);
				json.setSuccess(true);
				}else{
					//更新
				}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}*/
	

}
