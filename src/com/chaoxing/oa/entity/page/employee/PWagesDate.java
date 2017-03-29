package com.chaoxing.oa.entity.page.employee;

public class PWagesDate {
	private String date;
	private int ruzhiDay;
	private int lizhiDay;
	public String getDate() {
		return date;
	}
	public int getRuzhiDay() {
		return ruzhiDay;
	}
	public int getLizhiDay() {
		return lizhiDay;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRuzhiDay(int ruzhiDay) {
		this.ruzhiDay = ruzhiDay;
	}
	public void setLizhiDay(int lizhiDay) {
		this.lizhiDay = lizhiDay;
	}
	@Override
	public String toString() {
		return "PWagesDate [date=" + date + ", ruzhiDay=" + ruzhiDay + ", lizhiDay=" + lizhiDay + "]";
	}
	
}
