package ua.com.alevel;

public class KnightMove {

    private int positionX;
    private int positionY;

    KnightMove(int positionA, int positionB) {
        this.positionX = positionA;
        this.positionY = positionB;
    }

    public boolean isPossible(int positionX, int positionY) {
        int axisXMove = Math.abs(Math.abs(this.positionX) - Math.abs(positionX));
        if (axisXMove < 1 || axisXMove > 2) {
            return false;
        }
        int axisYMove = Math.abs(Math.abs(this.positionY) - Math.abs(positionY));
        if (axisYMove < 1 || axisYMove > 2) {
            return false;
        }
        return Math.abs(axisXMove - axisYMove) == 1;
    }

    public void move(int positionX, int positionY) {
        if (isPossible(positionX, positionY)) {
            this.positionX = positionX;
            this.positionY = positionY;
        }
    }

}
