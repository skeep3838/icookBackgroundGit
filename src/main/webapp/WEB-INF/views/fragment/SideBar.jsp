<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.icookBackstage.model.Manageral, java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 反灰的CSS屬性  --%>
<c:set var="opacity" value="style='opacity: 0.2'" />


		<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
		<a class="sidebar-brand d-flex align-items-center justify-content-center" href=<c:url value='backstage.page'/>>

				<div class="sidebar-brand-icon rotate-n-15">
					<!--           <i class="fas fa-laugh-wink"></i> -->
				</div>
				<div style="font-size: 20px" class="sidebar-brand-text mx-3">
					iCook後台管理 </sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value='backstage.page' />"> <!--           <i class="fas fa-fw fa-tachometer-alt"></i> -->
					<span style="margin-left: 18px">代辦事項</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading" >市場管理</div>

			<!-- Nav Item - Pages Collapse Menu -->
			
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" 
				data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo"
				<%-- 檢查 productAuth權限, 權限不夠就將字體反灰 --%>
				<c:if test="${!currentManager.productAuth}" var="123">${opacity}</c:if>> 
					<span style="margin-left: 20px">商品</span>
				</a>
				<%-- 檢查 productAuth權限, 權限不夠就將連結拿掉 --%>
				<c:if test="${currentManager.productAuth}">
					<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
						data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<!--             <h6 class="collapse-header">Custom Components:</h6> -->
							<a class="collapse-item" href="<c:url value='/demoNewProduct.page' />">新增商品</a>
							<a class="collapse-item" href="<c:url value='/demoMyProduct.page' />">商品管理</a> 
						</div>
					</div>
				</c:if>	
			</li>
					

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"
				<%-- 檢查 productAuth權限, 權限不夠就將字體反灰 --%>
				<c:if test="${!currentManager.productAuth}" var="123">${opacity}</c:if>> 
						<span style="margin-left: 20px">訂單</span>
				</a>
				<%-- 檢查 productAuth權限, 權限不夠就將連結拿掉 --%>
				<c:if test="${currentManager.productAuth}">
					<div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item" href="<c:url value='/ManagerOrders' />">訂單管理</a> 
							<a class="collapse-item" href="#">物流管理</a>
						</div>
					</div>
				</c:if>	
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">課程管理</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTree"
				aria-expanded="true" aria-controls="collapseTwo"
				<%-- 檢查 productAuth權限, 權限不夠就將字體反灰 --%>
				<c:if test="${!currentManager.productAuth}" var="123">${opacity}</c:if>> 
				<span style="margin-left: 20px">課程</span>
				</a>
				<%-- 檢查 productAuth權限, 權限不夠就將連結拿掉 --%>
				<c:if test="${currentManager.productAuth}">
					<div id="collapseTree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item" href="<c:url value='/course/courseList' />">課程管理</a> 
							<a class="collapse-item" href="<c:url value='/course/courseAddDate' />">新增課程</a>
						</div>
					</div>
				</c:if>	
			</li>
			
			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">帳戶管理</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"
				<%-- 檢查 accrountAuth權限, 權限不夠就將字體反灰 --%>
				<c:if test="${!currentManager.accrountAuth}" var="123">${opacity}</c:if>> 
				<span style="margin-left: 20px">管理</span>
				</a>
				<%-- 檢查 accrountAuth權限, 權限不夠就將連結拿掉 --%>
				<c:if test="${currentManager.accrountAuth}">
					<div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item" href="<c:url value='/userAccountManag.page' />">使用者帳戶</a> 
							<a class="collapse-item" href="<c:url value='/managerialManag.page' />">管理員帳戶</a>
							
						</div>
					</div>
				</c:if>	
			</li>

			<!-- Nav Item - Pages Collapse Menu -->
			<!-- 訊息回復功能, 暫時移除 -->

<!-- 			<li class="nav-item"><a class="nav-link collapsed" href="#" -->
<!-- 				data-toggle="collapse" data-target="#collapsePages2" -->
<!-- 				aria-expanded="true" aria-controls="collapsePages"> <span -->
<!-- 					style="margin-left: 20px">訊息回復</span> -->
<!-- 			</a> -->
<!-- 				<div id="collapsePages2" class="collapse" -->
<!-- 					aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!-- 					<div class="bg-white py-2 collapse-inner rounded"> -->
<!-- 						<a class="collapse-item" href="register.html">使用者訊息</a> -->
<!-- 					</div> -->
<!-- 				</div></li> -->

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">網頁維護</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item">
				<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#test3Pages"
				aria-expanded="true" aria-controls="collapsePages"
				<%-- 檢查 webMaintainAuth權限, 權限不夠就將字體反灰 --%>
				<c:if test="${!currentManager.webMaintainAuth}" var="123">${opacity}</c:if>>
				<span style="margin-left: 20px">管理</span>
				</a>
				<%-- 檢查 webMaintainAuth權限, 權限不夠就將連結拿掉 --%>
				<c:if test="${currentManager.webMaintainAuth}">
					<div id="test3Pages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item" href="<c:url value='news.page' />">布告欄公告</a>
							<a class="collapse-item" href="<c:url value='Managerhelp' />">客服管理</a>
							<a class="collapse-item" href="<c:url value='WebSocket'/>">客服回應</a>
						</div>
					</div>
				</c:if>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">前台連結</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#test4Pages"
				aria-expanded="true" aria-controls="collapsePages"> <!--           <i class="fas fa-fw fa-folder"></i> -->
					<span style="margin-left: 20px">iCook</span>
			</a>
				<div id="test4Pages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="<c:url value='http://localhost:8080/icookProjectSpringMVC200121/' />">iCook首頁</a> 
						<a class="collapse-item" href="<c:url value='http://localhost:8080/icookProjectSpringMVC200121/' />">食譜</a>
						<a class="collapse-item" href="<c:url value='http://localhost:8080/icookProjectSpringMVC200121/' />">市集</a> 
						<a class="collapse-item" href="<c:url value='http://localhost:8080/icookProjectSpringMVC200121/' />">課程</a>
					</div>
				</div></li>
			

			<hr class="sidebar-divider d-none d-md-block">

		</ul>