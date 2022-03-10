<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/16
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <title>Title</title>
    <script type="text/javascript">
        $(function () {
            $("#getAllBtn").click(function () {

                $.ajax({
                    "url": "getAdminByAjax.json",
                    "type": "post",
                    "dataType": "text",
                    "success": function (response) {
                        console.info(response);
                    },
                    "error": function (response) {
                        console.info(response);
                    }
                })

            });
        });

        $(function () {
            $("#getAdminById").click(function () {
                var id = {
                    "id": "1"
                };
                var idJson = JSON.stringify(id);
                $.ajax({
                    "url": "getAdminById.json",
                    "type": "post",
                    "data": idJson,
                    "contentType": "application/json;charset=UTF-8",
                    "dataType": "json",
                    "success": function (response) {
                        console.info(response)
                    },
                    "error": function (response) {
                        console.info(response)
                    }
                })
            })
        });

        $(function () {
            $("#studentBtn").click(function () {

                var student = {
                    "id": "1",
                    "name": "cjc",
                    "address": {
                        "province": "gd",
                        "city": "zj",
                    }
                }
                var studentJsonString = JSON.stringify(student);
               $.ajax({
                   "url":"student.json",
                   "type":"post",
                   "data":studentJsonString,
                   "contentType":"application/json;charset=UTF-8",
                   "dataType":"json",
                   "success":function (response){
                       console.info(response)
                   },
                   "error":function (response){
                       console.info(response)
                   }

               })
            });
        });


        $(function () {
            $("#resultBtn").click(function () {

                var student = {
                    "id": "1",
                    "name": "cjc",
                    "address": {
                        "province": "gd",
                        "city": "zj",
                    }
                }
                var studentJsonString = JSON.stringify(student);
                $.ajax({
                    "url":"testResultEntity.json",
                    "type":"post",
                    "data":studentJsonString,
                    "contentType":"application/json;charset=UTF-8",
                    "dataType":"json",
                    "success":function (response){
                        console.info(response)
                    },
                    "error":function (response){
                        console.info(response)
                    }

                })
            });
        });

        $(function (){
            $("#layerBtn").click(function (){
                layer
            })
        })

    </script>

</head>
<body>
<button id="getAllBtn">getAll</button>
<button id="getAdminById">getAdminById</button>
<button id="studentBtn">studentBtn</button>
<button id="resultBtn">resultBtn</button>
<button id="layerBtn">layerBtn</button>
</body>
</html>
