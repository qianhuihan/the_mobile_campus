package com.action;

import java.util.ArrayList;
import java.util.List;

import com.bean.Information;
import com.bean.InformationName;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class GetConcreteInformAction extends BaseAction
{
	private String SID;
	private String TID;
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String execute()
	{
		try{
			List<Information> list3=this.informationService.showSome(SID,TID);
			List<InformationName> list4 = new ArrayList<InformationName>();
			for(int i=0;i<list3.size();i++)
			{
				Information in=list3.get(i);
				InformationName inform = new InformationName();
				inform.setSID(in.getSID());
				inform.setTID(in.getTID());
				inform.setSender(in.getSender());
				inform.setTime(in.getTime());
				inform.setBody(in.getBody());
				inform.setSname(this.studentsService.find(in.getSID()).getName());
				inform.setTname(this.teachersService.find(in.getTID()).getName());
				list4.add(inform);
			}
			ActionContext.getContext().getSession().put("concreteInformation", list4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
