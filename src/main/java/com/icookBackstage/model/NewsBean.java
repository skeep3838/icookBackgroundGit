package com.icookBackstage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="news")
public class NewsBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsId;
	private String title;
	private String newsContent;
	@Column(columnDefinition="smalldatetime")
	private Date updateTime;
	@Column(columnDefinition="smalldatetime")
	private Date createTime;
	
	public NewsBean() {
		super();
	}
	
	public NewsBean(Integer newsId, String title, String newsContent, Date updateTime, Date createTime) {
		super();
		this.newsId = newsId;
		this.title = title;
		this.newsContent = newsContent;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + 
				", newsContent=" + newsContent + ", updateTime=" + updateTime + 
				", createTime=" + createTime + "]";
	}
	

}
