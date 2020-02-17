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
/* 	width: 300px; */
/* 	height: 100px; */
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

div.dataTables_paginate {
	margin: 0;
	white-space: nowrap;
	text-align: right;
}

#password2{
	margin-top : 2px;
}

.hideTr, .hideBr{
 	display : none; 
}
.passwordTwo{
	display : none; 
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

.errorMeg{
	color : red;
	display : none; 
}
.contentTop, .context{
	width:20%;				
 	overflow: hidden;		/*溢出的内容切割隐藏 */
	white-space: nowrap;		/*强制在一行显示*/
	text-overflow: ellipsis;
}

/* 要加才能在表單實現切割功能 */
#newTable{  
    width:100%;   
    table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */  
} 



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


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- 	加入文字編輯器ckEditor -->
<script src="https://cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>

</head>

<body id="page-top">

	<!-- dialog area -->
	<div id="dialog_div_insert" title="Create News"></div>
	<div id="dialog_div_update" title="Update News"></div>
	<div id="dialog_div_wait" title="Wait Upload"></div>
	<div id="dialog_div_error" title="error"></div>
	<!-- 	<input tpye='button' id='opener' value='dialog_div_insert Open'> -->


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
						<h1 class="h3 mb-0 text-gray-800">公告管理</h1>
						<!--             <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a> -->
					</div>
					<!-- 以下改寫 -->
					<!-- 外框div -->
					<div class="ui-tabs ui-corner-all ui-widget ui-widget-content">
						<!-- 抬頭div -->
						<div class='ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header' style='padding: 4px;'>
							<input type='button' class='btn btn-default btn-secondary btn-sm' onclick='insertNews()' value='新增公告'>
						</div>	
						<!-- 框內容div -->
						<div class='ui-tabs-panel ui-corner-bottom ui-widget-content'>
							<!-- 搜尋欄 -->
							<div style="margin-left: 500px;"
								class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
								<div class="input-group">
									<input type="text" class="form-control bg-light border-0 small searchInput"
										placeholder="請輸入使用者ID" aria-label="Search"
										aria-describedby="basic-addon2" id='searchIn' >
									<div class="input-group-append">
										<button class="btn btn-primary search" type="button" >
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
							</div>
							<!-- 表單 -->
							<div>
								<div id="test1"></div>
								<div class="row" id="pageArea1"></div>
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


					<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

					<!-- myJavaScript(products) -->
					<script src="js/newsUse.js"></script>
</body>

</html>
