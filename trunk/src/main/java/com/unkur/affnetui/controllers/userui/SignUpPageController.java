package com.unkur.affnetui.controllers.userui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;

/**
 * Servlet implementation class SignUpPageController
 */
@WebServlet("/signUp")
public class SignUpPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(SignUpPageController.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkErrorParams(request);
		
		request.setAttribute("uploadPage", Urls.USER_UPLOAD_PAGE_URL);
		request.setAttribute("signInPage", Urls.SIGNIN_PAGE_URL);
		request.setAttribute("checkSignUp", Urls.CHECK_SIGNUP_URL);
		request.setAttribute("email", Links.EMAIL_PARAM_NAME);
		request.setAttribute("password", Links.PASSWORD_PARAM_NAME);
		request.setAttribute("firstName", Links.FIRST_NAME_PARAM_NAME);
		request.setAttribute("lastName", Links.LAST_NAME_PARAM_NAME);
		request.setAttribute("shopName", Links.SHOP_NAME_PARAM_NAME);
		request.setAttribute("shopUrl", Links.SHOP_URL_PARAM_NAME);
		
		request.setAttribute("language", request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME));
		request.setAttribute("bundleBasename", Config.BUNDLE_BASENAME);
		
		request.getRequestDispatcher(Links.SIGNUP_PAGE_JSP).forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Verify if some messages for User are to be added to page
	 * @param ftlData
	 * @param queryStr
	 */
	private void checkErrorParams(HttpServletRequest request) {
		AppConfig cfg = AppConfig.getInstance();
		if(request.getParameter(Links.DUPLICATE_SHOP_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("duplicateShopMsg"));		
		} else if(request.getParameter(Links.DUPLICATE_USER_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("duplicateUserMsg"));	
		} else if(request.getParameter(Links.ERROR_PARAM_NAME) != null) {
			request.setAttribute("wrongData", cfg.get("wrongSignUpInfo"));	
		} 
	}

}

