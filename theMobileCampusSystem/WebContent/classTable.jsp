<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bean.Students"%>
<%@ page import="com.bean.Courses"%>
<%@ page import="com.bean.Teachers"%>
<%@ page import="com.bean.ClassInform"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>课程表</title>
<link href="css/layout7.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<script type="text/javascript">
	function insertTable1(list) {
		var table = document.getElementById("coursetable");
		for(var i=0;i<list.size();i++){
			if(list.get(i).getWday().equals("周一")){
				insertTable2(list.get(i).getTime(),"1");
			}
			if(list.get(i).getWday().equals("周二")){
				insertTable2(list.get(i).getTime(),"2");
			}
			if(list.get(i).getWday().equals("周三")){
				insertTable2(list.get(i).getTime(),"3");
			}
			if(list.get(i).getWday().equals("周四")){
				insertTable2(list.get(i).getTime(),"4");
			}
			if(list.get(i).getWday().equals("周五")){
				insertTable2(list.get(i).getTime(),"5");
			}
			if(list.get(i).getWday().equals("周六")){
				insertTable2(list.get(i).getTime(),"6");
			}
			if(list.get(i).getWday().equals("周日")){
				insertTable2(list.get(i).getTime(),"7");
			}
		}   
	}
	function insertTable2(time,wday){
		if(time.equals("1-2")){
			var oTd = table.rows[1].cells[wday];
			oTd.innerHTML =list.get(i).getCname();
		}
		if(time.equals("3-4")){
			var oTd = table.rows[3].cells[wday];
			oTd.innerHTML =list.get(i).getCname();
		}
		if(time.equals("5-6")){
			var oTd = table.rows[5].cells[wday];
			oTd.innerHTML =list.get(i).getCname();
		}
		if(time.equals("7-8")){
			var oTd = table.rows[7].cells[wday];
			oTd.innerHTML =list.get(i).getCname();
		}
		if(time.equals("9-10")){
			var oTd = table.rows[9].cells[wday];
			oTd.innerHTML =list.get(i).getCname();
		}
		
	}
</script>
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
	       
	       
	       <%
	       List<Courses> list;
	       if(type.equals("学生"))
	       {
	    	   list= (List<Courses>)session.getAttribute("Allcourse");
	    	}
	       else{
	    	   list= (List<Courses>)session.getAttribute("TAllcourse");
	       }
	       
	      %>
	      <%if(type.equals("学生")) {%>
	      
	    	<div class="center">
	    		<div class="title">⚝&nbsp;&nbsp;&nbsp;课程表</div>
	    		<table class="classt">
	    			<tr>
	    				<td class="day1"></td>
	    				<td class="day">周一</td>
	    				<td class="day">周二</td>
	    				<td class="day">周三</td>
	    				<td class="day">周四</td>
	    				<td class="day">周五</td>
	    				<td class="day">周六</td>
	    				<td class="day">周日</td></tr>
	    			<tr class="jie">
	    				<td>1</td>
	    				<td></td>
	    				<td></td>
	    				<td class="ju" rowspan="2">Java Web程序设计@凤起A101</td></tr>
	    			<tr class="jie">
	    				<td>2</td>
	    				</tr>
	    			<tr class="jie">
	    				<td>3</td>
	    				<td></td>
	    				<td class="ju" style="background:#188a1d" rowspan="2">数据库设计@云林B403<td>
	    				<td></td>
	    				<td class="ju" style="background:#9e3dbe" rowspan="2">算法分析与设计@弦溪A305</td></tr>
	    			<tr class="jie">
	    				<td>4</td></tr>
	    			<tr class="jie">
	    				<td>5</td></tr>
	    			<tr class="jie">
	    				<td>6</td>
	    				<td></td>
	    				<td></td>
	    			    <td class="ju" rowspan="2">计算机组成原理@云林A202</td></tr>
	    			<tr class="jie">
	    				<td>7</td></tr>
	    			<tr class="jie">
	    				<td>8</td>
	    				<td class="ju" style="background:#9e3dbe" rowspan="2">毛泽东思想和中国特色社会主义理论体系@凤起C504</td></tr>
	    			<tr class="jie">
	    				<td>9</td></tr>
	    			<tr class="jie">
	    				<td>10</td></tr>
	    		</table>
	    	</div>
	    	<%}else{%>
	    	<div class="center">
	    		<div class="title">⚝&nbsp;&nbsp;&nbsp;课程表</div>
	    		<table class="classt">
	    			<tr>
	    				<td class="day1"></td>
	    				<td class="day">周一</td>
	    				<td class="day">周二</td>
	    				<td class="day">周三</td>
	    				<td class="day">周四</td>
	    				<td class="day">周五</td>
	    				<td class="day">周六</td>
	    				<td class="day">周日</td></tr>
	    			<tr class="jie">
	    				<td>1</td>
	    				<td></td>
	    				<td></td>
	    				<td class="ju" rowspan="2">Java Web程序设计@凤起A101</td></tr>
	    			<tr class="jie">
	    				<td>2</td>
	    				</tr>
	    			<tr class="jie">
	    				<td>3</td>
	    				<td></td>
	    				<td></td>
	    				<td></td>
	    				<td></td>
	    				<td class="ju" style="background:#9e3dbe" rowspan="2">算法分析与设计@弦溪A305</td></tr>
	    			<tr class="jie">
	    				<td>4</td></tr>
	    			<tr class="jie">
	    				<td>5</td></tr>
	    			<tr class="jie">
	    				<td>6</td></tr>
	    			<tr class="jie">
	    				<td>7</td></tr>
	    			<tr class="jie">
	    				<td>8</td>
	    				</tr>
	    			<tr class="jie">
	    				<td>9</td></tr>
	    			<tr class="jie">
	    				<td>10</td></tr>
	    		</table>
	    	</div>
	    	
	    	<%} %>
	 
	    
    </div>
	<div class="down">Copyright@2016 computer，889966</div>
</div>
</body>
</html>   				 