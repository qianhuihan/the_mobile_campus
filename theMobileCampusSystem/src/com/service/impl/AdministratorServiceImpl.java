package com.service.impl;

import com.bean.Administrator;
import com.dao.AdministratorDao;
import com.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService
{
	private AdministratorDao administratorDao;
	
	public AdministratorDao getAdministratorDao() {
		return administratorDao;
	}

	public void setAdministratorDao(AdministratorDao administratordao) {
		this.administratorDao = administratordao;
	}

	public boolean check(Administrator administrator) 
	{
		if(administratorDao.check(administrator))
			return true;
		return false;
	}
	
}
