package ua.com.alevel;

public class TriangleArea {
    private int xA;
    private int yA;
    private int xB;
    private int yB;
    private int xC;
    private int yC;

    TriangleArea(int xA, int yA, int xB, int yB, int xC, int yC) {
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
        this.xC = xC;
        this.yC = yC;
    }

    public double area() {
        return (1d / 2) * Math.abs(xA * (yB - yC) + xB * (yC - yA) + xC * (yA - yB));
    }
}
