package com.unkur.affnetui.controllers.userui;

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
import com.unkur.affnetui.entity.User;

/**
 * Servlet implementation class UploadPageController
 */
@WebServlet("/user/upload")
public class UserUploadPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(UserUploadPageController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUploadPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session and user object (this request has passed Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute(Links.SESSION_USER_ATTR_NAME);
		
		//check if it's the first attempt,
		//if not, put "wrong" notification to dataModel
		checkErrorParams(request);
		
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		request.setAttribute("downloadPage", Urls.USER_UPLOAD_CONTROLLER_URL);
		request.setAttribute("uploadPage", Urls.USER_UPLOAD_PAGE_URL);
		request.setAttribute("cabinetPage", Urls.USER_CABINET_PAGE_URL);
		request.setAttribute("name", user.getEmail());
		request.setAttribute("shopId", user.getShopId());
		
		//send response to outputStrem
		request.getRequestDispatcher(Links.USER_UPLOAD_PAGE_JSP).forward(request, response);
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
		if(request.getParameter(Links.INVALID_FILE_PARAM_NAME) != null) {
			request.setAttribute("badFileFormat", cfg.get("invalidFileMsg"));		
		} else if(request.getParameter(Links.ERROR_PARAM_NAME) != null) {
			request.setAttribute("badFileFormat", cfg.get("badFileFormat"));	
		} 
	}
	
}
