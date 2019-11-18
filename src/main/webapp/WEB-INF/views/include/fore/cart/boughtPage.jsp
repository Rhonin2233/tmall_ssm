<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
    var deleteOrder = false;
    var deleteOrderid = 0;

    $(function () {
        $("a[orderStatus]").click(function () {
            var orderStatus = $(this).attr("orderStatus");
            if ('all' == orderStatus) {
                $("table[orderStatus]").show();
            } else {
                $("table[orderStatus]").hide();
                $("table[orderStatus=" + orderStatus + "]").show();
            }

            $("div.orderType div").removeClass("selectedOrderType");
            $(this).parent("div").addClass("selectedOrderType");
        });

        $("a.deleteOrderLink").click(function () {
            deleteOrderid = $(this).attr("oid");
            deleteOrder = false;
            $("#deleteConfirmModal").modal("show");
        });

        $("button.deleteConfirmButton").click(function () {
            deleteOrder = true;
            $("#deleteConfirmModal").modal('hide');
        });

        $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
            if (deleteOrder) {
                var page = "checkdeleteOrder";
                $.post(
                    page,
                    {"id": deleteOrderid},
                    function (result) {
                        if (result) {
                            $("table.orderListItemTable[oid=" + deleteOrderid + "]").hide();
                        } else {
                            location.href = "loginPage";
                        }
                    }
                );

            }
        })

        $(".ask2delivery").click(function () {
            var link = $(this).attr("link");
            $(this).hide();
            page = link;
            $.ajax({
                url: page,
                success: function (result) {
                    alert("卖家已秒发，刷新当前页面，即可进行确认收货")
                }
            });

        });
    });

</script>

<div class="boughtDiv">
    <div class="orderType">
        <div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
        <div><a orderStatus="waitPayment" href="#nowhere">待付款</a></div>
        <div><a orderStatus="waitDelivery" href="#nowhere">待发货</a></div>
        <div><a orderStatus="waitReceipt" href="#nowhere">待收货</a></div>
        <div><a orderStatus="waitReview" href="#nowhere" class="noRightborder">待评价</a></div>
        <div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
    </div>
    <div style="clear:both"></div>
    <div class="orderListTitle">
        <table class="orderListTitleTable">
            <tr>
                <td>宝贝</td>
                <td width="100px">单价</td>
                <td width="100px">数量</td>
                <td width="120px">实付款</td>
                <td width="100px">交易操作</td>
            </tr>
        </table>

    </div>

    <div class="orderListItem">
        <c:forEach items="${os}" var="o">
            <table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
                <tr class="orderListItemFirstTR">
                    <td colspan="2">
                        <b><fmt:formatDate value="${o.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></b>
                        <span>订单号: ${o.order_code}
					</span>
                    </td>
                    <td colspan="2"><img width="13px"
                                         src="${pageContext.request.contextPath}/img/site/orderItemTmall.png">天猫商场
                    </td>
                    <td colspan="1">
                        <a class="wangwanglink" href="#nowhere">
                            <div class="orderItemWangWangGif"></div>
                        </a>

                    </td>
                    <td class="orderItemDeleteTD">
                        <a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
                            <span class="orderListItemDelete glyphicon glyphicon-trash"></span>
                        </a>

                    </td>
                </tr>
                <c:forEach items="${o.orderItem}" var="oi" varStatus="st">
                    <tr class="orderItemProductInfoPartTR">
                        <td class="orderItemProductInfoPartTD"><img width="80" height="80"
                                                                    src="${pageContext.request.contextPath}/img/productSingle/${oi.product.firstImage.id}.jpg">
                        </td>
                        <td class="orderItemProductInfoPartTD">
                            <div class="orderListItemProductLinkOutDiv">
                                <a href="foreproduct?product_id=${oi.product.id}">${oi.product.name}</a>
                                <div class="orderListItemProductLinkInnerDiv">
                                    <img src="${pageContext.request.contextPath}/img/site/creditcard.png"
                                         title="支持信用卡支付">
                                    <img src="${pageContext.request.contextPath}/img/site/7day.png"
                                         title="消费者保障服务,承诺7天退货">
                                    <img src="${pageContext.request.contextPath}/img/site/promise.png"
                                         title="消费者保障服务,承诺如实描述">
                                </div>
                            </div>
                        </td>
                        <td class="orderItemProductInfoPartTD" width="100px">

                            <div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number"
                                                                                              value="${oi.product.price}"
                                                                                              minFractionDigits="2"/></div>
                            <div class="orderListItemProductPrice">￥<fmt:formatNumber type="number"
                                                                                      value="${oi.product.promotePrice}"
                                                                                      minFractionDigits="2"/></div>


                        </td>
                        <c:if test="${st.count==1}">

                            <td valign="top" rowspan="${fn:length(o.orderItem)}"
                                class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
                                <span class="orderListItemNumber">${o.totalNum}</span>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItem)}" width="120px"
                                class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
                                <div class="orderListItemProductRealPrice">￥<fmt:formatNumber minFractionDigits="2"
                                                                                              maxFractionDigits="2"
                                                                                              type="number"
                                                                                              value="${o.totalPrice}"/></div>
                                <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItem)}"
                                class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
                                <c:if test="${o.status=='waitReceipt' }">
                                    <a href="checkconfirmPay?id=${o.id}">
                                        <button class="orderListItemConfirm">确认收货</button>
                                    </a>
                                </c:if>
                                <c:if test="${o.status=='waitPayment' }">
                                    <a href="checkpayed?id=${o.id}&totalPrice=${o.totalPrice}">
                                        <button class="orderListItemConfirm">付款</button>
                                    </a>
                                </c:if>

                                <c:if test="${o.status=='waitDelivery' }">
                                    <span>待发货</span>
                                    <button class="btn btn-info btn-sm ask2delivery"
                                            link="admin/OrderDelivery?id=${o.id}">催卖家发货
                                    </button>

                                </c:if>

                                <c:if test="${o.status=='waitReview' }">
                                    <a href="checkreview?id=${o.id}">
                                        <button class="orderListItemReview">评价</button>
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>

            </table>
        </c:forEach>

    </div>

</div>