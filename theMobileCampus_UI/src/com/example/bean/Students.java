package com.example.bean;

public class Students {
	private String SID;//ѧ��
	private String password;//����
	private String name;//����
	private String phone;//�ֻ�����
	private String email;//����
	private String Sclass;//�༶
	
	public Students(){}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
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

	public String getSclass() {
		return Sclass;
	}

	public void setSclass(String sclass) {
		this.Sclass = sclass;
	}
	
	
}
