package com.unkur.affnetui.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;
import com.unkur.affnetui.config.Urls;

/**
 * Servlet implementation class UiFileRequestController
 */
@WebServlet("/")
public class UiFileRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(UiFileRequestController.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UiFileRequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//query string
		String query = request.getRequestURI();
		if(query.equals(request.getContextPath() + "/")) {
			response.sendRedirect(Urls.MAIN_PAGE_URL);
			return;
		}
		String queryStr = query.substring(request.getContextPath().length() + "/".length());
		if(queryStr.startsWith(Urls.USER_AREA_NAME)) {
			queryStr = queryStr.substring(Urls.USER_AREA_NAME.length());
		} else if(queryStr.startsWith(Urls.ADMIN_AREA_NAME)) {
			queryStr = queryStr.substring(Urls.ADMIN_AREA_NAME.length());
		}
		
		Path queryPath = FileSystems.getDefault().getPath(queryStr);
		
		//path to bootstrap folder
		//If this folder is on file system
		//Path bootstrapPath = FileSystems.getDefault().getPath(AppConfig.getInstance().getWithEnv("WebContentPath") + "/bootstrap");
		//If inside .war file
		ServletContext ctx = request.getServletContext();
	    Path bootstrapPath = FileSystems.getDefault().getPath( ctx.getRealPath(AppConfig.getInstance().getWithEnv("UiFilesPath")) );
	    
		//full path to file = bootstrap path + queryPath
		Path filePath = bootstrapPath.resolve(queryPath);
		
		File file = filePath.toFile();
		
		OutputStream os = response.getOutputStream();
		try {
			if (!file.exists() || file.isDirectory()) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				try (FileInputStream fis = new FileInputStream(file)) {
					IOUtils.copy(fis, os);
				} catch (IOException e) {
					logger.debug("Unable to send file " + e);
				}
			}
		} catch (IOException e) {
			logger.debug("Output stream problem");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
