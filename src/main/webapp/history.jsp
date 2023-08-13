<%@page import="db.LogAll"%>
<%@page import="java.util.List"%>
<%@page import="db.DbTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 히스토리 목록</title>
</head>  
<style>
	table {
		withd: 100%;
	}
	th, td {
		border: solid 1px #000;
	}
	thead {
		color: white;
		background-color: #006400;
	}
</style>
<body>
	<h1>위치 히스토리 목록</h1>
	<div>
	<%
		DbTest dbTest = new DbTest();
		List<LogAll> logList = dbTest.showLog();
	%>
		<a href="index.html">
			<button>Home</button>
		</a> <a href="history.jsp">
			<button>Location History</button>
		</a> <a href="load-wifi.jsp">
			<button>Open API 와이파이 정보 가져오기</button>
		</a>
	</div>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X 좌표</th>
				<th>Y 좌표</th>
				<th>조회일자</th>
				<th>비고</th>	
			</tr>
		</thead>
		<tbody>
			<tr>
				<%

				for (LogAll log : logList) {
					out.write("<tr>");
					out.write("<td>" + log.getID() + "</td>");
					out.write("<td>" + log.getLAT() + "</td>");
					out.write("<td>" + log.getLNT() + "</td>");
					out.write("<td>" + log.getTIMESTAMP() + "</td>");
					out.write("<td><button>" +  "삭제" + "</button></td>");
					out.write("</tr>");
				}
/* 				out.write(wifiList.toString()); */
				%>

			</tr>
		</tbody>
	</table>

</body>
</html>