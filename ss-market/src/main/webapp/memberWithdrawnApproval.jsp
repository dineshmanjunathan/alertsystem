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
								<center><li class="active"><a href="">Approve Member Withdrawn Transaction</a></li></center>
							</ul>

							<div id="myTabContent" class="tab-content custom-product-edit">
								<div class="product-tab-list tab-pane fade active in"
									id="description">
									<div class="row">
									<table class="full-right">
									<tr>
									<td>
										<a href="/admin/menu"
											class="rmk btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-12">
											<span><i class="fa fa-arrow-left"></i> <span>Back to Main</span>
										</span>
										</a>
									</td>
									</tr> 
									</table>
									<p style="color: green" align="center">${successMessage}</p>
								    <p style="color: red" align="center">${errorMessage}</p>
									<%-- <p style="color: red" align="center">${errormsg}</p> --%>
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
											<th data-field="memberid" data-editable="false">Member Id</th>
											<th data-field="withdrawnPoint" data-editable="false">Withdrawn Point</th>
											<th data-field="amount" data-editable="false">Amount</th>
											<th data-field="deduction" data-editable="false">Deduction</th>
											<th data-field="status" data-editable="false">Status</th>
											<th data-field="action" data-editable="false">Action</th>
										</tr>
									</thead>
                                        <tbody> 
                                        <c:forEach var="withdrawnPointHistoryDetails" items="${withdrawnPointsHistoryList}" varStatus="status">
                                            <tr>
												<td>${withdrawnPointHistoryDetails.memberid}</td>
												<td>${withdrawnPointHistoryDetails.point}</td>
												<td>${withdrawnPointHistoryDetails.amount}</td>  
												<td>${withdrawnPointHistoryDetails.deduction}</td>		
												<td>${withdrawnPointHistoryDetails.updatedOb}</td>
												<td>${withdrawnPointHistoryDetails.status}</td>
												
												 <td>
												  <a href="<c:url value='/admin/withdrawn/approve?id=${withdrawnPointHistoryDetails.id}' />">
												<button class="rmk btn btn-primary" type="button">
													<i class="fa fa-shopping-cart"> </i> Approve
												</button>
												  </a>		</td>	   
													     	
												 					 
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