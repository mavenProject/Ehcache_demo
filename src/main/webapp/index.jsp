<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date,java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>首页</title>
	</head>
	
	<body>
		<div style="text-align:center;margin-top:360px;">
			<font style="color:green;font-weight:bold;font-size: 18px"><%=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date())%></font><br><br>
			<font style="color:red;font-weight:bold;font-size: 27px">每次刷新页面，如果时间是变动的，则说明该页面没有被缓存或缓存已经过期，否则则说明该页面已经被缓存。</font>
		</div>
	</body>
</html>