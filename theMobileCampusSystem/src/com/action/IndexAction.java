package com.action;

import java.util.List;

import com.bean.News;
import com.bean.Phones;
import com.bean.Resource;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class IndexAction extends BaseAction
{
	public String execute()
	{
		List<News> news=this.newsService.display();
		List<Phones> phones=this.phonesService.display();
		List<Resource> resource = this.resourceService.dispaly();
		ActionContext.getContext().getSession().put("phones", phones);
		ActionContext.getContext().getSession().put("news", news);
		ActionContext.getContext().getSession().put("resource", resource);
		return SUCCESS;
	}
}
