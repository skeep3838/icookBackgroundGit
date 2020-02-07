//設定參數名稱
let roomJsonMap={};
var roomJson="";
var courseEvent=
		[{
				id: 'a',
				title:'麻婆豆腐',
				start: '2020-01-15',
				end: '2020-02-15',
			}]
		
		document.addEventListener('DOMContentLoaded', function () {
			//在HTML下要有個<div id="calendar">，calendar會塞入此div
			var calendarEl = document.getElementById('calendar');
// 			var startDate=document.getElementById("courseStartDate").vlaue;
			//new一個calendar object，FullCalendar api會自動讀取此object來作動作
			//(此object可在value中寫入Javascript程式碼)
			//要呼叫calendar相關指令，必須在此object中進行
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
				unselectable:moment(2,'days')._d,
				validRange: {start: moment()._d,
					end:moment().add(1,'years').d},
				//可不可以拖拉，拖拉結果會改變送出結果
				editable: true,
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
				//日曆下部，可以塞自訂按鈕，我在下面塞了一個submit的按鈕，處理Events中的值並送至Servlet
				footer: {
					right: 'DEMOButton submitButton',
				},
				events:courseEvent,
					 //顏色
//		            backgroundColor: 'white',
//		            borderColor: 'black',
//		            textColor: 'red',
				
// 					[
// 					{
// 						//id:可使用calendar.getElementById(id)獲取此eventObject資料
// 						"id": 'a',
// 						//title:日曆上會出現的字
// 						"title":'麻婆豆腐',
// 						//在日曆上出現的開始與結束時間(ISO-8601 date)T18:00:00T21:00:00
// 						"start": '2020-01-15',
// 						"end": '2020-02-15',
// // 						editable:'false',
// 						//顏色
// // 						backgroundColor: 'lightBlue',
// 						// borderColor: 'black',
// // 						textColor: 'red',
// 					}
// 				],
				
				dateClick: function(date, event, view) {
				    console.log('add event');
				    console.log(date);
				    console.log(event);
				    console.log(view);
				    var startDate=document.getElementById("courseStartDate").value;
				    var seleDate = moment(date.dateStr).format('YYYYMMDD');
				    console.log("seleDate: "+seleDate);

				    if(document.getElementById("roomNo").value==""){
			    		alert("請選擇上課教室。");
			    	}else if(startDate==null || startDate=="" ){
				    	 var r = confirm("是否設為課程開始日期?");
						 if (r == true) {
// 							 時間格式轉換，做比較用
						   txt = "開始上課日期: " + date.dateStr;
						   document.getElementById("courseStartDate").value = date.dateStr;
						   startDate=document.getElementById("courseStartDate").value;
						 } else {
						   txt = "開始上課日期: ";
						 }
						 document.getElementById("showStartDate").innerHTML = txt;
				    }else{
				    	startDate=moment(startDate).format('YYYYMMDD');
				    	console.log(parseInt(seleDate)>parseInt(startDate));
				    	
				    	if(parseInt(seleDate)<=parseInt(startDate)){
				    		alert("結束時間不可在開始時間之前，請重新選擇日期。");
				    	}else{
					    	var r = confirm("是否設為課程結束日期?");
							 if (r == true) {
							   txt = "課程結束日期: " + moment(date.dateStr).format('YYYY-MM-DD');
							   document.getElementById("courseEndDate").value = date.dateStr;
							 } else {
							   txt = "課程結束日期: ";
							 }
							 document.getElementById("showEndDate").innerHTML = txt;
				    	}
				    }
				    console.log(document.getElementById("courseStartDate").value);
				   
				},
				
				select:function( start,end ){

					console.log('select date');
					console.log(start);
					console.log(end);
				},

				eventClick: function(date, event, view) {
					var event = calendar.getEventById('a');
					console.log('modify event');
				    console.log(date);
				    console.log(event);
				    console.log(view);
				  },
				customButtons : {
					submitButton : {
						
				        //按鈕文字
					text : '送出',
					click : function() {
				               alert(event.id);
				               }
				       }
				   }
				  
			})
			
			//生成calendar指令
			calendar.render();
		});
		
// 		清除所有資訊
		function clearAll(){
			$("#showStartDate").html("開始上課日期: ");
			$("#showEndDate").html("課程結束日期: ");
			$("#courseStartDate").val("");
			$("#courseEndDate").val("");
			$("#courseName").val("");
			$("#roomNo").val("上課教室");
		}
		
		function roomInfo(){
			$.ajax({
				type:"GET",
				url:("roomJsonMap"),
				success:function(data){
					roomJsonMap=data;
//					重整不可選擇今天以前的日期
					var roomNo = document.getElementById("roomNo").value;
					roomJson = roomJsonMap[roomNo];
//					roomJson = JSON.parse(roomJsonMap[roomNo]);
					
//					alert(courseEvent);
			 		
				}	
			});
		}
		
		function checkDate(event){
			var startDate=document.getElementById("courseStartDate").value;
			var endDate=document.getElementById("courseEndDate").value;
			console.log(startDate);
			if(startDate==="" || endDate===""){
				alert("填寫資料不完全，請確認是否已選課程日期!");
				event.preventDefault();
			}
		}
				
		
// 			$("#courseInfo").html(eventJson);
//		}


// 		id: 'a',
//		title:'麻婆豆腐',
//		start: '2020-01-15',
//		end: '2020-02-15'

				
// 						editable:'false',
				//顏色
// 						backgroundColor: 'lightBlue',
				// borderColor: 'black',
// 						textColor: 'red',
 			
		
		