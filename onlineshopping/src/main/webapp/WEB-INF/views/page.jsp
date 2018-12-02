<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
	<link rel='shortcut icon' href="https://img.icons8.com/nolan/50/000000/shopping-cart.png" />
	

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap Datatable -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->

		<div class="content">

			<!-- Loading the home content -->
			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- Load only when user clicks about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Load only when user clicks contact -->
			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- Load only when user clicks view Produts -->
			<c:if
				test="${userClicksAllProducts == true or userClickCategoryProducts == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Load only when user clicks show single Product -->
			<c:if test="${userClicksShowProduct == true }">
				<%@include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- Load only when user clicks Manage Products -->
			<c:if test="${userClicksManageProducts == true }">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			<!-- Load only when user clicks Show Cart -->
			<c:if test="${userClickShowCart == true }">
				<%@include file="cart.jsp"%>
			</c:if>
			
			
			

		</div>


		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>



		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- jQuery Validator -->
		<script src="${js}/jquery.validate.js"></script>


		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Data table Pluging -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- Data table Bootstrap -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- Bootbox -->
		<script src="${js}/bootbox.min.js"></script>
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>