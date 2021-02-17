<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

$("input[type='checkbox']").change(function() {
    if(this.checked) {
        let val = ${member.repurcahse};
        let cartTotal = ${cartTotal} - val;
        $('#cartTotal').text(cartTotal);
        $('#redeemedPoints').val(val);
    } else {
    	$('#cartTotal').text(${cartTotal});
    	$('#redeemedPoints').val(0);
    }
});

function useWallet() {
	  var checkBox = document.getElementById("repurchase");
	  if (checkBox.checked == true){
		  let val = ${member.repurcahse};
	        let cartTotal = ${cartTotal} - val;
	        $('#cartTotal').text(cartTotal);
	        $('#redeemedPoints').val(val);
	  } else {
		  $('#cartTotal').text(${cartTotal});
	    	$('#redeemedPoints').val(0);
	  }
	}

</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-1 row">
		<div class="row">
			<div class="col-lg-12 col-md-10 col-md-offset-1 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<li class="active"><a href="">Add delivery Address</a></li>
					</ul>
					<!-- <form action="/userlisting" method="get"> -->
					<div class="payment-adress">
						<c:choose>
							<c:when test="${fn:contains(sessionScope.ROLE, 'STOCK_POINT')}">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
									href="/stock/point/menu" type="submit" name="submit"
									value="adminListing">Back to Main</a>
							</c:when>
							<c:otherwise>
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
									href="/menu" type="submit" name="submit" value="adminListing">Back
									to Main</a>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- </form> -->

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<form action="/purchase/confirm" method="post">

											<p style="color: red" align="center">${errormsg}</p>

											<input type="hidden" name="id" id="id" value="${member.id}">
											<input type="hidden" name="discount" id="discount" value="${discount}">
											<input type="hidden" name="redeemedPoints"
												id="redeemedPoints" value="${member.repurcahse}"> <input
												type="hidden" name="cartTotal" id="cartTotal"
												value='${cartTotal}'> <input type="hidden"
												name="shippingCharges" id="shippingCharges"
												value='${shippingCharge}'>

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${deletesuccessmessage}</p>

												<li class="active"><a>Address Details:</a></li>
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>

														<div class="form-group">
															<input name="addressLineOne" id="addressLineOne"
																type="text" class="form-control"
																placeholder="Address Line One"
																value="${address.addressLineOne}" required>
														</div>

														<div class="form-group">
															<input name="addressLineTwo" id="addressLineTwo"
																type="text" class="form-control"
																placeholder="Address Line Two"
																value="${address.addressLineTwo}" required>
														</div>
														<div class="form-group">
															<input name="postalCode" id="postalCode" type="text"
																class="form-control" placeholder="Postal Code"
																value="${address.postalCode}" required>
														</div>

													</div>

													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>

														<div class="form-group">
															<input name="city" id="city" type="text"
																class="form-control" placeholder="City"
																value="${address.city}" required>
														</div>
														<div class="form-group">
															<input name="state" id="state" type="text"
																class="form-control" placeholder="State"
																value="${address.state}" required>
														</div>


													</div>


												</div>

												<li class="active"><a>Payment Details:</a></li>
												<div class="well row">
													<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="form-check">
															<label class="radio-inline"> <input type="radio"
																name="paymentType" value="CASH" checked>
																<h4>Cash</h4>
															</label> <label class="radio-inline"> <input type="radio"
																name="paymentType" value="EPAY">
																<h4>e-Payment</h4>
															</label>
															<c:choose>
																<c:when test="${member.repurcahse>=cartTotal}">
																	<label class="radio-inline"> <input
																		type="radio" name="paymentType" value="REPURCHASE">
																		<h4>Use Re-Purchase wallet</h4>
																	</label>
																</c:when>
															</c:choose>
														</div>
													</div>

													<c:choose>
														<c:when test="${member.repurcahse>=total}">
															<div class="row">
																<a href="#"
																	class="btn btn-waring col-md-offset-9 col-md-3"> <span>
																		<i class="fa fa-cc-mastercard" style="font-size: 20px"></i>
																		<span><strong>Re-Purchase wallet:</strong> <span
																			id="repurcahsewallet">${member.repurcahse}</span> </span>
																</span></a>
															</div>
														</c:when>
													</c:choose>
													<div class="row">
														<a href="#"
															class="btn btn-waring col-md-offset-9 col-md-3">
															<table style="width: 100%">

																<tr>
																	<th><strong>Cart Total:</strong></th>
																	<td id="cartProductTotal">&#x20b9;${cartTotal}</td>
																</tr>
																<c:if test="${fn:contains(sessionScope.ROLE, 'MEMBER')}">
																	<tr>
																		<th><strong>Shipping Charge:</strong></th>
																		<td>&#x20b9;${shippingCharge}</td>
																	</tr>
																</c:if>
																<c:if
																	test="${fn:contains(sessionScope.ROLE, 'STOCK_POINT')}">
																	<tr>
																		<th><strong>Discount:</strong></th>
																		<td>&#x20b9;${discount}</td>
																	</tr>
																</c:if>
																<tr>
																	<th><strong>Purchase Total:</strong></th>
																	<td>&#x20b9;${total}</td>
																</tr>
															</table> <%-- <span> <i class="fa fa-shopping-cart"
													style="font-size: 20px"></i> <span><strong>Purchase Total:&#x20b9;</strong> <span id="cartTotal">${cartTotal}</span>
												</span> --%> </span>
														</a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">
														<button
															class="rmk btn btn-primary waves-effect waves-light"
															type="submit" name="submit" value="register">Proceed
															to Pay</button>
														<button
															class="rmk btn btn-primary waves-effect waves-light"
															type="reset" name="reset" value="reset">Clear</button>

													</div>
												</div>
											</div>

										</form>
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

