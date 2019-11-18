<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>

<div id="carousel-of-product" class="carousel-of-product carousel slide1" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-of-product" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-of-product" data-slide-to="1"></li>
        <li data-target="#carousel-of-product" data-slide-to="2"></li>
        <li data-target="#carousel-of-product" data-slide-to="3"></li>
        <li data-target="#carousel-of-product" data-slide-to="4"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox" style="margin-left: 150px">
        <div class="item active">
            <a href="forecategory?category_id=2"><img class="carousel carouselImage"
                                                      src="${pageContext.request.contextPath}/img/lunbo/1.jpg"></a>
        </div>
        <div class="item">
            <a href="forecategory?category_id=3"><img class="carousel carouselImage"
                                                      src="${pageContext.request.contextPath}/img/lunbo/2.jpg"></a>
        </div>
        <div class="item">
            <a href="forecategory?category_id=1"><img class="carousel carouselImage"
                                                      src="${pageContext.request.contextPath}/img/lunbo/3.jpg"></a>
        </div>

        <div class="item">
            <a href="forecategory?category_id=7"><img class="carousel carouselImage"
                                                      src="${pageContext.request.contextPath}/img/lunbo/4.jpg"></a>
        </div>

        <div class="item">
            <a href="forecategory?category_id=9"><img class="carousel carouselImage"
                                                      src="${pageContext.request.contextPath}/img/lunbo/5.jpg"></a>
        </div>
    </div>

    <!-- Controls -->
    <!--   <a class="left carousel-control" href="#carousel-of-product" role="button" data-slide="prev"> -->
    <!--     <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> -->

    <!--   </a> -->
    <!--   <a class="right carousel-control" href="#carousel-of-product" role="button" data-slide="next"> -->
    <!--     <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> -->

    <!--   </a> -->

</div>	