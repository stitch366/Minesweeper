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
public enum SweeperPreset {
    BEGINNER(9,9,10), INTERMEDIATE(16,16,40), ADVANCED(16,30,99), CUSTOM(0,0,0);
    private int rows;
    private int cols;
    private int mines;
    SweeperPreset(int r, int c, int m){
        this.rows = r;
        this.cols = c;
        this.mines = m;
    }
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;
    }
    public int getMines(){
        return this.mines;
    }
}
