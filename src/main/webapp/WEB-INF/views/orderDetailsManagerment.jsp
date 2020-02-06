<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, java.text.SimpleDateFormat, java.util.Date,com.icookBackstage.model.orderDetail, com.icookBackstage.model.orderBean, com.icookBackstage.model.MemberBean"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

.choosepage {
	position: absolute;
	bottom: 45px;
	left: 50%;
}

@media ( min-width : 991px) {
	.div-height {
		/* 			width:3000px;  */
		height: 580px;
	}
}

ul.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
}
</style>

<title>Demo_orderManage</title>

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
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/fragment/SideBar.jsp" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="/WEB-INF/views/fragment/TopBar.jsp" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<div class="services-breadcrumb">
						<div class="container">
							<ul>
								<li>< 訂單明細 ></li>
							</ul>
						</div>
					</div>
					<div class="bs-docs-example" align='center'>
						<table class="table">
							<thead>
								<tr>
									<th>產品編號</th>
									<th>產品名稱</th>
									<th>數量</th>
									<th>定價</th>
									<th>折扣</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach varStatus="i" var="orderDetails" items="${orderDetailsData}">
									<tr><td>${orderDetails.productId}
										<td>${orderDetails.describe}
										<td>${orderDetails.qty}
										<td>${orderDetails.unitPrice}
										<td>${orderDetails.discount}
								</c:forEach>
							</tbody>
						</table>
						<input class='btn btn-default btn-secondary' type='button' onclick="javascript:location.href='${pageContext.request.contextPath}/ManagerOrders?page=${page}'" value='返回訂單'  />
						</div>

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
					<script src="vendor/datatables/jquery.dataTables.min.js"></script>
					<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
</body>

</html>