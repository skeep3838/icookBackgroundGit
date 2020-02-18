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

<style>
table, tr, td {
	/* 	margin: auto; */
	/* 	border: 1px red solid; */
	/* 	text-align:center; */
	
}

/* textarea { */
/* 	width: 300px; */
/* 	height: 100px; */
/* } */

.testImgx {
	max-width: 150px;
	max-height: 150px;
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

.card {
    box-shadow: 0 0 1px rgba(0,0,0,.125), 0 1px 3px rgba(0,0,0,.2);
    margin-bottom: 1rem;
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
.errorFont{
	line-height : 150px;
	color : red;
	font-weight : bold;
}

.imgDiv{
	margin-right:10px;
	float:left;
}
/* #cke_1_contents{ */
/* 	width: 100%;   */
/*  	height: 1200px;   */
/* } */
</style>

<title>Demo_NewProduct</title>

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
	

<!-- jqueryUi -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
<!-- 	加入文字編輯器ckEditor -->
<script src="https://cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>
<!-- <script src="ckeditor/ckeditor.js"></script> -->
</head>

<body id="page-top">
	<!-- dialog area -->
	<div id="dialog_div_wait" title="Wait Upload"></div>
	<div id="dialog_div_error" title="error"></div>

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
			<!-- 以下改寫 -->
			
			<div class="container-fluid">
				<div class="col-sm-10">
					<div class='card card-success'>
						<div class="card-header">
							<h3 class="card-title">新增商品</h3>
						</div>
						<div class="card-body">
							<form role="form" id='formProduct' action="createNewProduct" method="post" enctype="multipart/form-data">
								<table>
									<tr>
										<td style='width: 500px;padding-right: 50px;'>
											<div class='form-group'>
												<label>商品名稱</label>
												<input type='text' class='form-control' name="productName">
											</div>
										</td>
										<td style=" width: 200px;padding-right: 50px;">
											<div class='form-group'>
												<label>商品總類</label>
												<select class='form-control' name="category">
													<option>肉類</option>
													<option>鍋具</option>
													<option>菜類</option>
													<option>咖啡</option>
													<option>家電</option>
													<option>麵包</option>
													<option>乾貨</option>
													<option>餐廚</option>
													
												</select>
											</div>
										</td>
									</tr>
								</table>
							
								<div id='typeGroup0'>
									<table>
										<tr>
											<td style='width: 500px; padding-right: 50px;'>
												<div class='form-group'>
													<label>商品規格</label>
													<input type='text' class='form-control' name="typeTitle">
												</div>
											</td>
											<td style='width: 200px; padding-right: 50px;'>
												<div class='form-group'>
													<label>售價</label>
													<input type='text' class='form-control'  name="unitPrice">
												</div>
											</td>
											<td style='width: 200px;  padding-right: 50px;' >
												<div class='form-group'>
													<label>庫存</label>
													<input type='text' class='form-control' name='unitStock'>
												</div>
											</td>
											<td style='width: 100px;' align='center' valign="middle">
												<div class='form-group'>
													<input type='button' class="btn btn-secondary" onclick='deleteType(0)' value='移除類型'>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div style='width: 100%;' align='center' valign="middle" id="addButton">
									<input  style='padding:0px' type='button' class='btn btn-primary  btn-lg btn-block' 
									onclick="addType()" value='新增商品類型'>
								</div>
								<br>
								<div>
									<span>新增圖片(最多五張)</span>
								</div>
								<div class='imgDiv' id='imgDiv1'>
									<label for='img1'>
										<input type='file' name='image1' index='1' id='img1' class='images'>
										<img class='viewImgClass' id='viewImg1' src="images/addPicture.png">
									</label>
								</div>
								<div style='clear:both;'>
								</div>
								<div style='width: 100%;'>
									<div class='form-group'>
										<label>商品資訊</label>
										<textarea id='test1' name="productInfo" ></textarea>
									</div>
								</div>
								<div style='text-align:right'>
								<input type="button" class="btn btn-primary" onclick='speetIn()' value='一鍵輸入'></input>
								<input type="button" class="btn btn-primary" onclick='waitSubmit()' value='新增商品'></input>
								</div>
							</form>
						</div>
					</div>
				</div>
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
					
					<!-- 		<script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
					<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
					<!-- myJavaScript(products) -->
					<script src="js/productUse2.js"></script>
</body>

</html>
