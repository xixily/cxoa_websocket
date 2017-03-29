package com.chaoxing.oa.controller.inner;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.common.POStructV;
import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.common.QueryForm;
import com.chaoxing.oa.entity.page.employee.PKaoQin;
import com.chaoxing.oa.entity.page.employee.PMonthWages;
import com.chaoxing.oa.entity.page.employee.PRenshiEmployee;
import com.chaoxing.oa.entity.page.employee.PSheBaoSummary;
import com.chaoxing.oa.entity.page.employee.PshebaoDetail;
import com.chaoxing.oa.entity.page.hetong.PFahuo;
import com.chaoxing.oa.entity.page.system.SessionInfo;
import com.chaoxing.oa.entity.po.view.ShebaoAR;
import com.chaoxing.oa.entity.po.view.ShebaoMX;
import com.chaoxing.oa.entity.po.view.Yidong;
import com.chaoxing.oa.service.EmployeeInfoService;
import com.chaoxing.oa.service.ExportExcelService;
import com.chaoxing.oa.service.HetongService;
import com.chaoxing.oa.util.data.BarCode128C;
import com.chaoxing.oa.util.io.FileOperateUtil;
import com.chaoxing.oa.util.system.ResourceUtil;

@Controller
@RequestMapping(value = "/file")
public class FileOperateController {
	private EmployeeInfoService employeeInfoService;
	private ExportExcelService exportExcelService;
//	@Autowired
//	private HetongService hetongService;
	
	
	public ExportExcelService getExportDao() {
		return exportExcelService;
	}
	@Autowired
	public void setExportDao(ExportExcelService exportExcelService) {
		this.exportExcelService = exportExcelService;
	}
	public EmployeeInfoService getEmployeeInfoService() {
		return employeeInfoService;
	}
	@Autowired
	public void setEmployeeInfoServiceI(EmployeeInfoService employeeInfoServiceI) {
		this.employeeInfoService = employeeInfoServiceI;
	}
	
	 /** 
     * 到上传文件的位置 
     *  
     * @author dengxf 
     * @return 
     */  
    @RequestMapping(value = "to_upload")  
    public ModelAndView toUpload() {  
        return new ModelAndView("fileOperate/upload");  
    }  
  
    /** 
     * 上传文件 
     *  
     * @author dengxf 
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "upload")  
    public ModelAndView upload(HttpServletRequest request) throws Exception {  
  
        Map<String, Object> map = new HashMap<String, Object>();  
  
        // 别名  
        String[] alaises = ServletRequestUtils.getStringParameters(request,"alais");  
  
        String[] params = new String[] { "alais" };  
        Map<String, Object[]> values = new HashMap<String, Object[]>();  
        values.put("alais", alaises);  
  
        List<Map<String, Object>> result = FileOperateUtil.upload(request,  
                params, values);  
  
        map.put("result", result);  
  
        return new ModelAndView("fileOperate/list", map);  
    }  
  
    /** 
     * 下载 
     *  
     * @author dengxf 
     * @param attachment 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "download")  
    public ModelAndView download(HttpServletRequest request,HttpServletResponse response) throws Exception {  
  
        String storeName = "201205051340364510870879724.zip";  
        String realName = "Java设计模式.zip";  
        String contentType = "application/octet-stream";  
  
        FileOperateUtil.download(request, response, storeName, contentType,realName);  
  
        return null;  
    }
    
    
    @RequestMapping(value = "/exportEmployeeExcel")
    public ModelAndView exprotRenshiQuery(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findRenshiUserName(queryForm, session,1);
    	List<PRenshiEmployee> renshiEmployeeInfos = (List<PRenshiEmployee>) res.get("rows");
    	if(renshiEmployeeInfos!=null){
    		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
    		boolean flag = false;
    		if(100 == sessionInfo.getRoleId()){
    			flag = true;
    		}
    		String storeName = exportExcelService.getRenshiQueryExport(renshiEmployeeInfos,flag);  
    		String realName = "导出查询结果表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportYidong")
    public ModelAndView exportYidong(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String,Object> res = employeeInfoService.findYidong(queryForm, session,true);
//    	Map<String, Object> res = employeeInfoService.getRenshiUserName(queryForm, session,1);
//    	List<PRenshiEmployee> renshiEmployeeInfos = (List<PRenshiEmployee>) res.get("rows");
//    	if(renshiEmployeeInfos!=null){
//    		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
//    		boolean flag = false;
//    		if(100 == sessionInfo.getRoleId()){
//    			flag = true;
//    		}
//    		String storeName = exportExcelService.getRenshiQueryExport(renshiEmployeeInfos,flag);  
//    		String realName = "导出查询结果表.xlsx";  
//    		String contentType = "application/octet-stream";  
//    		try {
//    			FileOperateUtil.download(request, response, storeName, contentType,realName);
//    		} catch (Exception e) {
//    			System.out.println("文件下载失败！");
//    			e.printStackTrace();
//    		} 
//    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportKaoqinExcel")
    public ModelAndView exportKaoqinExcel(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findKaoqin(queryForm, session,1);
    	List<PKaoQin> pkaoqins = (List<PKaoQin>) res.get("rows");
    	if(pkaoqins!=null&&pkaoqins.size()>0){
    		String storeName = exportExcelService.getKaoqinExportExcel(pkaoqins);  
    		String realName = "考勤查询结果表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportMonthWagesExcel")
    public ModelAndView exportMonthWagesExcel(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findMonthWages(queryForm, session,1);
    	List<PMonthWages> pMonthWages = (List<PMonthWages>) res.get("rows");
    	if(pMonthWages!=null&&pMonthWages.size()>0){
    		String storeName = exportExcelService.getMonthWagesExcel(pMonthWages);  
    		String realName = "当月工资查询结果表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportShebaoCompany")
    public ModelAndView exportShebaoCompany(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findShebaoCompany(queryForm, session,1);
    	List<PshebaoDetail> pShebaoDetails = (List<PshebaoDetail>) res.get("rows");
    	if(pShebaoDetails!=null&&pShebaoDetails.size()>0){
    		String storeName = exportExcelService.getShebaoCompany(pShebaoDetails);  
    		String realName = "公司社保汇总表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportOS")
    public ModelAndView exportOS(HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findAllStruct(null);
    	List<POStructV> pos = (List<POStructV>) res.get("rows");
    	if(pos!=null&&pos.size()>0){
    		String storeName = exportExcelService.getPOStructExcel(pos);  
    		String realName = "架构表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	return null;
    }
    
    @RequestMapping(value = "/exportOStruct")
    public ModelAndView exportOStruct(HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findAllStruct(null);
    	List<POStructV> pos = (List<POStructV>) res.get("rows");
    	if(pos!=null&&pos.size()>0){
    		String storeName = exportExcelService.getPOStructExcel2(pos);  
    		String realName = "组织结构表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	return null;
    }
    
    @RequestMapping(value = "/exportShebaoSummary")
    public ModelAndView exportShebaoSummary(QueryForm queryForm, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	Map<String, Object> res = employeeInfoService.findShebaoSummary(queryForm, session,1);
    	List<PSheBaoSummary> pShebaoSummarys = (List<PSheBaoSummary>) res.get("rows");
    	if(pShebaoSummarys!=null&&pShebaoSummarys.size()>0){
    		String storeName = exportExcelService.getShebaoSummary(pShebaoSummarys);  
    		String realName = "社保公司汇总表.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			System.out.println("文件下载失败！");
    			e.printStackTrace();
    		} 
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportShebaoDetail")
    public ModelAndView exportShebaoDetail(String type, Page page, String date, HttpServletRequest request, HttpServletResponse response, HttpSession session){
//    	Map<String, Object> res = employeeInfoService.findMonthShebaoDetail(page, date, session,1);
    	if(null!=type && !"".equals(type)){
    		if("111".equals(type)){
    			Map<String, Object> res = employeeInfoService.findShebaoMX(page, date, session,1);
    			List<ShebaoMX> shebaoMXs = (List<ShebaoMX>) res.get("rows");
    			if(shebaoMXs!=null&&shebaoMXs.size()>0){
    				String storeName = exportExcelService.getShebaoMX(shebaoMXs);  
    				String realName = "社保明细表.xlsx";  
    				String contentType = "application/octet-stream";  
    				try {
    					FileOperateUtil.download(request, response, storeName, contentType,realName);
    				} catch (Exception e) {
    					System.out.println("文件下载失败！");
    					e.printStackTrace();
    				} 
    			}
    		}    		
    		if("112".equals(type) || "113".equals(type)){
    			Map<String, Object> res = employeeInfoService.findAddorReduce(page, type, session,1);
    			List<ShebaoAR> shebaoMXs = (List<ShebaoAR>) res.get("rows");
    			if(shebaoMXs!=null&&shebaoMXs.size()>=0){
    				String storeName = exportExcelService.getshebaoAR(shebaoMXs, type);  
    				String realName = "社保明细表.xlsx";  
    				String contentType = "application/octet-stream";  
    				try {
    					FileOperateUtil.download(request, response, storeName, contentType,realName);
    				} catch (Exception e) {
    					System.out.println("文件下载失败！");
    					e.printStackTrace();
    				} 
    			}
    		}
    	}
    	
    	return null;
    }
    
    @RequestMapping(value = "/exportYiDong")
    public ModelAndView exportYiDong(String type, Page page, String date, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	if(null!=type && !"".equals(type)){
//    		Map<String, Object> res = employeeInfoService.findAddorReduce(page, type, session,1);
    		Map<String, Object> res = employeeInfoService.findYidong(page, type, session,1);
    		List<Yidong> yidongs = (List<Yidong>) res.get("rows");
    		if(yidongs!=null&&yidongs.size()>=0){
    			String storeName = exportExcelService.getYidong(yidongs, type);  
    			String realName = "211".equals(type)|| "311".equals(type)?"新员工入职人员登记表.xlsx":
    				"212".equals(type) || "312".equals(type)?"离职人员登记表.xlsx":
    					"213".equals(type)||"313".equals(type)?"员工转正人员登记表.xlsx":
    						"214".equals(type)|| "314".equals(type)?"部门调整登记表.xlsx":"异动表.xlsx";  
    			String contentType = "application/octet-stream";  
    			try {
    				FileOperateUtil.download(request, response, storeName, contentType,realName);
    			} catch (Exception e) {
    				System.out.println("文件下载失败！");
    				e.printStackTrace();
    			} 
    		}
    	}
    	
    	return null;
    }
    
//    @RequestMapping(value = "/exportFahuoQuery")
//    public ModelAndView exportFahuo(PFahuo pFahuo, HttpServletRequest request, HttpServletResponse response, HttpSession session){
//    	Map<String, Object> res = hetongService.findFahuo(pFahuo, new Page(), 1);
//    	List<PFahuo> pFahuos = (List<PFahuo>) res.get("rows");
//    	if(pFahuos!=null&&pFahuos.size()>0){
//    		String storeName = exportExcelService.getFahuo(pFahuos); 
//    		String realName = "发货管理.xlsx";  
//    		String contentType = "application/octet-stream";  
//    		try {
//    			FileOperateUtil.download(request, response, storeName, contentType,realName);
//    		} catch (Exception e) {
//    			System.out.println("文件下载失败！");
//    			e.printStackTrace();
//    		} 
//    	}
//    	
//    	return null;
//    }
    
    @RequestMapping(value = "/codeImage")
    public ModelAndView getcodeImage(PFahuo pfahuo, HttpServletRequest request, HttpServletResponse response, HttpSession session){
    	if(null != pfahuo.getMailno()&&""!=pfahuo.getMailno()&&!"".equals(pfahuo.getOrderid())){
    		try {
				BarCode128C.getCode128CPicture(pfahuo.getMailno(), 22, request, response );
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
}
