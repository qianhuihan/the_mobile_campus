<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.Teachers"%>
<%@ page import="com.bean.ClassInform"%>
<%@ page import="com.bean.InformationName"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>消息互发</title>
<link href="css/layout8.css" rel="stylesheet" type="text/css"/>
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
	        <div class="mewen">⚐&nbsp;&nbsp;&nbsp;<a href="">发布公告</a>&nbsp;&nbsp;&nbsp;⚐</div>
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
	    		<div class="c_left">
	    			<div class="allpeople">✧联系人列表</div>
	    	<%
    		List<InformationName> coulist=(List<InformationName>)session.getAttribute("allInformation");
	    	InformationName information;
    		if(coulist!=null && coulist.size()!=0)
            {
		    	if(type.equals("学生"))
		    	{
                for(int i=0;i<coulist.size();i++)
            	{
                	information=coulist.get(i);
            		    if(information!=null)
            		    {
            		    %>
	    			<a href="getConcreteInform.action?SID=<%=information.getSID() %>&&TID=<%=information.getTID() %>"><div class="person1">
	    				<div class="icon"><img src="images/icon.png" width="40" height="40"></div>
	    				<div class="name"><%=information.getTname() %></div></div></a>
	    			<%}}}else{
	    		for(int i=0;i<coulist.size();i++)
            	{
                	information=coulist.get(i);
            		    if(information!=null)
            		    {%>
	    			<a href="getConcreteInform.action?SID=<%=information.getSID() %>&&TID=<%=information.getTID() %>"><div class="person1">
	    				<div class="icon"><img src="images/icon.png" width="40" height="40"></div>
	    				<div class="name"><%=information.getSname() %></div></div></a>
            		<%}}}} %>
	    		</div>
	    		
	    		<div class="c_right">
	    		<%
	    		List<InformationName> inlist=(List<InformationName>)session.getAttribute("concreteInformation");
		    	InformationName conIn;
	    		if(inlist!=null && inlist.size()!=0)
	            {
			    	if(type.equals("学生"))
			    	{
			    		%>
			    		<div class="teachername"><%=inlist.get(0).getTname() %></div>
				    	<%
			    		for(int i=0;i<inlist.size();i++)
			    		{
			    			conIn=inlist.get(i);
	            		    if(conIn.getSender().equals("T"))
	            		    {
	    		%>
	    			<div class="chat">
	    				<div class="chat_icon"><img src="images/icon.png" width="35" height="35"></div>
	    				<div class="chat_passage"><%=conIn.getBody() %></div></div>
	    				<%}else{ %>
	    			<div class="chat">
	    				<div class="chat_icon1"><img src="images/icon.png" width="35" height="35"></div>
	    				<div class="chat_passage1"><%=conIn.getBody() %></div></div>
	    				<%}}%>
	    				<div class="foot">
	    				<form action="sendMessage?SID=<%=inlist.get(0).getSID() %>&&TID=<%=inlist.get(0).getTID() %>&&sender=S" method="post">
	    				<div class="te">
	    				<textarea class="text"  name="body"></textarea></div>
	    				<input class="butt" type="submit" name="add" value="发送"/></form>
	    			</div>
	    				<%}else{ %>
	    				<div class="teachername"><%=inlist.get(0).getSname() %></div><%
			    		for(int i=0;i<inlist.size();i++)
			    		{
			    			conIn=inlist.get(i);
	            		    if(conIn.getSender().equals("S"))
	            		    {
	    		%>
	    			<div class="chat">
	    				<div class="chat_icon"><img src="images/icon.png" width="35" height="35"></div>
	    				<div class="chat_passage"><%=conIn.getBody() %></div></div>
	    				<%}else{ %>
	    			<div class="chat">
	    				<div class="chat_icon1"><img src="images/icon.png" width="35" height="35"></div>
	    				<div class="chat_passage1"><%=conIn.getBody() %></div></div>
	    				<%}}%>
	    				<div class="foot">
	    				<form action="sendMessage?SID=<%=inlist.get(0).getSID() %>&&TID=<%=inlist.get(0).getTID() %>&&sender=T" method="post">
	    				<div class="te">
	    				<textarea class="text"  name="body"></textarea></div>
	    				<input class="butt" type="submit" name="add" value="发送"/></form>
	    			</div>
	    				<%}}
	    			if(session.getAttribute("sendmessage")!=null)
	    			{
	    			String message=(String)session.getAttribute("sendmessage"); 
	    			session.setAttribute("sendmessage", null);
	    			if(message.equals("fail"))
	    			{
	    				out.print("<script>alert('"+"信息发送失败，请重试！"+"');window.location.href='/theMobileCampusSystem/showInformation.action'</script>");
	    			}
	    			else{
	    				response.sendRedirect("/theMobileCampusSystem/showInformation.action");

	    	            return;        
	    			}}%>
	    				
	    			
	    		</div>
	    	</div>

	</div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html> 