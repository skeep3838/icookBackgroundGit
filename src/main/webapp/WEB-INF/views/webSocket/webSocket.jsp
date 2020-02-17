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
<link href="${pageContext.request.contextPath}/css/chatBox.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
	type="text/css" rel="stylesheet">
	
<style type="text/css">
		.direct-chat-msg, .direct-chat-text {
	display: block;
}

.direct-chat-msg {
	margin-bottom: 10px;
}

.direct-chat-msg::after {
	display: block;
	clear: both;
	content: "";
}
.direct-chat-infos {
	display: block;
	font-size: 0.875rem;
	margin-bottom: 2px;
}
.clearfix::after {
	display: block;
	clear: both;
	content: "";
}
.direct-chat-name {
	font-weight: 600;
}
.float-left {
	float: left !important;
}
.direct-chat.timestamp-light .direct-chat-timestamp {
	color: #30465f;
}

.direct-chat.timestamp-dark .direct-chat-timestamp {
	color: #cccccc;
}

.direct-chat-timestamp {
	color: #697582;
}

.float-right {
	float: right !important;
}
.nav2-sidebar>.nav2-item .float-right {
	margin-top: 3px;
}
.nav2-sidebar .nav2-item>.nav2-link>.float-right {
	margin-top: -7px;
	position: absolute;
	right: 10px;
	top: 50%;
}
.direct-chat-msg, .direct-chat-text {
	display: block;
}

.direct-chat-msg {
	margin-bottom: 10px;
}

.direct-chat-msg::after {
	display: block;
	clear: both;
	content: "";
}

.direct-chat-messages, .direct-chat-contacts {
	transition: -webkit-transform .5s ease-in-out;
	transition: transform .5s ease-in-out;
	transition: transform .5s ease-in-out, -webkit-transform .5s ease-in-out;
}

.direct-chat-text {
	border-radius: 0.3rem;
	background: #d2d6de;
	border: 1px solid #d2d6de;
	color: #444;
	margin: 5px 0 0 50px;
	padding: 5px 10px;
	position: relative;
}

.direct-chat-text::after, .direct-chat-text::before {
	border: solid transparent;
	border-right-color: #d2d6de;
	content: ' ';
	height: 0;
	pointer-events: none;
	position: absolute;
	right: 100%;
	top: 15px;
	width: 0;
}

.direct-chat-text::after {
	border-width: 5px;
	margin-top: -5px;
}

.direct-chat-text::before {
	border-width: 6px;
	margin-top: -6px;
}

.right .direct-chat-text {
	margin-left: 0;
	margin-right: 50px;
}

.right .direct-chat-text::after, .right .direct-chat-text::before {
	border-left-color: #d2d6de;
	border-right-color: transparent;
	left: 100%;
	right: auto;
}

.direct-chat-img {
	border-radius: 50%;
	float: left;
	height: 40px;
	width: 40px;
}

.right .direct-chat-img {
	float: right;
}

.direct-chat-infos {
	display: block;
	font-size: 0.875rem;
	margin-bottom: 2px;
}

.direct-chat-name {
	font-weight: 600;
}

.direct-chat-timestamp {
	color: #697582;
}

.direct-chat-contacts-open .direct-chat-contacts {
	-webkit-transform: translate(0, 0);
	transform: translate(0, 0);
}

.direct-chat-contacts {
	-webkit-transform: translate(101%, 0);
	transform: translate(101%, 0);
	background: #343a40;
	bottom: 0;
	color: #ffffff;
	height: 250px;
	overflow: auto;
	position: absolute;
	top: 0;
	width: 100%;
}

.direct-chat-contacts-light {
	background: #f8f9fa;
}

.direct-chat-contacts-light .contacts-list-name {
	color: #495057;
}

.direct-chat-contacts-light .contacts-list-date {
	color: #6c757d;
}

.direct-chat-contacts-light .contacts-list-msg {
	color: #545b62;
}
	</style>
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
				<!-- 			---------------------------- -->

				<div class="container">
					<div class="messaging">
						<div class="inbox_msg">
							<div class="inbox_people">
								<div class="headind_srch">
									<div class="recent_heading">
										<h4>客服回應</h4>
									</div>
									<div class="srch_bar">
										<div class="stylish-input-group">
											<input type="text" class="search-bar" placeholder="Search">
											<span class="input-group-addon">
												<button type="button">
													<i class="fa fa-search" aria-hidden="true"></i>
												</button>
											</span>
										</div>
									</div>
								</div>
								<div class="inbox_chat">
									<div class="chat_list" id='messageChatBox'>
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png"
													alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													Sunil Rajput <span class="chat_date">Dec 25</span>
												</h5>
												<p>Test, which is a new approach to have all solutions
													astrology under one roof.</p>
											</div>
										</div>
									</div>

									<div class="chat_list" id='messageChatBox'>
										<div class="chat_people">
											<div class="chat_img">
												<img src="https://ptetutorials.com/images/user-profile.png"
													alt="sunil">
											</div>
											<div class="chat_ib">
												<h5>
													Sunil Rajput <span class="chat_date">Dec 25</span>
												</h5>
												<p>Test, which is a new approach to have all solutions
													astrology under one roof.</p>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="mesgs" style="padding: 30px 13px 0 25px;">
								<div class="msg_history" id="messageContent">

<!-- 								訊息出現區 -->
<!-- 									<div class="direct-chat-msg right"> -->
<!-- 										<div class="direct-chat-infos clearfix"> -->
<!-- 											<span class="direct-chat-name float-right">Sarah -->
<!-- 												Bullock</span> <span class="direct-chat-timestamp float-left">23 -->
<!-- 												Jan 2:05 pm</span> -->
<!-- 										</div> -->
<!-- 										<div class="direct-chat-text">You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										You better believe it! -->
<!-- 										</div> -->
<!-- 									</div> -->

								</div>
								<div class="type_msg">
									<div class="input_msg_write">
										<input type="text" class="write_msg" id="text"
											placeholder="Type a message" />
										<button class="msg_send_btn" type="button" onclick="saveMessage(1,'客服人員')">
											<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>




				<!-- 			---------------------------- -->
				<!-- 				<div class="container-fluid"> -->
				<!-- 				Welcome<br /> -->
				<!-- 				<input id="text" type="text" /> -->
				<!-- 				<button onclick="send()">发送消息</button> -->
				<!-- 				<hr /> -->
				<!-- 				<button onclick="closeWebSocket()">关闭WebSocket连接</button> -->
				<!-- 				<hr /> -->
				<!-- 				<div id="message"></div> -->
				<!-- 				</div> -->
			</div>
			<!-- Main Content -->
		</div>
		<!-- Content Wrapper -->
	</div>

	<!-- 	----------------------------------------	 -->
	<script>
		$("#messageChatBox").mouseover(
				function() {
					document.getElementById("messageChatBox").classList
							.add("active_chat");
				});
		$("#messageChatBox").mouseout(
				function() {
					document.getElementById("messageChatBox").classList
							.remove("active_chat");
				});
		function getRealPath() {
			var curWwwPath = window.document.location.href;
			var pathName = window.document.location.pathname;
			var projectName = pathName.substring(1, pathName.substr(1).indexOf(
					'/') + 1);
			return projectName;
		}

		$(window).load(function() {
			sendMessage();
		});
		// 		------------------------------------------
		var packageName = getRealPath();
		var websocket = null;
		function sendMessage() {
			//判断当前浏览器是否支持WebSocket
			if ('WebSocket' in window) {
				websocket = new WebSocket(
						"ws://localhost:8080/icookBackstage02035/websocket");
			} else {
				alert('当前浏览器 Not support websocket')
			}

			//连接发生错误的回调方法
			websocket.onerror = function() {
				setMessageInnerHTML("WebSocket连接发生错误");
			};

			//连接成功建立的回调方法
			websocket.onopen = function() {
			}

			//接收到消息的回调方法
			websocket.onmessage = function(event) {
				var message = JSON.parse(event.data);
				if(message.type === "客戶") {
					setMessageInnerHTML(message.text, message.name,message.type,message.Date);
				}
				else {
					setMessageInnerHTML(message.text, message.name,message.type,message.Date);
				}
			}

			//连接关闭的回调方法
			websocket.onclose = function() {
//	 			alert("close");
			}

			//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
			window.onbeforeunload = function() {
				closeWebSocket();
			}
		}
		//关闭WebSocket连接
		function closeWebSocket() {
			websocket.close();
		}

		//发送消息
		function send(maId,nickname,message,date) {
			var msg = {
				    text: message,
				    id: maId,
				    name: nickname,
				    type: "客服人員",
				    Date: date
				  };
			websocket.send(JSON.stringify(msg));
		}
		function saveMessage(maId,nickname) {
			var message = document.getElementById('text').value;
			var packageName = getRealPath();
			$.ajax({
				type : "GET",
				url : "/" + packageName + "/WebSocket",
				data:{ Message: message , maid : maId },
				dataType : "text",
				success : function(data) {
					//傳回來儲存日期的資料
					send(maId,nickname,message,data);
				},
				error : function(error) {
				},
			});
		}
		//将消息显示在网页上
		function setMessageInnerHTML(innerHTML, nickname, type, date) {
			// 	        document.getElementById('message').innerHTML += nickname + "<br><div class='messagetemp'><div id='messagetemp'>" + innerHTML + "</div></div><br/><br>";
			if(type === "客服人員") {
				$("#messageContent").append("<div class='direct-chat-msg' style='margin-left: 5px;'><div class='direct-chat-infos clearfix'>" + 
						"<span class='direct-chat-name float-left'>"+ nickname +"</span>" +  
						"<span class='direct-chat-timestamp float-left' style='margin-left: 10px;'>"+ date +"</span></div>" + 
						"<div class='direct-chat-text' style='margin: 5px 50px 0 0; float: left; width: 60%;'>"+ innerHTML +"</div></div>");
				//讓訊息框保持至底
				$('#messageContent').scrollTop( $('#messageContent')[0].scrollHeight );
			}
			else {
				$("#messageContent").append("<div class='direct-chat-msg right' style='width: 590px;'><div class='direct-chat-infos clearfix'>" + 
				"<span class='direct-chat-name float-right'>"+ nickname +"</span>" + 
				"<span class='direct-chat-timestamp float-right' style='margin-right: 10px;'>"+ date +"</span></div>" +
				"<div class='direct-chat-text' style='float:right; margin-right:0px; width: 60%;'><div style='float:right;'>"+ innerHTML +"</div></div></div>");
				$('#messageContent').scrollTop( $('#messageContent')[0].scrollHeight );
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