package com.unkur.affnetui.file;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.unkur.affnetui.config.AppConfig;

public class FileFormat {
	
	public enum Type{
		SIMPLIFIED ("simplified"),
		VENDORMODEL ("vendor.model");
		
        private String formatName; 
        private Type(String name) { 
            this.formatName = name; 
        } 
        
        @Override 
        public String toString(){ 
            return formatName; 
        }
		
	};

	private static AppConfig cfg = AppConfig.getInstance();
	private static Logger logger = Logger.getLogger(FileFormat.class.getName());
	
	//Column names in file uploaded by shop
		//Common columns for all formats
			//Mandatory
	public static final String COLUMN_URL_PATH_M = "url";
	public static final String COLUMN_PRICE_M = "price";
	public static final String COLUMN_PRICE_CURRENCY_M = "currencyId";
	public static final String COLUMN_CATEGORY_M = "category";	
			//Optional
	public static final String COLUMN_AVAILABLE_O = "available";	
	public static final String COLUMN_BID_O = "bid";	
	public static final String COLUMN_CBID_O = "cbid";	
	public static final String COLUMN_PICTURE_O = "picture";	
	public static final String COLUMN_STORE_O = "store";	
	public static final String COLUMN_PICKUP_O = "pickup";	
	public static final String COLUMN_DELIVERY_O = "delivery";	
	public static final String COLUMN_DESCRIPTION_O = "description";	
	public static final String COLUMN_VENDOR_CODE_O = "vendorCode";	
	public static final String COLUMN_LOCAL_DELIVERY_COST_O = "local_delivery_cost";	
	public static final String COLUMN_SALES_NOTES_O = "sales_notes";	
	public static final String COLUMN_MANUFACTURER_WARRANTY_O = "manufacturer_warranty";	
	public static final String COLUMN_COUNTRY_OF_ORIGIN_O = "country_of_origin";	
	public static final String COLUMN_ADULT_O = "adult";	
	public static final String COLUMN_AGE_O = "age";	
	public static final String COLUMN_BARCODE_O = "barcode";	
	
		//Simplified format
			//Mandatory
	public static final String SIMPLIFIED_NAME_M = "name";
			//Optional
	
	
		//vendor.model format
			//Mandatory
	public static final String VENDORMODEL_TYPE_M = "type";
	public static final String VENDORMODEL_VENDOR_M = "vendor";
	public static final String VENDORMODEL_MODEL_M = "model";
			//Optional
	public static final String VENDORMODEL_TYPE_PREFIX_O = "typePrefix";
	public static final String VENDORMODEL_DOWNLOADABLE_O = "downloadable";
	
	/**
	 * Creates list of all column names, that MUST be present in uploadable csv file
	 * @return list of column names
	 */
	public static List<String> getMandatoryColumnNames(Enum type) {
		List<String> result = new ArrayList<String>(getFieldsByMask("COLUMN_", "_M"));
		if(type == Type.SIMPLIFIED) {
			result.addAll(getFieldsByMask("SIMPLIFIED_", "_M"));
		} else if (type == Type.VENDORMODEL) {
			result.addAll(getFieldsByMask("VENDORMODEL_", "_M"));
		} else {
			return null;
		}
		return result;
	}
	
	/**
	 * Creates list of all column names, that MUST be present in uploadable csv file
	 * @return list of column names
	 */
	public static List<String> getOptionalColumnNames(Enum type) {
		List<String> result = new ArrayList<String>(getFieldsByMask("COLUMN_", "_O"));
		if(type == Type.SIMPLIFIED) {
			result.addAll(getFieldsByMask("SIMPLIFIED_", "_O"));
		} else if (type == Type.VENDORMODEL) {
			result.addAll(getFieldsByMask("VENDORMODEL_", "_O"));
		} else {
			return null;
		}
		return result;
	}
	
	
	private static List<String> getFieldsByMask (String prefix, String suffix) {
		List<String> result = new ArrayList<String>();
		try {
			Field[] allFields = FileFormat.class.getDeclaredFields();
			for(Field field : allFields) {
				if(field.getName().startsWith(prefix) && field.getName().endsWith(suffix)) {
					result.add((String) field.get(null));
				}
			}
		} catch (Exception e) {
			logger.error("Unable to get list of fields: " + e);
		} 
		return result;
	}
	
}
