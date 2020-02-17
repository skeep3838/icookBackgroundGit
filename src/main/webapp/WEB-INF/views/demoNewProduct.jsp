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

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">新增商品</h1>
						<!--             <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
					</div>
					<!-- 以下改寫 -->

<!-- 					<form method="post" action="createNewProduct"> -->
					<form id='insertForm' method="post" action="createNewProduct"
						enctype="multipart/form-data">
						<table>
							<tr>
								<td colspan="2">商品名稱
								<td colspan="2"><input type="text" name="productName">
									<!-- 							</tr> --> <!-- 							<tr> --> <%--商品種類以寫死為主 --%>
								<td colspan="2">商品分類
								<td colspan="2"><select id="test" name="category">
										<option>肉類</option>
										<option>菜類</option>
										<option>鍋具</option>
										<option>調味料</option>
										<option>貓貓happy</option>
										<option>旺旺happy</option>
								</select>
							</tr>

							<tr>
								<td colspan="2">商品規格
								<td colspan="2"><input type="text" name="typeTitle"><input
									type="button" value="新增">
								<td>售價
								<td><input type="text" size="4" name="unitPrice">
								<td>庫存
								<td><input type="text" size="4" name="unitStock">
							</tr>

							<tr>
								<td colspan="2">商品規格
								<td colspan="2"><input type="text" name="typeTitle">
								<td>售價
								<td><input type="text" size="4" name="unitPrice">
								<td>庫存
								<td><input type="text" size="4" name="unitStock">
							</tr>

							<tr>
								<td colspan="2">商品規格
								<td colspan="2"><input type="text" name="typeTitle">
								<td>售價
								<td><input type="text" size="4" name="unitPrice">
								<td>庫存
								<td><input type="text" size="4" name="unitStock">
							</tr>

							<tr>
								<td colspan="2">商品描述
								<td colspan="6">
								<textarea id='test1' name="productInfo" ></textarea>
<!-- 								<textarea id='test1' name="productInfo" style="width: 500px; height: 180px;"></textarea> -->
							</tr>

							<tr>
								<td colspan="2">上傳圖片
								<td colspan="4"><input type="file" name="image1"
									id="image100">
								<td colspan="2"><img id="testImg1" class="testImgx" src="">
							</tr>
							<tr>
								<td colspan="2">上傳圖片
								<td colspan="4"><input type="file" name="image1"
									id="image200">
								<td colspan="2"><img id="testImg2" class="testImgx" src="">
							</tr>
							<tr>
								<td colspan="2">上傳圖片
								<td colspan="4"><input type="file" name="image1"
									id="image300">
								<td colspan="2"><img id="testImg3" class="testImgx" src="">
							</tr>
							<tr>
								<td colspan="2"><input type="submit" onclick='openWait()' value="送出">
<!-- 								<td colspan="2"><input type="button" onclick='openWait()' value="送出"> -->
								<td colspan="6"><input type="reset" value="重置">
							</tr>

						</table>
					</form>
					
					<script>
					<!-- 預覽圖片功能 -->
						function readURL100(input) {
							if (input.files && input.files[0]) {
								var reader = new FileReader();

								reader.onload = function(e) {
									$('#testImg1').attr('src', e.target.result);
								}

								reader.readAsDataURL(input.files[0]);
							}
						}

						$("#image100").change(function() {
							readURL100(this);
						});

						/*土法煉鋼一下*/
						function readURL200(input) {
							if (input.files && input.files[0]) {
								var reader = new FileReader();

								reader.onload = function(e) {
									$('#testImg2').attr('src', e.target.result);
								}

								reader.readAsDataURL(input.files[0]);
							}
						}

						$("#image200").change(function() {
							readURL200(this);
						});

						function readURL300(input) {
							if (input.files && input.files[0]) {
								var reader = new FileReader();

								reader.onload = function(e) {
									$('#testImg3').attr('src', e.target.result);
								}

								reader.readAsDataURL(input.files[0]);
							}
						}

						$("#image300").change(function() {
							readURL300(this);
						});
						
						
						CKEDITOR.replace('test1', {
							width: 1000,
							height: 500
						});
					</script>

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
