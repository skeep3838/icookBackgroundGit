package com.icookBackstage.course.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.icookBackstage.course.service.CourseService;
import com.icookBackstage.model.ClassRoomBean;
import com.icookBackstage.model.CourseBean;
import com.icookBackstage.model.Manageral;

@Controller
public class CourseController {
	CourseService service;

	@Autowired
	public void setService(CourseService service) {
		this.service = service;
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	// 課程首頁: 搜尋表單 + 全部課程清單
	@GetMapping("/course/courseList")
	public String searchCoursesForm(Model model) {
		System.out.println("進入controller: searchCoursesForm");
		String courseName = null;
		List<CourseBean> list = service.queryAllCourse();
//		Service似乎有問題
//		Map<Integer, Integer> courseStock = service.courseStock();
		model.addAttribute("courseName", courseName);
		model.addAttribute("courses", list);
//		model.addAttribute("courseStock", courseStock);	
		return "course/courseList";
	}

	// 課程首頁: 模糊搜尋課程清單
	@PostMapping("/course/courseList")
	public String searchCoursesList(@RequestParam("courseName") String courseName, Model model) {
		System.out.println("進入controller: searchCoursesList");
		List<CourseBean> list = null;
//		Map<Integer, Integer> courseStock = service.courseStock();
		if (courseName == null) {
			list = service.queryAllCourse();
		} else {
			list = service.queryCourse(courseName);
		}
		model.addAttribute("courses", list);
//		model.addAttribute("courseStock", courseStock);
		return "course/courseList";
	}

	@GetMapping("/course/courseUpdate")
	public String updateCourseForm(Model model, @RequestParam("id") Integer id) {
		CourseBean courseBean = service.getCourseById(id);
		model.addAttribute("courseBean", courseBean);
		return "course/courseUpdate";
	}

//	取得照片
	@GetMapping(value = "/getPic/{courseId}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse response, @PathVariable Integer courseId) {
		String filePath = "/WEB-INF/views/course/image/food1.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
//		String filename = "";
		int len = 0;
		CourseBean bean = service.getCourseById(courseId);
		if (bean != null) {
			Blob blob = bean.getCourseImage();
//			filename = bean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("Controller的getPicture()發生SQLException:" + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
//				filename = filePath;
			}
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//		String mimeType = context.getMimeType(filePath);
		MediaType mediaType = MediaType.IMAGE_JPEG;
//		System.out.println("mediaType= " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

//取得很多張照片
	private byte[] toByteArray(String filePath) {
		byte[] b = null;
		String realPath = context.getRealPath(filePath);
		File file = new File(realPath);
		long size = file.length();
		b = new byte[(int) size];
		InputStream fis = context.getResourceAsStream(filePath);
		try {
			fis.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	@PostMapping("/course/courseUpdateFinal")
	public String updateCourseFinal(Model model, 
			@RequestParam("id") Integer id,
			@RequestParam("courseName") String courseName, 
			@RequestParam("courseCategory") String courseCategory,
			@RequestParam("courseImage") MultipartFile courseImage, 
			@RequestParam("coursePrice") Integer coursePrice,
			@RequestParam("courseDiscount") Double courseDiscount,
			@RequestParam("hostName") String hostName,
			@RequestParam("roomNo") String roomNo, 
			@RequestParam("courseIntrod") String courseIntrod,
			@RequestParam("courseTime") String courseTime,
			@RequestParam("courseHour") Integer courseHour,
			@RequestParam("courseStartDate") String courseStartDate) {

		CourseBean courseBean = service.getCourseById(id);
		Integer hostId = courseBean.getHostId();
		String coursePhone = courseBean.getCoursePhone();
		String courseMail = courseBean.getCourseMail();

		// 設定寫入圖片參數
		MultipartFile coverImg = courseImage;
		Blob imageBlob = null;

		if (coverImg != null) {
			byte[] buf;

			try {
				buf = coverImg.getBytes();
				Blob blob = new SerialBlob(buf);
				imageBlob = blob;

			} catch (IOException | SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		CourseBean cb = new CourseBean(id, hostId, courseName, courseCategory, imageBlob, hostName,
				courseStartDate, roomNo, courseIntrod, coursePrice, coursePhone, courseMail, courseDiscount,
				(new Date()), courseTime, courseHour);

		Boolean flag = service.updateCourse(cb);
		if (flag == true) {
			model.addAttribute("message", "修改資料成功");
			return "redirect:/course/courseList";
		} else {
			model.addAttribute("message", "修改資料發生問題");
			return "/course/courseList";
		}
	}

	@GetMapping("/course/courseDelete")
	public String deleteCourse(Model model, @RequestParam("id") Integer id) {
		CourseBean courseBean = service.getCourseById(id);
		service.deleteCourse(courseBean);
		return "redirect:/course/courseList";
	}

//	新增課程確認日期
//	提供已預約教室資訊
	@GetMapping(value = "/course/courseAddDate", produces = "text/html;charset=UTF-8")
	public String checkCourseDate(Model model) {
		List<ClassRoomBean> crb = service.roomList();
//		Map<String, String> roomMap= service.queryClassRoom();
		model.addAttribute("roomBean", crb);
//		model.addAttribute("courseDate", roomMap);
		return "course/courseAddDate";
	}

//	回傳教室的map資訊
	@GetMapping(value = "/course/roomJsonMap", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> roomDateMap(Model model) {
		Map<String, String> roomMap = service.queryClassRoom();
		return roomMap;
	}

//	新增課程: 名稱、教室、上課時間、時數
//	回傳的時間是整數字串，需改成時間格式
	@PostMapping("/course/courseAdd1")
	public String addNewCourseForm(Model model,
			@SessionAttribute(value = "currentManager", required = false) Manageral currentSesMan,
			@RequestParam("courseName") String courseName, @RequestParam("roomNo") String roomNo,
			@RequestParam("courseStartDate") String courseStartDate, 
			@RequestParam("courseM") String courseM,
			@RequestParam("courseH") Integer courseH,
			@RequestParam("courseHour") Integer courseHour) {
		// 如果session含有登入者資訊 就直接以該使用者登入
		if (currentSesMan == null) {
			return "managementLogin";
		}
//		System.out.println("courseStartDate1: "+courseStartDate);
//		處理回傳的時間
		String courseTime = courseH +":"+ courseM;
//		String StartDate = (String)courseStartDate;
		model.addAttribute("courseName", courseName);
		model.addAttribute("roomNo", roomNo);
		model.addAttribute("courseStartDate", courseStartDate);
		model.addAttribute("courseTime", courseTime);
		model.addAttribute("courseHour", courseHour);
		return "course/courseAdd";
	}

	@PostMapping("/course/courseAdd2")
	public String addNewCourse(Model model, HttpServletRequest req, 
			@RequestParam("courseName") String courseName,
			@RequestParam("courseCategory") String courseCategory, 
			@RequestParam("hostName") String hostName,
			@RequestParam("courseStartDate") String courseStartDate, 
			@RequestParam("coursePrice") Integer coursePrice,
			@RequestParam("roomNo") String roomNo, 
			@RequestParam("courseDiscount") Double courseDiscount,
			@RequestParam("courseIntrod") String courseIntrod,
			@RequestParam("courseTime") String courseTime,
			@RequestParam("courseHour") Integer courseHour, 
			@RequestParam("courseImage") MultipartFile courseImage,
			@SessionAttribute(value = "currentManager", required = false) Manageral currentSesMan) {
//		設定新增資料
//		Integer hostId = currentSesMan.getMaId();
		String coursePhone = "";
		String courseMail= "";
		System.out.println("courseStartDate2: "+courseStartDate);
		
		String dateString = courseStartDate.substring(1,courseStartDate.length()-1);
		ArrayList<String> dataArray = new ArrayList<>(Arrays.asList(dateString.split(",")));
				
		// 設定寫入圖片參數
		MultipartFile coverImg = courseImage;
		Blob imageBlob = null;

		if (coverImg != null) {
			byte[] buf;

			try {
				buf = coverImg.getBytes();
				Blob blob = new SerialBlob(buf);
				imageBlob = blob;

			} catch (IOException | SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
			}
		}

		CourseBean courseBean = new CourseBean(null, null, courseName, courseCategory, imageBlob, hostName,
				null, roomNo, courseIntrod, coursePrice, coursePhone, courseMail, courseDiscount,
				(new Date()), courseTime, courseHour);
		
//		List<CourseBean> cbl =null;
		for(String cDate:dataArray) {
//			回傳的時間格式多了一對""
			CourseBean cbli = courseBean;
			cbli.setCourseStartDate(cDate.substring(1,cDate.length()-1));
			service.insertCourse(cbli);
//			cbl.add(cbli);
			cbli = courseBean;
		}
		

		return "redirect:/course/courseList";
	}

	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("courseId", "hostId", "courseName", "courseCategory", "courseImage", "hostName",
				"courseStartDate", "courseEndDate", "roomNo", "courseIntrod", "coursePrice", "saleStartDate",
				"saleEndDate", "coursePhone", "courseMail");
	}

}
