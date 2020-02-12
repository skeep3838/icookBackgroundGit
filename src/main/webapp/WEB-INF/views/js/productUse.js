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


//載入完成先觸發一次"上架商品"頁籤
$(document).ready(function(){
	console.log("start_first_click_Tag1")
	$("#tabsTag1").click()
});

//點擊"上架商品"頁籤, 初始化回第一頁資料
$("#tabsTag1").click(function(){ 
	nowPage = 1;
	productStatus ="true";
//	let page = 1;	//好像沒用到
	tableId="#test1";
	pageArea="#pageArea1";
	buildTable();
});

//點擊"下架商品"頁籤, 初始化回第一頁資料
$("#tabsTag2").click(function(){ 
	nowPage = 1;
	productStatus ="false";
//	let page = 1;	//好像沒用到
	tableId="#test2";
	pageArea="#pageArea2";
	buildTable();
});

//測試用
function ajaxGet(){
	$.ajax({ 
		type:"GET",
		url:("products/"+productStatus+"/"+nowPage),
		success:function(data){
			json = data;
		}
	})
}

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
	let stockLow = 5;
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
});

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


function productDetail(productID){

}


//顯示每一頁產品table的資訊
function buildTable(){
	$.ajax({ 
		type:"GET",
		url:("products/"+productStatus+"/"+nowPage),
		success:function(data){
			json = data;
			productPageJson = JSON.parse(json.productPageJson);
			tableContant = "";
			paginationContant = "";
			firstDataNumber = (json.page-1)*onePageNunber + 1
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
									+ "value='下架商品' onclick='#'>";
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
					+	"<tr><td>產品資訊:<td><textarea style='width: 500px; height: 180px;' id='productInfo'" 
						+ "name='productInfo'>" + productPageJson[number].productInfo + "</textarea></tr></table>"
					+	"<table id='datailTable2'><tbody style='width:100%;'><tr><td colspan='3'>更新圖片" 
					+	"<tr><td><input type='file' name='image1' index='1' id='img1' class='images'> <td><input type='file' name='image1' index='2' id='img2' class='images'>" 
					+	"<td><input type='file' name='image1' index='3' id='img3' class='images'><tr>";
					
	//產生預覽圖片
	for(let i = 1 ; i <= 3 ; ++i){
		detailContant 	+= 	"<td class='viewImgTd'><label for='img"+ i +"'>" 
						+	"<img class='viewImgClass' id='viewImg" + i + "' src='" 
						+ 	((splitPictureStr.length>=i)? splitPictureStr[i-1] : "#") + "'>"
						+	"</label>"
	}
	
	detailContant 	+=	"<tr><td>&nbsp</tr></table>"
					+	"<table id='detailTable3'>";
	//建立類型迴圈
	let type = productPageJson[number].type;
	for(let i=0 ; i < type.length ; ++i ){
		detailContant	+=	"<tr style='border-top: 1px solid #ddd;'><td>類型ID:<td>" + type[i].typeID
						+	"<tr><td>類型名稱:<td><input type='text' name='typeTitle' value='" 
							+ type[i].typeTitle + "'></input>"
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
						+	"<tr><td>&nbsp</tr>";
	}
	
	detailContant		+=	"</table></form>" 
						+	"<input type='button' onclick='updateDetailData()' value='test'>"
					
	//將Detail資訊寫到Dialog, 並顯示Dialog
	$("#dialog_div").html(detailContant);
	$("#dialog_div").dialog("open");
	//把textarea改成ckEditor
	CKEDITOR.replace('productInfo');
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
		url:("updateProduct/"+ detailId),

		
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
    $("#dialog_div").dialog({
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
        
        
        closeText :"滑鼠指到X時顯示的文字訊息",
        
        buttons: {
            "Ok": function() {
            		
            		$(this).dialog("close"); 
            	  },
            "Cancel": function() { $(this).dialog("close"); }
        }
    });
 
    $("#opener").click(function(productId) {
        $("#dialog_div").dialog("open");
    });
 
});


