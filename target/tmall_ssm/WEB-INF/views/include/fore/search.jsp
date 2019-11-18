
<%@ page contentType="text/html;charset=UTF-8" language="java"
		 pageEncoding="utf-8" isELIgnored="false" %>
<div class="searchOutDiv">
<a href="home">
	<img id="logo" src="${pageContext.request.contextPath}/img/site/logo.gif" class="logo">
</a>
<form action="foresearch" method="post" >
	<div class="searchDiv">
		<input name="keyword" type="text" value="${param.keyword}" placeholder="iphone11  ps4 ">
		<button  type="submit" class="searchButton">搜索</button>
		<div class="searchBelow">
			<c:forEach items="${cs}" var="c" varStatus="st">
				<c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="forecategory?category_id=${c.id}">
									${c.name}
							</a>
							<c:if test="${st.count!=8}">
								<span>|</span>
							</c:if>
						</span>
				</c:if>
			</c:forEach>
		</div>
	</div>
</form>
</div>