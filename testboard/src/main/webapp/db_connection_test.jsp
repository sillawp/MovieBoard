<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wp.movie.*, java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DBConnectionInfo dbInfo = 
		(DBConnectionInfo)application.getAttribute("jdbc-info");

	// 1. load JDBC driver class
	Class.forName(dbInfo.getDriverName());
	
	// 2. create db connection object
	Connection conn = DriverManager.getConnection(
			dbInfo.getUrl(), 
			dbInfo.getUsername(), 
			dbInfo.getPassword());
	
	if (conn != null) {
		out.println("Connect to DB suceessfully...<br>");
		out.println("Disconnecting from DB...<br>");
		conn.close();
	}
	else {
		out.println("Fail to connect to DB...");
	}
%>
</body>
</html>