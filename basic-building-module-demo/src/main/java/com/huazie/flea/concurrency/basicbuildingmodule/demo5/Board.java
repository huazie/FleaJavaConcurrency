package com.huazie.flea.concurrency.basicbuildingmodule.demo5;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Board {

    private int x, y;

    private Board board;

    public Board(int x, int y) {
        init(x, y);
    }

    private void init(int x, int y) {
        x = this.x;
        y = this.y;
    }

    public void setNewValue(int x, int y, Board board) {
        init(x, y);
        board = this.board;
    }

    public void commitNewValues() {
    }

    public Board getSubBoard(int count, int i) {
        return null;
    }

    public boolean hasConverged() {
        return false;
    }

    public int getMaxX() {
        return 0;
    }

    public int getMaxY() {
        return 0;
    }

    public void waitForConvergence() {
    }
}
