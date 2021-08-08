<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="content-header">
	<h1>Photo List of ${product.name }</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/product"> Product List</a></li>
		<li class="active">Photos</li>
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
										<tr role="row">
											<th class="sorting_asc" tabindex="0" aria-controls="example2"
												rowspan="1" colspan="1" aria-sort="ascending"
												aria-label="Rendering engine: activate to sort column descending">Id
											</th>
											<th class="sorting" tabindex="0" aria-controls="example2"
												rowspan="1" colspan="1"
												aria-label="Browser: activate to sort column ascending">Photo</th>
												<th class="sorting" tabindex="0" aria-controls="example2"
												rowspan="1" colspan="1"
												aria-label="CSS grade: activate to sort column ascending">Status</th>
											<th class="sorting" tabindex="0" aria-controls="example2"
												rowspan="1" colspan="1"
												aria-label="CSS grade: activate to sort column ascending">Main</th>
											<th class="sorting" tabindex="0" aria-controls="example2"
												rowspan="1" colspan="1"
												aria-label="CSS grade: activate to sort column ascending">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="photo" items="${product.photos }">
											<tr role="row" class="odd">
												<td class="sorting_1">${photo.id }</td>
												<td><img src="${pageContext.request.contextPath }/uploads/images/${photo.name }" 
												width="120"></td>
												<td>${photo.status ? "Show" : "Hide" }</td>
												<td>${photo.main ? "Main" : "" }</td>
												<td><a
													href="${pageContext.request.contextPath}/admin/photo/edit/${photo.id }">Edit</a>
													| <a
													href="${pageContext.request.contextPath}/admin/photo/delete/${photo.id }/${photo.product.id}"
													onclick="return confirm('Are you sure ?')">Delete</a></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th rowspan="1" colspan="1">Id</th>
											<th rowspan="1" colspan="1">Photo</th>
											<th rowspan="1" colspan="1">Status</th>
											<th rowspan="1" colspan="1">Main</th>
											<th rowspan="1" colspan="1">Action</th>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-5">
								
							</div>
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