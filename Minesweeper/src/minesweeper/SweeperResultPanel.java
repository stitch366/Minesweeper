/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Sydney
 */
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
public class SweeperResultPanel extends JLayeredPane{
    private static final Color sblue = new Color(66, 134, 244);
    private JLabel[] titles = new JLabel[]{new JLabel("Time:"), new JLabel("Grid Size:"),new JLabel("Mines Generated:"),new JLabel("Safe Plots Revealed:")};
    private HashMap<String, JLabel> stats = new HashMap<String, JLabel>();
    private String[] statlist = new String[]{"time", "grid", "mines", "plots"};
    private SweeperBtn[] btns = new SweeperBtn[]{new SweeperBtn("New Game", "N", 30), new SweeperBtn("Reset", "R", 30), new SweeperBtn("Exit", "E", 30)};
    private JLabel res = new JLabel();
    private int tsafeplots;
    public SweeperResultPanel(int width, int height, int r, int c, int m){
        this.tsafeplots = (r*c)-m;
        loadhash();
        stats.get("grid").setText(r+" x "+ c);
        stats.get("mines").setText(""+m);
        textsetup(res, 20, 10, 200, 30, new Font("Times New Roman", Font.BOLD, 20), Color.WHITE);
        add(res);
        btns[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                donewgame();
            }
        });
         btns[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                doreset();
            }
        });
          btns[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                doexit();
            }
        });
        addTitles(20, 35);
        addStats(20, 50);
        addBtns(20, 180);
        setOpaque(true);
        setBounds(0,0, width, height);
        setLayout(null);
        setBackground(SweeperUtil.SBLUE);
        setVisible(true);
    }
    private void donewgame(){
        new SweeperLaunchWin();
        doexit();
    }
    private void doreset(){
        ((SweeperGameWin) this.getParent()).newgame();
    }
    private void doexit(){
        ((SweeperGameWin) this.getParent()).dispose();
    }
    private void loadhash(){
        for(String k:statlist){
            stats.put(k, new JLabel());
        }
    }
    private void addStats(int x, int sy){
        int y = sy;
        for(String k: statlist){
            textsetup(stats.get(k), x, y, 100, 20, new Font("Times New Roman", Font.PLAIN, 15), Color.WHITE);
            add(stats.get(k));
            y= y+35;
        }
    }
    private void addBtns(int sx, int y){
        int x = sx;
        for(SweeperBtn s:btns){
            s.setLocation(x, y);
            add(s);
            x=x+35;
        }
    }
    private void addTitles(int x, int sy){
        int y = sy;
        for(JLabel j: titles){
            textsetup(j, x, y, 170, 20, new Font("Times New Roman", Font.BOLD, 15), Color.WHITE);
            add(j);
            y= y+35;
        }
    }
    private void textsetup(JLabel l, int x, int y, int w,int h, Font f, Color fcolor){
        l.setBounds(x, y, w, h);
        l.setFont(f);
        l.setForeground(fcolor);
    }
    public void setResult(GameResult r, String t, int p){
        if(r == GameResult.WON){
            res.setText("You Won!");
        }
        else{
           res.setText("You Lost..."); 
        }
        stats.get("time").setText(t);
        stats.get("plots").setText(""+p+"/"+this.tsafeplots);
       
    }
    
}
