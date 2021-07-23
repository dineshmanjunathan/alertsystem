<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssmarket</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- favicon
		============================================ -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<!-- Google Fonts
		============================================ -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,900"
	rel="stylesheet">
<!-- Bootstrap CSS
		============================================ -->
<!-- <link href="../../webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<!-- Bootstrap CSS
		============================================ -->
<link rel="stylesheet" href="../../css/font-awesome.min.css">
<!-- owl.carousel CSS
		============================================ -->
<link rel="stylesheet" href="../../css/owl.carousel.css">
<link rel="stylesheet" href="../../css/owl.theme.css">
<link rel="stylesheet" href="../../css/owl.transitions.css">
<!-- animate CSS
		============================================ -->
<link rel="stylesheet" href="../../css/animate.css">
<!-- normalize CSS
		============================================ -->
<link rel="stylesheet" href="../../css/normalize.css">
<!-- meanmenu icon CSS
		============================================ -->
<link rel="stylesheet" href="../../css/meanmenu.min.css">
<!-- main CSS
		============================================ -->
<link rel="stylesheet" href="../../css/main.css">
<!-- forms CSS
		============================================ -->
<link rel="stylesheet" href="../../css/form/all-type-forms.css">
<!-- educate icon CSS
		============================================ -->
<link rel="stylesheet" href="../../css/educate-custon-icon.css">
<!-- morrisjs CSS
		============================================ -->
<link rel="stylesheet" href="../../css/morrisjs/morris.css">
<!-- mCustomScrollbar CSS
		============================================ -->
<link rel="stylesheet"
	href="../../css/scrollbar/jquery.mCustomScrollbar.min.css">
<!-- metisMenu CSS
		============================================ -->
<link rel="stylesheet" href="../../css/metisMenu/metisMenu.min.css">
<link rel="stylesheet" href="../../css/metisMenu/metisMenu-vertical.css">
<!-- calendar CSS
		============================================ -->
<link rel="stylesheet" href="../../css/calendar/fullcalendar.min.css">
<link rel="stylesheet"
	href="../../css/calendar/fullcalendar.print.min.css">
<!-- x-editor CSS
		============================================ -->
<link rel="stylesheet" href="../../css/editor/select2.css">
<link rel="stylesheet" href="../../css/editor/datetimepicker.css">
<link rel="stylesheet" href="../../css/editor/bootstrap-editable.css">
<link rel="stylesheet" href="../../css/editor/x-editor-style.css">
<!-- normalize CSS
		============================================ -->
<link rel="stylesheet" href="../../css/data-table/bootstrap-table.css">
<link rel="stylesheet"
	href="../../css/data-table/bootstrap-editable.css">
<!-- summernote CSS
		============================================ -->
<link rel="stylesheet" href="../../css/summernote/summernote.css">
<!-- style CSS
		============================================ -->
<link rel="stylesheet" href="../../style.css">
<!-- responsive CSS
		============================================ -->
<link rel="stylesheet" href="../../css/responsive.css">
<!-- modernizr JS
		============================================ -->
<script src="../../js/vendor/modernizr-2.8.3.min.js"></script>

<!-- jquery
		============================================ -->
<script src="../../js/vendor/jquery-1.12.4.min.js"></script>
<!-- bootstrap JS
		============================================ -->
<script src="../../js/bootstrap.min.js"></script>
<!-- wow JS
		============================================ -->
<script src="../../js/wow.min.js"></script>
<!-- price-slider JS
		============================================ -->
<script src="../../js/jquery-price-slider.js"></script>
<!-- meanmenu JS
		============================================ -->
<script src="../../js/jquery.meanmenu.js"></script>
<!-- owl.carousel JS
		============================================ -->
<script src="../../js/owl.carousel.min.js"></script>
<!-- sticky JS
		============================================ -->
<script src="../../js/jquery.sticky.js"></script>
<!-- scrollUp JS
		============================================ -->
<script src="../../js/jquery.scrollUp.min.js"></script>
<!-- mCustomScrollbar JS
		============================================ -->
<script src="../../js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="../../js/scrollbar/mCustomScrollbar-active.js"></script>
<!-- metisMenu JS
		============================================ -->
<script src="../../js/metisMenu/metisMenu.min.js"></script>
<script src="../../js/metisMenu/metisMenu-active.js"></script>
<!-- morrisjs JS
		============================================ -->
<script src="../../js/sparkline/jquery.sparkline.min.js"></script>
<script src="../../js/sparkline/jquery.charts-sparkline.js"></script>
<!-- calendar JS
		============================================ -->
<script src="../../js/calendar/moment.min.js"></script>
<script src="../../js/calendar/fullcalendar.min.js"></script>
<script src="../../js/calendar/fullcalendar-active.js"></script>
<!-- maskedinput JS
		============================================ -->
<script src="../../js/jquery.maskedinput.min.js"></script>
<script src="../../js/masking-active.js"></script>
<!-- datepicker JS
		============================================ -->
<script src="../../js/datepicker/jquery-ui.min.js"></script>
<script src="../../js/datepicker/datepicker-active.js"></script>
<!-- form validate JS
		============================================ -->
<!-- <script src="../../js/form-validation/jquery.form.min.js"></script>
    <script src="../../js/form-validation/jquery.validate.min.js"></script>
    <script src="../../js/form-validation/form-active.js"></script> -->
<!-- dropzone JS
		============================================ -->
<script src="../../js/dropzone/dropzone.js"></script>
<!-- tab JS
		============================================ -->
<!-- data table JS
		============================================ -->
<script src="../../js/data-table/bootstrap-table.js"></script>
<script src="../../js/data-table/tableExport.js"></script>
<script src="../../js/data-table/data-table-active.js"></script>
<script src="../../js/data-table/bootstrap-table-editable.js"></script>
<script src="../../js/data-table/bootstrap-editable.js"></script>
<script src="../../js/data-table/bootstrap-table-resizable.js"></script>
<script src="../../js/data-table/colResizable-1.5.source.js"></script>
<script src="../../js/data-table/bootstrap-table-export.js"></script>
<!--  editable JS
		============================================ -->
<script src="../../js/editable/jquery.mockjax.js"></script>
<script src="../../js/editable/mock-active.js"></script>
<script src="../../js/editable/select2.js"></script>
<script src="../../js/editable/moment.min.js"></script>
<script src="../../js/editable/bootstrap-datetimepicker.js"></script>
<script src="../../js/editable/bootstrap-editable.js"></script>
<script src="../../js/editable/xediable-active.js"></script>
<!-- Chart JS
		============================================ -->
<script src="../../js/chart/jquery.peity.min.js"></script>
<script src="../../js/peity/peity-active.js"></script>
<!-- summernote JS
		============================================ -->
<script src="../js/summernote/summernote.min.js"></script>
<script src="../js/summernote/summernote-active.js"></script>

<script src="../../js/tab.js"></script>
<!-- plugins JS
		============================================ -->
<script src="../../js/plugins.js"></script>
<!-- main JS
		============================================ -->
<script src="../../js/main.js"></script>
<!-- tawk chat JS
		============================================ -->

<style>
.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #337ab7;
	color: white;
	text-align: center;
}
</style>

<nav style="background-image: linear-gradient(#0f68b4,#1a1e2c)" class="navbar navbar-dark bg-primary">

	<div class="header-right-info">
		<ul class="nav navbar-nav mai-top-nav header-right-menu" style="padding: 20px 60px;">
			<li class="nav-item">
					
				</li>
		</ul>
	</div>
</nav>

<div class="footer"><p>© 2021 Copyright: SS Marketing</p>
</div>

</html>

