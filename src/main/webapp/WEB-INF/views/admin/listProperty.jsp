<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/admin/adminHeader.jsp">
    <jsp:param name="pageTitle" value="属性管理"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp" %>

<script>
    $(function () {

        $("#addForm").submit(function () {
            if (checkEmpty("name", "属性名称"))
                return true;
            return false;
        });
    });
</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="listCategory">所有分类</a></li>
        <li><a href="listProperty?category_id=${c.id}">${c.name}</a></li>
        <li class="active"><a href="javascript:history.back(-1)">返回</a></li>
    </ol>
    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th class="center">ID</th>
                <th class="center">属性名称</th>
                <th class="center">编辑</th>
                <th class="center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${properties}" var="p">

                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td><a href="editProperty?id=${p.id}"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="property"
                           href="deleteProperty?id=${p.id}&category_id=${c.id}"><span
                            class=" 	glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="addProperty">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="category_id" value="${c.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp" %>
