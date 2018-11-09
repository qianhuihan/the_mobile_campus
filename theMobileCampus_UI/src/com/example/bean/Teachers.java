package com.example.bean;

public class Teachers{
	private String TID;//职工号
	private String password;//密码
	private String name;//姓名
	private String phone;//手机号码
	private String email;//邮箱
	private String title;//职称
	private String affiliation;//班主任从属，班级ID，若不是班主任，则为空
	private String sex;//性别
	
	public Teachers(){}
	
	public String getTID() {
		return TID;
	}
	public void setTID(String TID) {
		this.TID = TID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
