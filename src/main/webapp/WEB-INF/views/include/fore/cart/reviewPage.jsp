<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<form method="post" action="checkdoreview">
    <c:forEach items="${ps}" var="p">
        <div class="reviewDiv">
            <div class="reviewProductInfoDiv">
                <div class="reviewProductInfoImg"><img width="100px" height="100px"
                                                       src="${pageContext.request.contextPath}/img/productSingle/${p.firstImage.id}.jpg">
                </div>
                <div class="reviewProductInfoRightDiv">
                    <div class="reviewProductInfoRightText">
                            ${p.name}
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>
            <div class="reviewStasticsDiv">
                <div class="reviewStasticsLeft">
                    <div class="reviewStasticsLeftTop"></div>
                    <div class="reviewStasticsLeftContent">评价</div>
                    <div class="reviewStasticsLeftFoot"></div>
                </div>
                <div class="reviewStasticsRight">
                    <div class="reviewStasticsRightEmpty"></div>
                    <div class="reviewStasticsFoot"></div>
                </div>
            </div>

            <div class="makeReviewDiv">
                <input type="hidden" name="product_id" value="${p.id}">
                <textarea name="content" style="width: 90%;height: 100px; margin-top: 10px"></textarea>
            </div>


        </div>
    </c:forEach>
    <div class="makeReviewButtonDiv">
        <button type="submit">提交评价</button>
    </div>
</form>
