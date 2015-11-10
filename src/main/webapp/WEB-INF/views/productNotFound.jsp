<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section>
	<div class="jumbotron">
		<div class="container">
			<h2 class="alert	alert-danger">There is no product found with
				the Product id ${invalidProductId}</h2>
		</div>
	</div>
</section>

<section>
	<div class="container">
		<p>${url}</p>
		<p>${exception}</p>
	</div>
	<div class="container">
		<p>
			<a href="<spring:url	value="/products"	/>" class="btn	btnprimary">
				<span class="glyphicon-hand-left	glyphicon"></span> products
			</a>
		</p>
	</div>
</section>
