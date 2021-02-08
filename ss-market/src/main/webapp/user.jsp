<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">

function getSponserName() {
    	$.ajax({
            url: "/get/sponser",
            data: {
                "sponserId": document.getElementById("referedby").value
            },
            type: "get",
            cache: false,
            success: function (data) {

            	if(data.length>0) {
                	var s = document.getElementById("sponsername"); s.value = data;
                	$("#errmsg").text("");
                }else{
                	$("#errmsg").text("Invalid Sponsor Id");
                	$("span").css("color", "red");
                    document.getElementById("errmsg").style.visibility = "visible";
                    document.getElementById("sponsername").value='';
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('ERROR:' + XMLHttpRequest.status + ', status text: ' + XMLHttpRequest.statusText);
            }
        });
}
function enableTermsAndCond(){
	var checker = document.getElementById('terms').checked;
	var prichecker = document.getElementById('pripolicy').checked;
	var bt = document.getElementById('btSubmit');
	if(checker && prichecker){
		bt.disabled = false;
	} else {
		bt.disabled = true;
	}
}
</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-1 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<li class="active"><a href="">Member Registration</a></li>
					</ul>
				 <div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/landingPage.jsp" type="submit" name="submit"
							value="adminListing">Back</a>
					</div>

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<form action="/register" method="post">

											<p style="color: red" align="center">${errormsg}</p>

											<input type="hidden" name="id" id="id" value="${member.id}">
											<input type="hidden" name="role" id="role" value="MEMBER">

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${deletesuccessmessage}</p>
												
												<li class="active"><a href="">Sponsor Details:</a></li>
												
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
													<c:choose>
													<c:when test="${not empty member}">
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}" readonly>
													</div>
													</c:when>
													<c:otherwise>
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}">
													</div>
													</c:otherwise>
													</c:choose>
													<span id="errmsg"></span>
													</div>
												<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
												<div class="form-group">
																	<input name="sponsername" id="sponsername" type="text"
																		class="form-control" placeholder="Sponsor Name"
																		value="${SPONSERNAME}" readonly>
												</div>
												</div>
												</div>
												<li class="active"><a href="">User Details:</a></li>
												

												<div class="well row">
														<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														<div class="form-group">
															<input name="name" type="text" class="form-control"
																placeholder="As Pan Card Name" value="${member.name}"
																required>
														</div>

														<div class="form-group">
															<input name="password" type="password" 
																class="form-control" placeholder="Password" minlength="8"  maxlength="10"
																value="${member.password}" required>
														</div>
														<div class="form-group">
															<input name="email" type="email" class="form-control"
																placeholder="Email" value="${member.email}">
														</div>
													<br>
													</div>
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group">
															<label>Date of Birth:</label> <input name="dob"
																type="date" class="form-control"
																placeholder="Date of Birth" value="${member.dob}"
																required>
														</div>

														<div class="form-group">
															<label>Gender :</label> <input name="gender"
																class=" required " id="gender" type="radio" value="Male"
																${member.gender eq 'Male' ?'Checked':''}>Male <input
																name="gender" class=" required " id="gender"
																type="radio" value="Female"
																${member.gender eq 'Female' ?'Checked':''}>Female
														</div>

														<div class="form-group">
															<input name="phonenumber" type="text"
																class="form-control" placeholder="Phone Number"
																value="${member.phonenumber}" required>
														</div>

													</div>
														</div>
													<%-- <div class="form-group">
																<input name="password" type="password"
																	class="form-control" placeholder="Password"
																	value="${user.password}" required>
															</div>
															<div class="form-group">
																<input name="password2" type="password"
																	class="form-control" placeholder="Confirm Password"
																	required>
															</div> --%>
											<div class="text-center">
													<div class="form-group">
														<input type="checkbox" name="pripolicy" id="pripolicy"
															value="Privacy Policy" onclick="enableTermsAndCond();">
														"I have read and agree to the following" <a
															target="_blank" href="/memberTermsCond.jsp"
															rel="nofollow noopener ugc">Privacy Policy</a>
													</div>
													<div class="form-group">
														<input type="checkbox" name="terms" id="terms"
															value="Conditions" onclick="enableTermsAndCond();">
														"I have read and agree to the following" <a
															target="_blank" href="/memberTermsCond.jsp"
															rel="nofollow noopener ugc">Terms and conditions</a>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="submit" name="submit" id="btSubmit" value="register" disabled>Submit</button>
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

