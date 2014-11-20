package com.unkur.affnetui.controllers.adminui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.unkur.affnetui.ValidationException;
import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.config.HibernateUtil;
import com.unkur.affnetui.config.Links;
import com.unkur.affnetui.config.Urls;
import com.unkur.affnetui.controllers.StatusEndpoint;
import com.unkur.affnetui.dao.FileDao;
import com.unkur.affnetui.dao.impl.FileDaoImpl;
import com.unkur.affnetui.entity.Admin;
import com.unkur.affnetui.entity.UploadedFile;
import com.unkur.affnetui.file.MultipartDownloader;

/**
 * Servlet implementation class AdminUploadController
 */
@WebServlet("/admin/downloader")
public class AdminUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(AdminUploadController.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUploadController() {
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
		Admin admin = (Admin) session.getAttribute(Links.SESSION_ADMIN_ATTR_NAME);
		
		//Get file uploaded by user
		UploadedFile uploadedFile = null;
		try {
			//InputStream is closed inside
			uploadedFile = new MultipartDownloader().download(request.getInputStream(), request.getHeader("Content-Type"), Config.UPLOAD_FOLDER);
		} catch (ValidationException | IOException e) {
			logger.debug("Download failure: " + e);
			response.sendRedirect(Urls.ADMIN_UPLOAD_PAGE_URL + Links.createQueryString(Links.ERROR_PARAM_NAME));
			return;
		}
		
		//put file into DB
		Transaction tx = HibernateUtil.getCurrentSession().beginTransaction();
		FileDao fileDao = new FileDaoImpl();
		try {
			int dbId = fileDao.insertOne(uploadedFile);
			uploadedFile.setId(dbId);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			StatusEndpoint.incrementErrors();
			logger.error("File was downloaded, but service failed to add entry about it into DB, redirect to error page. " + 
			"filePath: " + uploadedFile.getFsPath() + " Exception: " + e.getClass().getName() + " Message: " + e.getMessage());
			response.sendRedirect(Urls.ERROR_PAGE_URL);;
			return;
		} 
		
		logger.debug("New file downloaded to " + uploadedFile.getFsPath());
		
		//OK, generate response html
		request.setAttribute("logoutPage", Urls.LOGOUT_PAGE_URL);
		
		//get session and user object (don't know if it's a user or admin)
		request.setAttribute("name", admin.getEmail());
		request.setAttribute("fileName", uploadedFile.getName());
		request.setAttribute("uploadMoreLink", Urls.ADMIN_UPLOAD_PAGE_URL);
		//ftlData.put("viewLastFiles", Urls.VIEW_LAST_FILES_PAGE_URL);

		request.getRequestDispatcher(Links.ADMIN_DOWNLOAD_SUCCESS_JSP).forward(request, response);
		logger.debug("Response sent. Return.");
	}

}
