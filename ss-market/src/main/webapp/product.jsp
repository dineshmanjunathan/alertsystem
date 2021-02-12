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
							<li class="active"><a href="">Manage Product</a></li>
						</ul></center>
							<div class="payment-adress">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2" href="/admin/productListing"
									type="submit" name="submit" value="adminListing">Back</a>
							</div>
						<!-- </form> -->
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-right: 21%; padding-left: 21%;" >
										<div class="review-content-section">
										<c:choose>
												<c:when test="${not empty productCode}">
													<c:url var="action" value="/admin/product/edit"/>
												</c:when>
												<c:otherwise>
													<c:url var="action" value="/admin/product/save"/>
												</c:otherwise>
											</c:choose>
											<form action="${action}" method="post" onsubmit="return ValidateForm(this);" enctype="multipart/form-data">
											<div id="dropzone1" class="pro-ad">
											
													<p style="color: green" align="center">${successMessage}</p>
													<p style="color: red" align="center">${deletesuccessmessage}</p>
													<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															</div>
															<div class="form-group">															
															<select name="category" id="category"
																class="form-control">
																<option value="">-Select category-</option>
																<c:forEach var="options" items="${categoryCodeList}" varStatus="status">
																	<option value="${options.code}" ${options.code == productCode.category.code ? 'selected' : ''}>${options.description}</option>
																</c:forEach>
															</select>
														</div>
														<c:choose>
														<c:when test="${not empty productCode}">
															<div class="form-group">
																<input name="code" type="text" class="form-control"
																	placeholder="Product Code" value="${productCode.code}" readonly>
															</div>
														</c:when>
														
														<c:otherwise>
															<div class="form-group">
																<input name="code" type="text" class="form-control"
																	placeholder="Product Code" value="${productCode.code}" required>
															</div>
														</c:otherwise>	
														</c:choose>
															<div class="form-group">
																<input name="prodDesc" type="text" class="form-control"
																	placeholder="Product Description" value="${productCode.prodDesc}" required>
															</div>
															
															<div class="form-group">
																<input name="quantity" type="number" class="form-control"
																	placeholder="Quantity" value="${productCode.quantity}" required>
															</div>
															
															<div class="form-group">
																<input name="bvPrice" type="number" class="form-control"
																	placeholder="BV" value="${productCode.bvPrice}" required>
															</div>
															
															<div class="form-group">
																<input name="price" type="text" class="form-control"
																	placeholder="Price Per-Item" value="${productCode.price}" required>
															</div>
															
															<div class="form-group">
																<input name="shippingCharge" type="text" class="form-control"
																	placeholder="Shipping Charge" value="${productCode.shippingCharge}" required>
															</div>
															
															<c:choose>
															<c:when test="${not empty productCode.code}">
															<div class="form-group">
																<input type="hidden" name="base64Image" value="${productCode.base64Image}">
																<img alt="img" src="data:image/jpeg;base64,${productCode.base64Image}" style="width: 100px;height: 100px;"/>
															</div>
															</c:when>
															</c:choose>
															<div class="form-group">
																<input class="btn btn-primary" type="file" name="image" />
															</div>
															
															
													</div>
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
															
															<c:choose>
															<c:when test="${not empty productCode.code}">
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

