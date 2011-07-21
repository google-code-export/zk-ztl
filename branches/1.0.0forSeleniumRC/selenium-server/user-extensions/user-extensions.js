/* zselenium.js

	Purpose:
		
	Description:
		
	History:
		Fri May 20 21:05:40     201, Created by TonyQ

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

This program is distributed under GPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
//Reference to 
//http://seleniumhq.org/docs/08_user_extensions.html
// The "inDocument" is a the document you are searching.
PageBot.prototype.locateElementByZK = function(text, inDocument) {

	var locator = text;
	
	if (locator.indexOf("zk.") == 0 || locator.indexOf("zk(") == 0
			|| locator.indexOf("jq") == 0) {
		locator = selenium.getUserEval(locator);
		
		if (locator.length == 0 )
			throw new SeleniumError("Element " + locator + " not found");
		
		if (locator.length)
			return locator[0];
		else if (locator.$n)
			return locator.$n();
		return (locator);
	}
	if (locator.charAt(0) == '$' || locator.charAt(0) == '#') {
		var win = this.getCurrentWindow();
		if (win.zk) {
			var element = win.zk(locator).jq[0];
			if (element != null) {
	        	return this.browserbot.highlight(element);
	    	}
	    }
	    LOG.debug('ZK is not found!');
	}
    var element = this.findElementOrNull(locator, win);
    if (element == null) throw new SeleniumError("Element " + locator + " not found");
    return element;
};


//Reference to http://wiki.openqa.org/display/SEL/eval
/*
 * @TODO review this with zSelenium.js 
 */
Selenium.prototype.getUserEval = function(script) {
    try {
        var window = this.browserbot.getUserWindow(),
			result = (window.jq ? window.jq.evalJSON : eval)(script);
        // Selenium RC doesn't allow returning null
        if (result == null) return "null";
        return result;
    } catch (e) {
        throw new SeleniumError("Threw an exception: " + extractExceptionMessage(e));
    }
};

