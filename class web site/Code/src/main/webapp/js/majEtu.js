HttpRequest = new XMLHttpRequest();

const sleep = (milliseconds) => {
  return new Promise(resolve => setTimeout(resolve, milliseconds))
}

value = document.getElementById('CoursId');
parameter = "identifiant=" + value.getAttribute("name");


HttpRequest.onreadystatechange = function() {

    if (HttpRequest.readyState===4) {
        document.getElementById('colonne1').innerHTML="";
        document.getElementById('colonne2').innerHTML="";
        var etudiants = JSON.parse(HttpRequest.responseText);
        var size = etudiants.length;
        for (var i = 0; i<size;i++) {
            if (i%2 === 0) {
                var ul = document.getElementById('colonne1');
            } else {
                var ul = document.getElementById('colonne2');
            }
            var li = document.createElement('li');
            if (etudiants[i][1] === true) {
                li.className='list-group-item list-group-item-success';
                li.innerHTML = etudiants[i][4] + "      "+ etudiants[i][2] + "      " + etudiants[i][3] + "<button id = \"" + etudiants[i][4] + "\" class=\"badge\" onclick = \"present('" + etudiants[i][4] +  "','" + etudiants[i][0] +"',false)\">présent" + "</button>";
            } else {
                li.className='list-group-item list-group-item-danger';
                li.innerHTML = etudiants[i][4] + "      "+ etudiants[i][2] + "      " + etudiants[i][3] + "<button id = \"" + etudiants[i][4] + "\" class=\"badge\" onclick = \"present('" + etudiants[i][4] +  "','" + etudiants[i][0] +"',true)\">absent" + "</button>";
            }
            ul.appendChild(li);
        }
        sleep(2000).then(() => {

        HttpRequest.abort();
        HttpRequest.open("GET","ReturnEtu?" + parameter,true);
        HttpRequest.send();
        })
    }
}
HttpRequest.open("GET","ReturnEtu?" + parameter,true);
HttpRequest.send();



var present = function(idStudent, idLesson,presence) {
    let HttpRequestUpdate = new XMLHttpRequest();
    HttpRequestUpdate.open("POST","ReturnEtu",true);
    HttpRequestUpdate.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    HttpRequestUpdate.send('idStudent=' + idStudent + '&idLesson=' + idLesson + '&presence=' + presence);
    if (HttpRequestUpdate.readyState === 4) {
        HttpRequest.open("GET","ReturnEtu",true);
        HttpRequest.send();
    }
}











