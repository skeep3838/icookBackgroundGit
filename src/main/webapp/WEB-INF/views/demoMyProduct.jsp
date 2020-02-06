<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<style>
/* ul.pagination { */
/*     margin: 2px 0; */
/*     white-space: nowrap; */
/*     justify-content: flex-end; */
/* } */
/* .pagination { */
/*     display: flex; */
/*     padding-left: 0; */
/*     list-style: none; */
/*     border-radius: .35rem; */
/* } */

textarea {
	width: 300px;
	height: 100px;
}

.testImgx {
	max-width: 150px;
	max-height: 150px;
}


@media(min-width:991px){
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

ul.pagination li a:hover:not(.active) { 
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

<!-- dataTables的CSS -->
<!-- <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"> -->

<!-- 測試 -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>

<body id="page-top">

<!-- test area -->
<!-- <div id="dialog_div" title="視窗的標題">內容，亦可為form</div> -->
<!-- <button id='opener'>open</button> -->

<!-- dialog area -->
<div id="dialog_div" title="ProductDetail">
</div>

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
							<form style="margin-left: 500px;"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
								<div class="input-group">
									<input type="text" class="form-control bg-light border-0 small"
										placeholder="Search for..." aria-label="Search"
										aria-describedby="basic-addon2">
									<div class="input-group-append">
										<button class="btn btn-primary" type="button">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</form>
							<!-- tabs-1資料顯示區 -->
							<div>
								<h4> tabs-1資料顯示區 </h4>
<!-- 								<span id="test1"> -->
<!-- 								</span> -->
								<div id="test1">
								</div>
								<div class="row" id="pageArea1">
								</div>
							</div>
							<div>
							</div>
							
						</div>
						<div id="tabs-2">
							<form style="margin-left: 500px;"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
								<div class="input-group">
									<input type="text" class="form-control bg-light border-0 small"
										placeholder="Search for..." aria-label="Search"
										aria-describedby="basic-addon2">
									<div class="input-group-append">
										<button class="btn btn-primary" type="button">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</form>
							<!-- tabs-2資料顯示區 -->
							<div>
								<h4> tabs-2資料顯示區 </h4>
<!-- 								<span id="test2"> -->
<!-- 								</span> -->
								<div id="test2">
								</div>
								<div class="row" id="pageArea2">
								</div>
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
