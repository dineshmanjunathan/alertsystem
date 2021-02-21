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
										<form action="/member/kycdetails/save" method="post" enctype="multipart/form-data">
											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${errorMessage}</p>
												<c:choose>
													<c:when test="${not empty details}">
												
												<div class="form-group">
													<input name="pancardNumber" type="text"
														class="form-control" placeholder="Pan Number"
														value="${details.pancardNumber}" required readonly>
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
												
												</c:when>
												<c:otherwise>
												<div class="form-group">
													<input name="pancardNumber" type="text"
														class="form-control" placeholder="Pan Number"
														value="${details.pancardNumber}" required>
												</div>
												<div class="form-group">
													<input class="btn btn-primary" type="file" name="image" required>
												</div><br>
												<p style="color: red" align="center">NOTE: Size should not exceed 2MB</p>
												</c:otherwise>
												</c:choose>

											</div>
									
									<c:choose>
									<c:when test="${not empty details}">
									</c:when>
									<c:otherwise>
									<div class="row">
										<div class="col-lg-12">
											<div class="payment-adress">
												<br>
												<br>
												<button onclick="return confirm('Are you sure you want to Save?')" class="rmk btn btn-primary waves-effect waves-light"
													type="submit" name="submit" value="register">Save</button>
											</div>
										</div>
									</div>
									</c:otherwise>
									</c:choose>

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

