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
    <%@ include file="header_sec.jsp" %>
    <body style="background-color: #35373a;">
        <div class="container-fluid">
            <form method="GET" action="/value/secretaire/PDFMult">
                    <input id="content" type="text" name="identifiant" value="${master}" style="display:none;"/>
                    <input type="submit" value="Exporter Feuilles"/>
            </form>
            <ul class="list-group">
                <c:forEach items="${students}" var="student">
                    <li class="list-group-item"> ${student.getName()} ${student.getFirstName()}
                        <span class="badge">
                            <form method="GET" action="/value/secretaire/ReturnStudent">
                                    <input id="return" type="text" name="identifiant" value="${student.getId()}" style="display:none;"/>
                                    <input style="color:black" type="submit" value="page étudiant"/>
                            </form>
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
    <%@ include file="../etudiant/footer_etu.html" %>

</html>
