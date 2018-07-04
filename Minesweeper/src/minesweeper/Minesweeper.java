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
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here,
        //win = new SweeperGameWin(SweeperPreset.BEGINNER, 0,0,0)
        new Minesweeper();
        
        
    }
    public Minesweeper(){
        new SweeperLaunchWin();
    }
}
