package com.icookBackstage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="ProductType")
public class ProductTypeBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//Expose(serialize = false):禁止ProductBean在toJSON時進行序列化, 不然會進入無窮迴圈拋出(StackOverflowError) 
	@Expose(serialize = false)
	@ManyToOne
	@JoinColumn(name="productID", nullable=false)
	private ProductBean products;
	private Integer typeID;
	private String typeTitle;
	private Integer unitPrice;
	private Integer unitStock;
	private Integer unitOrder;
	@Column(columnDefinition="NUMERIC(3,2)")
	private Float discount;
	
	public ProductTypeBean() {
		super();
	}
	
	public ProductTypeBean(Integer id, Integer typeID, String typeTitle, Integer unitPrice, Integer unitStock, Integer unitOrder) {
		super();
		this.id = id;
		this.typeID = typeID;
		this.typeTitle = typeTitle;
		this.unitPrice = unitPrice;
		this.unitStock = unitStock;
		this.unitOrder = unitOrder;
	}
	
	public ProductTypeBean(Integer id, Integer typeID, String typeTitle, Integer unitPrice,
			Integer unitStock, Integer unitOrder, ProductBean products) {
		super();
		this.id = id;
		this.typeID = typeID;
		this.typeTitle = typeTitle;
		this.unitPrice = unitPrice;
		this.unitStock = unitStock;
		this.unitOrder = unitOrder;
		this.products = products;
	}

	public ProductTypeBean(Integer id, Integer typeID, String typeTitle, Integer unitPrice,
			Integer unitStock, Integer unitOrder, Float discount) {
		super();
		this.id = id;
		this.typeID = typeID;
		this.typeTitle = typeTitle;
		this.unitPrice = unitPrice;
		this.unitStock = unitStock;
		this.unitOrder = unitOrder;
		this.discount = discount;
	}

	public ProductBean getProducts() {
		return products;
	}

	public void setProducts(ProductBean products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public int getProductID() {
//		return productID;
//	}
//
//	public void setProductID(int productID) {
//		this.productID = productID;
//	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public String getTypeTitle() {
		return typeTitle;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getUnitStock() {
		return unitStock;
	}

	public void setUnitStock(Integer unitStock) {
		this.unitStock = unitStock;
	}

	public Integer getUnitOrder() {
		return unitOrder;
	}

	public void setUnitOrder(Integer unitOrder) {
		this.unitOrder = unitOrder;
	}
	
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String toString() {
		return "Employee [id=" + id + ", typeID=" + typeID + ", typeTitle=" + typeTitle
				+ ", unitPrice=" + unitPrice + ", unitStock=" + unitStock + ", unitOrder="
				+ unitOrder + "]";
	}

}
