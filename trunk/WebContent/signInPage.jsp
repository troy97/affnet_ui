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
    <title>Sign In</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
  </head>
  
  <body>
    <div class="container">
    <a href="?language=en">en</a>  &nbsp <a href="?language=ru">ru</a>
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">
				 <c:if test="${!empty wrongCredentials}">
				 	<font color="#d9534f"><fmt:message key="signIn.wrongCredentials" /></font>
				 </c:if>
				 <c:if test="${empty wrongCredentials}">
				 	<fmt:message key="signIn.welcome" />
				 </c:if>
				   
				</h1>
				<div class="account-wall">
					<img class="profile-img" src="https://lh3.googleusercontent.com/-PYRBt5l1mpA/AAAAAAAAAAI/AAAAAAAAABY/Acc8QUErJ5E/photo.jpg"
					alt="i-Butler">
					<form class="form-signin" action="${checkSignIn}" method="POST">
						<input type="email" class="form-control" placeholder=<fmt:message key="signIn.emailPlaceholder" /> required autofocus name="${email}">
						<input type="password" class="form-control" placeholder=<fmt:message key="signIn.passwordPlaceholder" /> required name="${password}">
						<input type="hidden" class="form-control" name="language" value="${language}">
						<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signIn.signInButton" /></button>
						<label class="checkbox pull-left"><fmt:message key="signIn.notRegisteredYet" /></label>
						<a href="${signUpPage}?language=${language}" class="pull-right need-help"><fmt:message key="signIn.signUpLink" /></a><span class="clearfix"></span>
						
					</form>
				</div>
				<!-- <a href="#" class="text-center new-account">Create an account </a> -->
			</div>
		</div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>