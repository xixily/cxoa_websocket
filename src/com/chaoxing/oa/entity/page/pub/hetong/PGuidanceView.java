package com.chaoxing.oa.entity.page.pub.hetong;

import java.math.BigDecimal;

public class PGuidanceView {
	private String cellCore;
	private String email;
	private String cemail;
	private String gemail;
	private BigDecimal lastYear;
	private BigDecimal thisYear;
	private BigDecimal yingshou;
	
	public String getCellCore() {
		return cellCore;
	}

	public String getEmail() {
		return email;
	}

	public String getCemail() {
		return cemail;
	}

	public String getGemail() {
		return gemail;
	}

	public BigDecimal getLastYear() {
		return lastYear;
	}

	public BigDecimal getThisYear() {
		return thisYear;
	}

	public BigDecimal getYingshou() {
		return yingshou;
	}

	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public void setGemail(String gemail) {
		this.gemail = gemail;
	}

	public void setLastYear(BigDecimal lastYear) {
		this.lastYear = lastYear;
	}

	public void setThisYear(BigDecimal thisYear) {
		this.thisYear = thisYear;
	}

	public void setYingshou(BigDecimal yingshou) {
		this.yingshou = yingshou;
	}

	@Override
	public String toString() {
		return "PGuidanceView [cellCore=" + cellCore + ", cemail=" + cemail + ", gemail=" + gemail + ", lastYear="
				+ lastYear + ", thisYear=" + thisYear + ", yingshou=" + yingshou + "]";
	}
	
	
}
