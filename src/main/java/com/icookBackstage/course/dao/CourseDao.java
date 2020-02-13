package com.icookBackstage.course.dao;

import java.util.List;

import com.icookBackstage.model.ClassRoomBean;
import com.icookBackstage.model.CourseBean;


public interface CourseDao {
//	查詢功能
	List<CourseBean> queryAllCourse();
	List<CourseBean> queryCourse(String courseName);
	public CourseBean getCourseById(int courseId);
	
//	取得課程使勇的教室容量
	public Integer courseCapacity(int courseId);
	List<Integer> courseOrderQty(int courseId);
	
//	新增課程
	public void insertCourse(CourseBean bean);
//	教室清單
//	查詢教室被那些課程使用
	List<ClassRoomBean> roomList();
	List<CourseBean> queryClassRoom(String roomNo);
	List<Integer> querycourseOrser();
	
//	刪除及更新
	public void deleteCourse(CourseBean bean);
	Boolean updateCourse(CourseBean bean);
	
	
	
}
 