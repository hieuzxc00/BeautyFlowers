<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<section class="content-header">
	<h1>Add Category</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">Add Category</a></li>
	</ol>
</section>

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">

				<!-- form start -->
				<s:form method="post" modelAttribute="category"
					action="${pageContext.request.contextPath}/admin/category/add">

					<div class="box-body">

						<div class="form-group">
							<label for="name">Name</label>
							<s:input path="name" cssClass="form-control" id="name"
								required="required" />
						</div>

						<div class="checkbox">
							<label> <s:checkbox path="status"/> Status
							</label>
						</div>
						
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</s:form>
			</div>
			<!-- /.box -->



		</div>

	</div>
	<!-- /.row -->
</section>