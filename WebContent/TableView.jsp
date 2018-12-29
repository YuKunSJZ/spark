<%@ page language="java" import="BigDataAnalysis.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/ReportView.css" />
<script src="javascript/jquery-3.3.1.js"></script>
<script src="javascript/echarts.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="page">
<div id="header">
	<div class="logo"></div>
	<div class="logo_text">数据决策系统</div>
</div> 
<%
Base myBase = new Base(request,response);
String myTable = "Tables";
if (myBase.request.getParameter("Table") != null){
	myTable = myBase.request.getParameter("Table");
}
myBase.importScript("css/TableView.css",Base.StriptType.CSS);
myBase.importScript("js/TableView.js",Base.StriptType.JAVASCRIPT);
myBase.importScript("https://code.jquery.com/jquery-3.3.1.min.js",Base.StriptType.JAVASCRIPT);
myBase.printPage("<title>"+ myTable +"-瑞安金融大数据视图平台</title>");
myBase.printPage("<link rel=\"shortcut icon\" href=\"image/logo.png\" type=\"image/png\" />");
%>
<div id="contents"> 
	<div class="c_left">
	<div class="c_left_list"><a href="">流入</a></div>
	<div class="c_left_list"><a href="">流出</a></div>
	<div class="c_left_list"><a href="">存量</a></div>
	<div class="c_left_list"><a href="">结算</a></div>
	</div> 
	<div class="c_right"><div id="main">
	
<%
TableView myTableView = new TableView(myTable, myBase);
myTableView.buildPageSearch();
//myTableView.printTable();
%>
		<div class="table_row_column_name">
			<div class = "column_name">日期</div>
			<div class = "column_name">存量类型</div>
			<div class = "column_name">存量金额</div>
			<div class = "column_name">经办人姓名</div>
			<div class = "column_name">产品id</div>
			<div class = "column_name">活期利率</div>
			<div class = "column_name">日期</div>
			<div class = "column_name">存量类型</div>
			<div class = "column_name">存量金额</div>
			<div class = "column_name">经办人姓名</div>
			<div class = "column_name">产品id</div>
			<div class = "column_name">活期利率</div>
		</div>
		<div class="table_row">
			<div class = "column">abc</div>
			<div class = "column">1</div>
			<div class = "column">2</div>
			<div class = "column">3</div>
			<div class = "column">4</div>
			<div class = "column">5利率</div>
			<div class = "column">7</div>
			<div class = "column">5类型</div>
			<div class = "column">7金额</div>
			<div class = "column">经办人865465</div>
			<div class = "column">3435id</div>
			<div class = "column">343343</div>
		</div>
		<div class="table_row">
			<div class = "column">abc</div>
			<div class = "column">1</div>
			<div class = "column">2</div>
			<div class = "column">3</div>
			<div class = "column">4</div>
			<div class = "column">5利率</div>
			<div class = "column">7</div>
			<div class = "column">5类型</div>
			<div class = "column">7金额</div>
			<div class = "column">经办人865465</div>
			<div class = "column">3435id</div>
			<div class = "column">343343</div>
		</div>
		<div class="table_row">
			<div class = "column">dfe</div>
			<div class = "column">1</div>
			<div class = "column">2</div>
			<div class = "column">3</div>
			<div class = "column">4</div>
			<div class = "column">5利率</div>
			<div class = "column">7</div>
			<div class = "column">5类型</div>
			<div class = "column">7金额</div>
			<div class = "column">经办人865465</div>
			<div class = "column">3435id</div>
			<div class = "column">343343</div>
		</div>
	</div></div> 
</div> 

<div id="footer">数据决策系统<span></span></div> 
</div>


</body>
</html>