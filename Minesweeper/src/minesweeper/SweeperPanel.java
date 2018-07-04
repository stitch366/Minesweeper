/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Sydney
 */
public class SweeperPanel extends JLayeredPane {
    private final SweeperPanel me = this; 
    private Minefield field;
    private SweeperClock clock= new SweeperClock();
    private SweeperBtn controlsbtn = new SweeperBtn("Controls", "C", 30);
    private SweeperBtn resetbtn = new SweeperBtn("Reset", "R", 30);
    private SweeperDialog dialog;
    private static final int MIN_WIDTH = 190;
    private int nHeight=0;
    private int nWidth=0;
    private int rows;
    private int cols;
    private int mines;
    public SweeperPanel(int r, int c, int m){
        this.rows = r;
        this.cols = c;
        this.mines = m;
        this.setup();
    }
    public SweeperPanel(SweeperPreset preset){
        this.rows = preset.getRows();
        this.cols = preset.getCols();
        this.mines = preset.getMines();
        this.setup();
    }
    public SweeperPanel(String enumstr){
        SweeperPreset preset = SweeperPreset.valueOf(enumstr.toUpperCase());
        this.rows = preset.getRows();
        this.cols = preset.getCols();
        this.mines = preset.getMines();
        this.setup();
    }
    private void mainlayer(){
        removeAll();
        int layer = 0;
        this.field = new Minefield(this.rows,this.cols,this.mines);
        setLayer(this.field, layer);
        this.field.setLocation(10, 40);
        add(this.field);
        setLayer(this.clock, layer);
        this.clock.setLocation(15, 10);
        add(this.clock);
        setLayer(this.controlsbtn, layer);
        this.controlsbtn.setLocation(130,10);
        add(this.controlsbtn);
        this.controlsbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showDialog();
            }
        });
        setLayer(this.resetbtn, layer);
        this.resetbtn.setLocation(165,10);
        add(this.resetbtn);
        this.resetbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                me.reset();
            }
        });
        
    }
    private void setup(){
        this.mainlayer();
        this.nHeight = 25+20+this.field.getNheight()+5;
        this.nWidth = (MIN_WIDTH < field.getNwidth())? 20+field.getNwidth(): 20+MIN_WIDTH;
        setOpaque(true);
        setBounds(0,0, this.nWidth, this.nHeight);
        setLayout(null);
        setBackground(SweeperUtil.SBLUE);
        setVisible(true);
    }
    private void showDialog(){
        if(this.dialog == null){
            this.dialog = new SweeperDialog((Frame) this.getParent());
            dialog.setVisible(true);
            dialog.addWindowListener(new WindowAdapter(){  
                public void windowClosing(WindowEvent e) {  
                    hidedialog();  
                }  
            }); 
        } 
    }
    public void hidedialog(){
        if(this.dialog != null){
            dialog.dispose();
            this.dialog = null;
        }
    }
    public void startgame(){
        clock.start();
    }
    public void gameend(){
        clock.stop();
        ((SweeperGameWin) this.getParent()).setResultScrn(field.getRes(), clock.getTime(), field.getRevealed());
        ((SweeperGameWin) this.getParent()).gameend();
    }
    public void reset(){
        clock.stop();
        clock.reset();
        int layer = 0;
        remove(this.field);
        this.field = new Minefield(this.rows,this.cols,this.mines);
        setLayer(this.field, layer);
        this.field.setLocation(10, 40);
        add(this.field);
        clock.start();
    }
    public int getNheight(){
        return this.nHeight;
    }
    public int getNwidth(){
        return this.nWidth;
    }
    public int nrows(){
        return this.rows;
    }
    public int ncols(){
        return this.cols;
    }
    public int nmines(){
        return this.mines;
    }
    
}
