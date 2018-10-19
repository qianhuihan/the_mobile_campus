package com.action;

import java.util.List;

import com.bean.Courses;
import com.bean.Students;
import com.bean.Teachers;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ShowSingleCourseAction extends BaseAction
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
		String type=(String) ActionContext.getContext().getSession().get("type");
		Courses course=this.coursesService.find(CID);
		Teachers teacher = null;
		if(type.equals("Ñ§Éú"))
		{
			if(course!=null)
				teacher=this.coursesService.findt(course);
			ActionContext.getContext().getSession().put("CTeacher", teacher);
			ActionContext.getContext().getSession().put("singleCourse", course);
			return SUCCESS;
		}
		else
		{
			List<Students> list=this.coursesService.rollcallTable(CID);
			ActionContext.getContext().getSession().put("StuTable", list);
			ActionContext.getContext().getSession().put("singleCourse", course);
			return INPUT;
		}
	}
}
