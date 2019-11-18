<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/admin/adminHeader.jsp">
    <jsp:param name="pageTitle" value="产品图片管理"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $(".addFormSingle").submit(function () {
            if (checkEmpty("filepathSingle", "图片文件")) {
                $("#filepathSingle").value("");
                return true;
            }
            return false;
        });
        $(".addFormDetail").submit(function () {
            if (checkEmpty("filepathDetail", "图片文件"))
                return true;
            return false;
        });
    });

</script>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="listCategory">所有分类</a></li>
        <li><a href="listProduct?category_id=${c.id}">${c.name}</a></li>
        <li class="active">${p.name}</li>
        <
        <li class="active"><a href="javascript:history.back(-1)">返回</a></li>
    </ol>

    <table class="addPictureTable" align="center">
        <tr>
            <td class="addPictureTableTD">
                <div>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 单个 </b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormSingle" action="addProductImage"
                                  enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 尺寸400X400 为佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathSingle" type="file" name="image"/>
                                        </td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="single"/>
                                            <input type="hidden" name="product_id" value="${p.id}"/>
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品单个图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ps}" var="pi">
                            <tr>
                                <td>${pi.id}</td>
                                <td>
                                    <a title="点击查看大图"
                                       href="${pageContext.request.contextPath}/img/productSingle/${pi.id}.jpg"><img
                                            height="50px"
                                            src="${pageContext.request.contextPath}/img/productSingle/${pi.id}.jpg"></a>
                                </td>
                                <td><a deleteLink="true"
                                       href="deleteProductImage?id=${pi.id}&type=${pi.type}&product_id${pi.product_id}"><span
                                        class=" 	glyphicon glyphicon-trash"></span></a></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </td>
            <td class="addPictureTableTD">
                <div>

                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormDetail" action="addProductImage"
                                  enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 宽度790 为佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathDetail" type="file" name="image"/>
                                        </td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="detail"/>
                                            <input type="hidden" name="product_id" value="${p.id}"/>
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品详情图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pd}" var="pi">
                            <tr>
                                <td>${pi.id}</td>
                                <td>
                                    <a title="点击查看大图"
                                       href="${pageContext.request.contextPath}/img/productDetail/${pi.id}.jpg"><img
                                            height="50px"
                                            src="${pageContext.request.contextPath}/img/productDetail/${pi.id}.jpg"></a>
                                </td>
                                <td><a deleteLink="true"
                                       href="deleteProductImage?id=${pi.id}&type=${pi.type}&product_id=${pi.product_id}"><span
                                        class=" 	glyphicon glyphicon-trash"></span></a></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
    </table>


</div>

<%@include file="../include/admin/adminFooter.jsp" %>
