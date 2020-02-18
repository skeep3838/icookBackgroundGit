//設定參數名稱
let roomJsonMap = {};
var roomJsonObj = "";
//用在行事曆
var newroomJsonObj = [];
//用在課程時間判斷
var classTimeJson = [];
//紀錄日期，回傳用的json
var dateJson=[];

//var roomJson = "";
//
var roomNo = "";
var courseEvent =
	[{
		id: '201',
		title: '麻婆豆腐',
		start: '2020-01-15',

	}, {
		id: '202',
		title: '龍蝦蝦',
		start: '2020-02-17',
	}];


$(function () {
	$("#dialog_div").dialog({
		//固定視窗
		maxHeight: 800,
		maxWidth: 800,
		minHeight: 800,
		minWidth: 800,
		//拖移設定
		draggable: true,
		//dialog建立自動開啟設定
		autoOpen: false,
		//視窗外無法操作設定
		modal: true,
		closeText: "滑鼠指到X時顯示的文字訊息",
		buttons: {
			"Ok": function () {
				$(this).dialog("close");
			},
			"Cancel": function () { $(this).dialog("close"); }
		}
	});
})

function dateCalendar() {
	if (document.getElementById("roomNo").value == "") {
		alert("請選擇上課教室。");
	} else {
		tex = '<div id="calendar" style="width:50%"></div>';
		// 		tex = '<h3>Hello World!!</h3>'
		$("#dialog_div").html(tex);
		//		$('#calendar').fullCalendar();
		roomInfo();

		$("#dialog_div").dialog("open");
	}

}

//$(function () {
//	  $( "#dialog1" ).dialog({
//	    autoOpen: false
//	  });
//	  
//	  $("#dateCalendar").click(function() {
//	    $("#dialog1").dialog('open');
//	  });
//	});

function roomJson123() {
	roomNos = document.getElementById("roomNo").value;
	newroomJsonObj = [];
	classTimeJson = [];
	var roomJson = null;
	
	dateJson=[];
	$("#showStartDate").html("開始上課日期: ");
	$("#courseStartDate").val("");
	document.getElementById("courseDate").innerHTML="<p id='showStartDate'>課程日期: </p>";
	
	$.ajax({
		type: "GET",
		url: ("roomJsonMap"),
		success: function (data) {
			roomJsonMap = data;
			//			重整不可選擇今天以前的日期
			roomJson = roomJsonMap[roomNos];
			//		    roomJson轉成物件roomJsonObj
			
			roomJsonObj = JSON.parse(roomJson); //object

			//		     處理回傳的時間字串做分類
			roomJsonObj.forEach(function (item, i) {
				var t = item.cTime.substr(0, 5);
				_startTime = t.split(":");
				//		    	將時間資訊格式化
				console.log("修改前: " + item.start);
				//		    	var startDate = new Date(moment(item.start).format('YYYYMMDD'), 
				//		    							_startTime[0], _startTime[1], 0);
				console.log("item.cTime: " + t);
				var txt = item.cTime.substr(0, 5) + "~" + ((parseInt)(_startTime[0]) + 1) + ":" + (_startTime[1]);
				//		    	用在行事曆，顯示使用時間
				newroomJsonObj.push({
					title: txt,
					start: item.start,
					className: item.className
				});
				console.log("時間: " + newroomJsonObj.title);
				//		    	用在時間判斷
				classTimeJson.push({
					start: item.start,
					cTime: item.cTime,
					cHour: item.cHour
				});
				console.log(newroomJsonObj);
				console.log(classTimeJson);

			});

			roomInfo(newroomJsonObj);
		}
	});
}

function roomInfo(roomJson) {
	var calendarInnerTex = document.getElementById("calendar").innerHTML;

	if (calendarInnerTex != "") {
		document.getElementById("calendar").innerHTML = "";
	}

	var startDate = document.getElementById("courseStartDate").value;
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		//要使用的plugins，至少要有'dayGrid'
		plugins: ['interaction', 'dayGrid'],
		//預設要用什麼視角，有dayGridMonth , dayGridWeek , dayGridDay
		defaultView: 'dayGridMonth',
		//預設天是哪一天，這邊可以套用moment.js來作操作，預設為今天
		defaultDate: moment()._d,
		//要不要讓user切換month,week,day views
		navLinks: false,
		//可不可以選取
		selectable: true,
		//				不能選某個日期以前的時間
		unselectable: moment(2, 'days')._d,
		validRange: {
			start: moment()._d,
			end: moment().add(1, 'years').d
		},
		//		設為整天模式
		allDayDefault: true,
		//可不可以拖拉，拖拉結果會改變送出結果
		editable: false,
		//一天太多event，會在下面集合成"more.."
		eventLimit: true,

		//日曆上部，可以塞自訂按鈕，如要塞下拉式選單必須直接在外面使用append的方式更動DOM tree
		header: {
			left: 'addEventButton ',
			//預設title為月份/年份
			center: 'title',
			//查看上個月與下個月按鈕
			right: 'prev,next'
		},

		// your event source
		events: roomJson,
		eventBackgroundColor: "white",
		eventTextColor: "red",

		dateClick: function (date, event, view) {
			console.log('add event');
			console.log(date);

			var r = confirm("是否設為課程日期?");
			if (r == true) {
				// 							 時間格式轉換，做比較用
				txt = "<p id='showStartDate'>課程日期: " + date.dateStr + "</p>";
				document.getElementById("courseStartDate").value = date.dateStr;
				dateJson[dateJson.length]=date.dateStr;
//				console.log(dateJson);
			} else {
				txt = "課程日期: ";
			}
			
			if(dateJson.length==1){
				document.getElementById("courseDate").innerHTML=txt;
			}else{
				$(".courseDate").append(txt);
			}

//			document.getElementById("showStartDate").innerHTML = txt;
			// }
			// console.log(document.getElementById("courseStartDate").value);

		},
	})

	//生成calendar指令
	calendar.render();
}



// 		清除所有資訊
function clearAll() {
	dateJson=[];
	$("#showStartDate").html("開始上課日期: ");
	$("#courseStartDate").val("");
	document.getElementById("courseDate").innerHTML="<p id='showStartDate'>課程日期: </p>";
	document.getElementById("calendar").innerHTML = "";
	$("#courseName").val("");
	$("#roomNo").val("上課教室");
}


function checkDate() {
//	var startDate = document.getElementById("courseStartDate").value;
//	alert(dateJson);
	if (dateJson == "") {
		alert("填寫資料不完全，請確認是否已選課程日期!");
		event.preventDefault();
	}else{
		dateJson2=JSON.stringify(dateJson);
		$("#courseStartDate").val(dateJson2);
//		alert("dateJson: "+typeof(dateJson2) +":"+ dateJson2);
	}
}

function keyIn1() {
	$("#courseName").val("法式料理課 - 鮮蝦義大利麵");

}
function keyIn2() {
	$("#courseCategory").val("法式");
	$("#coursePrice").val("10000");
	$("#courseDiscount").val("1");
	$("#hostName").val("李嚴");
	$("#courseIntrod").val("蝦的挑選、烹煮與保存蝦去殼的完整手法: \n製作濃郁的蝦濃湯、醬汁與乾燥義大利麵的烹煮");
}







