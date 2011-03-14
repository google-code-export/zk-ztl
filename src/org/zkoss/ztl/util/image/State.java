package org.zkoss.ztl.util.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import org.zkoss.ztl.util.ImageUtil;

public class State {

    private int[][] map;
    private int width;
    private int height;
    private int average;
    private Image _img;
    
    public State(BufferedImage img, int resX, int resY) {
        // setup brightness map
        setWidth((int)(img.getWidth() / resX));
        setHeight((int)(img.getHeight() / resY));
        setMap(new int[getHeight()][getWidth()]);
        setImg(img);
        
        // build map and stats
        setAverage(0);
        int ta = 0;
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                ta = (int)(100 * ImageUtil.getBrightnessAtPoint(img, x * resX, y * resY));
                getMap()[y][x] = ta;
                setAverage(getAverage() + ta);
            }
        }
        
        setAverage((int)(getAverage() / (getWidth() * getHeight())));
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMap() {
        return map;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getAverage() {
        return average;
    }

    public void setImg(Image _img) {
        this._img = _img;
    }

    public Image getImg() {
        return _img;
    }
    
}