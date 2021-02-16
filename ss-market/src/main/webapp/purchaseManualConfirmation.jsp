<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<center>
							<li class="active"><a href="">Order confirmation</a></li>
						</center>
					</ul>

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="row">
								<c:set var="url" value="#"></c:set>
								<c:choose>
								<c:when test="${sessionScope.ROLE == 'ADMIN' }">
									<c:set var="url" value="/admin/menu"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="url" value="/stock/point/menu"></c:set>
								</c:otherwise>
								</c:choose>
									<a href="${url}"
										class="btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-2">
										<span><i class="fa fa-arrow-left"></i> <span>Back to Main</span> </span>
									</a> 
								</div>
								<br>
								<div class="row">
									<a href="#"
										class="btn btn-warning col-md-offset-4 col-md-3">
										<span>Order number : ${orderNumber} </span>
										<%-- <span>Address : ${orderAddress} </span> --%>
									</a>
									<a href="#"
										class="btn btn-warning col-md-offset-4 col-md-3">
										<span>Ordered Placed to : ${memberId} </span>
										<%-- <span>Address : ${orderAddress} </span> --%>
									</a>
								</div>
								<div class="sparkline13-graph">
									<div class="datatable-dashv1-list custom-datatable-overright">
										<div id="toolbar">
											<select class="form-control dt-tb">
												<option value="">Export Basic</option>
												<option value="all">Export All</option>
												<option value="selected">Export Selected</option>
											</select>
										</div>
										<table id="table" data-toggle="table" data-pagination="true"
											data-search="true" data-show-columns="true"
											data-show-pagination-switch="true" data-show-refresh="false"
											data-key-events="true" data-show-toggle="true"
											data-resizable="true" data-cookie="true"
											data-cookie-id-table="saveId" data-show-export="true"
											data-click-to-select="true" data-toolbar="#toolbar">
											<thead>
												<tr>
													<th data-field="prodCode" data-editable="false">Product</th>
													<th data-field="quantity" data-editable="false">Quantity</th>
													<th data-field="price" data-editable="false">Price per quantity</th>
													<th data-field="total" data-editable="false">Total</th>
													<th data-field="attachment" data-editable="false">Attachment</th>
												</tr>
											</thead>
											<tbody>
												<c:set var="cartTotal" value="${0}" />
												<c:forEach var="detail" items="${cartList}">
													<tr>
														<td>${detail.prodDesc}</td>
														<td>${detail.quantity}</td>
														<td>${detail.amount}</td>
														<c:set var="total" value="${detail.amount * detail.quantity}" />
														<td>${total}</td>
														<c:set var="cartTotal" value="${cartTotal + total}" />
													 <td>
		        								  <a href="<c:url value='/purchase/manual/order/pdf?orderNumber=${orderNumber}' />" > <center><i class="fa fa-download" aria-hidden="true"></i></center></a>
		        								 </td>	
													</tr>
												</c:forEach>
											</tbody>
										</table>

										<div class="row">
											<a href="#"
												class="btn btn-waring m-btn m-btn--custom m-btn--icon col-md-offset-9 col-md-3">
												<span> <i class="fa fa-shopping-cart"
													style="font-size: 20px"></i> <span>Purchase Total:
														&#x20b9; <span id="cartTotal">${cartTotal}</span>
												</span>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>