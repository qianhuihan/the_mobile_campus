<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.News" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>手机校园</title>
<link href="css/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="all">
	<div class="head">
	    	<div class="logo">⚘</div>
	    	<div class="logo1"><img src="images/logo1.jpg" width="550" height="50" >
	    	                   <img src="images/logo2.jpg" width="270" height="15" ></div>
	        <div class="name">欢迎您，请先登录！</div></div>
	<div class="container">
	    <div class="news">
	    	<div class="today">校园新闻</div>
	    	<%
	    	List<News> list=(List<News>)session.getAttribute("news");
	        News news;
	        if(list!=null && list.size()!=0)
            {
                 for(int i=0;i<list.size();i++)
                 {
                    	news=list.get(i);
                    	if(news!=null && news.getPicture()!=null)
                    	{
                    		%>
	    	<div class="imgnew"><img src="images/<%=news.getPicture() %>" height="150"></div>
	    	<div class="page"><a href=""><%=news.getTitle() %></a></div>
	    	<%  break;
	    		}}}else{ %>
	    	<div class="imgnew"><img src="images/new.jpg" height="150"></div>
	    	<div class="page"><a href="">暂无有效新闻！</a></div>
	    	<%} %>
	    	<div class="main">
	    		<div class="mainpic"><img src="images/m1.png" width="40" heigh="40"></div>
	    		<div class="maintext">学院资料</div></div>
	    	<div class="main">
	    		<div class="mainpic"><img src="images/m2.png" width="40" heigh="40"></div>
	    		<div class="maintext">点名签到</div></div>
	    	<div class="main">
	    		<div class="mainpic"><img src="images/m3.png" width="40" heigh="40"></div>
	    		<div class="maintext">消息互发</div></div>
	    	<div class="main">
	    		<div class="mainpic"><img src="images/m4.png" width="40" heigh="40"></div>
	    		<div class="maintext">资料共享</div></div></div>
	    <div class="register">    
	    <div class="xuan">  
	    	<div class="text1">用户登录</div>
	    	<form action="login.action" method="post">
	    	<div class="text4">用户类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="radio" id="usertype" name="usertype" value="student" checked="checked">学生
                        <input type="radio" id="usertype" name="usertype" value="teacher">教师
                        <input type="radio" id="usertype" name="usertype" value="administrator">管理员</div>
	    	<div class="text2">工&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	    	<input class="te1" type="text" name="loginname" id="loginname" size="25"></div>
	    	<div class="text3">密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
	    	<input class="te2" type="password" name="password" id="password" size="25"></div>
             <div class="text5"><input class="te3" type="submit" value="登录" size="30">
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <a href="">忘记密码？</a></div>
             </form>
             </div>
        </div>
	<%if(session.getAttribute("loginmessage")!=null)
	{
		String message=(String)session.getAttribute("loginmessage");
		session.setAttribute("loginmessage", null);
		if(message.equals("失败"))
			out.print("<script>alert('"+"用户名或密码错误，请重新登录！"+"');window.location.href='/theMobileCampusSystem/index.jsp'</script>");
		else if(message.equals("学生"))
			out.print("<script>alert('"+"登陆成功，跳转至学生主界面！"+"');window.location.href='/theMobileCampusSystem/studentMain.jsp'</script>");
		else if(message.equals("教师"))
			out.print("<script>alert('"+"登陆成功，跳转至教师主界面！"+"');window.location.href='/theMobileCampusSystem/teacherMain.jsp'</script>");
		else if(message.equals("管理员"))
			out.print("<script>alert('"+"登陆成功，跳转至管理员主界面！"+"');window.location.href='/theMobileCampusSystem/administratorMain.jsp'</script>");
	}%>

	</div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html>   	
