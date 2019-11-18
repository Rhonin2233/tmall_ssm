
<%@ page contentType="text/html;charset=UTF-8" language="java"
		 pageEncoding="utf-8" isELIgnored="false" %>
<title>模仿天猫官网-${c.name}</title>	
<div id="category">
	<div class="categoryPageDiv">
		<img class="categoryPictureInProductPage" src="${pageContext.request.contextPath}/img/category/${c.id}.jpg">
		<%@include file="sortBar.jsp"%>
		<%@include file="productsByCategory.jsp"%>
	</div>

</div>