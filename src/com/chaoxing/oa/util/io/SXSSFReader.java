package com.chaoxing.oa.util.io;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class SXSSFReader {
	public static final int CELL_TYPE_STRING = 1;
	public static final int CELL_TYPE_NUMBERIC = 2;
	private int currentRow = -1;
	private int currentCell = -1;
	private String fileName = null;
	private InputStream is = null;
	private Workbook wb = null;
	private Sheet sheet = null;
	private Row row = null;

	/**
	 * 
	 * @param fileName  传入文件的绝对路径
	 */
	public SXSSFReader(String fileName) {
		this.fileName = fileName;
		try {
			System.out.println("POIReader start read file." + fileName);
			//放入bufferdInput流
			is = new BufferedInputStream(new FileInputStream(fileName), 1024);
			System.out.println("POIReader start open file." + fileName);
			if (fileName.toLowerCase().endsWith("xls")) {
				this.wb = new SXSSFWorkbook();
			} else {
				this.wb = new SXSSFWorkbook();
			}
			// this.wb = WorkbookFactory.create(is);
			System.out.println("POIReader create sheet.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public void loadSheet(int i) {
		this.sheet = this.wb.getSheetAt(i);
	}

	public boolean hasRow() {
		return this.sheet.getRow(this.currentRow + 1) != null;
	}

	public void nextRow() {
		this.row = this.sheet.getRow(++this.currentRow);
		this.currentCell = -1;
	}

	public void skipCell(int i) {
		this.currentCell += i;
	}

	public void skipRow(int i) {
		this.currentRow += i;
	}

	public boolean isCellNull() {
		if (this.currentCell < 0) {
			this.currentCell = 0;
		}
		return this.row.getCell(this.currentCell) == null;
	}

	public boolean hasCell() {
		return this.row.getCell(this.currentCell + 1) != null;
	}

	public int getCellType(int i) {
		return this.row.getCell(i).getCellType();
	}

	public int getNextCellType() {
		return this.row.getCell(this.currentCell + 1).getCellType();
	}

	public String getNextString() {
		return this.row.getCell(++this.currentCell).getStringCellValue();
	}

	public String getNextStringFormat() {
		Cell c = this.row.getCell(++this.currentCell);
		if (c == null) {
			return "";
		}
		if (c.getCellType() == CELL_TYPE_STRING) {
			return c.getStringCellValue();
		} else {
			return NumberFormat.getInstance().format(c.getNumericCellValue())
					.replace(",", "").replace(".", "");
		}
	}

	public Date getNextDateFormat() {
		Cell c = this.row.getCell(++this.currentCell);
		if (c != null) {
			if (c.getCellType() == CELL_TYPE_STRING) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					return format.parse(c.getStringCellValue());
				} catch (ParseException e) {
					// MYTAG Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return c.getDateCellValue();
			}
		}
		return new Date(0);
	}

	public BigDecimal getNextBigDecimalFormat() {
		Cell c = this.row.getCell(++this.currentCell);
		if (c == null) {
			return new BigDecimal(0);
		}
		if (c.getCellType() == CELL_TYPE_STRING) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(c.getNumericCellValue());
		}
	}

	public String getStringByIndex(int i) {
		Cell c = this.row.getCell(i);
		if (c == null)
			return null;
		if (c.getCellType() == CELL_TYPE_STRING) {
			return c.getStringCellValue();
		} else {
			return NumberFormat.getInstance().format(c.getNumericCellValue())
					.replace(",", "").replace(".", "");
		}
		//return c.getStringCellValue();
	}

	public double getNextNumberic() {
		return this.row.getCell(++this.currentCell).getNumericCellValue();
	}

	public double getNumbericByIndex(int i) {
		return this.row.getCell(i).getNumericCellValue();
	}

	public void destroy() {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			is = null;
		}
		row = null;
		sheet = null;
		wb = null;
	}

}
