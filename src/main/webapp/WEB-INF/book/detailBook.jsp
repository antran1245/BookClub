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
    <title>Detail Book</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <!-- For any Bootstrap that uses JS or jQuery-->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<header class="row d-flex align-items-center">
			<h1 class="col-8"><c:out value="${book.getTitle()}"/></h1>
			<a class="col" href="/dashboard">Back to the Shelves</a>
		</header>
		<div class="row">
			<p>
				<c:choose>
					<c:when test="${book.getUser() == user}">
						You
					</c:when>
					<c:otherwise>
						<c:out value="${book.getUser().getName()}"/>
					</c:otherwise>
				</c:choose>
				 read <c:out value="${book.getTitle()}"/> by <c:out value="${book.getAuthor()}"/>.
			</p>
			<p>
				Here are
				<c:choose>
					<c:when test="${book.getUser() == user}">
						your
					</c:when>
					<c:otherwise>
						<c:out value="${book.getUser().getName()}"/>'s
					</c:otherwise>
				</c:choose>
				 thoughts.
			</p>
		</div>
		<div class="row">
			<div class="col-10 m-auto">
				<hr/>
				<c:out value="${book.getThought()}"/>
				<hr/>
			</div>
		</div>
	</div>
</body>
</html>