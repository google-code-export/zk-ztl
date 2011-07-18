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
 * 
 * @author sam
 * 
 */
public class ConfigHelper {
	
	private final static String[] BROWSER_NAMES = new String[] { "firefox", "firefox4", "firefox5", "chrome", "safari" ,"safari5", "opera", "ie", "ie6",
			"ie7", "ie8" ,"ie9", "iexplore", "ff" };

	// private final static String[] PORTABLE_BROWSERS = new String[] {
	// "firefox" };

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
	
	private boolean _openonce = false;
	
	private Map<String, Integer[]> _frames = new HashMap<String, Integer[]>();
	
	// 2011-03-02. Edited by Phoenix.
	// Add properties for image comparing.
	private String _imgsrc;
	private String _imgdest;
	private boolean _comparable;
	private int _granularity, _leniency;

	private static ConfigHelper ch = new ConfigHelper();

	/**
	 * key : Firefox, IE ... value : *firefox, *iexplore ...
	 */
	private HashMap<String, String> _browserNameMap;

	private HashMap<String, String> _browserPathMap;

	private HashMap<String, String> _browserClient;

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
			e.printStackTrace();
		} catch (Exception e) {
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

	public String getBrowserBand(String key) {
		return _browserNameMap.get(key);
	}

	public Map<String, Integer[]> getFrameSize() {
		return _frames;
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
			// @Todo add multiple client support
			String browserpath = _browserPathMap.containsKey(key) ? _browserPathMap.get(key):"";
			browserpath = ("".equals(browserpath) ? "" : " " + browserpath);
			
			String browserBand = getBrowserBand(key) ;
			if (_browserClient.containsKey(key)) {
				browser = new ZKSelenium(new HttpCommandProcessor(_browserClient.get(key) + "/selenium-server/driver/",
						browserBand + browserpath, _server), browserpath, browserBand,key,_openonce);
			} else {
				browser = new ZKSelenium(new HttpCommandProcessor(_client + "/selenium-server/driver/", browserBand + browserpath,
						_server), browserpath , browserBand,key,_openonce);
			}
			System.out.println("connecting "+key);
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

			_browserNameMap.put("ff", "*firefox");
			_browserNameMap.put("firefox", "*firefox");
			_browserNameMap.put("firefox4", "*firefox");
			_browserNameMap.put("firefox5", "*firefox");
			_browserNameMap.put("ie", "*iexplore");
			_browserNameMap.put("ie6", "*iexplore");
			_browserNameMap.put("ie7", "*iexplore");
			_browserNameMap.put("ie8", "*iexplore");
			_browserNameMap.put("ie9", "*iexplore");
			_browserNameMap.put("chrome", "*googlechrome");
			_browserNameMap.put("safari", "*safariproxy");
			_browserNameMap.put("safari4", "*safari");
			_browserNameMap.put("safari5", "*safariproxy");
			_browserNameMap.put("opera", "*opera");
		}

		if (_browserPathMap == null) {
			_browserPathMap = new HashMap<String, String>();
		}
		if (_browserClient == null) {
			_browserClient = new HashMap<String, String>();
		}

		initProperty();
	}

	private void addBrowserNameSetting(String browserName, String browserPath) {
		/**
		 * String browser = null; for (String portable : BROWSER_NAMES) { if
		 * (browserName.toLowerCase().startsWith(portable)) { browser =
		 * portable; break; } } if (browser == null) return;
		 */
		browserName = browserName.toLowerCase();
		String setting = browserPath;
		if (browserPath.indexOf(";") != -1) {
			String[] tokens = browserPath.split(";");
			_browserClient.put(browserName, tokens[0]);
			if(tokens.length >1){
				browserPath = tokens[1];
			}else{
				browserPath = "";
			}
			setting = browserPath;
		}
		_browserPathMap.put(browserName, setting);
	}

	@SuppressWarnings("unchecked")
	private void initProperty() throws Exception, IOException {
		InputStream in = null;
		if (_prop == null) {
			try {
				in = ClassLoader.getSystemResourceAsStream("config.properties");
				_prop = new Properties();
				_prop.load(in);
				_openonce = Boolean.parseBoolean(_prop.getProperty("openonce","false"));
				System.out.println("openonce="+_openonce);
				_lastModified = new File(ClassLoader.getSystemResource("config.properties").getFile()).lastModified();
				_client = _prop.getProperty("client");
				_server = _prop.getProperty("server");
				_contextPath = _prop.getProperty("context-path");
				_action = _prop.getProperty("action");
				_delay = _prop.getProperty("delay");
				_browser = _prop.getProperty("browser");
				_timeout = _prop.getProperty("timeout");
				_imgsrc = _prop.getProperty("imgsrc");
				_imgdest = _prop.getProperty("imgdest");
				_comparable = Boolean.parseBoolean(_prop.getProperty("comparable", "false"));
				_granularity = Integer.parseInt(_prop.getProperty("granularity"));
				_leniency = Integer.parseInt(_prop.getProperty("leniency"));
				for (Iterator iter = _prop.entrySet().iterator(); iter.hasNext();) {
					final Map.Entry setting = (Map.Entry) iter.next();
					String strKey = (String) setting.getKey();
					if (isBrowserSetting(strKey)) {
						addBrowserNameSetting(strKey, (String) setting.getValue());
						continue;
					}
				}

				String[] allBrowsers = _prop.getProperty(ALL_BROWSERS).split(",");
				for (String browser : allBrowsers) {
					String browserKey = browser.trim();
					if (_browserNameMap.containsKey(browserKey)) {
						_allBrowsers.add(browserKey);
					}
					String frames = _prop.getProperty(browserKey + "-frame");
					if (frames != null) {
						Integer[] sizes = new Integer[4];
						String[] sz = frames.split(",");
						for (int i = 0; i < sz.length; i++)
							sizes[i] = Integer.parseInt(sz[i]);
						_frames.put(browserKey, sizes);
						System.out.println(browserKey + "-frame:" + Arrays.asList(sizes));
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
	 * TODO Logging untested yet
	 */
	protected static final String RESULT_FILE_ENCODING = "UTF-8";

	protected static final String SCREENSHOT_PATH = "screenshots";

	protected static final String RESULTS_BASE_PATH = "Log";

	protected String screenshotsResultsPath;

	private LoggingSelenium createLoggingSelenium(String browserType, BufferedWriter loggingWriter) {
		LoggingSelenium browser = new LoggingDefaultSelenium(createLoggingProcessor(browserType, loggingWriter));
		browser.setSpeed(getDelay());

		return browser;
	}

	private LoggingCommandProcessor createLoggingProcessor(String browserType, BufferedWriter loggingWriter) {
		screenshotsResultsPath = new File(RESULTS_BASE_PATH + File.separator + SCREENSHOT_PATH).getAbsolutePath();
		if (!new File(screenshotsResultsPath).exists()) {
			new File(screenshotsResultsPath).mkdirs();
		}

		LoggingResultsFormatter htmlFormatter = new HtmlResultFormatter(loggingWriter, RESULT_FILE_ENCODING);
		htmlFormatter.setScreenShotBaseUri(SCREENSHOT_PATH + "/");
		htmlFormatter.setAutomaticScreenshotPath(screenshotsResultsPath);
		LoggingCommandProcessor myProcessor = new LoggingCommandProcessor(new HttpCommandProcessor(getClient()
				+ "/selenium-server/driver/", browserType, getServer()), htmlFormatter);

		return myProcessor;
	}

	public List<LoggingSelenium> getLoggingBrowsers(String keys, BufferedWriter writer) {
		List<String> browser = Arrays.asList(keys.split(","));
		List<LoggingSelenium> list = new ArrayList<LoggingSelenium>();
		if (browser.contains(ALL_BROWSERS)) {
			for (String key : _allBrowsers)
				list.add(createLoggingSelenium(key, writer));
			return list;
		}
		for (String key : browser)
			list.add(createLoggingSelenium(key, writer));
		return list;
	}

	protected String getCaptureScreenshotPath() {
		return screenshotsResultsPath + File.separator + LoggingUtils.timeStampForFileName() + ".png";
	}

	protected BufferedWriter createLoggerWriter(String target) {
		String resultsPath = new File(RESULTS_BASE_PATH).getAbsolutePath();
		String resultHtmlFileName = resultsPath + File.separator + target + "_TestResult.html";
		if (!new File(resultHtmlFileName).exists()) {
			new File(resultHtmlFileName).mkdirs();
		}
		BufferedWriter loggingWriter = LoggingUtils.createWriter(resultHtmlFileName, RESULT_FILE_ENCODING, true);
		return loggingWriter;
	}

    /**
     * Returns the path of the image source directory.
     * <p>
     * Property name in config.properties: <b>imgsrc</b>, which means the source
     * directory of the base image.
     */
	public String getImageSrc() {
        return _imgsrc;
    }

    /**
     * Returns the path of the image destination directory.
     * <p>
     * Property name in config.properties: <b>imgdest</b>, which means the destination
     * directory of the compared result, if fails.
     */
	public String getImageDest() {
        return _imgdest;
    }

    /**
     * Returns whether in a comparable mode.
     * <p>default: false.
     * Property name in config.properties: <b>comparable</b>, which means the image
     * comparison is in a comparable mode, if true specified. Otherwise, the image
     * is stored to the imgsrc directory as the base images.
     * 
     */
	public boolean isComparable() {
        return _comparable;
    }
	
	/**
	 * Returns the granularity for each comparing section.
	 * <p>
     * Property name in config.properties: <b>granularity</b>
	 * <p> It is better to have 1~15, less is a precise comparison, but performance
	 * is slow. Don't specify too high, it may compare without any different.
	 */
	public int getGranularity() {
		return _granularity;
	}
	
	/**
	 * Returns the leniency for each comparing section.
	 * <p>
     * Property name in config.properties: <b>leniency</b>
	 * <p> It is better to have 1~10, less is a precise comparison.
	 */
	public int getLeniency() {
		return _leniency;
	}

}
