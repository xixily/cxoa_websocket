package com.chaoxing.oa.controller.inner;
//package com.chaoxing.oa.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.chaoxing.oa.entity.page.SF.Cargo;
//import com.chaoxing.oa.entity.page.SF.KuaidiList;
//import com.chaoxing.oa.entity.page.SF.Order;
//import com.chaoxing.oa.entity.page.SF.OrderResponse;
//import com.chaoxing.oa.entity.page.common.Json;
//import com.chaoxing.oa.entity.page.common.Page;
//import com.chaoxing.oa.entity.page.hetong.PCompanyInfo;
//import com.chaoxing.oa.entity.page.hetong.PContract;
//import com.chaoxing.oa.entity.page.hetong.PFahuo;
//import com.chaoxing.oa.entity.page.hetong.PFapiao;
//import com.chaoxing.oa.service.HetongService;
//import com.chaoxing.oa.util.BarCode128C;
//import com.chaoxing.oa.util.ResourceUtil;
//import com.chaoxing.oa.util.SFUtil;
//
//@Controller
//@RequestMapping("/hetong")
//public class HeTongcontroller {
//	private HetongService hetongService;
//
//	public HetongService getHetongService() {
//		return hetongService;
//	}
//
//	@Autowired
//	public void setHetongService(HetongService hetongService) {
//		this.hetongService = hetongService;
//	}
//	
//	@RequestMapping(value = "/queryFahuo")
//	@ResponseBody
//	public Map<String, Object> getHetong(PFahuo pfahuo,Page page, HttpSession session){
//		Map<String, Object> hetongInfos = hetongService.findFahuo(pfahuo,page,0);
//		return hetongInfos;
//	}
//	
//	@RequestMapping(value = "/queryFapiao")
//	@ResponseBody
//	public Map<String, Object> getFapiao(PFapiao pfapiao, HttpSession session){
//		Map<String, Object> hetongInfos = hetongService.findFapiao(pfapiao, 0);
//		return hetongInfos;
//	}
//	
//	@RequestMapping(value = "/updateFapiao")
//	@ResponseBody
//	public Json updateFapiao(PFapiao pfapiao, HttpSession session){
//		Json result = new Json();
////		SessionInfo seesesion = (SessionInfo) session.getAttribute(Resource);
//		if(pfapiao.getId()!=0&&pfapiao.getQueryStatus()!=null){
//			if(hetongService.updateFapiao(pfapiao)>0){
//				result.setSuccess(true);
//				result.setMsg("更新成功!~");
//			}else{
//				result.setMsg("更新失败!~");
//			}
//		}else{
//			result.setMsg("id为空或者发票数量为空");
//		}
//		return result;
//	}
//	
//	@RequestMapping(value = "/sendKuaidi")
//	@ResponseBody
//	public Json sendKuaidi(PFahuo pfahuo, HttpSession session){
//		Json result = new Json();
//		if(null!=pfahuo.getOrderid()){
//			pfahuo = hetongService.getFahuo(pfahuo.getOrderid());
//			if(null!=pfahuo && null!=pfahuo.getD_address() && null!=pfahuo.getD_company() && null!=pfahuo.getD_contact() && null!=pfahuo.getD_tel() && null != pfahuo.getSender()){
//				PContract pcontract = hetongService.getContract(pfahuo.getHetongCode());
//				if(null!=pcontract){
//					Order order = new Order();
//					String company = pcontract.getCompany();
//					PCompanyInfo companyInfo = hetongService.getCompanyInfoByName(pcontract.getCompany());
//					BeanUtils.copyProperties(pfahuo, order);
//					//order 默认邮寄type=1 paymethod=1
//					order.setJ_tel(PCompanyInfo.DEFAULT_TEL);
//					order.setJ_address(PCompanyInfo.DEFAULT_ADDRESS);
//					if(null!=companyInfo){
//						if(null != companyInfo.getAddress()){
//							order.setJ_address(companyInfo.getAddress());
//						}
//						if(null != companyInfo.getTelNumber()){
//							order.setJ_tel(companyInfo.getTelNumber());
//						}
//					}
//					order.setJ_company(company);
//					order.setJ_contact(pfahuo.getSender());
//					order.setCustid(ResourceUtil.getKey("custid"));
//					Cargo cargo = new Cargo();
//					cargo.setName("文件");
//					cargo.setCount(1);
//					List<Cargo> cargos = new ArrayList<Cargo>();
//					cargos.add(cargo);
//					Json rs2 = SFUtil.sendOrder(order, cargos, null);
//					if(rs2.isSuccess()){
//						OrderResponse or = (OrderResponse) rs2.getObj();
//						result.setSuccess(true);
//						if(null != or){
//							pfahuo.setMailno(or.getMailno());
//							pfahuo.setDestcode(or.getDestcode());
//							pfahuo.setOrigincode(or.getOrigincode());
//							pfahuo.setFilter_result(or.getFilter_result());
//							if(hetongService.updateFahuo(pfahuo)>0){
//								result.setMsg("下订单成功，并录库!~");
//							}else{
//								result.setMsg("下订单成功，但录库失败。请手动保存邮寄凭证号：[" + or.getMailno() + "]");
//							}
//						}else{
//							result.setMsg("下单成功，但没有获取到任何反馈信息。");
//						}
//					}else{
//						result = rs2;
//					}
//				}
//			}else{
//				result.setMsg("寄件人信息存在错误，请核实！~");
//			}
//		}else{
//			result.setMsg("id不存在，请核实！");
//		}
//		return result;
//	}
//	
//	@RequestMapping(value = "/queryKuaidiList")
//	@ResponseBody
//	public Json getKuaidiList(PFahuo pfahuo){
//		Json result = new Json();
//		if(null != pfahuo.getOrderid()){
//			pfahuo = hetongService.getFahuo(pfahuo.getOrderid());
//			if(null != pfahuo){
//				PContract pcontract = hetongService.getContract(pfahuo.getHetongCode());
//				if(null!=pcontract){
//					String company = pcontract.getCompany();
//					PCompanyInfo companyInfo = hetongService.getCompanyInfoByName(pcontract.getCompany());
//					KuaidiList klist = new KuaidiList();
//					BeanUtils.copyProperties(pfahuo, klist);
//					klist.setJ_tel(PCompanyInfo.DEFAULT_TEL);
//					klist.setJ_address(PCompanyInfo.DEFAULT_ADDRESS);
//					if(null!=companyInfo){
//						if(null != companyInfo.getAddress()){
//							klist.setJ_address(companyInfo.getAddress());
//						}
//						if(null != companyInfo.getTelNumber()){
//							klist.setJ_tel(companyInfo.getTelNumber());
//						}
//					}
//					klist.setJ_company(company);
//					klist.setJ_contact(pfahuo.getSender());
//					klist.setCustid(ResourceUtil.getKey("custid"));
//					klist.setCount(1);
//					result.setObj(klist);
//					result.setSuccess(true);
//					result.setMsg("获取顺丰打印单信息成功！~");
//				}else{
//					result.setMsg("发货信息为空或没有该合同！~");
//				}
//			}else{
//				result.setMsg("没有该条发货信息！~");
//			}
//		}else{
//			result.setMsg("发货id不能为空！~");
//		}
//		return result;
//	}
//	
//	@RequestMapping(value = "/createCode128C")
//	@ResponseBody
//	public Json createCode128c(PFahuo pfahuo, HttpSession session){
//		Json result = new Json();
//		if(null != pfahuo.getMailno()&&""!=pfahuo.getMailno()&&!"".equals(pfahuo.getOrderid())){
//			String web_url = session.getServletContext().getRealPath("/") + "template/sfTemplate/img/" + "code_" + pfahuo.getOrderid() + ".jpg";;
//			BarCode128C.getCode128CPicture(pfahuo.getMailno(), 22, web_url );
//			result.setSuccess(true);
//			result.setMsg("条形码已重新生成！~");
//		}else{
//			result.setMsg("邮寄凭证号为空！~");
//		}
//		return result;
//		
//	}
//}
