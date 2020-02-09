let tableContant;
let paginationContant;
let json;
let topColumn = "<table class='table table-hover'><tr><th>使用者ID"
	+ "	<th>信箱<th>姓氏<th>名稱<th>暱稱<th>性別<th>生日<th>認證<th>";
let userAccountPageJson;
let onePageNunber = 10;
let nowPage = 1;
let maxPage = 1;
let firstDataNumber = 1;
let detailId= 1;
let genderArray= ["男 ","女生","其他"];
let checkstatusArray= ["Y","N"];
let detailContant = "";

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
		url:("userAccount/"+nowPage),
		success:function(data){
			json = data;
			userAccountPageJson = JSON.parse(json.userAccountPageJson);
			tableContant = "";
			paginationContant = "";
			firstDataNumber = (json.page-1)*onePageNunber + 1
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
			
		}
	})
}

//顯示產品Detail的資訊(Dialog內)
function detailUpdate(number){
	//紀錄detailId
	detailId = userAccountPageJson[number].userId;

	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailForm' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable1'>"
					+	"<tr><td>使用者ID:<td>" + detailId
					+	"<tr><td>信箱:<td><input type='text' id='account' name='account' value='" 
						+ userAccountPageJson[number].account + "'></input>"
					+	"<tr><td>密碼:<td><input type='password' id='password' name='password'" 
						+ "placeholder='在此輸入變更的密碼'></input>"
					+	"<tr><td>姓氏:<td><input type='text' id='lastname' name='lastname' value='" 
						+ userAccountPageJson[number].lastname + "'></input>"
					+	"<tr><td>名稱:<td><input type='text' id='firstname' name='firstname' value='" 
						+ userAccountPageJson[number].firstname + "'></input>"
					+	"<tr><td>暱稱:<td><input type='text' id='nickname' name='nickname' value='" 
						+ userAccountPageJson[number].nickname + "'></input>"
					+	"<tr><td>性別:<td><select id='gender' name='gender'>";
	//抓性別資料填預設值
	for(let i=0 ; i < genderArray.length ; ++i){
		detailContant 	+=	"<option value='"+ genderArray[i] +"'"
						+ 	((userAccountPageJson[number].gender === genderArray[i])?(" selected"):("")) 
						+	">" + genderArray[i];
	}
	detailContant 	+=	"</select>"
					+	"<tr><td>生日:<td><input type='text' id='birthday' name='birthday' value='" 
						+ userAccountPageJson[number].birthday 
						+ "' placeholder='在此輸入變更的密碼'></input>"
					+	"<tr><td>電話:<td><input type='text' id='phone' name='phone' value='" 
						+ userAccountPageJson[number].phone + "'></input>"
					+	"<tr><td>住址:<td><input type='text' id='address' name='address' value='" 
						+ userAccountPageJson[number].address + "'></input>"
					+	"<tr><td>認證:<td><select id='checkstatus' name='checkstatus'>";
	//抓認證資料填預設值
	for(let i=0 ; i < checkstatusArray.length ; ++i){
		detailContant 	+=	"<option value='"+ checkstatusArray[i] +"'"
						+ 	((userAccountPageJson[number].checkstatus === checkstatusArray[i])?(" selected"):(""))
						+	">" + checkstatusArray[i];
	}
	
	detailContant 	+=	"</select></td></tr></table></from>"
					+	"<input type='button' onclick='updateDetailData()' value='test'>";
					
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
		
		success:function(data){
			console.log(data);
		}
	});
}






//測試用Dialog
$(function() {
    $("#dialog_div_insert").dialog({
    	//固定視窗
    	maxHeight:	500,
    	maxWidth:	300,
    	minHeight:	500,
    	minWidth:	300,
    	
    	//拖移設定
    	draggable: true,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,
        
        
        closeText :"滑鼠指到X時顯示的文字訊息",
        
        buttons: {
            "Ok": function() {
            		
            		$(this).dialog("close"); 
            	  },
            "Cancel": function() { $(this).dialog("close"); }
        }
    });
 
    $("#insertButton").click(function(productId) {
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
        
        
        closeText :"滑鼠指到X時顯示的文字訊息",
        
        buttons: {
            "Ok": function() {
            		
            		$(this).dialog("close"); 
            	  },
            "Cancel": function() { $(this).dialog("close"); }
        }
    });
 
    $("#opener").click(function(productId) {
        $("#dialog_div_update").dialog("open");
    });
 
});