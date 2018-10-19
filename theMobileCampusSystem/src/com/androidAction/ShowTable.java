package com.androidAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.Students;

@SuppressWarnings("serial")
public class ShowTable extends BaseAction
{
	private Map<String, Object> jdata3;
	private String CID;

	public Map<String, Object> getJdata3() {
		return jdata3;
	}

	public void setJdata3(Map<String, Object> jdata3) {
		this.jdata3 = jdata3;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}
	
	public String execute()
	{
		jdata3 = new HashMap<String, Object>();
		try
		{
			List<Students> list=this.coursesService.rollcallTable(CID);
			
			jdata3.put("rollcallTable", list);
			ResultUtils.toJson(ServletActionContext.getResponse(), jdata3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
