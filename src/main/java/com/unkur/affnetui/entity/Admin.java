package com.unkur.affnetui.entity;

import javax.persistence.*;

import org.apache.log4j.Logger;

import com.unkur.affnetui.utils.Encrypter;

/**
 * Class represents a web-shop user who may upload 
 * *.csv files to our server
 * Password is stored in encrypted form.
 * @author Anton Lukashchuk
 *
 */
@Entity
@Table(name = "tbl_admins")
public class Admin {
	
	private static Logger log = Logger.getLogger(Admin.class.getName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id = 0;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password_ssha256_hex")
	private String encryptedPassword;
	
	
	public Admin(){}
	
	/**
	 * Constructs new Admin with given parameters. 
	 * @param name
	 * @param email
	 * @param encryptedPassword
	 */
	public Admin(String name, String email, String plainPassword) {
		this.name = name;
		this.email = email;
		this.encryptedPassword = Encrypter.encrypt(plainPassword);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setPassword(String plainPassword) {
		this.encryptedPassword = Encrypter.encrypt(plainPassword);
	}

	public int getDbId() {
		return id;
	}

	/**
	 * Set dbId returned by DBMS
	 * @param dbId
	 */
	public void setDbId(int dbId) {
		this.id = dbId;
	}

	/**
	 * Two admin are only equal if they have the same email
	 * everything else doesn't matter
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return this.email.hashCode();
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + "]";
	}



}
