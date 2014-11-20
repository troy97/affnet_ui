package com.unkur.affnetui.config;

public class Config {
	
	private static AppConfig cfg = AppConfig.getInstance();
	
	public static final int SERVER_PORT = Integer.valueOf(cfg.getWithEnv("port"));
	public static final String SERVER_HOSTNAME = cfg.getWithEnv("hostname");
	public static final int SERVER_BACKLOG = Integer.valueOf(cfg.getWithEnv("serverBacklog"));
	
	public static final String DB_URL = cfg.getWithEnv("dbURL");
	public static final String DB_USER = cfg.getWithEnv("dbUser");
	public static final String DB_PASSWORD = cfg.getWithEnv("dbPassword");
	public static final String DB_DRIVER_CLASS = cfg.getWithEnv("jdbcDriver");
	public static final String DB_HIBERNATE_DIALECT = cfg.getWithEnv("hibernateDialect");
	
	public static final String UI_FILES_PATH = cfg.getWithEnv("UiFilesPath");
	
	public static final String ENCODING = cfg.get("encoding");
	
	public static final String BUNDLE_BASENAME = "com.example.i18n.text";

	public static final String UPLOAD_FOLDER = cfg.getWithEnv("uploadPath");
	//public static final String WEB_CONTENT_FOLDER = cfg.getWithEnv("WebContentPath");
	public static final String LOGGER_CONFIG_PATH = cfg.getWithEnv("logginConfigPath");
	
	
	public String getBUNDLE_BASENAME() {
		return BUNDLE_BASENAME;
	}

}
