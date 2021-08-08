<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="homepage-slider" id="home-slider">
	<div class="flexslider">
		<ul class="slides">
			<c:forEach var="slide" items="${slides }">
			<li class=""
				style="width: 100%; float: left; margin-right: -100%; position: relative; display: none;">
				<img src="${pageContext.request.contextPath }/uploads/images/${slide.name}" alt="${slider.description }"
				style="height: 400px">
			</li>
			</c:forEach>
		</ul>
		<ul class="flex-direction-nav">
			<li><a class="flex-prev" href="#">Previous</a></li>
			<li><a class="flex-next" href="#">Next</a></li>
		</ul>
	</div>
</section>