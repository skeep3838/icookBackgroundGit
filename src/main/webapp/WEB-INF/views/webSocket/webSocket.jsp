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
		height: 580px;
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
				<div class="container-fluid">
				Welcome<br />
				<input id="text" type="text" />
				<button onclick="send()">发送消息</button>
				<hr />
				<button onclick="closeWebSocket()">关闭WebSocket连接</button>
				<hr />
				<div id="message"></div>
				</div>
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
		function getRealPath() {
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var projectName = pathName.substring(1, pathName.substr(1).indexOf(
					'/') + 1);
			return projectName;
		}

		$(window).load(function() {
			
		});

		function changeTable(p, totalp) {
			var allPage = totalp;
			if (p >= 1 && p <= allPage) {
				page = p;
				var pageNo = (page - 1) * onePageQuantity;
				var packageName = getRealPath();
				$
						.ajax({
							type : "POST",
							url : "/" + packageName + "/managerJson?page=" + p,
							dataType : "text",
							success : function(data) {
								console.log("you want to view no data" + data);
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
									temp = "<tr><td>" + lists[i].orderId
											+ "<td>" + lists[i].userId + "<td>"
											+ lists[i].shippingAddress;
									var orderDate = (lists[i].orderDate);
									orderDate = orderDate.substring(0, 10);
									temp += "<td>"
											+ orderDate
											+ "<td>"
											+ parseInt(lists[i].totalAmount)
													.toLocaleString();
									//parseInt(字串).toLocaleString() 為字串數字加上千分位符號
									switch (lists[i].status) {
									case "N":
										orderStatus += "<option>未出貨</option> <option>出貨中</option> <option>已到貨</option> <option>已取消</option>";
										break;
									case "S":
										orderStatus += "<option>出貨中</option> <option>未出貨</option> <option>已到貨</option> <option>已取消</option>";
										break;
									case "F":
										orderStatus += "<option>已到貨</option> <option>未出貨</option> <option>出貨中</option> <option>已取消</option>";
										break;
									case "C":
										orderStatus += "<option>已取消</option> <option>未出貨</option> <option>出貨中</option> <option>已到貨</option>";
										break;
									}
									if (lists[i].status == "C") {
										temp += "<td id='tdtatus"+ i +"'><select id='orderstatus"+ i +"' class='form-control' disabled >"
												+ orderStatus + "</select>";
									} else {
										temp += "<td id='tdtatus"+ i +"'><select id='orderstatus"+ i +"' class='form-control'>"
												+ orderStatus + "</select>"
									}
									temp += "<td><input type='button' style='float:right;' class='btn btn-default btn-secondary btn-sm' onclick=\"javascript:location.href='/"
											+ packageName
											+ "/SearchOrderDetails?id="
											+ lists[i].orderId
											+ "'\" value='訂單明細'  />";
									if (lists[i].status == "C") {
										temp += "<td><input type='button'  class='btn btn-default btn-secondary btn-sm' disabled value='儲存修改'  />"
									} else {
										temp += "<td><input type='button'  class='btn btn-default btn-secondary btn-sm' onclick=\"changeOrderStatus("
												+ i
												+ ","
												+ lists[i].orderId
												+ ","
												+ lists[i].userId
												+ ")\" value='儲存修改'  />";
									}
									temp += "<input type='hidden' name='" + lists[i].orderId + "' value='"
										+ lists[i].orderId + "'/>";
									$("#table1 tbody").append(temp);
								}
							},
							error : function(error) {
								console.log(error);
							},
						});
			}
		}
		
// 		------------------------------------------
		var packageName = getRealPath();
		    var websocket = null;
		    //判断当前浏览器是否支持WebSocket
		    if ('WebSocket' in window) {
		        websocket = new WebSocket("ws://localhost:8080/icookBackstage02035/websocket");
		    }
		    else {
		        alert('当前浏览器 Not support websocket')
		    }

		    //连接发生错误的回调方法
		    websocket.onerror = function () {
		        setMessageInnerHTML("WebSocket连接发生错误");
		    };

		    //连接成功建立的回调方法
		    websocket.onopen = function () {
		        setMessageInnerHTML("WebSocket连接成功");
		    }

		    //接收到消息的回调方法
		    websocket.onmessage = function (event) {
		    	var message = JSON.parse(event.data);
				if(message.type === "客戶") {
					setMessageInnerHTML(message.text, message.name);
				}
				else {
					setMessageInnerHTML(message.text, "客服人員");
				}
		    }

		    //连接关闭的回调方法
		    websocket.onclose = function () {
		        setMessageInnerHTML("WebSocket连接关闭");
		    }

		    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		    window.onbeforeunload = function () {
		        closeWebSocket();
		    }

		    //将消息显示在网页上
		    function setMessageInnerHTML(innerHTML) {
		        document.getElementById('message').innerHTML += innerHTML + '<br/>';
		    }

		    //关闭WebSocket连接
		    function closeWebSocket() {
		        websocket.close();
		    }

		    //发送消息
		    function send() {
		        var message = document.getElementById('text').value;
		        var msg = {
					    text: message,
					    id: 10007,
					    name: "客服人員",
					    type: "客服人員"
					  };
		        websocket.send(JSON.stringify(msg));
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