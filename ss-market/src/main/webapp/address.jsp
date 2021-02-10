<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

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
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/menu" type="submit" name="submit"
							value="adminListing">Back to Main</a>
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
											<input type="hidden" name="role" id="role" value="MEMBER">
											<input type="hidden" name="redeemedPoints" id="redeemedPoints" value="${address.redeemedPoints}">

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${deletesuccessmessage}</p>

												<li class="active"><a href="">Address Details:</a></li>
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														
														<div class="form-group">
															<input name="addressLineOne" id ="addressLineOne" type="text"
																class="form-control" placeholder="Address Line One"
																value="${address.addressLineOne}" required>
														</div>
														
														<div class="form-group">
															<input name="addressLineTwo" id ="addressLineTwo" type="text"
																class="form-control" placeholder="Address Line Two"
																value="${address.addressLineTwo}" required>
														</div>
														<div class="form-group">
															<input name="postalCode" id ="postalCode" type="text"
																class="form-control" placeholder="Postal Code"
																value="${address.postalCode}" required>
														</div>

													</div>
													
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														
														<div class="form-group">
															<input name="city" id ="city" type="text"
																class="form-control" placeholder="City"
																value="${address.city}" required>
														</div>
														<div class="form-group">
															<input name="state" id ="state" type="text"
																class="form-control" placeholder="State"
																value="${address.state}" required>
														</div>
														

													</div>
													
													
												</div>
												
												<li class="active"><a href="">Payment Details:</a></li>
												<div class="well row">
													<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12">
													</center>
													<c:choose>
															<c:when test="${member.repurcahse>0}">
													<div class="form-check">
															<input class="form-check-input" type="checkbox" value="" id="repurchase" /> 
															<label style="font-weight: normal;">
													   		Use<b> Re-Purchase wallet</b> [<b>${member.repurcahse}</b> points]    
													  		</label>
													</div><br><br>
													</c:when>
													</c:choose>
														<div class="form-check">
														<label class="radio-inline">
													      <input type="radio" name="payment" checked><h4 >Cash On Delivery</h4>
													    </label>
													    <label class="radio-inline">
													      <input type="radio" name="payment"> <h4>e-Payment</h4>
													    </label>
													</div>
												</div>
												</div>
												
												
												
											</div>
											<div class="row">
											<div class="col-lg-12">
											Purchase Total: <span id="cartTotal">${cartTotal}</span> 
											</div>
											</div>
											<br>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="submit" name="submit" value="register">Proceed to Pay</button>
														<button class="rmk btn btn-primary waves-effect waves-light"
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

