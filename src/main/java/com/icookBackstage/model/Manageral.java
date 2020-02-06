package com.icookBackstage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Manageral")
public class Manageral {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maId;
	@Column(unique=true)
	private String accrount;
	private String password;
	private Boolean productAuth;
	private Boolean orderAuth;
	private Boolean accrountAuth;
	private Boolean webMaintainAuth;
	
	public Manageral() {
	}
	
	public Manageral(String accrount,String password) {
		this.accrount = accrount;
		this.password = password;
	}
	
	public Manageral(Integer maId, String accrount, String password, Boolean productAuth, Boolean orderAuth,
			Boolean accrountAuth, Boolean webMaintainAuth) {
		super();
		this.maId = maId;
		this.accrount = accrount;
		this.password = password;
		this.productAuth = productAuth;
		this.orderAuth = orderAuth;
		this.accrountAuth = accrountAuth;
		this.webMaintainAuth = webMaintainAuth;
	}

	public Integer getMaId() {
		return maId;
	}
	public void setMaId(Integer maId) {
		this.maId = maId;
	}
	public String getAccrount() {
		return accrount;
	}
	public void setAccrount(String accround) {
		this.accrount = accround;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getProductAuth() {
		return productAuth;
	}
	public void setProductAuth(Boolean productAuth) {
		this.productAuth = productAuth;
	}
	public Boolean getOrderAuth() {
		return orderAuth;
	}
	public void setOrderAuth(Boolean orderAuth) {
		this.orderAuth = orderAuth;
	}
	public Boolean getAccrountAuth() {
		return accrountAuth;
	}
	public void setAccrountAuth(Boolean accroundAuth) {
		this.accrountAuth = accroundAuth;
	}
	public Boolean getWebMaintainAuth() {
		return webMaintainAuth;
	}
	public void setWebMaintainAuth(Boolean webMaintainAuth) {
		this.webMaintainAuth = webMaintainAuth;
	}
	
	//overriding顯示資料, 可以直接看資料內容
	@Override
	public String toString() {
		String Manage = "Manageral [MaId="+this.getMaId()+", Accrount="+ this.getAccrount()+", Password="+ this.getPassword()
											+", ProductAuth="+ this.getProductAuth()+", OrderAuth="+ this.getOrderAuth()
											+", AccrountAuth="+ this.getAccrountAuth()+", WebMaintainAuth="+ this.getWebMaintainAuth()
											+"]";
		return Manage;
	}
}
