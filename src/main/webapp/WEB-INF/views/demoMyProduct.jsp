<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />


<style>
textarea {
	width: 300px;
	height: 100px;
}

.testImgx {
	max-width: 150px;
	max-height: 150px;
}

@media ( min-width :991px) {
	.div-height {
		/* 			width:3000px;  */
		height: 540px;
	}
}

ul.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
}

ul.pagination li {
	display: inline;
}

ul.pagination li a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
	border: 1px solid #ddd;
}

ul.pagination li a.active {
	background-color: #1e90ff;
	color: white;
	border: 1px solid #1e90ff;
}

ul.pagination li a:hover:not (.active ) {
	background-color: #ddd;
}

div.center {
	text-align: center;
}

div.dataTables_paginate {
	margin: 0;
	white-space: nowrap;
	text-align: right;
}

.images {
	display: none;
}

.viewImgClass {
	width: 150px;
	height: 150px;
	object-fit: cover;
	margin-right: 10px;
}

.viewImgTd {
	width: 33%;
}


#dialog_div_wait,#dialog_div_error {
	text-align : center;
}

#dialog_div_wait img{
	margin-top : 50px;
	vertical-align : middle;
}

.errorFont{
	line-height : 150px;
	color : red;
	font-weight : bold;
}

.imgDiv{
	margin-right:10px;
	float:left;
}

</style>

<title>Demo_MyProduct</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
<!-- 	加入文字編輯器ckEditor -->
<script src="https://cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>

<!-- dataTables的CSS -->
<!-- <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"> -->

<!-- jqueryUi -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>

<body id="page-top">

	<!-- <!-- test area -->
	<!-- <img src='/icookBackstage02035/images/雞肉1.jpg'> -->

	<!-- dialog area -->
	<div id="dialog_div_update" title="ProductDetail"></div>
	<div id="dialog_div_wait" title="Wait Upload"></div>
	<div id="dialog_div_error" title="error"></div>
	<div id="dialog_div_ok" title="ok"></div>

	
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/fragment/SideBar.jsp" />
		<!-- Sidebar End -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="/WEB-INF/views/fragment/TopBar.jsp" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">商品查詢</h1>
						<!--             <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
					</div>
					<!-- 以下改寫 -->
					<div id="tabs">
						<ul>
							<li id="tabsTag1"><a href="#tabs-1">上架商品</a></li>
							<li id="tabsTag2"><a href="#tabs-2">下架商品</a></li>

						</ul>
						<div id="tabs-1">
							<div style="margin-left: 500px;"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
								<div class="input-group">
									<input type="text" class="form-control bg-light border-0 small searchInput"
										placeholder="請輸入商品ID" aria-label="Search"
										aria-describedby="basic-addon2" id='searchIn1'>
									<div class="input-group-append">
										<button class="btn btn-primary search" type="button">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
							<!-- tabs-1資料顯示區 -->
							<div>
								<div id="test1"></div>
								<div class="row" id="pageArea1"></div>
							</div>
							<div></div>

						</div>
						<div id="tabs-2">
							<!-- 搜尋欄 -->
							<div style="margin-left: 500px;"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
								<div class="input-group">
									<input type="text" class="form-control bg-light border-0 small searchInput"
										placeholder="請輸入商品ID" aria-label="Search"
										aria-describedby="basic-addon2" id='searchIn2' >
									<div class="input-group-append">
										<button class="btn btn-primary search" type="button" >
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
							<!-- tabs-2資料顯示區 -->
							<div>
								<div id="test2"></div>
								<div class="row" id="pageArea2"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->

		</div>
	</div>
	<!-- End of Main Content -->
	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<!-- 		<script src="vendor/chart.js/Chart.min.js"></script> -->

	<!-- Page level custom scripts -->
	<!-- 		<script src="js/demo/chart-area-demo.js"></script> -->
	<!-- 		<script src="js/demo/chart-pie-demo.js"></script> -->

	<!-- 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<!-- myJavaScript(products) -->
	<script src="js/productUse.js"></script>


</body>

</html>
