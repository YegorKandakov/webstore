<%@	taglib	prefix="spring"	uri="http://www.springframework.org/tags"%>

<div class="btn-group" role="group" aria-label="...">
  <a class="btn btn-default" href="<spring:url	value="/"/>">Home</a>
  <a class="btn btn-default" href="<spring:url	value="/products"/>">Products</a>
  
   <div class="btn-group" role="group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      Category
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu">
      <li><a href="<spring:url value="/products/smartphone"/>">Smartphone</a></li>
      <li><a href="<spring:url value="/products/laptop"/>">Laptop</a></li>
      <li><a href="<spring:url value="/products/tablet"/>">Tablet</a></li>
    </ul>
  </div>
  
  <a class="btn btn-default" href="<spring:url	value="/products/add"/>">Add Product</a>
  <a class="btn btn-default" href="<spring:url	value="/cart/"/>">Cart</a>
</div>
