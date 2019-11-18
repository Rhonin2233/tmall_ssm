

<%@ page contentType="text/html;charset=UTF-8" language="java"
		 pageEncoding="utf-8" isELIgnored="false" %>
	
<div class="aliPayPageDiv">
	<div class="aliPayPageLogo">
		<img class="pull-left" src="${pageContext.request.contextPath}/img/site/simpleLogo.png">
		<div style="clear:both"></div>
	</div>
	
	<div>
		<span class="confirmMoneyText">扫一扫付款（元）</span>
		<span class="confirmMoney">
		￥<fmt:formatNumber type="number" value="${o.totalPrice}" minFractionDigits="2"/></span>
		
	</div>
	<div>
		<h1>我是二维码</h1>
<%--		<img class="aliPayImg" src="${pageContext.request.contextPath}/img/site/alipay2wei.png">--%>
	</div>

	
	<div>
		<a href="checkpayed?id=${o.id}&totalPrice=${o.totalPrice}"><button class="confirmPay">确认支付</button></a>
	</div>

</div>