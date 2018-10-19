package com.action;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SignInAction extends BaseAction
{
	private String CID;
	private int stime;
	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}
	
	public int getStime() {
		return stime;
	}

	public void setStime(int stime) {
		this.stime = stime;
	}

	public String execute()
	{
		String message=this.coursesService.signinTable(CID,stime);
		ActionContext.getContext().getSession().put("signin",message);
		return SUCCESS;
	}
	
}
