<%@page import="BigDataAnalysis.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
FundCompany  myFundCompany = new FundCompany();
myFundCompany.load2ODS();
myFundCompany.close();
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
spark tasks
</body>
</html>