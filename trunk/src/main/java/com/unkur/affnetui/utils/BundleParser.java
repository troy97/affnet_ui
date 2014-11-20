package com.unkur.affnetui.utils;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import au.com.bytecode.opencsv.CSVReader;

import com.unkur.affnetui.config.AppConfig;


public class BundleParser {

	private static final String LANG_LIST_NAME = "lang_list";
	private static final String ENG = "en";
	private static String i18nFolder = AppConfig.getInstance().getWithEnv("i18nFolder");
	
	public static void parse() {
		try {
			Map<String, List<String>> langData = readLangData(i18nFolder + "/i18n.csv");
			List<String> langList = langData.get(LANG_LIST_NAME);
			checkHeader(langList);
			
			for(int i=0; i<langList.size(); i++) {
				writeLangFile(langData, i, langList.get(i));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void checkHeader(List<String> header) {
		
	}
	
	private static Map<String, List<String>> readLangData(String path) throws IOException {
		Map<String, List<String>> result = new LinkedHashMap<>();
		try(CSVReader reader = new CSVReader(new FileReader(path));) {
			String[] nextLine;
			while( (nextLine = reader.readNext()) != null ) {
				List<String> data = new ArrayList<String>(Arrays.asList(nextLine));
				result.put( data.get(0), new ArrayList<String>(data.subList(1, data.size())) );
			}
		}
		return result;
	}
	
	private static void writeLangFile(Map<String, List<String>> langData, int langIndex, String langCode) throws IOException {
		Properties prop = new Properties();
		langCode = langCode.equals(ENG) ? "" : "_"+langCode ;
		try(OutputStream output = new FileOutputStream(i18nFolder + "/text" + langCode + ".properties");) {
			for(Map.Entry<String, List<String>> entry : langData.entrySet() ) {
				// set the properties value
				prop.setProperty(entry.getKey(), entry.getValue().get(langIndex));
			}
			prop.store(output, null);
		}
	}
	
}
