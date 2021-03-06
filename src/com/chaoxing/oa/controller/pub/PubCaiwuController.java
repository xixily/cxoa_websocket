package com.chaoxing.oa.controller.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.caiwu.PUserBank;
import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.pub.PLeader;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PChupiaoBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PKoukuan;
import com.chaoxing.oa.entity.page.pub.caiwu.PSelfBaoxiao;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.service.UserServiceI;
import com.chaoxing.oa.service.impl.UserServiceImpl;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/public/caiwu")
public class PubCaiwuController {
	@Autowired
	private PubCaiwuService publicCaiwuService;
	@Autowired
	private UserServiceI userService;
	
	/**
	 * 个人报销信息查询ypzInit
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryBaoxiao")
	@ResponseBody
	public Map<String, Object> findBaoxiao(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Map<String, Object> results = publicCaiwuService.findBaoxiaoByUid(pbaoxiao,page,sessionInfo.getId());
//		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getId());
//		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getId());
//		results.put("lastYearTotal", lastyear);
//		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryBaoxiaoTotal")
	@ResponseBody
	public Map<String, Object> findBaoxiaoTotal(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		Map<String, Object> results =  new HashMap<String, Object>();
		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getId());
		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getId());
		results.put("lastYearTotal", lastyear);
		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	/**
	 * 添加报销申请
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addBaoxiao")
	@ResponseBody
	public Json addBaoxiao(PBaoxiao pbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessinfo = getSessInfo(session);
		pbaoxiao.setUid(sessinfo.getId());
		pbaoxiao.setStatus(SysConfig.CW_BX_BEGIN);
		String account = pbaoxiao.getAccount();
		Integer id = sessinfo.getId();
		String bank = pbaoxiao.getBank();
		Float money = pbaoxiao.getMoney();
		if(null != money &&  money>0 && null != bank && null!=account){
			if(null == pbaoxiao.getSpecifyId()){
				PLeader pld = userService.getCellCoreInfo(sessinfo.getDepartmentId());
				if(null!=pld){
					pbaoxiao.setSpecifyId(pld.getId());
				}else{
					result.setMsg("部门架构表里面没有找到您的默认批准人，请确认。");
					return result;
				}
			}
			if(null!=account && null != bank){
				publicCaiwuService.addUserBank(id, bank, account);
			}
			Serializable sid = publicCaiwuService.addBaoxiao(pbaoxiao);
			if(null!=sid){
				result.setSuccess(true);
				result.setMsg("添加成功，批次号为：" + sid.toString());
			}else{
				result.setMsg("抱歉，添加失败。");
			}
		}else{
			result.setMsg("您输入的信息有误，请重新输入。");
		}
		return result;
	}
	
	/**
	 * 删除报销申请单
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteBaoxiao")
	@ResponseBody
	public Json removeBaoxiao(PBaoxiao pbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		if(null != pbaoxiao.getId() && pbaoxiao.getId() != 0){
			if(publicCaiwuService.deleteBaoxiao(pbaoxiao.getId(),sessionInfo.getId())>0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败，请确认您删除的批次号是否存在或者是否是属于您的。");
			}
		}else{
			result.setMsg("删除失败，批次号不存在，请确认!");
		}
		return result;
	}
	
	/**
	 * 更新个人报销信息
	 * @param psbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateSelfBaoxiao")
	@ResponseBody
	public Json updateBaoxiao(PSelfBaoxiao psbaoxiao, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		if(null!=psbaoxiao.getId()){
			psbaoxiao.setUid(sessionInfo.getId());
			PBaoxiao baoxiao = new PBaoxiao();
			BeanUtils.copyProperties(psbaoxiao, baoxiao);
			baoxiao.setStatus(SysConfig.CW_BX_BEGIN);
			if(publicCaiwuService.updateSeltBaoxiao(baoxiao)>0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			}else{
				result.setMsg("更新失败，可能是批次号对应的报销人不匹配或现在的状态无法修改。");
			}
		}else{
			result.setMsg("没有批次号。");
		}
		return result;
	}
	
	/**
	 * 删除个人报销信息
	 * @param psbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deleteSelfBx")
	@ResponseBody
	public Json deleteSelfBx(@RequestParam(required=true) Long id, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		if(null != id){
			int count = publicCaiwuService.deleteBaoxiao(id, sessionInfo.getId());
			result.setObj(count);
			if(count>0){
				result.setSuccess(true);
			}else{
				result.setMsg("没有删除任何报销记录，可能该报销记录不存在，或该申请已出票");
			}
		}
		return result;
	}
	
	
	/**
	 * 查询待审批记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDaishenpi")
	@ResponseBody
	public Map<String, Object> findDaiShenpi(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = getSessInfo(session);
		pbaoxiao.setStatus(SysConfig.CW_BX_BEGIN);
		Map<String, Object> results = publicCaiwuService.findBaoxiaoByLeader(pbaoxiao, page, sessionInfo.getId());
//		Map<String, Object> results = publicCaiwuService.findBaoxiaoByLeader(pbaoxiao, page, sessionInfo.getEmail());
//		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getEmail());
//		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getEmail());
//		results.put("lastYearTotal", lastyear);
//		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryDaishenpiTotal")
	@ResponseBody
	public Map<String, Object> queryDaishenpiTotal(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = getSessInfo(session);
		Map<String, Object> results = new HashMap<>();
		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getEmail());
		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getEmail());
		results.put("lastYearTotal", lastyear);
		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	/**
	 * 查询已批准记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryYipizhun")
	@ResponseBody
	public Map<String, Object> findYipizhun(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = getSessInfo(session);
		Map<String, Object> results = publicCaiwuService.findBxByApprover(pbaoxiao, page, sessionInfo.getId());
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryYipizhunTotal")
	@ResponseBody
	public Map<String, Object> queryYipizhunTotal(PBaoxiao pbaoxiao, Page page, HttpSession session){
		SessionInfo sessionInfo = getSessInfo(session);
		pbaoxiao.setStatus(SysConfig.CW_BX_APPROVE_AGREE);
		Map<String, Object> results = new HashMap<String, Object>();
		Double lastyear = publicCaiwuService.getLastYearYpz(sessionInfo.getId());
		Double thisyear = publicCaiwuService.getThisYearYpz(sessionInfo.getId());
		results.put("lastYearTotal", lastyear);
		results.put("thisYearTotal", thisyear);
		results.put("success", true);
		return results;
	}
	
	/**
	 * 报销批准
	 * @param id
	 * @param agree
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/approveBaoxiao")
	@ResponseBody
	public Json approveBaoxiao(@RequestParam(value="id",required=true) Long id, String approRemark,@RequestParam(value="agree",required=true) Boolean agree, HttpSession session){
		Json result = new Json();
		if(null!=id && id!=0){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			PBaoxiao pbaoxiao = new PBaoxiao();
			pbaoxiao.setId(id);
			pbaoxiao.setApproRemark(approRemark);
			pbaoxiao.setApproid(sessionInfo.getId());
			pbaoxiao.setApprover(sessionInfo.getUsername());
			pbaoxiao.setAproEmail(sessionInfo.getEmail());
			if(publicCaiwuService.updateApprove(pbaoxiao,agree)>0){
				result.setSuccess(true);
				result.setMsg("批准成功");
			}else{
				result.setMsg("批准失败，可能原因是您没有批准此条记录的权限或该记录不是处于待批准状态，请您确认您是此条记录申请人组织架构里的细胞核或者是指导。");
			}
		}else{
			result.setMsg("报销信息批次号不存在。");
		}
		return result;
	}
	
	/**
	 * 查询待收票记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDaishoupiao")
	@ResponseBody
	public Map<String, Object> findDaishoupiao(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_APPROVE_AGREE);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryDaishoupiaoTotal")
	@ResponseBody
	public Map<String, Object> queryDaishoupiaoTotal(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_APPROVE_AGREE);
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("success", true);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();//2018.01.01
		cal.add(Calendar.YEAR, -2);
		Date lastYear = cal.getTime();
		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		return results;
	}
	
	/**
	 * 接收报销邮寄单
	 * @param pbaoxiao
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/recivedBaoxiao")
	@ResponseBody
	public Json recivedBaoxiao(@RequestParam(value="id",required=true) Long id,@RequestParam(value="agree",required=true) Boolean agree, String spRemarks, HttpSession session){
		Json result = new Json();
		if(null!= id && id != 0 && null != agree){
			SessionInfo sessionInfo = getSessInfo(session);
			spRemarks = spRemarks != null ? spRemarks : "";
			if(publicCaiwuService.updateBaoxiaoReceive(id, agree, spRemarks, sessionInfo.getId()) > 0){
				result.setSuccess(true);
				result.setMsg("更新成功！");
			};
		}else{
			result.setMsg("批次号不存在");
		}
		return result;
	}
	
	/**
	 * 查询待审核记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDaiShenhe")
	@ResponseBody
	public Map<String, Object> findDaiShenhe(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_RECIVED_AGREE);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryDaiShenheTotal")
	@ResponseBody
	public Map<String, Object> queryDaiShenheTotal(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_RECIVED_AGREE);
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("success", true);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();//2018.01.01
		cal.add(Calendar.YEAR, -2);
		Date lastYear = cal.getTime();
		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		return results;
	}
	
	/**
	 * 报销单审核
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/checkBaoxiao")
	@ResponseBody
	public Json checkBaoxiao(@RequestParam(value="id",required=true)Long id,@RequestParam(value="agree",required=true) Boolean agree, HttpSession session){
		Json result = new Json();
		if(null != id && id != 0){
			SessionInfo sessionInfo = getSessInfo(session);
				if(publicCaiwuService.updateBaoxiaoCheck(id, agree, sessionInfo.getId())>0){
					result.setSuccess(true);
					result.setMsg("录库成功!");
				}else{
					result.setMsg("录库失败!");
				}
		}else{
			result.setMsg("批次号不存在。");
		}
		return result;
	}
	
	/**
	 * 查询待出票记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDaiChupiao")
	@ResponseBody
	public Map<String, Object> findDaiChupiao(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_CHECK_AGREE);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryDaiChupiaoTotal")
	@ResponseBody
	public Map<String, Object> queryDaiChupiaoTotal(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_CHECK_AGREE);
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("success", true);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();//2018.01.01
		cal.add(Calendar.YEAR, -2);
		Date lastYear = cal.getTime();
		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		return results;
	}
	
	/**
	 * 报销单出票
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baoxiaoChupiao")
	@ResponseBody
	public Json baoxiaoChupiao(PChupiaoBaoxiao pbaoxiao, Page page, HttpSession session){
		Json result = new Json();
		if(null!=pbaoxiao.getId() && pbaoxiao.getId()!=0){
			PBaoxiao pbaoxiaos = new PBaoxiao();
			SessionInfo sessionInfo = getSessInfo(session);
			BeanUtils.copyProperties(pbaoxiao, pbaoxiaos);
			pbaoxiaos.setCpid(sessionInfo.getId());
			if(publicCaiwuService.updateBaoxiaoChupiao(pbaoxiaos) > 0){
				result.setSuccess(true);
				result.setMsg("出票成功！");
			}else{
				result.setMsg("请确认该申请单是待出票状态，请您刷新后重试。");
			}
		}else{
			result.setMsg("批次号不存在，请您刷新后再试。");
		}
		return result;
	}
	
	@RequestMapping(value="/addKoujiekuan")
	@ResponseBody
	public Json addBaoxiaoKjk(PKoukuan pkk){
		Json result = new Json();
		if(null != pkk.getBxid() && null != pkk.getMoney()){
			PBaoxiao pbx = publicCaiwuService.getBaoxiao(pkk.getBxid());
			if(SysConfig.CW_BX_CHECK_AGREE.equals(pbx.getStatus())){//确认是在待审核状态
				Serializable sab = publicCaiwuService.addKouJk(pkk);
				if(!sab.equals(0)){
					result.setSuccess(true);
					result.setObj(sab);
					result.setMsg("添加成功");
				}
			}else{
				result.setMsg("只有待审核状态才可以添加扣借款。");
			}
		}else{
			result.setMsg("请检查您的必填项是否都数据。");
		}
		return result;
	}
	
	@RequestMapping(value="/updateKoujiekuan")
	@ResponseBody
	public Json updateBaoxiaoKjk(PKoukuan pkk){
		Json result = new Json();
		System.out.println(pkk);
		if(null != pkk.getId() && null != pkk.getBxid() && null != pkk.getMoney()){
			if(publicCaiwuService.updateKouJk(pkk)>0){
				result.setSuccess(true);
				result.setMsg("更新成功");
			}
		}else{
			result.setMsg("请检查您的必填项是否都数据。");
		}
		return result;
	}
	
	@RequestMapping(value="/queryKoujiekuan")
	@ResponseBody
	public Json findBaoxiaoKjk(@RequestParam(value="bxid",required=true)Long bxid){
		Json result = new Json();
		if(null != bxid && bxid != 0){
			List<PKoukuan> pks = publicCaiwuService.findJiekoukuan(bxid);
			result.setSuccess(true);
			result.setObj(pks);
		}
		return result;
	}
	
	@RequestMapping(value="/querySelfjk")
	@ResponseBody
	public Json querySelfjk(@RequestParam(value="bxid",required=true)Long bxid, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		Integer id = sessionInfo.getId();
		if(null != bxid && bxid != 0){
			PBaoxiao pbx = publicCaiwuService.getBaoxiao(bxid);
			if(id.equals(pbx.getUid())){
				List<PKoukuan> pks = publicCaiwuService.findJiekoukuan(bxid);
				result.setSuccess(true);
				result.setObj(pks);
			}else{
				result.setMsg("你没有查询该报销单的权限。");
			}
		}else{
			result.setMsg("报销批次号不存在。");
		}
		return result;
	}
	
	/**
	 * 细胞核查看扣借款
	 * @param bxid
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryCellKjk")
	@ResponseBody
	public Json queryCellKjk(@RequestParam(value="bxid",required=true)Long bxid, HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		String email = sessionInfo.getEmail();
		if(null != bxid && bxid != 0){
			PBaoxiao pbx = publicCaiwuService.getBaoxiao(bxid);
			if(email.equals(pbx.getCellCoreEmail()) || email.equals(pbx.getGuidanceEmail())){
				List<PKoukuan> pks = publicCaiwuService.findJiekoukuan(bxid);
				result.setSuccess(true);
				result.setObj(pks);
			}else{
				result.setMsg("你没有查询该报销单的权限。");
			}
		}else{
			result.setMsg("报销批次号不存在。");
		}
		return result;
	}
	
	@RequestMapping(value="/deleteKoujiekuan")
	@ResponseBody
	public Json deleteBaoxiaoKjk(PKoukuan pkk){
		Json result = new Json();
		if(null != pkk.getId()){
			PBaoxiao pbx = publicCaiwuService.getBaoxiao(pkk.getBxid());
			if(SysConfig.CW_BX_CHECK_AGREE.equals(pbx.getStatus())){//确认是在待审核状态
				if(publicCaiwuService.deleteKouJk(pkk.getId())>0){
					result.setSuccess(true);
					result.setMsg("删除成功！");
				}else{
					result.setMsg("删除失败！");
				}
			}else{
				result.setMsg("只有待审核状态才可以删除扣借款。");
			}
		}else{
			result.setMsg("对不起，借款id不存在。");
		}
		return result;
	}
	
	@RequestMapping(value="/removeAllKjk")
	@ResponseBody
	public Json removeAllKjk(@RequestParam(value="bxid", required=true)Long bxid){
		Json result = new Json();
		if(null != bxid && bxid!=0){
			PBaoxiao pbx = publicCaiwuService.getBaoxiao(bxid);
			if(SysConfig.CW_BX_CHECK_AGREE.equals(pbx.getStatus())){//确认是在待审核状态
				result.setSuccess(true);
				result.setMsg("已经删除了[" + publicCaiwuService.deleteKjkByBxid(bxid) + "]条记录。");
			}else{
				result.setMsg("只有待审核状态才可以删除扣借款。");
			}
		}else{
			result.setMsg("报销id不存在，无法删除。");
		}
		return result;
	}
	
	@RequestMapping(value="/updateKoujk")
	@ResponseBody
	public Json baoxiaoKjk(@RequestBody List<Map<String, Object>> pkks){//没有用到的方法
		Json result = new Json();
		if(null != pkks && pkks.size() > 0){
			List<PKoukuan> addLis = new ArrayList<PKoukuan>();
			List<PKoukuan> updateLis = new ArrayList<PKoukuan>();
			List<PKoukuan> deleteLis = new ArrayList<PKoukuan>();
			Iterator<Map<String, Object>> it = pkks.iterator();
			while(it.hasNext()){
				PKoukuan  pk = new PKoukuan();
				Map<String, Object> map = it.next();
				if(null != map.get("bxid")){
					Integer flag = null != map.get("flag") ? (Integer) map.get("flag") : 1;
					if(flag == 1){
						pk.setBxid((Long) map.get("bxid"));
						pk.setOrder(null != map.get("order") ? (Integer) map.get("order") : null);
						pk.setItem(null != map.get("item") ? (String) map.get("item") : null);
						pk.setMoney(null != map.get("money") ? (Float) map.get("money") : null);
						pk.setDescription(null != map.get("description") ? (String) map.get("description") : null);
						addLis.add(pk);
					}else if(flag == 2){
						pk.setBxid((Long) map.get("bxid"));
						pk.setOrder(null != map.get("order") ? (Integer) map.get("order") : null);
						pk.setItem(null != map.get("item") ? (String) map.get("item") : null);
						pk.setMoney(null != map.get("money") ? (Float) map.get("money") : null);
						pk.setDescription(null != map.get("description") ? (String) map.get("description") : null);
						updateLis.add(pk);
					}else if(flag == 3){
						pk.setBxid((Long) map.get("bxid"));
						pk.setOrder(null != map.get("order") ? (Integer) map.get("order") : null);
						pk.setItem(null != map.get("item") ? (String) map.get("item") : null);
						pk.setMoney(null != map.get("money") ? (Float) map.get("money") : null);
						pk.setDescription(null != map.get("description") ? (String) map.get("description") : null);
						deleteLis.add(pk);
					}
					
				}
			}
//			publicCaiwuService.addKouJKList(addLis);
//			publicCaiwuService.updateKouJKList(addLis);
//			publicCaiwuService.deleteKouJKList(addLis);
		}
		return result;
	}
	
//	/**
//	 * 这里可以接受List，Array，Set等，写法是一样的，注意前端写法<br>
//	 * 另外这个必须要使用MappingJacksonHttpMessageConverter这个消息转换器
//	 * 请看我上面的配置
//	 * @param user
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("user2")
////	public String user2(@RequestParam(required = false, value = "list[]") List<User> user, HttpServletRequest request){
//	public String user2(@RequestBody List<Map<String, Object>> user){
//		System.out.println(user.size());
//		return "";
//	}
	
//	@RequestMapping(value="/testlist")
//	@ResponseBody
//	public Json addKoujk(@RequestParam("kks[]") ArrayList<PKoukuan> kks){
////	public Json addKoujk(Object kks){
//		Json result = new Json();
//		System.out.println(kks);
//		return result;
//	}
//	
//	//方式一，用list接收前台的数组参数。  
//	   @RequestMapping(value = "/testList")  
//	   @ResponseBody  
//	   public Json testList(@RequestParam(required = false, value = "list[]") List<String> list, HttpServletRequest request){
//		   Json result = new Json();
//	       System.out.println("---------------list:\t" + list);  
//	       return result;  
//	   }  
	   
//	   //方式一，用list接收前台的数组参数。  
//	   @RequestMapping(value = "/testListObj")  
//	   @ResponseBody  
//	   public Json tslist(@RequestParam(required = false, value = "list[]") List<PKoukuan> list, HttpServletRequest request){
//		   Json result = new Json();
//		   System.out.println("---------------list:\t" + list);  
//		   return result;  
//	   }  
//	   
//	   @RequestMapping(value = "/testObj")  
//	   @ResponseBody  
//	   public Json tstObj(@RequestBody List<PKoukuan> ts, HttpServletRequest request){
//		   
//		   System.out.println("the list size is: " + ts.size());
//		   return null;
//	   }
	   
	/**
	 * 查询待汇款记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryDaihuikuan")
	@ResponseBody
	public Map<String, Object> findDaihuikuan(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_CHUPIAO);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
//		Calendar cal = Calendar.getInstance();
//		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
//		Date thisYear = cal.getTime();
//		cal.add(Calendar.YEAR, 1);
//		Date afterYear = cal.getTime();//2018.01.01
//		cal.add(Calendar.YEAR, -2);
//		Date lastYear = cal.getTime();
//		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
//		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryDaihuikuanTotal")
	@ResponseBody
	public Map<String, Object> queryDaihuikuanTotal(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_CHUPIAO);
		Map<String, Object> results = new HashMap<String, Object>();
//		Calendar cal = Calendar.getInstance();
//		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
//		Date thisYear = cal.getTime();
//		cal.add(Calendar.YEAR, 1);
//		Date afterYear = cal.getTime();//2018.01.01
//		cal.add(Calendar.YEAR, -2);
//		Date lastYear = cal.getTime();
		results.put("dhkTotal", publicCaiwuService.gethuikuanTotal(pbaoxiao));
//		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
//		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		results.put("success", true);
		return results;
	}
	
	/**
	 * 报销单汇款
	 * @param pbaoxiao
	 * @param agree
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baoxiaoHuikuan")
	@ResponseBody
	public Json baoxiaoHuikuan(PBaoxiao pbaoxiao, Boolean agree, Page page, HttpSession session){
		Json result = new Json();
		Integer numbers = publicCaiwuService.updateBaoxiaoHuikuan(pbaoxiao);
		result.setSuccess(true);
		result.setMsg("批量更新成功，已修改数据[" + numbers + "]条数据。");
		return result;
	}
	
	/**
	 * 查询已汇款记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return  queryYihuikuanTotal
	 */
	@RequestMapping(value="/queryYihuikuan")
	@ResponseBody
	public Map<String, Object> findYihuikuan(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_YIHUIKUAN);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/queryYihuikuanTotal")
	@ResponseBody
	public Map<String, Object> queryYihuikuanTotal(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_YIHUIKUAN);
		Map<String, Object> results = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);//2017.01.01 00:00:00
		Date thisYear = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		Date afterYear = cal.getTime();//2018.01.01
		cal.add(Calendar.YEAR, -2);
		Date lastYear = cal.getTime();
		results.put("lastYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, lastYear, thisYear));
		results.put("thisYearTotal", publicCaiwuService.getBaoxiaoTotal(pbaoxiao, thisYear, afterYear));
		results.put("success", true);
		return results;
	}
	
	@RequestMapping(value="/getAllCells")
	@ResponseBody
	public Json getAllCells(HttpSession session){
		Json result = new Json();
		SessionInfo sessinfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		String level = null != sessinfo.getLevel() ? sessinfo.getLevel() : "" ;
		if("细胞核".equals(level) || "预备细胞核".equals(level) || "指导".equals(level)){
			publicCaiwuService.findAllCells(sessinfo.getEmail());
		}
		return result;
	}
	
//	private Map<Integer, Integer> getBaoxiaoStatus(){
//		if(null == CacheManager.getInstance().get(SysConfig.CACHE_COMMON+SysConfig.COMMON_BAOXIAO_STATUS)){
//			List<PBaoxiaoStatus> pbxStatus = publicCaiwuService.findAllBaoxiaoStatus();
//			CacheManager.getInstance().put(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS, pbxStatus);
//			Map<Integer, Integer> pbs = new HashMap<Integer, Integer>();
//			Iterator<PBaoxiaoStatus> it = pbxStatus.iterator();
//			while(it.hasNext()){
//				PBaoxiaoStatus pb = it.next();
//				pbs.put(pb.getStatus(), pb.getPrestatus());
//			}
//			return pbs;
//		}else{
//			return (Map<Integer, Integer>) CacheManager.getInstance().get(SysConfig.CACHE_COMMON + SysConfig.COMMON_BAOXIAO_STATUS);
//		}
//	}
	
	private SessionInfo getSessInfo(HttpSession session){
		return (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
	}
	
	@RequestMapping(value="/getBxBanks")
	@ResponseBody
	public Json getBxBanks(HttpSession session){
		Json result = new Json();
		SessionInfo sessionInfo = getSessInfo(session);
		Integer id = sessionInfo.getId();
		List<PUserBank> pubanks = publicCaiwuService.findBxBanks(id);
		result.setSuccess(true);
		result.setObj(pubanks);
		return result;
	}
	
	@RequestMapping(value="/test")
	public void test(String bank, String account, HttpSession session){
		SessionInfo sessionInfo = getSessInfo(session);
		publicCaiwuService.addUserBank(sessionInfo.getId(), bank, account);
//		System.out.println(ts.getId() + "," + ts.getDate() + "," + ts.getDte());
	}
	
	@RequestMapping(value="/updateKunhao")
	@ResponseBody
	public Json updateKunhao(@RequestParam(required=true) Long id, String kunhao){
		Json result = new Json();
		PBaoxiao pbx = new PBaoxiao();
		pbx.setId(id);
		pbx.setKunhao(kunhao);
		if(publicCaiwuService.updateBaoxiao(pbx, null)>0){
			result.setSuccess(true);
		}else{
			result.setMsg("更新失败。");
		}
		return result;
	}
	
	@RequestMapping(value="/findLeader")
	@ResponseBody
	public List<PLeader> findLeader(@RequestParam(required=true) String name){
		if(name!=""){
			return userService.findLearByname(name);
		}
		return null;
	}
	
	@RequestMapping(value="/getCellCoreInfo")
	@ResponseBody
	public PLeader  getCellcoreMsg(HttpSession session){
		SessionInfo sinfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		return userService.getCellCoreInfo(sinfo.getDepartmentId());
	}
}
