package com.androidAction;

import com.action.BaseAction;

@SuppressWarnings("serial")
public class DownLoad extends BaseAction
{
	private int RID;
	
	public int getRID() {
		return RID;
	}

	public void setRID(int rID) {
		RID = rID;
	}

	public String execute()
	{
		this.resourceService.addDown(RID);
		return null;
	}
}
