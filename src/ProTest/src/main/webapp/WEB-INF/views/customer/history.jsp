<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<h4 style="text-align: center">History Order</h4>

<section id="Table">
	<div class="row-fluid">
		<div class="span12">
			<table class="table">
				<thead>
				 	<tr>
				 		<th>#</th>
				 		<th>Name</th>
				 		<th>Created</th>
				 		<th>Status</th>
				 		<th>Action</th>
				 	</tr>
				</thead>
				<tbody>
					<c:forEach var="invoice" items="${invoices }">
					<tr>
						<td>${invoice.id }</td>
						<td>${invoice.name }</td>
						<td>${invoice.created }</td>
						<td>${invoice.status }</td>
						<td>
							<a class="btn btn-info" href="${pageContext.request.contextPath }/customer/invoicedetails/${invoice.id }">Details</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>