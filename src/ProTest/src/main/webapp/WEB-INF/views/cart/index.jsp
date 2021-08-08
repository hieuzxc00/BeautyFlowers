<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="span9">
		<ul class="breadcrumb">
			<li><a href="${pageContext.request.contextPath }/home">Home</a><span
				class="divider">/</span></li>
			<li class="active">Shopping Cart</li>
		</ul>
		<center>
			<h4>
				Shopping Cart <small>[${countItems } Item(s)]</small>
			</h4>
		</center>
		<a href="${pageContext.request.contextPath }/home"
			class="btn btn-primary pull-right">Continue Shopping</a>
	</div>
	<div class="span9">
		<h4 class="title">
			<span class="text"><strong>Your</strong> Cart </span>
		</h4>
	<form action="${payPalConfig.posturl}" method="post" target="_top">
	
	<!-- Paypal Setting -->
	<input type="hidden" name="cmd" value="_cart" />
	<input type="hidden" name="upload" value="1" />
	<input type="hidden" name="return" value="${payPalConfig.returnurl }" />
	<input type="hidden" name="business" value="${payPalConfig.business }" />
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Remove</th>
					<th>Product</th>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${sessionScope.cart }" varStatus="i">
					<c:set var="photo"
						value="${item.product.getPhotos().stream().filter(p -> p.isStatus() && p.isMain()).findFirst().get() }"></c:set>
					<tr>
						<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/cart/remove/${i.index}">
						<i class="icon icon-remove icon-white">
						</i></a>
						</td>
						<td><a href="${pageContext.request.contextPath }/product/details/${item.product.id }"><img alt=""
								src="${pageContext.request.contextPath }/uploads/images/${photo.name }" width="80px"></a></td>
						<td>${item.product.name }</td>
						<td><input type="number" class="input-mini" min="1" name="quantities" value="${item.quantity}" max="${item.product.quantity}"> <input type="submit" class="btn btn-info pull-right" formaction="${pageContext.request.contextPath }/cart/update " value="Update"></td>
						<td>$${item.product.price }</td>
						<td>$${item.product.price * item.quantity  }</td>
						
						<input type="hidden" name="item_name_${i.index + 1 }" value="${item.product.name }"/>
                        <input type="hidden" name="quantity_${i.index + 1 }" value="${item.quantity }"/>
                        <input type="hidden" name="amount_${i.index + 1 }"
                               value="${item.product.price }"/>
                               
					</tr>
				</c:forEach>				
			</tbody>
		</table>
		<hr>
		<h5 class="cart-total right">
			Total: <strong style="color: #bd362f">$${total }</strong>
		</h5>
		
		<input type="image" src="http://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" border="0"
                   name="submit" alt="PayPal ? The safer, easier way to pay online.">
		<a class="btn btn-inverse pull-right"
			href="${pageContext.request.contextPath }/cart/confirm">Checkout</a>
	</form>	
	</div>
	
	<div class="span3 col">
		<mt:categoriesList />

	</div>
	
</div>
<script type="text/javascript">
	$("#menu-filter").accordion();
</script>