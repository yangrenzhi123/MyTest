<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>监控</title>
</head>
<body>
	<div>运行状态</div>
	<div id="suspend"></div>
	<div id="content"></div>
</body>
<script src="/resources/jquery/jquery-1.7.2.min.js"></script>
<Script>
	function ajax(){
		$.ajax({
			url : "/info",
			success : function(result) {
				var s = "";
				for (var i = 0; i < result.length; i++) {
					s = s + result[i] + "<br />";
				}
				$("#content").html(s);
			},
			error : function(a) {
				$("#content").html("访问失败");
			}
		});

		$.ajax({
			url : "/getSuspend",
			success : function(result) {
				$("#suspend").html(result);
			},
			error : function(a) {
				$("#suspend").html("访问失败");
			}
		});
	}

	ajax();
	setInterval(function(){
		ajax();
	}, 60000);
</Script>
</html>