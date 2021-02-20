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

	
let cartTotal =  ${cartTotal == null ? 0.0:cartTotal};
let cartMap = new Map();

function addToCart(prodCode, price, quantity) {
	let cartQuantity = $("#cartQuantity_"+prodCode).val();
	if(cartQuantity<=0){
		alert('Please Enter quantity.');
		return false;
	}
	if( quantity < cartQuantity){
		alert("Invalid quantity.");
		$("#cartQuantity_" + prodCode).val("");
		return false;
	}
	
	$.ajax({
	    url: "/purchase/addToCart/stock",
	    data: {
	        "prodCode": prodCode,
	        "qty" :cartQuantity
	    },
	    type: "post",
	    cache: false,
	    success: function (data) {
	    	if(data){
	    		$('#cartTotal').text(data);
	    	}
	    },
	    error: function (XMLHttpRequest, textStatus, errorThrown) {
	        alert('Unable to add product to cart!');
	    }
	});

}

function removeFromCart(prodCode, price) {
	let cartQuantity = $("#cartQuantity_"+prodCode).val();
	if (cartQuantity<=0) {
		alert('Please Enter Quantity.');
		return;
	}
	if(confirm("Do you want to remove from cart?")) {
		$.ajax({
		    url: "/purchase/remove/cart",
		    data: {
		        "prodCode": prodCode
		    },
		    type: "post",
		    cache: false,
		    success: function (data) {
		    	if (data && data !== 'null') {
		    		$('#cartTotal').text(data);
		    	} else {
		    		$('#cartTotal').text(0.0);
		    	}
		    	$("#cartQuantity_" + prodCode).val("");
		    },
		    error: function (XMLHttpRequest, textStatus, errorThrown) {
		    	$("#cartQuantity_" + prodCode).val("");
		    }
		});
	}

}

function review() {
	let total = $('#cartTotal').text();
	if(!total || total <=0 || total == '0.0' || total == '0'){
		alert('Select product to purchase!');
	} else {
		window.location.href = "/purchase/manual/review";
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
							<li class="active"><a href="">Select products to add manual purchase</a></li>
						</center>
					</ul>

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="row">
								<c:set var="url" value="#"></c:set>
								<c:choose>
								<c:when test="${sessionScope.ROLE == 'ADMIN' }">
									<c:set var="url" value="/admin/menu"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="url" value="/stock/point/menu"></c:set>
								</c:otherwise>
								</c:choose>
									<a href="${url}"
										class="rmk btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-2">
										<span><i class="fa fa-arrow-left"></i> <span>Back
												to Main</span> </span>
									</a> <a href="#"
										class="btn btn-waring m-btn m-btn--custom m-btn--icon col-md-offset-5 col-md-2">
										<span> <i class="fa fa-shopping-cart"
											style="font-size: 20px"></i> <span>Purchase Total:
												&#x20b9; <span id="cartTotal">${cartTotal == null ? 0.0:cartTotal}</span>
										</span>
									</span>
									</a>
									<button class="rmk btn btn-primary col-md-offset-0 col-md-1"
										type="button" onclick="return review();">
										<i class="fa fa-plus"></i> Purchase
									</button>
								</div>
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
													<th data-field="image" data-editable="false">Image</th>
													<th data-field="category" data-editable="false">Category</th>
													<th data-field="code" data-editable="false">Product</th>
													<th data-field="price" data-editable="false">Price</th>
													<th data-field="quantity" data-editable="false">Quantity</th>
													<th data-field="total">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="details" items="${productList}"
													varStatus="status">
													<c:if test="${details.quantity > 0}">
													<tr>
														<td><img alt="img" src="data:image/jpeg;base64,${details.base64Image}" style="width: 100px;height: 100px;"/></td>
														<td>${details.category.description}</td>
														<td>${details.prodDesc}</td>
														<td>${details.price}</td>
														<td>
															<div class="form-group">
																<input name="quantity" id="cartQuantity_${details.code}" type="text" class="form-control"
																	placeholder="Available Qty:${details.quantity}" value="${cartMap[details.code]}" required>
															</div>
														</td>
														<td>
															<button class="btn btn-primary" type="button"
																onclick="return addToCart('${details.code}', '${details.price}',${details.quantity});">
																<i class="fa fa-shopping-cart"></i> Add to Cart
															</button>
															<button class="btn btn-danger" type="button"
																onclick="return removeFromCart('${details.code}', '${details.price}');">
																<i class="fa fa-remove"></i>Remove
															</button>
														</td>
													</tr>
													</c:if>
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