package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bean.Resource;
import com.bean.Teachers;
import com.opensymphony.xwork2.ActionContext; 

@SuppressWarnings("serial")
public class UploadAction extends BaseAction
{
	//�ϴ��ļ����·��   
	private final static String UPLOADDIR = "/resource";   
    //�ϴ��ļ�����   
    private List<File> file;   
    //�ϴ��ļ�������   
    private List<String> fileFileName;   
    //�ϴ��ļ��������ͼ���   
    private List<String> fileContentType;
    
    public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String execute() throws Exception 
	{   
		Teachers tea=(Teachers)ActionContext.getContext().getSession().get("user");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		for (int i = 0; i < file.size(); i++) 
		{   
			uploadFile(i);
			Resource resource=new Resource();
			resource.setTID(tea.getTID());
			resource.setDate(time);
			resource.setDown(0);
			resource.setFile(this.getFileFileName().toString().substring(1,this.getFileFileName().toString().lastIndexOf("]")));
			resource.setRname(this.getFileFileName().toString().substring(1,this.getFileFileName().toString().lastIndexOf(".")));
			
			String message=this.resourceService.upload(resource);
			ActionContext.getContext().getSession().put("uploadmessage", message);
		}   
		List<Resource> resource = this.resourceService.dispaly();
		ActionContext.getContext().getSession().put("resource", resource);
		
		return SUCCESS;   
	}   
	  
     //ִ���ϴ�����   
     private void uploadFile(int i) throws FileNotFoundException, IOException {   
         try {   
             InputStream in = new FileInputStream(file.get(i));   
             String dir = ServletActionContext.getRequest().getRealPath(UPLOADDIR);  
             File fileLocation = new File(dir);  
             //�˴�Ҳ������Ӧ�ø�Ŀ¼�ֶ�����Ŀ���ϴ�Ŀ¼  
             if(!fileLocation.exists()){  
                 boolean isCreated  = fileLocation.mkdir();  
                 if(!isCreated) {  
                     //Ŀ���ϴ�Ŀ¼����ʧ��,������������,�����׳��Զ����쳣��,һ��Ӧ�ò���������������  
                     return;  
                 }  
             }  
             String fileName=this.getFileFileName().get(i);  
             File uploadFile = new File(dir, fileName);   
             OutputStream out = new FileOutputStream(uploadFile);   
             byte[] buffer = new byte[1024 * 1024];   
             int length;   
             while ((length = in.read(buffer)) > 0) {   
                 out.write(buffer, 0, length);   
             }   
             in.close();   
             out.close();   
         } catch (FileNotFoundException ex) {   
             System.out.println("�ϴ�ʧ��!");  
             ex.printStackTrace();   
         } catch (IOException ex) {   
             System.out.println("�ϴ�ʧ��!");  
             ex.printStackTrace();   
         }   
     }   
}
