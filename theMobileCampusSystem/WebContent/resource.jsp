<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.Teachers"%>
<%@ page import="com.bean.ClassInform"%>
<%@ page import="com.bean.Resource"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源共享</title>
<link href="css/layout9.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="all">
	<div class="head">
	    	<div class="logo">⚘</div>
	    	<div class="logo1"><img src="images/logo1.jpg" width="550" height="50" >
	    	                   <img src="images/logo2.jpg" width="270" height="15" ></div>
	        <div class="exit"><a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="index.jsp">退出</a></div></div>
	<div class="container">
	<%
	String type=(String)session.getAttribute("type");
	if(type.equals("学生"))
	{ %>
	<div class="person">
	        	<div class="wen">
	        	<%
                		Students user=(Students)session.getAttribute("user");
                			if(user!=null)
               				{
            	%>
	        	<div class="sid">工&nbsp;&nbsp;&nbsp;号：&nbsp;<%=user.getSID() %></div>
	        	<div class="sid">姓&nbsp;&nbsp;&nbsp;名：&nbsp;<%=user.getName() %></div>
	        	<div class="sid">身&nbsp;&nbsp;&nbsp;份：&nbsp;学生</div>
	        	<%
               				}
                			else{
	        	%><a href="/theMobileCampusSystem/index.jsp">你尚未登录，请先登录！</a>
	        	<%} %>
	        	</div>
	        <div class="todayclass">
	        	<div class="classwen">
	        	<% String wday=(String)session.getAttribute("weekday"); %>
	        		<div class="cw1"><%=wday %>&nbsp;&nbsp;&nbsp;9周</div>
	        		<div class="cw2"><a href="classTable.jsp">>>更多</a></div></div>
	        	<%
	        		List<Courses> coulist=(List<Courses>)session.getAttribute("course");
	        		Courses course;
	        		if(coulist!=null && coulist.size()!=0)
                    {
                        for(int i=0;i<coulist.size();i++)
                    	{
                    		    course=coulist.get(i);
                    		    if(course!=null && course.getCname()!=null)
                    		    {%>
	        	<div class="c1">&nbsp;&nbsp;&nbsp;<%=course.getTime() %>&nbsp;&nbsp;&nbsp;<%=course.getCname() %></div>
	        	<% }}}
                    else
	        		{%>
	        	<div class="c1">今天没有课哦！</div>
	        	<%} %>
	        	</div>
	        <div class="message">
	        	<div class="mewen">重要通知</div>
	        	<%
	        		List<ClassInform> claInform=(List<ClassInform>)session.getAttribute("classInform");
	        		ClassInform inform;
	        		if(claInform!=null && claInform.size()!=0)
                    {
                        inform=claInform.get(0);
                 %>
                 <%=inform.getTitle() %>
                 <br><%=inform.getTime() %>
	        	<div class="jume"><%=inform.getBody() %></div>
	        	<%}else{ %>
	        	<div class="jume">暂无有效通知！</div>
	        	<%} %>
	        	</div>
	    	<a href="collegeInformation.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m1.png" width="40" heigh="40"></div>
	    		<div class="maintext">学院资料</div></div></a>
	    	<a href="classTable.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m2.png" width="40" heigh="40"></div>
	    		<div class="maintext">课程表</div></div></a>
	    	<a href="showInformation.action?SID=<%=user.getSID() %>"><div class="main">
	    		<div class="mainpic"><img src="images/m3.png" width="40" heigh="40"></div>
	    		<div class="maintext">消息互发</div></div></a>
	    	<a href="resource.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m4.png" width="40" heigh="40"></div>
	    		<div class="maintext">资料共享</div></div></div></a>
	
	<%}
	else if(type.equals("教师"))
	{%>
	<div class="person">
	        	<div class="wen">
	        	<%
                		Teachers user=(Teachers)session.getAttribute("user");
                			if(user!=null)
               				{
            	%>
	        	<div class="sid">工&nbsp;&nbsp;&nbsp;号：&nbsp;<%=user.getTID() %></div>
	        	<div class="sid">姓&nbsp;&nbsp;&nbsp;名：&nbsp;<%=user.getName() %></div>
	        	<div class="sid">身&nbsp;&nbsp;&nbsp;份：&nbsp;老师</div>
	            
	            <%
               				}
                			else{
	        	%>
	        	<a href="/theMobileCampusSystem/index.jsp">你尚未登录，请先登录！</a>
	        	<%} %>
	        	</div>
	        <div class="todayclass">
	        	<div class="classwen">
	        	<% String wday=(String)session.getAttribute("weekday"); %>
	        		<div class="cw1"><%=wday %>&nbsp;&nbsp;&nbsp;9周</div>
	        		<div class="cw2"><a href="classTable.jsp">>>更多</a></div></div>
	        	<%
	        		List<Courses> coulist=(List<Courses>)session.getAttribute("Tcourse");
	        		Courses course;
	        		if(coulist!=null && coulist.size()!=0)
                    {
                        for(int i=0;i<coulist.size();i++)
                    	{
                    		    course=coulist.get(i);
                    		    if(course!=null && course.getCname()!=null)
                    		    {%>
	        	<div class="c1">&nbsp;&nbsp;&nbsp;<%=course.getTime() %>&nbsp;&nbsp;&nbsp;<%=course.getCname() %></div>
	        	<% }}}
                    else
	        		{%>
	        	<div class="c1">今天没有课哦！</div>
	        	<%} %>
	        	</div>
	        <div class="mewen1">⚐&nbsp;&nbsp;&nbsp;<a href="">发布公告</a>&nbsp;&nbsp;&nbsp;⚐</div>
	    	<a href="teacherMain.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m1.png" width="40" heigh="40"></div>
	    		<div class="maintext">学院资料</div></div></a>
	    	<a href="classTable.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m2.png" width="40" heigh="40"></div>
	    		<div class="maintext">课程表</div></div></a>
	    	<a href="showInformation.action?TID=<%=user.getTID() %>"><div class="main">
	    		<div class="mainpic"><img src="images/m3.png" width="40" heigh="40"></div>
	    		<div class="maintext">消息互发</div></div></a>
	    	<a href="resource.jsp"><div class="main">
	    		<div class="mainpic"><img src="images/m4.png" width="40" heigh="40"></div>
	    		<div class="maintext">资料共享</div></div></a></div>
	<%} %>
	    			
	    			
	    	
	        <div class="center">
	        	<div class="title">资源共享</div>
	        	<div class="headtitle">
	        		<div class="mo"></div>
	        		<div class="mo1">文件名</div>
	        		<div class="mo2">上传者</div>
	        		<div class="mo2">下载量</div></div>
	        	<div class="res">
	        	<%
	    	List<Resource> list = (List<Resource>)session.getAttribute("resource");
	        	if(list!=null && list.size()!=0)
	        	{
	        		for(int i=0;i<list.size();i++)
	        		{
	        			Resource resour=list.get(i);
	    	%>
		        	<div class="headtitle">
		        		<div class="moo"><img src="images/txt.png" width="40" height="40" ></div>
		        		<div class="moo1"><a href="resource/<%=resour.getFile() %>"><%=resour.getRname() %></a></div>
		        		<div class="moo2"><%=resour.getTID() %></div>
		        		<div class="moo3"><%=resour.getDown() %></div></div>
		        	<div class="line"></div>
		        	<%}} %> 
	        </div>
	        
	        <%if(type.equals("教师")){ %>
			<div class="foot">
	        	<form action="upload.action" method="post" enctype="multipart/form-data">
	        	请选择需要上传的文件：<input class="butt1" type="file" id="dofile" name="file"/>
	        	<input class="butt" type="submit" name="btnupload" id="btnupload" value="上传资料"/>
	        	</form>
			</div> 
			<%} %>
	        </div>
	        
	        <%
	        if(session.getAttribute("uploadmessage")!=null)
	        {
		        String uploadmessage=(String)session.getAttribute("uploadmessage");
		        session.setAttribute("uploadmessage", null);
		        out.print("<script>alert('"+uploadmessage+"');window.location.href='/theMobileCampusSystem/resource.jsp'</script>");
	        }%>

	</div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html> 