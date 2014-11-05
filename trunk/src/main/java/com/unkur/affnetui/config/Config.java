package com.unkur.affnetui.config;

public class Config {
	
	private static AppConfig cfg = AppConfig.getInstance();
	
	public static final int SERVER_PORT = Integer.valueOf(cfg.getWithEnv("port"));
	public static final String SERVER_HOSTNAME = cfg.getWithEnv("hostname");
	public static final int SERVER_BACKLOG = Integer.valueOf(cfg.getWithEnv("serverBacklog"));
	
	public static final String ENCODING = cfg.get("encoding");
	

}
