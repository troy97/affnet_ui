package com.unkur.affnetui.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_clicks")
public class Click {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id = 0;
	
	@Column(name = "product_id")
	private long productId = 0;
	
	@Column(name = "shop_id")
	private int shopId = 0;
	
	@Column(name = "distributor_id")
	private int distributorId = 0;
	
	@Column(name = "sub_id")
	private int subId=-1;
	
	@Column(name = "product_name")
	private String productName = null;
	
	@Column(name = "product_price")
	private double productPrice = -1.0;
	
	@Column(name = "shipping_price")
	private double shippingPrice = -1.0;
	
	@Column(name = "click_time")
	private long clickTime = 0;
	
	public Click(){}
	
	/**
	 * Public constructor
	 * @param productId
	 * @param shopId
	 * @param distributorId
	 */
	public Click(long productId,
			int shopId,
			int distributorId,
			int subId,
			String prodName,
			double prodPrice,
			double shipPrice) {
		this.productId = productId;
		this.productName = prodName;
		this.productPrice = prodPrice;
		this.shippingPrice = shipPrice;
		this.shopId = shopId;
		this.distributorId = distributorId;
		this.subId = subId;
		this.clickTime = System.currentTimeMillis();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public long getClickTime() {
		return clickTime;
	}

	public void setClickTime(long clickTime) {
		this.clickTime = clickTime;
	}

	@Override
	public String toString() {
		return "Click [subId=" + subId + ", productName=" + productName
				+ ", productPrice=" + productPrice + ", shippingPrice="
				+ shippingPrice + ", clickTime=" + clickTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (clickTime ^ (clickTime >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Click other = (Click) obj;
		if (clickTime != other.clickTime)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	

	
	
	
}
