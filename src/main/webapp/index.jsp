<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./View/include/include.jsp"%> 
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Themify Icons-->
	<link rel="stylesheet" href="css/themify-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
		
	<div class="gtco-loader"></div>
	
	<div id="page">

	
	<div class="page-inner">
	<nav class="gtco-nav" role="navigation">
		<div class="gtco-container">
			
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<div id="gtco-logo"><a href="index.html"><img src="images/logo2.png"></a></div>
				</div>
		
			</div>
			
		</div>
	</nav>
	
	<header id="gtco-header" class="gtco-cover" role="banner" >
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					

					<div class="row row-mt-15em">
						<div class="col-md-6 mt-text animate-box" data-animate-effect="fadeInUp">
							
							<h1>æ¬¢è¿æ³¨åæä¸ºåºå è¯´ç®¡çå.</h1>	
						</div>
						<div class="col-md-5 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu">
										<li class="active gtco-first"><a href="#" data-tab="signup">æ³¨å</a></li>
										<li class="gtco-second"><a href="#" data-tab="login">ç»å½</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-content-inner active" data-content="signup">
											<form action="#">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">ç¨æ·å</label>
														<input type="text" class="form-control" id="username" 
														 onblur="checkname()" placeholder="ç¨æ·åç±è±æå­æ¯åæ°å­ç»æç6-16ä½å­ç¬¦ç»æ">
														<div id="uname"></div>
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">å¯ç </label>
														<input type="password" id="password" name="pwd" class="form-control" 
														tabindex="2" onblur="checkuserpassword()"/>
														<div id="upassword"></div>
														
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password2">åæ¬¡è¾å¥ç¡®è®¤å¯ç </label>
														<input type="password" class="form-control" id="txtpwdagin" placeholder="ä¸¤æ¬¡å¯ç è¾å¥å¿é¡»ä¸è´" onblur="checkpwdagin()">
														<div id="pwdagin"></div>
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password2">è¾å¥ææºå·</label>
														<input type="text" class="form-control" id="txttelephone" placeholder="è¯·è¾å¥çå®ææºå·ä»¥ä¾¿æ¥æ¶éªè¯ç " onblur="checktelephone()">
														<div id="telephone"></div>
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">è¾å¥éªè¯ç </label>
														<table>
															<tr>
														    <td> <input type="text" class="form-control" id="ecode" ></td>
														    <td style="padding-left: 20px;"> <input type="submit" class="btn btn-primary" value="åééªè¯ç "></td>
															</tr>
															
														</table>
													</div>
													<div id="telephone"></div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="submit" class="btn btn-primary" value="æ³¨å">
													</div>
												</div>
											</form>	
										</div>

										<div class="tab-content-inner" data-content="login">
											<form action="#">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">ç¨æ·å</label>
														<input type="text" class="form-control" id="username" onblur="checkname()">
														<div id="uname"></div>
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">å¯ç </label>
														<input type="password" class="form-control" id="password">
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="submit" class="btn btn-primary" value="ç»å½">
													</div>
												</div>
											</form>	
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
							
					
				</div>
			</div>
		</div>
	</header>
	
	
	</div>

	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	<script src="js/register.js"></script>
	
	</body>
</html>

