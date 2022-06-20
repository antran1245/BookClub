<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login And Registration</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <!-- For any Bootstrap that uses JS or jQuery-->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<header class="row">
				<h1 class="heading-text">Welcome!</h1>
				<p>Join our growing community</p>
		</header>
		<div class="row">
			<div class="col form-box p-3 m-2">
				<h2>Register</h2>
				<form:form method="post" action="/register" modelAttribute="newUser">
					<div class="mb-3">
						<form:label path="name" class="form-label">Name: </form:label>
						<form:input path="name" class="form-control"/>
						<form:errors path="name" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="email" class="form-label">Email: </form:label>
						<form:input type="email" path="email" class="form-control"/>
						<form:errors path="email" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:input type="password" path="password" class="form-control"/>
						<form:errors path="password" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="confirmPassword" class="form-label">Confirm Password: </form:label>
						<form:input type="password" path="confirmPassword" class="form-control"/>
						<form:errors path="confirmPassword" class="text-danger"/>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
			<div class="col form-box p-3 m-2">
				<h2>Log in</h2>
				<form:form method="post" action="/login" modelAttribute="loginUser">
					<div class="mb-3">
						<form:label path="email" class="form-label">Email: </form:label>
						<form:input type="email" path="email" class="form-control"/>
						<form:errors path="email" class="text-danger"/>
					</div>
					<div class="mb-3">
						<form:label path="password" class="form-label">Password: </form:label>
						<form:input type="password" path="password" class="form-control"/>
						<form:errors path="password" class="text-danger"/>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>