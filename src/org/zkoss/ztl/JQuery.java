/* JQuery.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Dec 4, 2009 10:45:32 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.ztl;

/**
 * A simulator of JQuery client side object, which wraps the JQuery client side
 * API.
 * @author jumperchen
 * 
 */
public class JQuery extends ClientWidget {
	/**
	 * The script of get jq by UUID
	 */
	private static String JQ = "jq('%1')";

	public JQuery(String uuid) {
		if (isEmpty(uuid))
			throw new NullPointerException("uuid cannot be null!");
		_out = new StringBuffer(JQ.replace("%1", uuid));
	}
	public JQuery(ClientWidget el) {
		_out = new StringBuffer(JQ.replace("'%1'", el.toString()));
	}
	public JQuery(StringBuffer out, String script) {
		_out = new StringBuffer(out).append(script);
	}
	public JQuery(StringBuffer out) {
		_out = new StringBuffer(out);
	}
	/**
	 * Returns the CSS value from the given name.
	 * @param name CSS name.
	 */
	public String css(String name) {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".css('" + name + "')");
	}
	
	/**
	 * Returns the attribute value from the given name.
	 * @param name attribute name of the element.
	 */
	public String attr(String name) {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".attr('" + name + "')");
	}
	
	/**
	 * Returns whether includes the className.
	 * @param className the CSS class name.
	 */
	public boolean hasClass(String className) {
		return Boolean.valueOf((ZKTestCase.getCurrent().getEval(_out.toString() + ".hasClass('" + className + "')")));
	}
	/**
	 * Finds the element from the given selector.
	 * @param selector the JQuery allowed.
	 */
	public JQuery find(String selector) {
		return new JQuery(_out, ".find('" + selector + "')");
	}
	
	/**
	 * Returns the first element in JQuery object.
	 */
	public JQuery first() {
		return new JQuery(_out, ".first()");
	}
	
	/**
	 * Returns the last element in JQuery object.
	 */
	public JQuery last() {
		return new JQuery(_out, ".last()");
	}
	
	/**
	 * Returns the previous element in JQuery object.
	 */
	public JQuery prev() {
		return new JQuery(_out, ".prev()");
	}
	
	/**
	 * Returns the next element in JQuery object.
	 */
	public JQuery next() {
		return new JQuery(_out, ".next()");
	}

	/**
	 * Returns the parent element in JQuery object.
	 */
	public JQuery parent() {
		return new JQuery(_out, ".parent()");
	}
	/**
	 * Returns the text content
	 */
	public String text() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".text()");
	}
	
	/**
	 * Returns the html content(innerHTML)
	 */
	public String html() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".html()");
	}
	
	/**
	 * Returns the current computed height for the first element.
	 */
	public String height() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".height()");
	}
	
	/**
	 * Returns the current computed width for the first element.
	 */
	public String width() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".width()");
	}
	
	/**
	 * Returns the current computed height for the first element,
	 * including padding but not border.
	 */
	public String innerHeight() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".innerHeight()");
	}
	
	/**
	 * Returns the current computed width for the first element,
	 * including padding but not border.
	 */
	public String innerWidth() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".innerWidth()");
	}
	
	/**
	 * Returns the current computed width for the first element,
	 * including padding and border.
	 */
	public String outerWidth() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".outerWidth()");
	}
	
	/**
	 * Returns the current computed height for the first element,
	 * including padding and border.
	 */
	public String outerHeight() {
		return ZKTestCase.getCurrent().getEval(_out.toString() + ".outerHeight()");
	}
	
	/**
	 * Switches to the ZK object.
	 */
	public ZK zk() {
		return new ZK(_out, ".zk");
	}
	/**
	 * Returns the scrollbar width.
	 */
	public static int scrollbarWidth() {
		return Integer.parseInt(ZKTestCase.getCurrent().getEval("jq.scrollbarWidth()"));
	}

	/**
	 * Returns whether the widget exists or not.
	 */
	public boolean exists() {
		return Boolean.valueOf(ZKTestCase.getCurrent().getEval("!!" + _out.toString() + "[0]"));
	}
}
