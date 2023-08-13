<%@page import="db.DbTest"%>
<%@page import="db.WifiAll"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WIFI DATA LIST</title>
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
</head>
<body>
<%
	String LAT = request.getParameter("LAT");
	String LNT = request.getParameter("LNT");
	DbTest dbTest = new DbTest();
	List<WifiAll> wifiList = dbTest.dbSelect(LAT, LNT);
	dbTest.searchLog(LAT, LNT);

/* 	out.write("lat : " + LAT + "<br>");
	out.write("lnt : " + LNT +  "<br>"); */
%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.html">
			<button>Home</button>
		</a> <a href="history.jsp">
			<button>Location History</button>
		</a> <a href="load-wifi.jsp">
			<button>Open API 와이파이 정보 가져오기</button>
		</a>
	</div>
	<div>
		<label for="fname">LAT:</label>
		<input type="text" id="LAT" name="LAT" value=<%=LAT%>>
		<label for="fname">LNT:</label>
		<input type="text" id="LNT" name="LNT" value=<%=LNT%>>
		
		<button>내 위치 가져오기 </button>
		
		<button>근처 WIFI 정보 보기 </button>

	</div>

	<table>
		<thead>
			<tr>
				<th>거리</th>
				<th>관리번호</th>	
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>	
				<th>상세주소</th>	
				<th>설치위치(층)</th>	
				<th>설치유형</th>	
				<th>설치기관</th>	
				<th>서비스구분</th>	
				<th>망종류</th>	
				<th>설치년도</th>	
				<th>실내외구분</th>	
				<th>wifi접속환경</th>	
				<th>Y좌표</th>	
				<th>X좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%

				for (WifiAll wifi : wifiList) {
					out.write("<tr>");
/* 					out.write("<td>" + wifi.getWifiNumber() + "</td>");
					out.write("<td>" + wifi.getWifiName() + "</td>"); */
					out.write("<td>" + wifi.getDistance() + "</td>");
					out.write("<td>" + wifi.getMGR_NO() + "</td>");
					out.write("<td>" + wifi.getWRDOFC() + "</td>");
					out.write("<td>" + wifi.getMAIN_NM() + "</td>");
					out.write("<td>" + wifi.getADRES1() + "</td>");
					out.write("<td>" + wifi.getADRES2() + "</td>");
					out.write("<td>" + wifi.getINSTL_FLOOR() + "</td>");
					out.write("<td>" + wifi.getINSTL_TY() + "</td>");
					out.write("<td>" + wifi.getINSTL_MBY() + "</td>");
					out.write("<td>" + wifi.getSVC_SE() + "</td>");
					out.write("<td>" + wifi.getCMCWR() + "</td>");
					out.write("<td>" + wifi.getCNSTC_YEAR() + "</td>");
					out.write("<td>" + wifi.getINOUT_DOOR() + "</td>");
					out.write("<td>" + wifi.getREMARS3() + "</td>");
					out.write("<td>" + wifi.getLAT() + "</td>");
					out.write("<td>" + wifi.getLNT() + "</td>");
					out.write("<td>" + wifi.getWORK_DTTM() + "</td>");
					
					out.write("</tr>");
				}
/* 				out.write(wifiList.toString()); */
				%>

			</tr>
		</tbody>
	</table>


</body>
</html>