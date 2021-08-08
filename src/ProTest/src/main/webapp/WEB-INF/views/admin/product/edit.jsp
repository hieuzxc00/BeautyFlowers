<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
	<h1>Edit Product</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i
				class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">Edit Product</a></li>
	</ol>
</section>

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">

				<!-- form start -->
				<s:form method="post" modelAttribute="product"
					action="${pageContext.request.contextPath}/admin/product/edit">

					<div class="box-body">

						<div class="form-group">
							<label for="name">Name</label>
							<s:input path="name" cssClass="form-control" id="name"
								required="required" />
						</div>

						<div class="form-group">
							<label for="price">Price</label>
							<s:input type="number" min="0" path="price"
								cssClass="form-control" id="price" required="required" />
						</div>

						<div class="form-group">
							<label for="quantity">Quantity</label>
							<s:input type="number" min="0" path="quantity"
								cssClass="form-control" id="quantity" required="required" />
						</div>

						<div class="form-group">
							<label for="description">Description</label>
							<s:textarea path="description" cssClass="form-control"
								id="description" />
						</div>

						<div class="form-group">
							<label for="details">Details</label>
							<s:textarea path="details" cssClass="form-control" id="details" />
						</div>

						<div class="checkbox">
							<label> <s:checkbox path="featured" /> Featured
							</label>
						</div>

						<div class="checkbox">
							<label> <s:checkbox path="status" /> Status
							</label>
						</div>

						<div class="form-group">
							<label>Category</label>
							<s:select path="category.id" class="form-control">
								<c:forEach var="cat" items="${categories }">
									<optgroup label="${cat.name }"></optgroup>
									<c:if test="${!cat.categories.isEmpty() }">
										<c:forEach var="subCategory" items="${cat.categories }">
											<option value="${subCategory.id }" ${product.category.id == subCategory.id ? 'selected="selected"' : "" }>${subCategory.name }</option>
										</c:forEach>
									</c:if>
								</c:forEach>
							</s:select>
						</div>

					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Save</button>
						<s:hidden path="id"/>
					</div>
				</s:form>
			</div>
			<!-- /.box -->



		</div>

	</div>
	<!-- /.row -->
</section>