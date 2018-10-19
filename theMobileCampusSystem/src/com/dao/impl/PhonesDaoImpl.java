package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Phones;
import com.dao.PhonesDao;

public class PhonesDaoImpl extends HibernateDaoSupport implements PhonesDao
{
	@SuppressWarnings("unchecked")
	public List<Phones> display() 
	{
		String sql="from Phones";
		List<Phones> list=this.getHibernateTemplate().find(sql);
		//ActionContext.getContext().getSession().put("phones", list);
		return list;
	}
	
}
