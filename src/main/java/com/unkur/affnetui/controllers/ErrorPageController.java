package com.unkur.affnetui.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;

/**
 * Servlet responsible for generating error page
 * 
 */
@WebServlet("/error")
public class ErrorPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ErrorPageController.class.getName());
	private static final String STATIC_HTML_ERROR_PAGE = "<html>"
														+ "<body>"
														+ "<h3>Something really bad happened on our server:(</h3>"
														+ "Press your browsers back button, and try again</br>"
														+ "</body>"
														+ "</html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String link = request.getHeader("Referer");
		if(link == null || link.equals("")) {
			link = Urls.MAIN_PAGE_URL;
		}
		request.setAttribute("someLink", link);
		try {
			request.getRequestDispatcher(Links.ERROR_PAGE_JSP).forward(request, response);
		} catch (Exception e) {
			logger.error("Unable render error.jsp " + e);
			PrintWriter out = response.getWriter();
			out.write(STATIC_HTML_ERROR_PAGE);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

}
