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
	<div class="col-md-12 row" style="padding-right: 3%;padding-left: 3%;">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="product-payment-inner-st">
						<center><ul id="myTabedu1" class="tab-review-design">
							<li class="active"><a href="">Trigger Message</a></li>
						</ul></center>
							<!-- <div class="payment-adress">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2" href="/admin/categoryCodeListing"
									type="submit" name="submit" value="adminListing">Back</a>
							</div> -->
						<!-- </form> -->
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
										<div class="review-content-section">
			
											<form action="message/trigger" method="post" >
											<div id="dropzone1" class="pro-ad">
											
													<p style="color: green" align="center">${successMessage}</p>
													<p style="color: red" align="center">${deletesuccessmessage}</p>
													<div class="row" style="padding-right: 80%;">
													
														<div class="form-group">															
															<select name="type" id="type"
																class="form-control">
																<option value="">-Select input type-</option>
																<option value="T">Text Area</option>
																<option value="F"> File Input</option>
															</select>
														</div>
														</div>
														<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															</div>
															
															<div class="form-group">
															  <label for="mobileno">Enter comma separated mobile numbers [****,****...etc] </label>
															  <textarea class="form-control" rows="5" name="mobileno" id="mobileno" value="${message.mobileno}"></textarea>
															</div>
															<div class="form-group">
															  <label for="message">Enter formatted message </label>
															  <textarea class="form-control" rows="5" name="message" id="message" value="${message.message}"></textarea>
															</div>
															
													</div>
													</div>
													<div class="row">
													<div class="form-group">
																<input class="btn btn-primary" type="file" name="fileupload" /><br>
													</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
																<button class="rmk btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Trigger</button>
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

