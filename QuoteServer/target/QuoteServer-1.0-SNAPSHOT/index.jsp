<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is the server for the quote on hallur.dk!</h1>
        <form id="test" enctype='multipart/form-data' onsubmit="return false">
            <input type= "file" name="file" />
            <input type="submit"/>
        </Form>
        <input id="pass" type="text"/>
        <button id="passbtn">zz</button>
        <img id="here"/>
        <script>
            fetch("api/upload/url", {method: "get"})
                    .then(function (response) {
                        return response.json();
                    })
                    .then(function (json) {
                        document.getElementById("here").src = json;
                        document.getElementById("here").alt = json;
                    })
                    .catch(function (error) {
                        alert(error);
                    });


            document.getElementById("passbtn").onclick = function () {
                var pass = {password: document.getElementById("pass").value};
                console.log(JSON.stringify(pass));
                fetch("api/quotes/password", {method: 'PUT',
                    headers: {
                        'Accept': 'application/json, text/plain, */*',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(pass)})
                        .then(function (element) {
                            return element.json();
                        }).then(function (json) {
                    alert(json);
                });
            };
            document.getElementById("test").onsubmit = function (e) {

                var input = document.querySelector('input[type="file"]');
                var passWord = prompt("you need a password for this");
                alert("/images/" + input.files[0].name);
                var data = new FormData();
                data.append('file', input.files[0]);
                data.append('password', passWord);
                fetch('api/upload/file', {
                    method: 'POST',
                    body: data,

                }).then(function(data){
                    return data.json();
                }).then(function(json){
                    alert(json);
                });
                e.preventDefault();
            };
        </script>
    </body>
</html>
