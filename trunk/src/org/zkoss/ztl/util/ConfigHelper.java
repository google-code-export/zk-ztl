/* ConfigHelper.java

	Purpose:
		
	Description:
		
	History:
		Wed Sep 16 12:49:43 TST 2009, Created by sam

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

This program is distributed under GPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
 */
package org.zkoss.ztl.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import com.unitedinternet.portal.selenium.utils.logging.HtmlResultFormatter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingCommandProcessor;
import com.unitedinternet.portal.selenium.utils.logging.LoggingDefaultSelenium;
import com.unitedinternet.portal.selenium.utils.logging.LoggingResultsFormatter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingSelenium;
import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;

/**
 * ZTL configuration helper.
 * @author sam
 * 
 */
public class ConfigHelper {

	private final static String[] BROWSER_NAMES = new String[] { "firefox",
			"chrome", "safari", "opera", "ie", "iexplore", "ff" };

	//private final static String[] PORTABLE_BROWSERS = new String[] { "firefox" };

	private final static String ALL_BROWSERS = "all";
	private static List<String> _allBrowsers = new LinkedList<String>();

	private String _client;
	private String _server;
	private String _contextPath;
	private String _action;
	private String _timeout;
	private String _delay;
	private String _browser;
	private Properties _prop;
	private long _lastModified;
	private static ConfigHelper ch = new ConfigHelper();
	/**
	 * key : Firefox, IE ... value : *firefox, *iexplore ...
	 */
	private HashMap<String, String> _browserNameMap;

	/**
	 * key : Firefox, IE ... value : Selenium browser
	 */
	private HashMap<String, Selenium> _browserHolder = new HashMap<String, Selenium>();

	private ConfigHelper() {
	}
	
	public static ConfigHelper getInstance() {
		try {
			ch.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ch;
	}
	public String getClient() {
		return _client;
	}

	public String getServer() {
		return _server;
	}

	public String getContextPath() {
		return _contextPath;
	}

	public String getAction() {
		return _action;
	}

	public String getDelay() {
		return _delay;
	}

	public String getTimeout() {
		return _timeout;
	}

	public String getBrowser() {
		return _browser;
	}
	
	public long lastModified() {
		return _lastModified;
	}

	/**
	 * 
	 * @param key
	 * @return value :
	 */
	private Selenium getBrowserFromHolder(String key) {
		key = key.toLowerCase();
		if (_browserNameMap.get(key) == null)
			throw new NullPointerException("Null Browser Type String");

		Selenium browser = _browserHolder.get(key);
		if (browser == null) {
			browser = new DefaultSelenium(new HttpCommandProcessor(_client
					+ "/selenium-server/driver/", _browserNameMap.get(key),
					_server));
			browser.setSpeed(getDelay());
			_browserHolder.put(key, browser);
		}
		return browser;
	}
	
	public List<Selenium> getBrowsers(String keys) {
		List<String> browser = Arrays.asList(keys.split(","));
		List<Selenium> list = new ArrayList<Selenium>();
		if (browser.contains(ALL_BROWSERS)) {
			for (String key : _allBrowsers)
				list.add(getBrowserFromHolder(key));
			return list;
		}
		for (String key : browser)
			list.add(getBrowserFromHolder(key));
		return list;
	}
	
	private void init() throws IOException, Exception {
		if (_browserNameMap == null) {
			_browserNameMap = new HashMap<String, String>();

			_browserNameMap.put("firefox", "*firefox");
			_browserNameMap.put("ie", "*iexplore");
			_browserNameMap.put("chrome", "*googlechrome");
			_browserNameMap.put("safari", "*safari");
			_browserNameMap.put("opera", "*opera");
		}

		initProperty();
	}

	private void addBrowserNameSetting(String browserName,
			String browserPath) {
		/**
		String browser = null;
		for (String portable : BROWSER_NAMES) {
			if (browserName.toLowerCase().startsWith(portable)) {
				browser = portable;
				break;
			}
		}
		if (browser == null)
			return;
		*/
		browserName = browserName.toLowerCase();
		String setting = "*" + browserName + " " + browserPath;
		_browserNameMap.put(browserName, setting);
	}

	@SuppressWarnings("unchecked")
	private void initProperty() throws Exception, IOException {
		InputStream in = null;
		if (_prop == null) {
			try {
				in = ClassLoader.getSystemResourceAsStream("config.properties");
				_prop = new Properties();
				_prop.load(in);
				_lastModified = new File(ClassLoader.getSystemResource("config.properties").getFile()).lastModified();
				_client = _prop.getProperty("client");
				_server = _prop.getProperty("server");
				_contextPath = _prop.getProperty("context-path");
				_action = _prop.getProperty("action");
				_delay = _prop.getProperty("delay");
				_browser = _prop.getProperty("browser");
				_timeout = _prop.getProperty("timeout");

				for (Iterator iter = _prop.entrySet().iterator(); iter
						.hasNext();) {
					final Map.Entry setting = (Map.Entry) iter.next();
					String strKey = (String) setting.getKey();
					if (isBrowserSetting(strKey)) {
						addBrowserNameSetting(strKey, (String) setting
								.getValue());
						continue;
					}
				}

				String[] allBrowsers = _prop.getProperty(ALL_BROWSERS).split(
						",");
				for (String browser : allBrowsers) {
					String browserKey = browser.trim();
					if (_browserNameMap.containsKey(browserKey)) {
						_allBrowsers.add(browserKey);
					}
				}
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
	}

	private boolean isBrowserSetting(String str) {
		for (String browserStr : BROWSER_NAMES) {
			if (str.toLowerCase().startsWith(browserStr))
				return true;
		}
		return false;
	}

	
	/**
	 *  TODO Logging untested yet
	 */
	protected static final String RESULT_FILE_ENCODING = "UTF-8";
	protected static final String SCREENSHOT_PATH = "screenshots";
	protected static final String RESULTS_BASE_PATH = "Log";
	protected String screenshotsResultsPath;
	
	private LoggingSelenium createLoggingSelenium(String browserType, BufferedWriter loggingWriter){
		LoggingSelenium browser = new LoggingDefaultSelenium(createLoggingProcessor(browserType, loggingWriter));
		browser.setSpeed(getDelay());
		
		return browser;
	}
	
	private LoggingCommandProcessor createLoggingProcessor(String browserType, BufferedWriter loggingWriter){
		screenshotsResultsPath = new File(RESULTS_BASE_PATH + File.separator + SCREENSHOT_PATH).getAbsolutePath();
		if (!new File(screenshotsResultsPath).exists()) {
			 new File(screenshotsResultsPath).mkdirs();
		}
		
		LoggingResultsFormatter htmlFormatter = new HtmlResultFormatter(loggingWriter, RESULT_FILE_ENCODING);
		htmlFormatter.setScreenShotBaseUri(SCREENSHOT_PATH+"/");
		htmlFormatter.setAutomaticScreenshotPath(screenshotsResultsPath);
		LoggingCommandProcessor myProcessor = 
			new LoggingCommandProcessor(new HttpCommandProcessor(getClient() + "/selenium-server/driver/",
																 browserType,
																 getServer()), htmlFormatter);
		
		return myProcessor;
	}

	
	public List<LoggingSelenium> getLoggingBrowsers(String keys, BufferedWriter writer){
		List<String> browser = Arrays.asList(keys.split(","));
		List<LoggingSelenium> list = new ArrayList<LoggingSelenium>();
		if (browser.contains(ALL_BROWSERS)) {
			for (String key : _allBrowsers)
				list.add(createLoggingSelenium(key, writer ));
			return list;
		}
		for (String key : browser)
			list.add(createLoggingSelenium(key, writer));
		return list;
	}
	
	
	protected String getCaptureScreenshotPath(){
		return screenshotsResultsPath + File.separator + LoggingUtils.timeStampForFileName() + ".png";
	}
	
	protected BufferedWriter createLoggerWriter(String target){
		String resultsPath = new File(RESULTS_BASE_PATH).getAbsolutePath();
		String resultHtmlFileName = resultsPath + File.separator + target + "_TestResult.html";
		if (!new File(resultHtmlFileName).exists()) {
			 new File(resultHtmlFileName).mkdirs();
		}
		BufferedWriter loggingWriter = LoggingUtils.createWriter(resultHtmlFileName,
																  RESULT_FILE_ENCODING, true);
		return loggingWriter;
	}

}
