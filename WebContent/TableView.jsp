<%@page import="BigDataAnalysis.Base.StriptType"%>
<%@page import="org.apache.avro.generic.GenericData.StringType"%>
<%@ page language="java" import="BigDataAnalysis.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<%
response.setHeader("Cache-Control", "no-cache");  
Base myBase = new Base(request,response);
String myTable = "Tables";
if (myBase.request.getParameter("Table") != null){
	myTable = myBase.request.getParameter("Table");
}

TableView myTableView = new TableView(myTable, myBase);



myBase.printPage("<html>");
myBase.printPage("	<head>");
myBase.importScript("css/ReportView.css",StriptType.CSS);
myBase.importScript("javascript/jquery-3.3.1.js",StriptType.JAVASCRIPT);
//myBase.printPage("<link rel=\"shortcut icon\" href=\"image/logo.png\" type=\"image/png\" />");
myBase.printPage("		<meta charset=\"utf-8\">");
myBase.printPage("		<title>大数据决策平台</title>");
myBase.printPage("	</head>");
myBase.printPage("<body>");
myBase.printPage("<div id=\"page\">");
myBase.printPage("<div id=\"header\">");
myBase.printPage("	<div class=\"logo\"></div>");
myBase.printPage("	<div class=\"logo_text\">大数据决策平台</div>");
myBase.printPage("</div> ");
myBase.printPage("<div id=\"contents\"> ");
myBase.printPage("	<div class=\"c_left\">");
myBase.printPage("	<div class=\"c_left_list\"><a href=\"\">流入</a></div>");
myBase.printPage("	<div class=\"c_left_list\"><a href=\"\">流出</a></div>");
myBase.printPage("	<div class=\"c_left_list\"><a href=\"\">存量</a></div>");
myBase.printPage("	<div class=\"c_left_list\"><a href=\"\">结算</a></div>");
myBase.printPage("	</div> ");
myBase.printPage("	<div class=\"c_right\">");
myBase.printPage("	   <div id=\"main\">");

myTableView.printTableLines();

myBase.printPage("     </div");
myBase.printPage("   </div>");




myBase.printPage("</body>");
myBase.printPage("</html>");

myTableView.kill();
%>



