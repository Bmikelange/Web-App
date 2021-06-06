<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<meta charset="UTF-8">
<html lang="en">
    <head>
        <title>page web étudiant</title>
        <link rel="stylesheet" href="../css/etudiant.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    </head>
    <%@ include file="header_prof.jsp" %>
    <body style="background-color: #35373a;">
        <ul class="list-group">
            <c:forEach items="${lessons}" var="lesson">
                <li class="list-group-item"> ${lesson.getName()} ${lesson.getDate().toString()} - ${lesson.getDuration()}h
                    <span class="badge">
                        <form method="POST" action="qrcode.jsp">
                                <input id="return" type="text" name="identifiant" value="${lesson.getId()}" style="display:none;"/>
                                <input style="color:black" type="submit" value="génerer QR Code"/>
                        </form>
                    </span>
                </li>
            </c:forEach>
        </ul>
    </body>
    <%@ include file="../etudiant/footer_etu.html" %>

</html>
