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
		textarea {
			width: 300px;
			height: 100px;
		}

		.testImgx {
			max-width: 150px;
			max-height: 150px;
		}

		.fc-day-grid-event .fc-time {
			/* font-weight: 700; */
			font-size: 0;
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
	<!-- 日曆專用 -->
	<!-- 主檔案的css，此套件有很多不同的css可以使用 -->
	<link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.css' rel='stylesheet' />
	<!-- daygrid maincss -->
	<link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.css' rel='stylesheet' />
	<!-- core .js -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.js'></script>
	<!-- plugins-daygrid -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/interaction/main.js'></script>
	<!-- 	抓時間用的API -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>

</head>

<body>
	<div id="dialog_div" title="新增課程時間"></div>
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

					<div class="services-breadcrumb">
						<div class="container">
							<ul>
								<li>
									<確認上課教室及開課日期>
								</li>
							</ul>
						</div>
					</div>

					<div class='container'>
						<div class="row">
							<div class="col-sm-4">
								<form method="post" action="${pageContext.request.contextPath}/course/courseAdd1"
									enctype="multipart/form-data">
									<!-- 					輸入課程名稱 -->
									<table>
										<tr>
											<td><input class="form-control" type="text" name='courseName' id='courseName'
													placeholder="請輸入課程名稱" required="required"
													style="height: 35px; width: 300px;">
										<tr>
											<td><select class="form-control" style="height: 35px; width: 300px;"
													name="roomNo" id="roomNo" onchange="roomJson123()"
													required="required">
													<option value="" disabled selected hidden>上課教室</option>
													<c:forEach var='bean' items='${roomBean}'>
														<option value='${bean.roomNo}'> ${bean.roomNo} 容納人數:
															${bean.roomCapacity}</option>
													</c:forEach>
												</select>
												<!-- 					取得教室資訊 -->
												<input type="hidden" id=courJson>${courseDate.get(bean.roomNo)}
												<div>
													<div class="courseDate" id="courseDate">
														<span id="showStartDate">課程日期: </span>
													</div>
													<div>
														<span> 　上課時間: </span>
														<select class="form-control"
															style="height: 35px; width: 70px;display:inline-block"
															name="courseH" id="courseH" required="required">
															<option value="" disabled selected hidden>時</option>
															<c:forEach var="i" begin="9" end="16">
																<option value='${i}'>${i}</option>
															</c:forEach>
														</select>
														<span> :　</span>
														<select class="form-control"
															style="height: 35px; width: 70px;display:inline-block"
															name="courseM" id="courseM" required>
															<option value="" disabled selected hidden>分</option>
															<option value='00'>00</option>
															<option value='30'>30</option>
														</select>
													</div>
													<div>
														<span> 　上課時數: </span>
														<select class="form-control"
															style="height: 35px; width: 55px;display:inline-block"
															name="courseHour" id="courseHour" required>
															<c:forEach var="i" begin="1" end="6" step="1">
																<option value='${i}'>${i}</option>
															</c:forEach>
														</select>
														<span>小時</span>
													</div>

													<input type="hidden" id="courseStartDate" name="courseStartDate" class="btn btn-secondary">
													<input type=button value="一鍵輸入" onclick="keyIn1()" class="btn btn-secondary">
													<input type=submit value="下一步" onclick="checkDate()" class="btn btn-secondary">
													<input type="button" onclick="clearAll()" value="重新設定" class="btn btn-secondary">
												</div>


									</table>
								</form>
							</div>
							<div class="col-sm-8">
								<!-- 				<div id="dialog_div" title="新增課程時間"></div></div> -->
								<div id="calendar" style="width:100%;"></div>
								<!-- 					包到行事曆之前 -->
							</div>
						</div>
					</div>

					<!-- <div>
						<c:forEach var='key' items='${courseDate.keySet()}'>
							<h5>${key} - ${courseDate.get(key)}</h5>
						</c:forEach>
					</div> -->

				</div>
				<!-- Begin Page Content --End -->
			</div>
			<!-- Main Content --End-->
		</div>
		<!-- Content Wrapper --End-->
	</div>
	<!-- Page Wrapper --End-->

	<!-- 	自己寫的JS -->
	<script src="${pageContext.request.contextPath}/js/courseJs.js"></script>
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
	<script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>

</html>