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
	private static final String TEMP_HEX = "^\\#[0-9a-fA-F]+[0-9a-fA-F]+[0-9a-fA-F]+";
	private static final String BYTE_NUMBER = "((1?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	private static final String TEMP_RGB = "^(?i)[r][g][b]\\(" + BYTE_NUMBER +"," +
														" *" + BYTE_NUMBER +"," +
														" *" + BYTE_NUMBER +"\\)";
	private static final String TEMP_NAMED_COLOR = "[a-zA-Z]+";
	private static final Map<String, String> commonColorMap = new HashMap<String, String>();
	
	static {
		// You can refer this link to improve color map.
		// http://web.njit.edu/~kevin/rgb.txt.html
		commonColorMap.put("red", "#ff0000");
		commonColorMap.put("green", "#00ff00");
		commonColorMap.put("blue", "#0000ff");
	}

	private ColorVerifingHelper() {
	}

	public static ColorVerifingHelper getInstance() {
		if (instance == null) {
			instance = new ColorVerifingHelper();
		}

		return instance;
	}

//	test case for red
//	public static void main(String[] args) {
//
//		//if you use String.matches , it's case-sensitive , uppercase will be different with lowercase.
//		String fullPattern = "(([rR][gG][bB]\\( *255 *, *0 *, *0 *\\))|(#[fF]{2}0000)|([rR][eE][dD]))";
//		System.out.println("true=>" + "rgb( 255, 0,0)".matches(fullPattern));
//		System.out.println("true=>" + "rgb( 255,   0 , 0 )".matches(fullPattern));
//		System.out.println("false=>" + "rgb( 25, 0,0)".matches(fullPattern));
//		System.out.println("true=>" + "red".matches(fullPattern));
//		System.out.println("false=>" + "redd".matches(fullPattern));
//		System.out.println("true=>" + "rEd".matches(fullPattern));
//
//		System.out.println("--");
//		//here we use (?i) to open sensitive mode. by Tony
//		String insensitivePattern = "(?i)((rgb\\( *255 *, *0 *, *0 *\\))|(#ff0000)|(red))";
//		System.out.println("true=>" + "rgb( 255, 0,0)".matches(insensitivePattern));
//		System.out.println("true=>" + "rgb( 255,   0 , 0 )".matches(insensitivePattern));
//		System.out.println("false=>" + "rgb( 25, 0,0)".matches(insensitivePattern));
//		System.out.println("true=>" + "red".matches(insensitivePattern));
//		System.out.println("false=>" + "redd".matches(insensitivePattern));
//		System.out.println("true=>" + "rEd".matches(insensitivePattern));
//	}
	
	public boolean isEqualColor(String value1, String value2) {
		if (value1 == null || value2 == null) {
			throw new IllegalArgumentException("Incorrect String! Please check again.");
		}
		
		String color1 = transform(value1.toLowerCase());
		String color2 = transform(value2.toLowerCase());
		
		if (color1 == null || color2 == null) {
			return false;
		}
		
		return color1.equals(color2);
	}

	public String transform(String value) {
		if (value.matches(TEMP_HEX)) {
			return value;
		} else if (value.matches(TEMP_RGB)) {
			String temp = value.replace("rgb(", "");
			temp = temp.replace(")", "");
			temp = temp.replace(" ", "");
			String[] colorCodes = temp.split(",");
			StringBuffer strBuff = new StringBuffer("#");
			
			for(String s : colorCodes) {
				int colorNum = Integer.parseInt(s);
				String hexString = Integer.toHexString(colorNum);
				hexString = hexString.length() > 1 ? hexString : "0" + hexString;
				strBuff.append(hexString);
			}
			
			return strBuff.toString();
		} else if (value.matches(TEMP_NAMED_COLOR)) {
			return commonColorMap.get(value);
		}
		
		throw new IllegalArgumentException("Not a color: \"" + value+"\"");
	}
}
