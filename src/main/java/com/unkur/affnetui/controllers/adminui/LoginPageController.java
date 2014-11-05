package com.unkur.affnetui.controllers.adminui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;

/**
 * Servlet implementation class LoginPageController
 */
@WebServlet("/login")
public class LoginPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginPageController.class.getName());   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPageController() {
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
		
		request.setAttribute("checkLogin", Urls.CHECK_LOGIN_URL);
		
		//render login page
		request.getRequestDispatcher(Links.LOGIN_PAGE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
