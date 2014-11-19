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
 * Servlet implementation class SignInPageController
 */
@WebServlet("/signIn")
public class SignInPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(SignInPageController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//check if it's the first attempt to login,
		//if not, put "wrong" notification to dataModel
		if(request.getParameter(Links.ERROR_PARAM_NAME) != null) {
			request.setAttribute("wrongCredentials", AppConfig.getInstance().get("wrongCredentials"));
		}
		
		request.setAttribute("email", Links.EMAIL_PARAM_NAME);
		request.setAttribute("password", Links.PASSWORD_PARAM_NAME);
		request.setAttribute("checkSignIn", Urls.CHECK_SIGNIN_URL);
		request.setAttribute("signUpPage", Urls.SIGNUP_PAGE_URL);
		
		request.setAttribute("language", request.getParameter(Links.LANGUAGE_PARAM_NAME) == null ? "en" : request.getParameter(Links.LANGUAGE_PARAM_NAME));
		request.setAttribute("bundleBasename", Config.BUNDLE_BASENAME);
		
		request.getRequestDispatcher(Links.SIGNIN_PAGE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
