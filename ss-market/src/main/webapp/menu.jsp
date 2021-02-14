<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
</head>
<div class="col-md-10 col-md-offset-2 well row">
	<h4>Member Details</h4>
	</br>
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="<c:url value='/user/edit?user_id=${sessionScope.MEMBER_ID}' />">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Member Profile</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>

	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/member/tree">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Member Tree</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
	
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/member/stat">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Member Statistics</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
	
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/member/kycdetails">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Member KYC Details</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
</div>

<div class="col-md-10 col-md-offset-2 well row">
	<h4>Transaction Management</h4>
	<br>
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/purchase/detail">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Purchase Details</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>	

</div>

</html>
