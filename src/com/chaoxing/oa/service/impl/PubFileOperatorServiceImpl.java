package com.chaoxing.oa.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoxing.oa.dao.impl.BaseDaoImpl;
import com.chaoxing.oa.entity.page.pub.caiwu.PBaoxiao;
import com.chaoxing.oa.entity.po.employee.UserName;
import com.chaoxing.oa.system.SysConfig;
import com.chaoxing.oa.util.io.SXSSFWriter;
import com.chaoxing.oa.util.system.DateUtil;
@Service("pubFileOperatorService")
public class PubFileOperatorServiceImpl implements com.chaoxing.oa.service.PubFileOperatorService {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private BaseDaoImpl<UserName> userDao;
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

	@Override
	public String getyihuikuanExcel(List<PBaoxiao> pbs) {
		SXSSFWriter sxffWriter = null;
		String filePath = null;
		//文件绝对路径
		try {
			sxffWriter = new SXSSFWriter("yihuiKuan");
			filePath = SXSSFWriter.DEFAULT_FOLDER + sxffWriter.getFileName();
			sxffWriter.createNewSheet("报销已汇款");
			createYhkHeader(sxffWriter);
			Map<Integer, String> users = getUserMap();
			Iterator<PBaoxiao> it = pbs.iterator();
			PBaoxiao pb = null;
			while(it.hasNext()){
				pb = it.next();
				sxffWriter.createRow();
				sxffWriter.createCell();
				sxffWriter.setStringData(String.valueOf(pb.getId()));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getUsername());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getFirstLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getSecondLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getThirdLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getFourthLevel());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getCellCoreEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getGuidanceEmail());
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getMoney() !=null ? pb.getMoney() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getHuankuan() !=null ? pb.getHuankuan() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getNumber() !=null ? pb.getNumber() : 0));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getExplain());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getBank());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getAccount());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getKdno());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getApprover());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getAproEmail());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getAproTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getApproRemark());
				
				sxffWriter.createCell();
				sxffWriter.setStringData(users.get(pb.getReciverId())!=null
						? users.get(pb.getReciverId()) :
							getUsername(pb.getReciverId()));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getReciveTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getRcRemarks());
				sxffWriter.createCell();
				sxffWriter.setStringData(users.get(pb.getCheckerId())!=null
						? users.get(pb.getCheckerId()) :
							getUsername(pb.getCheckerId()));
				sxffWriter.createCell();
				sxffWriter.setStringData(users.get(pb.getCpid())!=null
						? users.get(pb.getCpid()) :
							getUsername(pb.getCpid()));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getKunhao());
				sxffWriter.createCell();
				sxffWriter.setStringData(String.valueOf(pb.getCpNumber()));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getCpTime());
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getTuikuan() !=null ? pb.getTuikuan() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getKouchu() !=null ? pb.getKouchu() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getTuipiao() !=null ? pb.getTuipiao() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getKoujk() !=null ? pb.getKoujk() : 0));
				sxffWriter.createCell();
				sxffWriter.setDouble(Double.valueOf(pb.getHuikuan() !=null ? pb.getHuikuan() : 0));
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getBaoxTime());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getCaiwuRemarks());
				sxffWriter.createCell();
				sxffWriter.setStringData(pb.getUpdateTime());
			
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

	private String getUsername(Integer reciverId) {
		if(reciverId != null){
			UserName us = userDao.get(UserName.class, reciverId);
			if(null != us){
				return us.getUsername();
			}
		}
		return String.valueOf("没有找到id为：" + reciverId + "的员工信息。");
	}

	private Map<Integer, String> getUserMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("did", SysConfig.CN_DEPART_ID);
		List<UserName> us = userDao.find("from UserName where departmentId=:did ", params);
		Map<Integer, String> result = new HashMap<>();
		for (UserName userName : us) {
			result.put(userName.getId(), userName.getUsername());
		}
		return result;
	}

	private void createYhkHeader(SXSSFWriter sxffWriter) {
		sxffWriter.createRow();
		sxffWriter.addMergedRegion(0, 0, 0, 15);
		sxffWriter.addMergedRegion(0, 0, 16, 19);
		sxffWriter.addMergedRegion(0, 0, 20, 36);
		sxffWriter.createCell();
		sxffWriter.setHeadStyle();
		sxffWriter.setStringData("报销信息");
		sxffWriter.skipCell(15);
		sxffWriter.createCell();
		sxffWriter.setHeadStyle();
		sxffWriter.setStringData("批准人信息");
		sxffWriter.skipCell(3);
		sxffWriter.createCell();
		sxffWriter.setHeadStyle();
		sxffWriter.setStringData("财务信息");
		
		sxffWriter.createRow();
		sxffWriter.createCell();
		sxffWriter.setStringData("批次号");
		sxffWriter.createCell();
		sxffWriter.setStringData("报销人");
		sxffWriter.createCell();
		sxffWriter.setStringData("报销人邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("公司");
		sxffWriter.createCell();
		sxffWriter.setStringData("部门");
		sxffWriter.createCell();
		sxffWriter.setStringData("岗位");
		sxffWriter.createCell();
		sxffWriter.setStringData("小组");
		sxffWriter.createCell();
		sxffWriter.setStringData("细胞核邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("指导邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("申报金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("还借款金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("报销张数");
		sxffWriter.createCell();
		sxffWriter.setStringData("说明");
		sxffWriter.createCell();
		sxffWriter.setStringData("银行");
		sxffWriter.createCell();
		sxffWriter.setStringData("账号");
		sxffWriter.createCell();
		sxffWriter.setStringData("快递单号");
		sxffWriter.createCell();
		sxffWriter.setStringData("创建时间");
		
		//批准人信息
		sxffWriter.setStringData("批准人");
		sxffWriter.createCell();
		sxffWriter.setStringData("批准人邮箱");
		sxffWriter.createCell();
		sxffWriter.setStringData("批准时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("领导意见");
		
		//财务信息
		sxffWriter.createCell();
		sxffWriter.setStringData("收票人");
		sxffWriter.createCell();
		sxffWriter.setStringData("收票时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("收票备注");
		sxffWriter.createCell();
		sxffWriter.setStringData("审核人");
		sxffWriter.createCell();
		sxffWriter.setStringData("出票人");
		sxffWriter.createCell();
		sxffWriter.setStringData("捆号");
		sxffWriter.createCell();
		sxffWriter.setStringData("汇款批次号");
		sxffWriter.createCell();
		sxffWriter.setStringData("出票时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("退款金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("扣除金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("退票金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("扣借款");
		sxffWriter.createCell();
		sxffWriter.setStringData("汇款金额");
		sxffWriter.createCell();
		sxffWriter.setStringData("汇款时间");
		sxffWriter.createCell();
		sxffWriter.setStringData("财务备注");
		sxffWriter.createCell();
		sxffWriter.setStringData("最近更新时间");
	}
	
	

}
