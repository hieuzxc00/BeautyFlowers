<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="span12">
		<ul class="breadcrumb">
			<li><a href="${pageContext.request.contextPath }/home">Home</a><span
				class="divider">/</span></li>
			<li><a href="">Products</a><span class="divider">/</span></li>
			<li class="active">Product Details</li>
		</ul>
	</div>
	<center>
		<span><h4>Product Information</h4></span>
	</center>
	<br>
	<div class="span9">
		<c:set var="photo"
			value="${product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
		<div class="row">
			<div class="span4">
				<a
					href="${pageContext.request.contextPath }/uploads/images/${photo.name }"
					class="thumbnail" data-fancybox-group="group1"
					title="${product.name }"><img alt=""
					src="${pageContext.request.contextPath }/uploads/images/${photo.name }"></a>
				<ul class="thumbnails small">
					<c:forEach var="p" items="${photos }">
						<li class="span1"><a
							href="${pageContext.request.contextPath }/uploads/images/${p.name }"
							class="thumbnail" data-fancybox-group="group1"
							title="${product.name }"><img
								src="${pageContext.request.contextPath }/uploads/images/${p.name }"
								alt=""></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="span5">
				<address>
					<strong>Product name: </strong> <span>${product.name }</span><br>
				</address>
				<h4>
					<strong>Price: $${product.price }</strong>
				</h4>
			</div>
			<div class="span5">
				<form class="form-inline">

					<c:if test="${product.quantity == 0}">
						<p class="btn btn-danger">Sold out</p>
					</c:if>

					<c:if test="${product.quantity != 0}">
						<p>&nbsp;</p>
						<input type="number" min="1" class="span1" placeholder="Qty.">
						<br>
						<br>
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath }/cart/buy/${product.id}">Add
							to Cart | <i class="fas fa-shopping-cart"></i>
						</a>
					</c:if>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="span9">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#home">Details</a></li>
					<li class=""><a href="#profile">Description</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="home">${product.details }</div>
					<div class="tab-pane" id="profile">${product.description }</div>
				</div>
			</div>

		</div>
	</div>

	<div class="span3 col">
		<mt:categoriesList />
		
	</div>
	<div class="span12">
		<br>
		<h4 class="title">
			<span class="pull-left"><span class="text"><strong>Related</strong>
					Products</span></span>
		</h4>
		<div id="myCarousel-1" class="carousel slide">
			<div class="carousel-inner">
				<div class="item active">
					<ul class="thumbnails listing-products">
						<c:forEach var="p" items="${relatedProducts }">
							<c:set var="photo"
								value="${p.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
							<li class="span3">
								<div class="product-box">
									<a
										href="${pageContext.request.contextPath }/product/details/${p.id }"><img
										alt=""
										src="${pageContext.request.contextPath }/uploads/images/${photo.name }"></a><br>
									<a
										href="${pageContext.request.contextPath }/product/details/${p.id }"
										class="title">${p.name }</a><br> <a href="#"
										class="category">${p.description }</a>
									<p class="price">$${p.price }</p>
									<a class="btn btn-primary"
										href="${pageContext.request.contextPath }/cart/buy/${product.id}">Add
										to Cart | <i class="fas fa-shopping-cart"></i>
									</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#menu-filter").accordion();
</script>