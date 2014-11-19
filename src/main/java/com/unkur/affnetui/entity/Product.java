package com.unkur.affnetui.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_products")
public class Product {
	
	//DB markers
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id = 0;
	
	@Column(name = "file_id")
	private int fileId = 0;
	
	@Column(name = "shop_id")
	private int shopId = 0;
	
	@Column(name = "is_active")
	private boolean active = false;
	
	@Column(name = "is_processing")
	private boolean processing = false;
	
	//mandatory (not NULL in DB)
	@Column(name = "real_url")
	private String realUrl = null;
	
	@Column(name = "name")
	private String name = null;
	
	@Column(name = "price")
	private double price = 0.0;
	
	@Column(name = "currency_code")
	private String currencyCode  = null;
	
	@Column(name = "category")
	private String category  = null;
	
	//optional
	@Column(name = "image_url")
	private String imageUrl  = null;
	
	@Column(name = "description")
	private String description  = null;
	
	@Column(name = "description_short")
	private String descriptionShort  = null;
	
	@Column(name = "ean")
	private String ean  = null;
	
	@Column(name = "shipping_price")
	private double shippingPrice = -1.0;

	
	public Product(){}
	
	/**
	 * Public constructor
	 */
	public Product(int fileId,
				   int shopId,
				   //mandatory
				   String realUrl,
				   String name,
				   double price,
				   String currencyCode,
				   String category,
					//optional
				   String imageUrl,
				   String description,
				   String descriptionShort,
				   String ean,
				   double shippingPrice) {
		
		this.fileId = fileId;
		this.shopId = shopId;
		
		//mandatory
		this.realUrl = realUrl;
		this.name = name;
		this.price = price;
		this.currencyCode = currencyCode;
		this.category = category;
		//optional
		this.imageUrl = imageUrl;
		this.description = description;
		this.descriptionShort = descriptionShort;
		this.ean = ean;
		this.shippingPrice = shippingPrice;
	}
	
	
	@Override
	public String toString() {
		return "\n Product [urlPath=" + realUrl + ", name=" + name + "]";
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((realUrl == null) ? 0 : realUrl.hashCode());
		result = prime * result + shopId;
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
		Product other = (Product) obj;
		if (realUrl == null) {
			if (other.realUrl != null)
				return false;
		} else if (!realUrl.equals(other.realUrl))
			return false;
		if (shopId != other.shopId)
			return false;
		return true;
	}
	
	
	
}
