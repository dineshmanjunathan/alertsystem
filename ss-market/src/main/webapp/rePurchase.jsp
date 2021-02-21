<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">

<script type="text/javascript" charset="utf-8">
function review() {
	var walletBalance = $('#walletBalance').val();
	var repurcahse = $('#repurcahse').val();
	window.location.href = "/wallet/deduction/compute?walletBalance="+walletBalance+"&repurcahse="+repurcahse;
}

</script>

</head>
<body>


	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-2 row">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="product-payment-inner-st">
						<center><ul id="myTabedu1" class="tab-review-design">
							<li class="active"><a href="">RE-PURCHASE ENTRY</a></li>
						</ul></center>
						<!-- <form action="/userlisting" method="get"> -->
						<div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/member/repurchase/wallet" type="submit" name="submit"
							value="adminListing">Back</a>
						</div>
						<!-- </form> -->
						<p style="color: red" align="center">${errormsg}</p>
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-right: 21%; padding-left: 21%;">
										<div class="review-content-section">
										
											<form action="/updateRePurchase" method="post">
											
											
											<input type="hidden" name="id" id="id" value="${member.id}">
									
											<div id="dropzone1" class="pro-ad">
											
													<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															<label>Available Balance:</label>
																<input name="walletBalance" id="walletBalance" type="text" class="form-control"
																	placeholder="" value="${member.walletBalance}" readonly>
															</div>
														
														<c:choose>
														<c:when test="${not empty DEBIT}">
														<div class="form-group">
															<input name="repurcahse" id="repurcahse" type="number" class="form-control"
																placeholder="Add Points to Re-purcahse" value="${member.repurcahse}" required>
														</div>
														</c:when>
														<c:otherwise>
														<div class="form-group">
															<input name="repurcahse" id="repurcahse" type="number" class="form-control"
																placeholder="Add Points to Re-purcahse" required>
														</div>
														</c:otherwise>
														</c:choose>

														<div class="payment-adress">
															<a
																class="btn btn-success col-md-offset-9 col-md-3"
																onclick="return review();" type="button">Check for Charges </a>
														</div>
														<br><br><br>
														<c:choose>
														<c:when test="${not empty DEBIT}">
														<p style="color: red">${PAN_OFFER}</p>
														<div class="form-group">
														<label style="font-size: 25px;">Deduction: ${DEBIT} </label>
														</div>
														
														<div class="form-group">
														<label style="font-size: 25px;">Point to transfer: ${REPURCHASE_POINT}</label>
														</div>
														</c:when>
														</c:choose>
														</div>
															
													</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
																<button class="rmk btn btn-primary waves-effect waves-light"
																	onclick="return confirm('Are you sure you want to submit?')" type="submit" name="submit" value="register">Submit</button>

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

