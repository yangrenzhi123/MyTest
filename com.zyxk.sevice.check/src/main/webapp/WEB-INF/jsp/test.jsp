<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	请输入命令:<input id="cmd" style="with: 100%" /><input id="exec" type="button" value="执行" />
	<div id="content"></div>
</body>
<script src="/resources/jquery/jquery-1.7.2.min.js"></script>
<Script>
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
			},
			error : function() {
				alert(error);
			}
		});
	});
</Script>
</html>