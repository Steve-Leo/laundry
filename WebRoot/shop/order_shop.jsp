<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ruanku.model.Order"%>
<%@ page import="com.ruanku.model.OrderBusiShop"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<script src="script/order_shop.js"></script>
<script type="text/javascript">
	 function modify(var1,var2) {
		$.post(
			"shop/shop!changeOrderState.action?id="+var1+"&"+"state="+var2,
			callback,
			"xml"
		);
		function callback(data){
			//
			//window.location.reload();
			alert("提交成功！");
			$("#content").load("shop/shop!shopOrderList.action");
			

		};
	}
	$("#submit_info").click(modify);
	
</script>

<!DOCTYPE HTML>
<link rel="stylesheet" href="bootstrap-3.3.5-dist/css/bootstrap.min.css">

<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.4.4/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script> --%>
<!-- <div class="container"> -->
	<div class="input-group col-xs-4">
		<input type="text" id="searchOrderText" class="form-control"
			placeholder="输入订单号搜索">
		<div class="input-group-btn">
			<button class="btn btn-primary" id="searchOrder">搜索</button>
			<button class="btn btn-default dropdown-toggle" type="button"
				id="screen" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">
				筛选 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right"
				aria-labelledby="screen">
				<li><a id="screen" href="javascript:void(0)" name="0">全部</a></li>
				<li><a id="screen" href="javascript:void(0)" name="2">待接单</a></li>
				<li><a id="screen" href="javascript:void(0)" name="3">取件中</a></li>
				<li><a id="screen" href="javascript:void(0)" name="4">清洗中</a></li>
				<li><a id="screen" href="javascript:void(0)" name="5">已送达</a></li>
				<li><a id="screen" href="javascript:void(0)" name="11">待评价</a></li>
				<li><a id="screen" href="javascript:void(0)" name="12">已评价</a></li>
			</ul>
		</div>
	</div>
	<br>
	<table class="table">
		<thead>
			<tr>
				<th class="col-xs-2 text-center">衣服名称</th>
				<th class="col-xs-1 text-center">清洗方式</th>
				<th class="col-xs-2 text-center">单价</th>
				<th class="col-xs-1 text-center">数量</th>
				<th class="col-xs-2 text-center">总金额</th>
				<th class="col-xs-2 text-center">状态</th>
				<th class="col-xs-2 text-center">操作</th>
			</tr>
		</thead>
	</table>

	<c:if test="${!empty obslist}">
		<c:forEach var="obs" items="${obslist}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<ul class="list-inline">
						<li><strong>${fn:substring(obs.paytime, 0, 19)}</strong>
						</li>
						<li>订单号：${obs.ordernum}</li>
						<li>${obs.receiver}</li>
						<li>${obs.tel}</li>
						<li>${obs.address}</li>
					</ul>
				</div>
				<table class="table table-bordered">
					<tbody>
						<c:forEach var="item" items="${obs.items}" varStatus="state">
							<tr>
								<td class="col-xs-2 text-center">${item.clothName}</td>
								<td class="col-xs-1 text-center"><c:choose>
										<c:when test="${item.washMode == 1}">干洗</c:when>
										<c:when test="${item.washMode == 2}">水洗</c:when>
									</c:choose></td>
								<td class="col-xs-2 text-center">${item.price}</td>
								<td class="col-xs-1 text-center">${item.amount}</td>
								<c:if test="${state.count==1}">
									<td rowspan="${fn:length(obs.items)}"
										class="col-xs-2 text-center"><strong>${obs.totalAmount}</strong>
									</td>
									<td rowspan="${fn:length(obs.items)}"
										class="col-xs-2 text-center"><c:choose>
											<c:when test="${obs.state == 1}">未支付</c:when>
											<c:when test="${obs.state == 2}">待接单</c:when>
											<c:when test="${obs.state == 3}">取件中</c:when>
											<c:when test="${obs.state == 4}">清洗中</c:when>
											<c:when test="${obs.state == 5}">已送达</c:when>
											<c:when test="${obs.state == 11}">待评价</c:when>
											<c:when test="${obs.state == 12}">已评论</c:when>
											<c:when test="${obs.state == -1}">已取消</c:when>
										</c:choose></td>
									<td rowspan="${fn:length(obs.items)}"
										class="col-xs-2 text-center">
										<c:choose>
											<c:when test="${obs.state == 2}">
												<a href="javascript:void(0)"
													onclick="modify(${obs.id},${obs.state})">去取件</a>
											</c:when>
											<c:when test="${obs.state == 3}">
												<a href="javascript:void(0)"
													onclick="modify(${obs.id},${obs.state})">去清洗</a>
											</c:when>
											<c:when test="${obs.state == 4}">
												<a href="javascript:void(0)"
													onclick="modify(${obs.id},${obs.state})">已送达</a>
											</c:when>
										</c:choose>
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
			<li><a id="prevpage" href="javascript:void(0)"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a>
			</li>
			<c:choose>
				<c:when test="${total > 50 && page > 5}">
					<li><a id="pagetag" href="javascript:void(0)">${page-4}</a>
					</li>
					<li><a id="pagetag" href="javascript:void(0)">${page-3}</a>
					</li>
					<li><a id="pagetag" href="javascript:void(0)">${page-2}</a>
					</li>
					<li><a id="pagetag" href="javascript:void(0)">${page-1}</a>
					</li>
					<li class="active"><a id="pagetag" href="javascript:void(0)">${page}</a>
					</li>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${page == 1}">
							<li class="active"><a id="pagetag" href="javascript:void(0)">1</a>
							</li>
						</c:when>
						<c:when test="${page != 1}">
							<li><a id="pagetag" href="javascript:void(0)">1</a>
							</li>
						</c:when>
					</c:choose>
					<c:if test="${total > 10}">
						<c:choose>
							<c:when test="${page == 2}">
								<li class="active"><a id="pagetag"
									href="javascript:void(0)">2</a>
								</li>
							</c:when>
							<c:when test="${page != 2}">
								<li><a id="pagetag" href="javascript:void(0)">2</a>
								</li>
							</c:when>
						</c:choose>
					</c:if>
					<c:if test="${total > 20}">
						<c:choose>
							<c:when test="${page == 3}">
								<li class="active"><a id="pagetag"
									href="javascript:void(0)">3</a>
								</li>
							</c:when>
							<c:when test="${page != 3}">
								<li><a id="pagetag" href="javascript:void(0)">3</a>
								</li>
							</c:when>
						</c:choose>
					</c:if>
					<c:if test="${total > 30}">
						<c:choose>
							<c:when test="${page == 4}">
								<li class="active"><a id="pagetag"
									href="javascript:void(0)">4</a>
								</li>
							</c:when>
							<c:when test="${page != 4}">
								<li><a id="pagetag" href="javascript:void(0)">4</a>
								</li>
							</c:when>
						</c:choose>
					</c:if>
					<c:if test="${total > 40}">
						<c:choose>
							<c:when test="${page == 5}">
								<li class="active"><a id="pagetag"
									href="javascript:void(0)">5</a>
								</li>
							</c:when>
							<c:when test="${page != 5}">
								<li><a id="pagetag" href="javascript:void(0)">5</a>
								</li>
							</c:when>
						</c:choose>
					</c:if>
				</c:otherwise>
			</c:choose>

			<li><a id="nextpage" href="javascript:void(0)" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
			</a>
			</li>
		</ul>
	</nav>
	<p id="ordertype" style="display:none">${type}</p>
	<p id="total_amount" style="display:none">${total}</p>
<!-- </div> -->