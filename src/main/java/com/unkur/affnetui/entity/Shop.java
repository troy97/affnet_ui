package com.unkur.affnetui.entity;

public class Shop {
	

	private int id = 0;
	private String name = null;
	private String url = null;
	private String priceListUrl = "empty";
	
	/**
	 * Public constructor
	 * @param name
	 * @param url
	 */
	public Shop(String name, String url, String priceListUrl) {
		this.name = name;
		this.url = url;
		this.priceListUrl = priceListUrl;
	}
	
	/**
	 * Constructor only used by DAO
	 * @param dbId assigned by DBMS
	 * @param name
	 */
	public Shop(int dbId, String name, String url, String priceListUrl) {
		this(name, url, priceListUrl);
		this.id = dbId;
	}

	public int getId() {
		return id;
	}

	public void setId(int dbId) {
		this.id = dbId;
	}

	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		if(name != null && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void setUrl(String url) {
		if(url != null && !url.isEmpty()) {
			this.url = url;
		}
	}
	

	public String getPriceListUrl() {
		return priceListUrl;
	}

	public void setPriceListUrl(String priceListUrl) {
		this.priceListUrl = priceListUrl;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	

}
