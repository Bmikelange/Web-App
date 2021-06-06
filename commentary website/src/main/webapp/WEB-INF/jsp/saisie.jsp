<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Saisie d'un billet</title>
</head>

<body>
    <h1>Saisie d'un billet</h1>
    <form method="post" action="AjoutBillet">
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
    <%@ include file="menu.html" %>
</body>

</html>