package com.chaoxing.oa.controller.pub;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chaoxing.oa.entity.page.common.Page;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.service.PubCaiwuService;
import com.chaoxing.oa.service.PubFileOperatorService;
import com.chaoxing.oa.util.io.FileOperateUtil;

@Controller
@RequestMapping(value = "/public/file")
public class PubfileOperateController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private PubCaiwuService publicCaiwuService;
	@Autowired
	private PubFileOperatorService pubfileOpertor;
	
	@RequestMapping(value="/daihuikuanExport")
	public ModelAndView exportDaihuikuan(PBaoxiao pbaoxiao, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> results = publicCaiwuService.findBaoxiao(pbaoxiao, new Page(1, 30000));
		List<PBaoxiao> pbs = (List<PBaoxiao>) results.get("rows");
		if(pbs!=null && pbs.size()>0){
			String storeName = pubfileOpertor.getDaihuiKuanExcel(pbs);  
    		String realName = "交行自汇模板.xlsx";  
    		String contentType = "application/octet-stream";  
    		try {
    			FileOperateUtil.download(request, response, storeName, contentType,realName);
    		} catch (Exception e) {
    			logger.error("文件下载失败：PubfileOperateController.exportDaihuikuan:" + e);
    		} 
		}
		return null;
	}
}
