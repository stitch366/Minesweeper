/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Sydney
 */
public class SweeperCustomParams extends JPanel{
    private static final Color sblue = new Color(66, 134, 244);
    private static final Font f = new Font("Times New Roman", Font.PLAIN, 20);
    private static final Font f2 = new Font("Times New Roman", Font.BOLD, 20);
    private static final int width = 325;
    private static final int height = 255;
    private JSlider[] sliders;
    private JLabel[] slideTitles;
    private JLabel[] slideout;
    private int[] values = new int[]{0,0,0};
    private final int ROW = 0;
    private final int COL = 1;
    private final int MINE = 2;
    private boolean enabled = true;
    public SweeperCustomParams(){
        sliders = new JSlider[]{slide(9, 30, 9, 3, 1), slide(9, 30, 9, 3, 1), doubleslide()};
        slideTitles = new JLabel[]{new JLabel("Rows:"), new JLabel("Columns:"), new JLabel("Mine Ratio:") };
        slideout = new JLabel[]{new JLabel(), new JLabel(),new JLabel()};
        prep();
        setSize(width,height);
        setLayout(null);
        setBackground(this.sblue);
        setVisible(true);
    }
    public int mines(){
        int totalp = cols()*rows();
        double pcnt = (double)values[MINE]/100;
        double mines = totalp*pcnt;
        return (int)Math.round(mines);
    }
    public int cols(){
        return values[COL];
    }
    public int rows(){
        return values[ROW];
    }
    private void setOutLbl(int i){
        int v = sliders[i].getValue();
        this.values[i] = v;
        String str = ""+v;
        if(i==2){
            str+="%";
        }
        slideout[i].setText(str);
        
    }
    public void enable(){
        this.enabled = true;
        toggleComs();
    }
    public void disable(){
        this.enabled = false;
        toggleComs();
    }
    private void toggleComs(){
        for(JSlider s:sliders){
            s.setEnabled(this.enabled);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        line(g, 0,0, true, height, 3);
        line(g, 0,0, false, width, 3);
        line(g, width-3,0, true, height, 3);
        line(g, 0,height-3, false, width, 3);


    }
    private void line(Graphics g, int sx, int sy, boolean isVert, int length, int thick){
        if(isVert){
            g.fillRect(sx,sy, thick,length);
        }
        else{
            g.fillRect(sx,sy, length,thick);
        }
    }
    private void prep(){
        int i =0;
        int x = 10;
        int y = 10;
        for(JLabel j:slideTitles){
            textSetup(j, true);
            j.setBounds(x, y, 200, 20);
            y = y+80;
        }
        y= 30;
        for(JSlider s:sliders){
            s.setBounds(x, y, 250, 50);
            s.addChangeListener(new SliderListener(i));
            y = y +80;
            i=i+1;
        }
        x=265;
        y=10;
        for(JLabel j:slideout){
            textSetup(j,false);
            j.setBounds(x, y+25, 50, 20);
            y = y+80;
        }
        i=0;
        while(i<3){
            setOutLbl(i);
            add(slideTitles[i]);
            add(sliders[i]);
            add(slideout[i]);
            i=i+1;
            
        }
    }
    private void textSetup(JLabel lbl, boolean title){
        if(title){
            lbl.setFont(f2);
        }
        else{
            lbl.setFont(f);
        }
        
        lbl.setForeground(Color.WHITE);
    }
    private JSlider slide(int min, int max, int def, int mas, int mis){
        JSlider slider= new JSlider(JSlider.HORIZONTAL,min, max, def);
        slider.setMajorTickSpacing(mas);
        slider.setMinorTickSpacing(mis);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setBackground(this.sblue);
        slider.setForeground(Color.WHITE);
        slider.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        return slider;
    }
    private JSlider doubleslide(){
        JSlider slider = slide(10,50, 12, 5, 1);
        Hashtable labeltable = new Hashtable();
        JLabel temp;
        int i = 10;
        double act;
        while(i <= 50){
            
            act = (double)i/100;
            temp= new JLabel(""+i+"%");
            //temp= new JLabel(""+act);
            temp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            temp.setForeground(Color.WHITE);
            labeltable.put(new Integer(i), temp);
            i = i+5;
        }
        slider.setLabelTable(labeltable);
        return slider;
    }
    private class SliderListener implements ChangeListener{
        private int index;
        public SliderListener(int i){
            this.index = i;
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            setOutLbl(this.index);
        }
    }
}
