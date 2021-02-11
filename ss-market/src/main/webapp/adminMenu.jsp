<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<style type="text/css">

.h5_color{
 color:#000
}
</style>

</head>
<body >
<div class="col-md-10 col-md-offset-2 well row">
	<h4>User Status</h4>
	</br>
	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 btn btn-info" style="padding: inherit;">
		<a href="<c:url value='#' />">
			<div class="panel-body" style="padding-bottom: 15%;">
				<div class="stats-title" style="text-align: center;">
					<h5 class="h5_color">TOTAL MEMBER</h5>
				</div>
				<div class="stats-icon" style="text-align: center;">
					<i class="educate-icon">${TOTAL_MEMBER}</i>
				</div>
			</div>
		</a>
	</div>
	<div style="width: 2%;float: left; padding: inherit;"></div>
		<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 btn btn-success" style="padding: inherit;">
		<a href="<c:url value='#' />">
			<div class="panel-body" style="padding-bottom: 15%;">
				<div class="stats-title" style="text-align: center;">
					<h5 class="h5_color">ACTIVE MEMBER</h5>
				</div>
				<div class="stats-icon" style="text-align: center;">
					<i class="educate-icon">${ACTIVE_MEMBER}</i>
				</div>
			</div>
		</a>
	</div>
	<div style="width: 2%;float: left; padding: inherit;"></div>
	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 btn btn-warning" style="padding: inherit;">
		<a href="<c:url value='#' />">
			<div class="panel-body" style="padding-bottom: 15%;">
				<div class="stats-title" style="text-align: center;">
					<h5 class="h5_color">INACTIVE MEMBER</h5>
				</div>
				<div class="stats-icon" style="text-align: center;">
				<i class="educate-icon" style="text-align: center;">${INACTIVE_MEMBER}</i>
				</div>
			</div>
		</a>
	</div>
	<div style="width: 2%;float: left; padding: inherit;"></div>
	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 btn btn-primary" style="padding: inherit;">
		<a href="<c:url value='#' />">
			<div class="panel-body" style="padding-bottom: 15%;">
				<div class="stats-title" style="text-align: center;">
					<h5 class="h5_color">DAILY REG COUNT</h5>
				</div>
				<div class="stats-icon" style="text-align: center;">
					<i class="educate-icon">${TOTAY_MEMBER}</i>
				</div>
			</div>
		</a>
	</div>
	<div style="width: 2%;float: left; padding: inherit;"></div>
	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12 btn btn-danger"  style="padding: inherit;">
		<a href="<c:url value='#' />">
			<div class="panel-body" style="padding-bottom: 15%;">
				<div class="stats-title" style="text-align: center;">
					<h5 class="h5_color">STOCK POINT USER</h5>
				</div>
				<div class="stats-icon" style="text-align: center;">
					<i class="educate-icon">${STOCK_MEMBER}</i>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-md-10 col-md-offset-2 well row">
	<h4>Management</h4>
	</br>
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="<c:url value='/admin/member/listing' />">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Manage User</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
	
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="<c:url value='/admin/stockpurchase/listing' />">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Stock Point Sales History</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="<c:url value='/admin/ssconfig/listing' />">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Deduction/Reward configuration</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
</div>
<div class="col-md-10 col-md-offset-2 well row">
	<h4>Product Management</h4>
	</br>
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/admin/categoryCodeListing">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Manage Category</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/admin/productListing">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Manage Product</h5>
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
		<a href="/purchase/allMember/list">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Member Transaction History</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>	
	
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/purchase/allmanual/list">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Stock Point Transaction History</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>	
	
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/purchase/pending/list">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Approve Stock Point Transaction</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>	
	
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/admin/withdrawnPointHistory/list">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Withdrawn History</h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>
	</div>
	<div class="col-md-10 col-md-offset-2 well row">
	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		<a href="/admin/memberWithdrawnApproval/list">
			<div class="panel-body">
				<div class="stats-title pull-left">
					<h5>Approve Member Withdrawn </h5>
				</div>
				<div class="stats-icon pull-right">
					<i class="educate-icon educate-apps"></i>
				</div>
			</div>
		</a>
	</div>

</div>
</body>
</html>
