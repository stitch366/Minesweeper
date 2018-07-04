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
import javax.swing.*;
public class SweeperDialog extends Dialog{
    private static final Font dialogfont = new Font("Times New Roman", Font.PLAIN, 20);
    public SweeperDialog(Frame parent){
        super(parent);
        String str1 = "Left-Click: Press Plot";
        String str2 = "Right-Click: Mark Plot";
        setTitle("Minesweeper");
        JPanel content = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                //box-top
                g.drawLine(15, 34, 225, 34);
                g.drawLine(15, 35, 225, 35);
                g.drawLine(15, 36, 225, 36);
                //box-bottom
                g.drawLine(15, 89, 225, 89);
                g.drawLine(15, 90, 225, 90);
                g.drawLine(15, 91, 225, 91);
                //box-left
                g.drawLine(14, 34, 14, 91);
                g.drawLine(15, 35, 15, 90);
                g.drawLine(16, 36, 16, 89);
                //box-right
                g.drawLine(226, 34, 226, 91);
                g.drawLine(225, 35, 225, 90);
                g.drawLine(224, 36, 224, 89);

                
            }
        };
        setResizable(false);
        setLayout(null);
        content.setLayout(null);
        content.setBackground(SweeperUtil.SBLUE);
        content.setBounds(0, 20, 260, 255);
        setSize(240, 130);
        content.add(this.makeText(new Font("Times New Roman", Font.BOLD, 25), Color.WHITE, SwingConstants.CENTER ,"Controls", 25, 10, 200, 25));
        content.add(this.makeText(dialogfont, Color.WHITE, SwingConstants.LEFT ,str1, 25, 40 , 200, 25));
        content.add(this.makeText(dialogfont, Color.WHITE, SwingConstants.LEFT , str2, 25, 65, 200, 25));
        add(content);
        
    }
    private JLabel makeText(Font f, Color fcolor, int align, String str, int x, int y, int w, int h){
        JLabel l =new JLabel(str, align);
        l.setBounds(x, y, w, h);
        l.setFont(f);
        l.setForeground(fcolor);
        return l;
    }
}
