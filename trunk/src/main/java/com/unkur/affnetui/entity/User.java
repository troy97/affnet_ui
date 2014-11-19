package com.unkur.affnetui.entity;

import javax.persistence.*;

import com.unkur.affnetui.utils.Encrypter;

@Entity
@Table(name = "tbl_shop_users")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id = 0;
	
	@Column(name = "email")
	private String email = null;
	
	@Column(name = "password_ssha256_hex")
	private String encryptedPassword = null;
	
	@Column(name = "created_at")
	private long createdAt = 0;
	
	@Column(name = "name_first")
	private String firstName = null;
	
	@Column(name = "name_last")
	private String lastName = null;
	
	@Column(name = "is_active")
	private boolean isActive = false;
	
	@Column(name = "shop_id")
	private int shopId = 0;
	
	@Column(name = "language")
	private String language = "en"; 
	
	public User(){};
	
	/**
	 * Public constructor for new User registration purpose
	 * @param email
	 * @param plainPassword
	 * @param firstName
	 * @param lastName
	 * @param shopId
	 */
	public User(String email, String plainPassword, String firstName, String lastName, int shopId, String lang) {
		this.email = email;
		this.encryptedPassword = Encrypter.encrypt(plainPassword);
		this.firstName = firstName;
		this.lastName = lastName;
		this.shopId = shopId;
		this.language = lang;
		
		this.createdAt = System.currentTimeMillis();
	}


	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	
	/**
	 * Two users are only equal if they have the same email
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int dbId) {
		this.id = dbId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email != null && !email.isEmpty()) {
			this.email = email;
		}
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		if(encryptedPassword != null  && !encryptedPassword.isEmpty()) {
			this.encryptedPassword = encryptedPassword;
		}
	}
	
	public void setPassword(String plainPassword) {
		if(plainPassword != null && !plainPassword.isEmpty()) {
			this.encryptedPassword = Encrypter.encrypt(plainPassword);
		}
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName != null && !firstName.isEmpty()) {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName != null && !lastName.isEmpty()) {
			this.lastName = lastName;
		}
	}

	public int getShopId() {
		return shopId;
	}
	
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public boolean isActive() {
		return isActive;
	}
	
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Override
	public User clone() {
		User clone = new User();
		clone.setId(id);
		clone.setEmail(email);
		clone.setEncryptedPassword(encryptedPassword);
		clone.setCreatedAt(createdAt);
		clone.setFirstName(firstName);
		clone.setLastName(lastName);
		clone.setActive(isActive);
		clone.setShopId(shopId);
		clone.setLanguage(language);
		return clone;
	}

	
	
	
}
