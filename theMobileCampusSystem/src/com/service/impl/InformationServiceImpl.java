package com.service.impl;

import java.util.List;

import com.bean.Information;
import com.dao.InformationDao;
import com.service.InformationService;

public class InformationServiceImpl implements InformationService
{
	private InformationDao informationDao;
	
	public InformationDao getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}

	public List<Information> showAllT(String TID) 
	{
		return this.informationDao.showAllT(TID);
	}

	public List<Information> showAllS(String SID) 
	{
		return this.informationDao.showAllS(SID);
	}

	public List<Information> showSome(String SID, String TID) 
	{
		return this.informationDao.showSome(SID, TID);
	}
	
	public boolean save(Information inform)
	{
		return this.informationDao.save(inform);
	}
}
