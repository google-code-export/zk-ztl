package org.zkoss.ztl.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * A util which handles images.
 * 
 * @author phoenix
 * 
 */
public class ImageUtil {
    public static BufferedImage imageToBufferedImage(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, null, null);
        return bi;
    }

    // -------------------------- image related funcions ---------------------------

    public static float getBrightnessAtPoint(BufferedImage img, int x, int y) {
        return getColorFactor(new Color(img.getRGB(x, y)));
    }

    public static float getColorFactor(Color c) {
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        return (float) (hsb[2] * 0.5 + ((hsb[0] / 360) * 50/* 1/2 of 100 */));
    }

    // returns a value specifying some kind of average brightness in the image.
    public static int getAverageBrightness(BufferedImage img) {
        Raster r = img.getData();
        int total = 0;
        for (int y = 0; y < r.getHeight(); y++) {
            for (int x = 0; x < r.getWidth(); x++) {
                total += r.getSample(r.getMinX() + x, r.getMinY() + y, 0);
            }
        }
        return (int) (total / (r.getWidth() * r.getHeight()));
    }

    // write a buffered image to a jpeg file.
    public static void saveJPG(BufferedImage bi, String filename) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        } catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
        }
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
        param.setQuality(0.8f, false);
        encoder.setJPEGEncodeParam(param);
        try {
            encoder.encode(bi);
            out.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
        }
    }

    // read a jpeg file into a buffered image
    public static BufferedImage loadJPG(String filename) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(filename);
        } catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
        }
        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
        BufferedImage bi = null;
        try {
            bi = decoder.decodeAsBufferedImage();
            in.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
        }
        return bi;
    }

    // -------------------------- state / statistical related funcions
    // ---------------------------

    // returns a value avg value of an integer array.
    public static int intArrayStdDev(int[][] m) {
        int v = 0;
        int v2 = 0;
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                v += m[y][x];
                v2 += (m[y][x] * m[y][x]);
            }
        }
        int c = (m.length * m[0].length);
        return (int) (((v2 - ((v * v) / c))) / c);
    }

    // returns a value avg value of an integer array.
    public static int intArrayAvg(int[][] m) {
        int total = 0;
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                total += m[y][x];
            }
        }
        return (int) (total / (m.length * m[0].length));
    }

    // returns a value avg value of an integer array.
    public static int intArrayAvgNonZero(int[][] m) {
        int total = 0;
        int count = 0;
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] != 0) {
                    total += m[y][x];
                    count++;
                }
            }
        }
        if (count == 0)
            count = 1;
        return (int) (total / count);
    }

    // -------------------------- array output related funcions ---------------------------

    // simply output the contents of a 2d array to sysout
    public static void outputIntArray(int[][] m) {
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                System.out.print(m[y][x] + ",");
            }
            System.out.println();
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void outputIntArrayThreshold(int[][] m, int v, int f) {
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                System.out.print((m[y][x] * f > v ? "X" : " "));
            }
            System.out.println();
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void outputIntArrayThresholdStdDev(int[][] m) {
        int sd = intArrayStdDev(m);
        int avg = intArrayAvgNonZero(m);
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                System.out.print((((m[y][x] < avg - sd) || (m[y][x] > avg + sd)) ? "X" : " "));
            }
            System.out.println();
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void renderIntArrayToGraphicsDevice(int[][] m, int v, int f) {
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                System.out.print((m[y][x] * f > v ? "X" : " "));
            }
            System.out.println();
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void renderIntArrayToGraphicsDevice(int[][] m, int width, int height, java.awt.Graphics g) {
        renderIntArrayToGraphicsDevice(m, width, height, g, 1);
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void renderIntArrayToGraphicsDevice(int[][] m, int width, int height, java.awt.Graphics g, double factor) {
        g.setColor(Color.RED);

        float bx = (width / m[0].length);
        float by = (height / m.length);
        int rx = (int) (bx / 2);
        int ry = (int) (by / 2);

        for (int y = 0; y < m.length; y++) {
            int oy = (int) (y * by) + ry;
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] > 0) {
                    int ox = (int) (x * bx) + rx;
                    int radius = (int) (m[y][x] / (2 * factor));
                    int diameter = (int) (m[y][x] / factor);
                    g.drawOval(ox + -radius, oy - radius, diameter, diameter);
                }
            }
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void renderIntArrayCOGToGraphicsDevice(int[][] m, int width, int height, java.awt.Graphics g, double factor) {
        g.setColor(Color.GREEN);

        float bx = (width / m[0].length);
        float by = (height / m.length);
        int rx = (int) (bx / 2);
        int ry = (int) (by / 2);

        int mx = 0;
        int my = 0;
        int div = 0;

        // loop and calculate total mass on the y and on the x
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] > 0) {
                    mx += (m[y][x] * x);
                    my += (m[y][x] * y);
                    div += m[y][x];
                }
            }
        }
        if (div > 0) {
            mx = (int) (mx / div);
            my = (int) (my / div);

            int oy = (int) (my * by) + ry;
            int ox = (int) (mx * bx) + rx;
            int radius = (int) (20 / (2 * factor));
            int diameter = (int) (20 / factor);
            g.drawOval(ox - radius, oy - radius, diameter, diameter);
            g.drawOval(ox - radius - 2, oy - radius - 2, diameter + 4, diameter + 4);
        }
    }

    // output the contents of a 2d array to sysout using a value as a rule of
    // output
    public static void renderIntArrayStatsToGraphicsDevice(int[][] m, java.awt.Graphics g) {
        int v = 0;
        int v2 = 0;
        int total = 0;
        int countnz = 0;
        int max = 0;
        int min = 0;

        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                v += m[y][x];
                v2 += (m[y][x] * m[y][x]);
                total += m[y][x];
                if (m[y][x] != 0) {
                    countnz += m[y][x];
                }
                if (m[y][x] > max)
                    max = m[y][x];
                if (m[y][x] < min)
                    min = m[y][x];
            }
        }

        int count = (m.length * m[0].length);
        int variance = (int) (((v2 - ((v * v) / count))) / count);
        int average = (int) (total / count);
        int averagenz = (int) (total / (countnz == 0 ? 1 : countnz));

        g.setColor(Color.WHITE);
        g.drawString("min: " + min, 6, 16);
        g.drawString("avg: " + average, 6, 31);
        g.drawString("avg-non zero: " + averagenz, 6, 46);
        g.drawString("max: " + max, 6, 61);
        g.drawString("std-dev: " + variance, 6, 76);
        g.drawString("num non zero: " + countnz, 6, 91);
        g.drawString("pix-count: " + count, 6, 106);

        g.setColor(Color.BLACK);
        g.drawString("min: " + min, 5, 15);
        g.drawString("avg: " + average, 5, 30);
        g.drawString("avg-non zero: " + averagenz, 5, 45);
        g.drawString("max: " + max, 5, 60);
        g.drawString("std-dev: " + variance, 5, 75);
        g.drawString("num non zero: " + countnz, 5, 90);
        g.drawString("pix-count: " + count, 5, 105);

    }

    // a silly way of storing an integer array
    public static String sillyStoreIntArray(int[][] m) {
        String output = "";
        int count = 0;
        int last = m[0][0];
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] == last) {
                    count++;
                } else {
                    output += (output == "" ? "" : ",") + last + (count > 1 ? ":" + count : "");
                    count = 1;
                    last = m[y][x];
                }
            }
        }
        output += "," + last + (count > 1 ? ":" + count : "");
        return output;
    }

    // a silly way of storing an integer array
    public static int[][] sillyRetrieveIntArray(String input, int width, int height) {
        int[][] output = new int[height][width];

        int x = 0;
        int y = 0;

        try {
            java.util.StringTokenizer tok = new StringTokenizer(input, ",");
            while (tok.hasMoreTokens()) {
                String token = tok.nextToken();
                int iot = token.indexOf(':');
                if (iot > 0) {
                    int value = Integer.parseInt(token.substring(0, iot));
                    int count = Integer.parseInt(token.substring(iot + 1));
                    for (int i = 0; i < count; i++) {
                        output[y][x++] = value;
                        if (x >= width) {
                            x = 0;
                            y++;
                        }
                    }
                } else {
                    output[y][x++] = Integer.parseInt(token);
                }
                if (x >= width) {
                    x = 0;
                    y++;
                }
            }
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    // ------------------------------------ array manipulations ---------------------------
    // convert a bunch of ints to doubles
    public static double[][] intArrayToDoubleArray(int[][] m) {
        double[][] t = new double[m.length][m[0].length];
        for (int y = 0; y < m.length; y++)
            for (int x = 0; x < m[0].length; x++)
                t[y][x] = m[y][x];
        return t;
    }

    // returns a new array where values dont meet a threshold value
    public static int[][] intArrayZeroFillUnderThreshold(int[][] m, int v, int f) {
        int[][] t = new int[m.length][m[0].length];
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                if (m[y][x] * f > v) {
                    t[y][x] = m[y][x];
                } else {
                    t[y][x] = 0;
                }
            }
        }
        return t;
    }

    // returns a new array where islands of single values with no neighbours
    // have been removed
    // to quicken this up im not checking values on the edge. they just get 0.
    public static int[][] intArrayRemoveNeighbourless(int[][] m) {
        int[][] t = new int[m.length][m[0].length];
        for (int y = 0; y < m.length; y++) {
            boolean yf = ((y > 0) && (y < m.length - 1));
            for (int x = 0; x < m[0].length; x++) {
                boolean xf = ((x > 0) && x < (m[0].length - 1));
                if ((yf && xf) && (m[y][x] != 0)) {
                    if (((m[y - 1][x - 1] != 0) || (m[y - 1][x] != 0) || (m[y - 1][x + 1] != 0)) || ((m[y][x - 1] != 0) || (m[y][x + 1] != 0)) || ((m[y + 1][x - 1] != 0) || (m[y + 1][x] != 0) || (m[y + 1][x + 1] != 0))) {
                        t[y][x] = m[y][x];
                    } else {
                        t[y][x] = 0;
                    }
                } else {
                    t[y][x] = 0;
                }
            }
        }
        return t;
    }

    // ------------------------------- really arb funcions ------------------------------

    public static String getTimeStamp() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return "" + c.get(c.YEAR) + (c.get(c.MONTH) + 1 < 10 ? "0" : "") + (c.get(c.MONTH) + 1) + (c.get(c.DAY_OF_MONTH) < 10 ? "0" : "") + c.get(c.DAY_OF_MONTH) + (c.get(c.HOUR_OF_DAY) < 10 ? "0" : "") + c.get(c.HOUR_OF_DAY) + (c.get(c.MINUTE) < 10 ? "0" : "") + c.get(c.MINUTE)
                + (c.get(c.SECOND) < 10 ? "0" : "") + c.get(c.SECOND);
    }

    public static Properties loadProperties() {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("settings.ini"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }

    /**
     * Replaces all occurunces of a string with another.
     */
    public static String replaceAll(String target, String from, String to) {
        // returns a new String!
        int start = target.indexOf(from);
        if (start == -1)
            return target;
        int lf = from.length();
        char[] targetChars = target.toCharArray();
        StringBuffer buffer = new StringBuffer();
        int copyFrom = 0;
        while (start != -1) {
            buffer.append(targetChars, copyFrom, start - copyFrom);
            buffer.append(to);
            copyFrom = start + lf;
            start = target.indexOf(from, copyFrom);
        }
        buffer.append(targetChars, copyFrom, targetChars.length - copyFrom);
        return buffer.toString();
    }

    /**
     * Write the contents of a string object into a text file.
     * 
     * @param text
     * @param filename
     * @return boolean
     */
    public static boolean writeToTextFile(String text, String filename) {
        FileWriter out = null;
        try {
            out = new FileWriter(filename);
        } catch (java.io.FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            return false;
        } catch (java.io.IOException io) {
            io.printStackTrace();
            return false;
        }
        try {
            out.write(text);
            out.flush();
            out.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
            return false;
        }
        return true;
    }

    /**
     * Read the entire contents of a text file.
     * 
     * @param filename
     * @return String
     */
    public static String readTextFromFile(String filename) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
            return null;
        }
        String text = "";
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                text += line + "\n";
            }
            in.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
            return null;
        }
        return text;
    }

    public static Object readObjectFromFile(String fileName) {
        Object o = null;
        try {
            FileInputStream stream = new FileInputStream(fileName);
            ObjectInput input = new ObjectInputStream(stream);
            o = input.readObject();
            input.close();
            stream.close();
        } catch (Exception e) {
            System.out.println("Exception was thrown. Message is : " + e.getMessage());
        }
        return o;
    }

    public static void writeObjectToFile(Object o, String fileName) {
        try {
            FileOutputStream stream = new FileOutputStream(fileName);
            ObjectOutput output = new ObjectOutputStream(stream);
            output.writeObject(o);
            output.close();
            stream.close();
        } catch (Exception e) {
            System.out.println("Exception was thrown. Message is : " + e.getMessage());
        }
    }
}
