<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.Teachers" %>
<%@ page import="com.bean.ClassInform" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>具体课程</title>
<link href="css/layout3.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<% Courses ACourse=(Courses)session.getAttribute("singleCourse");
	Teachers CTeacher=(Teachers)session.getAttribute("CTeacher");%>
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
	    		<div class="maintext">资料共享</div></div></a></div>
	    <div class="up">
	    	<div class="up_left">
	    		<div class="left_word"><%=user.getName() %>，你好！</div>
	    		<div class="left_word">欢迎进入《<%=ACourse.getCname() %>》课程</div>
	    		<div class="left_word">主讲教师：<%=CTeacher.getName() %></div>
	    		<div class="left_word">登陆课程时间：2017-4-16&nbsp;&nbsp;22：35</div>
	    	</div>
	    	<div class="up_right">
	    			<input class="b1" type="submit" value="👩 &nbsp;&nbsp;教师信息"/>
	    			<input class="b1" type="submit" value="🎞 &nbsp;&nbsp;班级作业"/>
	    			<input class="b1" type="submit" value="📝 &nbsp;&nbsp;课程签到"/>
	    	</div>
	    </div>
	    <div class="below">
	    	<div class="homework_title">♙&nbsp;&nbsp;课程作业</div>
	    	<div class="xian"></div>
	    	<div class="h_tit">
	    	     <div class="h1">作业名称</div>
	    	     <div class="h1">截止时间</div>
	    	     <div class="h1">评分</div>
	    	     </div>
	    	<div class="home_ju">
	    	<div class="h_ju">
	    		<div class="h2"><a href="">实验一：hello world！</a></div>
	    		<div class="h2">2017-4-01&nbsp;&nbsp;24：00</div>
	    		<div class="h3">90/100</div>
	    		<a href=""><div class="b2">提交</div></a>
	    	</div>
	    	<div class="h_ju2">
	    		<div class="h2"><a href="">实验二：Soccer game</a></div>
	    		<div class="h2">2017-4-02&nbsp;&nbsp;24：00</div>
	    		<div class="h3">80/100</div>
	    		<div class="b3">已截止</div>

	    	</div>
	    	<div class="h_ju">
	    		<div class="h2"><a href="">实验三：表单制作</a></div>
	    		<div class="h2">2017-5-21&nbsp;&nbsp;24：00</div>
	    		<div class="h3">90/100</div>
	    		<a href=""><div class="b2">提交</div></a>
	    	</div>
	    	<div class="h_ju2">
	    		<div class="h2"><a href="">实验四：文件上传下载</a></div>
	    		<div class="h2">2017-6-02&nbsp;&nbsp;24：00</div>
	    		<div class="h3">暂无评分</div>
	    		<div class="b3">已截止</div>
	    	</div>
	        </div>

	    </div>
	    
    </div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html>