
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/admin/adminHeader.jsp">
	<jsp:param name="pageTitle" value="编辑产品属性值"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function() {
        $("input.pvValue").change(function(){
            var value = $(this).val();
            var page = "${pageContext.request.contextPath}/admin/updatePropertyValue";
            var pvid = $(this).attr("pvid");
            var parentSpan = $(this).parent("span");
            parentSpan.css("border","1px solid yellow");
            $.post(
                page,
                {"value":value,"id":pvid},
                function(result){
                    if(result)
                        parentSpan.css("border","1px solid green");
                    else
                        parentSpan.css("border","1px solid red");
                }
            );
        });
    });
</script>

<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="listCategory">所有分类</a></li>
		<li><a href="listProduct?category_id=${c.id}">${c.name}</a></li>
		<li class="active">${p.name}</li>
		<li class="active"><a href="javascript:history.back(-1)">返回</a></li>
	</ol>

	<div class="editPVDiv">
		<c:forEach items="${pv}" var="pv">
			<div class="eachPV">
				<span class="pvName" >${pv.property.name}</span>
				<span class="pvValue"><input class="pvValue" pvid="${pv.id}" type="text" value="${pv.value}"></span>
			</div>
		</c:forEach>
		<div style="clear:both"></div>
	</div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>