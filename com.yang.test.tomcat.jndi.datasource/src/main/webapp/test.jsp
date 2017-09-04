<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="rs" dataSource="jdbc/TestDB">
select id from T
</sql:query>

<html>
<head>
<title>DB Test</title>
</head>
<body>

	<h2>Results</h2>

	<c:forEach var="row" items="${rs.rows}">
    id ${row.id}<br />
	</c:forEach>
</body>
</html>