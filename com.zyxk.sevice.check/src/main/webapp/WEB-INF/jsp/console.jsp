<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="runStatus"></div><input id="start" type="button" value="运行" /><input id="stop" type="button" value="停止" />
	<div id="serviceStatus"></div>
	<div>请输入命令:<input id="cmd" style="with: 100%" /><input id="exec" type="button" value="执行" /></div>
	<div id="content"></div>
</body>
<script src="/resources/jquery/jquery-1.7.2.min.js"></script>
<Script>
	getRunStatus();
	getServiceStatus();

	$("#start").click(function(){
		$.ajax({
			url : "/1",
			success : function(result) {
				getRunStatus();
				getServiceStatus();
			},
			error : function(a) {
				alert(a);
			}
		});
	});
	$("#stop").click(function(){
		$.ajax({
			url : "/0",
			success : function(result) {
				getRunStatus();
				getServiceStatus();
			},
			error : function(a) {
				alert(a);
			}
		});
	});
	$("#exec").click(function(){
		if($("#cmd").val() == "" || $("#cmd").val() == null){
			alert("请输入命令");
			return ;
		}
		$.ajax({
			url : "/ps",
			data : {
				cmd : $("#cmd").val()
			},
			success : function(result) {
				var s = "";
				for (var i = 0; i < result.length; i++) {
					s = s + result[i] + "<br />";
				}
				$("#content").html(s);
				

				getRunStatus();
				getServiceStatus();
			},
			error : function(a) {
				alert(a);
			}
		});
	});
	
	function getServiceStatus(){
		$.ajax({
			url : "/serviceStatus",
			success : function(result) {
				var s = "";
				for (var i = 0; i < result.length; i++) {
					s = s + result[i] + "<br />";
				}
				$("#serviceStatus").html(s);
			},
			error : function(a) {
				alert(a);
			}
		});
	}
	
	function getRunStatus(){
		$.ajax({
			url : "/runStatus",
			success : function(a) {
				$("#runStatus").html(a);
			},
			error : function(a) {
				alert(a);
			}
		});
	}
</Script>
</html>