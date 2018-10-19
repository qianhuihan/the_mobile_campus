package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Resource;
import com.dao.ResourceDao;

public class ResourceDaoImpl extends HibernateDaoSupport implements ResourceDao
{
	@SuppressWarnings("unchecked")
	public List<Resource> display() 
	{
		String sql="from Resource order by RID desc";
		List<Resource> list = this.getHibernateTemplate().find(sql);
		return list;
	}
	
	public String addDown(int RID)
	{
		try
		{
			String sql="from Resource where RID="+RID;
			Resource resource=(Resource) this.getHibernateTemplate().find(sql).get(0);
			resource.setDown(resource.getDown()+1);
			this.getHibernateTemplate().update(resource);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
	
	public String upload(Resource resource)
	{
		try{
			this.getHibernateTemplate().save(resource);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "文件上传失败！";
		}
		return "文件上传成功！";
	}
}
