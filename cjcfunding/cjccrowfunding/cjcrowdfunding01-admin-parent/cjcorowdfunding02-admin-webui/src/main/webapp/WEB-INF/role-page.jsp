<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/27
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<%@include file="include-header.jsp"%>
<script src="jquery/jquery.pagination.js"></script>
<script src="funding/my-role.js"></script>
<script type="text/javascript">
    $(function (){
        // 1.为分页操作准备初始化数据
        window.pageNum=1;
        window.pageSize=5;
        window.keyword="";

        // 2.调用执行分页的函数，显示分页
        generatePage();



    })


    // 关键词查询
    $(function(){

        $("#searchButton").click(function(){
            // 给keyword赋值
            window.keyword = $("#keywordInput").val();

            // 调用生成页面方法
            generatePage();
        })

    });


    $(function (){

        // 点击新增显示模态框
        $("#insertButton").click(function (){
            $("#roleAddModal").modal("show");
        });

        // 判断输入的角色名是否存在


        // 点击新增模态框添加角色
        $("#saveModalButton").click(function (){

            // 获取模态框中roleName
            var roleName = $.trim($("#roleAddModal [name=roleName]").val());

            // ajax
            $.ajax({
                "url":"role/do/save.json",
                "type":"post",
                "data": {
                    "roleName": roleName,   // 发送普通数据，不需要写contenType
                },
                "success":function (response){
                    var message=response.message;
                    layer.msg(message);
                },
                "error":function (response){
                    var message=response.message;
                    layer.msg(message);
                }

            });

            // 关闭模态框
            $("#roleAddModal").modal("hide");

            // 清理模态框
            $("#roleAddModal [name=roleName]").val("");

            // 重新加载分页到最后一页
            window.pageNum=999999;
            generatePage();

        });


        // 点击模态框中的确认按钮进行删除角色
        $("#removeModalButton").click(function (){

            var requestBody = JSON.stringify(window.roleIdList);

            $.ajax({
                "url":"role/remove/by/role/id/array.json",
                "type":"post",
                "data":requestBody,
                "contentType":"application/json;charset=UTF-8",
                "dataType":"json",
                "success":function (response){

                    layer.msg("删除成功");
                },
                "error":function (response){

                    layer.msg("删除失败");
                }

            });

            // 关闭模态框
            $("#roleAddModal").modal("hide");

            // 重新加载分页当前页

            generatePage();


        });

        // 单条删除
        $("#rolePageBody").on("click",".removeBtn",function (){

            // 获取角色名称
            var name = $(this).parent().prev().text();


            var roleArray  = [{
                roleName:name,
                roleId:this.id
            }];

            // 打开模态框
            showConfirmModal(roleArray);
        });

    });









</script>

<body>

<%@include file="include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form  class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchButton" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button id="insertButton" type="button" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleTableBody">
                            <tr>
                                <td>1</td>
                                <td><input type="checkbox"></td>
                                <td>PM - 项目经理</td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                    <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                    <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="modal-role-add.jsp"%>
<%@include file="modal-role-update.jsp"%>
<%@include file="modal-role-delete-confirm.jsp"%>
</body>
</html>
