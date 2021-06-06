<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<meta charset="UTF-8">
<html lang="en">
    <head>
        <title>historique de connexion</title>
        <link rel="stylesheet" href="../css/etudiant.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    </head>
    <%@ include file="header_etu.jsp" %>
    <body style="background-color: #35373a;">
        <div class="container-fluid">
            <ul class="list-group">
                <c:forEach items="${student.getPresencesRecords()}" var="presence">
                    <c:choose>
                        <c:when test="${presence.isPresence()}">
                            <li class="list-group-item list-group-item-success"> ${presence.getLesson().getName()}
                                <span class="badge">
                                    présent
                                </span>
                            </li>
                        </c:when>    
                        <c:when test="${!(presence.isPresence())}">
                            <li class="list-group-item list-group-item-danger"> ${presence.getLesson().getName()}
                                <span class="badge">
                                    Absent
                                </span>
                            </li>
                        </c:when> 
                    </c:choose>
                </c:forEach>
            </ul> 
        </div>
    </body>
    <%@ include file="footer_etu.html" %>

</html>