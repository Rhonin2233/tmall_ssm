<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>

<div class="categoryMenu">
    <c:forEach items="${cs}" var="c">
        <div cid="${c.id}" class="eachCategory">
            <span class="glyphicon glyphicon-link"></span>
            <a href="forecategory?category_id=${c.id}">
                    ${c.name}
            </a>
        </div>
    </c:forEach>
</div>