package com.chaoxing.oa.controller.pub;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.chaoxing.oa.entity.po.commmon.OrganizationStructure;
import com.chaoxing.oa.entity.po.employee.UserName;
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
import com.chaoxing.oa.entity.po.view.Usercontracts;
import com.chaoxing.oa.entity.po.view.Usercontracts2;
import com.chaoxing.oa.service.HtService;
import com.chaoxing.oa.util.data.SwitchMoneyUtil;
import com.chaoxing.oa.util.system.ResourceUtil;

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
	// --销售进入创建合同页面
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
	
	// --获取用户和单位ID
	@ResponseBody
	@RequestMapping(value = "/getUserAndDepartId")
	public Json getUserAndDepartId(Integer danweiId, HttpSession session) {
		//danweiId 用户单位主键
		Json json = new Json();
		try {
			// 查询出销售本人负责的项目单位
			CustomerDepart c = htService.getUserAndDepartId(danweiId);
			json.setSuccess(true);
			json.setObj(c);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
    //--获取所属公司信息,公司的自动检索功能
	@ResponseBody
	@RequestMapping(value = "/getCompanyInfo")
	public Json getCompanyInfo() {
		Json json = new Json();
		try {
			List<Object> companyList =  htService.getCompanyInfo();
			json.setSuccess(true);
			json.setObj(companyList);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}

	// --销售点击添加产品时创建一个空合同对象
	@ResponseBody
	@RequestMapping(value = "/addRealContract")
	public Json addRealContract() {
		Json json = new Json();
		try {
			Contract contract = new Contract();
			contract.setDealConditon(-1);
			contract.setType(1);
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
	
	// --------------第三方产品中合同概要-----------------------------------------
	//--销售添加第三方产品时添加合同概要
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
	
	
	//--第三方产品回显,根据合同编号,获取合同概要
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
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	//--更新第三方产品时更新合同概要
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
	
	// --------------产品-----------------------------------------
	// --添加产品
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

	// --删除产品
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

	// --查询产品,根据id获得产品信息(表单回显),此处取名不恰当
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

	// --修改产品
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
	
	// --------------发票品名-----------------------------------------
	//--根据开票公司查询下面所有发票品名
	@ResponseBody
	@RequestMapping(value = "/getKaipiaoOption")
	public Json getKaipiaoOption(@RequestParam(value = "company") String company) {
		Json json = new Json();
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
	
	// --发票品名搜索
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
	
	// --------------发票-----------------------------------------
	// --添加发票
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

	// --删除发票
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

	// --查询发票(表单回显)
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
	
	// --行政更新发票(字段数比销售要多,分两个方法)
	@ResponseBody
	@RequestMapping(value = "/doUpdateFapiao") 
	public Json doUpdateFapiao(@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "capitalMoney") String capitalMoney, @RequestParam(value = "company") String company,
			@RequestParam(value = "departMement") String departMement, @RequestParam(value = "type") String type,
			@RequestParam(value = "name") String name, @RequestParam(value = "date") String date,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "fapiaoID") Integer fapiaoID,
			@RequestParam(value = "receivedpaymentsdate") String receivedpaymentsdateStr,
			@RequestParam(value = "huiKuan") BigDecimal huiKuan, @RequestParam(value = "fundType") String fundType,
			@RequestParam(value = "account") String account,@RequestParam(value = "yujihuikuanDate") String yujihuikuanDateStr,
			@RequestParam(value = "caiwuMonth") String caiwuMonth,@RequestParam(value = "hetongId") String hetongId) {
		
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//合同id只有行政可以修改
			Integer hetongId_=null; 
			Date date1 = null;
			Date receivedpaymentsdate = null;
			Date yujihuikuanDate = null;
			if(hetongId!=null){
				hetongId_ = Integer.parseInt(hetongId);
			}
			if(date!=null){
				date1 = sdf.parse(date);;
			}
			if(receivedpaymentsdateStr!=null){
				receivedpaymentsdate = sdf.parse(receivedpaymentsdateStr);
			}
			if(yujihuikuanDateStr!=null){
				yujihuikuanDate = sdf.parse(yujihuikuanDateStr);
			}
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
	
	// -- 销售更新发票信息
	@ResponseBody
	@RequestMapping(value = "/doUpdateFapiaoSale")
	public Json doUpdateFapiaoSale(@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "capitalMoney") String capitalMoney, @RequestParam(value = "company") String company,
			@RequestParam(value = "departMement") String departMement, @RequestParam(value = "type") String type,
			@RequestParam(value = "name") String name, @RequestParam(value = "date") String date,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "fapiaoID") Integer fapiaoID,
			@RequestParam(value = "yujihuikuanDate") String yujihuikuanDateStr) {
		
		Json json = new Json();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			Date yujihuikuanDate = null;
			if(date!=null){
				date1 = sdf.parse(date);
			}
			if(yujihuikuanDateStr!=null){
				yujihuikuanDate = sdf.parse(yujihuikuanDateStr);
			}
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
	
	// --获取快递发件地
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
				if(companyInfo!=null){
					json.setSuccess(true);
					json.setObj(companyInfo);
				}else{
					json.setSuccess(false);
					return json;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
	// --------------快递-----------------------------------------
	// --添加快递
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

	// --删除快递
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

	// --查询快递(回显)
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

	// --销售更新发货信息,行政比销售多3个字段,运单号mailno,邮寄日期jDate和合同编号hetongCode
	@ResponseBody
	@RequestMapping(value = "/doUpdateFahuo")
	public Json doUpdateFahuo(
			@RequestParam(value = "fahuoId") Integer fahuoId,
			@RequestParam(value = "d_contact") String d_contact,
			@RequestParam(value = "d_tel") String d_tel,
			@RequestParam(value = "d_address") String d_address, 
			@RequestParam(value = "postMethod") String postMethod,
			@RequestParam(value = "postAddress") String postAddress,
			@RequestParam(value = "content") String content) {
		
		Json json = new Json();
		try {
			htService.updateFahuo(null, d_contact, d_tel, d_address, null, postMethod, content, fahuoId, postAddress);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;
	}

	// --行政更新发货信息(可以编辑合同编号)
	@ResponseBody
	@RequestMapping(value = "/doUpdateFahuoXingzheng")
	public Json doUpdateFahuoXingzheng(
			@RequestParam(value = "fahuoId") Integer fahuoId,
			@RequestParam(value = "d_contact") String d_contact,
			@RequestParam(value = "d_tel") String d_tel,
			@RequestParam(value = "d_address") String d_address, 
			@RequestParam(value = "postMethod") String postMethod,
			@RequestParam(value = "postAddress") String postAddress,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "jDate") String jDate,
			@RequestParam(value = "hetongCode") String hetongCode,
			@RequestParam(value = "mailno") String mailno ) {
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
			htService.updateFahuoXingzheng(mailno, d_contact, d_tel, d_address, jDate, postMethod, content, fahuoId, hetongCodeInt, postAddress);
			json.setSuccess(true);
			json.setMsg("修改快递成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改快递失败");
		}
		return json;
	}
	
	// --销售 创建一个新合同(保存合同和提交合同),实际是更新
	//dealConditon  4为保存(暂存),0为提交(未处理) 
	@ResponseBody
	@RequestMapping(value = "/addContractNormal")
	public Json addContractNormal(@RequestParam("id") Integer id, @RequestParam(value="company",required=false) String company,
			@RequestParam("depart") String depart, @RequestParam("cid") Integer cid,
			@RequestParam("didNum") Integer didNum, @RequestParam("contractMoney") Float contractMoney,
			@RequestParam("agreementNumber") String agreementNumber, @RequestParam("endTime") String endTime,
			@RequestParam("agreementText") String agreementText, @RequestParam("remarksText") String remarksText,
			@RequestParam("payMethod") String payMethod, @RequestParam("danweiId") Integer danweiId,
			@RequestParam("dealConditon") Integer dealConditon, HttpSession session) { 
		Json json = new Json();
		try {
			if (id == null) {
				json.setSuccess(false);
				json.setMsg("请先填写完合同、产品信息再保存！");
				return json;
			}
			// 查询出销售本人负责的项目单位
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			int saleId = sessionInfo.getId();
			// 根据销售本人ID查询出RenshiUserName对象
			OrganizationStructure orgStructure = htService.getOrgStructure(saleId);
			// 岗位性质 部门名称 省份 细胞核
			String gangweiXingzhi = "";
			String bumenmingcheng = "";
			String shengfen = "";
			String xibaohe = "";
			if (orgStructure != null) {
				gangweiXingzhi = orgStructure.getFirstLevel();
				bumenmingcheng = orgStructure.getSecondLevel();
				shengfen = orgStructure.getThirdLevel();
				xibaohe = orgStructure.getFourthLevel();
			}
			// 提交时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date endTime2 = null;
			if(endTime!="" && endTime!=null){
				endTime2 = sdf.parse(endTime);
			}
			// 用户单位
			CustomerDepart c = htService.getUserAndDepartId(danweiId);
			String danweixingzhi = "";
			String yonghuxingzhi = "";
			if (c != null) {
				danweixingzhi = c.getXingzhi(); // 单位性质
				Integer id2 = c.getdId(); // 获取单位编号
				Customer customer = htService.getyonghuById(id2); // 用户列表
				if (customer != null) {
					yonghuxingzhi = customer.getXingzhi(); // 用户性质
				}
			}
		//	Integer dealConditon = 4; //暂存状态
			htService.updateContractSave(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2,
					agreementText, remarksText, payMethod, dealConditon, gangweiXingzhi, bumenmingcheng, shengfen,
					xibaohe, date, danweixingzhi, yonghuxingzhi);
			json.setSuccess(true);
			json.setMsg("保存合同成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存合同失败");
		}
		return json;
	}

//  --销售在修改合同页面点击提交(提交的合同状态可能是暂存和未通过,提交后都改成0 未处理)
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
			@RequestParam(value = "payMethod") String payMethod) {
		
		Json json = new Json();
		try {
			Integer dealConditon = 0;  //暂存和未通过的合同提交,状态改为0
			Contract contract = htService.getContracDetailById(id);
			if(contract.getType()==2){  //如果是副本合同提交,把副本合同和原合同的状态都置为5
				dealConditon = 5;
				htService.updateDealConditon(contract.getCopyId(),5);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date endTime2 = null;
	        if(endTime!=null && !"".equals(endTime.trim())){
	        	endTime2 = sdf.parse(endTime);
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
			htService.updateContract(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime2,
					agreementText, remarksText, payMethod, dealConditon);
			json.setSuccess(true);
			json.setMsg("更新合同信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("更新合同信息失败");
		}
		return json;
	}
	
	// --获取该合同下的产品,发票,快递数量,销售提交合同时验证是否填写了完整信息
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
	
	// --销售创建合同页面按取消按钮,删除其下产品,发票,快递,并删除合同
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
	
	// --销售查看合同列表点击详情,未处理 审核已通过 合同完结(这三种状态返回的页面)
	@RequestMapping(value = "/contractDetailForSale")
	public String contractDetailForSale(Model model, @RequestParam("id") Integer id, HttpSession session)
			throws ParseException {
		Contract contract = htService.getContracDetailById(id);
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id); // 产品列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id); // 发票列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id); // 快递列表
		ContractVO contractVO = htService.getContractVOByid(id); // 根据合同编号查询出错误消息
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

	//  --销售查看合同列表点击详情,审核未通过 暂存(这二种状态返回的页面)
	@RequestMapping(value = "/contractDetailForSale2")
	public String contractDetailForSale2(Model model, @RequestParam("id") Integer id, HttpSession session) throws ParseException {
		Contract contract = htService.getContracDetailById(id);
		List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id); // 产品列表
		List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id); // 发票列表
		List<Fahuo> fahuoList = htService.getFaHuoByContractId(id); // 快递列表
		ContractVO contractVO = htService.getContractVOByid(id); // 根据合同编号查询出错误消息
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
	//	model.addAttribute("state", state);
		return "app/views/hetong/contractNormalDetailSale2";
	}
	
	// --行政查看合同详情  回显 产品 发票 快递 
		@RequestMapping(value = "/contractDetail")
		public String contractDetail(Model model, @RequestParam("id") Integer id, HttpSession session)throws ParseException {
			Contract contract = htService.getContracDetailById(id);
			// 根据合同拿到所属公司
			String substringCompany = "";
			if (contract != null && StringUtils.isNotBlank(contract.getCompany())) {
				substringCompany = contract.getCompany().substring(0, 2);
			}
			List<Area> zhanghuList = htService.getZhanghu(substringCompany);
		//	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		//	String email = sessionInfo.getEmail();
			// 用户单位ID
			Integer cid = contract.getCid();
			CustomerDepart customerDepart = htService.getCustomerDepartByoperator(cid);
			String emailSale = customerDepart.getEmail();  //不能从session中取,session中是行政的邮箱
			// 根据邮箱拿联系电话
			UserName userName = htService.getTelephoneByEmail(emailSale);
			OrganizationStructure orgStructure = htService.getOrgStructureById(userName.getDepartmentId());
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
			model.addAttribute("phoneNumber", userName.getPhoneNumber());
			model.addAttribute("orgStructure", orgStructure);
			model.addAttribute("zhanghuList", zhanghuList);
			model.addAttribute("productList", productList);
			//销售邮箱
			model.addAttribute("email", emailSale);
			return "app/views/hetong/contractNormal";
		}
		
	// --通过合同,改变合同状态为2 删除合同对应的错误消息 
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

	// --获取大写金额 getDaxieAmount
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
	
	// --根据合同ID把对应合同删除
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

	// --(行政)更新合同信息
	@ResponseBody
	@RequestMapping(value = "/updateContractXingzheng")
	public Json updateContractXingzheng(@RequestParam(value = "id") String id,
			@RequestParam(value = "yinhuashui") String yinhuashui,
			@RequestParam(value = "huaizhangAmount") Float huaizhangAmount,
	//		@RequestParam(value = "company") String company, 
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

	// --点击归档时间,生成归档编号，发送邮件 (更新合同表相关数据)
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
	
	// --删除对应的错误信息
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

	// --合同未通过
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


	// --添加错误消息 ContractVO 始终保证每条合同对应一条错误消息,九个字段
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

	/*// 获取总发票金额
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
	}*/

	// --由审核通过状态改为合同完结状态(3)
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

	// 销售人员查看合同列表(只查看本人负责的合同)
		@RequestMapping(value = "/contractListSale")
		public String contractListSale(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "size", defaultValue = "10") int size, HttpSession session) {

			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			String email = sessionInfo.getEmail();
			List<Usercontracts2> contractList = htService.getContractListSale(email, page, size);
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
				//所含产品
				List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
				StringBuilder productName2 = new StringBuilder();
				String productName="";
				if(itemPriceList!=null && !itemPriceList.isEmpty()){
					for (ItemPrice itemPrice : itemPriceList) {
						productName2.append(",").append(itemPrice.getName());
					}
					 productName = productName2.substring(1); // 所含产品
				}
				contractList.get(i).setProduct(productName);
			}
			// 总合同数量
			int totalCountContract = htService.getTotalCountContractSale(email);
			PageBean<Usercontracts2> pageBean = new PageBean<Usercontracts2>();
			pageBean.init(size, totalCountContract, page);
			pageBean.setList(contractList);
		//	model.addAttribute("contractList", contractList);
			model.addAttribute("pageBean", pageBean);
			return "app/views/hetong/contractListSale";
		}

		/*// 行政查看所有合同(根据session信息)
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
				//所含产品
				List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
				StringBuilder productName2 = new StringBuilder();
				String productName="";
				if(itemPriceList!=null && !itemPriceList.isEmpty()){
					for (ItemPrice itemPrice : itemPriceList) {
						// Object b = itemPrice;
						productName2.append(",").append(itemPrice.getName());
					}
					// 所含产品
					 productName = productName2.substring(1);
			    }
				contractList.get(i).setProduct(productName);
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
			 PageBean<Contract> pageBean = new PageBean<Contract>(); 
			pageBean.init(size, totalCountContract, page);
			pageBean.setList(contractList);
		//	model.addAttribute("contractList", contractList);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("unhandledCount", unhandledCount);
			model.addAttribute("companyInfoList", companyInfoList);
			model.addAttribute("propertyList", propertyList);
			model.addAttribute("productList", productList);
			return "app/views/hetong/contractList";
		}*/

		// --行政查看所有合同,搜索和查看共用这一个方法
		@RequestMapping(value = "/contractList")  
		public String contractList(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "size", defaultValue = "10") int size,
				@RequestParam(value = "purchaseCom", defaultValue = "") String purchaseCom, // 购买单位
				@RequestParam(value = "gongsi", defaultValue = "全部") String gongsi, // 所属公司
				@RequestParam(value = "danwei", defaultValue = "全部") String danwei, // 单位性质
				@RequestParam(value = "group", defaultValue = "") String group, // 小组
				@RequestParam(value = "responsibility", defaultValue = "") String responsibility, // 项目负责人
				@RequestParam(value = "userId", defaultValue = "") Integer userId,
				@RequestParam(value = "zhuangtai", defaultValue = "-10") String stateStr// 合同状态,前台传-10代表全部
		) throws ParseException {
			try {
				Integer state = -10;
				if (stateStr != null && !"".equals(stateStr)) {
					state = Integer.parseInt(stateStr);
				}
				List<Usercontracts2> contractList = htService.getContractListConditionTest(purchaseCom, gongsi, danwei,group, responsibility, userId, state, page, size);
				// 查询开票金额,回款金额,最新回款时间
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
					//所含产品
					List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id);
					StringBuilder productName = new StringBuilder();
					if(itemPriceList!=null && !itemPriceList.isEmpty()){
						for (ItemPrice itemPrice : itemPriceList) {
							productName.append(",").append(itemPrice.getName());
						}
				    }
					String totalHuikuanAmountStr = String.valueOf(totalHuikuanAmount);
					contractList.get(i).setKaipiaoMoney(totalKaiapiaoAmount);
					contractList.get(i).setReceivedAmount(totalHuikuanAmountStr);
					contractList.get(i).setReceiveTime(latestDate);
					String realproductName="";
					if(productName.length()>0){
						realproductName = productName.substring(1);
					}
					contractList.get(i).setProduct(realproductName);
			    }
				int conditionCountContract = htService.getConditionCountContract(purchaseCom, gongsi, danwei, group,responsibility, userId, state);
				PageBean<Usercontracts2> pageBean = new PageBean<Usercontracts2>();
				pageBean.init(size, conditionCountContract, page);
				pageBean.setList(contractList);
				// 所属公司列表信息
				List<CompanyInfo> companyInfoList = htService.getCompanyList();
				// 单位性质列表信息
				List<Contract> propertyList = htService.getPropertyList();
				// 产品名称列表信息
				List<ItemPrice> productList = htService.getProductList();
				// 查询出未处理合同的数量
				int unhandledCount = htService.getUnhandledContract();
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

		/*// 获取未处理合同列表
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
		}*/
/*-----------------------注意----------------------在controler中开了事物且read-only="false"(默认) hibernate才会自动保存---------------------------------------------*/
		// --销售申请修改合同
		@RequestMapping(value = "/applyUpdate")  
		public String applyUpdate(Model model, @RequestParam("id") Integer id, HttpSession session) throws ParseException {
			Contract contract = htService.getContracDetailById(id);
			contract.setDealConditon(-1); //合同没正式提交前是垃圾合同
			contract.setType(2);; //标记为副本合同
			contract.setCopyId(id);; //标记隶属于哪个合同的副本
			htService.addContractNomal(contract);  //生成副本,id会自动改变
			List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id); // 产品列表
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id); // 发票列表
			List<Fahuo> fahuoList = htService.getFaHuoByContractId(id); // 快递列表
			for (ItemPrice itemPrice : itemPriceList) {
				itemPrice.setCtid(contract.getId());
				htService.addItemPrice(itemPrice);
			}
			for (FaPiao faPiao : faPiaoList) {
				faPiao.setHetongNumber(contract.getId());
				htService.addFaPiao(faPiao);
			}
			for (Fahuo fahuo : fahuoList) {
				fahuo.setHetongCode(contract.getId());
				htService.addFahuo(fahuo);
			}
		//	ContractVO contractVO = htService.getContractVOByid(id); // 根据合同编号查询出错误消息
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
		//	model.addAttribute("ContractVO", contractVO);
			model.addAttribute("productList", productList);
		//	model.addAttribute("state", state);
			return "app/views/hetong/contractNormalDetailSale2";
		}
		
		 //--销售查看副本合同
		@ResponseBody
		@RequestMapping(value = "/getCopyContract")
		public Json getCopyContract(int copyId) {
			Json json = new Json();
			try {
				Contract contract =  htService.getCopyContract(copyId);
				json.setSuccess(true);
				json.setObj(contract);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
			}
			return json;
		}
		
		// --行政查看申请修改的合同对照页面(状态为5的合同)
		@RequestMapping(value = "/contractCompare")
		public String contractDetailForapplyupdate(Model model, @RequestParam("id") Integer id, HttpSession session)throws ParseException {
			Contract contract = htService.getContracDetailById(id);
			List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(id); // 产品列表
			List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(id); // 发票列表
			List<Fahuo> fahuoList = htService.getFaHuoByContractId(id); // 快递列表
			ContractVO contractVO = htService.getContractVOByid(id); // 根据合同编号查询出错误消息
			model.addAttribute("contract", contract);
			model.addAttribute("itemPriceList", itemPriceList);
			model.addAttribute("faPiaoList", faPiaoList);
			model.addAttribute("fahuoList", fahuoList);
			model.addAttribute("ContractVO", contractVO);
			//副本信息
			Contract contractCopy =  htService.getCopyContract(id);
			int copyId = contractCopy.getId();
			List<ItemPrice> itemPriceListCopy = htService.getItemPriceByContractId(copyId); // 产品列表
			List<FaPiao> faPiaoListCopy = htService.getFaPiaoByContractId(copyId); // 发票列表
			List<Fahuo> fahuoListCopy = htService.getFaHuoByContractId(copyId); // 快递列表
			ContractVO contractVOCopy = htService.getContractVOByid(copyId); // 根据合同编号查询出错误消息
			model.addAttribute("contractCopy", contractCopy);
			model.addAttribute("itemPriceListCopy", itemPriceListCopy);
			model.addAttribute("faPiaoListCopy", faPiaoListCopy);
			model.addAttribute("fahuoListCopy", fahuoListCopy);
			model.addAttribute("ContractVOCopy", contractVOCopy);
			return "app/views/hetong/contractCompare";
		}
		
		// --行政未通过合同的修改申请(修改合同状态为6),填写错误信息
		@ResponseBody
		@RequestMapping(value = "/notAllowUpdateContract")
		public Json notAllowUpdateContract(Model model, @RequestParam("id") Integer id)throws ParseException {
			Json json = new Json();
			try {
				htService.updateDealConditon(id, 6);
				Contract contractCopy =  htService.getCopyContract(id);
				htService.updateDealConditon(contractCopy.getId(), 6);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setMsg("操作失败");
			}
			return json;
		}
		
		// --行政通过合同的修改申请(修改合同状态为2),覆盖合同
		@ResponseBody
		@RequestMapping(value = "/allowUpdateContract")
		public Json allowUpdateContract(Model model, @RequestParam("id") Integer id)throws ParseException {
			Json json = new Json();
			try {
			//	Contract contract = htService.getContracDetailById(id);
				Contract contractCopy =  htService.getCopyContract(id);
				int copyId = contractCopy.getId();
				//先删除原合同下的产品,发票,快递
				htService.deleteItemPriceByHtid(id);
				htService.deleteFapiaoByHtid(id);
				htService.deleteFahuoByHtid(id);
			//	htService.deleteContract(id);
				//再添加副本合同下的产品,发票,快递
				List<ItemPrice> itemPriceList = htService.getItemPriceByContractId(copyId); // 产品列表
				List<FaPiao> faPiaoList = htService.getFaPiaoByContractId(copyId); // 发票列表
				List<Fahuo> fahuoList = htService.getFaHuoByContractId(copyId); // 快递列表
				for (ItemPrice itemPrice : itemPriceList) {
					itemPrice.setCtid(id);
				//	htService.addItemPrice(itemPrice);
					htService.updateItemPrice(itemPrice);
				}
				for (FaPiao faPiao : faPiaoList) {
					faPiao.setHetongNumber(id);
				//	htService.addFaPiao(faPiao);
					htService.updateFaPiao(faPiao);
				}
				for (Fahuo fahuo : fahuoList) {
					fahuo.setHetongCode(id);
				//	htService.addFahuo(fahuo);
					htService.updateFahuo(fahuo);
				}
				//更新原合同并改变状态,销售只能修改这十个字段,所以只需更新这十个字段
				String company = contractCopy.getCompany();
				String depart = contractCopy.getDepart();
				Integer cid = contractCopy.getCid(); 
				Integer didNum = contractCopy.getDidNum(); 
				Float contractMoney = contractCopy.getContractMoney(); 
				String agreementNumber = contractCopy.getAgreementNumber(); 
				Date endTime = contractCopy.getEndTime(); 
				String agreementText = contractCopy.getAgreementText(); 
				String remarksText = contractCopy.getRemarksText(); 
				String payMethod = contractCopy.getPayMethod(); 
				Integer dealConditon = 2;  //状态改为通过
				htService.updateContract(id, company, depart, cid, didNum, contractMoney, agreementNumber, endTime,
						agreementText, remarksText, payMethod, dealConditon);
				//删除副本合同和其错误信息
				htService.deleteErrorInfo(copyId);
				htService.deleteContract(copyId);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setMsg("操作失败");
			}
			return json;
		}
		
		//行政废弃合同
		@ResponseBody
		@RequestMapping(value = "/abandonContract")
		public Json abandonContract(Model model, @RequestParam("id") Integer id)throws ParseException {
			Json json = new Json();
			try {
				htService.updateDealConditon(id, 7);
				json.setSuccess(true);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setMsg("操作失败");
			}
			return json;
		}
		
		//单位管理
		@RequestMapping(value = "/unitList")
		public String unitList(Model model, @RequestParam(required=false)Integer id, 
				@RequestParam(required=false)String remarks, 
				@RequestParam(required=false)String city, 
				@RequestParam(required=false)String province, 
				@RequestParam(required=false)String xingzhi, 
				@RequestParam(required=false)String customerName, 
				@RequestParam(required=false)String charger, 
				@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "size", defaultValue = "10") int size)throws ParseException {
			
			//此处查出来的是object的list
			List customerList = htService.getCustomerList(id, remarks, city, province, xingzhi, customerName, charger, page, size);
			List<Customer> newList = new ArrayList<>(); 
			for(int i=0;i<customerList.size();i++) {  
	            if(customerList.get(i)!=null){    
	            	Customer customer = new Customer();
	            	Object[] obj = (Object[])customerList.get(i);  
	            	if(obj[0]!=null){
	            		customer.setId((Integer)obj[0]);
	            	}
	            	if(obj[1]!=null){
		            	customer.setCustomerName(obj[1].toString());
	            	}
	            	if(obj[2]!=null){
		            	customer.setOldName(obj[2].toString());
	            	}
	            	if(obj[3]!=null){
		            	customer.setAgentCompany(obj[3].toString());
	            	}
	            	if(obj[4]!=null){
		            	customer.setXingzhi(obj[4].toString());
	            	}
	            	if(obj[5]!=null){
		            	customer.setProvince(obj[5].toString());
	            	}
	            	if(obj[6]!=null){
		            	customer.setCity(obj[6].toString());
	            	}
	            	if(obj[7]!=null){
		            	customer.setRemarks(obj[7].toString());
	            	}
	            	newList.add(customer);  
	            }
	        }  
			int totalCountCustomer = htService.getTotalCountCustomer(id, remarks, city, province, xingzhi, customerName, charger);
			PageBean<Customer> pageBean = new PageBean<Customer>();
			pageBean.init(size, totalCountCustomer, page);
		//	pageBean.setList(contractList);
		//	model.addAttribute("contractList", contractList);
			List<Object> xingzhiList = htService.getxingzhiList();
			model.addAttribute("xingzhiList", xingzhiList);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("customerList", newList);
			model.addAttribute("listsize", newList.size());
			return "app/views/hetong/unitList";
		}
		
		// --添加或更新母单位
		@ResponseBody
		@RequestMapping(value = "/addorUpdateUnit")
		public Json addorUpdateUnit(Customer customer) {
			Json json = new Json();
			try {
				if(customer.getId()!=null){
					htService.updateCustomer(customer);
				}else{
					htService.addCustomer(customer);
				}
				json.setSuccess(true);
			//	json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
		
		// --删除母单位
		@ResponseBody
		@RequestMapping(value = "/removeUnit")
		public Json removeUnit(Integer id) {
			Json json = new Json();
			try {
				htService.deleteCustomer(id);
				json.setSuccess(true);
				//	json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
		
	/*	// --更新母单位
		@ResponseBody
		@RequestMapping(value = "/updateUnit")
		public Json updateUnit(Customer customer) {
			Json json = new Json();
			try {
				htService.updateCustomer(customer);
				json.setSuccess(true);
				//	json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}*/
		
		// --查询母单位
		@ResponseBody
		@RequestMapping(value = "/getUnit")
		public Json getUnit(Integer id) {
			Json json = new Json();
			try {
				Customer customer = htService.getCustomer(id);
				json.setSuccess(true);
				json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
		
		@RequestMapping(value = "/childUnitList")
		public String childUnitList(Model model,Integer dId, 
				@RequestParam(value = "page", defaultValue = "1") int page,
				@RequestParam(value = "size", defaultValue = "10") int size){
			
			List<CustomerDepart> customerDepartList = htService.getCustomerDepartListByDid(dId, page, size);
			int totalCountCustomerDepart = htService.getTotalCountCustomerDepart(dId);
			PageBean<Customer> pageBean = new PageBean<Customer>();
			pageBean.init(size, totalCountCustomerDepart, page);
		//	List<Object> xingzhiList = htService.getxingzhiList();
			Customer customer = htService.getCustomer(dId);
			model.addAttribute("pageBean", pageBean);
			model.addAttribute("customerDepartList", customerDepartList);
			model.addAttribute("listsize", customerDepartList.size());
		//	model.addAttribute("xingzhiList", xingzhiList);
			model.addAttribute("customer", customer);
			return "app/views/hetong/childUnitList";
		}
		
		// --查询销售个人信息
		@ResponseBody
		@RequestMapping(value = "/getUseInfo")
		public Json getUseInfo(String email) {
			Json json = new Json();
			Map<String, Object> data= new HashMap<>();
			try {
				UserName userName = null;
				if(email!=null&&email.contains("@")){
					userName = htService.getUseInfo(email);
				}
				OrganizationStructure orgStructure = null;
				if(userName!=null){
					orgStructure = htService.getOrgStructureById(userName.getDepartmentId());
				}
				if(orgStructure!=null){
					orgStructure.setStructures(null);
				}
				data.put("userName", userName);
				data.put("orgStructure", orgStructure);
				json.setSuccess(true);
				json.setObj(data);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
		
		// --添加或更新子单位
		@ResponseBody  
		@RequestMapping(value = "/addorUpdateChildUnit")
		public Json addorUpdateChildUnit(CustomerDepart customerDepart) {
			Json json = new Json();
			try {
				if(customerDepart.getId()!=null){
					htService.updateCustomerDepart(customerDepart);
				}else{
					htService.addCustomerDepart(customerDepart);
				}
				json.setSuccess(true);
			//	json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
		
		// --删除子单位
		@ResponseBody
		@RequestMapping(value = "/removeChildUnit")
		public Json removeChildUnit(Integer id) {
			Json json = new Json();
			try {
				htService.deleteCustomerDepart(id);
				json.setSuccess(true);
				//	json.setObj(customer);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
				}
				return json;
		}
		
		// --查询子单位
		@ResponseBody 
		@RequestMapping(value = "/getChildUnit")
		public Json getChildUnit(Integer id) {
			Json json = new Json();
			try {
				CustomerDepart customerDepart = htService.getCustomerDepart(id);
				json.setSuccess(true);
				json.setObj(customerDepart);
				json.setMsg("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("操作失败");
			}
			return json;
		}
}
