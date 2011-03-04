package org.zkoss.ztl.util.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import org.zkoss.ztl.util.ImageUtil;

public class State {

    public int[][] map;
    public int width;
    public int height;
    public int average;
    public Image _img;
    
    public State(BufferedImage img, int resX, int resY) {
        // setup brightness map
        width = (int)(img.getWidth() / resX);
        height = (int)(img.getHeight() / resY);
        map = new int[height][width];
        _img = img;
        
        // build map and stats
        average = 0;
        int ta = 0;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ta = (int)(100 * ImageUtil.getBrightnessAtPoint(img, x * resX, y * resY));
                map[y][x] = ta;
                average += ta;
            }
        }
        
        average = (int)(average / (width * height));
    }
    
}