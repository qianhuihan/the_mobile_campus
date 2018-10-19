package com.action;

import java.util.List;

import com.bean.Students;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class RollCallAction extends BaseAction
{
	private String CID;

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}
	
	public String execute()
	{
		List<Students> list=this.coursesService.rollcallTable(CID);
		if(list!=null)
			ActionContext.getContext().getSession().put("rollcallTable",list);
		return SUCCESS;
	}
}
