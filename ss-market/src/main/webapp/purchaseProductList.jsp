<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">
$(document).ready(function(){
	
//     $('#category').on('change', function (){
//     	window.location.href = "/purchase/loadProduct/"+$( "#category option:selected" ).val();
//     });
});

let cart = new Map();
let cartTotal =  0;

function addToCart(prodCode, price) {
	let qty = $( "#quantity-"+prodCode+" option:selected" ).val();
	if(!qty){
		alert('Please select quantity.');
		return;
	}
	cart.set(prodCode,qty );
	let total = price *qty; 
	cartTotal = cartTotal + total;
	$('#cartTotal').text(cartTotal);
}

function removeFromCart(prodCode, price) {
	if(confirm("Do you want to remove from cart?")){
		cart.delete(prodCode);
		let total = price *qty; 
		cartTotal = cartTotal - total;
		$("#quantity-"+prodCode+" option:selected").removeAttr("selected");

	}
}

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
							<li class="active"><a href="">Select Products to
									purchase</a></li>
						</center>
					</ul>

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="row">
									<a href="/menu"
										class="btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-2">
										<span><i class="fa fa-arrow-left"></i> <span>Back
												to Main</span> </span>
									</a> 
									<a href="#"
										class="btn btn-waring m-btn m-btn--custom m-btn--icon col-md-offset-5 col-md-2">
										<span> <i class="fa fa-shopping-cart" style="font-size:20px"></i> <span>Purchase Total: &#x20b9; 
										<span id="cartTotal">0</span></span>
									</span>
									</a>
									<a href="#"
										class="btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-0 col-md-1">
										<span> <i class="fa fa-plus"></i> <span>Purchase</span>
									</span>
									</a>
								</div>
<!-- 								<br> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="form-group col-md-4 col-md-offset-4"> -->
<!-- 										<select name="category" id="category" class="form-control"> -->
<!-- 											<option value="">-Select category-</option> -->
<%-- 											<c:forEach var="options" items="${categoryCodeList}" --%>
<%-- 												varStatus="status"> --%>
<%-- 												<option value="${options.code}">${options.description}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<br>
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
													<th data-field="category" data-editable="false">Category
														Code</th>
													<th data-field="prodCode" data-editable="false">Product
														Code</th>
													<th data-field="prodDesc" data-editable="false">Product
														Description</th>
													<th data-field="price" data-editable="false">Price</th>
													<th data-field="quantity" data-editable="false">Quantity</th>
													<th data-field="total">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="details" items="${productList}"
													varStatus="status">
													<tr>
														<%-- <td>${details.id}</td> --%>
														<td>${details.category}</td>
														<td>${details.prodCode}</td>
														<td>${details.prodDesc}</td>
														<td>${details.price}</td>
														<td>
															<div class="form-group">
																<select name="quantity"
																	id="quantity-${details.prodCode}" class="form-control">
																	<option value="">-Select Quantity-</option>
																	<c:forEach begin="1" end="${details.quantity}"
																		varStatus="loop">
																		<option value="${loop.index}">${loop.index}</option>
																	</c:forEach>
																</select>
															</div>
														</td>
														<td>
															<button class="btn-primary" type="button"
																onclick="return addToCart('${details.prodCode}', '${details.price}')">Add
																to Cart</button>
															<button class="btn-danger" type="button"
																onclick="return removeFromCart('${details.prodCode}, '${details.price}')">Remove</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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