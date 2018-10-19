package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Information;
import com.dao.InformationDao;

public class InformationDaoImpl extends HibernateDaoSupport implements InformationDao
{
	@SuppressWarnings("unchecked")
	public List<Information> showAllT(String TID) 
	{
		String hql = "from Information as i where i.TID=? and i.IID in (select max(inf.IID) from Information as inf group by inf.TID,inf.SID) order by i.IID DESC";
		List<Information> list = this.getHibernateTemplate().find(hql, TID);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Information> showAllS(String SID) 
	{
		String hql = "from Information as i where i.SID=? and i.IID in (select max(inf.IID) from Information as inf group by inf.TID,inf.SID) order by i.IID DESC";
		List<Information> list = this.getHibernateTemplate().find(hql, SID);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Information> showSome(String SID, String TID) 
	{
		String hql = "from Information where TID=? and SID=? order by IID ASC";
		List<Information> list = this.getHibernateTemplate().find(hql,new String[]{TID,SID});
		return list;
	}

	public boolean save(Information inform) 
	{
		try
		{
			this.getHibernateTemplate().save(inform);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
}
