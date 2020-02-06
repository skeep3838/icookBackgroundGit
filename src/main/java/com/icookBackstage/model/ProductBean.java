package com.icookBackstage.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/*
Products的主表單Bean
 */
@Entity
@Table(name="Products")
public class ProductBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productID;  //設定自動PK
	private String productName;
	private Boolean itemStatus;
	private String category;
	private String productInfo;
	private String image1;
	@Column(columnDefinition="smalldatetime")
	private Date updateTime;
	@OneToMany(mappedBy="products", cascade= {CascadeType.PERSIST}, orphanRemoval = false, fetch =FetchType.EAGER)
	//將資料以typeID欄位 ASC方式排列後再寫進Set內
	@OrderBy("typeID")
	private Set<ProductTypeBean> type;
	
	public ProductBean() {
		super();
	}
	
	public ProductBean(Integer productID, String productName, Boolean itemStatus, String category, String productInfo,
			String image1, Date updateTime, Set<ProductTypeBean> type) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.itemStatus = itemStatus;
		this.category = category;
		this.productInfo = productInfo;
		this.image1 = image1;
		this.updateTime = updateTime;
		this.type = type;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Set<ProductTypeBean> getType() {
		return type;
	}

	public void setType(Set<ProductTypeBean> tpye) {
		this.type = tpye;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Boolean getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(Boolean itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String decument) {
		this.productInfo = decument;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	//overriding顯示資料, 可以直接看資料內容(包含關聯類別)
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int count1 = 0;
		for(ProductTypeBean b : type) {
			count1++;
			sb.append(", [ productType("+ count1 +") [id=" + b.getId() + ", typeID=" + b.getTypeID() 
					+ ", typeTitle=" + b.getTypeTitle()
					+ ", unitPrice=" + b.getUnitPrice() + ", unitStock=" 
					+ b.getUnitStock() + ", unitOrder="+ b.getUnitOrder() +  " ]");
		}
		
		return "Product [productID=" + productID + ", productName=" + productName + 
				", itemStrtus=" + itemStatus + ", category=" + category + ", productInfo=" + productInfo + 
				", image1=" + image1 +", updateTime" + updateTime + sb + "]";
		
	}

}
