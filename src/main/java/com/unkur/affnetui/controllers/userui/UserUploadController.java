package com.unkur.affnetui.controllers.userui;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.unkur.affnetui.ValidationException;
import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.FileDao;
import com.unkur.affnetui.dao.exceptions.DaoException;
import com.unkur.affnetui.dao.impl.FileDaoImpl;
import com.unkur.affnetui.entity.Admin;
import com.unkur.affnetui.entity.UploadedFile;
import com.unkur.affnetui.entity.User;
import com.unkur.affnetui.file.MultipartDownloader;

/**
 * Servlet implementation class UserUploadController
 */
@WebServlet("/user/downloader")
public class UserUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(UserUploadController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("Start file download request handling.");
		
		//get session and user object (this request has passed Auth filter)
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute(Links.SESSION_USER_ATTR_NAME);
		
		//Get file uploaded by user
		UploadedFile uploadedFile = null;
		try {
			//InputStream is closed inside
			uploadedFile = new MultipartDownloader().download(request.getInputStream(), request.getHeader("Content-Type"), Config.UPLOAD_FOLDER);
		} catch (ValidationException | IOException e) {
			logger.debug("Download failure: " + e);
			response.sendRedirect(Urls.USER_UPLOAD_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		
		//put file into DB
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().begin();
		FileDao fileDao = new FileDaoImpl();
		try {
			int dbId = fileDao.insertOne(uploadedFile);
			uploadedFile.setId(dbId);
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			StatusEndpoint.incrementErrors();
			logger.error("File was downloaded, but service failed to add entry about it into DB, redirect to error page. " + 
			"filePath: " + uploadedFile.getFsPath() + " Exception: " + e.getClass().getName() + " Message: " + e.getMessage());
			response.sendRedirect(Urls.ERROR_PAGE_URL);;
			return;
		} 
		
		logger.debug("New file downloaded to " + uploadedFile.getFsPath());
		
		//OK, generate response html
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		
		request.setAttribute("cabinetPage", Urls.USER_CABINET_PAGE_URL);
		request.setAttribute("name", user.getEmail());

		
		request.setAttribute("fileName", uploadedFile.getName());
		request.setAttribute("uploadMoreLink", Urls.USER_UPLOAD_PAGE_URL);
		request.setAttribute("viewLastFiles", Urls.VIEW_LAST_FILES_PAGE_URL);

		response.sendRedirect(Urls.USER_UPLOAD_PAGE_URL);
		//request.getRequestDispatcher(Links.USER_DOWNLOAD_SUCCESS_JSP).forward(request, response);
		logger.debug("Response sent. Return.");
	}

}

