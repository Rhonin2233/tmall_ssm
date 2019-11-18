
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/admin/adminHeader.jsp">
	<jsp:param name="pageTitle" value="用户管理"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
</script>
<div class="workingArea">
	<h1 class="label label-info" >用户管理</h1>

	<br>
	<br>

	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
			<tr class="success">
				<th>ID</th>
				<th>用户名称</th>
				<th>删除</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${u}" var="u">
				<tr>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td ><a  href="deleteUser?id=${u.id}"  ><span class="   glyphicon glyphicon-trash" ></span></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>


</div>

<%@include file="../include/admin/adminFooter.jsp"%>
