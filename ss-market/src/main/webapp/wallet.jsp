<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "header.jsp" %> 
<meta charset="ISO-8859-1"> 
<!-- <link rel="stylesheet" href="../../css/bootstrap.css"> -->
</head>
<body> 
<form action="/wallet/withdrawn" method="post" onsubmit="return validateForm(this);">
		   <div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<center><li class="active"><a href="">MY WALLET</a></li></center>
					</ul>
					<div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/menu" type="submit" name="submit"
							value="adminListing">Back to Main</a>
					</div>
						<p style="color: green" align="center">${successMessage}</p>
						<p style="color: red" align="center">${errormsg}</p>
					<!-- </form> -->

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row" >
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
									
									
									<input type="hidden" name="id" id="id" value="${userwallet.id}">
									<input type="hidden" name="walletBalance" id="walletBalance" value="${userwallet.walletBalance}">
									<input type="hidden" name="walletWithdrawn" id="walletWithdrawn" value="${userwallet.walletWithdrawn}">
									<input type="hidden" name="repurcahse" id="repurcahse" value="${userwallet.repurcahse}">
									
									
								<div class="well row" style="background-image: linear-gradient(#337ab7,#ffe6c5);">
										
								<div class=" col-lg-4 col-md-offset-4 col-md-4 col-sm-4 col-xs-12">
									  <div class="col-md-14">
									    <div class="row">
									      <div class="col-sm-11 btn btn-info">
									        <div class="thumbnail">
									          <div class="caption text-center" onclick="#">
									            <!-- <div class="position-relative">
									              <img src="https://az818438.vo.msecnd.net/icons/slack.png" style="width:72px;height:72px;" />
									            </div> -->
									            <h4 style="text-align: left;"id="thumbnail-label" style="text-decoration: underline;">TOTAL POINTS</h4>
									            <h2 style="text-align: right;"><span class="fa fa-google-wallet">&nbsp;&nbsp;</span>${userwallet.totalbalance}</h2>
									          </div>
									        </div>
									      </div>
									    </div>
									  </div>
									</div>
										</div>
								<div class="well row"style="background-image: linear-gradient(#337ab7,#ffe6c5);">
										<div class=" col-lg-4 col-md-offset-4 col-md-4 col-sm-4 col-xs-12">
									  <div class="col-md-14">
									    <div class="row">
									      <div class="col-sm-11 btn btn-warning">
									        <div class="thumbnail">
									          <div class="caption text-center" onclick="#">
									           <!--  <div class="position-relative">
									              <img src="https://az818438.vo.msecnd.net/icons/slack.png" style="width:72px;height:72px;" />
									            </div> -->
									            <h4 style="text-align: left;"id="thumbnail-label" style="text-decoration: underline;">WITHDRAWN POINTS</h4>
									            <h2 style="text-align: right;"><span class="fa fa-credit-card">&nbsp;&nbsp;</span>${userwallet.walletWithdrawn}</h2>
									          </div>
									        </div>
									      </div>
									    </div>
									  </div>
									</div>
										
									
									</div>
							<div class="well row" style="background-image: linear-gradient(#337ab7,#ffe6c5);">
									<!-- </div>
									<div class="well row"> -->
									
									
									<div class=" col-lg-4 col-md-offset-4 col-md-4 col-sm-4 col-xs-12">
									  <div class="col-md-14">
									    <div class="row">
									      <div class="col-sm-11 btn btn-success">
									        <div class="thumbnail">
									          <div class="caption text-center">
									            <!-- <div class="position-relative">
									              <img src="https://az818438.vo.msecnd.net/icons/slack.png" style="width:72px;height:72px;" />
									            </div> -->
									            <h4 style="text-align: left;"id="thumbnail-label" style="text-decoration: underline;">AVAILABLE POINTS</h4>
									            <h2 style="text-align: right;"><span class="fa fa-codepen">&nbsp;&nbsp;</span>${userwallet.walletBalance}</h2>
									            <button class="btn btn-link" type="submit" name="submit" value="withdrawn">(CLICK TO WITHDRAWN POINTS)</button>
									          </div>
									        </div>
									      </div>
									    </div>
									  </div>
									</div>									
									
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br><br>		
		   </div>
		   </form>
</body>
</html>