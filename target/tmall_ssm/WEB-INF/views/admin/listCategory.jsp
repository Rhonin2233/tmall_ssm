<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/admin/adminHeader.jsp">
    <jsp:param name="pageTitle" value="分类管理"/>
</jsp:include>
<%--<%@include file="../include/admin/adminHeader.jsp"%>--%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){

        $("#addForm").submit(function(){
            if(!checkEmpty("name","分类名称"))
                return false;
            if(!checkEmpty("categoryPic","分类图片"))
                return false;
            return true;
        });
        //图片点击缩放
        $(".min").click(function () {
            $(this).toggleClass("min");
            $(this).toggleClass("max");
        });
    });
$(function () {
    $("a.category").click(function () {
        var category_id=$(this).attr("cid");
        var url=$(this).attr("href");
        var page="checkProduct";
        $.post(
            page,
            {"category_id":category_id},
            function (result) {
                if (result){
                    alert("请先清空该分类下产品");
                } else{
                    var confirmDelete=confirm("确认删除");
                    if (confirmDelete) {
                        location.href=url
                    }
                }
            }
        );
        return false;
    });
});
</script>
<div class="workingArea">
    <h1 class="label label-info" >分类管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table  id="categoryTable" class="table table-striped table-bordered table-hover  table-condensed">
            <thead >
            <tr class="success">
                <th class="center">ID</th>
                <th class="center">图片</th>
                <th class="center">分类名称</th>
                <th class="center">属性管理</th>
                <th class="center">产品管理</th>
                <th class="center">编辑</th>
                <th class="center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="c">

                <tr class="center">
                    <td style="vertical-align: middle">${c.id}</td>
                    <td style="vertical-align: middle"><img   class="min" height="40px" src="${pageContext.request.contextPath}/img/category/${c.id}.jpg"></td>
                    <td style="vertical-align: middle">${c.name}</td>
                    <td style="vertical-align: middle"><a href="listProperty?category_id=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td style="vertical-align: middle"><a href="listProduct?category_id=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <td style="vertical-align: middle"><a href="editCategory?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td style="vertical-align: middle"><a class="category"  href="deleteCategory?id=${c.id}" cid="${c.id}" ><span class="   glyphicon glyphicon-trash" ></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="addCategory" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类圖片</td>
                        <td>
                            <input id="categoryPic" accept="image/*" type="file" name="image" />
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>