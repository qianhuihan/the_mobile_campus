<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Teachers"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.Students"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>具体课程_教师</title>
<link href="css/layout10.css" rel="stylesheet" type="text/css"/>
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
	    		
	    <div class="center">
	    	<div class="c_left">
	    		<div class="l_title"><a href="classTable.jsp">返回课程表</a></div>
	    		<div class="l_all">
	    		<%Courses ACourse=(Courses)session.getAttribute("singleCourse"); %>
	    			<div class="coursename">名称：<%=ACourse.getCname() %></div>
	    			<div class="coursename">编号：<%=ACourse.getCID() %></div>
	    			<div class="coursename">教室：<%=ACourse.getPlace() %></div>
	    			<div class="coursename">时间：<%=ACourse.getWday() %><%=ACourse.getTime() %></div>
	    		</div>
	    		<div class="up_right">
	    			<input class="b1" type="submit" value="🏃&nbsp;&nbsp;班级名单"/>
	    			<input class="b1" type="submit" value="🕐&nbsp;&nbsp;开启签到"⚝/>
	    			<input class="b1" type="submit" value="📃&nbsp;&nbsp;点名结果"/>
	    			<input class="b1" type="submit" value="✔&nbsp;&nbsp;签到结果"/></div></div>
	    	<div class="c_right">
	    		<div class="r_title">班级名单</div>
	    		<div class="r_all">
	    		
	    		<%
	    		List<Students> stutable=(List<Students>)session.getAttribute("StuTable"); 
	    		if(stutable!=null){
	    		for(int i=0;i<stutable.size();i++)
	    		{
	    			Students stu=stutable.get(i); %>
	    		
	    			<div class="person1">
		    			<div class="one"><%=stu.getSID() %></div>
		    			<div class="two"><%=stu.getName() %></div>
		    			<input type="checkbox" class="three" name="checkbox1" value="checkbox"></div>
		    		<%}} %>
	    		<div class="foot">
	    			<input class="sub" type="submit" value="提交"/>
	    		</div>
	    		</div>

	    	</div>

	    </div>

	</div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html>