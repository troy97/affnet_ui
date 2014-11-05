package com.unkur.affnetui.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.MultipartStream.MalformedStreamException;
import org.apache.log4j.Logger;

import com.google.common.base.Throwables;
import com.sun.net.httpserver.HttpExchange;
import com.unkur.affnetui.ParsingException;
import com.unkur.affnetui.ValidationException;
import com.unkur.affnetui.config.Config;
import com.unkur.affnetui.entity.UploadedFile;

/**
 * This class implements downloading of price-list file sent via http multipart 
 * @author Anton Lukashchuk
 *
 */
public class MultipartDownloader {
	
	private static Logger logger = Logger.getLogger(MultipartDownloader.class.getName());
	
	private static final int INPUT_BUFF_SIZE = 4096;
	/**
	 * Download single file
	 * @param InputStream. Note! this method closes InputStream of given exchange.
	 * @param http boundary as byte array
	 * @param path to folder where the resulting file will be stored, for example "/home/userName/downloadedFiles"
	 * @return UploadedFile object
	 * @throws IOException if failed to download file
	 * @throws ValidationException if file didn't pass initial validation and thus was discarded
	 */
	public UploadedFile download(InputStream in, String ctHeader, String folderPath) throws IOException, ValidationException {
		logger.debug("Starting multipart download.");
		UploadedFile result = null;
		try {
			int shopId = 0;
			String extension = null;

			String contentType = checkContentType(ctHeader);
			byte[] boundary;
			try {
				boundary = parseBoundary(contentType);
			} catch (ParsingException e) {
				logger.debug("Can't get multipart boundary.");
				throw new IOException();
			}

			MultipartStream multipartStream = new MultipartStream(in, boundary, INPUT_BUFF_SIZE, null);

			//first part (must contain shop id)
			if(multipartStream.skipPreamble()) {
				multipartStream.readHeaders();
				shopId = getShopId(multipartStream);
			} else {
				String msg = "Inconsisten upload";
				logger.debug(msg);
				throw new IOException(msg);
			}

			//next part (must contain price-list file)
			String tmpFilePath;
			if(multipartStream.readBoundary()) {
				String header = multipartStream.readHeaders();
				try {
					extension = parseExtension(header);
				} catch (ParsingException e) {
					logger.debug("Can't get file extension.");
					throw new IOException();
				}

				AbstractFileValidator validator = ValidationUtils.getValidator(extension);
				if(validator == null) {
					String msg = "Unsupported file extension: " + extension;
					logger.debug(msg);
					throw new ValidationException(msg);
				}
				byte[] firstLine = validator.validateDownload(multipartStream);
				tmpFilePath = saveToFile(folderPath, shopId, multipartStream, firstLine);
			} else {
				String msg = "Inconsisten upload";
				logger.debug(msg);
				throw new IOException(msg);
			}

			long uploadTime = System.currentTimeMillis();
			result = new UploadedFile(tmpFilePath, extension, uploadTime, shopId);
			logger.debug("File downloaded successfully. Return.");
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + Throwables.getStackTraceAsString(e));
			throw new IOException();
		}
		return result;
	}


	private String saveToFile(String folderPath, int shopId,
			MultipartStream multipartStream, byte[] firstLine)
			throws IOException {
		String tmpFilePath;
		try {
			//create unique temporary file name, later this file will be renamed to appropriate name format
			String tmpFileName = shopId + "_" + System.currentTimeMillis() + "_" + new Random().nextInt(512);
			tmpFilePath = folderPath + "/" + tmpFileName;
			File tmpFile = new File(tmpFilePath);
			FileOutputStream fileOut = new FileOutputStream(tmpFile);
			fileOut.write(firstLine);
			multipartStream.readBodyData(fileOut);
			fileOut.close();
			logger.debug("Temporary file created: " + tmpFilePath);
		} catch (Exception e) {
			logger.debug("Unable to save data to file: " + e);
			throw new IOException();
		}
		return tmpFilePath;
	}


	private String checkContentType(String header) throws IOException {
		String contentType = header;
		if(!contentType.contains("multipart/form-data")) {
			String msg = "Not a multipart content";
			logger.debug(msg);
			throw new IOException(msg);
		}
		return contentType;
	}

	
	/**
	 * Parse boundary header 
	 * @param contentTypeHeader
	 * @return boundary as byte array
	 */
	private byte[] parseBoundary(String contentTypeHeader) throws ParsingException {
		byte[] boundary;
		try {
			String boundaryStr = contentTypeHeader.split("boundary=")[1];
			boundary = boundaryStr.getBytes(Config.ENCODING);
		} catch (Exception e) {
			logger.error("Unable to get boundary from multipart headers: " + e);
			throw new ParsingException();
		}
		return boundary;
	}

	
	/**
	 * Read shop id from multipartStream
	 * @param multipartStream
	 * @return
	 * @throws MalformedStreamException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private int getShopId(MultipartStream multipartStream) throws IOException {
		int shopId;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			multipartStream.readBodyData(out);
			String shopIdStr = new String(out.toByteArray(), Config.ENCODING);
			shopId = Integer.valueOf(shopIdStr);
			out.close();
		} catch (Exception e) {
			logger.debug("Unable to read shop id from multipartStream: " + e);
			throw new IOException();
		}
		return shopId;
	}

	
	/**
	 * Parse file extension from given header
	 * @param header
	 * @return extension as ".xxx", actually last for letters of filename are returned
	 * @throws ParsingException 
	 */
	private String parseExtension(String header) throws ParsingException {
		String extension;
		try{
			String fileName = parseFileName(header);
			logger.debug("Original file name = " + fileName);
			extension = fileName.substring(fileName.length() - ".xxx".length()).toLowerCase();
		} catch (Exception e) {
			logger.debug("Unable to get file extension: " + e);
			throw new ParsingException();
		}
		return extension;
	}
	
	
	/**
	 * Get filename from headers
	 */
	private String parseFileName(String headers) throws ParsingException {
		String result = null;
		try {
			String tmp = headers.split("filename=")[1]; //get string, begining after "filename="
			result = tmp.split("\"")[1]; //get string begining after first \" and ending before next \"
		} catch (Exception e) {
			logger.debug("Unable to parse filename.");
			throw new ParsingException();
		}
		return result;
	}
	
}
