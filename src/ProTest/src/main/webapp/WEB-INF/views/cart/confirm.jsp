<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<div class="span12">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/home">Home</a><span
			class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath }/cart/index">Cart</a><span
			class="divider">/</span></li>
		<li class="active">Confirm</li>
	</ul>
</div>
<div class="row">
	<div class="span12">
		<h4 class="title">
			<span class="text"><strong>Confirm delivery information</strong></span>
		</h4>
		${err }
		
		<s:form method="post" class="form-stacked" modelAttribute="customer" style="padding-left: 40%"
			action="${pageContext.request.contextPath }/cart/confirm">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="fullname">Full Name <sup style="color: red">*</sup></label>
					<div class="controls">
						<s:input class="input-xlarge"
							path="fullName" required="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address">Address <sup style="color: red">*</sup></label>
					<div class="controls">
						<s:input class="input-xlarge"
							path="address" required="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">Email <sup style="color: red">*</sup></label>
					<div class="controls">
						<s:input type="email" class="input-xlarge"
							path="email" required="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="phone">Phone <sup style="color: red">*</sup></label>
					<div class="controls">
						<s:input class="input-xlarge"
							path="phone" required="required"/>
					</div>
				</div>
				<p>
					<span style="color: red">*</span> Required field
				</p>
				
				<div class="control-group">
					<div class="controls">
						<input class="btn btn-large btn-success"
							type="submit" value="Checkout"/>
							<s:hidden path="id"/>
					</div>
				</div>
				
			</fieldset>

		</s:form>
		
	</div>
</div>