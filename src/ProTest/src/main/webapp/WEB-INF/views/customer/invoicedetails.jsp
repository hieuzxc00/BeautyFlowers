<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<h4 style="text-align: center">Invoice Details</h4>

<section id="Table">
	<div class="row-fluid">
		<div class="span12">
			<table class="table">
				<thead>
				 	<tr>
				 		<th>#</th>
				 		<th>Name</th>
				 		<th>Photo</th>
				 		<th>Price</th>
				 		<th>Quantity</th>
				 		<th>Total</th>
				 	</tr>
				</thead>
				<tbody>
					<c:set var="total" value="0"></c:set>
					<c:forEach var="invoiceDetail" items="${invoice.invoiceDetailses }">
					<c:set var="total" value="${total + invoiceDetail.product.price * invoiceDetail.quantity }"></c:set>
					<c:set var="photo"
					value="${invoiceDetail.product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
					<tr>
						<td>${invoiceDetail.product.id }</td>
						<td>${invoiceDetail.product.name }</td>
						<td>
							<a href="${pageContext.request.contextPath }/product/details/${invoiceDetail.product.id }"><img
							alt=""
							src="${pageContext.request.contextPath }/uploads/images/${photo.name }" width="80px"></a>
						</td>
						<td>$${invoiceDetail.product.price }</td>
						<td>${invoiceDetail.quantity }</td>
						<td>$${invoiceDetail.product.price * invoiceDetail.quantity }</td>
					</tr>
					</c:forEach>
					<tr>
						<th colspan="5" align="right">Total</th>
						<th>$${total }</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<a href="${pageContext.request.contextPath }/customer/history" class="btn btn-info">History Order</a>
</section>