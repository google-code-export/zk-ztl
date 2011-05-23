/* ClientWidget.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Dec 4, 2009 10:51:37 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.ztl;

/**
 * The skeleton of ZK client side widget. It is used to manipulate a string buffer
 * to concatenate an executed JavaScript code.
 * @author jumperchen
 */
public abstract class ClientWidget {
	protected StringBuffer _out;
	/**
	 * Returns true if the string is null or empty.
	 */
	public static final boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
	/**
	 * Returns true if the string is null or empty or pure blank.
	 */
	public static final boolean isBlank(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	/**
	 * Returns the string that the first word of the name is upper case.
	 * @param key the prefix of the method name. Like <code>set</code> and <code>get</code>
	 * @param name the name of the method.
	 */
	protected String toUpperCase(String key, String name) {
		char[] buf = name.toCharArray();
		buf[0] = Character.toUpperCase(buf[0]);
		return key + new String(buf);
	}
	
	/**
	 * Returns the result of the evaluation, if any.
	 * @param script the JavaScript code
	 * @see #eval(String, boolean)
	 */
	public String eval(String script) {
		return eval(script, true);
	}
	
	/**
	 * Returns the result of the evaluation, if any.
	 * @param script The JavaScript code
	 * @param withDot if true, the dot '.' is added before the script.
	 */
	public String eval(String script, boolean withDot) {
		return ZKTestCase.getCurrent().getEval(_out.toString() + (withDot ? "." : "") + script);
	}
	/**
	 * Returns the evaluation string that is JavaScript format. 
	 */
	public String toLocator() {
		return "zk="+ _out.toString();
	}
	/**
	 * Returns the evaluation string that is JavaScript format. 
	 */
	public String toString() {
		return _out.toString();
	}
}
