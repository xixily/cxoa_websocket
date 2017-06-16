package com.chaoxing.oa.util.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.chaoxing.oa.entity.page.common.ExportModelVo;
import com.chaoxing.oa.util.data.UUIDGenerator;
import com.chaoxing.oa.util.system.ResourceUtil;

public class SXSSFWriter {
	public static final String DEFAULT_FOLDER = ResourceUtil.getDownloadDirectory();
	public static final String SUFFIX = ".xlsx";
	public static final int BUFFER_NUM = 500;
	private String fileName;
	private int currentRow = -1;
	private int currentCell = -1;
	private BufferedOutputStream outputStream;
	private Workbook wb = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;
	private DataFormat dataFormat;
	private CellStyle numbericStyle;
	private UUIDGenerator uuidUtil = new UUIDGenerator();
//	private CellRangeAddress cra = new CellRangeAddress(0, 3, 3, 9);
	
	public SXSSFWriter(String fileName) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date= format.format(new Date());
		this.fileName = fileName + date + uuidUtil.generate() + SUFFIX;
		File file = new File(DEFAULT_FOLDER + this.fileName);
		file.createNewFile();
		outputStream = new BufferedOutputStream(new FileOutputStream(file),1024);
		wb = new SXSSFWorkbook(BUFFER_NUM);
		dataFormat = wb.createDataFormat();
		numbericStyle = wb.createCellStyle();
		numbericStyle.setDataFormat(dataFormat.getFormat("#,###.00"));
	}
	public void createNewSheet(String sheetName) {
		sheet = wb.createSheet(sheetName);
		this.currentCell = -1;
		this.currentRow = -1;
	}

	public void createRow() {
		row = sheet.createRow(++this.currentRow);
		this.currentCell = -1;
	}
	public void createCell() {
		cell = row.createCell(++this.currentCell);
	}

	public void createCell(int i) {
		this.currentCell = i;
		cell = row.createCell(this.currentCell);
	}

	public void skipRow(int i) {
		this.currentRow += i;
	}

	public void skipCell(int i) {
		this.currentCell += i;
	}

	public void setData(String data){
		this.cell.setCellValue(data);
	}
	
	public void setStringData(String data) {
		this.cell.setCellType(Cell.CELL_TYPE_STRING);
		this.cell.setCellValue(data);
	}

	public void setNumbericData(BigDecimal numberic) {
		this.cell.setCellStyle(numbericStyle);
		DecimalFormat format = new DecimalFormat("0.00");
		this.cell.setCellValue(Double.valueOf(format.format(numberic
				.doubleValue())));
	}

	public void setIntegerData(Integer inte) {
		this.cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		this.cell.setCellValue(inte);
	}
	
	public void setDouble(Double dd) {
		this.cell.setCellValue(dd);
	}

	public void setNextStringData(String data) {
		this.createCell(this.currentCell + 1);
		setStringData(data);
	}

	public void setNextNumbericData(BigDecimal numberic) {
		this.createCell(this.currentCell + 1);
		setNumbericData(numberic);
	}

	public void setNextIntegerData(Integer inte) {
		this.createCell(this.currentCell + 1);
		setIntegerData(inte);
	}

	public void setNextFormule(String formule) {
		this.createCell(this.currentCell + 1);
		this.cell.setCellType(Cell.CELL_TYPE_FORMULA);
		this.cell.setCellValue(formule);
	}

	public void flush() throws IOException {
		wb.write(outputStream);
	}

	public void destroy() throws IOException {
		if (outputStream != null) {
			outputStream.close();
		}
		outputStream = null;
		cell = null;
		row = null;
		sheet = null;
		wb = null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void writeList(List<Object> list, List<ExportModelVo> heads){
		setHead(heads);
	}

	public void setHead(List<ExportModelVo> heads) {
		for(int i=0;i<heads.size();i++){
		}
	}
	
	public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol){
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}
	
	public Workbook getWorkBook(){
		return this.wb;
	}
	
	public CellStyle createCellstyle(){
		return wb.createCellStyle();
	}
	
	/**
	 * 给当前 cell 添加样式
	 * @param cs
	 */
	public void setCellstyle(CellStyle cs){
		this.cell.setCellStyle(cs);
	}
	
	/**
	 * 设置绝对居中
	 */
	public void addCenterCellstyle(){
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER); 
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cell.setCellStyle(cs);
	}
	
	/**
	 * 设置头加粗居中
	 */
	public void setHeadStyle(){
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗
		CellStyle cs = this.createCellstyle();
		cs.setFont(font);
		cs.setAlignment(CellStyle.ALIGN_CENTER); 
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cell.setCellStyle(cs);
	}
	
	public static void main(String[] args) {
		try {
			SXSSFWriter sw = new SXSSFWriter("qsdafa");
//			Workbook wb = sw.getWorkBook();
		/*	//字体
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 9);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗
			//样式
			CellStyle cs = wb.createCellStyle();
			cs.setFont(font);
			cs.setAlignment(CellStyle.ALIGN_CENTER); 
			cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//			cs.setBorderTop(CellStyle.BORDER_THIN);
			cs.setLocked(true);
			cs.setWrapText(true);*/
			CellStyle cs = sw.createCellstyle();
			cs.setAlignment(CellStyle.ALIGN_CENTER);
			cs.setBorderTop(CellStyle.BORDER_THIN);
			cs.setBorderBottom(CellStyle.BORDER_MEDIUM_DASHED);
			cs.setBorderLeft(CellStyle.BORDER_THIN);
			cs.setBorderRight(CellStyle.BORDER_THIN);
			sw.createNewSheet("table1");
//			sw.createRow();
//			sw.addMergedRegion(0, 0, 0, 6);
//			sw.createCell();
//			sw.setCellstyle(cs);
////			sw.addCenterCellstyle();
//			sw.setData("有没有合并啊");
			sw.createRow();
			sw.addMergedRegion(0, 0, 0, 15);
			sw.addMergedRegion(0, 0, 16, 19);
			sw.addMergedRegion(0, 0, 20, 36);
			sw.createCell();
			sw.setHeadStyle();
			sw.setStringData("报销信息");
			sw.skipCell(15);
			sw.createCell();
			sw.setHeadStyle();
			sw.setStringData("批准人信息");
			sw.skipCell(3);
			sw.createCell();
			sw.setHeadStyle();
			sw.setStringData("财务信息");
			
//			sw.skipCell(15);
//			sw.createCell();
			
//			sw.createCell();
//			sw.addCenterCellstyle();
			
//			sw.createCell();
//			sw.addCenterCellstyle();
			
			sw.createRow();
			sw.createCell();
			sw.setStringData("批次号");
			sw.createCell();
			sw.setStringData("报销人");
			sw.createCell();
			sw.setStringData("报销人邮箱");
			sw.createCell();
			sw.setStringData("公司");
			sw.createCell();
			sw.setStringData("部门");
			sw.createCell();
			sw.setStringData("岗位");
			sw.createCell();
			sw.setStringData("小组");
			sw.createCell();
			sw.setStringData("细胞核邮箱");
			sw.createCell();
			sw.setStringData("指导邮箱");
			sw.createCell();
			sw.setStringData("申报金额");
			sw.createCell();
			sw.setStringData("还借款金额");
			sw.createCell();
			sw.setStringData("报销张数");
			sw.createCell();
			sw.setStringData("说明");
			sw.createCell();
			sw.setStringData("银行");
			sw.createCell();
			sw.setStringData("账号");
			sw.createCell();
			sw.setStringData("快递单号");
			sw.createCell();
			sw.setStringData("创建时间");
			
			//批准人信息
			sw.setStringData("批准人");
			sw.createCell();
			sw.setStringData("批准人邮箱");
			sw.createCell();
			sw.setStringData("批准时间");
			sw.createCell();
			sw.setStringData("领导意见");
			
			//财务信息
			sw.createCell();
			sw.setStringData("收票人");
			sw.createCell();
			sw.setStringData("收票时间");
			sw.createCell();
			sw.setStringData("收票备注");
			sw.createCell();
			sw.setStringData("审核人");
			sw.createCell();
			sw.setStringData("出票人");
			sw.createCell();
			sw.setStringData("退票金额");
			sw.createCell();
			sw.setStringData("财务备注");
			sw.createCell();
			sw.setStringData("扣借款");
			sw.createCell();
			sw.setStringData("汇款金额");
			sw.createCell();
			sw.setStringData("汇款时间");
			sw.createCell();
			sw.setStringData("状态");
			sw.createCell();
			sw.setStringData("捆号");
			sw.createCell();
			sw.setStringData("汇款批次号");
			sw.createCell();
			sw.setStringData("出票时间");
			sw.createCell();
			sw.setStringData("退款金额");
			sw.createCell();
			sw.setStringData("扣除金额");
			sw.createCell();
			sw.setStringData("最近更新时间");
			
			for (int i = 0; i < 10; i++) {
				sw.createRow();
				for (int j = 0; j < 10; j++) {
					sw.createCell();
//					sw.setStringData("测试" + i + "," +j);
//					sw.setIntegerData(i*j);
					sw.setStringData("362424199301252517");
				}
			}
			System.out.println("表格已经生成");
			sw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
