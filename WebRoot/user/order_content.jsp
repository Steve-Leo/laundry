<%@ page language="java" import="java.util.*,com.ruanku.yeepay.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="script/order_content.js"></script>

<div id="inner_content">
<c:if test="${!empty oblist}">
	<c:forEach var="orderBusi" items="${oblist}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<ul class="list-inline">
					<li><strong>${fn:substring(orderBusi.paytime, 0, 19)}</strong>
					</li>
					<li>订单号：${orderBusi.ordernum}</li>
					<li>${orderBusi.shopName}</li>
				</ul>
			</div>
			<table class="table table-bordered">
				<tbody>
					<c:forEach var="item" items="${orderBusi.orderItems}"
						varStatus="state">
						<tr>
							<td class="col-xs-2 text-center">${item.clothName}</td>
							<td class="col-xs-1 text-center"><c:choose>
									<c:when test="${item.washMode == 1}">干洗</c:when>
									<c:when test="${item.washMode == 2}">水洗</c:when>
								</c:choose></td>
							<td class="col-xs-2 text-center">${item.price}</td>
							<td class="col-xs-1 text-center">${item.amount}</td>
							<c:if test="${state.count==1}">
								<td rowspan="${fn:length(orderBusi.orderItems)}"
									class="col-xs-2 text-center"><strong>${orderBusi.totalAmount}</strong>
								</td>
								<td rowspan="${fn:length(orderBusi.orderItems)}"
									class="col-xs-2 text-center"><c:choose>
										<c:when test="${orderBusi.state == 1}">未支付</c:when>
										<c:when test="${orderBusi.state == 2}">待接单</c:when>
										<c:when test="${orderBusi.state == 3}">取件中</c:when>
										<c:when test="${orderBusi.state == 4}">清洗中</c:when>
										<c:when test="${orderBusi.state == 5}">已送达</c:when>
										<c:when test="${orderBusi.state == 11}">待评价</c:when>
										<c:when test="${orderBusi.state == 12}">已评论</c:when>
										<c:when test="${orderBusi.state == -1}">已取消</c:when>
									</c:choose></td>
								<td rowspan="${fn:length(orderBusi.orderItems)}"
									class="col-xs-2 text-center">
									<form action="" method="post" method="post"
										enctype="multipart/form-data">
										<a id="detail" href="javascript:void(0)">查看详情</a>
										<c:choose>
											<c:when test="${orderBusi.state == 1}">
												<br>
												<a id="pay" href="javascript:void(0)">立即支付</a>
												<br>
												<a id="cancel" href="javascript:void(0)">取消订单</a>
											</c:when>
											<c:when test="${orderBusi.state == 2 && orderBusi.payMode == 1}">
												<br>
												<a id="cancelWithRefund" href="javascript:void(0)">取消订单</a>
											</c:when>
											<c:when test="${orderBusi.state == 5}">
												<br>
												<a id="confirm" href="javascript:void(0)">确认收货</a>
											</c:when>
											<c:when test="${orderBusi.state == 11}">
												<br>
												<a id="evaluate" href="javascript:void(0)">评价订单</a>
											</c:when>
										</c:choose>
										<input name="orderId" value="${orderBusi.id}"
											style="display:none">
									</form>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:if>
<nav>
	<ul class="pagination" style="float:right;">
		<li><a id="prevpage" href="javascript:void(0)" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span> </a>
			</li>
			<c:choose>
				<c:when test="${total > 50 && page > 5}">
					<li><a id="pagetag" href="javascript:void(0)">${page-4}</a></li>
					<li><a id="pagetag" href="javascript:void(0)">${page-3}</a></li>
					<li><a id="pagetag" href="javascript:void(0)">${page-2}</a></li>
					<li><a id="pagetag" href="javascript:void(0)">${page-1}</a></li>
					<li class="active"><a id="pagetag" href="javascript:void(0)">${page}</a></li>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${page == 1}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">1</a></li>
						</c:when>
						<c:when test="${page != 1}">
							<li><a id="pagetag" href="javascript:void(0)">1</a></li>
						</c:when>
					</c:choose>
					<c:if test="${total > 10}">
						<c:choose>
						<c:when test="${page == 2}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">2</a></li>
						</c:when>
						<c:when test="${page != 2}">
							<li><a id="pagetag" href="javascript:void(0)">2</a></li>
						</c:when>
					</c:choose>
					</c:if>
					<c:if test="${total > 20}">
						<c:choose>
						<c:when test="${page == 3}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">3</a></li>
						</c:when>
						<c:when test="${page != 3}">
							<li><a id="pagetag" href="javascript:void(0)">3</a></li>
						</c:when>
					</c:choose>
					</c:if>
					<c:if test="${total > 30}">
						<c:choose>
						<c:when test="${page == 4}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">4</a></li>
						</c:when>
						<c:when test="${page != 4}">
							<li><a id="pagetag" href="javascript:void(0)">4</a></li>
						</c:when>
					</c:choose>
					</c:if>
					<c:if test="${total > 40}">
						<c:choose>
						<c:when test="${page == 5}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">5</a></li>
						</c:when>
						<c:when test="${page != 5}">
							<li><a id="pagetag" href="javascript:void(0)">5</a></li>
						</c:when>
					</c:choose>
					</c:if>
				</c:otherwise>
			</c:choose>
			
		<li><a id="nextpage" href="javascript:void(0)" aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span></a></li>
	</ul>
</nav>
<p id="ordertype" style="display:none">${type}</p>
<p id="total_amount" style="display:none">${total}</p>
</div>