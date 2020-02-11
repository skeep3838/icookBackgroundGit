let tableContant;
let paginationContant;
let json;
let topColumn = "<table class='table table-hover'><tr><th>管理員ID"
	+ "	<th>管理員帳號<th>管理員密碼<th>帳號管理權限<th>產品管理權限<th>訂單管理權限<th>網站維護權限<th><th>";
let managerialPageJson;
let onePageNunber = 10;
let nowPage = 1;
let maxPage = 1;
let firstDataNumber = 1;
let detailId= 1;
let detailContant = "";
let createNoError = 1;
let authArray = [true,false];
let authName = ["accrountAuth","productAuth","orderAuth","webMaintainAuth"];



//認證密碼
$("body").on("change",".passwordOne, .passwordTwo",function(){
	let index = $(this).attr("index");
	let paOneClas= "#passwordOne" + index;
	let paTwoClas= "#passwordTwo" + index;
	
	let passwordOne = $(paOneClas).val();
	let passwordTwo = $(paTwoClas).val();
	if( passwordOne == passwordTwo ){
		createNoError = 1;
		$("#passwordMeg").html("")
		$("#passwordMeg").attr("class","passMeg")
	}else{
		createNoError = 0;
		$("#passwordMeg").html("*密碼不一致")
		$("#passwordMeg").attr("class","errorMeg")
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
	$.ajax({ 
		type:"GET",
		url:("managerial/"+nowPage),
		success:function(data){
			json = data;
			managerialPageJson = JSON.parse(json.managerialPageJson);
			tableContant = "";
			paginationContant = "";
			firstDataNumber = (json.page-1)*onePageNunber + 1
			//table內容	
			//新增用欄位
			tableContant	+= 	"<tr id='inSertTr' class='hideTr'><td><span>新增資料:</span>"
							+"<td><input size='12' type='text' id='inAccrount' name='accrount'>"
							+"<br><span id='inAccountMeg'></span>"
							+"<td><input size='12' type='password' id='inPasswordOne' class='passwordOne' placeholder='輸入密碼'>" 
							+"<br><input size='12' type='password' id='inPasswordTwo' class='passwordTwo' placeholder='再次輸入密碼'>"
							+"<br><span id='inPasswordMeg'></span>";
			//authName內每個權限輪尋一遍
			for(let j=0 ; j < authName.length ; ++j){
				tableContant	+=	"<td><select id='"+ "in" + (authName[j]) +"'>";
				//判斷true 或是false
				for(let k=0 ; k < authArray.length ; ++k){
					tableContant 	+=	"<option value='"+ authArray[k] +"' "
					+ (k===1?"selected":"") +">"+ (authArray[k].toString());
				}
			}

			tableContant	+=	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
					+ "value='新增資料' onclick='insertSubmit()'><br>"
//					+ "<input type='button' class='btn btn-default btn-secondary btn-sm'"
//					+ "value='取消新增' onclick='insertHide()'>";
			
				
			
			for(let i=0 ; i<managerialPageJson.length ; ++i){
				//顯示用資訊
				tableContant	+= 	"<tr id='showTr"+ i +"'><td>" + managerialPageJson[i].maId
								+	"<td>" + managerialPageJson[i].accrount
								+	"<td>" 
								+	"<td>" + managerialPageJson[i].accrountAuth
								+	"<td>" + managerialPageJson[i].productAuth
								+	"<td>" + managerialPageJson[i].orderAuth
								+	"<td>" + managerialPageJson[i].webMaintainAuth
								+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
									+ "value='開啟修改' onclick='updateOpen("+ i +")'>";
				//更新用欄位
				tableContant	+= 	"<tr id='updateTr"+ i +"' class='hideTr'><td><span>更新資料:</span>"
								+	"<input type='hidden' id='maId" + i + "' name='maId' value='"
									+	managerialPageJson[i].maId + "'>"
								+	"<td><input size='12' type='text' id='accrount" + i + "' name='accrount' value='"
									+	managerialPageJson[i].accrount + "'><br><span id='accountMeg'></span>"
								+	"<td><input size='12' type='password' index='" + i + "' id='passwordOne" + i + "' class='passwordOne' placeholder='輸入密碼'>" 
									+	"<br><input size='12' type='password' index='" + i + "' id='passwordTwo" + i + "' class='passwordTwo' placeholder='再次輸入密碼'>"
									+	"<br><span id='passwordMeg" + i + "'></span>";
				
				//抓權限填預設值
				//authName內每個權限輪尋一遍
				for(let j=0 ; j < authName.length ; ++j){
					tableContant	+=	"<td><select id='"+ (authName[j] + i)
									+	"' name='"+ authName[j] +"'>";
					//判斷true 或是false
					for(let k=0 ; k < authArray.length ; ++k){
						tableContant 	+=	"<option value='"+ authArray[k] +"'"
										+ 	((managerialPageJson[i][authName[j]] === authArray[k])?(" selected"):("")) 
										+	">" + (authArray[k].toString());
					}
				}
	
				tableContant	+=	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
									+ "value='資料修改' onclick='updateSubmit("+ i +")'><br>"
//									+ "<input type='button' class='btn btn-default btn-secondary btn-sm'"
//									+ "value='取消修改' onclick='updateHide("+ i +")'>";
			}
			//分頁左側內容, 顯示畫面上的資料數
			paginationContant 	+=	"<div class='col-sm-12 col-md-5'><div>"
								+	"Showing "+ firstDataNumber +" to "+ (firstDataNumber + managerialPageJson.length - 1)
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
			
		}
	})
}

//管理員資訊update開啟與關閉按鈕, 會將其他按鈕關閉
function updateOpen(number){
	//把其他可隱藏的class(hideTr)藏起來
	let tr =  "#updateTr" + number;
	$(".hideTr:not("+ tr +")").hide("fast");
	$(tr).toggle("fast");
}

//管理員資訊update關閉按鈕(!!暫時無用!!)
//function updateHide(number){
//	let tr =  "#updateTr" + number;
//	$(tr).hide("fast");
//}


//管理員資訊update
function updateSubmit(number){
	
	//抓傳送值
	let formData = new FormData();
	formData.append("maId" , $("#maId" + number).val() );
	formData.append("accrount" , $("#accrount" + number).val() );
	formData.append("password" , $("#passwordOne" + number).val() );
	formData.append("accrountAuth" , $("#accrountAuth" + number).val() );
	formData.append("productAuth" , $("#productAuth" + number).val() );
	formData.append("orderAuth" , $("#orderAuth" + number).val() );
	formData.append("webMaintainAuth" , $("#webMaintainAuth" + number).val() );
	
	//更新資料
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("updateUserManagerial"),

		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		data:formData,
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){
			$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
	        $("#dialog_div_wait").dialog("open");
		},
		
		//更新成功先關閉upload畫面, 再刷新當前畫面
		success:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_update").dialog("close");
			buildTable();
		},
		
		//更新失敗先關閉upload畫面, 在顯示錯誤訊息
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>更新帳戶失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
	});
	
}
//新增區:
//管理員資訊insert開啟與關閉按鈕, 會將其他按鈕關閉
function insertShow(){
	//把其他可隱藏的class(hideTr)藏起來
	$(".hideTr:not(#inSertTr)").hide("fast");
	$("#inSertTr").toggle("fast");
}

//管理員資訊insert關閉按鈕(!!暫時無用!!)
//function insertHide(){
//	$("#inSertTr").hide("fast");
//}

//管理員資訊insert
function insertSubmit(){
	
	//抓傳送值
	let formData = new FormData();
	formData.append("accrount" , $("#inAccrount").val() );
	formData.append("password" , $("#inPasswordOne").val() );
	formData.append("accrountAuth" , $("#inaccrountAuth").val() );
	formData.append("productAuth" , $("#inproductAuth").val() );
	formData.append("orderAuth" , $("#inorderAuth").val() );
	formData.append("webMaintainAuth" , $("#inwebMaintainAuth").val() );
	
	//更新資料
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("insertManagerial"),

		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		data:formData,
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){
			$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
	        $("#dialog_div_wait").dialog("open");
		},
		
		//更新成功先關閉upload畫面, 再刷新當前畫面
		success:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_update").dialog("close");
			buildTable();
		},
		
		//更新失敗先關閉upload畫面, 在顯示錯誤訊息
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>更新帳戶失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
	});
	
}

//Dialog設定區
$(function() {
    $("#dialog_div_insert").dialog({
    	//固定視窗
    	maxHeight:	550,
    	maxWidth:	450,
    	minHeight:	550,
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
    	maxHeight:	550,
    	maxWidth:	450,
    	minHeight:	550,
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