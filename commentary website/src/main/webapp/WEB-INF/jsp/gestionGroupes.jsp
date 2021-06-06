<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.GestionBillets" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Commentaire" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Groupe" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="fr.univlyon1.m1if.m1if03.helper.HelperLink" %>

<jsp:useBean id="groupes" scope="application" type="java.util.Map" />

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Mes copains !</title>
</head>

<body>
    <h1>Choisissez un groupe</h1>
    <form method="post" action= "${ HelperLink.groupe }">
        <p>
            Entrer un nouveau nom de groupe :
            <input type="text" name="newGroupe" value="test"/>
            <br>
            Avec une description : 
            <input type="text" name="description" value="test"/>
            <br>
            <input type="submit" value="Connexion">
        </p>
    </form>
    <p>
        OU Choisissez un groupe existant : 
        <c:forEach items="${ groupes.keySet() }" var="key">
            <form method="GET" action="${ HelperLink.groupe }/${ groupes.get(key).getName() }/billets">
                <div style="border:solid blue; margin:3%">
                    Nom du groupe :
                    <c:out value="${ groupes.get(key).getName() }" />
                    <br>
                    Proprietaire du groupe :
                    <c:out value="${ groupes.get(key).getProprietaire() }" />
                    <br>
                    Description du groupe :
                    <c:out value="${ groupes.get(key).getDescription() }" />
                    <br>
                    <button type="submit" value="${ groupes.get(key).getName() }" name="existingGroup">
                        Choisir
                    </button>
                </div>
            </form>                
        </c:forEach>
    </p>
    
    <%@ include file="menu.html" %>
</body>

</html>