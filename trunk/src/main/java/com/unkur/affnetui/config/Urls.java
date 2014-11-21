package com.unkur.affnetui.config;

import javax.servlet.annotation.WebServlet;

import com.unkur.affnetui.controllers.DashboardController;
import com.unkur.affnetui.controllers.ErrorPageController;
import com.unkur.affnetui.controllers.MainPageController;
import com.unkur.affnetui.controllers.StatusPageController;
import com.unkur.affnetui.controllers.UiFileRequestController;
import com.unkur.affnetui.controllers.adminui.AdminUploadController;
import com.unkur.affnetui.controllers.adminui.AdminUploadPageController;
import com.unkur.affnetui.controllers.adminui.CheckLoginController;
import com.unkur.affnetui.controllers.adminui.LoginPageController;
import com.unkur.affnetui.controllers.userui.CheckSignInController;
import com.unkur.affnetui.controllers.userui.CheckSignUpController;
import com.unkur.affnetui.controllers.userui.CheckUpdateProfileController;
import com.unkur.affnetui.controllers.userui.SignInPageController;
import com.unkur.affnetui.controllers.userui.SignUpPageController;
import com.unkur.affnetui.controllers.userui.UpdateUserProfileController;
import com.unkur.affnetui.controllers.userui.UserCabinetPageController;
import com.unkur.affnetui.controllers.userui.UserUploadController;
import com.unkur.affnetui.controllers.userui.UserUploadPageController;
import com.unkur.affnetui.controllers.userui.ViewLastFilesPageController;
import com.unkur.affnetui.controllers.LogoutPageController;


public class Urls {

	private static AppConfig cfg = AppConfig.getInstance();
	
	//service root name
	//public static final String SERVICE_NAME = "/affnetui";
	public static final String SERVICE_NAME = "";
	public static final String USER_AREA_NAME = "user/";
	public static final String ADMIN_AREA_NAME = "admin/";
	
	//url-mapping
	public static final String STATUS_PAGE_URL = 				SERVICE_NAME + StatusPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String UI_FILE_REQUEST_CONTROLLER_URL = SERVICE_NAME + UiFileRequestController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String CHECK_LOGIN_URL =				SERVICE_NAME + CheckLoginController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String ERROR_PAGE_URL = 				SERVICE_NAME + ErrorPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String LOGIN_PAGE_URL = 				SERVICE_NAME + LoginPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String ADMIN_UPLOAD_PAGE_URL = 			SERVICE_NAME + AdminUploadPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String LOGOUT_PAGE_URL = 				SERVICE_NAME + LogoutPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String ADMIN_UPLOAD_CONTROLLER_URL =    SERVICE_NAME + AdminUploadController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String USER_UPLOAD_CONTROLLER_URL =  	SERVICE_NAME + UserUploadController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String CHECK_SIGNIN_URL = 				SERVICE_NAME + CheckSignInController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String SIGNUP_PAGE_URL =				SERVICE_NAME + SignUpPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String CHECK_SIGNUP_URL = 				SERVICE_NAME + CheckSignUpController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String SIGNIN_PAGE_URL = 				SERVICE_NAME + SignInPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String USER_UPLOAD_PAGE_URL = 			SERVICE_NAME + UserUploadPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String CHECK_UPDATE_PROFILE_URL =		SERVICE_NAME + CheckUpdateProfileController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String UPDATE_USER_PROFILE_PAGE_URL =   SERVICE_NAME + UpdateUserProfileController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String VIEW_LAST_FILES_PAGE_URL = 		SERVICE_NAME + ViewLastFilesPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String USER_DASHBOARD_PAGE_URL = 		SERVICE_NAME + DashboardController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String USER_CABINET_PAGE_URL = USER_DASHBOARD_PAGE_URL;			//SERVICE_NAME + UserCabinetPageController.class.getAnnotation(WebServlet.class).value()[0];
	
	
	public static final String MAIN_PAGE_URL = 	SIGNIN_PAGE_URL;	//SERVICE_NAME + MainPageController.class.getAnnotation(WebServlet.class).value()[0];
	public static final String ADMIN_UI_MAIN_PAGE_URL = ADMIN_UPLOAD_PAGE_URL;
	public static final String USER_UI_MAIN_PAGE_URL = USER_DASHBOARD_PAGE_URL;
	
	
	
	/**
	 * Adds DOMAIN_NAME in front of given string
	 * @param url
	 * @return DOMAIN_NAME + relative
	 */
/*	public static String fullURL(String url) {
		return DOMAIN_NAME + url;
	}*/
	
}
