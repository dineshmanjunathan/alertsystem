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

.left-menu {
	color: white !important;
    padding: 5px 15px 5px 10px !important;
    border-color: #007bff;
    background-image: linear-gradient(#0f68b4,#4f5880);
    border: 1px solid #007bff;
}

 .rmk{
 background-image: linear-gradient(#0f68b4,#4f5880) !important;
 color:#fff !important;
} 

/* body {
    position: relative;
    overflow-x: hidden;
}

body, html {
    height: 100%;
} */

.nav .open > a {
    background-color: transparent;
}

    .nav .open > a:hover {
        background-color: transparent;
    }

    .nav .open > a:focus {
        background-color: transparent;
    }

#wrapper {
    padding-left: 0;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -ms-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

    #wrapper.toggled {
        padding-left: 220px;
    }

        #wrapper.toggled #sidebar-wrapper {
            width: 220px;
        }

        #wrapper.toggled #page-content-wrapper {
            margin-right: -220px;
            position: absolute;
        }

#sidebar-wrapper {
    background: #fff;
    height: 100%;
    left: 220px;
    margin-left: -220px;
    overflow-x: hidden;
    overflow-y: auto;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    -webkit-transition: all 0.5s ease;
    -ms-transition: all 0.5s ease;
    transition: all 0.5s ease;
    width: 0;
    z-index: 1000;
}

    #sidebar-wrapper::-webkit-scrollbar {
        display: none;
    }


#page-content-wrapper {
    /* padding-top: 70px; */
    width: 100%;
}


.sidebar-nav {
    list-style: none;
    margin: 0;
    padding: 0;
    position: absolute;
    top: 0;
    width: 220px;
}

    .sidebar-nav li {
        display: inline-block;
        line-height: 20px;
        position: relative;
        width: 100%;
    }

        .sidebar-nav li:before {
            background-color: #1c1c1c;
            content: '';
            height: 100%;
            left: 0;
            position: absolute;
            top: 0;
            -webkit-transition: width 0.2s ease-in;
            -moz-transition: width 0.2s ease-in;
            -ms-transition: width 0.2s ease-in;
            -o-transition: width 0.2s ease-in;
            transition: width 0.2s ease-in;
            width: 3px;
            z-index: -1;
        }

        .sidebar-nav li:before {
            background-color: #6bb4e7;
        }

        .sidebar-nav li:hover:before {
            -webkit-transition: width 0.2s ease-in;
            -moz-transition: width 0.2s ease-in;
            -ms-transition: width 0.2s ease-in;
            -o-transition: width 0.2s ease-in;
            transition: width 0.2s ease-in;
            width: 100%;
        }

        .sidebar-nav li a {
            color: #fff;
            display: block;
            padding: 10px 15px 10px 30px;
            text-decoration: none;
            background-image:linear-gradient(#0f68b4,#1a1e2c);
        }

        .sidebar-nav li.open:hover before {
            -webkit-transition: width 0.2s ease-in;
            -moz-transition: width 0.2s ease-in;
            -ms-transition: width 0.2s ease-in;
            -o-transition: width 0.2s ease-in;
            transition: width 0.2s ease-in;
            width: 100%;
        }

    .sidebar-nav .dropdown-menu {
        background-color: #222222;
        -ms-border-radius: 0;
        border-radius: 0;
        border: none;
        -webkit-box-shadow: none;
        -ms-box-shadow: none;
        box-shadow: none;
        margin: 0;
        padding: 0;
        position: relative;
        width: 100%;
    }

    .sidebar-nav li a:hover, .sidebar-nav li a:active, .sidebar-nav li a:focus, .sidebar-nav li.open a:hover, .sidebar-nav li.open a:active, .sidebar-nav li.open a:focus {
        background-color: transparent;
        color: #ffffff;
        text-decoration: none;
    }

    .sidebar-nav > .sidebar-brand {
        font-size: 20px;
        height: 65px;
        line-height: 44px;
    }

.hamburger {
    background: transparent;
    border: none;
    display: block;
    height: 32px;
    margin-left: 15px;
    position: fixed;
    top: 20px;
    width: 32px;
    z-index: 999;
}

    .hamburger:hover {
        outline: none;
    }

    .hamburger:focus {
        outline: none;
    }

    .hamburger:active {
        outline: none;
    }

    .hamburger.is-closed:before {
        -webkit-transform: translate3d(0, 0, 0);
        -moz-transform: translate3d(0, 0, 0);
        -ms-transform: translate3d(0, 0, 0);
        -o-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        content: '';
        display: block;
        font-size: 14px;
        line-height: 32px;
        -ms-opacity: 0;
        opacity: 0;
        text-align: center;
        width: 100px;
    }

    .hamburger.is-closed:hover before {
        -webkit-transform: translate3d(-100px, 0, 0);
        -moz-transform: translate3d(-100px, 0, 0);
        -ms-transform: translate3d(-100px, 0, 0);
        -o-transform: translate3d(-100px, 0, 0);
        transform: translate3d(-100px, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        display: block;
        -ms-opacity: 1;
        opacity: 1;
    }

    .hamburger.is-closed:hover .hamb-top {
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        top: 0;
    }

    .hamburger.is-closed:hover .hamb-bottom {
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        bottom: 0;
    }

    .hamburger.is-closed .hamb-top {
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        top: 5px;
    }

    .hamburger.is-closed .hamb-middle {
        margin-top: -2px;
        top: 50%;
    }

    .hamburger.is-closed .hamb-bottom {
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        bottom: 5px;
    }

    .hamburger.is-closed .hamb-top, .hamburger.is-closed .hamb-middle, .hamburger.is-closed .hamb-bottom, .hamburger.is-open .hamb-top, .hamburger.is-open .hamb-middle, .hamburger.is-open .hamb-bottom {
        height: 4px;
        left: 0;
        position: absolute;
        width: 100%;
    }

    .hamburger.is-open .hamb-top {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        transform: rotate(45deg);
        -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -moz-transition: -moz-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -ms-transition: -ms-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -o-transition: -o-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        transition: transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        margin-top: -2px;
        top: 50%;
    }

    .hamburger.is-open .hamb-middle {
         display: none;
    }

    .hamburger.is-open .hamb-bottom {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        -ms-transform: rotate(-45deg);
        -o-transform: rotate(-45deg);
        transform: rotate(-45deg);
        -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -moz-transition: -moz-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -ms-transition: -ms-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        -o-transition: -o-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        transition: transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
        margin-top: -2px;
        top: 50%;
    }

    .hamburger.is-open:before {
        -webkit-transform: translate3d(0, 0, 0);
        -moz-transform: translate3d(0, 0, 0);
        -ms-transform: translate3d(0, 0, 0);
        -o-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        content: '';
        display: block;
        font-size: 14px;
        line-height: 32px;
        -ms-opacity: 0;
        opacity: 0;
        text-align: center;
        width: 100px;
    }

    .hamburger.is-open:hover before {
        -webkit-transform: translate3d(-100px, 0, 0);
        -moz-transform: translate3d(-100px, 0, 0);
        -ms-transform: translate3d(-100px, 0, 0);
        -o-transform: translate3d(-100px, 0, 0);
        transform: translate3d(-100px, 0, 0);
        -webkit-transition: all 0.35s ease-in-out;
        -moz-transition: all 0.35s ease-in-out;
        -ms-transition: all 0.35s ease-in-out;
        -o-transition: all 0.35s ease-in-out;
        transition: all 0.35s ease-in-out;
        display: block;
        -ms-opacity: 1;
        opacity: 1;
    }


.overlay {
    position: fixed;
    display: none;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #000000;
    background-color: rgba(0, 0, 0, 0.3);
    z-index: 1;
}

.hamb-bottom, .hamb-middle, .hamb-top {
    background-color: #fff;
}

</style>

 <script type="text/javascript">

		window.history.forward();
		function noBack() {
			window.history.forward();
		}

		$(document).ready(function () {
		    var trigger = $('.hamburger'),
		        overlay = $('.overlay'),
		       isClosed = true;

		    if (typeof window.orientation !== 'undefined') { 
		    		var element = document.getElementById("headerbtn");
		    	  element.classList.remove("is-open");
		    	  element.classList.add("is-closed");
		    	  isClosed = false;
			     }else{
			    	var element = document.getElementById("wrapper");
			    	  element.classList.add("toggled");
			    	 var element = document.getElementById("headerbtn");
			    	 element.classList.remove("is-closed");
			    	  element.classList.add("is-open");
			    	  isClosed = true;
			    }
		    
		    function buttonSwitch() {

		        if (isClosed === true) {
		           // overlay.hide();
		            trigger.removeClass('is-open');
		            trigger.addClass('is-closed');
		            isClosed = false;
		        } else {
		           // overlay.show();
		            trigger.removeClass('is-closed');
		            trigger.addClass('is-open');
		            isClosed = true;
		        }
		    }

		    trigger.click(function () {
		        buttonSwitch();
		    });

		    $('[data-toggle="offcanvas"]').click(function () {
		        $('#wrapper').toggleClass('toggled');
		    });
		});
		
		
	</script> 
</head>


<nav style="background-image: linear-gradient(#0f68b4,#1a1e2c)" class="navbar navbar-dark bg-primary">

	<div class="header-right-info">
		<ul class="nav navbar-nav mai-top-nav header-right-menu"
			style="padding: 20px 60px;">
			<li class="nav-item"><c:choose>
					<c:when test="${ not empty sessionScope.LOGGED_ON}">
						<a href="#" data-toggle="dropdown" role="button"
							aria-expanded="false" class="nav-link dropdown-toggle"> <span
							class="admin-name">Logged In as -
								&nbsp;${sessionScope.MEMBER_NAME}</span> <i
							class="fa fa-angle-down edu-icon edu-down-arrow"></i>
						</a>
						<ul role="menu"
							class="dropdown-header-top author-log dropdown-menu animated zoomIn">
							<li><a href="/logout"><span
									class="edu-icon edu-locked author-log-ic"></span>Logout</a></li>
						</ul>
					</c:when>
				</c:choose></li>
		</ul>
	</div>
</nav>

<!-- Start Left menu area -->
<c:if test="${not empty sessionScope.LOGGED_ON}">

<div id="wrapper" class="">
   <!--  <div class="overlay"></div> -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <strong><a><img class="main-logo" src="../../img/logo/logo.jpg" alt=""></a></strong>
            </li>
            <br><br> <br><br><br>
        	<c:if test="${fn:contains(sessionScope.ROLE, 'MEMBER')}">
            <li>
                <a href="/menu"> <i class="glyphicon glyphicon-home"></i> HOME </a>
            </li>
            <br><br>
            <li>
                <a href="/wallet"><i class="glyphicon glyphicon-folder-close"></i> My Wallet</a>
            </li><br><br>
            <li>
                <a href="/member/repurchase/wallet"><i class="glyphicon glyphicon-hdd"></i> Re-Purchase Wallet</a>
            </li><br><br>
            <!-- <li>
                <a href="/purchase/review/edit"><i class="glyphicon glyphicon-check"></i> Click to Purchase</a>
            </li><br><br> -->
            <li>
                <a href="/contactus"><i class="glyphicon glyphicon-pushpin"></i> Contact Us</a>
            </li>
             <br><br><br><br>
            <%@ include file="timmer.jsp"%>
            </c:if>
            <c:if test="${fn:contains(sessionScope.ROLE, 'STOCK_POINT')}">
            <li>
                <a href="/stock/point/menu"> <i class="glyphicon glyphicon-home"></i> HOME </a>
            </li>
            <br><br>
            <li>
                <a href="/purchase/review/edit"><i class="glyphicon glyphicon-check"></i> Stock Purchase</a>
            </li><br><br>
            <li>
                <a href="/contactus"><i class="glyphicon glyphicon-pushpin"></i> Contact Us</a>
            </li>
			</c:if>
        </ul>
    </nav>
    <div id="page-content-wrapper">
        <button type="button" id = "headerbtn" class="hamburger animated fadeInLeft is-close" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
       <!--  <div class="container">MENU</div> -->
    </div>
</div>
			
		
</c:if>



<!-- <div class="footer" style="background-image: linear-gradient(#0f68b4,#1a1e2c)"><p>© 2021 Copyright: SS Marketing</p>
</div>
 -->
</html>

