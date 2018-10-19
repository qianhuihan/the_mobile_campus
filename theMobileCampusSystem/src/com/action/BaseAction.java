package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.AdministratorService;
import com.service.CoursesService;
import com.service.InformationService;
import com.service.NewsService;
import com.service.PhonesService;
import com.service.ResourceService;
import com.service.StudentsService;
import com.service.TeachersService;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport
{
	public AdministratorService administratorService;
	public TeachersService teachersService;
	public StudentsService studentsService;
	public CoursesService coursesService;
	public NewsService newsService;
	public PhonesService phonesService;
	public InformationService informationService;
	public ResourceService resourceService;
	
	public AdministratorService getAdministratorService() {
		return administratorService;
	}
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	public TeachersService getTeachersService() {
		return teachersService;
	}
	public void setTeachersService(TeachersService teachersService) {
		this.teachersService = teachersService;
	}
	public StudentsService getStudentsService() {
		return studentsService;
	}
	public void setStudentsService(StudentsService studentsService) {
		this.studentsService = studentsService;
	}
	public CoursesService getCoursesService() {
		return coursesService;
	}
	public void setCoursesService(CoursesService coursesService) {
		this.coursesService = coursesService;
	}
	public NewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public PhonesService getPhonesService() {
		return phonesService;
	}
	public void setPhonesService(PhonesService phonesService) {
		this.phonesService = phonesService;
	}
	public InformationService getInformationService() {
		return informationService;
	}
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}
	public ResourceService getResourceService() {
		return resourceService;
	}
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
}
