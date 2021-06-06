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
<%@ page import="fr.univlyon1.m1if.m1if03.helper.HelperLink" %>

<!doctype html>
<html lang="fr">
<head>
    <title>Gestion de Billet</title>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="5">
</head>
<body>
<!-- <h1>Hello !</h1> -->
<h2>Saisie d'un billet</h2>
<form method="post" action="${ HelperLink.groupe }/${result.getName()}/billets">
    <p>
        Titre :
        <input type="text" name="titre">
    </p>
    <p>
        Contenu :
        <textarea name="contenu"></textarea>
    </p>
    <p>
        <input type="submit" value="Envoyer" name="Envoyer">
    </p>

</form>
<div>
    <div >
        <ul>
            <c:forEach items="${ result.getGestion().getListBillets() }" var="billet">
                <li> <a href=<c:out value="/v2/groupes/${result.getName()}/billets/${billet.getId() }" />><c:out value="${ billet.getTitre() }" /> </a></li>
            </c:forEach>
        </ul>
    </div>
    <c:forEach items="${ result.getGestion().getListBillets() }" var="billet">


    <div style="border:solid blue; margin:3%">
        <h1 id=<c:out value="${ billet.getTitre() }" /> >Titre Billet: <c:out value="${ billet.getTitre() }" /></h1>
        <h2>Auteur : <c:out value="${ billet.getAuteur() }" /></h2>
        <p>
            Contenu : <c:out value="${ billet.getContenu() }" /><br>
            Commentaires :
            <c:forEach items="${ billet.getCommentaires() }" var="commentaire">
                <div style="border:solid red; margin:3%">
                    <h2>Auteur : <c:out value="${ commentaire.getAuteur() }" /></h2>
                    <p>
                        Contenu : <c:out value="${ commentaire.getContenu() }" /><br>
                    </p>
                </div>
            </c:forEach>
        </p>


        <form method="post" action="${ HelperLink.groupe }/${result.getName()}/billets/${billet.getId()}/commentaires">
            <input type="text" name ="commentaire" placeholder="commentaire" >
            <input type="text" name ="idCommentaire" style="display:none" value="${ billet.getId() }" >
            <button type="submit" name="soumettreCommentaire">Soumettre Commentaire</button>
        </form>
    </div>
    </c:forEach>
</div>

<%@ include file="menu.html" %>

</body>
</html>
