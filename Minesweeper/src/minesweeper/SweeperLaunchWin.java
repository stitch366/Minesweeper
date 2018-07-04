/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Sydney
 */
public class SweeperLaunchWin extends Frame{
    private final int h =415;
    private final int w = 355;
    private SweeperFormPane form = new SweeperFormPane(h, w);
    public SweeperLaunchWin(){
        SweeperUtil.test();
        int padv = 30;
        int padh = 0;
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        }); 
        setTitle("Minesweeper");
        form.setLocation(padh, padv);
        setResizable(false);
        add(form);
        setSize(w,h); 
        setLayout(null);
        setVisible(true);
    }
}
