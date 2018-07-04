/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sydney
 */
public class SweeperUtil {
    public static final Color SBLUE = new Color(66, 134, 244);
    private static final Color SBLUE_DARK = new Color(56,114,207);
    private static final Color SBLUE_LIGHT = new Color(76,154,255);
    
    public static void test(){
        trapizoid(0,0, 1, 5, 40);
        trapizoid(0,40, 0, 5, 40);
        trapizoid(40,0, 2, 5, 40);
        trapizoid(0,0, 3, 5, 40);
    }
    public static void bevelborder(Graphics g, int size, int thick, boolean outsset){
        bevelborder(g,size, size, thick, outsset);
    }
    public static void bevelborder(Graphics g, int width, int height, int thick, boolean outset){
        Polygon top = trapizoid(0,0, 1, thick, width);
        Polygon left = trapizoid(0,0, 3, thick, height);
        Polygon bottom = trapizoid(0,height, 0, thick, width);
        Polygon right = trapizoid(width,0, 2, thick, height);
        Color topleft = (outset)? SBLUE_LIGHT:SBLUE_DARK;
        Color bottomRight= (outset)? SBLUE_DARK:SBLUE_LIGHT;
        g.setColor(topleft);
        g.fillPolygon(left);
        g.fillPolygon(top);
        g.setColor(bottomRight);
        g.fillPolygon(right);
        g.fillPolygon(bottom);
        g.setColor(SBLUE);
        g.fillRect(thick, thick, width-(2*thick), height-(2*thick));
    }
    public static void border(Graphics g, int size, int thick){
        border(g,size, size, thick);
    }
    public static void border(Graphics g, int width, int height, int thick){
        Polygon top = trapizoid(0,0, 1, thick, width);
        Polygon left = trapizoid(0,0, 3, thick, height);
        Polygon bottom = trapizoid(0,height, 0, thick, width);
        Polygon right = trapizoid(width,0, 2, thick, height);
        g.setColor(Color.WHITE);
        g.fillPolygon(left);
        g.fillPolygon(top);
        g.fillPolygon(right);
        g.fillPolygon(bottom);
    }
    private static Polygon trapizoid(int sx, int sy, int dir, int thick, int len){
        String str = "";
        int innerlen = len-(2*thick);
        int[] y = new int[4];
        int[] x = new int[4];
        x[0]=sx;
        y[0]=sy;
        int tmpx;
        int tmpy;
        if(dir == 0){
            //up
            tmpx = sx+thick;
            tmpy = sy-thick;
            x[1]=tmpx;
            y[1]=tmpy;
            x[2]=tmpx+innerlen;
            y[2]=tmpy;
            x[3]=sx+len;
            y[3]=sy;
            
        }
        else if(dir == 1){
            //down
            tmpx = sx+thick;
            tmpy = sy+thick;
            x[1]=tmpx;
            y[1]=tmpy;
            x[2]=tmpx+innerlen;
            y[2]=tmpy;
            x[3]=sx+len;
            y[3]=sy;
        }
        else if(dir == 2){
            //left
            tmpx = sx-thick;
            tmpy = sy+thick;
            x[1]=tmpx;
            y[1]=tmpy;
            x[2]=tmpx;
            y[2]=tmpy+innerlen;
            x[3]=sx;
            y[3]=sy+len;
        }
        else if(dir == 3){
            //right
            tmpx = sx+thick;
            tmpy = sy+thick;
            x[1]=tmpx;
            y[1]=tmpy;
            x[2]=tmpx;
            y[2]=tmpy+innerlen;
            x[3]=sx;
            y[3]=sy+len;
        }
        return new Polygon(x,y,4);
    }
    public static void centerTXT(Graphics g, int width, int hieght, String str, int pad, Font f){
        Rectangle rect = new Rectangle(pad, pad, width-(2*pad), hieght-(2*pad));
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(f);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(str)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setColor(Color.WHITE);
        // Set the font
        g.setFont(f);
        // Draw the String
        g.drawString(str, x, y);
    }
    public static BufferedImage getImg(String str){
        InputStream is = SweeperUtil.class.getResourceAsStream("/img/"+str+".png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return img;
    }
}
