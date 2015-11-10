<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;	charset=utf-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Thank you</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h2 class="alert	alert-danger">Thank you</h2>
				<p>
					Thanks for the order. Your order will be delivered to you on
					<fmt:formatDate type="date"
						value="${order.shippingDetail.shippingDate}" />
					!
				</p>
				<p>Your Order Number is ${order.orderId}</p>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<p>
				<a href="<spring:url	value="/products"	/>" class="btn	btnprimary">
					<span class="glyphicon-hand-left	glyphicon"></span> Products
				</a>
			</p>
		</div>
	</section>
</body>
</html>