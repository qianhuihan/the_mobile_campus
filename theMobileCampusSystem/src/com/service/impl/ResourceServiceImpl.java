package com.service.impl;

import java.util.List;

import com.bean.Resource;
import com.dao.ResourceDao;
import com.service.ResourceService;

public class ResourceServiceImpl implements ResourceService 
{
	private ResourceDao resourceDao;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	public List<Resource> dispaly()
	{
		return this.resourceDao.display();
	}
	
	public String addDown(int RID)
	{
		return this.resourceDao.addDown(RID);
	}

	public String upload(Resource resource)
	{
		return this.resourceDao.upload(resource);
	}
}
