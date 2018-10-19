<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.News"%>
<%@ page import="com.bean.Phones"%>
<%@ page import="com.bean.ClassInform"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机校园-学生主页</title>
<link href="css/layout1.css" rel="stylesheet" type="text/css"/>
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
	    		<div class="maintext">资料共享</div></div></a></div>
	    <div class="right">    
	    <div class="xuan">  
	    	<div class="rpic">
	    	<img src="images/new.jpg" width="600" height="400" >
	    	
	    	</div>
	    	<div class="next">
	    	<div class="r1">
	    		<div class="r1_news"><a href="">新闻速递</a></div>
	    		<div class="r1_heng"></div>
	    		<div class="r1_word">
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">我校校友获中国高校校友羽毛球联赛冠军</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">我校小城镇协同创新中心在黄岩举行产学研基地揭牌仪式</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">梅新林一行赴富阳洽谈校地合作</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">“学者与编辑面对面-高水平学术论文的写作与发表”报告会在我校举行</a></div>
	    		</div></div>
	    	<div class="r1">
	    		<div class="r1_news"><a href="">班级通知</a></div>
	    		<div class="r1_heng"></div>
	    		<div class="r1_word">
	    			<div class="mark">●</div>
	    		    <div class="word"><a href="">关于申报2017年度省级引进国外智力项目的通知</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">诚邀海外英才申报“千人计划”项目</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">关于推荐非洲加纳大学孔子学院中方院长人选的通知</a></div>
	    		    <div class="mark">●</div>
	    		    <div class="word"><a href="">关于做好2017年百千万人才工程国家级人选推荐工作的通知</a></div>

	    		</div></div>
	        <div class="r1">
	        	<div class="r1_news"><a href="">常用电话</a></div>
	    		<div class="r1_heng"></div>
	    		<div class="r1_word">
	    		    
	    		    <div class="word1">校园值班电话</div>
	    		    <div class="wp">85003199</div>
	    		    <div class="word1">校园值班电话</div>
	    		    <div class="wp">85003199</div>
	    		    <div class="word1">校园值班电话</div>
	    		    <div class="wp">85003199</div>
	    		    <div class="word1">校园值班电话</div>
	    		    <div class="wp">85003199</div>
	    		</div></div></div>
        </div></div>


	</div>
	<div class="down">Copyright©2016 computer，889966</div>
</div>
</body>
</html>