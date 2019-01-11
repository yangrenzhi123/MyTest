<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content"></div>
</body>
<script src="/resources/jquery/jquery-1.7.2.min.js"></script>
<Script>
	setInterval(function(){
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
				$("#content").html(a);
			}
		});
	}, 60000);
</Script>
</html>