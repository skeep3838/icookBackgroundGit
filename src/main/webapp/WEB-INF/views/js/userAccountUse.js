let tableContant;
let paginationContant;
let json;
let topColumn = "<table class='table table-hover'><tr><th>使用者ID"
	+ "	<th>信箱<th>姓氏<th>名稱<th>暱稱<th>性別<th>生日<th>認證<th>";
let noDataContant = "<tr><td colspan=9 align='center' valign='middle'><h2>Not any Data...</h2>"
let userAccountPageJson;
let onePageNunber = 10;
let nowPage = 1;
let maxPage = 1;
let firstDataNumber = 1;
let detailId= 1;
let genderArray= ["男生","女生","其他"];
let checkstatusArray= ["Y","N"];
let detailContant = "";
let createNoError = 1;
let emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
let birthdayRule = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;

let searchMod = false;
let searchInt;

//輸入判斷區

//認證信箱
$("body").on("change","#account2",function(){
	let userMail = $(this).val();
	//檢查格式
	if(userMail.search(emailRule) != -1 ){
		createNoError = 1;
		$("#account2Meg").html("")
		$("#account2Meg").attr("class","passMeg")
	}else{
		createNoError = 0;
		$("#account2Meg").html("*信箱格式錯誤")
		$("#account2Meg").attr("class","errorMeg")
	}
});

//認證密碼
$("body").on("change","#password2, #password3",function(){
	let password2 = $("#password2").val();
	let password3 = $("#password3").val();
	if( password2 == password3 ){
		createNoError = 1;
		$("#password2Meg").html("")
		$("#password2Meg").attr("class","passMeg")
	}else{
		createNoError = 0;
		$("#password2Meg").html("*密碼不一致")
		$("#password2Meg").attr("class","errorMeg")
	}
});

//認證生日
$("body").on("change","#birthday2",function(){
	let userBirthday = $(this).val();
	//檢查格式
	if(userBirthday.search(birthdayRule) != -1 ){
		createNoError = 1;
		$("#birthday2Meg").html("")
		$("#birthday2Meg").attr("class","passMeg")
	}else{
		createNoError = 0;
		$("#birthday2Meg").html("*生日格式錯誤")
		$("#birthday2Meg").attr("class","errorMeg")
	}
});

//在搜尋欄按Enter也會進行搜尋
//$(".searchInput").keyup(function(event){
//	//判斷是不是按Enter
//	if(event.keyCode === 13 || event.which === 13){
//		$(".search").click();
//	}
//});

//動態搜尋
$(".searchInput").on("input",function(event){
	$(".search").click();
});

//點擊搜尋
$(".search").click(function(){
	
	//取使用者輸入id
	searchInt = $("#searchIn").val();
	
	//設定搜尋初始值
	nowPage = 1;
	if(searchInt != ""){
		searchMod = true;
	}else{
		searchMod = false;
	}
	
	//如果serchInt內不是數字就不搜尋
	if( !isNaN(searchInt)){
		searchInt = parseInt(searchInt , 10);
		buildTable();
	}else{
		console.log("[sys]:searchInt is not Integer!")
	}
});


//載入完成後匯入使用者帳戶資訊
$(document).ready(function(){
	console.log("start_first_select")
	nowPage = 1;
	buildTable();
});

//換頁數並刷新畫面
$("body").on("click",".paginate_button",function(){
	//建立必要參數
	let pageTemp = $(this).text();
	let pageTempNumber = parseFloat(pageTemp);
	
	//判斷輸入的按鈕來改變新的頁面為多少
	if((pageTemp == "Previous") && (nowPage != 1)){
		nowPage = nowPage - 1;
	}else if((pageTemp == "Next") && (nowPage < maxPage)){
		nowPage = nowPage + 1;
	}else if(!isNaN(pageTempNumber)){
		nowPage = pageTempNumber;
	}else{
		console.log("[SYSTEM]:paginate is not number")
	}
	console.log("===== nowPage(changePage function)= " + nowPage + " =====");
	
	//刷新產品table
	buildTable();
	
});


//顯示每一頁產品table的資訊
function buildTable(){

	let goUrl = "";
	
	//確認是否為搜尋模式
	if(searchMod){
		goUrl = "userAccountSearch/"+searchInt;
	}else{
		goUrl = "userAccount/"+nowPage;
	}
	
	$.ajax({ 
		type:"GET",
		url:goUrl,
		success:function(data){
			json = data;
			
			tableContant = "";
			paginationContant = "";
			firstDataNumber = (json.page-1)*onePageNunber + 1
			//判斷是否有值
			if(json.userAccountPageJson != null){
				userAccountPageJson = JSON.parse(json.userAccountPageJson);
				//table內容
				for(let i=0 ; i<userAccountPageJson.length ; ++i){
					tableContant	+= 	"<tr><td>" + userAccountPageJson[i].userId
									+	"<td>" + userAccountPageJson[i].account
									+	"<td>" + userAccountPageJson[i].lastname
									+	"<td>" + userAccountPageJson[i].firstname
									+	"<td>" + userAccountPageJson[i].nickname
									+	"<td>" + userAccountPageJson[i].gender
									+	"<td>" + userAccountPageJson[i].birthday
									+	"<td>" + userAccountPageJson[i].checkstatus
									+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
										+ "value='詳細修改' onclick='detailUpdate("+ i +")'>";
				}
				//分頁左側內容, 顯示畫面上的資料數
				paginationContant 	+=	"<div class='col-sm-12 col-md-5'><div>"
									+	"Showing "+ firstDataNumber +" to "+ (firstDataNumber + userAccountPageJson.length - 1)
									+	" of "+ json.allAccountNumber +" entries"
									+	"</div></div>";
				
				//分頁右側內容, 顯示分頁按鈕, 先顯示上一頁按鈕				
				paginationContant	+=	"<div class='col-sm-12 col-md-7'> <div class='dataTables_paginate' id='dataTables_paginate'>"
									+	"<ul class='pagination'> <li class='Previous'> <a class='paginate_button' href='javascript:void(0);'>Previous</a></li>";
				
				//計算最後頁
				maxPage = ((json.allAccountNumber)>onePageNunber) ? parseInt((json.allAccountNumber)/onePageNunber)+1 : 1 ;
				console.log("==== maxPage:" + maxPage + " ====")
				//產生頁數的迴圈
				for(let i=1 ; i<= maxPage ; ++i){
					//如果是當前頁, 就加上class='active'
					if(i == nowPage){
						paginationContant 	+=	"<li> <a class='paginate_button active' href='javascript:void(0);'>" +
											+	i + "</a></li>";
					}else{
						paginationContant 	+=	"<li> <a class='paginate_button' href='javascript:void(0);'>" +
											+	i + "</a></li>";
					}
				}
				//補上下一頁按鈕
				paginationContant 	+=	"<li class='Next'> <a class='paginate_button' href='javascript:void(0);'>Next</a></li></div></div>";
				
				//將整頁資訊寫入div
				$("#test1").html(topColumn + tableContant);
				$("#pageArea1").html(paginationContant);
			}else{
				//寫入沒有資料的訊息
				$("#test1").html(topColumn + noDataContant);
				$("#pageArea1").html("");
			}
		}
	})
}

//使用者帳戶update的Detail資訊(Dialog內)
function detailUpdate(number){
	
	//紀錄detailId
	detailId = userAccountPageJson[number].userId;
	
	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailForm' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable1'>"
					+	"<tr><td>使用者ID:<td>" + detailId
					+	"<tr><td>信箱:<td><input type='text' id='account' name='account' class='form-control' value='" 
						+ userAccountPageJson[number].account + "'></input>"
					+	"<tr><td>密碼:<td><input type='password' id='password' name='password'" 
						+ "placeholder='在此輸入變更的密碼' class='form-control' ></input>"
					+	"<tr><td>姓氏:<td><input type='text' id='lastname' name='lastname' class='form-control' value='" 
						+ userAccountPageJson[number].lastname + "'></input>"
					+	"<tr><td>名稱:<td><input type='text' id='firstname' name='firstname' class='form-control' value='" 
						+ userAccountPageJson[number].firstname + "'></input>"
					+	"<tr><td>暱稱:<td><input type='text' id='nickname' name='nickname' class='form-control' value='" 
						+ userAccountPageJson[number].nickname + "'></input>"
					+	"<tr><td>性別:<td><select id='gender' class='form-control' name='gender'>";
	//抓性別資料填預設值
	for(let i=0 ; i < genderArray.length ; ++i){
		detailContant 	+=	"<option value='"+ genderArray[i] +"'"
						+ 	((userAccountPageJson[number].gender === genderArray[i])?(" selected"):("")) 
						+	">" + genderArray[i];
	}
	detailContant 	+=	"</select>"
					+	"<tr><td>生日:<td><input type='text' id='birthday' name='birthday' class='form-control' value='" 
						+ userAccountPageJson[number].birthday 
						+ "' placeholder='yyyy-MM-DD'></input>"
					+	"<tr><td>電話:<td><input type='text' id='phone' name='phone' class='form-control' value='" 
						+ userAccountPageJson[number].phone + "'></input>"
					+	"<tr><td>住址:<td><input type='text' id='address' name='address' class='form-control' value='" 
						+ userAccountPageJson[number].address + "'></input>"
					+	"<tr><td>認證:<td><select id='checkstatus' class='form-control' name='checkstatus'>";
	//抓認證資料填預設值
	for(let i=0 ; i < checkstatusArray.length ; ++i){
		detailContant 	+=	"<option value='"+ checkstatusArray[i] +"'"
						+ 	((userAccountPageJson[number].checkstatus === checkstatusArray[i])?(" selected"):(""))
						+	">" + checkstatusArray[i];
	}
	
	detailContant 	+=	"</select></td></tr></table></from>";
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div_update").html(detailContant);
	$("#dialog_div_update").dialog("open");
}

//update Detail資訊
function updateDetailData(){
	//打包form(id="detailForm")的資料
	let detailForm = new FormData($("#detailForm")[0]);
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("updateUserAccount/"+ detailId),

		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		data:detailForm,
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){
			$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
	        $("#dialog_div_wait").dialog("open");
		},
		
		//更新成功先關閉upload畫面, 再刷新當前畫面
		success:function(data){
			console.log(data);
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_update").dialog("close");
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>更新帳戶失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
		
		
	});
}

//使用者帳戶insert的Detail資訊(Dialog內)
function detailInsert(){
	
	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailForm2' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable2'>"
					+	"<tr><td>信箱:<td><input type='text' id='account2' name='account' class='form-control' required='required'></input>"
						+ "<span id='account2Meg'></span>"	
					+	"<tr><td>密碼:<td><input type='password' id='password2' name='password' class='form-control' " 
						+ "placeholder='在此輸入變更的密碼' ></input> <span id='password2Meg'></span>"
					+	"<tr><td>密碼:<td><input type='password' id='password3' class='form-control' " 
						+ "placeholder='再次確認變更的密碼' ></input>"	
					+	"<tr><td>姓氏:<td><input type='text' id='lastname2' name='lastname' class='form-control' ></input>"
						+ "<span id='lastname2Meg'></span>"
					+	"<tr><td>名稱:<td><input type='text' id='firstname2' name='firstname' class='form-control' ></input>"
						+ "<span id='firstname2Meg'></span>"
					+	"<tr><td>暱稱:<td><input type='text' id='nickname2' name='nickname' class='form-control' ></input>"
						+ "<span id='nickname2Meg'></span>"
					+	"<tr><td>性別:<td><select id='gender2' class='form-control' name='gender'>";
	//抓性別資料填預設值
	for(let i=0 ; i < genderArray.length ; ++i){
		detailContant 	+=	"<option value='"+ genderArray[i] +"'>" + genderArray[i];
	}
	detailContant 	+=	"</select>"
					+	"<tr><td>生日:<td><input type='text' id='birthday2' class='form-control' name='birthday' "
						+ "placeholder='yyyy-MM-DD'></input> <span id='birthday2Meg'></span>"
					+	"<tr><td>電話:<td><input type='text' id='phone2' name='phone' class='form-control' ></input>"
						+ "<span id='phone2Meg'></span>"
					+	"<tr><td>住址:<td><input type='text' id='address2' name='address' class='form-control' ></input>"
						+ "<span id='address2Meg'></span>"
					+	"<tr><td>認證:<td><select id='checkstatus2' class='form-control' name='checkstatus'>";
	//抓認證資料填預設值
	for(let i=0 ; i < checkstatusArray.length ; ++i){
		detailContant 	+=	"<option value='"+ checkstatusArray[i] +"'>" + checkstatusArray[i];
	}
	detailContant 	+=	"</select></td></tr></table></from>";
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div_insert").html(detailContant);
	$("#dialog_div_insert").dialog("open");
}

//update Detail資訊
function insertDetailData(){
	//打包form(id="detailForm")的資料
	let detailForm = new FormData($("#detailForm2")[0]);
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("insertUserAccount"),

		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		data:detailForm,
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){
			$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
	        $("#dialog_div_wait").dialog("open");
		},
		
		//更新成功先關閉upload畫面, 再刷新當前畫面
		success:function(data){
			console.log(data);
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_insert").dialog("close");
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>創立帳戶失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
		
		
	});
}

//Dialog設定區
$(function() {
    $("#dialog_div_insert").dialog({
    	//固定視窗
    	maxHeight:	600,
    	maxWidth:	450,
    	minHeight:	600,
    	minWidth:	450,
    	
    	//拖移設定
    	draggable: true,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,

        //open事件發生時, 將dialog樣式右上的x顯示
        open:function(event,ui){$(".ui-dialog-titlebar-close").show();},
        
        buttons: {
            "Create": function() {
            	insertDetailData();
            },
            "Cancel": function() { 
            	$(this).dialog("close");
            }
        }
    });
 
    $("#123").click(function(productId) {
        $("#dialog_div_insert").dialog("open");
    });
 
});

$(function() {
    $("#dialog_div_update").dialog({
    	//固定視窗
    	maxHeight:	600,
    	maxWidth:	450,
    	minHeight:	600,
    	minWidth:	450,
    	
    	//拖移設定
    	draggable: true,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,

        //open事件發生時, 將dialog樣式右上的x顯示
        open:function(event,ui){$(".ui-dialog-titlebar-close").show();},
        
        buttons: {
            "Update": function() {
            	updateDetailData();
            },
            "Cancel": function() { 
            	$(this).dialog("close");
            }
        }
    });
 
    $("#opener").click(function(productId) {
        $("#dialog_div_update").dialog("open");
    });
 
});


$(function() {
    $("#dialog_div_wait").dialog({
    	//固定視窗
    	maxHeight:	250,
    	maxWidth:	250,
    	minHeight:	250,
    	minWidth:	250,
    	
    	//拖移設定
    	draggable: false,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,
        
        //不能Esc關閉
        closeOnEscape: true,
        
        //open事件發生時, 將dialog樣式右上的x隱藏
        open:function(event,ui){$(".ui-dialog-titlebar-close").hide();}
        
    });
    
    $("#wait_butt").click(function(productId) {
		$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
        $("#dialog_div_wait").dialog("open");
    });
});

$(function() {
    $("#dialog_div_error").dialog({
    	//固定視窗
    	maxHeight:	250,
    	maxWidth:	250,
    	minHeight:	250,
    	minWidth:	250,
    	
    	//拖移設定
    	draggable: false,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,
        
        //不能Esc關閉
        closeOnEscape: true,

        //open事件發生時, 將dialog樣式右上的x顯示
        open:function(event,ui){$(".ui-dialog-titlebar-close").show();}
  
    });
    
    $("#error_butt").click(function(productId) {
		$("#dialog_div_error").html("<span class='errorFont'>更新失敗!</span>");
        $("#dialog_div_error").dialog("open");
    });
});