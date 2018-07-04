/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.Timer;
/**
 *
 * @author Sydney
 */
public class SweeperClock extends Component{
    private static final Font clockfont =  new Font("Times New Roman", Font.PLAIN, 20);
    private final SweeperClock me = this;
    private int totalsec=0;
    private final Timer timer;
    private String time="000:00:00";
    private static final int HEIGHT = 30;
    private static final int WIDTH = 110;
    private static final int BTHICK = 5;
    private static final int PAD = 5;
    public SweeperClock(){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                me.update();
            }
        };
        this.timer = new Timer(1000, taskPerformer);
        setBounds(0,0, WIDTH, HEIGHT);
        setVisible(true);
    }
    private void setTime(){
        int sub=0;
        int hr = totalsec/3600;
        sub = hr*3600;
        int min = (totalsec-sub)/60;
        sub = sub + (min*60);
        int sec = totalsec-sub;
        this.time = String.format ("%03d:%02d:%02d", hr,min, sec);
    }
    private void update(){
        this.totalsec=totalsec+1;
        this.setTime();
        repaint();
    }
    public void paint(Graphics g){
        SweeperUtil.bevelborder(g,WIDTH, HEIGHT, BTHICK, false);
        SweeperUtil.centerTXT(g, WIDTH, HEIGHT, time, PAD, clockfont);
    }
    public void start(){
        this.timer.start();
    }
    public void stop(){
        this.timer.stop();
    }
    public void reset(){
        this.totalsec =0;
        this.setTime();
        repaint();
    }
    public String getTime(){
        return this.time;
    }
}
