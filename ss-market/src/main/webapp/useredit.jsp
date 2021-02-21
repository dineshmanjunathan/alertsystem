<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
</head>
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

function viewpassword() {
    var $pwd = $(".pwd");
    if ($pwd.attr('type') === 'password') {
        $pwd.attr('type', 'text');
    } else {
        $pwd.attr('type', 'password');
    }
}

function adjustrequired(value)
{
    if(value=="MEMBER"){
    	document.getElementById("referedby").required = true;
    }else{
    	document.getElementById("referedby").required = false;
    	$("#errmsg").text("");
    }       
}

</script>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<c:choose>
							<c:when test="${fn:contains(sessionScope.ROLE, 'MEMBER')}">
								<li class="active"><a href="">Member profile</a></li>
							</c:when>
							<c:otherwise>
								<li class="active"><a href="">User</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
					<!-- <form action="/userlisting" method="get"> -->

					<div class="payment-adress">

						<c:choose>
							<c:when test="${fn:contains(sessionScope.ROLE, 'MEMBER')}">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
									href="/menu" type="submit" name="submit" value="adminListing">Back
									to Main</a>
							</c:when>
							<c:when test="${fn:contains(sessionScope.ROLE, 'ADMIN')}">
								<a
									class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
									href="/admin/member/listing" type="submit" name="submit"
									value="adminListing">Back to Main</a>
							</c:when>
						</c:choose>
					</div>
					<!-- </form> -->

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<c:choose>
											<c:when test="${not empty member}">
												<c:url var="action" value="/user/edit" />
											</c:when>
											<c:otherwise>
												<c:url var="action" value="/register" />
											</c:otherwise>
										</c:choose>
										<form action="${action}" method="post"
											onsubmit="return ValidateForm(this);">
											<p style="color: red" align="center">${errormsg}</p>
											<input type="hidden" name="id" id="id" value="${member.id}">
											<input type="hidden" name="walletBalance" id="walletBalance" value="${member.walletBalance}">
											<input type="hidden" name="walletWithdrawn" id="walletWithdrawn" value="${member.walletWithdrawn}">
											<input type="hidden" name="repurcahse" id="repurcahse" value="${member.repurcahse}">
											<input type="hidden" name="referencecode" id="referencecode" value="${member.referencecode}">

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<script type="text/javascript">
													function ValidateForm(frm) {
														if (frm.password1.value != frm.password2.value) {
															alert('Passwords do not match');
															frm.password1
																	.focus();
															return false;
														}
														if (frm.mobile.value.length != 10) {
															alert('Required 10 digits, match requested format!');
															frm.mobile.focus();
															return false;
														}

													}
												</script>
												<li class="active"><a >Sponsor Details:</a></li>
												<c:if test="${fn:contains(sessionScope.ROLE, 'ADMIN')}">
												<span style="color:red;">Note: Sponsor id is not required to create ADMIN and STOCK POINT.</span>
												</c:if>
												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
													<c:choose>
													<c:when test="${not empty member}">
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}" readonly required>
													</div>
													</c:when>
													<c:otherwise>
													<div class="form-group">
																	<input name="referedby" id="referedby" type="text" onblur="getSponserName();"
																		class="form-control" placeholder="Sponsor Id"
																		value="${member.referedby}" required>
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
												<li class="active"><a >User Details:</a></li>
													<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														<div class="form-group">
															<input name="name" type="text" class="form-control"
																placeholder="Member Name" value="${member.name}"
																${not empty member.name? 'readonly':''} required>
														</div>
														<div class="input-group">
															<input name="password" type="password" class="form-control pwd" placeholder="Password" minlength="8"  maxlength="10"
																value="${member.password}" required>
																 <span class="input-group-btn">
												            <button class="btn btn-default reveal" type="button" onclick="viewpassword();"><i class="glyphicon glyphicon-eye-open"></i></button>
												          </span>  
														</div><br>
													<c:choose>
															<c:when test="${fn:contains(sessionScope.ROLE, 'ADMIN')}">

																<div class="form-group">
																	<select name="role" id="role" class="form-control" onchange="adjustrequired(this.value);">
																		<option value="">-Select Role-</option>
																		<option value="ADMIN"
																			${member.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
																		<option value="MEMBER"
																			${member.role == 'MEMBER' ? 'selected' : ''}>MEMBER</option>
																		<option value="STOCK_POINT"
																			${member.role == 'STOCK_POINT' ? 'selected' : ''}>STOCK
																			POINT</option>
																	</select>
																</div>
															</c:when>
															<c:otherwise>
																<input type="hidden" name="role" id="role"
																	value="${member.role}">
															</c:otherwise>
														</c:choose>
														
														<div class="form-group">
															<input name="email" type="email" class="form-control"
																placeholder="Email" value="${member.email}">
														</div>
														
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
																value="${member.phonenumber}" ${not empty member.phonenumber ? 'readonly':''} required>
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
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">

														<c:choose>
															<c:when test="${not empty member.id}">
																<button class="rmk btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Save Changes</button>
															</c:when>
															<c:otherwise>
																<button class="rmk btn btn-primary waves-effect waves-light"
																	type="submit" name="submit" value="register">Create</button>
															</c:otherwise>
														</c:choose>
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

