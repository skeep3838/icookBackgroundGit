package com.icookBackstage.course.dao;

import java.util.List;

import com.icookBackstage.model.ClassRoomBean;
import com.icookBackstage.model.CourseBean;


public interface CourseDao {
	List<CourseBean> queryAllCourse();
	List<CourseBean> queryCourse(String courseName);
	public CourseBean getCourseById(int courseId);
	public Integer courseCapacity(int courseId);
	List<Integer> courseOrderQty(int courseId);
	public void insertCourse(CourseBean bean);
	List<ClassRoomBean> roomList();
	List<CourseBean> queryClassRoom(String roomNo);
	
}
 