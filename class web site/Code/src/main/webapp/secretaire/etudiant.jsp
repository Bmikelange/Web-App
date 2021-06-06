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
            <h1 style="color:white;">${student.getName()} ${student.getFirstName()}</h1>
            <h2 style="color:white;">${student.getId()}</h2>
            <form method="GET" action="/value/secretaire/PDFOne">
                    <input id="content" type="text" name="identifiant" value="${identifiant}" style="display:none;"/>
                    <input type="submit" value="Exporter Feuilles"/>
            </form>
            <ul class="list-group">
                <c:forEach items="${student.getPresencesRecords()}" var="presence">
                    <c:choose>
                        <c:when test="${presence.isPresence()}">
                            <li class="list-group-item list-group-item-success"> ${presence.getLesson().getName()}
                                <button class="badge" onclick = "present(${presence.getLesson().getId()},${student.getId()},false)">présent</button>
                            </li>
                        </c:when>    
                        <c:when test="${!(presence.isPresence())}">
                            <li class="list-group-item list-group-item-danger"> ${presence.getLesson().getName()}
                                <button class="badge" onclick = "present(${presence.getLesson().getId()},${student.getId()},true)">absent</button>
                            </li>
                        </c:when> 
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </body>
    <%@ include file="../etudiant/footer_etu.html" %>
    <form action="ReturnStudent" name="FormReturn" method="GET" style="display:none;"><input id="return" type="text" name="identifiant" value=""/></form>

    <script>
        var present = async function(idLesson, idStudent,presence) {
            let HttpRequestUpdate = new XMLHttpRequest();
            HttpRequestUpdate.open("POST","ReturnStudent",true);
            HttpRequestUpdate.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpRequestUpdate.send('idStudent=' + idStudent + '&idLesson=' + idLesson + '&presence=' + presence);
            document.getElementById("return").value=idStudent;
            document.forms['FormReturn'].submit();
        }
    </script>

</html>
