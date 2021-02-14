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
							<li class="active"><a href="">Manage Category</a></li>
						</ul></center>
							<div class="payment-adress">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2" href="/admin/categoryCodeListing"
									type="submit" name="submit" value="adminListing">Back</a>
							</div>
						<!-- </form> -->
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-right: 21%; padding-left: 21%;">
										<div class="review-content-section">
										<c:choose>
												<c:when test="${not empty categoryCode}">
													<c:url var="action" value="/admin/categoryCode/edit"/>
												</c:when>
												<c:otherwise>
													<c:url var="action" value="/admin/categoryCode/save"/>
												</c:otherwise>
											</c:choose>
											<form action="${action}" method="post" onsubmit="return ValidateForm(this);">
											<div id="dropzone1" class="pro-ad">
											
													<p style="color: green" align="center">${successMessage}</p>
													<p style="color: red" align="center">${deletesuccessmessage}</p>
													<script type="text/javascript">
															function ValidateForm(frm) {
																if (frm.password1.value != frm.password2.value) {
																	alert('Passwords do not match');
																	frm.password1.focus();
																	return false;
																	} 
																if (frm.mobile.value.length!=10){
																	alert('Required 10 digits, match requested format!');
																	frm.mobile.focus();
																	return false;
																	}
																
																} 
															</script>
													<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															</div>
															<c:choose>
															<c:when test="${not empty categoryCode}">
															<div class="form-group">
																<input name="code" type="text" class="form-control"
																	placeholder="Category Code" value="${categoryCode.code}" readonly>
															</div>
															</c:when>
															<c:otherwise>
															<div class="form-group">
																<input name="code" type="text" class="form-control"
																	placeholder="Category Code" value="${categoryCode.code}" required>
															</div>
															</c:otherwise>
															</c:choose>
															<div class="form-group">
																<input name="description" type="text" class="form-control"
																	placeholder="Description" value="${categoryCode.description}" required>
															</div>
															<div class="form-group">
																<input name="activedays" type="text" class="form-control"
																	placeholder="Active Days" value="${categoryCode.activedays}" required>
															</div>
															<div class="form-group">
																<input name="rewardPoint" type="text" class="form-control"
																	placeholder="Reward Point" value="${categoryCode.rewardPoint}" required>
															</div>
															
													</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
																<c:choose>
															<c:when test="${not empty categoryCode.code}">
																<button class="rmk btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Update</button>
															</c:when>
															<c:otherwise>
																<button class="rmk btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Create</button>
															</c:otherwise>
															</c:choose>
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

