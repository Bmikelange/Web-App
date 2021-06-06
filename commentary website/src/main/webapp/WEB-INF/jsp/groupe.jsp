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
            Nom du groupe :
            <c:out value="${ groupes.get(HelperURL.getNiemeElement(request.getRequestURI(),1)).getName() }" />
            <br>
            Proprietaire du groupe :
            <c:out value="${ groupes.get(HelperURL.getNiemeElement(request.getRequestURI(),1)).getProprietaire() }" />
            <br>
            Description du groupe :
            <c:out value="${ groupes.get(HelperURL.getNiemeElement(request.getRequestURI(),1)).getDescription() }" />
            <br>
        </div>
    </p>

    <%@ include file="menu.html" %>
</body>

</html>