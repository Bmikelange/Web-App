<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<meta charset="UTF-8">


<html lang="en">

  <head>

      <title>Lecture de QR code</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
      <link rel="stylesheet" href="../css/etudiant.css">
      <script src="../js/jquery.min.js"></script>

      <script src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
  </head>

  <%@ include file="header_etu.jsp" %>

  <body style="background-color : #35373a;">

    <div>  
        <div class="text-center">
            <h1 style="color : blue;"> SCANNEZ VOTRE QR CODE </h1>
        </div>  
        <form action="VerifQR" name="FormReturn" method="post" style="display:none;"><input id="return" type="text" name="contenu" value=""/></form>
        <div class="embed-responsive embed-responsive-16by9"> 
            <video id="visio" class="embed-responsive-item"></video>
        </div>
    </div>
    <script type="text/javascript">

      let scanner = new Instascan.Scanner({ video: document.getElementById('visio') });

      scanner.addListener('scan', function (contenu) { 
        document.getElementById("return").value=contenu;
        document.forms['FormReturn'].submit();

      });

      Instascan.Camera.getCameras().then(function (cameras) {

       if (cameras.length > 0) {

          scanner.start(cameras[0]);

        } else {

          console.error('No cameras found.');

        }

      }).catch(function (e) {

        console.error(e);

      });

    </script>

   

  </body>
  <%@ include file="footer_etu.html" %>
</html>
