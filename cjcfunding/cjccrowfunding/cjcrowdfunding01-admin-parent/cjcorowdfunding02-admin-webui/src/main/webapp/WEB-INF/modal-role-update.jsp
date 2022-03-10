<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/30
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<div id="roleUpdateModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加角色</h4>
            </div>
            <div class="modal-body">
                <from>
                    <div class="form-group has-success has-feedback">
                        <input type="text" value="${role.id}" class="form-control" id="roleNameInput" name="roleName"  autofocus>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                </from>
            </div>
            <div class="modal-footer">
                <button id="saveModalButton" type="button" class="btn btn-primary">修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->