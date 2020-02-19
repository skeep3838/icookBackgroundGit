//初始值
typeNumber = 1;
imgLength = 1;

//設定ckeditor的套件資訊
CKEDITOR.replace('test1', {
							width: "100%",
							height: 500
});

//按下新增商品
function waitSubmit(){
	openWait();
	$("#formProduct").submit();
}

function openWait(){
	$("#dialog_div_wait").html("<img src='images/ajaxload.gif'><br><span>載入中...</span>");
    $("#dialog_div_wait").dialog("open");
}

//一鍵輸入
function speetIn(){
	CKEDITOR.instances["test1"].setData("123");
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

//新增type按鈕
function addType(){
	let contant1 = `<div id='typeGroup`+ typeNumber +`'>
	<table>
	<tr>
		<td style='width: 500px; padding-right: 50px;'>
			<div class='form-group'>
				<label>商品規格</label>
				<input type='text' class='form-control' name="typeTitle">
			</div>
		</td>
		<td style='width: 200px; padding-right: 50px;'>
			<div class='form-group'>
				<label>售價</label>
				<input type='text' class='form-control'  name="unitPrice">
			</div>
		</td>
		<td style='width: 200px;  padding-right: 50px;' >
			<div class='form-group'>
				<label>庫存</label>
				<input type='text' class='form-control' name='unitStock'>
			</div>
		</td>
		<td style='width: 100px;' align='center' valign="middle">
			<div class='form-group'>
				<input type='button' class="btn btn-secondary" onclick='deleteType(`+ typeNumber +`)' value='移除類型'>
			</div>
		</td>
	</tr>
</table>
</div>`;
	
//	let contant1 =	"<div id='typeGroup" + typeNumber + "'><hr><table>"
//		+	"<tr><td>類型名稱:<td><input type='text' name='typeTitle'></input>"
//		+	"<td><input type='button' value='移除' onclick='deleteType("+ typeNumber +")'>"
//		+	"<tr><td>售價:<td><input type='text' id='unitPrice" + typeNumber + "' index='"
//			+ typeNumber + "' name='unitPrice' class='unitPrice'>"
//			+ "</input><span id='calculator" + typeNumber + "'></span>"
//		+	"<tr><td>折扣:<td><input type='text' id='discount" + typeNumber + "' index ='"
//			+ typeNumber + "' name='discount' class='discount'></input>"
//		+	"<tr><td>庫存:<td><input type='text' name='unitStock'></input>"
//		+	"<tr><td>已訂貨:<td><input type='text' name='unitOrder'></input>"
//		+	"<tr><td>&nbsp</tr></table></div>";
	
	$("#addButton").before(contant1);
	typeNumber = typeNumber + 1;
}


//================== dialog初始化區  ==================

//wait
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


//error
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