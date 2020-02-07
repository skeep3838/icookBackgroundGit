package com.icookBackstage.course.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
		model.addAttribute("courseName", courseName);
		model.addAttribute("courses", list);
		return "course/courseList";
	}

	// 課程首頁: 模糊搜尋課程清單
	@PostMapping("/course/courseList")
	public String searchCoursesList(@RequestParam("courseName") String courseName, Model model) {
		System.out.println("進入controller: searchCoursesList");
		List<CourseBean> list = null;
		if (courseName == null) {
			list = service.queryAllCourse();
		} else {
			list = service.queryCourse(courseName);
		}
		model.addAttribute("courses", list);
		return "course/courseList";
	}
	
	@GetMapping("/course/courseUpdate")
	public String updateCourseForm(Model model,@RequestParam("id")Integer id) {
		CourseBean courseBean = service.getCourseById(id);
		model.addAttribute("courseBean", courseBean);
		return "course/courseUpdate";
	}
	
	@PostMapping("/course/courseUpdate")
	public String updateCourse(Model model,@RequestParam("id")Integer id) {
		CourseBean courseBean = service.getCourseById(id);
		model.addAttribute("courseBean", courseBean);
		return "course/courseUpdate";
	}

//	新增課程確認日期
//	提供已預約教室資訊
	@GetMapping(value = "/course/courseAddDate", produces="text/html;charset=UTF-8")
	public String checkCourseDate(Model model) {
		List<ClassRoomBean> crb = service.roomList();
//		Map<String, String> roomMap= service.queryClassRoom();
		model.addAttribute("roomBean", crb);
//		model.addAttribute("courseDate", roomMap);
		return "course/courseAddDate";
	}
	
//	回傳教室的map資訊
	@GetMapping(value = "/course/roomJsonMap", produces="application/json")
	@ResponseBody
	public Map<String, String> roomDateMap(Model model) {
		Map<String, String> roomMap= service.queryClassRoom();
		return roomMap;
	}

	@PostMapping("/course/courseAdd1")
	public String addNewCourseForm(Model model,
			@SessionAttribute(value = "currentManager", required = false) Manageral currentSesMan,
			@RequestParam("courseName")String courseName,
			@RequestParam("roomNo")String roomNo,
			@RequestParam("courseStartDate")String courseStartDate,
			@RequestParam("courseEndDate")String courseEndDate) {
		// 如果session含有登入者資訊 就直接以該使用者登入
		if (currentSesMan == null) {
			return "managementLogin";
		}
		model.addAttribute("courseName",courseName);
		model.addAttribute("roomNo", roomNo);
		model.addAttribute("courseStartDate", courseStartDate);
		model.addAttribute("courseEndDate", courseEndDate);
		model.addAttribute("hostId",currentSesMan.getAccrount());
		return "course/courseAdd";
	}

	@PostMapping("/course/courseAdd2")
	public String addNewCourse(Model model, HttpServletRequest req,
			@RequestParam("courseImage") MultipartFile courseImage,
			@SessionAttribute(value = "currentManager", required = false) Manageral currentSesMan) {

//		設定新增資料
//		Integer hostId = currentSesMan.getMaId();
		String courseName = req.getParameter("courseName");
		String courseCategory = req.getParameter("courseCategory");
		String hostName = req.getParameter("hostName");
		String courseStartDate = req.getParameter("courseStartDate");
		String courseEndDate = req.getParameter("courseEndDate");
		String saleStartDate = req.getParameter("saleStartDate");
		String saleEndDate = req.getParameter("saleEndDate");
		String coursePrice = req.getParameter("coursePrice");
		String roomNo = req.getParameter("roomNo");
		String courseIntrod = req.getParameter("courseIntrod");
		String coursePhone = req.getParameter("coursePhone");
		String courseMail = req.getParameter("courseMail");
		String courseDiscount = req.getParameter("courseDiscount");

		// 設定寫入圖片參數
//		int count = 0;
		InputStream inStream;
		OutputStream outStream;
		String fileName = "";
		String allImg = "";
		String imgAddress = "C:/_JSP/eclipse-workspace/iCookTest/src/main/webapp/CourseImg/";
		File imgAddressMacker = new File(imgAddress);
		byte[] buf = new byte[1024];
		int data;

		// 將圖片寫入雲端(本機)
		if (imgAddressMacker.exists() == false) {
			imgAddressMacker.mkdirs();
		}
		if (courseImage != null) {
			try {
//				for (MultipartFile image : courseImage) {
//					System.out.println("Part name=" + image.getName());
//					++count;
				inStream = courseImage.getInputStream();
				fileName = courseName + ".jpg";
				outStream = new FileOutputStream(imgAddress + fileName);

				while ((data = inStream.read(buf)) != -1) {
					outStream.write(buf, 0, data);
				}
				inStream.close();
				outStream.close();
				allImg += imgAddress + fileName;

//				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		CourseBean courseBean = new CourseBean(null, null, courseName, courseCategory, allImg, hostName,
				courseStartDate, courseEndDate, roomNo, courseIntrod, coursePrice, saleStartDate, saleEndDate,
				coursePhone, courseMail, courseDiscount);
		
		service.insertCourse(courseBean);

		return "redirect:/course/courseList";
	}
	
	

	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("courseId", "hostId", "courseName", "courseCategory", "courseImage", "hostName",
				"courseStartDate", "courseEndDate", "roomNo", "courseIntrod", "coursePrice", "saleStartDate",
				"saleEndDate", "coursePhone", "courseMail");
	}

}
