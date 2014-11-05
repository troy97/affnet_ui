package com.unkur.affnetui.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {
	
	private static Logger logger = Logger.getLogger(CSVParser.class.getName());
	
	/**
	 * Return List of header names found in file
	 * headers must be the first line in the file.
	 * @return List<String> first line of file.
	 * @throws IOException 
	 */
	public static List<String> getHeaders(String filePath, char separator) throws IOException {
		List<String> result = new ArrayList<>();
		try ( CSVReader reader = new CSVReader(new FileReader(filePath), separator) ) {
			result = new ArrayList<String>(Arrays.asList(reader.readNext()));
		} catch (FileNotFoundException e) {
			logger.debug("Error reading file: " + e);
			throw new IOException();
		}
		return result;
	}

	/**
	 * Parses given byte[] into List<String>
	 * byte[] must end with 'LF'
	 * @param line
	 * @param encoding to convert byte[] into String
	 * @return
	 * @throws IOException 
	 */
	public static List<String> parseLine(byte[] line, String encoding, char separator) throws IOException {
		List<String> result = null;
		CSVReader reader = new CSVReader( new StringReader(new String(line, encoding)), separator );
		result = new ArrayList<String>( Arrays.asList(reader.readNext()) );
		reader.close();
		return result;
	}

}
