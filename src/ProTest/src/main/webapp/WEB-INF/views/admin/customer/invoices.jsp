<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="content-header">
	<h1>Invoices List</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/customer">
				Customer List</a></li>
		<li class="active">Invoices List</li>
	</ol>
</section>

<section class="content">
	<div class="row">
		<div class="col-xs-12">

			<c:if test="${error != null }">
				<div class="alert alert-danger alert-dismissible">${error }</div>
			</c:if>

			<div class="box">
				<div class="box-body">
					<div id="example2_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap">
						<div class="row">
							<div class="col-sm-6"></div>
							<div class="col-sm-6"></div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="categoryList"
									class="table table-bordered table-hover dataTable" role="grid"
									aria-describedby="example2_info">
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
												<td><a class="btn btn-info"
													href="${pageContext.request.contextPath }/admin/invoice/details/${invoice.id }">Details</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th>#</th>
											<th>Name</th>
											<th>Created</th>
											<th>Status</th>
											<th>Action</th>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-5"></div>
							<div class="col-sm-7">
								<div class="dataTables_paginate paging_simple_numbers"
									id="example2_paginate">
									<ul class="pagination">
										<li class="paginate_button previous disabled"
											id="example2_previous"><a href="#"
											aria-controls="example2" data-dt-idx="0" tabindex="0">Previous</a></li>
										<li class="paginate_button active"><a href="#"
											aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
										<li class="paginate_button next" id="example2_next"><a
											href="#" aria-controls="example2" data-dt-idx="7"
											tabindex="0">Next</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
			</div>


		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>

<script>
	$(function() {
		$('#categoryList').DataTable({
			'paging' : true,
			'lengthChange' : false,
			'searching' : false,
			'ordering' : true,
			'info' : true,
			'autoWidth' : false
		})
	})
</script>