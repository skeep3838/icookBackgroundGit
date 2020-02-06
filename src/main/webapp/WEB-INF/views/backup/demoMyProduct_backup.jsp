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
table, tr, td {
	/* 	margin: auto; */
	/* 	border: 1px red solid; */
	/* 	text-align:center; */
	
}

textarea {
	width: 300px;
	height: 100px;
}

.testImgx {
	max-width: 150px;
	max-height: 150px;
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
							<li><a href="#tabs-1">上架商品</a></li>
							<li><a href="#tabs-2">下架商品</a></li>

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
							<div class="card-body" style="width: 75%">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>產品名稱</th>
												<th>產品類型</th>
												<th>平均售價</th>
												<th>最低庫存量</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>產品名稱</th>
												<th>產品類型</th>
												<th>平均售價</th>
												<th>最低庫存量</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</tfoot>
										<tbody>
											<tr>
												<td>雞肉</td>
												<td>肉類</td>
												<td>333</td>
												<td>20</td>
												<td><button>修改</button></td>
												<td><button>刪除</button></td>
											</tr>
											<tr>
												<td>平底鍋</td>
												<td>鍋具</td>
												<td>9716</td>
												<td>2</td>
												<td><button>修改</button></td>
												<td><button>刪除</button></td>
											</tr>
											<tr>
												<td>高麗菜</td>
												<td>菜類</td>
												<td>450</td>
												<td>20</td>
												<td><button>修改</button></td>
												<td><button>刪除</button></td>
											</tr>
										</tbody>
									</table>
								</div>
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
							<div class="card-body" style="width: 75%">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>產品名稱</th>
												<th>產品類型</th>
												<th>平均售價</th>
												<th>最低庫存量</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>產品名稱</th>
												<th>產品類型</th>
												<th>平均售價</th>
												<th>最低庫存量</th>
												<th>修改</th>
												<th>刪除</th>
											</tr>
										</tfoot>
										<tbody>
											<tr>
												<td>測試</td>
												<td>測試</td>
												<td>11111</td>
												<td>11</td>
												<td><button>修改</button></td>
												<td><button>刪除</button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->

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
		<script src="vendor/chart.js/Chart.min.js"></script>

		<!-- Page level custom scripts -->
		<script src="js/demo/chart-area-demo.js"></script>
		<script src="js/demo/chart-pie-demo.js"></script>

		<!-- 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>

</html>
