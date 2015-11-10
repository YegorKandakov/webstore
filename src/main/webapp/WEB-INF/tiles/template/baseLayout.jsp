<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@	taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width,	initialscale=1.0">
<title><tiles:insertAttribute name="title" /></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="well">	
			<ul class="nav nav-pills pull-right">
				<tiles:insertAttribute name="navigation" />
			</ul>		
			<h2 class="text-muted">Web Store</h2>
			<p>
				<tiles:insertAttribute name="tagline" />
			</p>
		</div>
		<div class="row">
			<tiles:insertAttribute name="content" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>