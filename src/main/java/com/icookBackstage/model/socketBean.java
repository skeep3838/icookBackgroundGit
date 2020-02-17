package com.icookBackstage.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "socket")
public class socketBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	private Integer category;
	private Integer id;
	private String message;
	private String updateTime;
	
	public socketBean() {}
	
	public socketBean(Integer seq, Integer category, Integer id, String message, String updateTime) {
		super();
		this.seq = seq;
		this.category = category;
		this.id = id;
		this.message = message;
		this.updateTime = updateTime;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
