package com.chaoxing.oa.entity.page.system;

import java.util.List;

public class SessionInfo implements java.io.Serializable {
	private static final long serialVersionUID = -610081469379406214L;
	private int id;//ID
	private String ip;
	private String email;//邮箱
	private String password;//用户密码
	private String username;//用户姓名
	private String sex;//性别
	private int roleId;//权限
	private int roleLevel;
	private String position;//职位
	private String company;//公司名称
	private String identityCard;//身份号
	private String borthDay;//出生年月 
	private String nation;//民族
	private String degree;//学历
	private String phoneNumber;//电话号码
	private int departmentId;//部门ID
	private String fourthLevel;//小组
	private String cellCore;//细胞核
	private String cellCoreEmail;//细胞核邮箱
	private String guidance;//指导
	private String guidanceEmail;//指导邮箱
	private String graduatedSchool;//毕业学校
	private String major;//专业
	private String level;//级别
	private String workPlace;//工作地点
	private String ifSecret;//是否保密
	List<String> resourceUrls;
	private Object response;
	private boolean loginMethod=true;//默认登录方式是账户密码登录，否则是学习通跳转。
	public int getId() {
		return id;
	}
	public String getIp() {
		return ip;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public String getSex() {
		return sex;
	}
	public int getRoleId() {
		return roleId;
	}
	public int getRoleLevel() {
		return roleLevel;
	}
	public String getPosition() {
		return position;
	}
	public String getCompany() {
		return company;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public String getBorthDay() {
		return borthDay;
	}
	public String getNation() {
		return nation;
	}
	public String getDegree() {
		return degree;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public String getFourthLevel() {
		return fourthLevel;
	}
	public String getCellCore() {
		return cellCore;
	}
	public String getCellCoreEmail() {
		return cellCoreEmail;
	}
	public String getGuidance() {
		return guidance;
	}
	public String getGuidanceEmail() {
		return guidanceEmail;
	}
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public String getMajor() {
		return major;
	}
	public String getLevel() {
		return level;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public String getIfSecret() {
		return ifSecret;
	}
	public List<String> getResourceUrls() {
		return resourceUrls;
	}
	public Object getResponse() {
		return response;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public void setBorthDay(String borthDay) {
		this.borthDay = borthDay;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public void setFourthLevel(String fourthLevel) {
		this.fourthLevel = fourthLevel;
	}
	public void setCellCore(String cellCore) {
		this.cellCore = cellCore;
	}
	public void setCellCoreEmail(String cellCoreEmail) {
		this.cellCoreEmail = cellCoreEmail;
	}
	public void setGuidance(String guidance) {
		this.guidance = guidance;
	}
	public void setGuidanceEmail(String guidanceEmail) {
		this.guidanceEmail = guidanceEmail;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public void setIfSecret(String ifSecret) {
		this.ifSecret = ifSecret;
	}
	public void setResourceUrls(List<String> resourceUrls) {
		this.resourceUrls = resourceUrls;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public boolean isLoginMethod() {
		return loginMethod;
	}
	public void setLoginMethod(boolean loginMethod) {
		this.loginMethod = loginMethod;
	}
	
	@Override
	public String toString() {
		return "SessionInfo [id=" + id + ", ip=" + ip + ", email=" + email + ", password=" + password + ", username="
				+ username + ", sex=" + sex + ", roleId=" + roleId + ", roleLevel=" + roleLevel + ", position="
				+ position + ", company=" + company + ", identityCard=" + identityCard + ", borthDay=" + borthDay
				+ ", nation=" + nation + ", degree=" + degree + ", phoneNumber=" + phoneNumber + ", departmentId="
				+ departmentId + ", fourthLevel=" + fourthLevel + ", cellCore=" + cellCore + ", cellCoreEmail="
				+ cellCoreEmail + ", guidance=" + guidance + ", guidanceEmail=" + guidanceEmail + ", graduatedSchool="
				+ graduatedSchool + ", major=" + major + ", level=" + level + ", workPlace=" + workPlace + ", ifSecret="
				+ ifSecret + ", resourceUrls=" + resourceUrls + ", response=" + response + ", loginMethod="
				+ loginMethod + "]";
	}
}
