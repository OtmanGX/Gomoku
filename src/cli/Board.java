/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.io.Serializable;

/**
 *
 * @author otmangx
 */


public abstract class Board implements Serializable{

    public char[][] board;
    public char nextPlayer;
    public char prevPlayer;
    public char winner;
    int n;
    int m;
    /**
	 * Board constructor
	 * @param  n board dimension
	 * @param  m winning chain length
	 */
    public Board(int n, int m) {
        this.n = n;
	this.m = m;
        this.board = new char[n][n];
    }
    
}
