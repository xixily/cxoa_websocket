package com.chaoxing.oa.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.util.io.SXSSFWriter;
@Service("pubFileOperatorService")
public class PubFileOperatorServiceImpl implements com.chaoxing.oa.service.PubFileOperatorService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public String getDaihuiKuanExcel(List<PBaoxiao> pbs) {
		SXSSFWriter sxffWriter = null;
		String filePath = null;
		//文件绝对路径
		try {
			sxffWriter = new SXSSFWriter("daihuikuan");
			filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("报销待汇款");
			createDhkHeader(sxffWriter);
			Iterator<PBaoxiao> it = pbs.iterator();
			PBaoxiao pb = null;
			while(it.hasNext()){
				pb = it.next();
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(getBank(pb.getBank()));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getAccount());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getUsername());
				sxffWriter.createCell();
				sxffWriter.setNumbericData(new BigDecimal(pb.getKoujk()).setScale(2, BigDecimal.ROUND_HALF_UP));//扣借款
				sxffWriter.createCell();
				sxffWriter.setNumbericData(new BigDecimal(pb.getHuikuan()).setScale(2, BigDecimal.ROUND_HALF_UP));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getCaiwuRemarks());
			}
		} catch (IOException e) {
			logger.error("PubFileOperatorServiceImpl.getDaihuiKuanExcel:" + e);
		}finally{
			if(null != sxffWriter){
				try {
					sxffWriter.flush();
					sxffWriter.destroy();
				} catch (IOException e) {
					logger.error("PubFileOperatorServiceImpl.getDaihuiKuanExcel:" + e);
				}
			}
		}
		return filePath;
	}

	private void createDhkHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("银行");
		sxffWriter.createCell();
		sxffWriter.setStringData("卡号");
		sxffWriter.createCell();
		sxffWriter.setStringData("报销人");
		sxffWriter.createCell();
		sxffWriter.setStringData("还款金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("汇款金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("财务备注");
	}
	
	private String getBank(String bcode){
		if(null != bcode){
			if(bcode.equals("jtyh"))return "交通银行";
			if(bcode.equals("gs"))return "工商银行";
			if(bcode.equals("gd"))return "光大银行";
			if(bcode.equals("js"))return "建设银行";
			if(bcode.equals("zs"))return "招商银行";
			if(bcode.equals("nh"))return "农业银行";
			if(bcode.equals("ms"))return "民生银行";
			if(bcode.equals("gk"))return "国家开放银行";
		}
		return "";
	}

}
