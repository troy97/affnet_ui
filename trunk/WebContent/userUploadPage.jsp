<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 3.3.1
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Upload</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="icon" href="assets/admin/layout/img/favicon.ico" type="image/x-icon">
</head>

<div class="page-container">
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<div class="page-logo">
				<a href="index.html">
					<img src="assets/admin/layout/img/beta-logo.png" alt="logo" class="logo-default"/>
				</a>
			</div>
			<div class="sidebar-logout-wrapper">
				<span class="welcome">Welcome <span class="user-name">${name}</span></span>
				<a href="${logoutPage}">
					<i class="icon-logout"></i>
				</a>
			</div>
			<div class="language-switcher">
				<a href="javascript:;">
					<i class="icon-eng"></i>
				</a>
				<a href="javascript:;">
					<i class="icon-ru"></i>
				</a>
			</div>
			<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="start active open">
					<a href="javascript:;">
					<i class="icon-graph"></i>
					<span class="title">Statistics</span>
					<span class="selected"></span>
					<span class="arrow open"></span>
					</a>
					<ul class="sub-menu">
						<li class="active">
							<a href="${dashPage}">
							<i class="fa fa-eye"></i>
							Overview</a>
						</li>
						<li>
							<a href="index_2.html">
							<i class="icon-basket"></i>
							Orders</a>
						</li>
						<li>
							<a href="index_3.html">
							<i class=" fa fa-hand-o-up"></i>
							Clicks</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="fa fa-exclamation"></i>
					<span class="title">Notifications</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ecommerce_index.html">
							<i class="icon-envelope"></i>
							Unread</a>
						</li>
						<li>
							<a href="ecommerce_orders.html">
							<i class="fa fa-file-archive-o"></i>
							Archive</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-docs"></i>
					<span class="title">Accounting</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="layout_horizontal_sidebar_menu.html">
								<i class="glyphicon glyphicon-book"></i>
							History</a>
						</li>
						<li>
							<a href="index_horizontal_menu.html">
								<i class="glyphicon glyphicon-plus-sign"></i>
							Add money</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-link"></i>
					<span class="title">Integration</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="layout_horizontal_sidebar_menu.html">
							<i class="icon-handbag"></i>
							Upload products</a>
						</li>
						<li>
							<a href="index_horizontal_menu.html">
							<i class="icon-tag"></i>
							Upload orders</a>
						</li>
						<li>
							<a href="index_horizontal_menu.html">
							<i class="glyphicon glyphicon-refresh"></i>
							Synchronization</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title">Settings</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="layout_horizontal_sidebar_menu.html">
							<i class="icon-user"></i>
							Profile info</a>
						</li>
						<li>
							<a href="index_horizontal_menu.html">
							<i class="icon-envelope-open"></i>
							E-mail notifications</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-book-open"></i>
					<span class="title">Merchant documentation</span>
					</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="page-content-wrapper">
		<div class="page-content">
			<h3 class="page-title">
			Dashboard <small>reports & statistics</small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="index.html">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">Dashboard</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="upload-block">
						<h1>Upload your price-list file:</h1>
						<p class="text-error">
							<font color="red"> ${badFileFormat} </font>
						</p>
						<p>
							<form action="${downloadPage}" enctype="multipart/form-data" method="post" id="uploadform">
								Choose file to upload (only .csv and .yml formats accepted)
								<input type="hidden" name="webshopname" value="${shopId}">  <!-- ######################## DONT FORGET TO ADD THIS ATTR ####################################### -->
								<input type="file" name="datafile" size="40">
								<br/>
								<button class="btn btn-lg btn-primary" type="submit"><span class="glyphicon glyphicon-open"></span> Upload</button>
							</form>
						</p>
					</div>
				</div>
								
			</div>
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								Last 10 files You have uploaded
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										Name
									</th>
									<th>
										Products count
									</th>
									<th>
										Upload time
									</th>
									<th>
										Size in bytes
									</th>
									<th>
										Validity
									</th>
									<th>
										Activity
									</th>
									<th>
										Validation Message
									</th>
								</tr>
								</thead>
								<tbody>
        <c:forEach items="${fileList}" var="file">
			<tr>
				<td>${file.name}</td>
				<td>${file.productsCount}</td>
				<!-- conver long ${file.uploadTime} to date ${dateValue} -->
					<jsp:useBean id="dateValue" class="java.util.Date"/>
					<jsp:setProperty name="dateValue" property="time" value="${file.uploadTime}"/>
				<!--  	<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/>  -->
				<td><fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy HH:mm"/></td>
				<td>${file.size}</td>
				<td><c:if test="${file.valid}">OK</c:if><c:if test="${not file.valid}">invalid</c:if></td>
				<td><c:if test="${file.active}">Active</c:if><c:if test="${not file.active}">inactive</c:if></td>
				<td>${file.validationMessage}</td>
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
</div>
<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script src="assets/admin/pages/scripts/charts-flotcharts.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
   Demo.init(); // init demo features 
   Index.init();   
   Index.initDashboardDaterange();
   Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
});
</script>
<script src="assets/global/plugins/flot/jquery.flot.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.resize.js" type="text/javascript"></script>      
<script>
jQuery(document).ready(function() {       
   ChartsFlotcharts.init();
   ChartsFlotcharts.initCharts();
   ChartsFlotcharts.initPieCharts();
   ChartsFlotcharts.initBarCharts();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
