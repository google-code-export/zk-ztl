package org.zkoss.ztl.util.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Phoenix
 *
 */
public class Comparer {

    protected int comparex;
    protected int comparey;
    protected int leniency;
    protected int debugMode; // 1: textual indication of change, 2: difference of factors

    public Comparer(int comparex, int comparey, int leniency) {
        this.comparex = comparex;
        this.comparey = comparey;
        this.leniency = leniency;
        debugMode = 0;
    }

    protected BufferedImage imgc = null;
    
    public BufferedImage getChangeIndicator() {
        return imgc;
    }
    // buffered images are just better.
    protected static BufferedImage imageToBufferedImage(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, null, null);
        return bi;
    }
    // compare two images.
    public Comparison compare(State s1, State s2) {
        imgc = imageToBufferedImage(s2.getImg());
        Graphics2D gc = imgc.createGraphics();
        gc.setColor(Color.RED);
        int cx = comparex;
        if (cx > s1.getWidth()) cx = s1.getWidth();
        int cy = comparey;
        if (cy > s1.getHeight()) cy = s1.getHeight();
        
        // how many points per section
        int bx = (int)(Math.floor(s1.getWidth() / cx));
        if (bx <= 0) bx = 1;
        int by = (int)(Math.floor(s1.getHeight() / cy));
        if (by <= 0) by = 1;
        int[][] variance = new int[cy][cx];
        
        // set to a match by default, if a change is found then flag non-match
        boolean match = true;
        // loop through whole image and compare individual blocks of images
        int ty = 0;
        for (int y = 0; y < cy; y++) {
            if (debugMode > 0) System.out.print("|");
            ty = y*by;
            for (int x = 0; x < cx; x++) {
                int b1 = aggregateMapArea(s1.getMap(), x*bx, ty, bx, by);
                int b2 = aggregateMapArea(s2.getMap(), x*bx, ty, bx, by);
                int diff = Math.abs(b1 - b2);
                variance[y][x] = diff; 
                if (diff > leniency) { // the difference in a certain region has passed the threshold value
                    gc.drawRect(x*bx, y*by, bx - 1, by - 1);
                    match = false;
                }
                if (debugMode == 1) System.out.print((diff > leniency ? "X" : " "));
                if (debugMode == 2) System.out.print(diff + (x < cx - 1 ? "," : ""));
            }
            if (debugMode > 0) System.out.println("|");
        }
        return new Comparison(s1, s2, variance, match);
    }
    
    private int aggregateMapArea(int[][] map, int ox, int oy, int w, int h) {
        int t = 0;
        for (int i = 0; i < h; i++) {
            int ty = oy+i;
            for (int j = 0; j < w; j++)
                t += map[ty][ox+j];
        }
        return (int)(t/(w*h));
    }

}
