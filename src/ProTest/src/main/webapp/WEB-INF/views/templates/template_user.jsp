<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mt" uri="http://localhost:9596/ProTest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Project4 - Beauty Flowers</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/user/bootstrap/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath }/resources/user/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/user/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/user/themes/css/bootstrappage.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/user/themes/css/font-awesome.min.css">
<!-- <link rel="stylesheet" type="text/css" href="themes/css/font-awesome.min2.css"> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/user/themes/css/font-awesome.min3.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
	integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg"
	crossorigin="anonymous">
<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/user/themes/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath }/resources/user/themes/css/jquery.fancybox.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/user/themes/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath }/resources/user/themes/css/flexslider.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath }/resources/user/themes/css/main.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/user/themes/css/jquery-ui.min.css">

<!-- scripts -->
<script
	src="${pageContext.request.contextPath }/resources/user/themes/js/jquery-1.7.2.min.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/user/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/user/themes/js/superfish.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/user/themes/js/jquery.scrolltotop.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/user/themes/js/jquery.fancybox.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/user/themes/js/jquery-ui.min.js"></script>
<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div id="top-bar" class="container">
		<div class="row">
			<div class="span6">

				<mt:searchTag />

			</div>
			<div class="span6">
				<div class="account pull-right">
					<ul class="user-menu">
						<li><mt:cartTag /></li>

						<c:if test="${pageContext.request.userPrincipal.name != null}">

							<li class="dropdown notifications-menu open"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown"
								aria-expanded="true"> <i class="fa fa-bell-o"></i>Welcome
									${pageContext.request.userPrincipal.name } !
							</a>
								<ul class="dropdown-menu">
									<li>
										<!-- inner menu: contains the actual data -->
										<ul class="menu">
											<li><a
												href="${pageContext.request.contextPath }/customer/profile"><i
													class="icon-user"></i> Profile </a></li>
											<li><a
												href="${pageContext.request.contextPath }/customer/history"><i
													class="icon-book"></i> History Order</a></li>
											<li><a
												href="${pageContext.request.contextPath }/customer/process-logout"><i
													class="fas fa-sign-out-alt"></i> Logout </a></li>
										</ul>
								</ul></li>


						</c:if>
						<c:if test="${pageContext.request.userPrincipal.name == null}">
							<li><a
								href="${pageContext.request.contextPath }/customer-panel">Login
							</a></li>
						</c:if>

					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="wrapper" class="container">
		<section class="navbar main-menu">
			<div class="navbar-inner main-menu">
				<a href="${pageContext.request.contextPath }/home"
					class="logo pull-left"><h4 class="title">Beauty Flowers</h4></a>
				<nav id="menu" class="pull-right">
					<ul>
						<li><a href="#">Contact</a></li>
					</ul>
				</nav>
			</div>
		</section>

		<c:if test="${isHome != null }">
			<mt:slider />
		</c:if>

		<br>


		<!-- Messenger Plugin chat Code -->
    <div id="fb-root"></div>

    <!-- Your Plugin chat code -->
    <div id="fb-customer-chat" class="fb-customerchat">
    </div>

    <script>
      var chatbox = document.getElementById('fb-customer-chat');
      chatbox.setAttribute("page_id", "114024883792584");
      chatbox.setAttribute("attribution", "biz_inbox");

      window.fbAsyncInit = function() {
        FB.init({
          xfbml            : true,
          version          : 'v11.0'
        });
      };

      (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    </script>
		
		<section class="main-content">
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</section>
		<hr>
		<div class="row feature_box">
			<div class="span4">
				<div class="service">
					<div class="responsive">
						<img
							src="${pageContext.request.contextPath }/resources/user/themes/images/feature_img_2.png"
							alt="">
						<h4>
							MODERN <strong>DESIGN</strong>
						</h4>
						<p>The products are designed according to modern and trendy
							designs in accordance with current user trends.</p>
					</div>
				</div>
			</div>
			<div class="span4">
				<div class="service">
					<div class="customize">
						<img
							src="${pageContext.request.contextPath }/resources/user/themes/images/feature_img_1.png"
							alt="">
						<h4>
							FREE <strong>SHIPPING</strong>
						</h4>
						<p>Products are delivered quickly, neatly in many forms to
							satisfy customers' requirements well.</p>
					</div>
				</div>
			</div>
			<div class="span4">
				<div class="service">
					<div class="support">
						<img
							src="${pageContext.request.contextPath }/resources/user/themes/images/feature_img_3.png"
							alt="">
						<h4>
							SERVICE <strong>24/7</strong>
						</h4>
						<p>Customers are always first priority and are available 24/7.</p>
					</div>
				</div>
			</div>
		</div>

		<section style="background-color: #857474;" id="footer-bar">
			<div class="row">
				<div class="span3">
					<h4>Directional</h4>
					<ul class="nav">
						<li><a href="${pageContext.request.contextPath }/home">Home
								Page</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="${pageContext.request.contextPath }/cart/index">Cart</a></li>
						<li><a
							href="${pageContext.request.contextPath }/customer-panel/login">Login</a></li>
					</ul>
				</div>
			
				<div class="span4">
					<h4>Contact</h4>

					<div class="contacts-info" style="color: white">
						<address>
							<i class="glyphicon glyphicon-map-marker">&nbsp;</i>Address: 590 CMT, Ward 10, Dict 3.
						</address>
						<p class="phone-footer">
							<i class="phone-icon">&nbsp;</i>Phone: 0359313750
						</p>
						<div class="email-footer">
							<i class="fa fa-mail"></i> <a
								href="mailto:hieuzxc00@gmail.com">Mail: hieuzxc00@gmail.com</a>
						</div>
						

					</div>
				</div>
				<div class="span5">
					<p class="logo">
						<!-- <img src="themes/images/logo.png" class="site_logo" alt=""> -->
					<h4>Facebook</h4>
					</p>
					<div class="fb-page"
						data-href="https://www.facebook.com/PetShop-114024883792584/"
						data-tabs="timeline" data-width="" data-height="100"
						data-small-header="false" data-adapt-container-width="true"
						data-hide-cover="false" data-show-facepile="true">
						<blockquote
							cite="https://www.facebook.com/PetShop-114024883792584/"
							class="fb-xfbml-parse-ignore">
							<a href="https://www.facebook.com/PetShop-114024883792584/">PetShop</a>
						</blockquote>
					</div>
				</div>
				
			</div>
		</section>
		<section id="copyright">
			<span>Copyright 2021 Project4 (Hieu - Huan - Phu)</span>
		</section>
	</div>
	<script
		src="${pageContext.request.contextPath }/resources/user/themes/js/common.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/user/themes/js/jquery.flexslider-min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(document).ready(function() {
				$('.flexslider').flexslider({
					animation : "fade",
					slideshowSpeed : 4000,
					animationSpeed : 600,
					controlNav : false,
					directionNav : true,
					controlsContainer : ".flex-container" // the container that holds the flexslider
				});
			});
		});
	</script>
	<script>
		$(function() {
			$('#myTab a:first').tab('show');
			$('#myTab a').click(function(e) {
				e.preventDefault();
				$(this).tab('show');
			})
		})
		$(document).ready(function() {
			$('.thumbnail').fancybox({
				openEffect : 'none',
				closeEffect : 'none'
			});

			$('#myCarousel-2').carousel({
				interval : 2500
			});
		});
	</script>
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v8.0"
		nonce="pxPJ7k8V"></script>
</body>
</html>