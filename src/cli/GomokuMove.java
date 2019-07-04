/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

/**
 *
 * @author otmangx
 */
public class GomokuMove {
    
    int x;
    int y;
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
        return hash;
    }

    /**
     *
     * @param x
     * @param y
     */
    public GomokuMove(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        GomokuMove move = (GomokuMove) o;
        if (move.x ==x && move.y==y) return true;
        else return false;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     *
     * @return
     */
    public int[] getXY() {
        return new int[]{x,y};
    }
    
    
}
