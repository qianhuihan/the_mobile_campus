package com.androidAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.RollCallTable;
import com.bean.RollCallTable2;

@SuppressWarnings("serial")
public class FindRollCallResult extends BaseAction
{
	private Map<String, Object> jdata;
	private String CID;
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	
	public String execute()
	{
		jdata = new HashMap<String, Object>();
		
        try
        {
        	List<RollCallTable> list=this.teachersService.findRollcall(CID);
        	List<RollCallTable2> list2=new ArrayList<RollCallTable2>();
        	int num=this.coursesService.classNum(CID);
        	for(int i=0;i<list.size();i++)
        	{
        		RollCallTable2 rollcall = new RollCallTable2();
        		rollcall.setCID(list.get(i).getCID());
        		rollcall.setSID(list.get(i).getSID());
        		rollcall.setRdate(list.get(i).getRdate());
        		rollcall.setSname(this.studentsService.find(list.get(i).getSID()).getName());
        		rollcall.setState(list.get(i).isState());
        		
        		list2.add(rollcall);
        	}
        	jdata.put("rollcallresult", list2);
        	jdata.put("classNum", num);
        	
        	ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return null;
	}

}
