<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../include/admin/adminHeader.jsp">
    <jsp:param name="pageTitle" value="产品管理"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp" %>

<script>
    $(function () {
        $("#addForm").submit(function () {
            if (!checkEmpty("name", "产品名称"))
                return false;
//          if (!checkEmpty("subTitle", "小标题"))
//              return false;
            if (!checkNumber("originalPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
        //图片点击缩放
        $(".min").click(function () {
            $(this).toggleClass("min");
            $(this).toggleClass("max");
        });
    });
</script>
<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="listCategory">所有分类</a></li>
        <li><a href="listProduct?category_id=${c.id}">${c.name}</a></li>
        <li class="active"><a href="javascript:history.back(-1)">返回</a></li>
    </ol>

    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th class="center">ID</th>
                <th class="center">图片</th>
                <th class="center">产品名称</th>
                <th class="center">产品小标题</th>
                <th width="53px" class="center">原价格</th>
                <th width="80px" class="center">优惠价格</th>
                <th width="80px" class="center">库存数量</th>
                <th width="80px" class="center">图片管理</th>
                <th width="80px" class="center">设置属性</th>
                <th width="42px" class="center">编辑</th>
                <th width="42px" class="center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="p">
                <tr>
                    <td style="vertical-align: middle;text-align: center">${p.id}</td>
                    <td style="vertical-align: middle;text-align: center">
                        <c:if test="${!empty p.firstImage}">
                            <img class="min" width="40px"
                                 src="${pageContext.request.contextPath}/img/productSingle/${p.firstImage.id}.jpg">
                        </c:if>
                    </td>
                    <td style="vertical-align: middle;text-align: center">${p.name}</td>
                    <td style="vertical-align: middle;text-align: center">${p.sub_title}</td>
                    <td style="vertical-align: middle;text-align: center">${p.price}</td>
                    <td style="vertical-align: middle;text-align: center">${p.promotePrice}</td>
                    <td style="vertical-align: middle;text-align: center">${p.stock}</td>
                    <td style="vertical-align: middle;text-align: center"><a href="listProductImage?product_id=${p.id}"><span
                            class="glyphicon glyphicon-picture"></span></a></td>
                    <td style="vertical-align: middle;text-align: center"><a
                            href="editPropertyValue?product_id=${p.id}"><span
                            class="glyphicon glyphicon-th-list"></span></a></td>

                    <td style="vertical-align: middle;text-align: center"><a href="editProduct?id=${p.id}"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td style="vertical-align: middle;text-align: center"><a deleteLink="true"
                                                                             href="deleteProduct?id=${p.id}&category_id=${c.id}"><span
                            class="     glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增产品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="addProduct">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="subTitle" name="sub_title" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="originalPrice" value="99.98" name="price" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="19.98" name="promotePrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" value="99" name="stock" type="text"
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