package com.unkur.affnetui.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;

/**
 * Servlet implementation class LogoutPageController
 */
@WebServlet("/logout")
public class LogoutPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(LogoutPageController.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = (HttpSession) request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
		//create html
		request.setAttribute("goodByeMessage", AppConfig.getInstance().get("goodByeMessage"));
		request.setAttribute("mainPage", Urls.MAIN_PAGE_URL);
		
		response.sendRedirect(Urls.SIGNIN_PAGE_URL);
		//request.getRequestDispatcher(Links.LOGOUT_PAGE_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
