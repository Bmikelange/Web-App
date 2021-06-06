<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>
	<link rel="stylesheet" href="../css/etudiant.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/qrcode.js"></script>
    <title>Title</title>
</head>
	<%@ include file="header_prof.jsp" %>
	<body style="background-color : #35373a;">
		<div name = <%= request.getParameter("identifiant") %> id="CoursId"></div>
		<div style="width: 60%;float: left;">
			<div style="float: left;width: 45%;">
				<ul class="list-group" id="colonne1">
                </ul>
			</div>
			<div style="padding-left: 50%;padding-right: 5%;">
			<ul class="list-group" id="colonne2">
             </ul>
			</div>
		</div>
		<div>
			<div class="container-fluid">
				<p>
					<div id="qrcode"></div>
					<script type="text/javascript">
						var qrcode = new QRCode(document.getElementById("qrcode"), {
						<% String identifiant = '"'+request.getParameter("identifiant")+'"'; %>
							text: <%= identifiant %>,
							width: 500,
							height: 500,
							colorDark : "#000000",
							colorLight : "#ffffff",
							correctLevel : QRCode.CorrectLevel.H
						});
						document.getElementsByTagName("img")[1].setAttribute("class","img-responsive");
						document.getElementsByTagName("img")[1].setAttribute("style","border:10px solid #ffffff;");
					</script>
				</p>
			</div>
		</div>
		<div style = "height: 50;">
			<p style="color : #35373a;">.</p>
		</div>
	</body>
	<%@ include file="../etudiant/footer_etu.html" %>
	<script src="../js/majEtu.js"></script>
</html>