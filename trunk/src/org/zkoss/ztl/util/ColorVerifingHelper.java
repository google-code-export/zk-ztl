package org.zkoss.ztl.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
		colorMap.put(RED, "(?i)((rgb\\( *255 *, *0 *, *0 *\\))|(#ff0000)|(red))");
		colorMap.put(GREEN, "(?i)((rgb\\( *0 *, *255 *, *0 *\\))|(#00ff00)|(green))");
		colorMap.put(BLUE, "(?i)((rgb\\( *0 *, *0 *, *255 *\\))|(#0000ff)|(blue))");
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

	// public boolean compare(String value1, String value2) {
	//
	// if(value1==null || value2==null)
	// throw new IllegalArgumentException("can't be NULL!");
	//
	// String color1 = transform(value1);
	// String color2 = transform(value2);
	//
	// return color1.equals(color2);
	//
	//
	// }
	//
	// private static final String HEX =
	// "^\\#[0-9a-fA-F]+[0-9a-fA-F]+[0-9a-fA-F]+";
	// private static final String RGB = "^rgb\\(";
	// private static final String NAMED_COLOR = "[a-zA-z]+";
	//
	// public String transform(String value1) {
	// return null;
	// }
}
