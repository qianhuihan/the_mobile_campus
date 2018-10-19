package com.action;

import java.util.ArrayList;
import java.util.List;

import com.bean.Information;
import com.bean.InformationName;
import com.bean.Students;
import com.bean.Teachers;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ShowInformationAction extends BaseAction
{
	private String SID = null;
	private String TID = null;
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
			List<Information> list = null;
			if(this.SID!=null)
				list=this.informationService.showAllS(SID);
			else if(this.TID!=null)
				list=this.informationService.showAllT(TID);
			else
			{
				String type=(String)ActionContext.getContext().getSession().get("type");
				Students stu;
				Teachers tea;
				if(type.equals("学生"))
				{
					stu= (Students)ActionContext.getContext().getSession().get("user");
					list=this.informationService.showAllS(stu.getSID());
				}
				else if(type.equals("教师"))
				{
					tea=(Teachers)ActionContext.getContext().getSession().get("user");
					list=this.informationService.showAllT(tea.getTID());
				}
			}
			List<InformationName> list2 = new ArrayList<InformationName>();
			for(int i=0;i<list.size();i++)
			{
				Information in=list.get(i);
				InformationName inform = new InformationName();
				inform.setSID(in.getSID());
				inform.setTID(in.getTID());
				inform.setSender(in.getSender());
				inform.setTime(in.getTime());
				inform.setBody(in.getBody());
				inform.setSname(this.studentsService.find(in.getSID()).getName());
				inform.setTname(this.teachersService.find(in.getTID()).getName());
				list2.add(inform);
			}
			List<Information> list3=this.informationService.showSome(list2.get(0).getSID(), list2.get(0).getTID());
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
			ActionContext.getContext().getSession().put("allInformation", list2);
			ActionContext.getContext().getSession().put("concreteInformation", list4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
