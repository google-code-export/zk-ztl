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
	private String _browserpath;
	private String _browsername;
	
	private boolean _openonce = false;
	private int _cyclecount = 0;
	
	private boolean isBrowserOpened = false;
	@Override
	public void start() {
		if(_openonce){
			_cyclecount++;
				
			if(!isBrowserOpened){
				super.start();
				isBrowserOpened = true;
			}
		}else{
			super.start();
		}
	}
	@Override
	public void close() {
		if(!_openonce || _cyclecount % 20 == 0){
			super.close();
			isBrowserOpened = false;
		}
	}
	@Override
	public void stop() {
		if(!_openonce  || _cyclecount % 20 == 0){
			super.stop();
			isBrowserOpened = false;
		}
	}
	
	public ZKSelenium(CommandProcessor processor,boolean openonce) {
		super(processor);
		this._openonce=openonce;
	}
	public ZKSelenium(CommandProcessor processor,String browserpath,  String browserbrand, 
			String browsername,boolean openonce) {
		super(processor);
		this._openonce=openonce;
		_browserpath = browserpath;
		_browserbrand = browserbrand;
		_browsername = browsername;
	}
	public String getBrowserBrand() {
		return _browserbrand;
	}
	
	public String getBrowserName() {
		return _browsername;
	}
	
	public String getBrowserpath() {
		return _browserpath;
	}
	
	public CommandProcessor getCmdProcessor() {
	    return commandProcessor;
	}
	
	// fixed Chrome dragdropTo issue
	public void dragdropFrom(String locatorOfObjectToBeDragged, String from) {
		commandProcessor.doCommand("dragdropFrom", new String[] {locatorOfObjectToBeDragged,from,});
	}
	
	public void shutdown(){}
	
}
