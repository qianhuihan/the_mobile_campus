package com.bean;

public class Courses {
	private String CID;//�γ̺�
	private String Cname;//�γ���
	private String TID;//��ʦid
	private String time;//�Ͽ�ʱ��
	private String wday;//�Ͽ�����
	private String place;//�Ͽεص�

	public Courses(){}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWday() {
		return wday;
	}

	public void setWday(String wday) {
		this.wday = wday;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
