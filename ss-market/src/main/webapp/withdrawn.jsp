<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

function review() {
	var walletBalance = $('#walletBalance').val();
	var walletWithdrawn = $('#walletWithdrawn').val();
	window.location.href = "/withdrawn/deduction/compute?walletBalance="+walletBalance+"&walletWithdrawn="+walletWithdrawn;
}
function paytypeFunction(value)
{
    if(value=="NEFT"){
    	document.getElementById("GPAY").style.display = "none";
    	document.getElementById("NEFT").style.display = "block";
    	accountNumber.attributes.required = "required";
    	accHolderName.attributes.required = "required";
    	sIFSCCode.attributes.required = "required";
    	document.getElementById("accountNumber").required = true;
    	document.getElementById("accHolderName").required = true;
    	document.getElementById("sIFSCCode").required = true;
    	document.getElementById("phonenumber").required = false;
    }  else if(value=="GPAY"){
    	document.getElementById("NEFT").style.display = "none";
		document.getElementById("GPAY").style.display = "block";
		document.getElementById("accountNumber").required = false;
    	document.getElementById("accHolderName").required = false;
    	document.getElementById("sIFSCCode").required = false;
    	document.getElementById("phonenumber").required = true;
    }  else if(value=="PPAY"){
    	document.getElementById("NEFT").style.display = "none";
		document.getElementById("GPAY").style.display = "block";
		document.getElementById("accountNumber").required = false;
    	document.getElementById("accHolderName").required = false;
    	document.getElementById("sIFSCCode").required = false;
    	document.getElementById("phonenumber").required = true;
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
						<center><ul id="myTabedu1" class="tab-review-design">
							<li class="active"><a href="">WITHDRAWN ENTRY</a></li>
						</ul></center>
						<!-- <form action="/userlisting" method="get"> -->
						<div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/wallet" type="submit" name="submit"
							value="adminListing">Back</a>
						</div>
						<!-- </form> -->
						<p style="color: red" align="center">${errormsg}</p>
						
						<div id="myTabContent" class="tab-content custom-product-edit">
							<div class="product-tab-list tab-pane fade active in"
								id="description">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding-right: 21%; padding-left: 21%;">
										<div class="review-content-section">
										
											<form action="/withdrawn" method="post">
											
											
											<input type="hidden" name="memberid" id="memberid" value="${member.id}">
									
											<div id="dropzone1" class="pro-ad">
											
													<div class="row">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group">
															<label>Available Balance:</label>
																<input name="walletBalance" id="walletBalance" type="text" class="form-control"
																	placeholder="" value="${member.walletBalance}" readonly>
															</div>
														<c:choose>
														<c:when test="${not empty DEBIT}">
														<div class="form-group">
															<input name="walletWithdrawn" id="walletWithdrawn" type="number" class="form-control"
																placeholder="Add Points to Withdrawn" value="${member.walletWithdrawn}" required>
														</div>
														</c:when>
														<c:otherwise>
														<div class="form-group">
															<input name="walletWithdrawn" id="walletWithdrawn" type="number" class="form-control"
																placeholder="Add Points to Withdrawn" required>
														</div>
														</c:otherwise>
														</c:choose>
														<div class="payment-adress">
															<a
																class="btn btn-success col-md-offset-9 col-md-3"
																onclick="return review();" type="button">Check for Charges </a>
														</div>
														
														<c:choose>
														<c:when test="${not empty DEBIT}">
														<div class="form-group">
														<label style="font-size: 15px;">Deduction: ${DEBIT} </label>
														</div>
														<div class="form-group">
														<label style="font-size: 15px;">Point to withdrawn: ${WITHDRAWN_POINT}</label>
														</div>
														</c:when>
														<c:otherwise>
														<div class="form-group"></div>
														<br>
														</c:otherwise>
														</c:choose>	
														<div class="form-group">
																<select name="paymentType" id="paymentType" onmousedown="this.value='';" onchange="paytypeFunction(this.value);" class="form-control" required>
																	<option value="">-Select Payment type-</option>
																	<option value="NEFT">NEFT/IMPS</option>
																	<option value="PPAY">PhonePay</option>
																	<option value="GPAY">GPay</option>
																</select>
														</div>
														</div>
													</div>
													
													
													<div class="row" id="NEFT" style="display: none;">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">
															
														
														<div class="form-group">
														<label>Payment Details:</label>
															<input name="accountNumber" id="accountNumber" type="number" class="form-control"
																placeholder="Account Number" >
														</div>
														
														<div class="form-group">
															<input name="accHolderName" id="accHolderName" type="text" class="form-control"
																placeholder="Account Holder Name" >
														</div>
														
														<div class="form-group">
															<input name="sIFSCCode" id="sIFSCCode" type="text" class="form-control"
																placeholder="IFSC CODE"  >
														</div>
															
														</div>
															
													</div>
													
													<div class="row" id="GPAY" style="display: none;">
														<div class=" well col-lg-12 col-md-12 col-sm-12 col-xs-12">				
														<div class="form-group">
														<label>Payment Details:</label>
															<input name="phonenumber" id="phonenumber" type="text" class="form-control"
																placeholder="Mobile Number" >
														</div>
															
														</div>
															
													</div>
													
													</div>
													<div class="row">
														<div class="col-lg-12">
															<div class="payment-adress">
																<button class="rmk btn btn-primary waves-effect waves-light"
																	onclick="return confirm('Are you sure you want to submit?')" type="submit" name="submit" value="walletWithdrawn">Submit</button>

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

