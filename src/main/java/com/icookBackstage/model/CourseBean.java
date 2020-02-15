package com.icookBackstage.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
@Table(name="course")
public class CourseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//請依資料表欄位名稱取名
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer courseId;
		private Integer hostId;
		@Expose
		@SerializedName("title")
		private String courseName;
		private String courseCategory;
		private Blob courseImage; //資料類型待確認
		private String hostName;
		@Expose
		@SerializedName("start")
		private Date courseStartDate;
//		@Expose
//		2/15更新Table-已移除
//		@SerializedName("end")
//		private String courseEndDate;
		private String courseIntrod;
		private Integer coursePrice;
		private String coursePhone;
		private String courseMail;
		private Double courseDiscount;
		private Date updateTime;
		@Expose
		@SerializedName("className")
		private String roomNo;
//		新增時間和時數欄位
		@Expose
		@SerializedName("cTime")
		private String courseTime;
		@Expose
		@SerializedName("cHour")
		private Integer courseHour;
		

		
		public CourseBean() {}
		
		public CourseBean( String courseName, Date courseStartDate, 
				String courseEndDate) {
			this.courseName = courseName;
			this.courseStartDate = courseStartDate;
//			this.courseEndDate = courseEndDate;
		}
		
		public CourseBean(
			Integer courseId,
			Integer hostId,
			String courseName,
			String courseCategory,
			Blob courseImage, //資料類型待確認
			String hostName,
			Date courseStartDate,
			String roomNo,
			String courseIntrod,
			Integer coursePrice,
			String coursePhone,
			String courseMail,
			Double courseDiscount,
			Date updateTime,
			String courseTime,
			Integer courseHour) {
				this.courseId = courseId;
				this.hostId = hostId;
				this.courseName = courseName;
				this.courseCategory =courseCategory;
				this.courseImage = courseImage;
				this.hostName = hostName;
				this.courseStartDate = courseStartDate;
				this.roomNo = roomNo;
				this.courseIntrod = courseIntrod;
				this.coursePrice = coursePrice;
				this.coursePhone = coursePhone;
				this.courseMail = courseMail;
				this.courseDiscount = courseDiscount;
				this.updateTime = updateTime;
				this.courseTime = courseTime;
				this.courseHour = 	courseHour;
		}
		
		
		

		public String getCourseTime() {
			return courseTime;
		}

		public void setCourseTime(String courseTime) {
			this.courseTime = courseTime;
		}

		public Integer getCourseHour() {
			return courseHour;
		}

		public void setCourseHour(Integer courseHour) {
			this.courseHour = courseHour;
		}

		public Integer getCourseId() {
			return courseId;
		}

		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}
		public Integer getHostId() {
			return hostId;
		}
		public void setHostId(Integer hostId) {
			this.hostId = hostId;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getCourseCategory() {
			return courseCategory;
		}
		public void setCourseCategory(String courseCategory) {
			this.courseCategory = courseCategory;
		}
		public Blob getCourseImage() {
			return courseImage;
		}
		public void setCourseImage(Blob courseImage) {
			this.courseImage = courseImage;
		}
		public String getHostName() {
			return hostName;
		}
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		public Date getCourseStartDate() {
			return courseStartDate;
		}
		public void setCourseStartDate(Date courseStartDate) {
			this.courseStartDate = courseStartDate;
		}
//		public String getCourseEndDate() {
//			return courseEndDate;
//		}
//		public void setCourseEndDate(String courseEndDate) {
//			this.courseEndDate = courseEndDate;
//		}
		public String getRoomNo() {
			return roomNo;
		}
		public void setRoomNo(String roomNo) {
			this.roomNo = roomNo;
		}
		public String getCourseIntrod() {
			return courseIntrod;
		}
		public void setCourseIntrod(String courseIntrod) {
			this.courseIntrod = courseIntrod;
		}
		public Integer getCoursePrice() {
			return coursePrice;
		}
		public void setCoursePrice(Integer coursePrice) {
			this.coursePrice = coursePrice;
		}
		
		public String getCoursePhone() {
			return coursePhone;
		}
		public void setCoursePhone(String coursePhone) {
			this.coursePhone = coursePhone;
		}
		public String getCourseMail() {
			return courseMail;
		}
		public void setCourseMail(String courseMail) {
			this.courseMail = courseMail;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Double getCourseDiscount() {
			return courseDiscount;
		}

		public void setCourseDiscount(Double courseDiscount) {
			this.courseDiscount = courseDiscount;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		
		
		
}
