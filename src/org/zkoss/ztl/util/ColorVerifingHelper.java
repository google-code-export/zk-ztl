package org.zkoss.ztl.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Phoenix
 *
 */
public class ColorVerifingHelper {
	private static ColorVerifingHelper instance;
	private Map<String, String> colorMap;
	public static final String RED = "red";
	public static final String GREEN = "green";
	public static final String BLUE = "blue";
	
	private ColorVerifingHelper() {
		colorMap = new HashMap<String, String>();
		colorMap.put(RED, "rgb(255, 0, 0)#ff0000red");
		colorMap.put(GREEN, "rgb(0, 255, 0)#00ff00green");
		colorMap.put(BLUE, "rgb(0, 0, 255)#0000ffblue");
	}
	
	public static ColorVerifingHelper getInstance() {
		if (instance == null) {
			instance = new ColorVerifingHelper();
		}
		
		return instance;
	}
	
	public boolean isColorMatch(String value1, String value2) {
		String colorString = colorMap.get(value1);
		return colorString.contains(value2);
	}
	
	
	
//	public boolean compare(String value1, String value2) {
//		
//		if(value1==null || value2==null)
//			throw new IllegalArgumentException("can't be NULL!");
//		
//		String color1 = transform(value1);
//		String color2 = transform(value2);
//		
//		return color1.equals(color2);
//		
//		
//	}
//
//	private static final String HEX = "^\\#[0-9a-fA-F]+[0-9a-fA-F]+[0-9a-fA-F]+";
//	private static final String RGB = "^rgb\\(";
//	private static final String NAMED_COLOR = "[a-zA-z]+";
//	
//	public String transform(String value1) {
//		return null;
//	}
}
