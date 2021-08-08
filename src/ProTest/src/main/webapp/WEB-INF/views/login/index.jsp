<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<div class="span12">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath }/home">Home</a><span
			class="divider">/</span></li>
		<li class="active">Login</li>
	</ul>
</div>
<div class="row">
	<div class="span6">
		<h4 class="title">
			<span class="text"><strong>Login</strong></span>
		</h4>
		
		${msg }
		<form action="${pageContext.request.contextPath}/customer/process-login" 
			method="post">
			<input type="hidden" name="next" value="/">
			<fieldset>
				<div class="control-group">
					<label class="control-label">User name</label>
					<div class="controls">
						<input type="text" id="username"
							class="input-xlarge" name="username">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Password</label>
					<div class="controls">

						<input type="password" id="password"
							class="input-xlarge" name="password">
					</div>
				</div>
				<div class="control-group">
					<button type="submit"  class="btn btn-primary">Login</button>
					<br>
					<a href="${pageContext.request.contextPath }/customer/register">Register</a>
				</div>
			</fieldset>
		</form>
	</div>
</div>