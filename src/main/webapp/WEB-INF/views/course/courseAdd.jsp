<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
	</style>

	<title>Demo_MyProduct</title>

	<!-- Custom fonts for this template-->
	<link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
		type="text/css">
	<link
		href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
		rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

	<!-- dataTables的CSS -->
	<!-- <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"> -->

	<!-- 測試 -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

	<!-- 	抓時間用的API -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
</head>

<body>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- 側欄 -->
		<jsp:include page="/WEB-INF/views/fragment/SideBar.jsp" />
		<!-- 側欄 End -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- Topbar -->
				<jsp:include page="/WEB-INF/views/fragment/TopBar.jsp" />
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- 標頭 -->
					<!-- Page Heading -->
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">新增課程</h1>
						<!--             <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
					</div>
					<!-- 標頭 End -->
					<!-- 					新增課程表格 -->
					<form method="POST" action="${pageContext.request.contextPath}/course/courseAdd2" enctype="multipart/form-data">
						<table>
							<tr>
								
								<td colspan="2">課程類別
								<td colspan="2">
									<select class="custom-select" style="height: 35px; width: 200px;" name="courseCategory">
										<option value="" disabled selected hidden>課程類別</option>	
										<option value='中式'>中式</option>
										<option value='義式'>義式</option>
										<option value='法式'>法式</option>
										<option value='日式'>日式</option>
										<option value='創意'>創意</option>
										<option value='證照班'>證照班</option>
									</select>

							</tr>
							<tr>
								<td colspan="2">上傳圖片
								<td colspan="4"><input type="file" name="courseImage" id="image100">
								<td colspan="2"><img id="testImg1" class="testImgx" src="">
							</tr>
							<tr>
								<td colspan="2">課程價格
								<td colspan="2"><input type="text" name="coursePrice">
								<td colspan="2">課程優惠
								<td colspan="2">
									<select class="custom-select" style="height: 35px; width: 200px;" name="courseDiscount">
										<option value="" disabled selected hidden>課程優惠</option>	
										<option value='1'>無折扣</option>
										<option value='0.95'>95折</option>
										<option value='0.9'>9折</option>
										<option value='0.85'>85折</option>
										<option value='0.8'>8折</option>
										<option value='0.75'>75折</option>
										<option value='0.7'>7折</option>
									</select>

							</tr>
							<tr>
<!-- 								<td colspan="2">課程負責人 -->
<%-- 								<td colspan="2"><input type="text"  disabled value="${hostId}"> --%>
								<td colspan="2">主辦單位名稱
								<td colspan="2"><input type="text" name="hostName">

							</tr>
							
<!-- 							<tr> -->
<!-- 								<td colspan="2">開賣時間 -->
<!-- 								<td colspan="2"><input type="date" name="saleStartDate"> -->
<!-- 								<td colspan="2"><input type="date" name="saleEndDate"> -->
<!-- 							</tr> -->
							<tr>
								<td colspan="2">課程描述
								<td colspan="6"><textarea name="courseIntrod"
										style="width: 500px; height: 180px;"></textarea>
							</tr>
							<%-- <tr>
								<td colspan="2">上課教室
								<td colspan="2"><input type="text" name="roomNo">
							</tr> --%>
							
							<input type="hidden" name="courseName" id="courseName" value='${courseName}'>
							<input type="hidden" name="roomNo" id="roomNo" value='${roomNo}'>
							<input type="hidden" name="courseStartDate" id="courseStartDate" value='${courseStartDate}'>
							<input type="hidden" name="courseEndDate" id="courseEndDate" value='${courseEndDate}'>	
							<tr>
								<td colspan="2"><input type="submit" value="送出">
								<td colspan="6"><input type="reset" value="重置">
							</tr>
							

						</table>
					</form>
					<div id="calendar"></div>
					

				</div>
				<!-- Begin Page Content --End -->
			</div>
			<!-- Main Content --End-->
		</div>
		<!-- Content Wrapper --End-->
	</div>
	<!-- Page Wrapper --End-->
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
	</script>
	<!-- Bootstrap core JavaScript-->
	<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>
	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
	<!-- Page level plugins -->
	<script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>
	<!-- Page level custom scripts -->
	<script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
	<script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>
<!-- 	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->
</body>

</html>