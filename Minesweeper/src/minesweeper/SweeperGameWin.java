/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import java.awt.*;  
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;  
/**
 *
 * @author Sydney
 */
public class SweeperGameWin extends Frame{
    private SweeperPanel game;
    private SweeperResultPanel resultsrcn;
    private int height =0; 
    private int width =0;
    public SweeperGameWin(SweeperPreset preset, int r, int c, int m){
        int padv = 30;
        int padh = 0;
        if(preset != SweeperPreset.CUSTOM){
            this.game = new SweeperPanel(preset);
        }
        else{
            this.game = new SweeperPanel(r,c,m);
        }
        game.setLocation(padh, padv);
        setTitle("Minesweeper");
        game.startgame();
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
        this.height = padv+game.getNheight();
        this.width = padh+game.getNwidth();
        this.resultsrcn= new SweeperResultPanel(game.getNwidth(), game.getNheight(), game.nrows(), game.ncols(), game.nmines());
        this.resultsrcn.setLocation(padh, padv);
        resultsrcn.setVisible(false);
        add(game);
        add(resultsrcn);
        setResizable(false);
        setSize(this.width,this.height); 
        setLayout(null);
        setVisible(true);
    }
    public void setResultScrn(GameResult r, String time, int plots){
        resultsrcn.setResult(r, time, plots);
    }
    public void gameend(){
       game.setVisible(false);
       resultsrcn.setVisible(true);
    }
    public void newgame(){
        resultsrcn.setVisible(false);
        game.setVisible(true);
        game.reset();
    }
}
