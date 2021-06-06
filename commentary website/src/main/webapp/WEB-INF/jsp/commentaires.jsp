<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Commentaire" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Groupe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="fr.univlyon1.m1if.m1if03.helper.HelperURL" %>

<jsp:useBean id="groupes" scope="application" type="java.util.Map" />

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Mes copains !</title>
</head>

<body>
    <p>
        <div style="border:solid blue; margin:3%">
            <c:forEach items="${ groupes.get(HelperURL.getNiemeElement(request.getRequestURI(),1)).getGestion().getListBillets()
                .getBillet(HelperURL.getNiemeElement(request.getRequestURI(),3)).getCommentaires() }" var="commentaire">
                <div style="border:solid red; margin:3%">
                    <h2>Auteur : <c:out value="${ commentaire.getAuteur() }" /></h2>
                    <p>
                        Contenu : <c:out value="${ commentaire.getContenu() }" /><br>
                    </p>
                </div>
            </c:forEach>
        </div>
    </p>

    <%@ include file="menu.html" %>
</body>

</html>