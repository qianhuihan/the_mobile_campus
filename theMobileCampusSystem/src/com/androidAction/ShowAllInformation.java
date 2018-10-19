package com.androidAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.Information;
import com.bean.InformationName;

@SuppressWarnings("serial")
public class ShowAllInformation extends BaseAction
{
	private Map<String, Object> jdata;
	private String SID = null;
	private String TID = null;
	
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
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
		jdata = new HashMap<String, Object>();
		try
		{
			List<Information> list = null;
			if(this.SID!=null)
				list=this.informationService.showAllS(SID);
			else if(this.TID!=null)
				list=this.informationService.showAllT(TID);
			
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
			
			jdata.put("information", list2);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
}
