package com.unkur.affnetui.file;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.log4j.Logger;

import com.unkur.affnetui.ValidationException;

/**
 * This abstract class must be extended by classes that provide file validation and processing.
 * Each implementation of this interface MUST be marked with @FileExtension annotation.
 * @author Anton Lukashchuk
 *
 */
public abstract class AbstractFileValidator {
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * Validates file during download, usually file can be validated on it's small piece (first line for example).
	 * This methods reads this first piece (particular piece depends on implementation) and checks it according to 
	 * requirements for concrete type of file.
	 * @param stream to read data from
	 * @return byte[] first piece read from stream, usually this piece is written to file before downloading remaining data.
	 * @throws ValidationException 
	 */
	public abstract byte[] validateDownload(MultipartStream stream) throws ValidationException;
	
	/**
	 * Checks if given header contains all of mandatory columns and
	 * only ones
	 * @param someHeader
	 * @param mandatoryColumns
	 * @throws ValidationException
	 */
	protected void validateHeader(List<String> someHeader) throws ValidationException {
		List<String> header = new LinkedList<String>(someHeader);
		
		//get list of mandatory columns according to type
		List<String> mandatoryColumns;
		if(header.contains(FileFormat.VENDORMODEL_TYPE_M)) {
			mandatoryColumns = FileFormat.getMandatoryColumnNames(FileFormat.Type.VENDORMODEL);
		} else {
			mandatoryColumns = FileFormat.getMandatoryColumnNames(FileFormat.Type.SIMPLIFIED);
		}
		
		//check for presence of all mandatory columns
		if( !header.containsAll(mandatoryColumns) ) {
			String msg = "File is missing some mandatory data."
					+ " Header line is: " + header + "; Mandatory columns are: " + mandatoryColumns +
					". Reject file, throw exception...";
			logger.debug(msg);
			throw new ValidationException(msg);
		}
		
		//check for duplicates of mandatory columns
		for(String c : mandatoryColumns) {
			if( header.contains(c) ) {
				header.remove(c);
				if(header.contains(c)) {
					String msg = "File has duplicate mandatory column in header: " + c;
					logger.debug(msg);
					throw new ValidationException(msg);
				}
			}
		}
	}//method
	
}
