<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/23
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<%@include file="include-header.jsp" %>
<script type="text/javascript">
    function judgeLoinAcctExist() {
        // 1.获取输入框内容
        var input = document.getElementById("loginAcctInput").value;

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
        })
    }

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

    //提交表单时，判断所有的输入框是否为null
    function judgeNull() {

        // 设定一个flag 只有满足以下所有的条件，才为true，才跳转
        var flag = true;
        // 反(登录账号不为空 且 登录账号没有已存在)
        if (!(judgeNull0("loginAcctInput", "登录账号为空") || judgeLoinAcctExist())) {
            flag = false;
        }
        // 用户名为空
        if (!(judgeNull0("userNameInput", "用户名为空"))) {
            flag = false;
        }
        // 密码不为空
        if (!(judgeNull0("userPswdInput", "密码为空"))) {
            flag = false;
        }
        // 邮箱不为空 且 格式正确
        if (!(judgeNull0("EmailInput", "邮箱有空") || judgeEmailPatten())) {
            flag=false;
        }
        return flag;


    }

    //被judgeNull调用一一判断输入框是否为null
    function judgeNull0(id, message) {
        var idString = "#"+id;
        var content = $().val(idString);
        if (content === "") {
            showMessage(id, message);
            console.info(content)
            // 为空
            return false;
        }
        // 不为空
        return true;

    }

    $(function (){
        $("#submitBtn").on("click",function (){
            return judgeNull();
        });
    })


    //用来显示信息
    function showMessage(id, message) {
        var p = "#"+id;
        var exceptionP = $(p);
        exceptionP.text("");
        exceptionP.text(message);
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
                <li class="active">新增</li>

            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form action="admin/do/save.html" method="post" role="form" >
                        <div class="form-group">
                            <label for="exampleInputPassword1">登陆账号</label>
                            <div id="exceptionDiv"><p ID="exceptionMessageP" class="help-block label label-warning"></p>
                            </div>
                            <input id="loginAcctInput" onchange="judgeLoinAcctExist()" name="loginAcct" type="text"
                                   class="form-control" id="exampleInputPassword1"
                                   placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户名称</label>
                            <input id="userNameInput" name="userName" type="text" class="form-control"
                                   id="exampleInputPassword2"
                                   placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">密码</label>
                            <input id="userPswdInput" name="userPswd" type="text" class="form-control"
                                   placeholder="请输入用户名称">
                        </div>

                        <div class="form-group">
                            <label for="exampleInputPassword1">邮箱地址</label>
                            <input oninput="judgeEmailPatten()" name="email" type="email" class="form-control" id="EmailInput"
                                   placeholder="请输入邮箱地址">
                            <p id="emailMassage" class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
                                xxxx@xxxx.com</p>
                        </div>
                        <button id="submitBtn" type="submit" class="btn btn-success"><i
                                class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
