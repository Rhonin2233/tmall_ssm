<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>

<div class="searchOutDiv">
    <a href="home">
        <img id="simpleLogo" class="simpleLogo" src="${pageContext.request.contextPath}/img/site/simpleLogo.png">
    </a>

    <form action="foresearch" method="post">
        <div class="simpleSearchDiv pull-right">
            <input type="text" placeholder="iphone11  ps4" value="${param.keyword}" name="keyword">
            <button class="searchButton" type="submit">搜天猫</button>
            <div class="searchBelow">
                <c:forEach items="${cs}" var="c" varStatus="st">
                    <c:if test="${st.count>=2 and st.count<=4}">
					<span>
						<a href="forecategory?category_id=${c.id}">
                                ${c.name}
                        </a>
						<c:if test="${st.count!=4}">
                            <span>|</span>
                        </c:if>
					</span>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </form>
    <div style="clear:both"></div>
</div>