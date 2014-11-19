package com.unkur.affnetui.entity;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.persistence.*;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.Table;



//@DynamicUpdate
@Entity
@Table(name = "tbl_shop_sources")
public class ShopSource {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id = 0;
	
	@Column(name = "shop_id")
	private int shop_id = 0;
	
	@Column(name = "file_format")
	private String file_format = null;
	
	@Column(name = "download_url")
	private String download_url = null;
	
	@Column(name = "basic_http_auth_required")
	private boolean basic_http_auth_required = false;
	
	@Column(name = "basic_http_auth_username")
	private String basic_http_auth_username = null;
	
	@Column(name = "basic_http_auth_password")
	private String basic_http_auth_password = null;
	
	@Column(name = "is_active")
	private boolean is_active = false;
	
	@Column(name = "last_queried_at")
	private long last_queried_at = 0;
	
	
	
	public ShopSource() {}


	public ShopSource(int shop_id, String file_format, String download_url,
			boolean basic_http_auth_required, String basic_http_auth_username,
			String basic_http_auth_password, boolean is_active, long last_queried_at) {
		this.shop_id = shop_id;
		this.file_format = file_format;
		this.download_url = download_url;
		this.basic_http_auth_required = basic_http_auth_required;
		this.basic_http_auth_username = basic_http_auth_username;
		this.basic_http_auth_password = basic_http_auth_password;
		this.is_active = is_active;
		this.last_queried_at = last_queried_at;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getShop_id() {
		return shop_id;
	}


	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}


	public String getFile_format() {
		return file_format;
	}


	public void setFile_format(String file_format) {
		this.file_format = file_format;
	}


	public String getDownload_url() {
		return download_url;
	}


	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}


	public boolean isBasic_http_auth_required() {
		return basic_http_auth_required;
	}


	public void setBasic_http_auth_required(boolean basic_http_auth_required) {
		this.basic_http_auth_required = basic_http_auth_required;
	}


	public String getBasic_http_auth_username() {
		return basic_http_auth_username;
	}


	public void setBasic_http_auth_username(String basic_http_auth_username) {
		this.basic_http_auth_username = basic_http_auth_username;
	}


	public String getBasic_http_auth_password() {
		return basic_http_auth_password;
	}


	public void setBasic_http_auth_password(String basic_http_auth_password) {
		this.basic_http_auth_password = basic_http_auth_password;
	}


	public boolean isIs_active() {
		return is_active;
	}


	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


	public long getLast_queried_at() {
		return last_queried_at;
	}


	public void setLast_queried_at(long last_queried_at) {
		this.last_queried_at = last_queried_at;
	}

}
