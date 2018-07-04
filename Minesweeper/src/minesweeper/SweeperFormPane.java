/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Sydney
 */
public class SweeperFormPane extends JLayeredPane{
    private static final Font tfont = new Font("Times New Roman", Font.BOLD, 30);
    private final JLabel title = new JLabel("Minesweeper");
    private PresetPanel btns = new PresetPanel();
    private SweeperPreset preset = SweeperPreset.BEGINNER;
    private SweeperCustomParams params = new SweeperCustomParams();
    private SweeperBtn launch = new SweeperBtn("Start");
    public SweeperFormPane(int h, int w){
        title.setFont(this.tfont);
        title.setForeground(Color.WHITE);
        title.setBounds(93, 5, 170, 35);
        btns.setLocation(15,50);
        params.setLocation(15, 85);
        params.disable();
        launch.setLocation(240, 345);
        launch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               launchGame();
            }
        });
        add(launch);
        add(title);
        add(params);
        add(btns);
        setOpaque(true);
        setBounds(0,0, w, h-30);
        setLayout(null);
        setBackground(SweeperUtil.SBLUE);
        setVisible(true);
    }
    public void launchGame(){
        int m =0;
        int r =0;
        int c =0;
        if(preset == SweeperPreset.CUSTOM){
            m = params.mines();
            r =params.rows();
            c = params.cols();
        }
        new SweeperGameWin(preset, r,c,m);
        ((SweeperLaunchWin) this.getParent()).dispose();
    }
    private class PresetPanel extends JPanel{
        private static final int width = 325;
        private static final int height = 25;
        private final JLabel ptitle = new JLabel("Preset:");
        private JComboBox combo;
        private String[] opts = new String[]{"Beginner", "Intermediate", "Advanced", "Custom"};
        public PresetPanel(){
            this.combo = new JComboBox(this.opts);
            this.combo.setBounds(70,0, 200, 25);
            ptitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
            ptitle.setForeground(Color.WHITE);
            ptitle.setBounds(0,0, 65, 20);
            this.combo.addActionListener(new PresetListener());
            add(ptitle);
            add(combo);
            setSize(width,height);
            setLayout(null);
            setBackground(SweeperUtil.SBLUE);
            setVisible(true);
        }
        private void setPreset(){
           String value = (String)this.combo.getSelectedItem();
           preset = SweeperPreset.valueOf(value.toUpperCase());
           if(preset == SweeperPreset.CUSTOM){
               params.enable();
           }
           else{
               params.disable();
           }
        }
        private class PresetListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                setPreset();
            }
        }
    }
}
