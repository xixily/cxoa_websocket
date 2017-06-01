package com.chaoxing.oa.controller.pub;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.hetong.Area;
import com.chaoxing.oa.entity.po.hetong.CompanyInfo;
import com.chaoxing.oa.entity.po.hetong.Contract;
import com.chaoxing.oa.entity.po.hetong.ContractVO;
import com.chaoxing.oa.entity.po.hetong.Customer;
import com.chaoxing.oa.entity.po.hetong.CustomerDepart;
import com.chaoxing.oa.entity.po.hetong.FaPiao;
import com.chaoxing.oa.entity.po.hetong.Fahuo;
import com.chaoxing.oa.entity.po.hetong.ItemPrice;
import com.chaoxing.oa.entity.po.hetong.PageBean;
import com.chaoxing.oa.entity.po.view.RenshiUserName;
import com.chaoxing.oa.entity.po.view.Usercontracts;
import com.chaoxing.oa.entity.po.view.Usercontracts2;
import com.chaoxing.oa.service.HtService;
import com.chaoxing.oa.util.system.ResourceUtil;
import com.chaoxing.oa.util.data.SwitchMoneyUtil;

@Controller
@RequestMapping("/public/ht")
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

		// 查询出未处理合同的数量   开票金额 float    回款金额  String
		int unhandledCount = htService.getUnhandledContract();
		List<Usercontracts2> contractList = htService.getContractList(page, size);
		
		for (int i = 0; i < contractList.size(); i++) {
			Integer id = contractList.get(i).getId();//合同编号
			float totalKaiapiaoAmount = 0.0f;
			float totalHuikuanAmount = 0.0f;
			Date latestDate=null;
			
			// 发票列表
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
			if(faPiaoList.size()>0){
				//时间基准
				for (int j = 0; j < faPiaoList.size(); j++) {
					Date receivedpaymentsdate = faPiaoList.get(j).getReceivedpaymentsdate();//回款日期
					if(receivedpaymentsdate!=null && !"".equals(receivedpaymentsdate)){
						latestDate = receivedpaymentsdate;
						break;
					}
				}
				
				for (int j = 0; j < faPiaoList.size(); j++) {
					BigDecimal money = faPiaoList.get(j).getMoney();//发票金额
					if(money!=null){
						float moneyInt = money.floatValue(); 
						totalKaiapiaoAmount = moneyInt+totalKaiapiaoAmount;
					}
					
					BigDecimal huiKuan = faPiaoList.get(j).getHuiKuan();//回款金额
					if(huiKuan!=null){
						float huiKuanInt = huiKuan.floatValue(); 
						totalHuikuanAmount = huiKuanInt+totalHuikuanAmount;
					}
					
					Date receivedpaymentsdate = faPiaoList.get(j).getReceivedpaymentsdate();//回款日期
					if(latestDate!=null && receivedpaymentsdate!=null && receivedpaymentsdate.after(latestDate)){
						latestDate = receivedpaymentsdate;
					}
				}
			}
			String totalHuikuanAmountStr = String.valueOf(totalHuikuanAmount);
			contractList.get(i).setKaipiaoMoney(totalKaiapiaoAmount);
			contractList.get(i).setReceivedAmount(totalHuikuanAmountStr);
			contractList.get(i).setReceiveTime(latestDate);
		}
		// 总合同数量
		int totalCountContract = htService.getTotalCountContract();
		// 所属公司列表信息
		List<CompanyInfo> companyInfoList = htService.getCompanyList();
		// 单位性质列表信息
		List<Contract> propertyList = htService.getPropertyList();
		// 产品名称列表信息
		List<ItemPrice> productList = htService.getProductList();
		PageBean<Usercontracts2> pageBean = new PageBean<Usercontracts2>();
		/* PageBean<Contract> pageBean = new PageBean<Contract>(); */
		pageBean.init(size, totalCountContract, page);
		pageBean.setList(contractList);

		model.addAttribute("contractList", contractList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("unhandledCount", unhandledCount);
		model.addAttribute("companyInfoList", companyInfoList);
		model.addAttribute("propertyList", propertyList);
		model.addAttribute("productList", productList);
		return "app/views/hetong/contractList";

	}

	// 合同详情 产品 发票 快递 获取负责人相关信息 RenshiUserName
	@RequestMapping(value = "/contractDetail")
	public String contractDetail(Model model, @RequestParam("id") Integer id, HttpSession session)
			throws ParseException {
		Contract contract = htService.getContracDetailById(id);
		
		// 根据合同拿到所属公司
		String substringCompany = "";
		if (contract != null && StringUtils.isNotBlank(contract.getCompany())) {
			substringCompany = contract.getCompany().substring(0, 2);
		}
		List<Area> zhanghuList = htService.getZhanghu(substringCompany);
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		// 根据邮箱在人事username里拿联系电话
		RenshiUserName renshiUserName = htService.getTelephoneByEmail(email);

		// 用户单位ID
		Integer cid = contract.getCid();
		CustomerDepart customerDepart = htService.getCustomerDepartByoperator(cid);

		/*
		 * Date dengjiTime = new Date(); contract.setDengjiTime(dengjiTime);
		 */
		// 产品列表
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		// 发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		// 快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);

		// 根据合同编号查询出错误消息
		ContractVO contractVO = htService.getContractVOByid(id);
		
		// 获取所有产品列表
		List<Object> productList = htService.getAllProduct();

		model.addAttribute("contract", contract);
		model.addAttribute("itemPriceList", itemPriceList);
		model.addAttribute("faPiaoList", faPiaoList);
		model.addAttribute("fahuoList", fahuoList);
		model.addAttribute("customerDepart", customerDepart);
		model.addAttribute("contractVO", contractVO);
		model.addAttribute("renshiUserName", renshiUserName);
		model.addAttribute("zhanghuList", zhanghuList);
		model.addAttribute("productList", productList);
		//销售邮箱
		model.addAttribute("email", email);
		return "app/views/hetong/contractNormal";
	}

	// 未处理 审核已通过 合同完结(这三种状态返回的页面)
	@RequestMapping(value = "/contractDetailForSale")
	public String contractDetailForSale(Model model, @RequestParam("id") Integer id, HttpSession session)
			throws ParseException {
		Contract contract = htService.getContracDetailById(id);

		// 产品列表
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		// 发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		// 快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);

		// 根据合同编号查询出错误消息
		ContractVO contractVO = htService.getContractVOByid(id);

		// 查询出销售本人负责的项目单位
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();

		// 销售本人负责的单位列表
		List<CustomerDepart> companyList = htService.getDepartListAboutSale(email);

		model.addAttribute("contract", contract);
		model.addAttribute("itemPriceList", itemPriceList);
		model.addAttribute("faPiaoList", faPiaoList);
		model.addAttribute("fahuoList", fahuoList);
		model.addAttribute("companyList", companyList);
		model.addAttribute("ContractVO", contractVO);
		return "app/views/hetong/contractNormalDetailSale";
	}

	// 审核未通过 暂存
	@RequestMapping(value = "/contractDetailForSale2")
	public String contractDetailForSale2(Model model, @RequestParam("id") Integer id,
			@RequestParam("state") Integer state, HttpSession session) throws ParseException {
		Contract contract = htService.getContracDetailById(id);

		// 产品列表
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		// 发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		// 快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);

		// 根据合同编号查询出错误消息
		ContractVO contractVO = htService.getContractVOByid(id);

		// 查询出销售本人负责的项目单位
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();

		// 销售本人负责的单位列表
		List<CustomerDepart> companyList = htService.getDepartListAboutSale(email);
		// 获取所有产品列表
		List<Object> productList = htService.getAllProduct();

		model.addAttribute("contract", contract);
		model.addAttribute("itemPriceList", itemPriceList);
		model.addAttribute("faPiaoList", faPiaoList);
		model.addAttribute("fahuoList", fahuoList);
		model.addAttribute("companyList", companyList);
		model.addAttribute("ContractVO", contractVO);
		model.addAttribute("productList", productList);
		model.addAttribute("state", state);
		return "app/views/hetong/contractNormalDetailSale2";
	}

	// 年份 购买单位 所属公司 单位性质 产品名称 省份 小组/细胞核 项目负责人 合同金额100-500 合同状态
	@RequestMapping(value = "/contractListCondition")
	public String contractListCondition(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			// @RequestParam(value = "year", defaultValue = "") String
			// yearStr,//年份
			@RequestParam(value = "purchaseCom", defaultValue = "") String purchaseCom, // 购买单位
			@RequestParam(value = "gongsi", defaultValue = "") String gongsi, // 所属公司
			@RequestParam(value = "danwei", defaultValue = "") String danwei, // 单位性质
			/*
			 * @RequestParam(value = "chanpin", defaultValue = "") String
			 * product,//产品名称
			 */ /*
				 * @RequestParam(value = "shengfen", defaultValue = "") String
				 * province,//省份
				 */ @RequestParam(value = "group", defaultValue = "") String group, // 小组
			@RequestParam(value = "responsibility", defaultValue = "") String responsibility, // 项目负责人
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "zhuangtai", defaultValue = "") String stateStr// 合同状态
	) throws ParseException {

		// List<Contract> contractList =
		// htService.getContractListCondition(year,purchaseCom,gongsi,danwei,product,province,group,responsibility,zhuangtai,userId,page,size);

		// 所属公司列表信息
		List<CompanyInfo> companyInfoList = htService.getCompanyList();
		// 单位性质列表信息
		List<Contract> propertyList = htService.getPropertyList();
		// 产品名称列表信息
		List<ItemPrice> productList = htService.getProductList();
		// 查询出未处理合同的数量
		int unhandledCount = htService.getUnhandledContract();
		/*
		 * Date year = null; if(yearStr!=null && (yearStr.equals("")==false)){
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); year =
		 * sdf.parse(yearStr); }
		 */
		Integer state = -1;
		try {
			if (stateStr != null && (stateStr.equals("") == false)) {
				state = Integer.parseInt(stateStr);
			}

			List<Usercontracts2> contractList = htService.getContractListConditionTest(purchaseCom, gongsi, danwei,
					group, responsibility, userId, state, page, size);
			int conditionCountContract = htService.getConditionCountContract(purchaseCom, gongsi, danwei, group,
					responsibility, userId, state);

			PageBean<Usercontracts2> pageBean = new PageBean<Usercontracts2>();
			pageBean.init(size, conditionCountContract, page);
			pageBean.setList(contractList);

			model.addAttribute("gongsi", gongsi);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("companyInfoList", companyInfoList);
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
		List<Usercontracts2> unHandledContractList = htService.getUnHandledContract(page, size);
		PageBean<Usercontracts2> pageBean = new PageBean<Usercontracts2>();
		
		// 所属公司列表信息
		List<CompanyInfo> companyInfoList = htService.getCompanyList();
		// 单位性质列表信息
		List<Contract> propertyList = htService.getPropertyList();
		pageBean.init(size, unhandledCount, page);
		pageBean.setList(unHandledContractList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("unhandledCount", unhandledCount);
		model.addAttribute("companyInfoList", companyInfoList);
		model.addAttribute("propertyList", propertyList);
		return "app/views/hetong/contractList";
	}

	// 获取用户和单位ID
	@ResponseBody
	@RequestMapping(value = "/getUserAndDepartId")
	public Json getUserAndDepartId(Integer danweiId, HttpSession session) {
		//danweiId 用户单位主键
		Json json = new Json();
		try {
			// 查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			CustomerDepart c = htService.getUserAndDepartId(danweiId);
			json.setSuccess(true);
			json.setObj(c);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// -销售 创建一个新合同(保存合同)
	@ResponseBody
	@RequestMapping(value = "/addContractNormal")
	public Json addContractNomal(@RequestParam("id") Integer id, @RequestParam(value="company",required=false) String company,
			@RequestParam("depart") String depart, @RequestParam("cid") Integer cid,
			@RequestParam("didNum") Integer didNum, @RequestParam("contractMoney") Float contractMoney,
			@RequestParam("agreementNumber") String agreementNumber, @RequestParam("endTime") String endTime,
			@RequestParam("agreementText") String agreementText, @RequestParam("remarksText") String remarksText,
			@RequestParam("payMethod") String payMethod, @RequestParam("danweiId") Integer danweiId,
			HttpSession session) { 
		Json json = new Json();
		try {
			if (id == null) {
				json.setSuccess(false);
				json.setMsg("请先填写完合同、产品信息再保存！");
				return json;
			}
			Contract contract = htService.getContracDetailById(id);
			/*String agreementTextkuli = contract.getAgreementText();
			if(agreementTextkuli==null){
				agreementTextkuli = "";
			}
			String agreementTextFinal = agreementTextkuli+agreementText;*/
			Integer dealConditon = 4;
			// 查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();

			int saleId = sessionInfo.getId();
			// 根据销售本人ID查询出RenshiUserName对象
			RenshiUserName renshiUserName = htService.getRenshiUserName(saleId);

			// 岗位性质 部门名称 省份 细胞核
			String gangweiXingzhi = "";
			String bumenmingcheng = "";
			String shengfen = "";
			String xibaohe = "";
			if (renshiUserName != null) {
				gangweiXingzhi = renshiUserName.getFirstLevel();
				bumenmingcheng = renshiUserName.getSecondLevel();
				shengfen = renshiUserName.getThirdLevel();
				xibaohe = renshiUserName.getFourthLevel();
			}

			// 提交时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date endTime2 = null;
			if(endTime!="" && endTime!=null){
				try {
					endTime2 = sdf.parse(endTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 用户单位
			CustomerDepart c = htService.getUserAndDepartId(danweiId);
			String danweixingzhi = "";
			String yonghuxingzhi = "";
			if (c != null) {
				// 单位性质
				danweixingzhi = c.getXingzhi();
				// 获取单位编号
				Integer id2 = c.getdId();
				// 用户列表
				Customer customer = htService.getyonghuById(id2);
				if (customer != null) {
					// 用户性质
					yonghuxingzhi = customer.getXingzhi();
				}
			}

			StringBuilder productName2 = new StringBuilder();
			String productName="";
			// 产品列表
			List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
			if(itemPriceList!=null && !itemPriceList.isEmpty()){
				for (ItemPrice itemPrice : itemPriceList) {
					// Object b = itemPrice;
					productName2.append(",").append(itemPrice.getName());
				}
				// 所含产品
				 productName = productName2.substring(1);
			}
			
			/*// 开票总金额
			float f = 0.0f;
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
			if(faPiaoList!=null && !faPiaoList.isEmpty()){
				for (FaPiao faPiao : faPiaoList) {
					BigDecimal money = faPiao.getMoney();
					float moneyFloat = money.floatValue();
					f = moneyFloat + f;
				}
			}*/

			htService.updateContractSave(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2,
					agreementText, remarksText, payMethod, dealConditon, gangweiXingzhi, bumenmingcheng, shengfen,
					xibaohe, date, danweixingzhi, yonghuxingzhi, productName);
			json.setSuccess(true);
			json.setObj(contract);
			json.setMsg("保存合同成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存合同失败");
		}
		return json;
	}

	// -销售 创建一个新合同(提交)
	@ResponseBody
	@RequestMapping(value = "/addContractNomalSubmit")
	public Json addContractNomalSubmit(@RequestParam("id") Integer id, @RequestParam("company") String company, // 所属公司
			@RequestParam("depart") String depart, @RequestParam("cid") Integer cid,
			@RequestParam("didNum") Integer didNum, @RequestParam("contractMoney") Float contractMoney,
			@RequestParam("agreementNumber") String agreementNumber, @RequestParam("endTime") String endTime,
			@RequestParam("agreementText") String agreementText, @RequestParam("remarksText") String remarksText,
			@RequestParam("payMethod") String payMethod, @RequestParam("danweiId") Integer danweiId,
			HttpSession session) {
		Json json = new Json();
		try {
			if (id == null) {
				json.setSuccess(false);
				json.setMsg("请填写完合同、产品信息后再提交!");
				return json;
			} else {

				Contract contract = htService.getContracDetailById(id);
				
				
				StringBuilder productName2 = new StringBuilder();
				String productName="";
				// 产品列表
				List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
				if(itemPriceList!=null && !itemPriceList.isEmpty()){
					for (ItemPrice itemPrice : itemPriceList) {
						// Object b = itemPrice;
						productName2.append(",").append(itemPrice.getName());
					}
					// 所含产品
					 productName = productName2.substring(1);
				}
				
			/*	// 开票总金额
				float f = 0.0f;
				List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
				if(faPiaoList!=null && !faPiaoList.isEmpty()){
					for (FaPiao faPiao : faPiaoList) {
						BigDecimal money = faPiao.getMoney();
						float moneyFloat = money.floatValue();
						f = moneyFloat + f;
					}
				}*/
				
				Integer dealConditon = 0;
				// 查询出销售本人负责的项目单位
				SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
				String email = sessionInfo.getEmail();
				int saleId = sessionInfo.getId();
				// 根据销售本人ID查询出RenshiUserName对象
				RenshiUserName renshiUserName = htService.getRenshiUserName(saleId);

				// 岗位性质 部门名称 省份 细胞核(销售人员信息)
				String gangweiXingzhi = "";
				String bumenmingcheng = "";
				String shengfen = "";
				String xibaohe = "";
				if (renshiUserName != null) {
					gangweiXingzhi = renshiUserName.getFirstLevel();
					bumenmingcheng = renshiUserName.getSecondLevel();
					shengfen = renshiUserName.getThirdLevel();
					xibaohe = renshiUserName.getFourthLevel();
				}

				// 根据邮箱获取负责人 从用户单位表中查出的负责人
				String charger = htService.getOperator(email);
				// 要从session中获取用户信息 set 负责人(本人负责的合同)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date endTime2 = null;
				if(endTime!="" && endTime!=null){
					try {
						endTime2 = sdf.parse(endTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 提交时间
				Date date = new Date();
				CustomerDepart c = htService.getUserAndDepartId(danweiId);

				String danweixingzhi = "";
				String yonghuxingzhi = "";
				if (c != null) {
					// 单位性质
					danweixingzhi = c.getXingzhi();
					// 单位编号
					Integer id2 = c.getdId();
					// 用户列表
					Customer customer = htService.getyonghuById(id2);
					if (customer != null) {
						// 用户性质
						yonghuxingzhi = customer.getXingzhi();
					}

				}
				htService.updateContractSave(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2,
						agreementText, remarksText, payMethod, dealConditon, gangweiXingzhi, bumenmingcheng, shengfen,
						xibaohe, date, danweixingzhi, yonghuxingzhi, productName);
				json.setSuccess(true);
				json.setObj(contract);
				json.setMsg("创建合同成功");

			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("创建合同失败");
		}
		return json;
	}

	// 取消
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
	public String createContract(Model model, HttpSession session) {
		// 查询出销售本人负责的项目单位
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		// 销售本人负责的单位列表
		List<CustomerDepart> companyList = htService.getDepartListAboutSale(email);

		// 产品列表
		List<Object> productList = htService.getAllProduct();
		model.addAttribute("companyList", companyList);
		model.addAttribute("productList", productList);
		return "app/views/hetong/contractNormalSale";
	}

	// 创建一个空合同对象
	@ResponseBody
	@RequestMapping(value = "/addRealContract")
	public Json addRealContract() {
		Json json = new Json();
		try {
			Contract contract = new Contract();
			contract.setDealConditon(-1);
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

		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String email = sessionInfo.getEmail();
		/*
		 * //根据邮箱获取负责人 String responsibility = htService.getOperator(email);
		 */

		/*
		 * List<Contract> contractList =
		 * htService.getContractListSale(responsibility, page, size);
		 */
		List<Usercontracts> contractList = htService.getContractListSale(email, page, size);
		
		for (int i = 0; i < contractList.size(); i++) {
			Integer id = contractList.get(i).getId();//合同编号
			float totalKaiapiaoAmount = 0.0f;
			float totalHuikuanAmount = 0.0f;
			Date latestDate=null;
			
			// 发票列表
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
			if(faPiaoList.size()>0){
				//时间基准
				for (int j = 0; j < faPiaoList.size(); j++) {
					Date receivedpaymentsdate = faPiaoList.get(j).getReceivedpaymentsdate();//回款日期
					if(receivedpaymentsdate!=null && !"".equals(receivedpaymentsdate)){
						latestDate = receivedpaymentsdate;
						break;
					}
				}
				
				for (int j = 0; j < faPiaoList.size(); j++) {
					BigDecimal money = faPiaoList.get(j).getMoney();//发票金额
					if(money!=null){
						float moneyInt = money.floatValue(); 
						totalKaiapiaoAmount = moneyInt+totalKaiapiaoAmount;
					}
					
					BigDecimal huiKuan = faPiaoList.get(j).getHuiKuan();//回款金额
					if(huiKuan!=null){
						float huiKuanInt = huiKuan.floatValue(); 
						totalHuikuanAmount = huiKuanInt+totalHuikuanAmount;
					}
					
					Date receivedpaymentsdate = faPiaoList.get(j).getReceivedpaymentsdate();//回款日期
					if(latestDate!=null && receivedpaymentsdate!=null &&!"".equals(receivedpaymentsdate) && receivedpaymentsdate.after(latestDate)){
						latestDate = receivedpaymentsdate;
					}
				}
			}
			String totalHuikuanAmountStr = String.valueOf(totalHuikuanAmount);
			contractList.get(i).setKaipiaoMoney(totalKaiapiaoAmount);
			contractList.get(i).setReceivedAmount(totalHuikuanAmountStr);
			contractList.get(i).setReceiveTime(latestDate);
		}
		
		// 总合同数量
		int totalCountContract = htService.getTotalCountContractSale(email);

		PageBean<Usercontracts> pageBean = new PageBean<Usercontracts>();
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

	// 改变合同状态 删除合同对应的错误消息  更新开票金额和回款金额
	@ResponseBody
	@RequestMapping(value = "/updateContractCondition")
	public Json changeContractCondition(@RequestParam(value = "id") String id) {
		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			if (contractId > 0) {
				htService.updateContractCondition(contractId);
				htService.deleteErrorInfo(contractId);
                // 开票总金额
				/*	float f = 0.0f;
				//回款金额
				Float totalHuikuan = 0.0f;
				String huikuanString="";
				List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(contractId);
				if(faPiaoList!=null && !faPiaoList.isEmpty()){
					for (FaPiao faPiao : faPiaoList) {
						BigDecimal money = faPiao.getMoney();
						float moneyFloat = money.floatValue();
						f = moneyFloat + f;
						BigDecimal huiKuan = faPiao.getHuiKuan();
						if(huiKuan!=null){
							float huikuanFloat = huiKuan.floatValue();
							totalHuikuan = huikuanFloat + totalHuikuan;
							huikuanString = totalHuikuan.toString();
						}
					}
				}*/
				
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

	// (行政)更新合同信息
	@ResponseBody
	@RequestMapping(value = "/updateContractXingzheng")
	public Json updateContractXingzheng(@RequestParam(value = "id") String id,
			@RequestParam(value = "yinhuashui") String yinhuashui,
			@RequestParam(value = "huaizhangAmount") Float huaizhangAmount,
			@RequestParam(value = "company") String company, 
			@RequestParam(value = "guidangNum") String guidangNum, // 归档份数
			@RequestParam(value = "kuaijifenlei") String kuaijifenlei, //会计分类
			@RequestParam(value = "dengjiTime") String dengjiTime //登记时间
	) {

		Json json = new Json();
		int contractId;
		try {
			contractId = Integer.parseInt(id);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dengjiTime2 = sdf1.parse(dengjiTime);
			if (contractId > 0) {
				htService.updateContractXingzheng(yinhuashui,huaizhangAmount,contractId,guidangNum,kuaijifenlei,dengjiTime2); 
				json.setSuccess(true);
				json.setMsg("保存成功");
			}else{
				json.setSuccess(false);
				json.setMsg("保存失败");
			} 
	} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("保存失败");

		}
		return json;
	}

	//点击归档时间,生成归档编号，发送邮件 (更新合同表相关数据)
	@ResponseBody
	@RequestMapping(value = "/generateGuidangnum")
	public Json generateGuidangnum(@RequestParam(value = "date2") String guidangDate,
			                       @RequestParam(value = "company") String company,
			                       @RequestParam(value = "id") Integer contractId) {
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			
			// 生成归档编号
			String code = htService.getdanweicode(company);// cx
			Date date = new Date();
			
			String format = sdf.format(date);// 2017
			String substringformat = format.substring(2);// 17
			if (code != null) {
				String guidangMaxNum = htService.getguidangMaxNum(code);// DX170001
				String substring = guidangMaxNum.substring(4);// 0009
				int a = Integer.parseInt(substring);
				int b = a + 1;
				String bs = String.valueOf(b);
				if (bs.length() == 1) {
					bs = "000" + bs;
				}
				if (bs.length() == 2) {
					bs = "00" + bs;
				}
				if (bs.length() == 3) {
					bs = "0" + bs;
				}
				if (bs.length() == 4) {
					bs = "" + bs;
				}
				String guidangCode2 = code + substringformat + bs;
				//更新合同归档编号
				htService.updateContractGuidangnum(guidangDate, guidangCode2,contractId);
				json.setSuccess(true);
				json.setObj(guidangCode2);
				json.setMsg("生成归档编号为"+guidangCode2);
			} else {
				json.setSuccess(false);
				json.setMsg("生成归档编号失败!");
				return json;
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("生成归档编号失败!");
		}
		return json;
	}
	
	
	// 删除对应的错误信息
	@ResponseBody
	@RequestMapping(value = "/deleteErrorInfo")
	public Json deleteErrorInfo(@RequestParam(value = "id") String id) {

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

	// 合同未通过
	@ResponseBody
	@RequestMapping(value = "/updatecontractNoPass")
	public Json updatecontractNoPass(@RequestParam(value = "id") String id) {

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

	// 更新合同信息(回显页面)
	@ResponseBody
	@RequestMapping(value = "/updateContractNormal")
	public Json updateContractNormal(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "company") String company, @RequestParam(value = "depart") String depart,
			@RequestParam(value = "cid") Integer cid, @RequestParam(value = "didNum") Integer didNum,
			@RequestParam(value = "contractMoney") Float contractMoney,
			@RequestParam(value = "agreementNumber") String agreementNumber,
			@RequestParam(value = "endTime") String endTime,
			@RequestParam(value = "agreementText") String agreementText,
			@RequestParam(value = "remarksText") String remarksText,
			@RequestParam(value = "payMethod") String payMethod, @RequestParam(value = "state") String state) {
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date endTime2 = null;
			Integer stateInt = null;
	        if(endTime!=null && !"".equals(endTime.trim())){
	        	endTime2 = sdf.parse(endTime);
	        }		
			if(state!=null && !"".equals(state.trim())){
				stateInt = Integer.parseInt(state);
			}	
            
		/*	// 开票总金额
			float f = 0.0f;
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
			if(faPiaoList!=null && !faPiaoList.isEmpty()){
				for (FaPiao faPiao : faPiaoList) {
					BigDecimal money = faPiao.getMoney();
					float moneyFloat = money.floatValue();
					f = moneyFloat + f;
				}
			}*/
			
			if (stateInt == 1) {
				htService.updateContract(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2,
						agreementText, remarksText, payMethod);
				json.setSuccess(true);
				json.setMsg("更新合同信息成功");
			} else if (stateInt == 4) {
				// 由暂存状态改为未处理状态
				Integer changeStatus = 0;
				htService.updateZanCunContract(id, company, depart, cid, didNum, contractMoney, agreementNumber,
						endTime2, agreementText, remarksText, payMethod, changeStatus);
				json.setSuccess(true);
				json.setMsg("更新合同信息成功");
			} else {
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("更新合同信息失败");
		}
		return json;
	}

	// --------------产品-----------------------------------------

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
	public Json updateItemPrice(@RequestParam(value = "productID") Integer productID,
			@RequestParam(value = "name") String productName, @RequestParam(value = "amount") Float amount,
			@RequestParam(value = "begain") String begain, @RequestParam(value = "end") String end,
			@RequestParam(value = "money") Float money) {
		Json json = new Json();
		try {
			htService.updateItemPrice(productName, amount, begain, end, money, productID);
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
	@ResponseBody
	@RequestMapping(value = "/addFaPiao")
	public Json addFaPiao(FaPiao faPiao) {
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
	public Json doUpdateFapiao(@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "capitalMoney") String capitalMoney, @RequestParam(value = "company") String company,
			@RequestParam(value = "departMement") String departMement, @RequestParam(value = "type") String type,
			@RequestParam(value = "name") String name, @RequestParam(value = "date") String date,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "fapiaoID") Integer fapiaoID,
			@RequestParam(value = "receivedpaymentsdate",required=false) String receivedpaymentsdateStr,
			@RequestParam(value = "huiKuan",required=false) BigDecimal huiKuan, @RequestParam(value = "fundType",required=false) String fundType,
			@RequestParam(value = "account",required=false) String account,@RequestParam(value = "yujihuikuanDate") String yujihuikuanDateStr,
			@RequestParam(value = "caiwuMonth",required=false) String caiwuMonth,@RequestParam(value = "hetongId",required=false) String hetongId) {
		Json json = new Json();
		try {
			Integer hetongId_=null; 
			if(hetongId!=null){
				hetongId_ = Integer.parseInt(hetongId);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			Date receivedpaymentsdate = null;
			Date yujihuikuanDate = null;
			if(receivedpaymentsdateStr!=null){
				receivedpaymentsdate = sdf.parse(receivedpaymentsdateStr);
			}
			date1 = sdf.parse(date);
			yujihuikuanDate = sdf.parse(yujihuikuanDateStr);
			
			htService.updateFapiao(money, capitalMoney, company, departMement, type, name, date1, remark, fapiaoID,
					receivedpaymentsdate, huiKuan, fundType, account,yujihuikuanDate,caiwuMonth,hetongId_);
			json.setSuccess(true);
			json.setMsg("修改发票成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改发票失败");
		}
		return json;

	}
    //doUpdateFapiaoSale  销售更新发票信息
	@ResponseBody
	@RequestMapping(value = "/doUpdateFapiaoSale")
	public Json doUpdateFapiaoSale(@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "capitalMoney") String capitalMoney, @RequestParam(value = "company") String company,
			@RequestParam(value = "departMement") String departMement, @RequestParam(value = "type") String type,
			@RequestParam(value = "name") String name, @RequestParam(value = "date") String date,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "fapiaoID") Integer fapiaoID,
			@RequestParam(value = "yujihuikuanDate") String yujihuikuanDateStr
			) {
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			
			Date yujihuikuanDate = null;
			date1 = sdf.parse(date);
			yujihuikuanDate = sdf.parse(yujihuikuanDateStr);
			 
			htService.updateFapiaoSale(money, capitalMoney, company, departMement, type, name, date1, remark, fapiaoID,yujihuikuanDate);
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
	@ResponseBody
	@RequestMapping(value = "/addFaHuo")
	public Json addFaHuo(Fahuo fahuo) {
		Json json = new Json();
		try {
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
	public Json selectFahuo(@RequestParam(value = "fahuoId") Integer fahuoId) {
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

	//更新发货信息
	@ResponseBody
	@RequestMapping(value = "/doUpdateFahuo")
	public Json doUpdateFahuo(@RequestParam(value = "fahuoId") Integer fahuoId,
			@RequestParam(value = "mailno",required=false) String mailno, 
			@RequestParam(value = "d_contact") String d_contact,
			@RequestParam(value = "d_tel") String d_tel,
			@RequestParam(value = "d_address") String d_address, 
			@RequestParam(value = "jDate") String jDate,
			@RequestParam(value = "postMethod") String postMethod,
			@RequestParam(value = "content") String content) {
		Json json = new Json();
		try {
			htService.updateFahuo(mailno, d_contact, d_tel, d_address, jDate, postMethod, content, fahuoId);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;
	}

	//销售更新发货信息
	@ResponseBody
	@RequestMapping(value = "/doUpdateFahuoSale")
	public Json doUpdateFahuoSale(@RequestParam(value = "fahuoId") Integer fahuoId,
			@RequestParam(value = "mailno",required=false) String mailno, @RequestParam(value = "d_contact") String d_contact,
			@RequestParam(value = "d_tel") String d_tel,
			/* @RequestParam(value ="d_company") String d_company, */
			@RequestParam(value = "d_address") String d_address, @RequestParam(value = "jDate") String jDate,
			@RequestParam(value = "postMethod") String postMethod,
			// @RequestParam(value ="remark") String remark,
			@RequestParam(value = "content") String content) {
		Json json = new Json();
		try {
			htService.updateFahuo(mailno, d_contact, d_tel, d_address, jDate, postMethod, content, fahuoId);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;
	}
	
	//行政更新发货信息(可以编辑合同编号)
	@ResponseBody
	@RequestMapping(value = "/doUpdateFahuoXingzheng")
	public Json doUpdateFahuoXingzheng(
			@RequestParam(value = "fahuoId") Integer fahuoId,
			@RequestParam(value = "mailno") String mailno, 
			@RequestParam(value = "d_contact") String d_contact,
			@RequestParam(value = "d_tel") String d_tel,
			@RequestParam(value = "d_address") String d_address, 
			@RequestParam(value = "jDate") String jDate,
			@RequestParam(value = "postMethod") String postMethod,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "hetongCode") String hetongCode
			) {
		Json json = new Json();
		try {
			Integer hetongCodeInt=null;
			try {
				hetongCodeInt = Integer.valueOf(hetongCode);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("请输入正确的合同编号!");
			}
			htService.updateFahuoXingzheng(mailno, d_contact, d_tel, d_address, jDate, postMethod, content, fahuoId,hetongCodeInt);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;
	}
	
	
	// 获取发件地
	@ResponseBody
	@RequestMapping(value = "/getFajiandi")
	public Json getFajiandi(String company) {
		Json json = new Json();
		try {
			if (company == null || company.equals("")) {
				json.setSuccess(false);
				return json;
			} else {
				CompanyInfo companyInfo = htService.getFajiandi(company);
				json.setSuccess(true);
				json.setObj(companyInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// 添加错误消息 ContractVO 始终保证每条合同对应一条错误消息
	@ResponseBody
	@RequestMapping(value = "/addErrorInfo")
	public Json addErrorInfo(ContractVO contractVO) {
		Json json = new Json();
		try {
			//合同编号
			Integer id = contractVO.getId();
			if(id!=null){
			htService.deleteErrorInfo(id);
			}
			//创建
			htService.addErrorInfo(contractVO);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// 获取总发票金额
	@ResponseBody
	@RequestMapping(value = "/getTotalFapiaoAmount")
	public Json getTotalFapiaoAmount(@RequestParam(value = "id") Integer id) {
		Json json = new Json();
		try {
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId2(id);
			int a = 0;
			for (FaPiao faPiao : faPiaoList) {
				BigDecimal money = faPiao.getMoney();
				int b = money.intValue();
				a = a + b;
			}
			json.setSuccess(true);
			json.setObj(a);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// updateHuikuanAndDate  更新回款金额和日期
	@ResponseBody
	@RequestMapping(value = "/updateHuikuanAndDate")
	public Json updateHuikuanAndDate(@RequestParam(value = "contractId") Integer contractId,
			@RequestParam(value = "total") String total, 
			@RequestParam(value = "latestDate2") String latestDate2,
			@RequestParam(value = "totalKaiPiao") String totalKaiPiao) {
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 float totalKaiPiao_ = Float.parseFloat(totalKaiPiao);
			Date latestDate = null;
			try {
				latestDate = sdf.parse(latestDate2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			htService.updateHuikuanAndDate(contractId, total, latestDate,totalKaiPiao_);
			json.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// updateDealCondition 由审核通过状态改为合同完结状态
	@ResponseBody
	@RequestMapping(value = "/updateDealCondition")
	public Json updateDealCondition(@RequestParam(value = "id") Integer contractId) {
		Json json = new Json();
		try {
			htService.updateDealCondition(contractId);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// 获取大写金额 getDaxieAmount
	@ResponseBody
	@RequestMapping(value = "/getDaxieAmount")
	public Json getDaxieAmount(@RequestParam(value = "kaipiaoAmount") String kaipiaoAmount) {
		Json json = new Json();
		try {
			BigDecimal kaipiaoAmount_ = new BigDecimal(kaipiaoAmount);

			String number2cnMontrayUnit = SwitchMoneyUtil.number2CNMontrayUnit(kaipiaoAmount_);
			json.setSuccess(true);
			json.setObj(number2cnMontrayUnit);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("请输入正确的开票金额！");
			json.setSuccess(false);
		}
		return json;
	}
	//getKaipiaoOption 根据开票公司显示对应品名
	@ResponseBody
	@RequestMapping(value = "/getKaipiaoOption")
	public Json getKaipiaoOption(@RequestParam(value = "company") String company) {
		Json json = new Json();
		System.out.println("公司名称：" + company);
		try {
			List<Object> kaipiaoList = htService.getKaipiaoOption(company);
			json.setObj(kaipiaoList);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	//getKaipiaoOption 根据开票公司显示对应品名
	@ResponseBody
	@RequestMapping(value = "/getKaipiaoOptionSearch")
	public Json getKaipiaoOptionSearch(@RequestParam(value = "company") String company,@RequestParam(value = "pinmingSearch") String pinmingSearch) {
		Json json = new Json();
		try {
			List<Object> kaipiaoList = htService.getKaipiaoOptionSearch(company,pinmingSearch);
			json.setObj(kaipiaoList);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	//获取该合同下的产品,发票,快递数量
	@ResponseBody
	@RequestMapping(value = "/getChanpinFapiaoFahuoNum")
	public Json getChanpinFapiaoFahuoNum(@RequestParam(value="id") Integer id) {
		Json json = new Json();
		// 产品列表  
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
		int chanpinSize = itemPriceList.size();
		// 发票列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id);
		int fapiaoSize = faPiaoList.size();
		// 快递列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id);
		int fahuoSize = fahuoList.size();
		
		List<Integer> numList = new ArrayList<Integer>();
		numList.add(chanpinSize);
		numList.add(fapiaoSize);
		numList.add(fahuoSize);
		json.setObj(numList);
		return json;
	}
	
	//添加合同概要
	@ResponseBody
	@RequestMapping(value = "/addHtgaiyao")
	public Json addHtgaiyao(@RequestParam(value="htgaiyao") String htgaiyao,@RequestParam(value="ctid") Integer ctid) {
		Json json = new Json();
		try {
			Contract contract = htService.getContracDetailById(ctid);
			String agreementText = contract.getAgreementText();//合同内容
			if(agreementText==null){
				agreementText = "";
			}
			String htgaiyaofinal = agreementText+htgaiyao;
			htService.updateContractGaiyao(ctid,htgaiyaofinal);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	//获取合同概要
	@ResponseBody
	@RequestMapping(value = "/getHtgaiyao")
	public Json getHtgaiyao(@RequestParam(value="id") Integer id) {
		Json json = new Json();
		try {
			Contract contract = htService.getContracDetailById(id);
			String agreementText = contract.getAgreementText();//合同内容
			if(agreementText==null){
				agreementText = "";
			}
			json.setObj(agreementText);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	//更新合同概要
	@ResponseBody
	@RequestMapping(value = "/updateHtgaiyao")
	public Json updateHtgaiyao(@RequestParam(value="id") Integer id,@RequestParam(value="disanfangChanpin") String disanfangChanpin) {
		Json json = new Json();
		try {
			 htService.updateContractGaiyao(id,disanfangChanpin);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	
	/*
	 * //添加错误消息
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/addErrorInfo") public Json addErrorInfo(String
	 * shuxingming,String errorInfo,Integer contractId) { Json json = new
	 * Json(); try { //没有合同错误信息对象 创建 有 更新 ContractVO contractVO =
	 * htService.getContractVObyhtid(contractId); if(contractVO==null){ //创建
	 * htService.addErrorInfo(shuxingming,errorInfo,contractId);
	 * json.setSuccess(true); }else{ //更新 } } catch (Exception e) {
	 * e.printStackTrace(); json.setSuccess(false); } return json; }
	 */
}
