package com.androidAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.SignInTable;
import com.bean.SignInTable2;

@SuppressWarnings("serial")
public class FindSignInResult extends BaseAction
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
        	List<SignInTable> list = this.teachersService.findSignin(CID);
        	int num=this.coursesService.classNum(CID);
        	List<SignInTable2> list2 = new ArrayList<SignInTable2>();
        	for(int i=0;i<list.size();i++)
        	{
        		SignInTable2 signin=new SignInTable2();
        		signin.setCID(list.get(i).getCID());
        		signin.setDeadline(list.get(i).getDeadline());
        		signin.setDistance(list.get(i).getDistance());
        		signin.setSdate(list.get(i).getSdate());
        		signin.setSID(list.get(i).getSID());
        		signin.setStime(list.get(i).getStime());
        		signin.setSname(this.studentsService.find(list.get(i).getSID()).getName());
        		
        		list2.add(signin);
        	}
        	jdata.put("signinresult", list2);
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
