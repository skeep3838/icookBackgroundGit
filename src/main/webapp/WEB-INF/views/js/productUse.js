/**
 * Product Use
 */

//必要參數
let productStatus ="true";
let tableId="#test1";
let pageArea="#pageArea1";
let json;
let topColumn = "<table class='table table-hover'><tr><th>商品ID"
	+ "	<th>商品名稱<th>總類<th>折扣<th>庫存狀態<th>更新時間<th><th>";
let noDataContant = "<tr><td colspan=8 >not any date"
let productPageJson;
let tableContant = "";
let paginationContant = "";
let chickMap = null;
let onePageNunber = 10;
let nowPage = 1;
let maxPage = 1;
let firstDataNumber = 1;
let detailContant = "";
let detailId = 1;
let splitPictureStr = null;
let pathName = "";
let projectName = "";
let strTest = "";
let index="";
let viewImg="";
let searchMod = false;
let searchInt;

let typeNumber;
let pirtureNumber;

let imgLength;

//載入完成先觸發一次"上架商品"頁籤
$(document).ready(function(){
	console.log("start_first_click_Tag1")
	$("#tabsTag1").click()
});

//點擊"上架商品"頁籤, 初始化回第一頁資料
$("#tabsTag1").click(function(){ 
	nowPage = 1;
	productStatus ="true";
	searchMod=false;
	tableId="#test1";
	pageArea="#pageArea1";
	$("#searchIn1").val("")
	buildTable();
});

//點擊"下架商品"頁籤, 初始化回第一頁資料
$("#tabsTag2").click(function(){ 
	nowPage = 1;
	productStatus ="false";
	searchMod=false;
	tableId="#test2";
	pageArea="#pageArea2";
	$("#searchIn2").val("")
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
	
	//判斷抓哪個搜尋欄位
	if(productStatus === "true"){
		searchInt = $("#searchIn1").val();
	}else if(productStatus === "false"){
		searchInt = $("#searchIn2").val();
	}else{
		console.log("[sys]:productStatus is falded")
	}
	
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

//是否特價確認方法
function discountChick(array){
	for(let i=0 ; i<array.length ; ++i){
		if(array[i].discount != 1){
			return "特價中";
		}
	}
	return "沒有特價";
}

//庫存檢查方法
function stockChick(array){
	chickMap = [{"name":"已經沒有庫存","value":""},{"name":"已經沒有庫存(補貨中..)","value":""}
					,{"name":"即將沒有庫存","value":""},{"name":"即將沒有庫存(補貨中..)","value":""}
					,{"name":"庫存充足","value":""}];
	let stockLow = 10;
	let result = "";
	
	for(let i=0 ; i<array.length ; ++i ){
		if(array[i].unitStock <= 0){
			if(array[i].unitOrder > 0){
				chickMap[1].value += array[i].typeID + ",";
			}
			chickMap[0].value += array[i].typeID + ",";
		}else if(array[i].unitStock - stockLow <= 0){
			if(array[i].unitOrder > 0){
				chickMap[3].value += array[i].typeID + ",";
			}
			chickMap[2].value += array[i].typeID + ",";
		}else {
			chickMap[4].value += array[i].typeID + ",";
		}
	}
	for(let i=0 ; i<chickMap.length ; ++i ){
		if(chickMap[i].value != ""){
			result += chickMap[i].name;
			if(chickMap[i].name != "庫存充足"){
				result += " <small>*("+chickMap[i].value+ ")</small>" ;
			}
			
			return result;
		}
	}
	return "計算錯誤";
}

//動態計算優惠價格(改"售價"或"折扣"時)
$("body").on("keyup",".unitPrice,.discount",function(){
	//取index
	let index = $(this).attr("index");
	let calculatorId = "#calculator" + index;
	let	unitPriceId = "#unitPrice" + index;
	let	discountId = "#discount" + index;
	
	//計算價錢
	let price 	= $(unitPriceId).val() + "×" + $(discountId).val() 
				+ "= " + ($(unitPriceId).val()*$(discountId).val())
	
	//更新顯示的價格
	$(calculatorId).text(price);
});

//切割圖片並加入路徑
function splitPicture(number){
	//檢查有沒有圖檔
	if(productPageJson[number].image1 !== ""){
		splitPictureStr = productPageJson[number].image1.split(",");
		console.log(splitPictureStr);
//		getRootPath();
		
		//將圖檔加入路徑
//		for(let i = 0 ; i < splitPictureStr.length ; ++i){
//			splitPictureStr[i] = projectName + "/" + splitPictureStr[i];
//			console.log(i);
//			console.log(splitPictureStr);
//		}
	}else{
		splitPictureStr = "";
	}
	
}

//預覽圖片功能
function readURL(input) {
	index = $(input).attr("index");
	viewImg = "#viewImg" + index;
	
	console.log(viewImg);
	
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$(viewImg).attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$("body").on("change",".images",function(){
	console.log("change done")
	readURL(this);
	//載入圖片完成後, 判斷是否需要給新增按鈕(為最後一個按鈕? and 不超過5個)
	console.log("if:"+ (($(this).attr("index") === imgLength.toString()) && (imgLength < 5)) )
	if( ($(this).attr("index") === imgLength.toString()) && (imgLength < 5) ){
		let divId = "#imgDiv"+ imgLength;
		
		imgLength = imgLength + 1;
		console.log("divId:" + divId);
		let context =	"<div class='imgDiv' id='imgDiv"+ imgLength +"'><label for='img"+ imgLength +"'>"
					+	"<input type='file' name='image1' index='"+ imgLength +"' id='img"+ imgLength +"' class='images'>"
					+	"<img class='viewImgClass' id='viewImg" + imgLength + "' src='images/addPicture.png'></label></div>";
		console.log("context:" + context);
		$(divId).after(context);
	}
});

//刪除type按鈕
function deleteType(index){
	let typeGroup = "#typeGroup" + index;
	$(typeGroup).remove();	
}

//最下面新增type按鈕
function addType(){
	let contant1 =	"<div id='typeGroup" + typeNumber + "'><hr><table>"
		+	"<tr><td>類型名稱:<td><input type='text' name='typeTitle'></input>"
		+	"<td><input type='button' value='移除' onclick='deleteType("+ typeNumber +")'>"
		+	"<tr><td>售價:<td><input type='text' id='unitPrice" + typeNumber + "' index='"
			+ typeNumber + "' name='unitPrice' class='unitPrice'>"
			+ "</input><span id='calculator" + typeNumber + "'></span>"
		+	"<tr><td>折扣:<td><input type='text' id='discount" + typeNumber + "' index ='"
			+ typeNumber + "' name='discount' class='discount'></input>"
		+	"<tr><td>庫存:<td><input type='text' name='unitStock'></input>"
		+	"<tr><td>已訂貨:<td><input type='text' name='unitOrder'></input>"
		+	"<tr><td>&nbsp</tr></table></div>";
	
	$("#addButton").before(contant1);
	typeNumber = typeNumber + 1;
}

function getRootPath(){
	//獲取當前網址，如：/icookBackstage02035/demoMyProduct.page
	pathName = window.document.location.pathname;
	//取得專案名稱, 如: /icookBackstage0203
	projectName = pathName.substring(0, (pathName.substr(1).indexOf('/') + 1))
	}


//換頁數並刷新畫面
$("#tabs").on("click",".paginate_button",function(){
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
		goUrl = "produSearch/" + productStatus + "/" + searchInt + "/" + nowPage;
	}else{
		goUrl = "products/" + productStatus + "/" + nowPage;
	}
	
	$.ajax({ 
		type: "GET",
		url: goUrl,
		success:function(data){
			json = data;
			tableContant = "";
			paginationContant = "";
			firstDataNumber = (json.page-1)*onePageNunber + 1
			//判斷是否有值
			if(json.productPageJson != null){
				productPageJson = JSON.parse(json.productPageJson);
				//table內容
				for(let i=0 ; i<productPageJson.length ; ++i){
					tableContant	+= 	"<tr><td>" + productPageJson[i].productID
									+	"<td>" + productPageJson[i].productName
									+	"<td>" + productPageJson[i].category
									+	"<td>" + discountChick(productPageJson[i].type)
									+	"<td>" + stockChick(productPageJson[i].type)
									+	"<td>" + productPageJson[i].updateTime
									+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
										+ "value='詳細修改' onclick='detailUpdate("+ i +")'>"
										+	"<td><input type='button' class='btn btn-default btn-secondary btn-sm'"
										+ "value='"+ ((productStatus === "true")?"下架商品":"上架商品") + "' onclick='changeStatus("+ i +")'>";
				}
				//分頁左側內容, 顯示畫面上的資料數
				paginationContant 	+=	"<div class='col-sm-12 col-md-5'><div>"
									+	"Showing "+ firstDataNumber +" to "+ (firstDataNumber + productPageJson.length - 1)
									+	" of "+ json.allProductNumber +" entries"
									+	"</div></div>";
			
				//分頁右側內容, 顯示分頁按鈕, 先顯示上一頁按鈕				
				paginationContant	+=	"<div class='col-sm-12 col-md-7'> <div class='dataTables_paginate' id='dataTables_paginate'>"
									+	"<ul class='pagination'> <li class='Previous'> <a class='paginate_button' href='javascript:void(0);'>Previous</a></li>";
			
				//計算最後頁
				maxPage = ((json.allProductNumber)>onePageNunber) ? parseInt((json.allProductNumber)/onePageNunber)+1 : 1 ;
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
				$(tableId).html(topColumn + tableContant);
				$(pageArea).html(paginationContant);
			}else{
				//寫入沒有資料的訊息
				$(tableId).html(topColumn + noDataContant);
				$(pageArea).html("");
			}
		}
	})
}

//顯示產品Detail的資訊(Dialog內)
function detailUpdate(number){
		
	//紀錄detailId
	detailId = productPageJson[number].productID;

	//切割圖片
	splitPicture(number);
	
	//Detail資訊內容建立
	detailContant 	= 	"<form id='detailForm' method='post' enctype='multipart/form-data'>"
					+	"<table id='detailTable1'>"
					+	"<tr><td>產品ID:<td>" + detailId
					+	"<tr><td>產品名稱:<td><input type='text' id='productName' name='productName' value='" 
						+ productPageJson[number].productName + "'></input>"
					+	"<tr><td>產品種類:<td><input type='text' id='category' name='category' value='"
						+ productPageJson[number].category + "'></input>"
					+	"<tr><td>產品資訊:<td><textarea style='width: 700px; height: 240px;' id='productInfo'" 
						+ "name='productInfo'>" + productPageJson[number].productInfo + "</textarea></tr></table>"
					+	"<div><span>更新圖片</span></div>"; //test
//					+	"<table id='datailTable2'><tbody style='width:100%;'><tr><td colspan='3'>更新圖片"
//					+	"<tr><td><input type='file' name='image1' index='1' id='img1' class='images'> <td><input type='file' name='image1' index='2' id='img2' class='images'>" 
//					+	"<td><input type='file' name='image1' index='3' id='img3' class='images'><tr>";
					
	//產生預覽圖片
//	for(let i = 1 ; i <= 3 ; ++i){
//		detailContant 	+= 	"<td class='viewImgTd'><label for='img"+ i +"'>" 
//						+	"<img class='viewImgClass' id='viewImg" + i + "' src='" 
//						+ 	((splitPictureStr.length>=i)? splitPictureStr[i-1] : "#") + "'>"
//						+	"</label>"
//	}
	
	for(imgLength = 1 ; imgLength <= splitPictureStr.length ; ++imgLength){
		detailContant 	+=	"<div class='imgDiv' id='imgDiv"+ imgLength +"'><label for='img"+ imgLength +"'>"
						+	"<input type='file' name='image1' index='"+ imgLength +"' id='img"+ imgLength +"' class='images'>"
						+	"<img class='viewImgClass' id='viewImg" + imgLength + "' src='"
							+ splitPictureStr[imgLength-1] + "'></label></div>";
	}
	
	//如果還沒滿五張圖, 就顯示新增圖片按鈕
	if(splitPictureStr.length < 5){
		detailContant	+=	"<div class='imgDiv' id='imgDiv"+ imgLength +"'><label for='img"+ imgLength +"'>"
						+	"<input type='file' name='image1' index='"+ imgLength +"' id='img"+ imgLength +"' class='images'>"
						+	"<img class='viewImgClass' id='viewImg" + imgLength + "' src='images/addPicture.png'></label></div>";
	}
	
	detailContant 	+=	"<div style='clear:both;'></div><br>";
//					+	"<table id='detailTable3'>";
	//建立類型迴圈
	let type = productPageJson[number].type;
	typeNumber = type.length;
	for(let i=0 ; i < typeNumber ; ++i ){
		detailContant	+=	"<div id='typeGroup" + i + "'><hr><table>"
						+	"<tr><td>類型名稱:<td><input type='text' name='typeTitle' value='" 
							+ type[i].typeTitle + "'></input>"
							+ "<td><input type='button' value='移除' onclick='deleteType("+ i +")'>"
						+	"<tr><td>售價:<td><input type='text' id='unitPrice" + i + "' index='"
							+ i + "' name='unitPrice' class='unitPrice' value='" 
							+ type[i].unitPrice + "'></input><span id='calculator" + i + "'>"
							+ type[i].unitPrice + "×" + type[i].discount + "= " 
							+ (type[i].unitPrice*type[i].discount)+ "</span>"
						+	"<tr><td>折扣:<td><input type='text' id='discount" + i + "' index ='"
							+ i + "' name='discount' class='discount' value='" 
							+ type[i].discount + "'></input>"
						+	"<tr><td>庫存:<td><input type='text' name='unitStock' value='" 
							+ type[i].unitStock + "'></input>"
						+	"<tr><td>已訂貨:<td><input type='text' name='unitOrder' value='" 
							+ type[i].unitOrder + "'></input>"
						+	"<tr><td>&nbsp</tr></table></div>";
	}
	
	detailContant		+=	"<input type='button' value='新增Type' id='addButton' onclick='addType()'></form>" 
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div_update").html(detailContant);
	$("#dialog_div_update").dialog("open");
	//把textarea改成ckEditor
	CKEDITOR.replace('productInfo');
}

//update Detail資訊
function updateDetailData(){
	//打包form(id="detailForm")的資料
	let detailForm = new FormData($("#detailForm")[0]);
	
	//更新CKEditor的內容到formDate上
	detailForm.set("productInfo", CKEDITOR.instances.productInfo.getData());
	
	$.ajax({ 
		type:"POST",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("updateProduct/"+ detailId),

		
		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		data:detailForm,
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){
			$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>上傳圖片中...</span>");
	        $("#dialog_div_wait").dialog("open");
		},
		
		success:function(data){
			console.log(data);
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_update").dialog("close");
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_wait").dialog("close");
			$("#dialog_div_error").html("<span class='errorFont'>產品更新失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
	});
}

//上下架商品功能
function changeStatus(number){
	detailId = productPageJson[number].productID;
	
	$.ajax({ 
		type:"GET",
		cache:true,
		
		//header不傳contexntType
		contentType:false,
		url:("changeStatus/"+ productStatus +"/"+ detailId),

		//ajax會自動將data改成字串型態, 這裡使用processData:false來阻止資料被轉成字串, 不然multipart資料接收會錯誤
		processData:false,
		
		//ajax傳送更新資料前 先出現upload畫面
		beforeSend:function(){

		},
		
		success:function(data){
			if(number === 0 && maxPage!= 1){nowPage = nowPage-1}
			buildTable();
		},
		
		error:function(data){
			$("#dialog_div_error").html("<span class='errorFont'>狀態改變失敗!</span>");
			$("#dialog_div_error").dialog("open");
		},
	});
	
}

//測試用Dialog
$(function() {
    $("#dialog_div_update").dialog({
    	//固定視窗
    	maxHeight:	800,
    	maxWidth:	800,
    	minHeight:	800,
    	minWidth:	800,
    	
    	//拖移設定
    	draggable: true,
    	
    	//dialog建立自動開啟設定
        autoOpen: false,
        
        //視窗外無法操作設定
        modal : true,
        
        //open事件發生時, 將dialog樣式右上的x顯示
        open:function(event,ui){$(".ui-dialog-titlebar-close").show();},
        
        closeText :"滑鼠指到X時顯示的文字訊息",
        
        buttons: {
            "Update": function() {
            		updateDetailData();
            	  },
            "Cancel": function() { $(this).dialog("close"); }
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

$(function() {
    $("#dialog_div_ok").dialog({
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
		$("#dialog_div_error").html("<span class='errorFont'>改變狀態OK!</span>");
        $("#dialog_div_error").dialog("open");
    });
});






