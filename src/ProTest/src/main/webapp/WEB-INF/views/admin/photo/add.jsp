<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
	<h1>Add Photo to ${product.name }</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/product"></i> Product List</a></li>
		<li class="active">Add Photo</li>
	</ol>
</section>

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">

				<!-- form start -->
				<s:form method="post" modelAttribute="photo" enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/admin/photo/add">

					<div class="box-body">

						<div class="form-group">
							<label for="name">File</label>
							<input type="file" name="file" />
						</div>
						
						<div class="checkbox">
							<label> <s:checkbox path="main" /> Main
							</label>
						</div>

						<div class="checkbox">
							<label> <s:checkbox path="status" /> Status
							</label>
						</div>

					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Save</button>
						<s:hidden path="product.id"/>
					</div>
				</s:form>
			</div>
			<!-- /.box -->



		</div>

	</div>
	<!-- /.row -->
</section>