<%-- <%@page import="db.FromApi"%> --%>
<%@page import="db.WifiInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WIFI INFO SAVED</title>
</head>
<body>

	<%
	System.out.println("saved page");
/*  	FromApi api = new FromApi();  */
/* 	out.print(api.getWifi()); */
/* 	String str = api.getWifi().toString(); */
/*  	out.print(str); */

	WifiInfo wifi = new WifiInfo();
	int totalNum = wifi.TotalCnt();
	wifi.AddWifi();

	System.out.println(totalNum);
	System.out.println("-----");
	%>
	
	<h1 align='center'> <%=totalNum%> 개의 와이파이 정보를 정상적으로 저장하였습니다.</h1>
	<div align='center'>
		<a href=index.html>
			<button>Go back to Home</button>
		</a>
	</div>
</body>
</html>