<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title></title>
</head>
<body>
<% session.removeAttribute("login"); %>
<% session.invalidate(); %>
<% response.sendRedirect("https://192.168.75.37/v1"); %>
</body>
</html>