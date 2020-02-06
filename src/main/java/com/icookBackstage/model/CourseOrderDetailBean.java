package com.icookBackstage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courseOrderDetail")
public class CourseOrderDetailBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId")
	Integer orderId;
	@Column(name = "courseId")
	Integer courseId;
	Integer orderQty;
	String courseName;
	Integer coursePrice;
	String courseIntrod;
	Double courseDiscount;
	
	public CourseOrderDetailBean(
			Integer orderId,
			Integer courseId,
			Integer orderQty,
			String courseName,
			Integer coursePrice,
			String courseIntrod,
			Double courseDiscount) {
		this.orderId = orderId;
		this.courseId = courseId;
		this.orderQty = orderQty;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		this.courseIntrod = courseIntrod;
		this.courseDiscount = courseDiscount;
	}
	
	public CourseOrderDetailBean() {
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCourseIntrod() {
		return courseIntrod;
	}

	public void setCourseIntrod(String courseIntrod) {
		this.courseIntrod = courseIntrod;
	}

	public Double getCourseDiscount() {
		return courseDiscount;
	}

	public void setCourseDiscount(Double courseDiscount) {
		this.courseDiscount = courseDiscount;
	}
	
	
	
}



