<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundleBasename}" />
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
<html lang="${language}">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>i-Butler AffiliNet</title>
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
<!-- <link href="assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/> -->
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
				<a href="#">
					<img src="assets/admin/layout/img/beta-logo.png" alt="logo" class="logo-default"/>
				</a>
			</div>
			<div class="sidebar-logout-wrapper">
				<span class="welcome"><fmt:message key="sideBar.welcome" /> <span class="user-name">${name}</span></span>
				<a href="${logoutPage}">
					<i class="icon-logout"></i>
				</a>
			</div>
			<div class="language-switcher">
				<a href="?language=en">
					<i class="icon-eng"></i>
				</a>
				<a href="?language=ru">
					<i class="icon-ru"></i>
				</a>
			</div>
			<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="start active open">
					<a href="javascript:;">
					<i class="icon-graph"></i>
					<span class="title"><fmt:message key="sideBar.statistics" /></span>
					<span class="selected"></span>
					<span class="arrow open"></span>
					</a>
					<ul class="sub-menu">
						<li class="active">
							<a href="${dashPage}">
							<i class="fa fa-eye"></i>
							<fmt:message key="sideBar.overview" /></a>
						</li>
						<li>
							<a href="#">
							<i class="icon-basket"></i>
							<fmt:message key="sideBar.orders" /></a>
						</li>
						<li>
							<a href="#">
							<i class=" fa fa-hand-o-up"></i>
							<fmt:message key="sideBar.clicks" /></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="fa fa-exclamation"></i>
					<span class="title"><fmt:message key="sideBar.notifications" /></span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="#">
							<i class="icon-envelope"></i>
							<fmt:message key="sideBar.unread" /></a>
						</li>
						<li>
							<a href="#">
							<i class="fa fa-file-archive-o"></i>
							<fmt:message key="sideBar.archive" /></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-docs"></i>
					<span class="title"><fmt:message key="sideBar.accounting" /></span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-book"></i>
							<fmt:message key="sideBar.history" /></a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-plus-sign"></i>
							<fmt:message key="sideBar.addMoney" /></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-link"></i>
					<span class="title"><fmt:message key="sideBar.integration" /></span>
					
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="${userUploadPage}">
							<i class="icon-handbag"></i>
							<fmt:message key="sideBar.uploadProducts" /></a>
						</li>
						<li>
							<a href="#">
							<i class="icon-tag"></i>
							<fmt:message key="sideBar.uploadOrders" /></a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-refresh"></i>
							<fmt:message key="sideBar.synch" /></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title"><fmt:message key="sideBar.settings" /></span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="${updateProfilePage}">
							<i class="icon-user"></i>
							<fmt:message key="sideBar.profileInfo" /></a>
						</li>
						<li>
							<a href="#">
							<i class="icon-envelope-open"></i>
							<fmt:message key="sideBar.emailNotif" /></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">
					<i class="icon-book-open"></i>
					<span class="title"><fmt:message key="sideBar.merchantDocs" /></span>
					</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="page-content-wrapper">
		<div class="page-content">
			<h3 class="page-title">
			<fmt:message key="dash.header" />
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="#"><fmt:message key="sideBar.home" /></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#"><fmt:message key="sideBar.statistics" /></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div class="portlet solid bordered grey-cararra">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.graphName" /></div>
						</div>
						<div class="portlet-body">
							<div id="site_statistics_loading">
								<img src="assets/admin/layout/img/loading.gif" alt="loading"/>
							</div>
							<div id="site_statistics_content" class="display-none">
								<div id="site_statistics" class="chart">
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.last10orders" /></div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										<fmt:message key="dash.rowTime" />
									</th>
									<th>
										<fmt:message key="dash.rowStatus" />
									</th>
									<th>
										<fmt:message key="dash.rowTitle" />
									</th>
									<th>
										<fmt:message key="dash.rowPrice" /> &euro;
									</th>
								</tr>
								</thead>
								<tbody>
							        <c:forEach items="${lastTenOrders}" var="order">
										<tr>
												<%-- conver long ${file.uploadTime} to date ${dateValue} --%>
												<jsp:useBean id="dateValue" class="java.util.Date"/>
												<jsp:setProperty name="dateValue" property="time" value="${order.created_at+172900000}"/>
											<td><fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/></td>
											<td>${order.status}</td>
											<td>${order.title}</td>
											<td>${order.price_common}</td>
<%-- 											<td><c:if test="${file.valid}">OK</c:if><c:if test="${not file.valid}">invalid</c:if></td>
											<td><c:if test="${file.active}">Active</c:if><c:if test="${not file.active}">inactive</c:if></td>
											<td>${file.validationMessage}</td> --%>
										</tr>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 double-chart">
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.currentMonthStat" /></div>
						</div>
						<div class="portlet-body">
							<div class="half-block">
								<h4><fmt:message key="dash.ordersTotal" /> 312)</h4>
								<div id="pie_chart_111" class="chart"></div>
							</div>
							<div class="half-block">
								<h4><fmt:message key="dash.conversion" /></h4>
								<div id="pie_chart_222" class="chart"></div>
							</div>
						</div>
					</div>
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.previousMonthStat" /></div>
						</div>
						<div class="portlet-body">
							<div class="half-block">
								<h4><fmt:message key="dash.ordersTotal" /> 278)</h4>
								<div id="pie_chart_333" class="chart"></div>
							</div>
							<div class="half-block">
								<h4><fmt:message key="dash.conversion" /></h4>
								<div id="pie_chart_444" class="chart"></div>
							</div>
						</div>
					</div>
				</div>
			</div>		
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.topProductsVisited" /></div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										<fmt:message key="dash.rowTitle" />
									</th>
									<th>
										<fmt:message key="dash.rowVisitsLastDay" />
									</th>
									<th>
										<fmt:message key="dash.rowVisitsLastWeek" />
									</th>
									<th>
										<fmt:message key="dash.rowPrice" /> &euro;
									</th>
								</tr>
								</thead>
								<tbody>
							        <c:forEach items="${topProductsVisited}" var="product" varStatus="loop">
										<tr>
											<td>${product.name}</td>
											<td>${visitsDay[loop.index]}</td>
											<td>${visitsWeek[loop.index]}</td>
											<td>${product.price}</td>
										</tr>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><fmt:message key="dash.topGrowingSales" /></div>
						</div>
						<div class="portlet-body">
							<div class="table-scrollable">
								<table class="table table-hover">
								<thead>
								<tr>
									<th>
										<fmt:message key="dash.rowTitle" />
									</th>
									<th>
										<fmt:message key="dash.rowPrice" /> &euro;
									</th>
								</tr>
								</thead>
								<tbody>
							        <c:forEach items="${topGrowingProducts}" var="product">
										<tr>
											<td>${product.name}</td>
											<td>${product.price}</td>
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
<!-- <script src="assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script> -->
 <script src="assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.time.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/flot/jquery.flot.pie.min.js" type="text/javascript"></script>
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
  // Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
});
</script>
<script>
jQuery(document).ready(function() {       
   //ChartsFlotcharts.init();
   //ChartsFlotcharts.initCharts();
   //ChartsFlotcharts.initPieCharts();
   //ChartsFlotcharts.initBarCharts();
   /*var plot = $.plot('#site_statistics', [ ['2014-12-12',2],['2014-12-14',2],['2014-12-16',3],['2014-12-17',5] ], {
		series: {
			lines: {
				show: true
			},
			points: {
				show: true
			}
		},
		grid: {
			hoverable: true
		}
	}
   );*/
	var d = '/graphData';
   $.ajax(
		   {
			   'url': '/graphData',
			   'dataType': 'json',
			   'success': function(data, textStatus, jqXHR) {
				   console.log(data);
				   var line1 = data.clicks;
				   var line2 = data.orders;
				   
				   var options = {
						   series:{
							   points:{
								   show:true
							   },
							   lines: {
								   show:true
							   },
						   },
						   grid: {
								hoverable: true,
								clickable: true
							},
							xaxis: {
								mode: "time",
								//tickLength: 5
							},
							selection: {
								//mode: "x"
							}
							/*grid: {
								markings: weekendAreas
							}*/
						};
				   
				   var plot = $.plot("#site_statistics", [{data:line1, label: '<fmt:message key="dash.clicks" />'}, {data:line2, label: '<fmt:message key="dash.orders" />'}], options);

				   $("<div id='tooltip'></div>").css({
						position: "absolute",
						display: "none",
						border: "1px solid #fdd",
						padding: "2px",
						"background-color": "#fee",
						opacity: 0.80
					}).appendTo("body");

				   $("#site_statistics").bind("plothover", function (event, pos, item) {
						if (item) {
							var x = item.datapoint[0].toFixed(2),
								y = item.datapoint[1].toFixed(2);

							$("#tooltip").html(item.series.label + " " + moment(parseInt(x)).format('MMM DD') + " = " + parseInt(y))
								.css({top: item.pageY+5, left: item.pageX+5})
								.fadeIn(200);
						} else {
							$("#tooltip").hide();
						}
					});
			   }
		   })
		
		   
		   
		   
		   
		   
		   
		   $.ajax({
			   'url': '/pieData?name=orders&period=current',
			   'dataType': 'json',
			   'success': function(data, textStatus, jqXHR) {
				   console.log(data);
				   $.plot('#pie_chart_111', data, {
					    series: {
					        pie: {
					            show: true,
					            radius: 1,
					            label: {
					                show: true,
					                radius: 3/4,
					                background: { 
					                    opacity: 0.5,
					                    color: '#000'
					                }
					            }
					        }
					    },
					    grid: {
					        hoverable: true,
					        clickable: true
					    }
					});
			   }
			   
		   });
   
   $.ajax({
	   'url': '/pieData?name=conversion&period=current',
	   'dataType': 'json',
	   'success': function(data, textStatus, jqXHR) {
		   console.log(data);
		   $.plot('#pie_chart_222', data, {
			    series: {
			        pie: {
			            show: true,
			            radius: 1,
			            label: {
			                show: true,
			                radius: 3/4,
			                background: { 
			                    opacity: 0.5,
			                    color: '#000'
			                }
			            }
			        }
			    },
			    grid: {
			        hoverable: true,
			        clickable: true
			    }
			});
		   
		   /*  document.getElementById('pie_chart_222').getChild('.legend').getChild('.pieLabel').text('lol') ; */
		   /*  $('.pieLabel').text('lol'); */
	   }
	   
   });
   
   //TODO replace url
   $.ajax({
	   'url': '/pieData?name=orders&period=previous',
	   'dataType': 'json',
	   'success': function(data, textStatus, jqXHR) {
		   $.plot('#pie_chart_333', data, {
			    series: {
			        pie: {
			            show: true,
			            radius: 1,
			            label: {
			                show: true,
			                radius: 3/4,
			                background: { 
			                    opacity: 0.5,
			                    color: '#000'
			                }
			            }
			        }
			    },
			    grid: {
			        hoverable: true,
			        clickable: true
			    }
			});
	   }
	   
   });

	$.ajax({
	'url': '/pieData?name=conversion&period=previous',
	'dataType': 'json',
	'success': function(data, textStatus, jqXHR) {
	
	   $.plot('#pie_chart_444', data, {
		    series: {
		        pie: {
		            show: true,
		            radius: 1,
		            label: {
		                show: true,
		                radius: 3/4,
		                background: { 
		                    opacity: 0.5,
		                    color: '#000'
		                }
		            }
		        }
		    },
		    grid: {
		        hoverable: true,
		        clickable: true
		    }
		});
	}
	
	});
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>