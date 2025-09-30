package fr.ylombardi.adventofcode.y2018.d10;

public class Firefly {
    private int x;
    private int y;
    private final int velocityX;
    private final int velocityY;

    public Firefly(int x, int y, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }
}
