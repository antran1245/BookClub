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
    <title>Dashboard</title>
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
			<div class="col-8 d-flex flex-column justify-content-around">
				<div class="row">
					<h1 class="heading-text">Welcome, <c:out value="${user.getName()}"/>!</h1>
				</div>
				<div class="row">
					<p>Books from every	everyone's shelves:		
				</div>			
			</div>
			<div class="col d-flex flex-column justify-content-around">
				<div class="row text-end">
					<a href="/logout">logout</a>
				</div>
				<div class="row text-end">
					<a href="/books/new">+ Add a Book to My Shelf</a>
				</div>				
			</div>
		</header>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Title</th>
						<th scope="col">Author Name</th>
						<th scope="col">Posted By</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<tr>
							<th scope="row"><c:out value="${book.getId()}"/></th>
							<td><a href="/books/${book.getId()}"><c:out value="${book.getTitle()}"/></a></td>
							<td><c:out value="${book.getAuthor()}"/></td>
							<td><c:out value="${book.getUser().getName()}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>