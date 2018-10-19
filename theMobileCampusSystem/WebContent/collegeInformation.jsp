<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.News"%>
<%@ page import="com.bean.Phones" %>
<%@ page import="com.bean.ClassInform"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学院资料</title>
<link href="css/layout2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="all">
	<div class="head">
	    	<div class="logo">⚘</div>
	    	<div class="logo1"><img src="images/logo1.jpg" width="550" height="50" >
	    	                   <img src="images/logo2.jpg" width="270" height="15" ></div>
	        <div class="exit"><a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="index.jsp">退出</a></div></div>
	<div class="container">
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
	    		
	    <div class="center">  
	        <div class="news">  
	        	<div class="news_title">
	        		<div class="tu"><div class="magnifying-glass"></div></div>
	        		<div class="news_word">校园新闻</div>
	        		<div class="more_news"><a href="">>>更多</a></div>
	        	</div>
	        	<div class="news_specific">
	        	<%
	    	List<News> newslist=(List<News>)session.getAttribute("news");
	        News news;
	        if(newslist!=null && newslist.size()!=0)
            {
                 for(int i=0;i<newslist.size()&& i<10;i++)
                 {
                    	news=newslist.get(i);
                    	if(news!=null)
                    	{
                    		%>
	        		<div class="ns">
	        		<div class="n1">●&nbsp;&nbsp;&nbsp;<a href=""><%=news.getTitle() %> </a></div>
	        		<div class="n2"><%=news.getDate().subSequence(0, 10) %></div></div>
	        		<%}}}else{ %>
	        		<div class="ns">暂无有效新闻！
	        		<%} %>
	        	</div></div>
	        <div class="course">
	        	<div class="course_title">
	        		<div class="tu1"><div class="pacman"></div></div>
	        		<div class="course_word">课程列表</div>
	        		<div class="more_course"><a href="classTable.jsp">>>更多</a></div>
	        	</div>
	        	<div class="course_specific">
	        		<div class="cs">
	        			<%
	        		List<Courses> coulist2=(List<Courses>)session.getAttribute("Allcourse");
	        		Courses course2;
	        		if(coulist2!=null && coulist2.size()!=0)
                    {
                        for(int i=0;i<coulist2.size();i++)
                    	{
                    		    course2=coulist2.get(i);
                    		    if(course2!=null && course2.getCname()!=null)
                    		    {
                    		    if(i%2==0){%>
	        			<div class="cs1">
	        				<div class="cname"><a href="findACourse.action?CID=<%=course2.getCID() %>"><%=course2.getCname() %></a></div>
	        				<div class="cn"><%=course2.getCID() %></div>
	        			    <div class="cn"><%=course2.getPlace() %></div>
	        			</div>
	        			<%}
                    		    else{
                    		    	%>
                    		    	<div class="cs2">
	        				<div class="cname"><a href="findACourse.action?CID=<%=course2.getCID() %>"><%=course2.getCname() %></a></div>
	        				<div class="cn2"><%=course2.getCID() %></div>
	        			    <div class="cn2"><%=course2.getPlace() %></div>
	        			    </div>
	        			    <% }
                    		    }
                        else{ %>
	        			您本学期并无待学科目！
	        			<%}}}%>
	        		</div>
	        	</div>
	        </div>
	    </div>

    </div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html>