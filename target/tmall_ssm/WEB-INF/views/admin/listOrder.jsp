
<%@ page language="java" contentType="text/html;charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../include/admin/adminHeader.jsp">
	<jsp:param name="pageTitle" value="订单管理"/>
</jsp:include>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });

</script>

<div class="workingArea">
	<h1 class="label label-info" >订单管理</h1>
	<br>
	<br>

	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover1  table-condensed">
			<thead>
			<tr class="success">
				<th>ID</th>
				<th>状态</th>
				<th>金额</th>
				<th width="100px">商品数量</th>
				<th width="100px">买家名称</th>
				<th>创建时间</th>
				<th>支付时间</th>
				<th>发货时间</th>
				<th>确认收货时间</th>
				<th width="120px">操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${o}" var="o">
				<tr>
					<td align="center">${o.id}</td>
					<td align="center">
						<c:if test="${o.status=='waitPayment'}">待付款</c:if>
						<c:if test="${o.status=='waitDelivery'}">待发货</c:if>
						<c:if test="${o.status=='waitReceipt'}">待收货</c:if>
						<c:if test="${o.status=='waitReview'}">待评价</c:if>
						<c:if test="${o.status=='finish'}">完成</c:if>
					</td>
					<td align="center">￥<fmt:formatNumber type="number" value="${o.totalPrice}" minFractionDigits="2"/></td>
					<td align="center">${o.totalNum}</td>
					<td align="center">${o.user.name}</td>

					<td align="center"><fmt:formatDate value="${o.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><fmt:formatDate value="${o.pay_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><fmt:formatDate value="${o.delivery_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center"><fmt:formatDate value="${o.confirm_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

					<td>
						<button class="orderPageCheckOrderItems btn btn-primary btn-xs" oid=${o.id} >查看详情</button>

						<c:if test="${o.status=='waitDelivery'}">
							<a href="OrderDelivery?id=${o.id}">
								<button class="btn btn-primary btn-xs">发货</button>
							</a>
						</c:if>
					</td>
				</tr>
				<tr class="orderPageOrderItemTR" style="display: none" oid=${o.id}>
					<td colspan="10" align="center">

						<div  class="orderPageOrderItem">
							<table width="800px" align="center" class="orderPageOrderItemTable">
								<c:forEach items="${o.orderItem}" var="oi">
									<tr>
										<td align="left">
											产品图片：<img width="40px" height="40px" src="${pageContext.request.contextPath}/img/productSingle/${oi.product.firstImage.id}.jpg">
										</td>

										<td>
											产品名称：<a href="foreproduct?pid=${oi.product.id}">
												<span>${oi.product.name}</span>
											</a>
										</td>
										<td align="right">

											<span class="text-muted">产品数量：${oi.number}个</span>
										</td>
										<td align="right">

											<span class="text-muted">单价：￥${oi.product.promotePrice}</span>
										</td>

									</tr>
								</c:forEach>

							</table>
						</div>

					</td>
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
