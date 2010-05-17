/* ZKClientTestCase.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Dec 4, 2009 9:50:12 AM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.ztl;

/**
 * A skeleton of ZK client widget.
 * @author jumperchen
 *
 */
public class ZKClientTestCase extends ZKTestCase {
	protected int _timeout;

	public static final String PAGE_UP = "33";
	public static final String PAGE_DOWN = "34";
	public static final String END = "35";
	public static final String HOME = "36";
	
	public static final String LEFT = "37";
	public static final String UP = "38";
	public static final String RIGHT = "39";
	public static final String DOWN = "40";

	public static final String INSERT = "45";
	public static final String DELETE = "46";
	
	public static final String BACKSPACE = "8";
	public static final String TAB = "9";
	public static final String ENTER = "13";
	public static final String ESC = "27";
	
	/**
	 * Waits for Ajax response.
	 * <p>By default the timeout time is specified in config.properties
	 * @see #waitResponse(int) 
	 */
	protected void waitResponse() {
		waitResponse(_timeout);
	}
	
	/**
	 * Waits for Ajax response according to the timeout attribute.
	 * @param timeout the time. (millisecond).
	 */
	protected void waitResponse(int timeout) {
		long s = System.currentTimeMillis();
		while(Boolean.valueOf(this.getEval("!!zAu.processing()"))) {
			if (System.currentTimeMillis() - s > timeout) {
				assertTrue("Test case timeout!", false);
				break;
			}
		}
	}
	
	protected void runZscript(String zscript) {
		getEval("zAu.send(new zk.Event(null, 'onZTLService', '"+ zscript + "', 10))");
	}
	
	/**
	 * Returns the Widget object of the UUID.
	 * @param number the number of the widget ID.
	 * @see #uuid(int)
	 * @see #widget(String)
	 */
	protected Widget widget(int number) {
		return widget(uuid(number));
	}
	
	/**
	 * Returns the Widget object of the UUID.
	 * @param uuid the widget ID.
	 */
	protected Widget widget(String uuid) {
		return new Widget(uuid);
	}
	
	/**
	 * Returns the Widget object from the JQuery object.
	 * @param jQuery the JQuery object.
	 */
	protected Widget widget(JQuery jQuery) {
		return new Widget(jQuery);
	}
	
	/**
	 * Returns the Jquery object of the selector
	 * <p> Default: without "#" sign
	 * @param selector the selector
	 */
	protected JQuery jq(String selector) {
		return new JQuery(selector);
	}
	
	/**
	 * Returns the Jquery object of the ZKClientObject.
	 * @param el the ZKClientObject
	 */
	protected JQuery jq(ClientWidget el) {
		return new JQuery(el);
	}
	
	/**
	 * Returns the ZK object of the ZKClientObject.
	 * @param el the ZKClientObject
	 */
	protected ZK zk(ClientWidget el) {
		return new ZK(el);
	}
	
	/**
	 * Returns the ZK object of the selector
	 * @param selector the selector of the element
	 */
	protected ZK zk(String selector) {
		return new ZK(selector);
	}
	/**
	 * Returns the int value from the given string number.
	 * @param number the string number, if null, 0 is assumed.
	 */
	public static int parseInt(String number) {
		if (number != null) {
			number = number.replaceAll("[^-0-9\\.]", "");
			int decimal = number.indexOf('.');
			if (decimal > 0)
				number = number.substring(0, decimal);
			return Integer.parseInt(number);
		}
		return 0;
	}
	public void addSelection(ClientWidget locator, String optionLocator) {
		super.addSelection(locator.toString(), optionLocator);
	}

	public void assignId(ClientWidget locator, String identifier) {
		super.assignId(locator.toString(), identifier);
	}

	public void check(ClientWidget locator) {
		super.check(locator.toString());
	}

	public void click(ClientWidget locator) {
		super.click(locator.toString());
	}

	public void clickAt(ClientWidget locator, String coordString) {
		super.clickAt(locator.toString(), coordString);
	}
	
	public void contextMenu(ClientWidget locator) {
		super.contextMenu(locator.toString());
	}

	public void contextMenuAt(ClientWidget locator, String coordString) {
		super.contextMenuAt(locator.toString(), coordString);
	}

	public void doubleClick(ClientWidget locator) {
		// don't use doubleClick(), because it fails in IE
		super.doubleClickAt(locator.toString(), "0,0");
	}

	public void doubleClickAt(ClientWidget locator, String coordString) {
		super.doubleClickAt(locator.toString(), coordString);
	}

		public void dragAndDrop(ClientWidget locator, String movementsString) {
		super.dragAndDrop(locator.toString(), movementsString);
	}
	
	public void dragdropTo(ClientWidget locatorOfObjectToBeDragged, String from, String to) {
		super.dragdropTo(locatorOfObjectToBeDragged.toString(), from, to);
	}
	
	public void dragdropToObject(ClientWidget locatorOfObjectToBeDragged,
			ClientWidget locatorOfDragDestinationObject, String from, String to) {
		super.dragdropToObject(locatorOfObjectToBeDragged.toString(),
				locatorOfDragDestinationObject.toString(), from, to);
	}
	
	public void dragAndDropToObject(ClientWidget locatorOfObjectToBeDragged,
			ClientWidget locatorOfDragDestinationObject) {
		super.dragAndDropToObject(locatorOfObjectToBeDragged.toString(), locatorOfDragDestinationObject.toString());
	}

	public void dragdrop(ClientWidget locator, String movementsString) {
		super.dragdrop(locator.toString(), movementsString);
	}

	public void fireEvent(ClientWidget locator, String eventName) {
		super.fireEvent(locator.toString(), eventName);
	}

	public void focus(ClientWidget locator) {
		super.focus(locator.toString());
	}
	
	public void blur(ClientWidget locator) {
		super.fireEvent(locator.toString(), "blur");
	}

	public String getAttribute(ClientWidget attributeLocator) {
		return super.getAttribute(attributeLocator.toString());
	}

	public Number getCursorPosition(ClientWidget locator) {
		return super.getCursorPosition(locator.toString());
	}

	public Number getElementHeight(ClientWidget locator) {
		return super.getElementHeight(locator.toString());
	}

	public Number getElementIndex(ClientWidget locator) {
		return super.getElementIndex(locator.toString());
	}

	public Number getElementPositionLeft(ClientWidget locator) {
		return super.getElementPositionLeft(locator.toString());
	}

	public Number getElementPositionTop(ClientWidget locator) {
		return super.getElementPositionTop(locator.toString());
	}

	public Number getElementWidth(ClientWidget locator) {
		return super.getElementWidth(locator.toString());
	}

	public String[] getSelectOptions(ClientWidget selectLocator) {
		return super.getSelectOptions(selectLocator.toString());
	}

	public String getSelectedId(ClientWidget selectLocator) {
		return super.getSelectedId(selectLocator.toString());
	}

	public String[] getSelectedIds(ClientWidget selectLocator) {
		return super.getSelectedIds(selectLocator.toString());
	}

	public String getSelectedIndex(ClientWidget selectLocator) {
		return getCurrent().getSelectedIndex(selectLocator.toString());
	}

	public String[] getSelectedIndexes(ClientWidget selectLocator) {
		return super.getSelectedIndexes(selectLocator.toString());
	}

	public String getSelectedLabel(ClientWidget selectLocator) {
		return super.getSelectedLabel(selectLocator.toString());
	}

	public String[] getSelectedLabels(ClientWidget selectLocator) {
		return super.getSelectedLabels(selectLocator.toString());
	}

	public String getSelectedValue(ClientWidget selectLocator) {
		return super.getSelectedValue(selectLocator.toString());
	}

	public String[] getSelectedValues(ClientWidget selectLocator) {
		return super.getSelectedValues(selectLocator.toString());
	}


	public String getTable(ClientWidget tableCellAddress) {
		return super.getTable(tableCellAddress.toString());
	}

	public String getText(ClientWidget locator) {
		return super.getText(locator.toString());
	}

	public String getValue(ClientWidget locator) {
		return super.getValue(locator.toString());
	}

	public void highlight(ClientWidget locator) {
		super.highlight(locator.toString());
	}

	public boolean isChecked(ClientWidget locator) {
		return super.isChecked(locator.toString());
	}

	public boolean isEditable(ClientWidget locator) {
		return super.isEditable(locator.toString());
	}

	public boolean isElementPresent(ClientWidget locator) {
		return super.isElementPresent(locator.toString());
	}

	public boolean isOrdered(ClientWidget locator1, ClientWidget locator2) {
		return super.isOrdered(locator1.toString(), locator2.toString());
	}

	public boolean isSomethingSelected(ClientWidget selectLocator) {
		return super.isSomethingSelected(selectLocator.toString());
	}

	@Override
	public boolean isTextPresent(String pattern) {
		return super.isTextPresent(pattern);
	}

	public boolean isVisible(ClientWidget locator) {
		return super.isVisible(locator.toString());
	}

	public void keyDown(ClientWidget locator, String keySequence) {
		super.keyDown(locator.toString(), keySequence);
	}

	public void keyPress(ClientWidget locator, String keySequence) {
		super.keyPress(locator.toString(), keySequence);
	}


	public void keyUp(ClientWidget locator, String keySequence) {
		super.keyUp(locator.toString(), keySequence);
	}

	public void mouseDown(ClientWidget locator) {
		super.mouseDown(locator.toString());
	}

	public void mouseDownAt(ClientWidget locator, String coordString) {
		super.mouseDownAt(locator.toString(), coordString);
	}

	public void mouseDownRight(ClientWidget locator) {
		super.mouseDownRight(locator.toString());
	}

	public void mouseDownRightAt(ClientWidget locator, String coordString) {
		super.mouseDownRightAt(locator.toString(), coordString);		
	}

	public void mouseMove(ClientWidget locator) {
		super.mouseMove(locator.toString());
	}

	public void mouseMoveAt(ClientWidget locator, String coordString) {
		super.mouseMoveAt(locator.toString(), coordString);
	}

	public void mouseOut(ClientWidget locator) {
		super.mouseOut(locator.toString());
	}

	public void mouseOver(ClientWidget locator) {
		super.mouseOver(locator.toString());
	}

	public void mouseUp(ClientWidget locator) {
		super.mouseUp(locator.toString());
	}

	public void mouseUpAt(ClientWidget locator, String coordString) {
		super.mouseUpAt(locator.toString(), coordString);
	}

	public void mouseUpRight(ClientWidget locator) {
		super.mouseUpRight(locator.toString());
	}

	public void mouseUpRightAt(ClientWidget locator, String coordString) {
		super.mouseUpRightAt(locator.toString(), coordString);
	}

	public void removeAllSelections(ClientWidget locator) {
		super.removeAllSelections(locator.toString());
	}

	public void removeSelection(ClientWidget locator, String optionLocator) {
		super.removeSelection(locator.toString(), optionLocator);
	}

	public void rollup(ClientWidget rollupName, String kwargs) {
		super.rollup(rollupName.toString(), kwargs);
	}

	public void select(ClientWidget selectLocator, String optionLocator) {
		super.select(selectLocator.toString(), optionLocator);
	}

	public void selectFrame(ClientWidget locator) {
		super.selectFrame(locator.toString());
	}

	public void setContext(ClientWidget context) {
		super.setContext(context.toString());
	}

	public void setCursorPosition(ClientWidget locator, String position) {
		super.setCursorPosition(locator.toString(), position);
	}
	
	public void submit(ClientWidget formLocator) {
		super.submit(formLocator.toString());
	}

	/**
	 * Types the value to the locator.
	 * <p> The method will call focus() before typing and blur() after typed.
	 */
	public void type(ClientWidget locator, String value) {
		focus(locator);
		super.type(locator.toString(), value);
		blur(locator);
	}

	/**
	 * Types the value to the locator.
	 * <p> The method will call focus() before typing and blur() after typed.
	 */
	public void typeKeys(ClientWidget locator, String value) {
		focus(locator);
		super.typeKeys(locator.toString(), value);
		blur(locator);
	}

	public void uncheck(ClientWidget locator) {
		super.uncheck(locator.toString());
	}
	
}
