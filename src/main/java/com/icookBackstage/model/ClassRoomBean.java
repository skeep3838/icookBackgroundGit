package com.icookBackstage.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="classRoom")
public class ClassRoomBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	String roomNo;
	String roomCapacity;
	
	public ClassRoomBean() {}
	public ClassRoomBean(String roomNo, String roomCapacity) {
		this.roomNo = roomNo;
		this.roomCapacity = roomCapacity;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	
//	@OneToMany(mappedBy="classRoomBean", cascade=CascadeType.ALL)
//	Set<CourseBean> items = new LinkedHashSet<>();

}
