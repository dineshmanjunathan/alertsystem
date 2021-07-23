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
						<center><ul id="myTabedu1" class="tab-review-design">
							<li class="active"><a href="">Send Messagey</a></li>
						</ul></center>
							<!-- <div class="payment-adress">
								<a
									class="btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2" href="/admin/categoryCodeListing"
									type="submit" name="submit" value="adminListing">Back</a>
							</div> -->
						<!-- </form> -->
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-right: 21%; padding-left: 21%;">
										<div class="review-content-section">

											<form action="/admin/categoryCode/save" method="post" onsubmit="return ValidateForm(this);">
											<div id="dropzone1" class="pro-ad">
											
													<p style="color: green" align="center">${successMessage}</p>
													<p style="color: red" align="center">${deletesuccessmessage}</p>

													<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															</div>
															<div class="form-group">
																<input name="code" type="text" class="form-control"
																	placeholder="Reference Id" value="${categoryCode.code}" required>
															</div>
															<div class="form-group">
																<input name="description" type="textarea" class="form-control"
																	placeholder="Message" value="${categoryCode.message}" required>
															</div>
															<div class="form-group">
																<input name="mobileno" type="textarea" class="form-control"
																	placeholder="Mobile no" value="${categoryCode.mobileno}" required>
															</div>
															
													</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
																<button class="btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Submit</button>
																<button class="btn btn-primary waves-effect waves-light"
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

