<%@ page contentType="text/html; charset=UTF-8" %>
<%
    //String choix = request.getParameter("choix");
    session = request.getSession();
    //session.setAttribute("choix.session",choix);
%>

<header>
    <div class="font">
        <div class="container-fluid">
            <div style="float : left;">
                <img src="../img/icone.png" alt="" class="img-responsive">
            </div>
            <div class="text-right">
                <a style="color:white;" href="/value/Deconnexion">deconnexion<span class="glyphicon glyphicon-log-out white"></span></a>
            </div>
            <nav class="navbar navbar-inverse" style = "margin-top: 60px;">
                <ul class="nav navbar-nav">
                    <li><a href="etudiant.jsp">page étudiant</a></li><!--
                    --><li><a href="etudiant.jsp">mes cours</a></li><!--
                    --><li><a href="/value/etudiant/ReturnCoursEtudiant">mes présences</a></li><!--
                    --><li><a href="scanne_QRCode.jsp">scanner QRCode</a>
                </ul>
            </nav>
        </div>
    </div>
</header>
