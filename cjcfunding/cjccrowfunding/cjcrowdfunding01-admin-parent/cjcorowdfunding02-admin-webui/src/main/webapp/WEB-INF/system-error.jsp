<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/20
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<head>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <style>
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container" style="text-align: center">


        <h2 class="form-signin-heading" ><i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息</h2>
        <h3>${requestScope.exception.message}</h3>
        <button id="backBtn" style="width: 150px;margin: 50px auto 0px auto;"  class="btn btn-lg btn-success btn-block">后退</button>
</div>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript"> $(function () {
    $("#backBtn").click(function () {
        // 调用 back()方法类似于点击浏览器的后退按钮
        window.history.back();
    });
});
</script>
</body>
</html>

