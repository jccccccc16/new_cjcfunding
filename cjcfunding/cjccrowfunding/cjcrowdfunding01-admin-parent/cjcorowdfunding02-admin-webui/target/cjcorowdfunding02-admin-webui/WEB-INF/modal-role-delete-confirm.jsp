<%--
  Created by IntelliJ IDEA.
  User: cjc
  Date: 2020/9/28
  Time: 21:04
  To change this template use File | Settings | File Templates.
  添加角色所用的模态框
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">
<div id="roleConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除角色</h4>
            </div>
            <div class="modal-body">
                <h4>请确认是否要删除下列角色</h4>
                <span id="roleNameSpan"></span>
            </div>
            <div class="modal-footer">
                <button id="removeModalButton" type="button" class="btn btn-primary">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->