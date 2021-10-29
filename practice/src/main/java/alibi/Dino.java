package alibi;

import processing.core.PImage; 
import processing.core.PApplet; 

import java.util.List;

public class Dino extends GameObject {

    private final int jumpHeight = 12;

    private int ground;
    private int yVel;
    private boolean alive;

    private List<Cactus> cacti;

    public Dino(int x, int y, PImage sprite, List<Cactus> cacti) {
        super(x, y, sprite);

        this.ground = y;
        this.yVel = 0;
        this.alive = true;
        this.cacti = cacti;
    }

    public void tick() {
        // Handles logic

        if (this.alive) {
            this.y -= this.yVel;

            if (this.y > this.ground) {
                this.y = this.ground;
                this.yVel = 0;
            } else {
                this.yVel--;
            }

            this.checkCollision();
        }
    }

    public void draw(PApplet app) {
        // Handling graphics
        if (this.alive) {
            app.image(this.sprite, this.x, this.y);
        }
    }

    public boolean jump() {
        if (this.y == this.ground) {
            this.yVel = this.jumpHeight;
            return true;
        } else {
            return false;
        }
    }

    public void collide() {
        this.alive = false;
    }

    public void checkCollision() {
        for (Cactus cactus : this.cacti) {

            int cactusLeft = cactus.getX();
            int cactusRight = cactus.getX() + cactus.getWidth();
            int cactusTop = cactus.getY();
            int cactusBottom = cactus.getY() + cactus.getHeight();
            int dinoRight = this.x + this.sprite.width;
            int dinoBottom = this.y + this.sprite.height;
            
            if (dinoRight > cactusLeft && this.x < cactusRight && dinoBottom > cactusTop && this.y < cactusBottom) {
                this.collide();
                return;
            }
        }
    }
}