<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/25
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<%@include file="include-header.jsp" %>
<script type="text/javascript">
    function judgeEmailPatten() {
        var obj = document.getElementById("EmailInput");
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
// 获取该对象
        var emailMassage = $("#emailMassage");
// 清空内容
        emailMassage.text("");
        if (obj.value === "") { //输入不能为空
// 显示信息
            emailMassage.text("输入不能为空!")
            return false;
        } else if (!reg.test(obj.value)) { //正则验证不通过，格式不对
            emailMassage.text("email格式不正确");
            return false;
        } else {
            emailMassage.text("该email可用");
            return true;
        }
    }

    function judgeLoinAcctExist() {
        // 1.获取输入框内容
        var input = document.getElementById("loginAcctInput").value;
        var loginAcct="${admin.loginAcct}";
        console.info("input: "+input);
        console.info("loginAcct: "+loginAcct);
        console.info(loginAcct==input)
        if(!(loginAcct==input)){
            // 2.ajax传入后台
            $.ajax({
                url: "admin/do/judgeLoginAcctExist.json",
                type: "post",
                data: input,
                contentType: "application/json;charset=UTF-8",
                dataType: "json",
                success: function (response) {
                    // 2.1. 成功
                    var message = response.message;
                    var exceptionP = $("#exceptionMessageP");
                    exceptionP.text("");
                    exceptionP.append(message);
                    return true;
                },
                error: function (response) {
                    // 2.2. 失败显示异常信息
                    var message = response.message;
                    var exceptionP = $("#exceptionMessageP");
                    exceptionP.text("");
                    exceptionP.append(message);
                    return false;
                }
            });
        }else{
            // 如果输入的和loginAcct相同，那么清除消息
            var exceptionP = $("#exceptionMessageP");
            exceptionP.text("");
        }




    }
</script>

<body>

<%@include file="include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/do/edit.html" method="post" role="form">
                        <input type="hidden" name="id" value="${admin.id}">
                        <div class="form-group">
                            <label for="loginAcctInput">登陆账号</label>
                            <div id="exceptionDiv"><p ID="exceptionMessageP" class="help-block label label-warning"></p>
                            </div>
                            <input id="loginAcctInput" name="loginAcct" oninput="judgeLoinAcctExist()" type="text" class="form-control"
                                   id="loginAcctInpu


                                   t"
                                   value="${admin.loginAcct}">
                        </div>
                        <div class="form-group">
                            <label for="userName">用户名称</label>
                            <input id="userName" name="userName" type="text" class="form-control"
                                   id="exampleInputPassword2"
                                   value="${admin.userName}">
                        </div>
                        <div class="form-group">
                            <label for="EmailInput">邮箱地址</label>
                            <div><p ID="emailMassage" class="help-block label label-warning"></p></div>
                            <input id="EmailInput" oninput="judgeEmailPatten()" name="email"
                                   type="email" class="form-control"
                                   id="EmailInput"
                                   placeholder="请输入邮箱地址" value="${admin.email}">
                        </div>
                        <input type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改
                        </input>
                        <input type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </input>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
