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
								<c:set var="url" value=""></c:set>
								<c:choose>
										<c:when test="${sessionScope.ROLE == 'MEMBER' }">
											<c:set var="url" value="/menu"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="url" value="/stock/point/menu"></c:set>
										</c:otherwise>
									</c:choose>
									<a href="${url }"
										class="rmk btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-2">
										<span><i class="fa fa-arrow-left"></i> <span>Back to Main</span> </span>
									</a> 
								</div>
								<br>
								<div class="row">
									<a href="#"
										class="btn btn-warning col-md-offset-1 col-md-2" style="text-align: left;">
										<span><strong>Order number :</strong> ${orderNumber} </span>
									</a> </div><br>
									<div class="row">
									<a href="#"
										class="btn btn-warning col-md-offset-1 col-md-3" style="text-align: left;">
										<strong>Address :</strong><br>
										<span>${address.addressLineOne}, ${address.addressLineTwo} </span><br>
										<span>${address.city}, ${address.state} </span><br>
										<span>${address.postalCode} </span>
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
														<c:set var="shippingCharge" value="${detail.shippingCharge}" />
											     <td>
		        								  <a href="<c:url value='/purchase/order/generate/pdf?orderNumber=${orderNumber}' />" > <center><i class="fa fa-download" aria-hidden="true"></i></center></a>
		        								 </td>	
													</tr>
												</c:forEach>
											</tbody>
										</table>

										<div class="row">
											<a href="#"
												class="btn btn-waring m-btn m-btn--custom m-btn--icon col-md-offset-9 col-md-3">
												<c:if test="${address.redeemedPoints gt 0}">
												<span>  <span><strong>Total:
														&#x20b9;</strong><span id="cartTotal">${cartTotal}</span>
												</span>
												</span><br>
												<span><strong>Re-purchase Used:&#x20b9;</strong> <span id="cartTotal"> ${address.redeemedPoints}</span>
												</span><br>
												<c:set var="cartTotal" value="${cartTotal - address.redeemedPoints}" />
												</c:if>
												<table style="width:100%">
												<tr>
												<tr>
												<th><strong>Cart Total:&#x20b9;</strong> </th>
												<td id="cartProductTotal">${cartTotal}</td>
												</tr>
												<th><strong>Shipping Charge:&#x20b9;</strong></th>
												<td id="shippingCharge">${shippingCharge}</td>
												</tr>
												<tr>
												<th><strong>Purchase Total:&#x20b9;</strong> </th>
												<td id="cartTotal">${cartTotal+shippingCharge}</td>
												</tr>
												</table>								
												<!-- 
												<span>  <span><strong>Shipping Charge:
														&#x20b9;</strong><span id="shippingCharge">${shippingCharge}</span>
												</span><br>
												 <span>  <span><strong>Cart Total:
														&#x20b9;</strong><span id="cartProductTotal">${cartTotal}</span>
												</span><br>
												<span>  <span><strong>Purchase Total:
														&#x20b9;</strong><span id="cartTotal">${cartTotal+shippingCharge}</span>
												</span>-->
											</span></a>
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