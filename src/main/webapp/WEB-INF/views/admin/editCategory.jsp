<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<jsp:include page="../include/admin/adminHeader.jsp">
    <jsp:param name="pageTitle" value="编辑分类"/>
</jsp:include>
<%--<%@include file="../include/admin/adminHeader.jsp"%>--%>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {

        $("#editForm").submit(function () {
            if (!checkEmpty("name", "分类名称"))
                return false;
            // if(!checkEmpty("categoryPic","分类图片"))
            //     return false;
            return true;
        });
    });
</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="listCategory">所有分类</a></li>
        <li class="active"><a href="javascript:history.back(-1)">返回</a></li>
    </ol>
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑分类</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="updateCategory" enctype="multipart/form-data">
                <table class="editTable">
                    <tbody>
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" value="${category.name}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类圖片</td>
                        <td>
                            <input id="categoryPic" type="file" accept="image/*" name="image">
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${category.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>