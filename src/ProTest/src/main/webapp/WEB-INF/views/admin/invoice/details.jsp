<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="content-header">
	<h1>Invoice Details</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/invoice">
				Invoice List</a></li>
		<li class="active">Invoice Details</li>
	</ol>
</section>

<section class="invoice">
	<div id="print">
		<!-- title row -->
		<div class="row">
			<div class="col-xs-12">
				<h1 style="text-align: center; font-weight: bolder;">BEAUTY FLOWERS</h1>
				<h2 class="page-header">
					<i class="fa fa-globe"></i> ${invoice.name } (#${invoice.id }) <small
						class="pull-right">Date: ${invoice.created }</small>
				</h2>
			</div>
			<!-- /.col -->
		</div>
		<!-- info row -->
		<div class="row invoice-info">

			<div class="col-sm-4 invoice-col">
				To:
				<address>
					<strong>${invoice.account.fullName }</strong><br> Address:<br>
					<strong>${invoice.account.address }</strong><br> Phone:<br> <strong>${invoice.account.phone }</strong><br>
					Email:<br> <strong>${invoice.account.email }</strong>
				</address>
			</div>

		</div>
		<!-- /.row -->

		<!-- Table row -->
		<div class="row">
			<div class="col-xs-12 table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Photo</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="total" value="0"></c:set>
						<c:forEach var="invoiceDetail"
							items="${invoice.invoiceDetailses }">
							<c:set var="total"
								value="${total + invoiceDetail.product.price * invoiceDetail.quantity  }"></c:set>
							<c:set var="photo"
								value="${invoiceDetail.product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
							<tr>
								<td>${invoiceDetail.product.id }</td>
								<td>${invoiceDetail.product.name }</td>
								<td><!-- <a
									href="${pageContext.request.contextPath }/product/details/${invoiceDetail.product.id }"><img
										alt=""
										src="${pageContext.request.contextPath }/uploads/images/${photo.name }"
										width="80px"></a></td> -->
										<img alt=""
										src="${pageContext.request.contextPath }/uploads/images/${photo.name }"
										width="80px"></td>
								<td>$${invoiceDetail.product.price }</td>
								<td>${invoiceDetail.quantity }</td>
								<td>$${invoiceDetail.product.price * invoiceDetail.quantity
									}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->

		<div class="row">

			<div class="col-xs-12">

				<div class="table-responsive  pull-right" style="padding-right: 70px">
					<table class="table">
						<tbody>
							<tr>
								<th><h3>Total:</h3></th>
								<td><h3 style="color: red;">$${total }</h3></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.print -->


	<div class="row no-print">
		<div class="col-xs-6">
			<button type="button" class="btn btn-info pull-left" onclick="printDiv('print')">
				<span class="glyphicon glyphicon-print"></span> Print
			</button>
		</div>
		<c:if test="${invoice.status == 'pending' }">
			<div class="col-xs-6">

				<a
					href="${pageContext.request.contextPath }/admin/invoice/update/${invoice.id }"
					class="btn btn-primary pull-right" style="margin-right: 5px;">
					Update </a>
			</div>
		</c:if>
		<br>
	</div>


</section>