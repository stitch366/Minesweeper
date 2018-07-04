/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;
import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
/**
 *
 * @author Sydney
 */
public class SweeperBtn extends JComponent {
    private Font btnfont = new Font("Times New Roman", Font.PLAIN, 15);
    private String label="";
    private int height=25;
    private int width=100; 
    private BufferedImage img = null;
    private boolean isimgbtn = false;
    private static final int PAD=5;
    private static final int BTHICK = 5;
    public SweeperBtn(String l){
        this.label = l;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBounds(0,0, width, height);
        setVisible(true);
        
    }
    public SweeperBtn(String l, int width, int height, int ftsize){
        this.label = l;
        this.width = width;
        this.height = height;
        this.btnfont = new Font("Times New Roman", Font.PLAIN, ftsize);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBounds(0,0, width, height);
        setVisible(true);
        
    }
    public SweeperBtn(String l, String img, int size){
        this.label = l;
        this.width = size;
        this.height = size;
        this.isimgbtn = true;
        this.img = SweeperUtil.getImg(img);
        setToolTipText(this.label);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBounds(0,0, width, height);
        setVisible(true);
    }
    
    public void paint(Graphics g){
        SweeperUtil.bevelborder(g, width, height, BTHICK, true);
        if(this.isimgbtn){
             g.drawImage(this.img, BTHICK,BTHICK, null);
        }
        else{
            SweeperUtil.centerTXT(g, width, height, label, WIDTH, btnfont);
        }
    }
}
