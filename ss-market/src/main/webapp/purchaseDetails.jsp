<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "header.jsp" %> 
<meta charset="ISO-8859-1"> 
</head>
<body> 
				<div class="col-md-10 col-md-offset-2 row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="product-payment-inner-st">
							<ul id="myTabedu1" class="tab-review-design">
								<center><li class="active"><a href="">Purchase List</a></li></center>
							</ul>

							<div id="myTabContent" class="tab-content custom-product-edit">
								<div class="product-tab-list tab-pane fade active in"
									id="description">
									<div class="row">
									<table class="full-right">
									<tr>
									<td>
										<a href="/menu"
											class="btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-12">
											<span><i class="fa fa-arrow-left"></i> <span>Back to Main</span>
										</span>
										</a>
									</td>
									</tr> 
									</table>
										 <div class="sparkline13-graph">
                                <div class="datatable-dashv1-list custom-datatable-overright">
                                    <div id="toolbar">
                                        <select class="form-control dt-tb">
											<option value="">Export Basic</option>
											<option value="all">Export All</option>
											<option value="selected">Export Selected</option>
										</select>
                                    </div>
                                     <table id="table" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="true" data-show-pagination-switch="true" data-show-refresh="false" data-key-events="true" data-show-toggle="true" data-resizable="true" data-cookie="true"
                                        data-cookie-id-table="saveId" data-show-export="true" data-click-to-select="true" data-toolbar="#toolbar">
                                     <thead>
										<tr> 
											<th data-field="orderNumber" data-editable="false">Order Number</th>
											<th data-field="category" data-editable="false">Category</th>
											<th data-field="product" data-editable="false">Product</th>
											<th data-field="qty" data-editable="false">Quantity</th>
											<th data-field="price" data-editable="false">Price</th>
											<th data-field="total" data-editable="false">Total</th>
											<th data-field="purchasedOn" data-editable="false">Purchased On</th>
										</tr>
									</thead>
                                        <tbody> 
                                        <c:set var="cartTotal" value="${0}" />
                                        <c:forEach var="details" items="${purchaseList}" varStatus="status">
                                            <tr>
												<td>${details.orderNumber}</td>
												<td>${details.product.category.description} [${details.product.category.code}]</td>
												<td>${details.product.prodDesc} [${details.product.code}]</td>												
												<td>${details.quantity}</td>
												<td>${details.amount}</td>
												<c:set var="total" value="${details.amount * details.quantity}" />
												<td>${total}</td>
												<c:set var="cartTotal" value="${cartTotal + total}" />  
												<td>${details.purchasedOn}</td>		
		        								 
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
	  						 
		   
		   
</body>
</html>