let tableContant;
let paginationContant;
let json;
let topColumn = "<table id='newTable' class='table table-hover'><tr><th>公告ID"
	+ "	<th>公告標題<th class='contentTop'>公告內容<th>創建時間<th>修改時間<th><th>";
let noDataContant = "<tr><td colspan=7 >not any date"
let newsPageJson;
let onePageNunber = 10;
let nowPage = 1;
let maxPage = 1;
let firstDataNumber = 1;
let detailId= 1;
let detailContant = "";
let createNoError = 1;
let reTag = /<(?:.|\s)*?>/g;

let searchMod = false;
let searchInt;


//載入完成後匯入公告資訊
$(document).ready(function(){
	console.log("start_first_select")
	nowPage = 1;
	buildTable();
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

//將公告內容的HTML標籤濾掉
function getOnlyText(context){
	console.log(context);
	let tempContext = context.replace(reTag,"");
	return tempContext;
}

//顯示每一頁公告table的資訊
function buildTable(){
	
	let goUrl = "";
	
	//確認是否為搜尋模式
	if(searchMod){
		goUrl = "newsSearch/"+searchInt;
	}else{
		goUrl = "news/"+nowPage;
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
			if(json.newsPageJson != null){
				newsPageJson = JSON.parse(json.newsPageJson);
				
				//table內容	
				for(let i=0 ; i<newsPageJson.length ; ++i){
					tableContant	+= 	"<tr><td>" + newsPageJson[i].newsId
									+	"<td>" + newsPageJson[i].title
									+	"<td class='context'>" + (getOnlyText(newsPageJson[i].newsContent))
									+	"<td>" + newsPageJson[i].createTime
									+	"<td>" + newsPageJson[i].updateTime
									+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
									+	"value='修改公告' onclick='updateNews("+ i +")'>"
									+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
									+	"value='移除公告' onclick='deleteNews("+ i +")'>";
				}
				
				//分頁左側內容, 顯示畫面上的資料數
				paginationContant 	+=	"<div class='col-sm-12 col-md-5'><div>"
									+	"Showing "+ firstDataNumber +" to "+ (firstDataNumber + newsPageJson.length - 1)
									+	" of "+ json.allNewsNumber +" entries"
									+	"</div></div>";
				
				//分頁右側內容, 顯示分頁按鈕, 先顯示上一頁按鈕				
				paginationContant	+=	"<div class='col-sm-12 col-md-7'> <div class='dataTables_paginate' id='dataTables_paginate'>"
									+	"<ul class='pagination'> <li class='Previous'> <a class='paginate_button' href='javascript:void(0);'>Previous</a></li>";
				
				//計算最後頁
				maxPage = ((json.allNewsNumber)>onePageNunber) ? parseInt((json.allNewsNumber)/onePageNunber)+1 : 1 ;
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

//============ 更新帳戶功能區: ============

//公告update的Detail資訊(Dialog內)
function updateNews(number){
	
	//紀錄detailId
	detailId = newsPageJson[number].newsId;

	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailForm' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable1'>"
					+	"<tr><td>公告ID:<td>" + detailId
					+	"<tr><td>公告標題:<td><input type='text' id='title' name='title' value='" 
						+ newsPageJson[number].title + "'></input>"
					+	"<tr><td>公告內容:<td><textarea id='newsContent' name='newsContent' style='width:1000px; height: 300px;'>"
						+ newsPageJson[number].newsContent + "</textarea>"
//					+	"<tr><td><input type='hidden' name='createTime' value='"
//						+ newsPageJson[number].createTime +"'>"
					+	"</td></tr></table></from>";
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div_update").html(detailContant);
	$("#dialog_div_update").dialog("open");
	CKEDITOR.replace('newsContent');
}


//update Detail資訊
function updateDetailData(){
	//打包form(id="detailForm")的資料
	let detailForm = new FormData($("#detailForm")[0]);
	
	//更新CKEditor的內容到formDate上
	detailForm.set("newsContent", CKEDITOR.instances.newsContent.getData());
			
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("updateNews/"+ detailId),

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
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_update").dialog("close");
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>更新公告失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
		
		
	});
}

//============ 新增公告功能區: ============

//公告insert的Detail資訊(Dialog內)
function insertNews(number){
	
	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailFormIn' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable1'>"
					+	"<tr><td>公告ID:<td>"
					+	"<tr><td>公告標題:<td><input type='text' id='titleIn' name='title'></input>"
					+	"<tr><td>公告內容:<td><textarea id='newsContentIn' name='newsContent'" 
						+ " style='width:1000px; height: 300px;'></textarea>"
					+	"</td></tr></table></from>";
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div_insert").html(detailContant);
	$("#dialog_div_insert").dialog("open");
	CKEDITOR.replace('newsContentIn');
}


//insert Detail資訊
function insertDetailData(){
	//打包form(id="detailForm")的資料
	let detailForm = new FormData($("#detailFormIn")[0]);
	
	//更新CKEditor的內容到formDate上
	detailForm.set("newsContent", CKEDITOR.instances.newsContentIn.getData());
			
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:"insertNews",

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
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_insert").dialog("close");
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>新增公告失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
		
		
	});
}

//============ 刪除公告功能區: ============

//按下移除公告按鈕
function deleteNews(number){
	
	//出現確認視窗
	if(confirm("確定要移除公告?")){
		//紀錄detailId
		detailId = newsPageJson[number].newsId;
		
		$.ajax({ 
			type:"GET",
			cache:true,
			
			//header不傳contexntType
			contentType:false,
			url:("deleteNews/"+ detailId),
			
			//ajax傳送更新資料前 先出現upload畫面
			beforeSend:function(){
				$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
		        $("#dialog_div_wait").dialog("open");
			},
			
			//更新成功先關閉upload畫面, 再刷新當前畫面
			success:function(data){
				console.log(data)
				$("#dialog_div_wait").dialog("close");
				$("#dialog_div_update").dialog("close");
				buildTable();
			},
			
			error:function(data){
				$("#dialog_div_wait").dialog("close");
				$("#dialog_div_error").html("<span class='errorFont'>更新公告失敗!</span>");
				$("#dialog_div_error").dialog("open");
			},
		});
	}
	
}

//============ Dialog設定區: ============

//新增
$(function() {
    $("#dialog_div_insert").dialog({
    	//固定視窗
    	maxHeight:	600,
    	maxWidth:	800,
    	minHeight:	600,
    	minWidth:	800,
    	
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

//更新
$(function() {
    $("#dialog_div_update").dialog({
    	//固定視窗
    	maxHeight:	600,
    	maxWidth:	800,
    	minHeight:	600,
    	minWidth:	800,
    	
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

//等待資料傳輸轉圈圈
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

//資料傳輸錯誤
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



