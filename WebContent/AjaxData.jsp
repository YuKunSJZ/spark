<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String optionString="{ \"title\":{\"text\":\"瑞安金融各公司存量金额(单位：万元)\"},\"tooltip\":{},\"legend\":{\"data\":[\"销量\",\"收入1\"]},\"xAxis\":{\"data\":[\"衬衫\",\"羊毛衫\",\"雪纺衫\",\"裤子\",\"高跟鞋\",\"袜子\"]},\"yAxis\":{},\"series\":[{\"name\":\"销量\",\"type\":\"bar\",\"data\":[5,20,36,10,10,20]},{\"name\":\"收入1\",\"type\":\"bar\",\"data\":[5,20,36,10,10,20]}]}";
response.getWriter().print(optionString);

%>