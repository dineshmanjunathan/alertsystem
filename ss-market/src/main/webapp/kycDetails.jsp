<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<center>
						<ul id="myTabedu1" class="tab-review-design">
							<li class="active"><a href="">Add KYC Details</a></li>
						</ul>
					</center>
					<div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/menu" type="submit" name="submit" value="adminListing">Back
							to main</a>
					</div>
					<!-- </form> -->

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
									style="padding-right: 21%; padding-left: 21%;">
									<div class="review-content-section">
										<c:choose>
											<c:when test="${not empty productCode}">
												<c:url var="action" value="/admin/product/edit" />
											</c:when>
											<c:otherwise>
												<c:url var="action" value="/admin/product/save" />
											</c:otherwise>
										</c:choose>
										<form action="/member/kycdetails/save" method="post" enctype="multipart/form-data">
											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${errorMessage}</p>


												<div class="form-group">
													<input name="pancardNumber" type="text"
														class="form-control" placeholder="Shipping Charge"
														value="${details.pancardNumber}" required>
												</div>

												<c:choose>
													<c:when test="${not empty details.base64Image}">
														<div class="form-group">
															<input type="hidden" name="base64Image"
																value="${details.base64Image}"> <img
																alt="img"
																src="data:image/jpeg;base64,${details.base64Image}"
																style="width: 100px; height: 100px;" />
														</div>
													</c:when>
												</c:choose>
												<div class="form-group">
													<input class="btn btn-primary" type="file" name="image" required/>
												</div>


											</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="payment-adress">

												<button class="rmk btn btn-primary waves-effect waves-light"
													type="submit" name="submit" value="register">Create</button>
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
	
</body>

</html>

