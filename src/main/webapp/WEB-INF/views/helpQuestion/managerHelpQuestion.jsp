<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, java.text.SimpleDateFormat, java.util.Date,com.icookBackstage.model.orderBean, com.icookBackstage.model.orderBean, com.icookBackstage.model.MemberBean"%>
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
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}

ul

 

.pagination

 

li

 

a


:hover


:not

 

(
.active

 

)
{
background-color


:

 

#ddd


;
}
div.center {
	text-align: center;
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

					<!-- 	----------------------------------------	 -->
					<c:if test="${stat == false}">
						<script>alert('沒有訂單資訊')</script>
					</c:if>
					<div class="services-breadcrumb">
						<div class="container">
							<ul>
								<li>< 客服管理 > 共 ${orderSize} 筆問題</li>
							</ul>
						</div>
					</div>
					<div class="bs-docs-example div-height" align='center'>
						<table id='table1' class="table table-hover">
							<thead>
								<tr>
									<th>問題編號</th>
									<th>會員編號</th>
									<th>Email</th>
									<th>標題</th>
									<th>回覆狀態</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody id='main'>
								<c:forEach varStatus="i" var="Help" items="${HelpQuestionData}"
									begin="${pageNo}" end="${endPage-1}">
									<tr>
										<td>${Help.helpQAId}
										<td>${Help.userID}
										<td style='width: 30%'>${Help.email}
										<td>${Help.title}
										<c:choose>
												<c:when test="${Help.responseStatus == 'N'}">
													<td> 未回覆
												</c:when>
												<c:otherwise>
													<td> 已回覆
												</c:otherwise>
											</c:choose>
										<td><input style='float: right;'
											class='btn btn-default btn-secondary btn-sm' type='button'
											value='問題內容' /> 
											<c:choose>
												<c:when test="${Help.responseStatus == 'N'}">
													<td><input type='button'
														class='btn btn-default btn-secondary btn-sm' disabled
														value='回覆問題' />
												</c:when>
												<c:otherwise>
													<td><input type='button'
														class='btn btn-default btn-secondary btn-sm'
														value='回覆問題' />
												</c:otherwise>
											</c:choose>
								</c:forEach>

							</tbody>
						</table>
					</div>
					<div align='center'>
						<ul class="pagination">
							<li><a href='javascript:'
								onclick="changeTable(${page},${range})">first</a> <c:forEach
									varStatus="i" begin="1" end="${range}">
									<li><a href='javascript:'
										onclick="changeTable(${i.index},${range})">${i.index}</a></li>
								</c:forEach>
							<li><a href='javascript:'
								onclick="changeTable(${range},${range})">last</a>
						</ul>
					</div>
				</div>
				<!-- Begin Page Content -->
			</div>
			<!-- Main Content -->
		</div>
		<!-- Content Wrapper -->
	</div>

	<!-- 	----------------------------------------	 -->
	<script>
			var page = 1;
			var allPage = 1;
			var onePageQuantity = 8;
			//取得自己專案的名稱
			function getRealPath(){
				var curWwwPath=window.document.location.href;
				var pathName=window.document.location.pathname;
				var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);
				return projectName;
			}
			
			$(window).load(function () {
				var qty = $("#table1>tbody").children("tr").length;
				for(var j = 0;j<qty;j++) {
					var options = $("#orderstatus"+ j +" option:selected");
					var colors = new Array("未出貨","出貨中","已到貨", "已取消");
					var temp = "";
					for(var i=0;i<colors.length;i++) {
						if(colors[i] != options.text()) {
							temp += "<option>"+ colors[i] +"</option>";
						}
					}
					$("#orderstatus"+ j).append(temp);
				}
			});
			
			function changeOrderStatus(num,orderId,userId) {
				var packageName = getRealPath();
				var options = $("#orderstatus"+ num +" option:selected");
				$.ajax({
					type : "POST",
					url : "/" + packageName + "/changeOrderStatus?orderId=" + orderId +"&status=" + options.text()+"&userId=" + userId,
					dataType : "text",
					success : function(data) {
						alert("訂單儲存成功");
						var temp = "";
						if(options.text() == "已取消") {
							$("#tdtatus"+ num + " select").remove();
							temp = "<select id='orderstatus"+ num + "' class='form-control' disabled><option>已取消</option></select>";
							$("#tdtatus"+ num).append(temp);
						}
					},
					error : function(error) {
						console.log(error);
					},
				});
			}
			
			//取得自己專案的名稱
			function getRealPath() {
				var curWwwPath = window.document.location.href;
				var pathName = window.document.location.pathname;
				var projectName = pathName.substring(1, pathName.substr(1).indexOf('/') + 1);
				return projectName;
			}

			function changeTable(p, totalp) {
				var allPage = totalp;
				if (p >= 1 && p <= allPage) {
					page = p;
					var pageNo = (page - 1) * onePageQuantity;
					var packageName = getRealPath();
					$.ajax({
							type: "POST",
							url: "/" + packageName + "/helpQuestionJson?page=" + p,
							dataType: "text",
							success: function (data) {
								$("#main tr").remove();
								var temp = "";
								var lists = JSON.parse(data);
								
								if ((lists.length - pageNo) >= 8) {
									EndPage = pageNo + 8;
								} else {
									EndPage = lists.length;
								}
								for (var i = pageNo; i < EndPage; i++) {
									var orderStatus = "";
									temp = "<tr><td>" + lists[i].helpQAId
										+ "<td>" + lists[i].userID + "<td style='width: 30%'>"
										+ lists[i].email + "<td>" + lists[i].title;
									if (lists[i].responseStatus == "N") {
										temp+= "<td> 未回覆";
									}
									else {
										temp+= "<td> 已回覆";
									}
									temp += "<td><input type='button' style='float:right;' class='btn btn-default btn-secondary btn-sm' value='問題內容'  />";
									if (lists[i].responseStatus == "N") {
										temp += "<td><input type='button'  class='btn btn-default btn-secondary btn-sm' disabled value='回覆問題'  />"
									} else {
										temp += "<td><input type='button'  class='btn btn-default btn-secondary btn-sm' value='回覆問題'  />";
									}
									$("#table1 tbody").append(temp);
								}
							},
							error: function (error) {
								console.log(error);
							},
						});
				}
			}
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
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
</body>

</html>