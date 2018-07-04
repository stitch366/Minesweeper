/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
/**
 *
 * @author Sydney
 */
public class Minefield extends Panel {
    private Minefield me = this;
    private int nrows = 8;
    private int ncols = 8;   
    private int mines = 10;
    private FeildPlot[][] cells;
    private boolean mineRevealed = false;
    private int totalPlots;
    private int revealed;
    private int nheight =0;
    private int nwidth=0;
    private GameResult res;
    Minefield(int r, int c, int m){
      this.nrows =  r;
      this.ncols = c;
      this.mines = m;
      this.totalPlots = this.nrows * this.ncols;
      this.nheight = (this.nrows*FeildPlot.SIZE)+10;
      this.nwidth = (this.ncols*FeildPlot.SIZE)+10;
      this.generate();
      setBounds(0, 0, this.nwidth, this.nheight);
      setLayout(null);
      setVisible(true);
      
      
    }
    public GameResult getRes(){
        return this.res;
    }
    private void generate(){
        removeAll();
        this.res = GameResult.NO_RESULT;
        this.revealed = 0;
        this.cells = new FeildPlot[this.nrows][this.ncols];
        int min=0;
        Random rn = new Random();
        int[] miner = new int[this.mines];
        int[] minec = new int[this.mines];
        for(int x =0; x < this.mines; x++){
            miner[x]= rn.nextInt((this.nrows-1) - min + 1)+min;
            minec[x]=rn.nextInt((this.ncols-1) - min + 1)+min;
        }
        boolean mcheck=false;
        for(int r=0; r < this.nrows; r++){
            for(int c =0; c < this.ncols; c++){
                for(int m =0; m < this.mines; m++){
                    mcheck = ((miner[m] == r)&&(minec[m] == c));
                    if(mcheck){
                        break;
                    }
                }
                this.cells[r][c]= new FeildPlot(r,c,mcheck);
                this.cells[r][c].setVal(miner, minec);
                add(this.cells[r][c]);
                this.cells[r][c].addMouseListener(new PlotClickListener(r,c));
            }
        }
    }
    public FeildPlot getPlot(int r, int c){
        return this.cells[r][c];
    }
    public int getRevealed(){
        return this.revealed;
    }
    //returns true if plot is a mine
    private boolean pressPlot(int sr, int sc){
        boolean mine = false;
        if((0<=sr && sr < nrows) && (0<=sc && sc < ncols)){
            FeildPlot plot = getPlot(sr, sc);
            if(!plot.isPressed()){
                mine = getPlot(sr, sc).press();
                getPlot(sr, sc).deleteListeners();
                getPlot(sr, sc).repaint();
                if(plot.getVal().equals("0")){
                    pressPlot(sr-1, sc-1);
                    pressPlot(sr-1, sc+1);
                    pressPlot(sr+1, sc-1);
                    pressPlot(sr+1, sc+1);

                    pressPlot(sr, sc+1);
                    pressPlot(sr, sc-1);
                    pressPlot(sr+1, sc);
                    pressPlot(sr-1, sc);
                }
                if(!mine){
                    revealed = revealed+1;
                }
            }
        }
        
        return mine;
    }
    public int getNheight() {
        return nheight;
    }
    public int getNwidth() {
        return nwidth;
    }
    public void output(){
        String str="";
        for(int r=0; r < this.nrows; r++){
            str="";
            for(int c=0; c < this.ncols; c++){
                str+=this.cells[r][c].getVal()+" ";
                if(c != this.ncols-1){
                    str+="| ";
                }
            }
            System.out.println(str);
        }
    }
    public void disable(){
        for(int r =0; r < this.nrows; r++){
            for(int c =0; c < this.ncols; c++){
                this.cells[r][c].deleteListeners();
            }
        }
    }
    private class PlotClickListener implements MouseListener{
      private int r;
      private int c;
      public PlotClickListener(int r, int c){
          this.r = r;
          this.c = c;
      }
      private String btn(MouseEvent e){
          String b = "middle";
          if(SwingUtilities.isLeftMouseButton(e)){
              b="left";
          }
          else if(SwingUtilities.isRightMouseButton(e)){
              b="right";
          }
          return b;
      }
      private void pressAction(){
          mineRevealed = pressPlot(r,c);
            if(mineRevealed || (revealed == (totalPlots - mines))){
                if(mineRevealed){
                    res = GameResult.LOST;
                }
                else{
                    res = GameResult.WON;
                }
               ((SweeperPanel) me.getParent()).gameend();
            }
      }
      
      private void markAction(){
          cells[r][c].toggleMark();
      }
      private void act(String btn){
          
          switch(btn){
              case "left":
                  this.pressAction();
                  break;
              case "right":
                  this.markAction();
                  break;
              default:
                  break;
          }
            
      }	

        @Override
        public void mouseClicked(MouseEvent e) {
            //this.act(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.act(this.btn(e));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
   }
}
