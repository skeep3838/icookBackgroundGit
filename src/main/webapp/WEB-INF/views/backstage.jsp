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

<title>Demo</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
	rel="stylesheet" type="text/css">

</head>


<body id="page-top">
<!-- Test area -->

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/fragment/SideBar.jsp" />
		<!-- Sidebar End -->

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
						<h1 class="h3 mb-0 text-gray-800">代辦事項</h1>
						<!--             <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div style="text-align: center;" class="col mr-2">
											<div style="font-size: 25px;"
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">未處裡訂單</div>
											<div style="font-size: 25px;"
												class="h5 mb-0 font-weight-bold text-gray-800">25筆</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div style="text-align: center;" class="col mr-2">
											<div style="font-size: 25px;"
												class="text-xs font-weight-bold text-success text-uppercase mb-1">庫存量不足商品</div>
											<div style="font-size: 25px;"
												class="h5 mb-0 font-weight-bold text-gray-800">10項</div>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div style="text-align: center;" class="col mr-2">
											<div style="font-size: 25px;"
												class="text-xs font-weight-bold text-warning text-uppercase mb-1">尚未回應的訊息</div>
											<div style="font-size: 25px;"
												class="h5 mb-0 font-weight-bold text-gray-800">18筆</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Bootstrap core JavaScript-->
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

					<!-- Core plugin JavaScript-->
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

					<!-- Custom scripts for all pages-->
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

					<!-- Page level plugins -->
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

					<!-- Page level custom scripts -->
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>
</body>

</html>