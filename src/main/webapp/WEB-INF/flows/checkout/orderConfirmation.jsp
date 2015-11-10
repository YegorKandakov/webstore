<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Order Confirmation</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h2>Order</h2>
				<p>Order Confirmation</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<h2>Receipt</h2>
					</div>
					<div class="row">
						<div class="col-xs-6	col-sm-6	col-md-6">
							<strong>Name</strong> <br>
							${order.customer.name}<br>
							<strong>Shipping Date</strong> <br>
							<fmt:formatDate type="date"
								value="${order.shippingDetail.shippingDate}" /><br>
							<strong>Street</strong> <br>
							${order.shippingDetail.shippingAddress.streetName}<br>
							<strong>House number</strong> <br>
							${order.shippingDetail.shippingAddress.houseNo}<br>

							<button id="back" class="btn	btn-default"
								name="_eventId_backToCollectShippingDetail">Back</button>
							<button type="submit" class="btn	btn-success"
								name="_eventId_orderConfirmed">
								Confirm <span class="glyphicon	glyphicon-chevronright"></span>
							</button>
							<button id="btnCancel" class="btn	btn-default"
								name="_eventId_cancel">Cancel</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>