<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Login</title>

  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

  <style>
	<%-- 調整login圖片 --%>
    .loginPicture{
      background: url(${pageContext.request.contextPath}/image/download.jpg);
      background-position: center;
      background-size: cover;
    }
    
    <%-- 錯誤訊息樣式 --%>
    .errMessage{
    	color: red;
    }
    

  </style>

</head>

<body class="bg-gradient-primary">
  <div class="container">
  <!-- test -->

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block loginPicture" ></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                  </div>
                  <form class="user" method="post" action="managementLogin.do">
<!--                     <div class="form-group"> -->
<!--                       <input type="text" class="form-control form-control-user" name="inputAccount" aria-describedby="emailHelp"  -->
<%--                       	placeholder="Enter your Account" value="<c:if test="${cookie.acCookie} != null">${cookie.acCookie.value}</c:if>"> --%>
<!--                     </div> -->
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="inputAccount" name="inputAccount" aria-describedby="emailHelp" 
                      	placeholder="Enter your Account" value="">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="inputPassword" name="inputPassword" 
                      placeholder="Password" value="">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck" name="rememberBox">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div>
                    
                    <div>
                      <span class="errMessage">${errMessage}</span>
                    </div>

                    <div>
                      <input type="submit" class="btn btn-primary btn-user btn-block" style="margin-top: 50px;" value="Login">

                    </div>
                    <div>
                    <a href="<c:url value='/' />">
                      <input type="button" class="btn btn-primary btn-user btn-block" style="margin-top: 10px;" value="Turn Back to iCook">
                    </a>
                    </div>
                    <!-- <a href="index.html" class="btn btn-primary btn-user btn-block">
                      Login
                    </a> -->
                    <!-- <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a> -->
                  </form>
                  <!-- <hr>
                  <div class="text-center">
                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="register.html">Create an Account!</a>
                  </div> -->
                </div>
              </div>
            </div>
          </div>
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
  
<!--  CheckBox設定 -->
  <!--   載入jquery.cookie套件 -->
  <script src="vendor/jquery/jquery.cookie.js"></script>
  <!--   如果含有cookie.remAccrount物件, 就預設將remember打勾 -->
  <script>
 	<%-- 如果有儲存帳號cookie, 將帳號載入 +打勾remember--%>
  	if($.cookie("remAccrount")){
  		$("#customCheck").prop("checked", true);
  		$("#inputAccount").val($.cookie("remAccrount"));
  	}
  	<%-- 如果有儲存密碼cookie, 將密碼載入 --%>
 	if($.cookie("remPassword")){
  		$("#inputPassword").val($.cookie("remPassword"));
  	}
  </script>
</body>

</html>