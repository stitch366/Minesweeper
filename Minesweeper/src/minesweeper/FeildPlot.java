/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.event.*;

/**
 *
 * @author Sydney
 */
public class FeildPlot extends Component{
    public static final int SIZE= 20;//40
    private static final int BTHICK= 3;
    private int row =0;
    private int col=0;
    private String val="0";
    private boolean isMine=false;
    private boolean pressed= false;
    private boolean marked=false;
    public FeildPlot( int r, int c, boolean m){
        this.row =r;
        this.col = c;
        this.isMine = m;
        if(this.isMine){
            this.val="M";
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBounds((this.col*SIZE)+5, (this.row*SIZE)+5, SIZE, SIZE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        BufferedImage img;
        SweeperUtil.bevelborder(g,SIZE, BTHICK, (!pressed));
        if(this.pressed){
            img= SweeperUtil.getImg(this.val); 
            g.drawImage(img, 4,4, null);
         }
         else if(this.marked){
             img= SweeperUtil.getImg("S"); 
             g.drawImage(img, 3,3, null);
         }  
    }
    //returns if this plot contains a mine;
    public boolean press(){
        this.pressed = true;
        //repaint();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return this.isMine;
    }
    public void deleteListeners(){
        for( MouseListener listener : this.getMouseListeners() ) {
            this.removeMouseListener(listener);
        }
    }
    public void toggleMark(){
        this.marked = (this.marked)? false: true;
        repaint();
    }
    public void setVal(int[] miner, int[] minec){
        if(!this.isMine){
            int[] rpool ={(this.row-1), (this.row-1), (this.row-1), (this.row), (this.row+1),(this.row+1),(this.row+1), (this.row)};
            int[] cpool ={(this.col-1), (this.col), (this.col+1), (this.col+1), (this.col+1), (this.col), (this.col-1), (this.col-1)};
            int num=0;
            for(int x =0; x < rpool.length; x++){
                for(int m=0; m < miner.length; m++){
                    if((rpool[x] == miner[m])&&(cpool[x] == minec[m])){
                        num = num+1;
                        break;
                    }
                }
            }
            this.val=""+num;
        }
    }
    public String getVal(){
        return this.val;
    }
    public boolean isPressed(){
        return this.pressed;
    }
    
}
