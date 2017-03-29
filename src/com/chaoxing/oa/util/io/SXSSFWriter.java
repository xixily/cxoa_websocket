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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.chaoxing.oa.entity.page.common.ExportModelVo;
import com.chaoxing.oa.util.data.UUIDGenerator;
import com.chaoxing.oa.util.system.ResourceUtil;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class SXSSFWriter {
	public static final String DEFAULT_FOLDER = ResourceUtil.getDownloadDirectory();
	public static final String SUFFIX = ".xlsx";
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
	
	public SXSSFWriter(String fileName) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date= format.format(new Date());
		this.fileName = fileName + date + uuidUtil.generate() + SUFFIX;
		File file = new File(DEFAULT_FOLDER + this.fileName);
		file.createNewFile();
		outputStream = new BufferedOutputStream(new FileOutputStream(file),1024);
		wb = new SXSSFWorkbook(500);
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
	
	public static void main(String[] args) {
		try {
			SXSSFWriter sw = new SXSSFWriter("export2");
			sw.createNewSheet("table1");
			for (int i = 0; i < 10; i++) {
				sw.createRow();
				for (int j = 0; j < 10; j++) {
					sw.createCell();
//					sw.setStringData("测试" + i + "," +j);
//					sw.setIntegerData(i*j);
					sw.setStringData("362424199301252517");
				}
			}
			sw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
