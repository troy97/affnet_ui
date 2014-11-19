<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundleBasename}" />
<!DOCTYPE html>
<html lang="${language}">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Anton">
    <link rel="icon" href="favicon.ico">

    <title><fmt:message key="signUp.title" /></title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" >Affiliate Network</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
	    	<li><a href="${signInPage}?language=${language}"><fmt:message key="signUp.signInLink" /></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
		<h3><fmt:message key="signUp.headMessage" /></h3>
		<fmt:message key="signUp.asterisksMessage" />
	    	</br></br><font color="red">${wrongData}</font>
	    
	    	
		<form name="input" class="form-horizontal" action="${checkSignUp}" method="POST">
		</br>
			<div class="form-group">
			<div class="col-xs-3">
			 <label for="id1"><fmt:message key="signUp.shopName" /> <font color="red">*</font></label>
			 <input type="text" class="form-control" id="id1" placeholder="<fmt:message key="signUp.shopNamePlaceholder" />" name="${shopName}">
			</div>
			</div>
			
			<div class="form-group">
			<div class="col-xs-3">
			 <label for="id2"><fmt:message key="signUp.shopUrl" /> <font color="red">*</font></label>
			 <input type="text" class="form-control" id="id2" placeholder="<fmt:message key="signUp.shopUrlPlaceholder" />" name="${shopUrl}">
			</div>
			</div>
			
			</br>
			
			<div class="form-group">	
			<div class="col-xs-3">
			 <label for="id4"><fmt:message key="signUp.email" /> <font color="red">*</font></label>
			 <input type="text" class="form-control" id="id4" placeholder="<fmt:message key="signUp.emailPlaceholder" />" name="${email}">
			</div> 
			</div> 
			<div class="form-group">	
			<div class="col-xs-3">
			 <label for="id5"><fmt:message key="signUp.password" /> <font color="red">*</font></label>
			 <input type="password" class="form-control" id="id5" placeholder="<fmt:message key="signUp.passwordPlaceholder" />" name="${password}">
			</div> 
			</div> 
			<div class="form-group">	
			<div class="col-xs-3">
			 <label for="id6"><fmt:message key="signUp.firstName" /></label>
			 <input type="text" class="form-control" id="id6" placeholder="<fmt:message key="signUp.firstNamePlaceholder" />" name="${firstName}">
			</div> 
			</div> 
			<div class="form-group">	
			<div class="col-xs-3">
			 <label for="id7"><fmt:message key="signUp.lastName" /></label>
			 <input type="text" class="form-control" id="id7" placeholder="<fmt:message key="signUp.lastNamePlaceholder" />" name="${lastName}">
			</div> 
			</div> 
			<div class="form-group">	
			<div class="col-xs-3">
			 <input type="hidden" class="form-control" id="id8" name="language" value="${language}">
			</div> 
			</div> 
			<button class="btn btn-lg btn-primary" type="submit"><span class="glyphicon glyphicon-edit"></span> <fmt:message key="signUp.buttonText" /></button>
		</form>
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="dist/js/bootstrap.min.js"></script>
  </body>
</html>