<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">

	<div class="span9">
		<h4>
			<span>New Products</span>
		</h4>
		<hr>
		<ul class="thumbnails listing-products">

			<c:forEach var="product" items="${newProducts }">
				<c:set var="photo"
					value="${product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
				<li class="span3">
					<div class="product-box">
						<span class="sale_tag"></span> <a
							href="${pageContext.request.contextPath }/product/details/${product.id }"><img
							alt=""
							src="${pageContext.request.contextPath }/uploads/images/${photo.name }" style="height: 350px;"></a>
						<br /> <a
							href="${pageContext.request.contextPath }/product/details/${product.id }"
							class="title">${product.name }</a><br /> <a href="#"
							class="category">${product.description }</a><br />
						<p class="price">$${product.price }</p>

						<c:if test="${product.quantity == 0}">
							<p class="btn btn-danger"
									>Sold out
								</p>
						</c:if>
						
						<c:if test="${product.quantity != 0}">
								<a class="btn btn-primary"
									href="${pageContext.request.contextPath }/cart/buy/${product.id}">Add
									to Cart | <i class="fas fa-shopping-cart"></i>
								</a>
						</c:if>	
					
							</div>
				</li>
			</c:forEach>

		</ul>

	</div>

	<div class="span3 col">
		<mt:categoriesList />

	</div>
	<div class="span9">
		<h4>
			<span>Featured Products</span>
		</h4>
		<hr>
		<ul class="thumbnails listing-products">
			<c:forEach var="product" items="${featuredProducts }">
				<c:set var="photo"
					value="${product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
				<li class="span3">
					<div class="product-box">
						<span class="sale_tag"></span> <a
							href="${pageContext.request.contextPath }/product/details/${product.id }"><img style="height: 350px;"
							alt=""
							src="${pageContext.request.contextPath }/uploads/images/${photo.name }"></a><br />
						<a
							href="${pageContext.request.contextPath }/product/details/${product.id }"
							class="title">${product.name }</a><br />
						<a href="#" class="category">${product.description }</a><br />
						<p class="price">$${product.price }</p>
						<c:if test="${product.quantity == 0}">
							<p class="btn btn-danger"
									>Sold out
								</p>
						</c:if>
						
						<c:if test="${product.quantity != 0}">
								<a class="btn btn-primary"
									href="${pageContext.request.contextPath }/cart/buy/${product.id}">Add
									to Cart | <i class="fas fa-shopping-cart"></i>
								</a>
						</c:if>	
					</div>
				</li>
			</c:forEach>

		</ul>

	</div>
</div>
<script type="text/javascript">
	$("#menu-filter").accordion();
</script>