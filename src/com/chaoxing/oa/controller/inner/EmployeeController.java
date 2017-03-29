package com.chaoxing.oa.controller.inner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.annotation.SystemControllerLog;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.PComboBox;
import com.chaoxing.oa.entity.page.common.PCompany;
import com.chaoxing.oa.entity.page.common.PHouseholdType;
import com.chaoxing.oa.entity.page.common.PLevel;
import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.common.POStructs;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PShebao;
import com.chaoxing.oa.entity.page.employee.PShebaoType;
import com.chaoxing.oa.entity.page.employee.PWagesDate;
import com.chaoxing.oa.entity.page.employee.PgridWage;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.employee.Pwage_;
import com.chaoxing.oa.entity.page.employee.Pwages;
import com.chaoxing.oa.entity.page.employee.ShebaoWage;
import com.chaoxing.oa.entity.page.system.PSystemConfig;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.commmon.CountStructure;
import com.chaoxing.oa.entity.po.commmon.TxStructs;
import com.chaoxing.oa.service.EmployeeInfoService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.system.cache.CacheManager;
import com.chaoxing.oa.util.system.DateUtil;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private EmployeeInfoService employeeInfoService;
	
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoService employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}

	@RequestMapping(value = "/renshiUser")
	@ResponseBody
	@SystemControllerLog(description="查询username")
	public Map<String, Object> getRenshiUserName(QueryForm queryForm, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute(ResourceUtil.getSessionInfoName());
		Integer roleId = sessionInfo.getRoleId();
		Map<String, Object> userInfos = null;
		if(roleId != 109){
			userInfos = employeeInfoService.findRenshiUserName(queryForm, session);
			return userInfos;
		}else{
			return getcnUserName(queryForm, session);
		}
		
	}
	
	public Map<String, Object> getcnUserName(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findcnUserName(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/getFourThLevel")
	@ResponseBody
	public Json getFourthLevel(){
		Json result = new Json();
		List<PComboBox> lists = employeeInfoService.findForthLevel();
		if(lists.size()>0){
			result.setSuccess(true);
			result.setMsg("查询成功！");
			result.setObj(lists);
		}else{
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("查询失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getOStruct")
	@ResponseBody
	public List<POStructs> getOStruct(){
		List<POStructs> lists = employeeInfoService.findOStruct();
		return lists;
	}
	
//	@RequestMapping(value = "/queryJiagou")
//	@ResponseBody
//	public Map<String,Object> queryJiagou(QueryForm queryform){
//		
//		Map<String,Object> osInfo = employeeInfoService.getOStruct(queryform,0);
//		return osInfo;
//	}
	
	@RequestMapping(value = "/getAllJiagou")
	@ResponseBody
	public Map<String,Object> queryJiagou(POStructV poStructV){
		Map<String,Object> osInfo = employeeInfoService.findAllStruct(poStructV);
		return osInfo;
	}
	
	@RequestMapping(value = "/updateOrsaveOS")
	@ResponseBody
	public Json updateOrsaveOS(POStructV poStructV){
		Json result = new Json();
		if(null !=poStructV.getFourthLevel() && null != poStructV.getId()){
			if(-1 == poStructV.getId()){
				poStructV.setId(null);
			}
			if(employeeInfoService.updateOrSave(poStructV)>0){
				CacheManager.getInstance().remove(SysConfig.CACHE_COMMON + SysConfig.COMMON_JIAGOU);
				result.setMsg("更新成功");
				result.setSuccess(true);
			}
		}else{
			result.setMsg("您输入的不合法，请检查！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteOS")
	@ResponseBody
	public Json deleteOs(POStructV poStructV){
		Json result = new Json();
		if(null !=poStructV.getFourthLevel() && null != poStructV.getId()){
			if(-1 == poStructV.getId()){
				result.setSuccess(true);
				return result;
			}
			if(employeeInfoService.deleteOS(poStructV)>0){
				CacheManager.getInstance().remove(SysConfig.CACHE_COMMON + SysConfig.COMMON_JIAGOU);
				result.setMsg("删除成功！");
				result.setSuccess(true);
			}
		}else{
			result.setMsg("您输入的不合法，请检查！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryOStruct")
	@ResponseBody
	public List<POStructV> queryOStruct(POStructV pOStructV){
		List<POStructV> lists = null;
		if(pOStructV.getId()!=null || pOStructV.getLevel()!=null){
			lists = employeeInfoService.findOStruct(pOStructV, 0);
		}
		return lists;
	}
	
/*	@RequestMapping(value = "/queryStruct")
	@ResponseBody
	public Map<String,Object> queryStruct(QueryForm queryform){
		Map<String,Object> structInfo = employeeInfoService.findStruct(queryform,0);
		return structInfo;
	}*/
	
	@RequestMapping(value = "/getLevel")
	@ResponseBody
	public List<PLevel> getLevel(){
		List<PLevel> lists = employeeInfoService.findLevel();
		return lists;
	}
	
	@RequestMapping(value = "/getCompany")
	@ResponseBody
	public List<PCompany> getCompany(){
//			System.out.println("xml:" + xml);
//			System.out.println("verifyCode:" + verifyCode);
		return employeeInfoService.findCompany();
	}
	
	@RequestMapping(value = "/getTxs")
	@ResponseBody
	public List<TxStructs> getTxs(){
		return employeeInfoService.findTxs();
	}
	
	
	@RequestMapping(value = "/getCountst")
	@ResponseBody
	public List<CountStructure> getCountSturcture(){
		return employeeInfoService.findCountStructure();
	}
	
	@RequestMapping(value = "/getInsuranceCompany")
	@ResponseBody
	public List<PComboBox> getInsuranceCompany(){
		return employeeInfoService.findInsuranceCompany();
	}
	
	@RequestMapping(value = "/getWagesList")
	@ResponseBody
	public List<Pwages> getWagesList(QueryForm queryForm, HttpSession session){
		List<Pwages> wagesList = null ;
		if(queryForm.getId()>0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			wagesList = employeeInfoService.findWagesList(queryForm.getId(),sessionInfo,ifSecret);
		}
		return wagesList;
	}
	
	@RequestMapping(value = "/getWages")
	@ResponseBody
	public Pwages getWages(QueryForm queryForm){
		Pwages pwages = null;
		if(queryForm!=null&&queryForm.getId()!=0){
			pwages = employeeInfoService.getWages(queryForm.getId());
		}
		return pwages;
	}
	
	@RequestMapping(value = "/updateGridWages")
	@ResponseBody
	public Json updateGridWage(PgridWage pgridWage){
		Json result = new Json();
		if(null != pgridWage.getId() && pgridWage.getId() != 0){
			if(employeeInfoService.updateGridWage(pgridWage)>0){
				result.setMsg("工资编号：[" + pgridWage.getId() + "]更新成功。");
				result.setSuccess(true);
			}
		}else{
			result.setMsg("工资ID不存在");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateGridShebaoWages")
	@ResponseBody
	public Json updateShebaoWage(ShebaoWage shebaoWage){
		Json result = new Json();
		PSystemConfig ps = employeeInfoService.getSysconfig(shebaoWage.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
			Pwages pwage = new Pwages();
			BeanUtils.copyProperties(shebaoWage, pwage);
			if(caculateWages(pwage)>0){
				if(employeeInfoService.updateWageShebao(pwage)>0){
					result.setSuccess(true);
					result.setMsg("工资编号：[" + shebaoWage.getId() + "]更新成功。");
				}else{
					result.setMsg("入库失败，请刷新后重试。");
				}
			}
		}else{
			result.setMsg("社保公司[" + shebaoWage.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
		}
		
		return result;
	}
	
//	public Json updatePartWages(Pwages pwage){
//		Json result = new Json();
//		PSystemConfig ps = employeeInfoService.getSysconfig(pwage.getCompany(), SysConfig.SHEBAO_SUMMARY);
//		if(ps==null || ps.getLocked()==0){
//			if(pwage!=null&&pwage.getId()!=null&&pwage.getId()!=0){
//				Pwages target = employeeInfoService.getWages(pwage.getId());
//				if(target!=null){
//					target.setRadix(pwage.getRadix());
//					target.setCompany(pwage.getCompany());
//					target.setHouseholdType(pwage.getHouseholdType());
//					target.setRubaoTime(pwage.getRubaoTime());
//					if(pwage.getIdentityCard()!=null){
//						target.setIdentityCard(pwage.getIdentityCard());
//					}
//					if(pwage.getAccountBank()!=null){
//						target.setAccountBank(pwage.getAccountBank());
//					}
//					if(pwage.getAccount()!=null){
//						target.setAccount(pwage.getAccount());
//					}
//					if(caculateWages(target)==1){
//						if(employeeInfoService.updateWages(target)!=0){
//							result.setSuccess(true);
//							result.setMsg("<strong>"+ target.getUsername() + ":" +target.getId() +"</strong>更新成功！");
//						}else{
//							result.setMsg("更新失败！");
//						}
//					}else{
//						result.setMsg("社保计算失败！");
//					}
//				}else{
//					result.setMsg("没有<strong>"+ pwage.getUsername() + ":" +pwage.getId() + "</strong>该条工资信息！");
//				}
//			}
//		}else{
//			result.setMsg("社保公司[" + pwage.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
//		}
//		return result;
//	}
	
	public int caculateWages(Pwages pwage){
		if(pwage.getRadix()==null){
			pwage.setRadix(0d);
		}
		Double oradix = pwage.getRadix();
		String company = pwage.getCompany();
		String houseHoldType = pwage.getHouseholdType();
		List<PShebao> shebaoCompany = employeeInfoService.findShebaoRadioByCompany(company);
		BigDecimal rRadix;
		if(pwage.getRadix()==0){
			pwage.setSubEndowmentIinsurance(0d);
			pwage.setSubHouseIinsurance(0d);
			pwage.setSubMedicare(0d);
			pwage.setSubUnemployedInsurance(0d);
			pwage.setcBirthIinsurance(0d);
			pwage.setcEndowmentIinsurance(0d);
			pwage.setcHouseIinsurance(0d);
			pwage.setcInjuryInsurance(0d);
			pwage.setcMedicare(0d);
			pwage.setcUnemployedInsurance(0d);
			return 1;
		}else if(shebaoCompany!=null&&shebaoCompany.size()>0){
			for (PShebao pShebao : shebaoCompany) {//社保计算
				Double radix = oradix;
				radix = radix<pShebao.getRadixMin()?pShebao.getRadixMin():(radix>pShebao.getRadixMax()?pShebao.getRadixMax():radix);
				rRadix = new BigDecimal(radix.toString());
				if(pShebao.getShebaoType().equals("养老保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubEndowmentIinsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcEndowmentIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("失业保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubUnemployedInsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcUnemployedInsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("工伤保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setcInjuryInsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("生育保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setcBirthIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("医疗保险")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubMedicare(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcMedicare(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}else if(pShebao.getShebaoType().equals("公积金")){
					if(pShebao.getHouseholdType().equals("ALL") || pShebao.getHouseholdType().equals(houseHoldType)){
						pwage.setSubHouseIinsurance(rRadix.multiply(new BigDecimal(pShebao.getRadio().toString())).add(new BigDecimal(pShebao.getFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						pwage.setcHouseIinsurance(rRadix.multiply(new BigDecimal(pShebao.getcRadio().toString())).add(new BigDecimal(pShebao.getcFixedValue().toString())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
				}
			}
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value = "/updateWages")
	@ResponseBody
	public Json updateWages(Pwage_ pwages, HttpSession session){
		Json result = new Json();
//		PSystemConfig ps = employeeInfoService.getSysconfig(pwages.getCompany(), SysConfig.SHEBAO_SUMMARY);
//		if(ps==null || ps.getLocked()==0){
			if(pwages.getId()!=null&&pwages.getId()!=0){
				QueryForm queryForm = new QueryForm();
				queryForm.setId(pwages.getEmployeeId());
				SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
				String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
				if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
					Pwages mw = employeeInfoService.getWages(pwages.getId());
					pwages.setTaxStructure(mw.getTaxStructure());
					pwages.setCountId(mw.getCountId());
//					pwages.getSubEndowmentIinsurance(m);
					BeanUtils.copyProperties(pwages, mw);
					if(employeeInfoService.updateWages(mw)!=0){
						result.setSuccess(true);
						result.setMsg("更新成功！");
					}else{
						result.setMsg("更新失败！");
					}
				}
			}else{
				result.setMsg("没有找到该条记录！");
			}
//		}else{
//			result.setMsg("社保公司[" + pwages.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
//		}
		return result;
	}
	
	@RequestMapping(value = "/updateShebaoDetail")
	@ResponseBody
	public Json updateShebaoDetail(PshebaoDetail pshebaoDetail, HttpSession session){
		Json result = new Json();
		PSystemConfig ps = employeeInfoService.getSysconfig(pshebaoDetail.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
			if(pshebaoDetail.getId()!=null&&pshebaoDetail.getId()!=0){
				QueryForm queryForm = new QueryForm();
				queryForm.setId(pshebaoDetail.getEmployeeId());
				Pwages pwages = new Pwages();
				BeanUtils.copyProperties(employeeInfoService.getWages(pshebaoDetail.getId()), pwages);
				String username = pwages.getUsername();
				String idcard = pwages.getIdentityCard();
				BeanUtils.copyProperties(pshebaoDetail, pwages);
				pwages.setUsername(username);//username和idcard字段不更新
				pwages.setIdentityCard(idcard);
				if(employeeInfoService.updateWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}else{
					result.setMsg("更新失败！");
				}
			}else{
				result.setMsg("没有找到该条记录！");
			}
		}else{
			result.setMsg("社保公司[" + pshebaoDetail.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/updateWagesRadix")
	@ResponseBody
	public Json updateWagesRadix(PshebaoDetail pwages){
		Json result = new Json();
		PSystemConfig ps = employeeInfoService.getSysconfig(pwages.getCompany(), SysConfig.SHEBAO_SUMMARY);
		if(ps==null || ps.getLocked()==0){
			if(pwages.getId()!=0){
				if(employeeInfoService.updateWagesRadix(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}else{
					result.setMsg("更新失败！");
				}
			}
		}else{
			result.setMsg("社保公司[" + pwages.getCompany() +"]已被锁定，请您联系社保管理员解锁！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/addWages")
	@ResponseBody
	public Json addWages(Pwage_ pwages, HttpSession session){
		Json result = new Json();
		QueryForm queryForm = new QueryForm();
		queryForm.setId(pwages.getEmployeeId());
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
		String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
		if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
			Pwages pw = new Pwages();
			BeanUtils.copyProperties(pwages, pw);
			if(employeeInfoService.addWages(pw)!=0){
				result.setSuccess(true);
				result.setMsg("添加成功！");
			}else{
				result.setMsg("添加失败！");
			}
		}else{
			result.setMsg("对不起，您没有删除权限~！");
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteWages")
	@ResponseBody
	public Json deleteWages(Pwages pwages, HttpSession session ){
		Json result = new Json();
		if(pwages.getId()!=null&&pwages.getId()!=0){
			QueryForm queryForm = new QueryForm();
			queryForm.setId(pwages.getEmployeeId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());	
			String ifSecret = employeeInfoService.getUserInfo(queryForm).getIfSecret();
			if(sessionInfo.getRoleId()<=1||sessionInfo.getRoleId()==100||ifSecret.equals("off")){
				if(employeeInfoService.deleteWages(pwages)!=0){
					result.setSuccess(true);
					result.setMsg("删除成功！");
				}else{
					result.setMsg("删除失败！");
				}
			}else{
				result.setMsg("对不起，您没有删除权限~！");
			}
		}else{
			result.setMsg("没有找到该条记录！");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/getShebao")
	@ResponseBody
	public Map<String, Object>  getAllShebaoRadio(QueryForm queryform){
		Map<String, Object> pshebaos = employeeInfoService.findAllShebaoRadio(queryform);
		return pshebaos;
	}
	
	@RequestMapping(value = "/getShebaoRadioByCompany")
	@ResponseBody
	public Json getShebaoRadioByCompany(QueryForm queryForm){
		Json result = new Json();
		if(queryForm.getCompany()==null){
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("没有公司名称！");
			return result;
		}
		List<PShebao> pshebaos = employeeInfoService.findShebaoRadioByCompany(queryForm.getCompany());
		if(pshebaos.size() > 0){
			result.setSuccess(true);
			result.setObj(pshebaos);
			result.setMsg("查询成功！");
		}else{
			result.setErrorCode(SysConfig.REQUEST_ERROR);
			result.setMsg("没有数据或者查询失败！");
		}
		return result;
	}
	@RequestMapping(value = "/getShebaoRadioByType")
	@ResponseBody
	public Json getShebaoRadioByType(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			List<PShebao> pshebaos = employeeInfoService.findShebaoRadioByCompany(company);
			if(pshebaos.size() > 0){
				result.setSuccess(true);
				result.setObj(pshebaos);
				result.setMsg("查询成功！");
			}else{
				result.setErrorCode(SysConfig.REQUEST_ERROR);
				result.setMsg("没有数据或者查询失败！");
			}
		}else{
			result.setMsg("公司名称为空！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/getShebaoType")
	@ResponseBody
	public List<PShebaoType> getShebaoType(){
		return employeeInfoService.findShebaoType();
	}
	
	@RequestMapping(value = "/updateShebao")
	@ResponseBody
	public Json updateShebao(PShebao pshebao){
		Json result = new Json();
		if(pshebao.getSid()!=0){
			if(employeeInfoService.updateShebao(pshebao) != 0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败！");
			}
		}else{
			result.setMsg("没有id");
		}
		return result;
	}
	
	@RequestMapping(value = "/getHouseholdType")
	@ResponseBody
	public List<PHouseholdType> getHouseholdType(){
		return employeeInfoService.findHouseholdType();
	}
	
	@RequestMapping(value = "/addShebao")
	@ResponseBody
	public Json addShebao(PShebao pshebao){
		Json result = new Json();
		if(pshebao.getCompany()!=null){
			if(employeeInfoService.addShebao(pshebao)!=0){
				result.setSuccess(true);
				result.setMsg("增加成功！");
			}else{
				result.setMsg("添加失败！");
			}
		}else{
			result.setMsg("添加社保比例，至少要有公司名称~！");
		}
		return result;
	}
	@RequestMapping(value = "/deleteShebao")
	@ResponseBody
	public Json deleteShebao(PShebao pshebao){
		Json result = new Json();
		if(pshebao.getSid()!=0){
			if(employeeInfoService.deleteShebao(pshebao)!=0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败！");
			}
		}else{
			result.setMsg("没有该条记录~~!");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryShebaoSummary")
	@ResponseBody
	public Map<String, Object> queryShebaoSummary(QueryForm queryForm, HttpSession session){
		Map<String, Object> shebaoSummaries = employeeInfoService.findShebaoSummary(queryForm, session);
		return shebaoSummaries;
	}
	
	@RequestMapping(value = "/luckedShebaoSummary")
	@ResponseBody
	public Json luckedShebaoSummary(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			PSystemConfig ps = new PSystemConfig();
			ps.setName(company);
			ps.setLocked((byte) 1);
			ps.setConfigType("shebaoSummary");
			if(employeeInfoService.updateSysconfig(ps)>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}else{
			result.setMsg("公司名称为空");
		}
		return result;
	}
	
	@RequestMapping(value = "/unluckeShebaoSummary")
	@ResponseBody
	public Json unluckeShebaoSummary(String company){
		Json result = new Json();
		if(company!=null&&!company.equals("")){
			PSystemConfig ps = new PSystemConfig();
			ps.setName(company);
			ps.setLocked((byte) 0);
			ps.setConfigType("shebaoSummary");
			if(employeeInfoService.updateSysconfig(ps)>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}else{
			result.setMsg("公司名称为空");
		}
		return result;
	}
	
	@RequestMapping(value = "/getShebaoCompany")
	@ResponseBody
	public Map<String, Object> getShebaoCompany(QueryForm queryForm, HttpSession session){
		if(queryForm!=null&&queryForm.getCompany()!=null){
			Map<String, Object> shebaoCompanys = employeeInfoService.findShebaoCompany(queryForm, session);
			return shebaoCompanys;
		}
		return null;
	}
	
	@RequestMapping(value = "/generateCompany")
	@ResponseBody
	public Json generateCompany(String company, HttpSession session){
		Json result = new Json();
		if(null!=company && !"".equals(company)){
			if(employeeInfoService.updateShebaoCompany(company)>0){
				result.setSuccess(true);
				result.setMsg("社保信息["+ company +"]更新成功！");
			}else{
				result.setMsg("社保信息["+ company +"]更新失败");
			}
		}else{
			result.setMsg("公司名称为空！");
		}
		return result;
	}
	
	@RequestMapping(value = "/generateCompanyType")
	@ResponseBody
	public Json generateCompanyType(String company, String type, HttpSession session){
		Json result = new Json();
		if(null!=company && !"".equals(company) && null!=type && !"".equals(type)){
			if(employeeInfoService.updateShebaoCompanyType(company, type)>0){
				result.setSuccess(true);
				result.setMsg("社保信息["+ company +"]["+ type + "]更新成功！");
			}else{
				result.setMsg("社保信息["+ company +"]["+ type + "]更新失败");
			}
		}else{
			result.setMsg("社保类型为空，或者公司名称为空！");
		}
		return result;
	}
	
	@RequestMapping(value = "/queryKaoqin")
	@ResponseBody
	public Map<String, Object> queryKaoqin(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findKaoqin(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateKaoqin")
	@ResponseBody
	public Json updateKaoqin(PKaoQin pkaoqin){
		Json result = new Json();
		if(employeeInfoService.updateKaoqin(pkaoqin)!=0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}else{
			result.setMsg("更新失败！");
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteKaoqin")
	@ResponseBody
	public Json deleteKaoqin(PKaoQin pkaoqin){
		Json result = new Json();
		if(pkaoqin.getId()!=0){
			if(employeeInfoService.deleteKaoqin(pkaoqin)>0){
				result.setSuccess(true);
				result.setMsg("删除成功！~");
			}else{
				result.setMsg("删除失败！~");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/queryMonthWages")
	@ResponseBody
	public Map<String, Object> queryMonthWages(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findMonthWages(queryForm, session);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateMonthWages")
	@ResponseBody
	public Json updateMonthWages(PMonthWages pmonthWages, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Json result = new Json();
		PMonthWages target = employeeInfoService.getMonthWages(pmonthWages.getId());
		if(sessionInfo.getRoleId()!=0&&sessionInfo.getRoleId()!=100){
			target.setChuqinDay(pmonthWages.getChuqinDay());
			target.setZhuanzhengChaeDay(pmonthWages.getZhuanzhengChaeDay());
			target.setFakuan(pmonthWages.getFakuan());
			target.setJiangjin(pmonthWages.getJiangjin());
			target.setBufaSalary(pmonthWages.getBufaSalary());
			target.setShiJiaHour(pmonthWages.getShiJiaHour());
			target.setBingJiaHour(pmonthWages.getBingJiaHour());
			target.setKuangGongHour(pmonthWages.getKuangGongHour());
			target.setChidaoYingkouDay(pmonthWages.getChidaoYingkouDay());
			target.setKaoQinremarks(pmonthWages.getKaoQinremarks());
		}else{
			target = pmonthWages;
		}
		if(caculateWages(target)==0){
			if(employeeInfoService.updateMonthWages(target, sessionInfo)!=0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败！");
			}
		}else{
			result.setMsg("计算工资失败,可能是转正报表有问题！~");
		}
		return result;
	}
	
	private int caculateWages(PMonthWages pmonthWages) {
		Double salary = pmonthWages.getSalary()!=null ? pmonthWages.getSalary():0;
		Float chuqin = pmonthWages.getChuqinDay()!=null ? pmonthWages.getChuqinDay():0;
		double chaeDay = pmonthWages.getZhuanzhengChaeDay()!=null ? pmonthWages.getZhuanzhengChaeDay():0;
		double zhuanzhengChae = 0.00;
		double lishiSalary = pmonthWages.getLishiSalary()!=null ? pmonthWages.getLishiSalary():0;
		double baomiSub = pmonthWages.getSecrecySubsidy()!=null?pmonthWages.getSecrecySubsidy():0;
		double lunchSub = pmonthWages.getLunchSubsidy()!=null?pmonthWages.getLunchSubsidy():0;
		double tongxunSub = pmonthWages.getCommunicationSubsidy()!=null?pmonthWages.getCommunicationSubsidy():0;
		double jiangjin = pmonthWages.getJiangjin()!=null?pmonthWages.getJiangjin():0;
		double bufaSalary = pmonthWages.getBufaSalary()!=null?pmonthWages.getBufaSalary():0;
		double fakuan = pmonthWages.getFakuan()!=null?pmonthWages.getFakuan():0;
		float shijia = (pmonthWages.getShiJiaHour()!=null)? pmonthWages.getShiJiaHour():0;
		String zhuanZhengRport = pmonthWages.getZhuanzhengReport();
		//计算考勤总额
		Double bingjia = 0.0;
		if(pmonthWages.getSickLleaveTotal()==null){
			pmonthWages.setSickLleaveTotal(0d);
		}
		if(pmonthWages.getSickLleaveTotal()<=24){
			bingjia =  ((pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal())>24?(pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal() -24):0);
		}else{
			bingjia =(double)(pmonthWages.getBingJiaHour()!=null?pmonthWages.getBingJiaHour():0);
		}
		Float kuanggong = pmonthWages.getKuangGongHour()!=null?pmonthWages.getKuangGongHour():0;
		Float chidaoYingkou = pmonthWages.getChidaoYingkouDay()!=null?pmonthWages.getChidaoYingkouDay():0;
		if(chidaoYingkou<=3){
			chidaoYingkou = chidaoYingkou * 20;
		}else{
			chidaoYingkou = chidaoYingkou * 50 - 150;
		}
		if(lishiSalary!=0 && chaeDay!=0){
			zhuanzhengChae = (salary - lishiSalary)/21 * (21 - chaeDay);
		}
		float kaoqinTotal = (float) (salary*shijia/168 + bingjia*salary/336 + kuanggong*salary/56 + chidaoYingkou + zhuanzhengChae) ;
		//计算社保代扣总额
		if(pmonthWages.getSubEndowmentIinsurance()==null){
			pmonthWages.setSubEndowmentIinsurance(0d);
		}
		if(pmonthWages.getSubHouseIinsurance()==null){
			pmonthWages.setSubHouseIinsurance(0d);
		}
		if(pmonthWages.getSubMedicare()==null){
			pmonthWages.setSubMedicare(0d);
		}
		if(pmonthWages.getSubUnemployedInsurance()==null){
			pmonthWages.setSubUnemployedInsurance(0d);
		}
		float subTotal = (float) (pmonthWages.getSubEndowmentIinsurance()+pmonthWages.getSubHouseIinsurance()+pmonthWages.getSubMedicare()+pmonthWages.getSubUnemployedInsurance());
		if(zhuanZhengRport!=null && (!zhuanZhengRport.equals(""))){
			if(zhuanZhengRport.length() >= 6){
				zhuanZhengRport.substring(0, 6);
				try {
					Integer a = Integer.valueOf(zhuanZhengRport.substring(0, 6));
					SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
					Calendar cal = Calendar.getInstance();
					String afterDate = df.format(cal.getTime());
					Integer b = Integer.valueOf(afterDate);
					if(a>=b){
						salary = lishiSalary;
					}
				} catch (Exception e) {
					return -1;
				}
			}else{
				return -1;
			}
		}
	
		//应发工资
		float yingfa = (float) (salary*chuqin/21 -kaoqinTotal-subTotal + baomiSub + lunchSub + tongxunSub+jiangjin+bufaSalary-fakuan);
		//个税
		float selfTax = 0;
		if((yingfa - 3500)<=0){
			selfTax = 0;
		}else if(0<(yingfa-3500)&&(yingfa-3500)<=1500){
			selfTax = (float) ((yingfa-3500)*0.03);
		}else if((yingfa-3500)<=4500){
			selfTax = (float) ((yingfa-3500)*0.1 - 105);
		}else if((yingfa - 3500)<=9000){
			selfTax = (float) ((yingfa-3500)*0.2 - 555);
		}else if((yingfa - 3500)<=35000){
			selfTax = (float) ((yingfa-3500)*0.25 - 1005);
		}else if((yingfa - 3500)<=55000){
			selfTax = (float) ((yingfa-3500)*0.30 - 2755);
		}else if((yingfa - 3500)<=80000){
			selfTax = (float) ((yingfa-3500)*0.35 - 5505);
		}else{
			selfTax = (float) ((yingfa-3500)*0.45 - 13505);
		}
		pmonthWages.setSubTotal(subTotal);
		pmonthWages.setKaoqinTotal(kaoqinTotal);
		pmonthWages.setYingfaTotal(yingfa);
		pmonthWages.setSelfTax(selfTax);
		pmonthWages.setShifaTotal(yingfa-selfTax);
		return 0;
	}
	
	public void caculateWages(PMonthWages pmonthWages, int i) {
		Double salary = pmonthWages.getSalary();
		BigDecimal b_salary = new BigDecimal(salary.toString());
		Float chuqin = pmonthWages.getChuqinDay();
		BigDecimal b_chuqin = new BigDecimal(chuqin.toString());
		double chaeDay = pmonthWages.getZhuanzhengChaeDay()!=null ? pmonthWages.getZhuanzhengChaeDay():0;
//		BigDecimal b_chaeDay = new BigDecimal(String.valueOf(chaeDay));
//		double zhuanzhengChae = 0.00;
		double lishiSalary = pmonthWages.getLishiSalary()!=null ? pmonthWages.getLishiSalary():0;
		BigDecimal b_lishiSalary = new BigDecimal(String.valueOf(lishiSalary));
		double baomiSub = pmonthWages.getSecrecySubsidy()!=null?pmonthWages.getSecrecySubsidy():0;
		BigDecimal b_baomiSub = new BigDecimal(String.valueOf(baomiSub));
		double lunchSub = pmonthWages.getLunchSubsidy()!=null?pmonthWages.getLunchSubsidy():0;
		BigDecimal b_lunchSub = new BigDecimal(String.valueOf(lunchSub));
		double tongxunSub = pmonthWages.getCommunicationSubsidy()!=null?pmonthWages.getCommunicationSubsidy():0;
		BigDecimal b_tongxunSub = new BigDecimal(String.valueOf(tongxunSub));
		double jiangjin = pmonthWages.getJiangjin()!=null?pmonthWages.getJiangjin():0;
		BigDecimal b_jiangjin = new BigDecimal(String.valueOf(jiangjin));
		double bufaSalary = pmonthWages.getBufaSalary()!=null?pmonthWages.getBufaSalary():0;
		BigDecimal b_bufaSalary = new BigDecimal(String.valueOf(bufaSalary));
		double fakuan = pmonthWages.getFakuan()!=null?pmonthWages.getFakuan():0;
		BigDecimal b_fakuan = new BigDecimal(String.valueOf(fakuan));
		float shijia = (pmonthWages.getShiJiaHour()!=null)? pmonthWages.getShiJiaHour():0;
		BigDecimal b_shijia = new BigDecimal(String.valueOf(shijia));
		//计算考勤总额
		Double bingjia = 0.0;
		if(pmonthWages.getSickLleaveTotal()<=24){
			bingjia =  ((pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal())>24?(pmonthWages.getBingJiaHour() + pmonthWages.getSickLleaveTotal() -24):0);
		}else{
			bingjia =(double)pmonthWages.getBingJiaHour();
		}
		BigDecimal b_bingjia = new BigDecimal(bingjia.toString());
		Float kuanggong = pmonthWages.getKuangGongHour();
		BigDecimal b_kuanggong = new BigDecimal(kuanggong.toString());
		
		//迟到应扣
		Float chidaoYingkou = pmonthWages.getChidaoYingkouDay();
		if(chidaoYingkou<=3){
			chidaoYingkou = chidaoYingkou * 20;
		}else{
			chidaoYingkou = chidaoYingkou * 50 - 150;
		}
		BigDecimal b_chidaoYingkou = new BigDecimal(chidaoYingkou.toString());
		
		//转正差额
		BigDecimal b_zhuanzhengChae = new BigDecimal("0");
		if(lishiSalary!=0){
			b_zhuanzhengChae = b_salary.subtract(b_lishiSalary).divide(new BigDecimal("21")).multiply(new BigDecimal(String.valueOf(21-chaeDay)));
//			zhuanzhengChae = (salary - lishiSalary)/21 * (21 - chaeDay);
		}
		
		//计算考勤总额，包括转正差额
		BigDecimal b_kaoqinTotal = b_salary.multiply(b_shijia).divide(new BigDecimal("168")).
				add(b_bingjia.multiply(b_salary).divide(new BigDecimal("336"))).
				add(b_kuanggong.multiply(b_salary).divide(new BigDecimal("56"))).
				add(b_chidaoYingkou).
				add(b_zhuanzhengChae);
//		float kaoqinTotal = (float) (salary*shijia/168 + bingjia*salary/336 + kuanggong*salary/56 + chidaoYingkou + zhuanzhengChae) ;
		
		//计算社保代扣总额
//		float subTotal = (float) (pmonthWages.getSubEndowmentIinsurance()+pmonthWages.getSubHouseIinsurance()+pmonthWages.getSubMedicare()+pmonthWages.getSubUnemployedInsurance());
		BigDecimal b_subTotal = (new BigDecimal(pmonthWages.getSubEndowmentIinsurance().toString())).
				add(new BigDecimal(pmonthWages.getSubHouseIinsurance().toString())).
				add(new BigDecimal(pmonthWages.getSubMedicare().toString())).
				add(new BigDecimal(pmonthWages.getSubUnemployedInsurance().toString()));
		
		//应发工资
		BigDecimal b_yingfa = b_salary.multiply(b_chuqin).divide(new BigDecimal("21")).
				subtract(b_kaoqinTotal).
				subtract(b_subTotal).
				add(b_baomiSub).
				add(b_lunchSub).
				add(b_tongxunSub).
				add(b_jiangjin).
				add(b_bufaSalary).
				subtract(b_fakuan);
//		float yingfa = (float) (salary*chuqin/21 -kaoqinTotal-subTotal + baomiSub + lunchSub + tongxunSub+jiangjin+bufaSalary-fakuan);
		//个税
		BigDecimal b_selfTax = new BigDecimal("0.00");
		if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=1500){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.03")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=4500){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.10")).subtract(new BigDecimal("105")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=9000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.20")).subtract(new BigDecimal("555")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=35000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.35")).subtract(new BigDecimal("1005")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=55000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.30")).subtract(new BigDecimal("2755")));
		}else if(b_yingfa.subtract(new BigDecimal("3500")).floatValue()<=80000){
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.35")).subtract(new BigDecimal("5505")));
		}else{
			b_selfTax = b_yingfa.subtract(new BigDecimal("3500").multiply(new BigDecimal("0.45")).subtract(new BigDecimal("13505")));
		}
//		float selfTax = 0;
//		if((yingfa - 3500)<=0){
//			selfTax = 0;
//		}else if(0<(yingfa-3500)&&(yingfa-3500)<=1500){
//			selfTax = (float) ((yingfa-3500)*0.03);
//		}else if((yingfa-3500)<=4500){
//			selfTax = (float) ((yingfa-3500)*0.1 - 105);
//		}else if((yingfa - 3500)<=9000){
//			selfTax = (float) ((yingfa-3500)*0.2 - 555);
//		}else if((yingfa - 3500)<=35000){
//			selfTax = (float) ((yingfa-3500)*0.25 - 1005);
//		}else if((yingfa - 3500)<=55000){
//			selfTax = (float) ((yingfa-3500)*0.30 - 2755);
//		}else if((yingfa - 3500)<=80000){
//			selfTax = (float) ((yingfa-3500)*0.35 - 5505);
//		}else{
//			selfTax = (float) ((yingfa-3500)*0.45 - 13505);
//		}
		pmonthWages.setSubTotal(b_subTotal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setKaoqinTotal(b_kaoqinTotal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setYingfaTotal(b_yingfa.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setSelfTax(b_selfTax.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
		pmonthWages.setShifaTotal(b_yingfa.subtract(b_selfTax).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
//		pmonthWages.setShifaTotal(yingfa-selfTax);
	}
	@RequestMapping(value = "/queryWagesDate")
	@ResponseBody
	public Map<String, Object> queryWagesDate(QueryForm queryForm, HttpSession session){
		Map<String, Object> userInfos = employeeInfoService.findWagesDate(queryForm);
		return userInfos;
	}
	
	@RequestMapping(value = "/updateWagesDate")
	@ResponseBody
	public Json updateWagesDates(PWagesDate pwagesDate){
		Json result = new Json();
		int savaNum = employeeInfoService.updateWagesDate(pwagesDate);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg(String.valueOf(savaNum));
		}
		return result;
	}
	
	@RequestMapping(value = "/generateWagesDates")
	@ResponseBody
	public Json generateWagesDates(Integer year,Integer month){
		Json result = new Json();
		if(year==null||year==0||month==null||month==0){
			return result;
		}
		List<PWagesDate> pwagesDates = DateUtil.getWagesDate(year, month);
		int savaNum = employeeInfoService.updateWagesDates(pwagesDates);
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg(String.valueOf(savaNum));
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteWagesDate")
	@ResponseBody
	public Json deleteWagesDate(PWagesDate pwagesDate){
		Json result = new Json();
		if(pwagesDate.getDate()!=null){
			int savaNum = employeeInfoService.deleteWagesDate(pwagesDate);
			if(savaNum>0){
				result.setSuccess(true);
				result.setMsg(String.valueOf(savaNum));
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/generateKaoqin")
	@ResponseBody
	public Json generateKaoqin(){
		Json result = new Json();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		String afterDate = df.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String date = df.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String preDate = df.format(cal.getTime());
//		int year,month;
//		List<PWagesDate> pwagesDates = null;
//		for (int i = 0; i < 2; i++) {
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DATE, 1);  
//			year = cal.get(Calendar.YEAR);
//			month = cal.get(Calendar.MONTH)+1;
//			String date = df.format(cal.getTime()).substring(0, 7);
//			QueryForm queryForm = new QueryForm();
//			queryForm.setWagesMonth(date);
//			pwagesDates = (List<PWagesDate>) employeeInfoService.queryWagesDate(queryForm).get("rows");
//			int j = 0;
//			if(pwagesDates.size()<28){
//				result.setMsg("请检查"+ date +"的工作日表");
//				return result;
//			}
//			while(cal.get(Calendar.YEAR) == year &&     
//	                cal.get(Calendar.MONTH) < month){
//				if(df.format(cal.getTime())!= pwagesDates.get(j).getDate()){
//					result.setMsg("请检查"+ date +"的工作日表");
//					return result;
//				}
//			}
//		}
//		System.out.println(date);
		QueryForm queryForm = new QueryForm();
		queryForm.setWagesMonth(date);
		PSystemConfig ps = employeeInfoService.findSysconfig(null, SysConfig.KAOQIN_BUTTON).get(0);
		if(ps!=null&&ps.getLocked()==0){
		if(((Long)employeeInfoService.findWagesDate(queryForm).get("total"))>27){
			queryForm.setWagesMonth(preDate);
			if(((Long)employeeInfoService.findWagesDate(queryForm).get("total"))>27){
				int savaNum = employeeInfoService.generateKaoqin(date,preDate,afterDate);
				if(savaNum>0){
					result.setSuccess(true);
					result.setMsg("更新成功！");
				}
			}else{
				result.setMsg("请检查[" + preDate +"]的工作日~！");
			}
		}else{
			result.setMsg("请检查[" + date +"]的工作日~！");
		}
		}else{
			result.setMsg("生成考勤已加锁，请联系人事管理员解锁。");
		}
		return result;
	}
	
	@RequestMapping(value = "/generateMonthWages")
	@ResponseBody
	public Json generateMonthWages(){
		Json result = new Json();
		int savaNum = employeeInfoService.generateMonthWages();
		if(savaNum>0){
			result.setSuccess(true);
			result.setMsg("更新成功！");
		}
		return result;
	}
	
	@RequestMapping(value = "/getIfGenerateKaoqin")
	@ResponseBody
	public Json getIfGenerate(){
		Json result = new Json();
		List<PSystemConfig> rs = employeeInfoService.findSysconfig("ifGenerate", SysConfig.KAOQIN_BUTTON);
		if(rs.size()>0){
			result.setSuccess(true);
			if(rs.get(0)!=null){
				result.setObj(rs.get(0).getLocked());
			}else{
				result.setObj(0);
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/lockedKaoqin")
	@ResponseBody
	public Json lockedKaoqin(Boolean locked){
		System.out.println(locked);
		Json result = new Json();
		PSystemConfig  ps = new PSystemConfig();
		ps.setName("ifGenerate");
		ps.setConfigType(SysConfig.KAOQIN_BUTTON);
		if(locked){
			ps.setLocked((byte) 1);
		}else{
			ps.setLocked((byte) 0);
		}
		if(employeeInfoService.updateSysconfig(ps)>0){
			result.setSuccess(true);
			result.setMsg("更新成功！~");
		}
		return result;
	}
	
	@RequestMapping(value = "/fangfa")
	@ResponseBody
	public Json fangfa(Boolean locked){
		System.out.println(locked);
		Json result = new Json();
		PSystemConfig  ps = new PSystemConfig();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM");
		Calendar cal = Calendar.getInstance();
		String date = df.format(cal.getTime());
		ps.setName("wages_fafang_" + date);
		ps.setConfigType(SysConfig.MONTHWAGE_FAFANG);
		ps.setLocked((byte) 1);
		if(employeeInfoService.updateSysconfig(ps)>0){
			if(employeeInfoService.fafang()>0){
				result.setSuccess(true);
				result.setMsg("更新成功！~");
			}
		}
		return result;
	}
	
//	@RequestMapping(value = "testDir.action")
//	@ResponseBody
//	public Json getDir(HttpServletRequest request, HttpSession session){
//		Json result = new Json();
////		String dir = request.
////		request.getRealPath("");
//		Map<String, Object> mm = new HashMap<String, Object>();
//		System.out.println(request.getContextPath());
//		mm.put("requestt-contentPath", request.getServletContext().getRealPath("/"));
////		mm.put("cxoa", request.getRealPath("cxoa"));
////		mm.put("sss", request.getRealPath(""));
//		mm.put("getPathTranslated", request.getPathTranslated());
//		mm.put("getServletPath", request.getServletPath());
////		mm.put("getRealPath:upload", request.getRealPath("\\uploadFolder\\"));
//		mm.put("classPath", this.getClass().getResource("").getFile().toString());
//		mm.put("SFUtil:classPath", SFUtil.class.getResource("/").getFile().toString());
//		mm.put("session-servletContext", session.getServletContext().getRealPath("/"));
//		result.setObj(mm);
////		KuaidiList kl = new KuaidiList();
////		kl.setAddService_name("增值服务名");
////		kl.setAddService_value1("value1");
////		kl.setAddService_value2("value2");
////		kl.setContent("发票");
////		kl.setCustid("66666");
////		kl.setD_address("jxnu");
////		kl.setD_company("江西师范大学");
////		kl.setD_contact("Mrs deng");
////		kl.setD_mobile("131346789");
////		kl.setD_tel("0791-123456");
////		kl.setDestcode("079");
////		kl.setExpress_type(26);
////		kl.setJ_address("BeiJing");
////		kl.setJ_company("SJCX");
////		kl.setJ_contact("Mrs deng");
////		kl.setJ_tel("17745678913");
////		kl.setMailno("123456789");
////		kl.setRemark("备注");
////		result.setObj(kl);
//		return result;
//	}

	@RequestMapping(value = "/getgongzihuizong")
	@ResponseBody
	public Map<String, Object>  getgongzihuizong(QueryForm queryform){
		QueryForm queryForm = new QueryForm();
		queryForm.setOrder(queryform.getOrder());
		queryForm.setSort(queryform.getSort());
		queryForm.setPage(queryform.getPage());
		queryForm.setRows(queryform.getRows());;
		queryForm.setFourthLevel(queryform.getFourthLevel());
		queryForm.setUsername(queryform.getUsername());
		queryForm.setConfigurable(queryform.getConfigurable());
		queryForm.setConfigurable_value(queryform.getConfigurable_value());
		Map<String, Object> pshebaos = employeeInfoService.findgongzihuizong(queryForm);
		return pshebaos;
	}
	
	@RequestMapping(value = "/quickQuery")
	@ResponseBody
	public Map<String, Object>  quickQuery(Page page, Integer type, HttpSession session){
		if(null != type){
			Map<String, Object> pshebaos = employeeInfoService.findRenshiQuick(page, type, session);
			return pshebaos;
		}
		return null;
	}
	
//	@RequestMapping(value = "/testExport")
//	@ResponseBody
////	@SystemControllerLog(description="进入TestExport...")
//	public Map<String, Object>  test(QueryForm queryForm, HttpSession session){
//		System.out.println("testExport in.....");
//		return null;
////		return employeeInfoService.findYidong(queryForm, session, false);
//	}
//	
	@RequestMapping(value = "/gtMonthKaoqin")
	@ResponseBody
	public Json gtKaoqin(){
		Json result = new Json();
		employeeInfoService.rmoveKaoqin();
		employeeInfoService.updateAllKaoqin();
		return result;
	}
	
	@RequestMapping(value = "/gtMonthWage")
	@ResponseBody
	public Json generateWages(){
		Json result = new Json();
		employeeInfoService.rmoveMonthWage();
		employeeInfoService.updateAllMonthWage();
		return result;
	}
	
}
