/* ZtlGenerator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mar 3, 2010 7:06:20 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.ztl.util;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.zkoss.ztl.Tags;
import org.zkoss.ztl.parser.SAXParser;

/**
 * The ZTL generator that generates the Java class from ZTL file to Selenium test case.
 * <p>
 * Usage:
 * <ol>
 * <li>
 * Generate the java class. <br/>
 * java org.zkoss.ztl.util.ZtlGenerator -src ./test -dest ./codegen
 * </li>
 * <li>
 * Run all of test cases that match with the tags<br/>
 * java org.zkoss.ztl.util.ZtlGenerator -run [srcdir] [grid,listbox,button...]
 * </li>
 *</ol>
 * 
 * @author jumperchen
 */
public class ZtlGenerator {
	private static void log(Object... os) {
		for (Object o : os) {
			System.out.print(o + (os[os.length - 1] != o ? "," : ""));
		}
		System.out.println();
	}

	private void run(Test t, String dist) {
		if (t == null) {
			log("Test cannot be null!");
			return;
		}
		try {
			final String destdir = dist.replace("/", File.separator);
			Velocity.init(ClassLoader.getSystemResource("velocity.properties")
					.getPath());

			String PackageName = t.getPackage();
			String FileName = t.getFileName();
			String path = destdir + File.separator
					+ PackageName.replace(".", File.separator) + File.separator
					+ FileName + ".java";

			File dir = new File(path);
			dir.getParentFile().mkdirs();
			if (dir.isFile()) {
				if (dir.lastModified() > t.lastModified())
					return;
			} else {
				dir.createNewFile();
			}

			VelocityContext context = new VelocityContext();
			context.put("ztl", t);

			SimpleDateFormat sdf = new SimpleDateFormat(
					"MMM, d, yyyy HH:mm:ss a");
			context.put("CreateTime", sdf.format(new Date()));
			sdf = new SimpleDateFormat("yyyy");
			context.put("Year", sdf.format(new Date()));

			Template template = null;

			try {
				template = Velocity.getTemplate("ztl.vm");
			} catch (ResourceNotFoundException rnfe) {
				log("Example : error : cannot find template ");
			} catch (ParseErrorException pee) {
				log("Example : Syntax error in template:" + pee);
			}

			FileWriter writer = new FileWriter(dir);

			if (template != null)
				template.merge(context, writer);

			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Test load(File f, String src) {
		try {
			String fileName = f.getName();
			fileName = fileName.substring(0, fileName.indexOf("."));
			fileName = fileName.replace("-", "_");
			String pkg = f.getParent();
			int index = pkg.indexOf(src);
			if (index == 0)
				pkg = pkg.substring(src.length());
			if (pkg.length() > 0)
				pkg = pkg.replace(File.separator, ".").substring(1);
			ConfigHelper ch = ConfigHelper.getInstance();
			
			/** config filed ***/
			Test test = SAXParser.parser(f);
			
			// if config.properties is changed, we need to regenerate Java.
			test.setLastModified(f.lastModified() < ch.lastModified() ?
					ch.lastModified(): f.lastModified());
			test.setFileName(fileName);
			test.setServer(ch.getServer());
			test.setPackage(pkg);
			test.setContextPath(ch.getContextPath());
			test.setAction(ch.getAction());
			test.setDelay(ch.getDelay());
			test.setTimeout(ch.getTimeout());
			test.setBrowser(ch.getBrowser());
			return test;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<File> getFiles(File dir, List<File> list, String fileType) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				getFiles(f, list, fileType);
			}
		} else if (dir.getName().endsWith(fileType)
				&& !dir.getName().equals(fileType))
			list.add(dir);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String src = null, dist = null, tags = "", bin = "";
		boolean run = false;
		int i = 0;
		for (String s : args) {
			if ("-src".equalsIgnoreCase(s)) {
				src = args[i + 1];
				log("src=" + src);
			} else if ("-dist".equalsIgnoreCase(s)) {
				dist = args[i + 1];
				log("dist=" + dist);
			} else if ("-run".equalsIgnoreCase(s)) {
				run = true;
				bin = args[i + 1];
				tags = args[i + 2];
				if (tags.toLowerCase().endsWith(".ztl"))
					log("bin=" + bin, "case=" + tags);
				else
					log("bin=" + bin, "tags=" + tags);
			}
			i++;
		}

		if (!run && src == null && dist == null) {
			log("\n");
			log("Usage:\n");
			log("1.Generate the java class\n");
			log("	java org.zkoss.ztl.util.ZtlGenerator -src ./test -dest ./codegen");
			log("\n2.Or run with tags\n");
			log("	java org.zkoss.ztl.util.ZtlGenerator -run [srcdir] [grid,listbox,button...]");
			log("\n3.Or run with case\n");
			log("	java org.zkoss.ztl.util.ZtlGenerator -run [srcdir] [B123456.ztl,...]");
			log("\n");
			System.exit(-1);
		}
		if (!run) {
			ZtlGenerator t = new ZtlGenerator();
			File dir = new File(src);
			for (File f : getFiles(dir, new ArrayList<File>(30), ".ztl"))
				t.run(t.load(f, dir.getPath()), dist);
		} else {
			try {
				tags = tags.toLowerCase();
				String[] tag = tags.split(",");
				List<File> files = getFiles(new File(bin), new ArrayList<File>(
						30), "Test.class");
				if (tags.endsWith(".ztl")) {
					Map<String, String> map = new HashMap<String, String>(tag.length);
					for (String nm : tag) {
						map.put(nm.replace("-", "_").replace(".ztl", "") + "test", "");
					}
					boolean foundAll = false;
					for (File f : files) {
						String pkg = f.getParent().replace(bin, "");
						if (pkg.length() > 0)
							pkg = pkg.replace(File.separator, ".") + '.';
						int b = pkg.indexOf("org.zkoss");
						if (b > 0)
							pkg = pkg.substring(b);
						String name = f.getName().replace(".class", "");
						if (map.containsKey(name.toLowerCase())) {
							Class c = Class.forName(pkg
									+ f.getName().replace(".class", ""));
							log(c);
							junit.textui.TestRunner.run(c);
							foundAll = true;
						}
					}
					if (!foundAll)
						log("Not found the case [" + tag + "]");
				} else {
					boolean foundAll = false;
					for (File f : files) {
						String pkg = f.getParent().replace(bin, "");
						if (pkg.length() > 0)
							pkg = pkg.replace(File.separator, ".") + '.';
						int b = pkg.indexOf("org.zkoss");
						if (b > 0)
							pkg = pkg.substring(b);
						Class c = Class.forName(pkg
								+ f.getName().replace(".class", ""));
						Tags atags = ((Tags) c.getAnnotation(Tags.class));
						if (atags == null)
							continue;
						String keys = atags.tags();
						if (keys == null)
							continue;
						keys = keys.toLowerCase();
						boolean found = true;
						for (String t : tag) {
							if (keys.indexOf(t) == -1) {
								found = false;
								break;
							}
						}
						if (found) {
							foundAll = true;
							log(c);
							junit.textui.TestRunner.run(c);
						}
					}
					if (!foundAll)
						log("Not found the tags [" + tags + "]");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}