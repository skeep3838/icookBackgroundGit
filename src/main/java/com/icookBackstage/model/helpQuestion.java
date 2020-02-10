package com.icookBackstage.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "help")
public class helpQuestion implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer helpQAId;
	private Integer userID;
	private String email;
	private String title;
	private String contentText;
	private String responseStatus;
	
	public helpQuestion(){};
	public helpQuestion(Integer helpQAId, Integer userID, String email, String title, String contentText,
			String responseStatus) {
		super();
		this.helpQAId = helpQAId;
		this.userID = userID;
		this.email = email;
		this.title = title;
		this.contentText = contentText;
		this.responseStatus = responseStatus;
	}
	public Integer getHelpQAId() {
		return helpQAId;
	}
	public void setHelpQAId(Integer helpQAId) {
		this.helpQAId = helpQAId;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
}
