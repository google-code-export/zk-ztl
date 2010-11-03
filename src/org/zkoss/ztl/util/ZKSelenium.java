/* ZKSelenium.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Aug 18, 2010 3:54:01 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.ztl.util;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 * @author jumperchen
 *
 */
public class ZKSelenium extends DefaultSelenium {
	private String _browserbrand;
	private String _browsername;
	
	public ZKSelenium(CommandProcessor processor) {
		super(processor);
	}
	public ZKSelenium(CommandProcessor processor, String browserbrand, String browsername) {
		super(processor);
		_browserbrand = browserbrand;
		_browsername = browsername;
	}
	public String getBrowserBrand() {
		return _browserbrand;
	}
	
	public String getBrowserName() {
		return _browsername;
	}
	
}
