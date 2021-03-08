<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <%@ include file = "header.jsp" %> 
</head>

	<div class="row text-center">
			<div class="text-center m-b-md custom-login">
				<a href="/"><img class="main-logo" src="../../img/logo/logo.jpg" alt="" /></a><br/><br/>
			</div>
			<div class="well col-md-4 col-md-offset-4">
			<!-- <h3 >SS MARKET</h3>  -->
		<c:choose>
			<c:when test="${ROLE == 'STOCK_POINT'}">
				<c:set var="url" scope="session" value="/stock/point/login" />
				<h3 >Stock Point Login</h3>
			</c:when>
			<c:otherwise>
				<c:set var="url" scope="session" value="/admin/login" />
				<h3 >Admin Login</h3>
			</c:otherwise>
		</c:choose>
		<form action="${url}" method="post">
                      	<p style="color:red" align="center">${errormsg}</p>  
                      	<p style="color:green" align="center">${adminlogout}</p>
                      	<p style="color:green" align="center">${registersuccess}</p> 
                      	<input type="hidden" name="role" value="${ROLE}">
                     <div class="row">
                         <div class="col-md-12">
                             <div class="form-group">
                                 <input type="text" placeholder="SS************" title="Please enter you username" 
                                 required="Enter user name" value="" name="id" id="id" class="form-control">
                             </div>
                         </div>
                     </div>
					<div class="row">
                         <div class="col-md-12">
                             <div class="form-group">
                                 <input type="password" title="Please enter your password" placeholder="******"  value="" name="password" id="password-field" class="form-control" required>
                             </div>
                         </div>
                     </div> 
                     <div class="row">
                     	 <div class="col-md-3"></div>
                         <div class="col-md-6">
                              <button class="rmk btn btn-success btn-block loginbtn" type="submit" name="submit" value="login">Login</button>
                              <c:choose>
                              <c:when test="${ROLE == 'STOCK_POINT'}">
									<a class="rmk btn btn-success btn-block loginbtn" href="/index.jsp">Back</a>
							</c:when>
                            </c:choose>
                         </div>
                         <div class="col-md-3"></div>
                     </div>
                 </form> 
             </div>
    </div>
   
</html>