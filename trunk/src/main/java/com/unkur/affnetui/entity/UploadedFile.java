package com.unkur.affnetui.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.*;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;

@Entity
@Table(name = "tbl_files")
public class UploadedFile {
	
	private static AppConfig cfg = AppConfig.getInstance();
	private static Logger log = Logger.getLogger(UploadedFile.class.getName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id = 0;
	
	@Transient
	private String extension = null;
	
	@Column(name = "name") 
	private String name = null;
	
	@Column(name = "fs_path")
	private String fsPath = null;
	
	@Column(name = "upload_time")
	private long uploadTime = 0; //in milliseconds since EPOCH
	
	@Column(name = "shop_id")
	private int shopId = 0;
	
	@Column(name = "file_size")
	private long size = 0;
	
	@Column(name = "is_active")
	private boolean active = false;
	
	@Column(name = "is_valid")
	private boolean valid = false;
	
	@Column(name = "products_count")
	private int productsCount = -1;
	
	@Column(name = "validation_message")
	private String validationMessage = "Not processed";
	
	@Column(name = "is_processed")
	private boolean processed = false;
	

	public UploadedFile() {}
	
/*	*//**
	 * This constructor is only called by DAO
	 * @param dbId
	 * @param name
	 * @param fsPath
	 * @param uploadTime
	 * @param webshopId
	 *//*
	public UploadedFile(int dbId, String name, String fsPath, long uploadTime, int shopId,
			boolean isActive, boolean isValid, int productsCount, long fileSize, String validationMessage, boolean processed) {
		this.id = dbId;
		this.name = name;
		this.fsPath = fsPath;
		this.uploadTime = uploadTime;
		this.extension = name.substring(name.length()-".zip".length());
		this.shopId = shopId;
		this.size = fileSize;
		this.active = isActive;
		this.valid = isValid;
		this.productsCount = productsCount;
		this.validationMessage = validationMessage;
		this.processed = processed;
	}*/
	

	/**
	 * Public constructor
	 * @param tmpFilePath - path to file which was downloaded and stored on disk
	 * @param uploadTime (ms since EPOCH)
	 * @param extension ".zip" for example
	 * @param shopId
	 */
	public UploadedFile(String tmpFilePath, String extension, long uploadTime, int shopId) {
		String uploadFolder = cfg.getWithEnv("uploadPath");
		//create directory if it does not exist yet
		new File(uploadFolder).mkdir();
		//rename temporary file to format: [shopId]_[uploadTimeMillis].[extension]
		String correctName = "" + shopId + "_" + uploadTime + extension;
		String correctPath = uploadFolder + "/" + correctName;
		
		File tmpFile = new File(tmpFilePath);
		boolean isRenamed = tmpFile.renameTo(new File(correctPath));
		int suffix = 1;
		while(!isRenamed) {
			log.debug("File \"" + correctName + "\" already exists, adding suffix...");
			//change file name format to: [shopId]_[uploadTimeMillis]_[suffix].[extension]
			correctName = "" + shopId + "_" + uploadTime + "_" + suffix + extension;
			correctPath = uploadFolder + "/" + correctName;
			isRenamed = tmpFile.renameTo(new File(correctPath));
			suffix++;
		}
		
		this.fsPath = correctPath;
		this.name = correctName;
		this.extension = extension;
		this.uploadTime = uploadTime;
		this.shopId = shopId;
		this.size = new File(fsPath).length();
		
	}
	
	@Override
	public String toString() {
		return "UploadedFile [name=" + name + ", uploadTime=" + new Date(uploadTime)
				+ ", webShopId=" + shopId + ", validationMessage=" + validationMessage + "]";
	}

	public String getName() {
		return name;
	}

	public String getFsPath() {
		return fsPath;
	}

	public long getUploadTime() {
		return uploadTime;
	}

	@Transient
	public String getExtension() {
		return extension;
	}

	public int getShopId() {
		return shopId;
	}

	public void setId(int dbId) {
		this.id = dbId;
	}
	
	public int getId() {
		return id;
	}
	
	public long getSize() {
		return size;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean isValid() {
		return valid;
	}

	public int getProductsCount() {
		return productsCount;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public void setProductsCount(int productsCount) {
		this.productsCount = productsCount;
	}


	public String getValidationMessage() {
		return validationMessage;
	}


	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}


	public boolean isProcessed() {
		return processed;
	}


	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	

}
