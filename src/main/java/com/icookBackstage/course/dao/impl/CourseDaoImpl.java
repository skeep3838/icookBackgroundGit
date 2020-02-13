package com.icookBackstage.course.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.course.dao.CourseDao;
import com.icookBackstage.model.ClassRoomBean;
import com.icookBackstage.model.CourseBean;

@Repository
public class CourseDaoImpl implements CourseDao{
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

//	列出所有課程清單
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseBean> queryAllCourse() {
		System.out.println("進入DAO AllCourse");
		
		List<CourseBean> courses = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM CourseBean order by courseId desc";
		courses = session.createQuery(hql).getResultList();
		return courses;
	}
	
//	關鍵字查詢課程
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseBean> queryCourse(String courseName) {
		System.out.println("進入DAO courseName :" + courseName);
		
		List<CourseBean> courses = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM CourseBean c WHERE c.courseName like :name";
		courses = session.createQuery(hql).setParameter("name", "%"+courseName+"%").getResultList();
		
		return courses;
	}

//	課程詳細資訊
	@Override
	public CourseBean getCourseById(int courseId) {
		Session session = factory.getCurrentSession();
		CourseBean cb = session.get(CourseBean.class, courseId);
		return cb;
	}
	
//	課程 => 確認教室 => 確認教室容納人數
	@Override
	public Integer courseCapacity(int courseId) {
		Session session = factory.getCurrentSession();
//		找出該課程所使用的教室
		String hql1 = "Select roomNo FROM CourseBean cb WHERE cb.courseId = :id";
//		找出該教室的容納人數
		String hql2 = "Select roomCapacity FROM ClassRoomBean cr WHERE cr.roomNo = :no";
		String no = (String)session.createQuery(hql1).setParameter("id", courseId).getSingleResult();
//		System.out.println(session.createQuery(hql1).setParameter("id", courseId).getSingleResult().getClass());
		Integer courseCapacity = Integer.valueOf((String) session.createQuery(hql2).setParameter("no", no).getSingleResult());
		return courseCapacity;
	}
	
//	課程 => 確認訂購人數清單
//	找出這堂課的所有訂單
//	 and orderStatus=1 確認訂單狀況先拿掉
	@Override
	public List<Integer> courseOrderQty(int courseId) {
		Session session = factory.getCurrentSession();
		String hql = "Select orderQty FROM CourseOrderBean co WHERE co.courseId=:id";
		@SuppressWarnings("unchecked")
		List<Integer> orderQty = (List<Integer>)session.createQuery(hql).setParameter("id", courseId).getResultList();
		return orderQty;
	}

	@Override
	public void insertCourse(CourseBean bean) {
		Session session = factory.getCurrentSession();
		session.persist(bean);
		
	}

	@Override
	public List<ClassRoomBean> roomList() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ClassRoomBean";
		@SuppressWarnings("unchecked")
		List<ClassRoomBean> roomBean = (List<ClassRoomBean>)session.createQuery(hql).getResultList();
		return roomBean;
	}


//	找出教室使用資訊
	@Override
	public List<CourseBean> queryClassRoom(String roomNo) {
		Session session = factory.getCurrentSession();
//		String hql = "Select courseName, courseStartDate, courseEndDate "
//				+ "From CourseBean cb where cb.roomNo =:no";
		String hql = "From CourseBean cb where cb.roomNo =:no";
		@SuppressWarnings("unchecked")
		List<CourseBean> roomInfo = (List<CourseBean>)session.createQuery(hql).setParameter("no", roomNo).getResultList();
		System.out.println("queryClassRoom: " + roomInfo);
		return roomInfo;
	}
	
	@Override
	public void deleteCourse(CourseBean bean) {
		Session session = factory.getCurrentSession();
		session.delete(bean);
		
	}
	
	@Override
	public Boolean updateCourse(CourseBean bean) {
		Session session = factory.getCurrentSession();
		try {
			session.update(bean);
		}catch (Exception e) {
			e.getStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public List<Integer> querycourseOrser() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT courseId FROM CourseOrderBean;";
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>)session.createQuery(hql).getResultList();
		return list;
	}


}
