<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="pagedListHolder" scope="request" 
	type="org.springframework.beans.support.PagedListHolder" />
<c:url value="/product/category/${category.id }" var="pagedLink">
	<c:param name="p" value="~"></c:param>
</c:url>

<div class="row">
<div class="span12">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/home">Home</a><span
			class="divider">/</span></li>
		<li class="active">${category.name }</li>
		
	</ul>

</div>

<div class="span9">
	<center>
		<span><h4>${category.name }</h4></span>
	</center>
	<h3>
		<small class="pull-right"><strong>${category.products.size() }</strong> products are available</small>
		</h3>
	<br>
	
	<ul class="thumbnails listing-products">
		<c:forEach var="product" items="${pagedListHolder.pageList }">
			<c:set var="photo"
				value="${product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
			<li class="span3">
				<div class="product-box">
					<span class="sale_tag"></span> <a
						href="${pageContext.request.contextPath }/product/details/${product.id }"><img
						alt=""
						src="${pageContext.request.contextPath }/uploads/images/${photo.name }"></a><br>
					<a
						href="${pageContext.request.contextPath }/product/details/${product.id }"
						class="title">${product.name }</a><br> <a href="#"
						class="category">${product.description }</a>
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
	<hr>
	<div class="pagination pagination-small pagination-centered">
		<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink }" />
	</div>
</div>

<div class="span3 col">
	<mt:categoriesList />
	

</div>
</div>
<script type="text/javascript">
	$("#menu-filter").accordion();
</script>
