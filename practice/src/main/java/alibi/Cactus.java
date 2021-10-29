package alibi;

import processing.core.PImage;

public class Cactus extends GameObject {

    private int xVel;

    public Cactus(int x, int y, int xVel, PImage sprite) {
        super(x, y, sprite);
        this.xVel = xVel;
    }

    public void tick() {
        this.x -= this.xVel;
    }

}