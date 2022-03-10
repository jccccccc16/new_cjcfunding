<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/20
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<%@include file="include-header.jsp" %>
<link rel="style sheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
    $(function () {
        iniPagination();
    });

    function iniPagination() {
        // 总记录数
        var totalRecord =${pageInfo.total};

        // 生成导航条需要设置的属性
        var properties = {
            num_edge_entries: 3,// 边缘页数
            num_display_entries: 4, // 主体页数
            callback: pageSelectCallback, // 用户点击页面进行翻页时的回调函数，重新初始化？
            items_per_page:${pageInfo.pageSize},
            current_page: ${pageInfo.pageNum-1}, // pagePagination Cong ling kai shi
            prev_text: "上一页",
            next_text: "下一页",
        };


        // 生成页码导航条
        $("#Pagination").pagination(totalRecord, properties);
    };

    // pageIndex时pagination传给我们的那个从零开始的页码。
    // 点击页码超链接时，该函数定义 点击超链接时，如何进行跳转，pageIndex为当前的页码
    function pageSelectCallback(pageIndex, jQuery) {
        // 得到我们传回后台的pageNum
        var pageNum = pageIndex + 1;
        <c:if test="${empty param.keyword}">
        window.location.href = "admin/get/page.html?pageNum=" + pageNum;
        </c:if>
        <c:if test="${ !empty param.keyword}">
        window.location.href = "admin/get/page.html?pageNum=" + pageNum + "&keyword=" +${param.keyword};
        </c:if>
        return false;
    }


    function deleteAttention(AdminId){

        var id = AdminId;
        var currentAdminId = ${sessionScope.loginAdmin.id};
        if(id==currentAdminId){
            alert("不能删除自己")
            return false;
        }
        if(confirm("确定删除吗")){
            return true;
        }else{
            return false;
        }
    }



</script>
<body>

<%@include file="include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/get/page.html" method="post" class="form-inline" role="form"
                          style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>

                                <input name="keyword" value="${!empty param.keyword?param.keyword:''}"
                                       class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="submit" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='admin/to/add/page.html'"><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--                            如果数据集为空--%>
                            <c:if test="${empty requestScope.pageInfo.list}">
                                <tr>
                                    <td colspan="6" align="center">抱歉，没有数据</td>
                                </tr>
                            </c:if>
                            <%--                            如果数据集不为空--%>
                            <c:if test="${!empty requestScope.pageInfo.list}">
                                <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct}</td>
                                        <td>${admin.userName}</td>
                                        <td>${admin.email}</td>
                                        <td>
                                            <a href="assign/to/assign/role/page.html?adminId=${admin.id}&pageNum=${pageInfo.pageNum}&keyword=${param.keyword}" type="button" class="btn btn-success btn-xs"><i
                                                    class=" glyphicon glyphicon-check"></i></a>
                                            <a href="admin/to/edit/page.html?id=${admin.id}" class="btn btn-primary btn-xs"><i
                                                    class=" glyphicon glyphicon-pencil"></i></a>
                                            <a id="deleteA"  onclick="deleteAttention(${admin.id})"
                                               href="admin/delect/${admin.id}/${pageInfo.pageNum}/${param.keyword}.html"
                                               class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">

                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                    <div id="Searchresult" class="pagination"></div>


                                    <%--                                    <ul class="pagination">--%>
                                    <%--                                        <li class="disabled"><a href="#">上一页</a></li>--%>
                                    <%--                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>--%>
                                    <%--                                        <li><a href="#">2</a></li>--%>
                                    <%--                                        <li><a href="#">3</a></li>--%>
                                    <%--                                        <li><a href="#">4</a></li>--%>
                                    <%--                                        <li><a href="#">5</a></li>--%>
                                    <%--                                        <li><a href="#">下一页</a></li>--%>
                                    <%--                                    </ul>--%>
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

</body>
</html>
