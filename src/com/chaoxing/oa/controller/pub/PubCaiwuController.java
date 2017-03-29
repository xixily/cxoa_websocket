package com.chaoxing.oa.controller.pub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PChupiaoBaoxiao;
import com.chaoxing.oa.entity.page.pub.caiwu.PKoukuan;
import com.chaoxing.oa.entity.page.pub.caiwu.PSelfBaoxiao;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping("/public/caiwu")
public class PubCaiwuController {
	@Autowired
	private PubCaiwuService publicCaiwuService;
	
	@RequestMapping(value="/owners/{ownerId}",method=RequestMethod.GET)
	public String getOwner(@PathVariable String ownerId, Model model){
		System.out.println("路径值参："+ownerId);
		return null;
	}
	
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
//		pbaoxiao.setUsername(sessinfo.getUsername());
//		pbaoxiao.setEmail(sessinfo.getEmail());
//		pbaoxiao.setCellCoreEmail(sessinfo.getCellCoreEmail());
		pbaoxiao.setStatus(SysConfig.CW_BX_BEGIN);
		Serializable sid = publicCaiwuService.addBaoxiao(pbaoxiao);
		if(null!=sid){
			result.setSuccess(true);
			result.setMsg("添加成功，批次号为：" + sid.toString());
		}else{
			result.setMsg("抱歉，添加失败。");
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
				result.setMsg("更新失败，可能是批次号对应的报销人不匹配。");
			}
		}else{
			result.setMsg("没有批次号。");
		}
		return result;
	}
	
//	@RequestMapping(value="/testAnno")
//	@ResponseBody
//	public Json testAnno(){
//		class S{
//			private String type;
//			private String name;
//			private String anno;
//			private Object obj;
//			
//			public String getType() {
//				return type;
//			}
//			public String getName() {
//				return name;
//			}
//			public String getAnno() {
//				return anno;
//			}
//			public Object getObj(){
//				return obj;
//			}
//			public void setType(String type) {
//				this.type = type;
//			}
//			public void setName(String name) {
//				this.name = name;
//			}
//			public void setAnno(String anno) {
//				this.anno = anno;
//			}
//			public void setObj(Object obj){
//				this.obj = obj;
//			}
//			
//		}
//		Json result = new Json();
//		Field[] fields = Baoxiao.class.getDeclaredFields();
//		Baoxiao.class.getMethods();
//		List<S> lis = new ArrayList<S>();
//		S s;
//		Method method;
//		String mes;
//		for (Field field : fields) {
//			s = new S();
//			s.setType(field.getType().getSimpleName());
//			s.setName(field.getName());
//			mes = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
//			try {
//				if(!"getJTime".equals(mes)){
//					method = Baoxiao.class.getMethod(mes);
//					if(null != method.getAnnotation(javax.persistence.Column.class)){
//						s.setObj(method.getAnnotation(javax.persistence.Column.class));
//						if(!method.getAnnotation(javax.persistence.Column.class).updatable())
//							s.setAnno("MethodAnno:" +false);
//					}
//				}
//				if(null != field.getAnnotation(javax.persistence.Column.class))
//					if(field.getAnnotation(javax.persistence.Column.class).updatable())
//						s.setAnno("@Column.updatable:" + true);
//				lis.add(s);
//			} catch (Exception e) {
//				continue;
//			}
//		}
//		result.setObj(lis);
//		return result;
//	}
	
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
		Map<String, Object> results = publicCaiwuService.findBaoxiaoByLeader(pbaoxiao, page, sessionInfo.getEmail());
//		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getEmail());
//		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getEmail());
//		results.put("lastYearTotal", lastyear);
//		results.put("thisYearTotal", thisyear);
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
		pbaoxiao.setStatus(SysConfig.CW_BX_APPROVE_AGREE);
		Map<String, Object> results = publicCaiwuService.findBaoxiaoByLeader(pbaoxiao, page, sessionInfo.getEmail());
		Double lastyear = publicCaiwuService.getLastYear(sessionInfo.getEmail());
		Double thisyear = publicCaiwuService.getThisYear(sessionInfo.getEmail());
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
			Serializable sab = publicCaiwuService.addKouJk(pkk);
			if(!sab.equals(0)){
				result.setSuccess(true);
				result.setObj(sab);
				result.setMsg("添加成功");
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
	
	@RequestMapping(value="/deleteKoujiekuan")
	@ResponseBody
	public Json deleteBaoxiaoKjk(PKoukuan pkk){
		Json result = new Json();
		if(null != pkk.getId()){
			if(publicCaiwuService.deleteKouJk(pkk.getId())>0){
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}else{
				result.setMsg("删除失败！");
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
			result.setSuccess(true);
			result.setMsg("已经删除了[" + publicCaiwuService.deleteKjkByBxid(bxid) + "]条记录。");
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
		Integer numbers = publicCaiwuService.updateBaoxiaoHuikuan();
		if(numbers>0){
			result.setSuccess(true);
			result.setMsg("批量更新成功，已修改数据[" + numbers + "]条数据。");
		}
		return result;
	}
	
	/**
	 * 查询已汇款记录
	 * @param pbaoxiao
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queryYihuikuan")
	@ResponseBody
	public Map<String, Object> findYihuikuan(PBaoxiao pbaoxiao, Page page){
		pbaoxiao.setStatus(SysConfig.CW_BX_YIHUIKUAN);
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, page);
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
}
