<%@ page language="java" contentType="text/html; charset=utf-8"
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
<div id="contents"> 
	<div class="c_left">
	<div class="c_left_list"><a href="">流入</a></div>
	<div class="c_left_list"><a href="">流出</a></div>
	<div class="c_left_list"><a href="">存量</a></div>
	<div class="c_left_list"><a href="">结算</a></div>
	</div> 
	<div class="c_right"><div id="main">报表模块</div></div> 
</div> 

<div id="footer">数据决策系统</div> 
</div>

<script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));
        var stroption = '{ "title":{"text":"瑞安金融各公司存量金额(单位：万元)"},"tooltip":{},"legend":{"data":["销量","收入1"]},"xAxis":{"data":["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]},"yAxis":{},"series":[{"name":"销量","type":"bar","data":[5,20,36,10,10,20]},{"name":"收入1","type":"bar","data":[5,20,36,10,10,20]}]}';
        var option = JSON.parse(stroption);
        myChart.setOption(option);
</script>
</body>
</html>