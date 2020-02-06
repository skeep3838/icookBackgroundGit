package com.icookBackstage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courseOrder")
public class CourseOrderBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seqno;
	@Column(name="orderId")
	private Integer orderId;
	@Column(name = "courseId")
	private Integer courseId;
	private Integer orderQty;
	
	public CourseOrderBean() {}
	
	public CourseOrderBean(Integer seqno,Integer orderId,Integer courseId,Integer orderQty) 
	{
		this.orderId = orderId;
		this.courseId = courseId;
		this.orderQty = orderQty;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
